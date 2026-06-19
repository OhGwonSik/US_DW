----------------------------------------------------------------------------------------------------
Controller
----------------------------------------------------------------------------------------------------
@RestController
분류 + Controller

Method annotation
	- HttpMethod GET : @GetMapping("경로") - select
	- HttpMethod POST : @PostMapping("경로") - 일부 select / insert & update
	- HttpMethod PUT : @PutMapping("경로") - update
	- HttpMethod DELETE : @DeleteMapping("경로") - delete

	메소드명은 메소드가 수행하는 행위를 뜻하는 동사로 시작한다.
	(get, do, select, to, has, is, contains, validation, save, update, delete, modify...)
----------------------------------------------------------------------------------------------------
BillingMaster - 정산 마스터
Code - 공통, 사유코드 마스터
Common - 공통 사용 데이터(select box) 마스터
Organization - 회사, 창고, 에어리어, 존, 로케이션 마스터
Partner - 화주, 업체 마스터
Units - SKU, 단위, 포장 등 SKU관련 마스터