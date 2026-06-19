
package com.logistics.configuration;

import java.security.Key;
import java.util.*;
import java.util.stream.Collectors;

import com.logistics.common.dto.CommonDTO;
import com.logistics.md.domain.MwarmaDTO;
import com.logistics.md.service.MDO02Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.logistics.sy.domain.UserVO;
import com.logistics.sy.service.UserService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import static java.lang.Math.ceil;

/*
 * 2023 08 04 이재형 (TM)
 * TM 앱 통신 jwt 변경
 * JWT 토큰 사용 JwtTokenProvider :
 * https://gksdudrb922.tistory.com/217
 * https://velog.io/@suhongkim98/Spring-Security-JWT로-인증-인가-구현하기
 * https://inten.tistory.com/entry/Spring-스프링-Security-Token-Provider-만드는-법
 */

@Slf4j
@Component
public class JwtTokenProvider {

    protected final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    // private final CustomUserDetailService customUserDetailService;

    protected static final String AUTHORITIES_KEY = "auth";

    protected final String secretKey;
    protected final long tokenValidityInSeconds;
    protected final long refreshTokenValidityInSeconds;
    private final Key key;

    @Autowired
    private UserService userService;

    @Autowired
    private MDO02Service mdo02Service;

    public JwtTokenProvider(@Value("${jwt.secret}") String secretKey,
                            @Value("${jwt.token-validity-in-seconds}") long tokenValidityInSeconds) {
        this.secretKey = secretKey;
        this.tokenValidityInSeconds = tokenValidityInSeconds; // * 1000;
        this.refreshTokenValidityInSeconds = 30 * this.tokenValidityInSeconds;

        // this.customUserDetailService = customUserDetailService;

        // 시크릿 값을 decode해서 키 변수에 할당
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        // byte[] keyBytes = Base64.getMimeDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // 유저 정보를 가지고 AccessToken, RefreshToken 을 생성하는 메서드
    public TokenDataDTO generateToken(Authentication authentication) {
        // 권한 가져오기
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        UserVO userVo = (UserVO) authentication.getPrincipal();
        String compkey = userVo.getCompkey();
        String useract = userVo.getUseract();

        long now = (new Date()).getTime();
        // Access Token 생성
        Date accessTokenExpiresIn = new Date(now + this.tokenValidityInSeconds);
        Date refreshTokenExpiresIn = new Date(now + this.refreshTokenValidityInSeconds);

        String accessToken = Jwts.builder()
                // .setSubject(authentication.getName())
                // .setSubject(authentication)
                // .setClaims(null)
                .claim("auth", authorities)
                .claim("compkey", compkey)
                .claim("useract", useract)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(refreshTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return TokenDataDTO.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    /*  getAuthentication - 토큰값 복호화 메소드
     *   최초 생성일시	: 2023-08-04
     *   최초 생성자		: 이재형
     *   입력 PARAMETA	: String accessToken
     *   출력 PARAMETA	: UsernamePasswordAuthenticationToken
     *   설명			: 물류 운영 에서 생성한 cookie값 (cookie명 : token) 을 복호화 하여 인증 토큰 객체를 생성한다
     *   수정 내역		:
     *   수정일시		: 2023-10-27
     *	 수정자			: 신원혁
     *	 변경 사항		:
     * 수정 내역2 : rms 새 로그인 토큰에 의한 warekeyList size 0 에러 수정
     * 수정일시 : 2024-01-01
     * 수정자 : 이재형
     * 변경 사항 :
     */
    public Authentication getAuthentication(String accessToken) {
        // 토큰 복호화
        Claims claims = parseClaims(accessToken);
        UserVO userInfo = null;

        if (claims.get("group_code") == null) {
            return null;
        }

        long tokenExpiredTime = Long.parseLong(claims.get("exp").toString());
        Date validity = new Date(tokenExpiredTime*1000);

        // 클레임에서 권한 정보 가져오기
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("group_code").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserVO 객체를 만들어서 Authentication UsernamePasswordAuthenticationToken Return
        String userName 	= claims.get("user_id").toString();					//계정ID      기존 : sub
        String userCompkey 	= claims.get("account_code").toString();			//회사코드     기존 : compkey
        String usertyp		= claims.get("user_type").toString();				//유저타입      변경전 : usertyp // customer, partner, 3pl...
        String ownerky      = claims.get("owner_code") != null ? claims.get("owner_code").toString() : null;              //화주코드
        String ptnrkey      = claims.get("partner_code") != null ? claims.get("partner_code").toString() : null;             //협력사 코드
        String rgroup_id	= claims.get("group_code").toString();			    //창고권한     변경전 : rgroup_id
        String auth			= claims.get("group_code").toString();				//메뉴권한      group_code로 임시변경해놓음    변경전 : auth
        String warekey      = null;
        String langkey      = "";

        if(userCompkey.equals("200")){
            langkey = "us";
        }else if(userCompkey.equals("100")){
            langkey = "gj";
        }else{
            langkey = "";
        }

        if(SecurityContextHolder.getContext()!=null && SecurityContextHolder.getContext().getAuthentication()!=null && SecurityContextHolder.getContext().getAuthentication().getPrincipal()!=null){
            Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if((obj instanceof UserVO)) {
                userInfo = (UserVO) obj;
            }
        }

        if(userInfo == null || (userInfo != null && !userName.equals(userInfo.getUseract()))){
            userInfo = new UserVO();
            userInfo.setUseract(userName);
            userInfo.setCompkey(userCompkey);
            userInfo.setOwnerky(ownerky);
            userInfo.setPtnrkey(ptnrkey);
            userInfo.setLangkey(langkey);
            userInfo.setRolgkey(auth);

            userInfo.setRgropid(rgroup_id);
            userInfo.setUsertyp(usertyp);
            userInfo.setThemety(" ");                                           //2023.10.17 insert SUPAHI 시 에러발생하여 임시로 강제 세팅

            // V_SUSRMA 정보 넣기
            List<UserVO> vSusrmaInfoList = userService.getUserInfoFromViewSusrma(userInfo);
            if (vSusrmaInfoList.size() > 0) {
                userInfo.setUsernam(vSusrmaInfoList.get(0).getUsernam());
                userInfo.setUsertyp(vSusrmaInfoList.get(0).getUsertyp());
            }

            MwarmaDTO mwarmaDTO = new MwarmaDTO();
            mwarmaDTO.setUserData(userInfo);
            List<CommonDTO> warekeyList = mdo02Service.getWarehouseUserSelectBox(mwarmaDTO);    //창고권한 찾기

            if(warekeyList.size() == 0){
                warekey = null;
            }else{
                warekey = warekeyList.get(0).getCombovl() != null ? warekeyList.get(0).getCombovl() : null;
            }

            userInfo.setWaredky(warekey);
        }

        //Locale setting
        Locale.setDefault(new Locale(langkey));
        return new UsernamePasswordAuthenticationToken(userInfo, accessToken, authorities);
    }

    public String getBodyParam(String token, String targetNameString) {
        return (this.parseClaims(token).get(targetNameString)).toString();
    }

    // 토큰 정보를 검증하는 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    public long expiredTokenTime(String accessToken){
        Claims claims = parseClaims(accessToken);

        if (claims.get("group_code") == null) {
            return 0;
        }

        long now = (new Date()).getTime();                                      //현재 시간
        long tokenExpiredTime = Long.parseLong(claims.get("exp").toString());   //인증서버 토큰 exp 값
        long diffTime = (tokenExpiredTime * 1000 - now) / 1000;                 //차이 값 (초)

        return diffTime;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }
}
