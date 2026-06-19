package com.logistics.wm.domain;

import com.logistics.common.dto.CommonDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InventoryDTO extends CommonDTO{
	/*
	 ****************************************************************************************
	 - DTO Name : InventoryDTO
	 - Description : 재고 페이지에서 사용할 필드를 모아놓은 DTO
	 - Made By : 최강호
	 - Creation Date :  2023.07.25
	 -------------------------------------------------------------------------------------------------
	 ****************************************************************************************
	 */
	//WSTKKY
	private String compkey; 			// VARCHAR(20) Company Key             
	private String ownerky;             // VARCHAR(20) 화주                      
	private String stockky;             // VARCHAR(10) 재고키                     
	private String doccate;             // VARCHAR(10) 마지막 문서유형                
	private String doctype;             // VARCHAR(10) 마지막 문서타입                
	private String warekey;             // VARCHAR(20) 창고                      
	private String areakey;             // VARCHAR(20) Area key                
	private String zonekey;             // VARCHAR(20) Zone key                
	private String locakey;             // VARCHAR(20) Locationn key           
	private String skumkey;             // VARCHAR(50) 운영사 sku                 
	private String skudesc;             // VARCHAR(100) 부품명                    
	private String stkstat;             // VARCHAR(10) 재고 상태                   
	private String lotnmky;             // VARCHAR(10) LOT NUMBER              
	private int stotqty; 	            // INT(11) 총재고수량                       
	private int sallqty; 	            // INT(11) 할당수량                        
	private int soubqty; 	            // INT(11) 출고중 수량                      
	private int sbloqty; 	            // INT(11) 블록 수량                       
	private int sinbqty; 	            // INT(11) 입고중 수량                      
	private String lotat01;             // VARCHAR(20) 재고LOT 속성		          
	private String lotat02;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat03;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat04;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat05;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat06;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat07;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat08;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat09;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat10;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat11;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat12;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat13;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat14;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat15;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat16;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat17;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat18;	            // VARCHAR(20) 재고LOT 속성		          
	private String lotat19;             // VARCHAR(20) 재고LOT 속성			      
	private String lotat20;             // VARCHAR(20) 재고LOT 속성                
	private String subsect;	            // VARCHAR(20) SUB Section             
	private String truntyp;             // VARCHAR(10) 이동용기타입                  
	private String trunpid;             // VARCHAR(60) 이동용기 ID                 
	private String vendkey;             // VARCHAR(20) 입고처                     
	private String sirbcod;             // VARCHAR(20) *개별관리코드                 
	private String proddat;             // VARCHAR(8) 생산일자                     
	private int shelife; 	            // INT(11) 유통기한                        
	private String expidat;             // VARCHAR(8) 유통기간                     
	private String rcvdcdt;             // VARCHAR(8) 입고일자                     
	private String rcvfrdt;             // VARCHAR(8) 최초입고일자                   
	private String lotfrky;             // VARCHAR(10) 최초입고LOT NUMBER          
	private String rcvdcky;             // VARCHAR(10) 입고문서번호                  
	private int rcvdcit;	            // INT(11) 입고문서번호 아이템                  
	private String taskoky;             // VARCHAR(10) 작업문서번호                  
	private int taskoit;	            // INT(11) 작업문서번호 아이템                  
	private String shpdcky;	            // VARCHAR(10) 출고문서번호                  
	private int shpdcit;	            // INT(11) 출고문서번호 아이템                  
	private String suomkey;             // VARCHAR(10) Stock UOM               
	private String blockid;             // VARCHAR(10) Block                   
	private String physsec;             // VARCHAR(3) Section Y행               
	private String physflo;             // VARCHAR(3) ROW 3열                   
	private String physrow;             // VARCHAR(3) Floor Z단                 
	private String storfky;             // VARCHAR(10) 재고키 참고번호                
	
	//WADJIT
	private String adjsoky; 			// VARCHAR(10) 조정문서번호   
	private String adjgrky;             // VARCHAR(10) 조정문서 그룹번호
	private String rsncode;             // VARCHAR(10) 사유코드     
	private String adjsrmk;             // VHRCHAR(200) 사유내용    
	private int adjsoit;	            // INT(11) 조정문서번호 아이템   
	
	//WPHYIT
	private String physoky; 			// VARCHAR(10) 실사문서 번호    
	private int physoit; 	            // INT(11) 실사문서 item key  
	private String phyname;             // VARCHAR(200) 실사문서 제목   
	private String phymode;             // VARCHAR(10) 실사문서 상태    
	private int systqty; 	            // INT(11) 시스템 수량         
	private int physqty; 	            // INT(11) 실사수량           
	private int compqty; 	            // INT(11) 확정수량           
	private String physrmk;             // VARCHAR(200) 실사차이 발생내역 
	
	//MAREMA
	private String areanam;
	//MZONMA
	private String zonenam;
	//MLOCMA
	private String locanam;
	
	//OEADJI
	private String oeadjky;				// VARCHAR(10) 물류오더 문서번호
	private int oeadjit;				
	private String oeinsst;				// VARCHAR(10) 물류오더 처리상태
	private int rtodqty;				// INT(11) 조정지시 수량
	private int rtcfqty;				// INT(11) 조정완료 수량
	private String atkstat;				// VARCHAR(10) 조정지시 재고상태
	
	
	
	//CUSTOMIZE
	private int rcvsqty; 				//입하에어리어 수량			사용프로그램 : WMI10
	private int stgsqty;				//보관에어리어 수량			사용프로그램 : WMI10
	private int taksqty;				//작업에어리어 수량			사용프로그램 : WMI10
	private int shpsqty;				//출고에어리어 수량			사용프로그램 : WMI10
	private int syssqty;				//시스템에어리어 수량			사용프로그램 : WMI10
	private String upccode;				// 바코드					사용프로그램 : WMI10
	private String tab;		  			// 재고조회 시 tab         사용프로그램 : WMI10
	private int avalqty;	 			// 가용수량 		  	    사용프로그램 : WHI10
	private boolean zeroqty; 			// 0재고   		        사용프로그램 : WMI11 
	private int scbloqty;    			// 블락수량 		        사용프로그램 : WMI30 , WMI31 
	private int schnqty;	 			// 변경수량			    사용프로그램 : WMI11 , WMI12
	private int diffqty;	 			// 차이수량			    사용프로그램 : WMI22
	private String slctsku;  			// sku 셀렉트박스(검색용)    사용프로그램 : WMI21 
	private String whnamlc;				// 창고명					사용프로그램 : WMI42
	private String ownamlc;				// 화주명					사용프로그램 : WMI42
	private String chskuky;				// 변경 부코드				사용프로그램 : WMI42
	private String chskunm;				// 변경 부품명				사용프로그램 : WMI42
	private String statnam;				// 재고상태명				사용프로그램 : WMI42
}
