------------------------
Service
------------------------
@Service
ProgramID + Service

Method
	- 메소드명은 메소드가 수행하는 행위를 뜻하는 동사로 시작한다. (get, do, select, to, has, is, contains, validation, save, update, delete, modify...)
	- Transaction으로 묶여야 하는경우 반드시 save, update, delete, modify로 시작해야 하며
	  Transaction으로 묶일 필요가 없는 경우는 get, do, select, to, has, is, contains, validation 으로 시작해야 하며 필요한 prefix가 있는경우 담당자한테 요청한다.
	- 메소드에 @Transactional은 사용하지 않는다.
---------------------
program명 = md + 분류 + 프로그램 번호

mdc - code
mdo - organization
mdp - partner
mdu - unit
(정산 / billing은 통합)
--------------------------
mdc06 - 사유코드
mdc07 - 공통코드
mdc10 - 물류달력
--------------------------
mdo01 - 회사
mdo02 - 창고
mdo03 - 에어리어
mdo04 - 존
mdo05 - 로케이션
--------------------------
mdp10 - 화주수신
mdp11 - 화주등록
mdp20 - 업체수신
mdp21 - 업체등록
mdp30 - 도착지등록
--------------------------
mdu01 - 부품수신
mdu02 - 부품등록
mdu04 - 이동용기등록
mdu05 - 입수량등록
mdu10 - 세트구성등록
--------------------------
