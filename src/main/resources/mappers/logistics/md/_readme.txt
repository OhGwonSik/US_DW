------------------------
mybatis SQL
------------------------
SQL 예약어 : 소문자
테이블, 컬럼 : 대문자
MapperName : ERD-SUB Name

        select *
          from SUSRMA
         where COMPKEY=#{compkey}
           and USERACT=#{useract}

------------------------
Mapper List

BillingMasterMapper - 정산 마스터
CodeMapper - 공통, 사유코드 마스터
DocumentMapper - 문서 마스터
OrganizationMapper - 창고, 에어리어, 존, 로케이션 마스터
PartnerMapper - 화주, 업체 마스터
Units - SKU, 단위, 포장 마스터