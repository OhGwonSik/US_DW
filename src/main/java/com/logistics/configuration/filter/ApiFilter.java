package com.logistics.configuration.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter(urlPatterns= "/api/*")
public class ApiFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    Filter.super.init(filterConfig);
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpRequest = (HttpServletRequest) request;
    String apiKey = httpRequest.getHeader("x-api-key");
    String url = httpRequest.getRequestURI();
    log.info("api key => {}", apiKey);
    log.info("api url => {}", url);

    //TODO Key 검증로직 활성화
//    if (url.matches("^/api/v\\d+/dayou/.*")) {
//      if(!"dayouKEY".equals(apiKey)){
//        responseUnauthorized(response);
//        return;
//      }
//    } else {
//      responseNotFound(response);
//      return;
//    }
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
    Filter.super.destroy();
  }

  private void responseUnauthorized(ServletResponse response){
    try {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      //TODO Message 공통화 필요
      httpResponse.getWriter().write("Unauthorized");
    }catch(IOException e){
      log.error("api filter IOException(401) => {}", e);
    }
  }

  private void responseNotFound(ServletResponse response){
    try {
      HttpServletResponse httpResponse = (HttpServletResponse) response;
      httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);
      //TODO Message 공통화 필요
      httpResponse.getWriter().write("Not Found");
    }catch(IOException e){
      log.error("api filter IOException(404) => {}", e);
    }
  }
}
