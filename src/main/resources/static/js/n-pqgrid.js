/**
 * pqgrid 공동 기능 js
 */
//============================================================
// layoutSetting  : Name Default 셋팅
//============================================================

let LOlayoutSave, LOlayoutSetting, LOlayoutReset, LOexport, LOexportCsv, LOexportHtml, LOexportJson, LOexportXlsx
  , LOnumberCell, LOselectionModel, LOselectionModelCell, LOselectionModelRow, LOfreeze, LOcolsCancel
  , LOrowsCancel, LOfreezeCol, LOfreezeRow, LOcolumnBorders, LOrowBorders, LOstripeRows, LOaddNodes
  , LOundo, LOredo, LOgroupModel, LOcopy, LOpaste, TBallFields, TBenterYourKeyword, TBfind, TBadd, TBreset, TBdelete, TBrefresh, TBsave;
//============================================================
// 공통 그리드
//============================================================
let GridUtil = function(colModel, progrId, gridId){
	//colModel : 각 열의 속성(title, width, dataIndex..)배열을 정의
	//progrId : gridId를 생성하는데 사용되는 문자열
	//gridId : 그리드의 id를 나타냄

	let gridWidth	= "atuo";
	// gridWidth는 auto로 설정

	if(!colModel || !progrId || !gridId){  // colModel, progrId, gridId 중 하나라도 빈 값이면 alert창 띄우기
		alert("그리드 정보가 잘못되었습니다.");
		return;
	}
		// 사용자 그리드 셋팅 & 컬럼 레이아웃 & layoutSetting 툴 Name 가져오기.
	let data = callAjax("data", "GET", "/sy/syu10Select.do", { progrid : progrId, pgridid : gridId });
	this.colModel = colModel;
	//common.js에 있는 callAjax 함수 호출
	if(data && data.gridSettingLayout.length > 0){	//gridSettingLayout : SGRIDL테이블 조회
		const layoutInfos = data.gridSettingLayout;
		for(const layoutInfo of layoutInfos){
			this.numberCell = layoutInfo.nubrcel;		//new GridUtil로 생성한 객체의 numberCell(행 번호)의 값은 layoutInfo.nubrcel
			this.hoverMode = layoutInfo.hovermd;		//new GridUtil로 생성한 객체의 hoverMode의 값은 layoutInfo.hovermd
			this.freezeCols = layoutInfo.frezcol;		//new GridUtil로 생성한 객체의 freezeCols(고정시킬 총 열 개수 지정)의 값은 layoutInfo.frezcol
			this.freezeRows = layoutInfo.frezrow;		//new GridUtil로 생성한 객체의 freezeRows(고정시킬 총 행 개수 지정)의 값은 layoutInfo.frezrow
			this.columnBorders = layoutInfo.colbodr;	//new GridUtil로 생성한 객체의 columnBorders(세로 테두리)의 값은 layoutInfo.colbodr
			this.rowBorders = layoutInfo.rowbodr;		//new GridUtil로 생성한 객체의 rowBorders(가로 테두리)의 값은 layoutInfo.rowbodr
			this.stripeRows = layoutInfo.strprow;		//new GridUtil로 생성한 객체의 stripeRows(그리드의 행에 대한 스타일을 교대로 지정하는 기능)의 값은 layoutInfo.strprow
			this.height = layoutInfo.gheight;			//new GridUtil로 생성한 객체의 height(높이)의 값은 layoutInfo.gheight
		}
		this.colModel = setColModel(colModel, data.gridColumnLayout);
		layoutSettingName(data.layoutSettingName);	//layoutSetting : 언어 설정?
	// layoutSettingName 함수 호출, data.layoutSettingName을 매개변수로 받음
	}
	this.progrId = progrId ? progrId : null;
	this.gridId = gridId ? gridId : null;


	this.setTitle = function(title){	// new GridUtil로 생성한 객체의 title의 값은 해당 객체의 setTitle 함수를 호출해서 매개변수로 입력받은 title 값이다.
		this.title = title;
	}

    this.setHeight = function(height){
    	this.height = height;
    }

	// create 이벤트 정의/재정의
	this.create = function() {
		this.widget().pqTooltip();	// new GridUtil로 객체 생성 후 create 함수를 호출하면 해당 객체의 widget 함수의 pqTooltip 함수가 호출된다.
	}
	this.setCreate = function(fun) {  // new GridUtil로 객체 생성 후 setCreate 함수를 호출하면 create 함수는 fun 함수로 재정의된다.
		this.create = fun;
	}

	this.setChange = function(fun){		// new GridUtil로 객체 생성 후 setChange 함수를 호출하면 change 함수는 fun 함수로 재정의된다.
		this.change = fun;
	}

	this.setSelectChange = function(fun){		// new GridUtil로 객체 생성 후 setSelectChange 함수를 호출하면 selectChange 함수는 fun 함수로 재정의된다.
		this.selectChange = fun;
	}

	// cellSave 이벤트 정의/재정의
	this.setCellSave = function(fun){		// new GridUtil로 객체 생성 후 setCellSave 함수를 호출하면 cellBeforeSave 함수는 fun 함수로 재정의된다.
		this.cellSave = fun;
	}

	this.setCellBeforeSave = function(fun){		// new GridUtil로 객체 생성 후 setCellBeforeSave 함수를 호출하면 cellBeforeSave 함수는 fun 함수로 재정의된다.
		this.cellBeforeSave = fun;
	}

	this.setCellClick = function(fun){		// new GridUtil로 객체 생성 후 setCellClick 함수를 호출하면 cellClick 함수는 fun 함수로 재정의된다.
		this.cellClick = fun;
	}

	this.setCellKeyDown = function(fun){		// new GridUtil로 객체 생성 후 setCellKeyDown 함수를 호출하면 cellKeyDown 함수는 fun 함수로 재정의된다.
		this.cellKeyDown = fun;
	}

	this.setBeforeValidate = function(fun){		// new GridUtil로 객체 생성 후 setBeforeValidate 함수를 호출하면 beforeValidate 함수는 fun 함수로 재정의된다.
		this.beforeValidate = fun;
	}

	this.setCheck = function(fun){		// new GridUtil로 객체 생성 후 setCheck 함수를 호출하면 check 함수는 fun 함수로 재정의된다.
		this.check = fun;
	}

	this.setBeforeCheck = function(fun){		// new GridUtil로 객체 생성 후 setBeforeCheck 함수를 호출하면 beforeCheck 함수는 fun 함수로 재정의된다.
		this.beforeCheck = fun;
	}

	this.setRowDblClick = function(fun){		// new GridUtil로 객체 생성 후 setRowDblClick 함수를 호출하면 rowDblClick 함수는 fun 함수로 재정의된다.
		this.rowDblClick = fun;
	}

	this.setEditorBegin = function(fun){		// new GridUtil로 객체 생성 후 setSelectChange 함수를 호출하면 selectChange 함수는 fun 함수로 재정의된다.
		this.editorBegin = fun;
	}

	this.setEditorEnd = function(fun){		// new GridUtil로 객체 생성 후 setSelectChange 함수를 호출하면 selectChange 함수는 fun 함수로 재정의된다.
		this.editorEnd = fun;
	}

	this.setEditorKeyUp = function(fun){		// new GridUtil로 객체 생성 후 setEditorKeyUp 함수를 호출하면 editorKeyUp 함수는 fun 함수로 재정의된다.
		this.editorKeyUp = fun;
	}

	this.setBeforeCheck = function(fun){		// new GridUtil로 객체 생성 후 setBeforeCheck 함수를 호출하면 beforeCheck 함수는 fun 함수로 재정의된다.
		this.beforeCheck = fun;
	}

	this.setRowInit = function(fun){	// new GridUtil로 객체 생성 후 setRowInit 함수를 호출하면 rowInit 함수는 fun 함수로 재정의된다.
		this.rowInit = fun;
	}

	this.setRowClick = function(fun){	// new GridUtil로 객체 생성 후 setRowInit 함수를 호출하면 rowInit 함수는 fun 함수로 재정의된다.
		this.rowClick = fun;
	}

	this.setCellDblClick = function(fun){		// new GridUtil로 객체 생성 후 setCellDblClick 함수를 호출하면 cellDblClick 함수는 fun 함수로 재정의된다.
		this.cellDblClick = fun;
	}

	this.setHeaderCellClick = function(fun) {	// new GridUtil로 객체 생성 후 setHeaderCellClick 함수를 호출하면 headerCellClick 함수는 fun 함수로 재정의된다.
		this.headerCellClick = fun;
	}

	this.setHistory = function(fun) {	// new GridUtil로 객체 생성 후 setHeaderCellClick 함수를 호출하면 headerCellClick 함수는 fun 함수로 재정의된다.
		this.history = fun;
	}

	this.contextMenu = {	//그리드 내에서 마우스 우클릭 시 나타나는 메뉴의 속성 설정
	    on: true ,
	    headItems: layoutSetting ,   //header context menu items.
	    cellItems: layoutSetting     //body context menu items
	},

	this.tabModel = {
	};

	this.setTabModel = function(arr){
		this.tabModel = {
			noAdd: true,
			tabs : !arr ? [{name : 'Try search first',	noRename : true}] : arr,
			newTab : function(){
				return {
					noClose: true,
					noRename: true
				}
			}
		}
		this.contextMenu.on = false;
	}

    this.groupModel = {	// 그룹화 설정
        on: false,	// 그룹화 활성화 여부
        collapsed: [false, true],	// 그룹화된 항목 축소 여부
        summaryInTitleRow: 'all',	// 그룹화된 항목의 요약정보를 표시할 위치
        merge: true,	// 그룹화된 항목의 셀 병합 여부
        showSummary: [true, true],	// 그룹화된 항목의 요약 정보 표시 여부
        grandSummary: true	// 모든 그룹화된 항목의 총 요약 정보 표시 여부,
    }
    this.setGroupModel = function(groupModel){
    	this.groupModel = groupModel;
    }

	this.setMergeCells = function (fun){
		this.mergeCells = fun;
	}

	this.setSort = function(fun){
		this.sort = fun;
	}

	// toolbar : find 이벤트 정의/재정의
	let findTb = false;
	this.setFind = function(fun){		// findTb가 false로 정의 되어있는데 new GridUtil로 객체 생성 후 setFind 함수를 호출하면 findTb가 true로 재정의된다.
		findTb = true;
	}

	// toolbar : add 이벤트 정의/재정의
	let addTb = false;
	let addFun = function(evt, ui){
	}
	this.setAdd = function(fun){		// addTb가 false로, addFun 함수는 evt, ui를 매개변수로 받는 빈 함수로 정의 되어있는데 new GridUtil로 객체 생성 후 setAdd 함수를 호출하면 addTb는 true로, addFun 함수는 fun 함수로 재정의된다.
		addTb = true;
		addFun = fun;
	}

	// toolbar : reset 이벤트 정의/재정의
	let resetTb = false;
	this.setReset = function(fun){		// resetTb가 false로 정의 되어있는데 new GridUtil로 객체 생성 후 setReset 함수를 호출하면 resetTb가 true로 재정의된다.
		resetTb  = true;
	}

	// toolbar : delete 이벤트 정의/재정의
	let deleteTb = false;
	let deleteFun = function(evt, ui){
	}
	this.setDelete = function(fun){		// deleteTb가 false로, deleteFun 함수는 evt, ui를 매개변수로 받는 빈 함수로 정의 되어있는데 new GridUtil로 객체 생성 후 setAdd 함수를 호출하면 deleteTb는 true로, deleteFun 함수는 fun 함수로 재정의된다.
		deleteTb = true;
		deleteFun = fun;
	}

	// toolbar : refresh 이벤트 정의/재정의
	let refreshTb = false;
	let refreshFun = function(evt, ui){
	}

	// toolbar : save 이벤트 정의/재정의
	let saveTb = false;
	let saveFun = function(evt, ui){
	}
	this.setSave = function(fun){		// saveTb가 false로, saveFun 함수는 evt, ui를 매개변수로 받는 빈 함수로 정의 되어있는데 new GridUtil로 객체 생성 후 setSave 함수를 호출하면 saveTb는 true로, saveFun 함수는 fun 함수로 재정의된다.
		saveTb = true;
		saveFun = fun;
	}

	this.setRefresh = function(fun){	// refreshTb가 false로, refreshFun 함수는 evt, ui를 매개변수로 받는 빈 함수로 정의 되어있는데 new GridUtil로 객체 생성 후 setAdd 함수를 호출하면 refreshTb는 true로, refreshFun 함수는 fun 함수로 재정의된다.
		refreshTb = true;
		refreshFun = fun;
	}

	function toolbarItems(){
		let items = new Array();	// items 배열 객체 생성

		items.push(  // 아래 구조체들을 items에 push
			// find 활성화 여부
			{ 	type: 'select',		// type은 select
				style: findTb === true ? '' : 'display: none;',		// findTb가 true면 보이고 아니면 안보이게
				cls: 'findColumn',			// class이름 findColumn
				listener: findHandler,		// findHandlerd 이벤트 발생
				options: function (ui) {
					let CM = ui.colModel;
                    let opts = [{ '': TBallFields ? TBallFields : allCategoryMsg }];
                    for (let column of CM) {
                        let obj = {};
                        if(column.dataIndx !== 'state' && column.hidden !== true && column.dataType !== 'integer'){
    						obj[column.dataIndx] = column.title;
                        	opts.push(obj);
    					}
                    }
                    return opts;
				}
			},
			{ 	type: 'textbox', 	// type은 textbox
				style: findTb === true ? '' : 'display: none;',
				attr: 'placeholder="' + (TBenterYourKeyword ? TBenterYourKeyword : 'enter your keyword') + '"',
				//	placeholor는 TBenterYourKeyword 값이 있다면 TBenterYourKeyword, 값이 없으면 enter your keyword
				cls: 'findValue',		// class이름 findValue
				listener: { timeout: findHandler }		// timeout:findHandler 이벤트 발생 => 사용자가 검색 기능 사용 시 검색 대상 데이터 찾을 때까지 대기할 시간 지정
			},
			{ 	type: 'button',		// type은 button
            	style: findTb === true ? '' : 'display: none;',
            	label: "<img/>" + " " + (TBfind ? TBfind : 'Find') + " ",		//label은 TBfind, 없으면 Find
            	cls : 'pq-search-btn btn-search btn-navy',		// class이름 pq-search-btn
            	listener: focusHandler		//focusHandler 이벤트 발생
            },
            // reset 활성화 여부
			{ 	type: 'button',		// type은 button
				style: resetTb === true ? 'float: right;' : 'display: none;',		// resetTb true면 float: right; 아니면 안보이게
				// resetTb가 true면 float:right, 아니면 안보이게
				cls : 'pq-action-btn btn-white',		// class이름 pq-action-btn
				label: "<img class='ic-bt-reset'>" + " " + (TBreset ? TBreset : 'Reset') + " ",
				// label은 <i class='ui-button-icon-primary ui-icon ui-icon-refresh-f'></i> + TBreset(없으면 Reset)
				listener: function () {
					if(!confirm(modifyRollbackMsg)){
						return;
					}
					this.rollback();	// 이벤트 발생 : rollback 함수 실행 => 이전으로 되돌리기
					this.history({ method: 'reset' });	//이전 상태를 기록하고 이력 관리 수행(사용자가 실수로 데이터를 삭제하거나 변경한 경우, 이전 상태로 되돌릴 수 있다) => 취소/다시실행 기능에서 사용
				}
			},
			// delete 활성화 여부
			{ 	type: 'button',		// type은 button
				style: deleteTb === true ? 'float: right;' : 'display: none;',		// deleteTb true면 float: right; 아니면 안보이게
				// deleteTb true면 float:right, 아니면 안보이게
				cls : 'pq-action-btn btn-white',		// class이름 pq-action-btn
				label: "<img class='ic-bt-delete'>" + " " + (TBdelete ? TBdelete : 'Delete') + " ",
				// label은 <i class='fa-regular fa-trash-can ui-icon-trash-f'></i> + TBdelete(없으면 Delete)
				listener: deleteFun		//  deleteFun 이벤트 발생
			},
            // add 활성화 여부
            { 	type: 'button',		// type은 button
				style: addTb === true ? 'float: right;' : 'display: none;',		// addTb true면 float: right; 아니면 안보이게
				// addTb true면 float:right, 아니면 안보이게
				cls: 'pq-tool-btns pq-action-btn btn-white',		// class이름 pq-tool-btns pq-action-btn
				label: "<img class='ic-bt-add'>" + " " + (TBadd ? TBadd : 'Add') + " ",
				// label은 <i class='ui-button-icon-primary ui-icon ui-icon-add-f'></i> + TBadd(없으면 Add)
				listener: addFun		// addFun 이벤트 발생
			},
			// refresh 활성화 여부
			{ 	type: 'button',		//type은 button
				style: refreshTb === true ? 'float: right;' : 'display: none;',		// refreshTb true면 float: right; 아니면 안보이게
				cls : 'pq-action-btn btn-white',		// class이름 pq-action-btn
				label: "<img class='ic-bt-reset'>" + " " + (TBrefresh ? TBrefresh : 'Refresh') + " ",
				// label은 <i class='ui-button-icon-primary ui-icon ui-icon-refresh-f'></i> + TBrefresh(없으면 Refresh)
				listener: refreshFun		// refreshFun 이벤트 발생
			},
			// save 활성화 여부
			{ 	type: 'button',		//type은 button
				style: saveTb === true ? 'float: right;' : 'display: none;',		// refreshTb true면 float: right; 아니면 안보이게
                cls : 'pq-action-btn btn-navy btn-green',		// class이름 pq-action-btn
                label: "<img class='ic-bt-save'>" + " " + (TBsave ? TBsave : 'save') + " ",
				// label은 <i class='ui-button-icon-primary ui-icon ui-icon-refresh-f'></i> + TBrefresh(없으면 Save)
				listener: saveFun		// refreshFun 이벤트 발생
			}
		);
		return items;		// items 배열을 리턴
	}

	this.open = function(){		// new GridUtil로 생성한 객체의 open 함수 ==> 해당 객체의 속성값 설정
		let obj = {
			title: this.title,		//그리드 제목
			toolbar: { cls: "pq-toolbar-search", items: toolbarItems() },	//toolbar 설정
			warning: { icon: '', style: '', cls: '' },	//경고 메세지 설정
			bootstrap: { on: false },	//부트스트랩 CSS 사용 여부 false
			width: gridWidth,	//width는 gridWidth(=auto)로 설정  ==> 그리드 너비
			height: this.height || 500,	//그리드 높이, 0으로 설정하면 높이는 650으로 지정됨
			colModel: this.colModel,	//각 개체가 열 속성에 해당하는 개체의 배열입니다.
			contextMenu: this.contextMenu,
	        numberCell: { show: this.numberCell ? this.numberCell : true, minWidth: 50 }, 		//row(행)번호
			// numberCell : {show:(numberCell의 값이 있으면 해당 numbercell, 없으면 false)}
	        hoverMode: this.hoverMode ? this.hoverMode : 'row',             //Mouse Over Mode(selection)
			// hoverMode : 마우스 오버 시 선택되는 항목의 모드 설정
	        selectionModel: { type: 'cell', mode: 'block'},
	        // selectionModel : 그리드에서 선택되는 영역의 모드 설정
	        freezeCols: this.freezeCols ? this.freezeCols : 0,
			// freezeCols : freezeCols의 값이 있으면 해당 freezeCols 값 만큼 열 고정, 없으면 0
	        freezeRows: this.freezeRows ? this.freezeRows : 0,
			// freezeRows : freezeRows 값이 있으면 해당 freezeRows 값 만큼 행 고정, 없으면 0
	        columnBorders: this.columnBorders ? this.columnBorders : true,
			// columnBorders : columnBorders 값이 있으면(true) 열(세로) 테두리 그리기, 없으면 true(테두리 O)
	        rowBorders: this.rowBorders ? this.rowBorders : true,
			// rowBorders : rowBorders 값이 있으면(true) (행)가로 테두리 그리기, 없으면 true(테두리 O)
	        stripeRows: this.stripeRows ? this.stripeRows : true,
			// stripeRows : stripeRows 값이 있으면 행에 대한 스타일 교대로 지정, 없으면 true
	        groupModel: this.groupModel,
	        gprogrid: this.progrId, 	//그리드가 속한 프로그램 ID
	        resizable: true,             //그리드 크기 조절 가능 여부
	        menuIcon: false,             //컬럼 아이콘
	        collapsible: {on: false, toggle: false},	//상단 툴바의 오른쪽 아이콘 두개(크게보기, 줄이기)
	        showTitle: this.title ? true : false,	//타이틀 나오는 상단 툴바
	        showToolbar: (findTb || addTb || resetTb || deleteTb || refreshTb || saveTb) ? true : false,	//버튼 나오는 상단 툴바.
	        hwrap: false,	//줄 바꿈
	        wrap : false,	// true인 경우 셀의 텍스트가 다음 줄로 줄 바꿈되고 그렇지 않으면 넘친 텍스트가 숨겨지고 연속 기호 ...가 끝에 표시됩니다.
			//rowHt: 32,	//그리드의 모든 행의 높이를 일정하게 설정합니다.
			trackModel: { on: true },	// 그리드 인라인 추가, 업데이트 및 삭제 작업에 대한 추적 속성을 설정
			postRenderInterval: -1,	//그리드 보기 새로 고침과 column.postRender 에 대한 첫 번째 호출 사이의 시간 간격(밀리초)을 결정합니다 .
	        scrollModel: { autoFit: false },	//Grid는 스크롤 없이 뷰포트에 모두 맞도록 열의 너비를 변경, 그리드 너비의 모든 크기 조정/변경은 열 너비의 비례 변경
	        editor: { select: true }, 	//전체 그리드에 대한 편집기 속성을 제공합니다.
			//flex: { on: false, one: false, all: false },	//이 옵션은 모든 셀 내용이 줄 바꿈 없이 한 줄에 표시되는 방식으로 열 또는 열 수를 설정하는 데 도움이 됩니다.
	        columnTemplate: findTb ? {render: findRender} : false,	//이 옵션은 DRY 원칙에 따라 모든 열에서 속성이 반복되지 않도록 공통 열 속성을 정의합니다(반복하지 않음).
	        create: this.create,	//그리드가 생성될 때 트리거(이벤트 발생)됩니다.
	        change: this.change,	//인라인 셀 편집, 메소드 호출을 통한 행 추가/업데이트/삭제, 행/셀 붙여넣기, 실행 취소, 다시 실행으로 인해 그리드 데이터가 변경된 후 트리거됩니다.
	        selectChange: this.selectChange,	// 선택된 행이 변경될 때 이벤트 발생
	       	editorBegin : this.editorBegin,
	       	editorEnd : this.editorEnd,
	       	cellSave : this.cellSave,
	        cellBeforeSave: this.cellBeforeSave,	//인라인 편집 중에 셀이 pqGrid에 저장되기 전에 트리거됩니다. false를 반환하여 데이터 저장을 취소할 수 있습니다.
	        cellClick : this.cellClick,	// 셀을 클릭할 때 이벤트 발생
	        cellKeyDown : this.cellKeyDown,	// 셀에서 키보드를 누를때 이벤트 발생
	        beforeValidate : this.beforeValidate,	// 셀에 입력한 값의 유효성을 검사하기 전에 이벤트 발생
	        check : this.check,	// 체크박스 셀 관리 기능
	        beforeCheck : this.beforeCheck,	// 체크박스를 체크/언체크 하기 전에 이벤트 발생
	        rowDblClick : this.rowDblClick,	// 행 더블클릭 시 이벤트 발생
	        editorKeyUp : this.editorKeyUp,	// 셀 에디터에서 키보드를 눌렀다 떼었을 때 이벤트 발생
	        rowInit : this.rowInit,	// 각각의 행이 초기화 될 때 이벤트 발생
	        rowClick : this.rowClick,
	        cellDblClick : this.cellDblClick,	// 셀을 더블클릭할 때 이벤트 발생
	        headerCellClick: this.headerCellClick,	//헤더셀을 클릭할 때 이벤트 발생
	        history: this.history,	// 히스토리에 접근했을 때 발생
			tabModel: this.tabModel,	// 탭 UI를 표시하기 위한 속성을 제공합니다.
			mergeCells: this.mergeCells,
            sort: this.sort,
            skipSingleSummary: true, // summary 열이 1개일 시 합계를 스킵함
            summaryTitle: {avg: "평균 : {0}", count: '횟수 : {0}', max: "최대 : {0}", min: "최소 : {0}", sum: getCaseBySummaryTitle } // summary 랜더링
		};

		this.gridDom = pq.grid('#' + this.gridId, obj);
    	// new GridUtil로 생성한 객체의 gridDom 값은 pq.grid('#' + this.gridId, obj)
		// gridDom : 그리드의 뷰를 생성하고 관리하는 객체, pq.grid('#그리드명') ==> #그리드명 : id가 그리드명인 HTML 요소

	};

	//======== 2022.08.30 SWH 추가 ==========
	//======== grid Destroy =======
	this.destroy = function(){		// new GridUtil로 생성한 객체의 destroy 함수는 해당 객체의 gridDom의 destroy 함수를 실행
		this.gridDom.destroy();		// destroy : 해당 그리드 기능을 제거하고 요소를 초기화 전 상태로 되돌림
	}

	//======== grid dataModel =======
	this.searchList = function(data){	// new GridUtil로 생성한 객체의 searchList 함수는 해당 객체의 gridDom의 showLoading, option, hideLoading, refreshDataAndView 함수를 실행
		this.gridDom.showLoading();		// 로딩 보여주기
		this.gridDom.option( "dataModel", { data: data, recIndx: "rowkey" } );  //recIndx : record index
		this.gridDom.hideLoading();		//로딩 사라짐
		this.gridDom.refreshDataAndView();	// 새로고침
	}

	this.getGrid = function(){		// new GridUtil로 생성한 객체의 getGrid 함수는 해당 객체의 gridDom 값을 리턴
		return this.gridDom;
	}

	this.getChanges = function(){		// new GridUtil로 생성한 객체의 getChanges 함수는 해당 객체의 gridDom의 getChanges 함수를 리턴
		return this.gridDom.getChanges({all:true});	// 그리드에서 변경된 값 가져오기
	}

	//===========================================
	// Common Grid Preview (mdu2 참조)
	//===========================================
	this.preViewShowPage = function(url, param){	//그리드 뷰의 미리보기 창을 화면에 표시(url : 미리보기 창에 표시할 url)

		let urlParameters = "";
		let tURL = "";

		$("#"+gridId).closest(".grid-container").addClass("fl-block");	//선택된 gridId로 지정된 id 속성을 가진 HTML 요소에서 가장 가까운 grid-container 클래스를 갖는 요소에 fl-block 클래스 추가
		$("#"+gridId).closest(".grid-container").find(".grid-preview").addClass("active");	//.find는 선택된 HTML 요소의 하위 요소 중 grid-preview 클래스를 갖는 요소를 선택해서 active 클래스 추가

		if(url != null){
			if(param != null) urlParameters = Object.entries(param).map(e => e.join('=')).join('&');
			tURL = url+"&"+urlParameters;

			$("#"+gridId).closest(".grid-container").find(".grid-preview").load(tURL);
		}
	}

	this.preViewShow = function(){	// 뷰 페이지 보여주기, 내용 초기화?
		let gridColDom 		= $("#"+gridId).closest(".row").find(".grid-col");
		let preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");

		preViewColDom.show();  // preViewColDom 보여주기?

		gridColDom.removeClass();	// gridColDom에 클래스 삭제
		gridColDom.addClass("grid-col col-md-9");	// gridColDom에 클래스 추가

		preViewColDom.removeClass();
		preViewColDom.addClass("preview-col col-md-3");
		preViewColDom.find(".grid-preview").children().remove();	// preViewColDom에 grid-preview 클래스를 가진 요소의 자식 요소 삭제
	}

	this.preViewAppendDom = function(dom){	// 미리보기 창에 새로운 dom 요소 추가
		let preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
		preViewColDom.find(".grid-preview").children().remove();
		preViewColDom.find(".grid-preview").append(dom);	// preViewColDom에 grid-preview 클래스를 가진 요소에 dom 요소 추가
	}

	this.preViewRemoveDom = function(){	// dom 요소 제거
		let preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
		preViewColDom.find(".grid-preview").children().remove();
	}

	this.preViewHide = function(){	// 미리보기 창 숨기기
		let gridColDom 		= $("#"+gridId).closest(".row").find(".grid-col");
		let preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");

		gridColDom.removeClass();
		gridColDom.addClass("grid-col col-md-12");

		preViewColDom.removeClass();
		preViewColDom.addClass("preview-col");
		preViewColDom.hide();	// preViewColDom 숨기기?
		preViewColDom.find(".grid-preview").children().remove();
	}

	this.preViewRemove = function(){	//미리보기 창 내용 삭제
		let preViewColDom 	= $("#"+gridId).closest(".row").find(".preview-col");
			preViewColDom.find(".grid-preview").children().remove();
	}
}

//	this.removeData

//>>>>>>>>>>>>>>>>>>>>>>>>>여기까지 GridUtil<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

//============================================================
//공통 그리드 : grid Column Layout 재정의
//============================================================
function setColModel(colModel, gridColMD){
	for(let gridCol of gridColMD){
		for(let idx = 0; idx < colModel.length; idx++){
			const col = colModel[idx]
			if(col.dataIndx === gridCol.dataidx){
				//hidden 값 추가 부분
				if(gridCol.phidden !== null){
					col.hidden = gridCol.phidden;
				}
				//Width 값 추가 부분
				if(gridCol.pqwidth !== null){
					col.width = gridCol.pqwidth;
				}
				//순서 바꾸기
				const item = colModel.splice(idx, 1);
				colModel.splice(gridCol.sortnum, 0, item[0]);
			}
		}
	}
	return colModel;
}

//============================================================
// layoutSetting
//============================================================
function layoutSetting(evt, ui){
    return [
        {
        	name: LOlayoutSave ? LOlayoutSave : 'Layout Save',
        	action: function(){
				if(window['setLayoutSetting']){
        			window['setLayoutSetting'](this);
				}
        	}
        },
        {
        	name: LOlayoutSetting ? LOlayoutSetting : 'Layout Setting',
        	action: function(){
				modalgridlayoutshow(this);
        	}
        },
        {
        	name: LOlayoutReset ? LOlayoutReset : 'Layout Reset',
        	action: function(){
				if(window['setLayoutReset']){
        			window['setLayoutReset'](this);
				}
        	}
        },
        'separator',
        {
            name: LOexport ? LOexport : 'Export',
            subItems: [
            	{
					name: LOexportCsv ? LOexportCsv : 'csv',
            	    action: function(){
						exportData.call(this, 'csv');
					}
				},
                {
					name: LOexportHtml ? LOexportHtml : 'html',
					action: function(){
						exportData.call(this, 'html');
					}
				},
                {
					name: LOexportJson ? LOexportJson : 'json',
					action: function(){
						exportData.call(this, 'json');
					}
				},
                {
					name: LOexportXlsx ? LOexportXlsx : 'xlsx',
					action: function(){
						exportData.call(this, 'xlsx');
					}
				}
            ]
        },
        'separator',
        {
        	name: LOnumberCell ? LOnumberCell : 'Row Number',
        	action: function(){
        		let numberCell = this.option( "numberCell" );
        		this.option("numberCell.show", !numberCell.show);
        		this.refresh();
        	}
        },
        {
        	name: LOselectionModel ? LOselectionModel : 'Selection Model',
        	subItems: [
        		{
					name: LOselectionModelCell ? LOselectionModelCell : 'cell',
					action: function( ){
						this.option( "hoverMode", "cell");
						this.refresh();
        			}
        		},
        		{
					name: LOselectionModelRow ? LOselectionModelRow : 'row',
					action: function( ){
						this.option( "hoverMode", "row");
						this.refresh();
        			}
        		}
        	],
        },
        {
        	name: LOfreeze ? LOfreeze : 'Freeze',
        	subItems: [
        		(this.option('freezeCols') ? { name: LOcolsCancel ? LOcolsCancel : 'Cols Cancel', action: function(){ this.option('freezeCols', 0); this.refresh(); } } : null),
        		(this.option('freezeRows') ? { name: LOrowsCancel ? LOrowsCancel : 'Rows Cancel', action: function(){ this.option('freezeRows', 0); this.refresh(); } } : null),
        		'separator',
        		{name: LOfreezeCol ? LOfreezeCol : 'Col Freeze',  action: function(evt, ui){ this.option("freezeCols", ui.colIndx+1); this.refresh();}},
        		{name: LOfreezeRow ? LOfreezeRow : 'Row Freeze',  action: function(evt, ui){ this.option("freezeRows", ui.rowIndx+1); this.refresh();}}
        	],
        },
        {
        	name: LOcolumnBorders ? LOcolumnBorders : 'Column Borders',
        	action: function(){
        		let b = this.option( "columnBorders" );
        		this.option("columnBorders", !b);
        	}
        },
        {
        	name: LOrowBorders ? LOrowBorders : 'Row Borders',
        	action: function(){
        		let b = this.option( "rowBorders" );
        		this.option("rowBorders", !b);
        	}
        },
        {
        	name: LOstripeRows ? LOstripeRows : 'Stripe Rows',
        	icon: 'ui-icon ui-icon-shuffle',
        	action: function(){
        		let b = this.option( "stripeRows" );
        		this.option("stripeRows", !b);
        		this.refresh();
        	}
        },
        'separator',
        {
            name: LOundo ? LOundo : 'Undo',
            icon: 'ui-icon ui-icon-arrowrefresh-1-n',
            disabled: !this.History().canUndo(),
            action: function(){
                this.History().undo();
            }
        },
        {
            name: LOredo ? LOredo : 'Redo',
            icon: 'ui-icon ui-icon-arrowrefresh-1-s',
            disabled: !this.History().canRedo(),
            action: function(){
                this.History().redo();
            }
        },
        'separator',
        {
			name: LOgroupModel ? LOgroupModel : 'Data Group',
			icon: 'ui-icon ui-icon-calculator',
            action: function () {
				let b = this.option( "groupModel.on" );
                this.Group().option({ on: !b });
            }
		},
        'separator',
        {
            name: LOcopy ? LOcopy : 'Copy',
            icon: 'ui-icon ui-icon-copy',
            shortcut: 'Ctrl - C',
            tooltip: "Works only for copy / paste within the same grid",
            action: function(){
                this.copy();
            }
        },
        {
            name: LOpaste ? LOpaste : 'Paste',
            icon: 'ui-icon ui-icon-clipboard',
            shortcut: 'Ctrl - V',
            action: function(){
                this.paste();
            }
        }
    ]
}

//============================================================
// layoutSetting : Layout 툴 Name 재정의
//============================================================
function layoutSettingName(layoutInfos){
	for(const layoutInfo of layoutInfos){
		LOlayoutSave = layoutInfo.layoutSave;
		LOlayoutSetting = layoutInfo.layoutSetting;
		LOlayoutReset = layoutInfo.layoutReset;
		LOexport = layoutInfo.export;
		LOexportCsv = layoutInfo.exportCsv;
		LOexportHtml = layoutInfo.exportHtml;
		LOexportJson = layoutInfo.exportJson;
		LOexportXlsx = layoutInfo.exportXlsx;
		LOnumberCell = layoutInfo.numberCell;
		LOselectionModel = layoutInfo.selectionModel;
		LOselectionModelCell = layoutInfo.selectionModelCell;
		LOselectionModelRow = layoutInfo.selectionModelRow;
		LOfreeze = layoutInfo.freeze;
		LOcolsCancel = layoutInfo.colsCancel;
		LOrowsCancel = layoutInfo.rowsCancel;
		LOfreezeCol = layoutInfo.freezeCol;
		LOfreezeRow = layoutInfo.freezeRow;
		LOcolumnBorders = layoutInfo.columnBorders;
		LOrowBorders = layoutInfo.rowBorders;
		LOstripeRows = layoutInfo.stripeRows;
		LOaddNodes = layoutInfo.addNodes;
		LOundo = layoutInfo.undo;
		LOredo = layoutInfo.redo;
		LOgroupModel = layoutInfo.groupModel;
		LOcopy = layoutInfo.copy;
		LOpaste = layoutInfo.paste;

		TBallFields = layoutInfo.allFields;
		TBenterYourKeyword = layoutInfo.enterYourKeyword;
		TBfind = layoutInfo.find;
		TBadd = layoutInfo.add;
		TBreset = layoutInfo.reset;
		TBdelete = layoutInfo.delete;
		TBrefresh = layoutInfo.refresh;
		TBsave = layoutInfo.save;
	}
}

//============================================================
// layoutSetting : export 이벤트 정의
//============================================================
function exportData(format){	//그리드의 데이터를 다양한 형식으로 내보내기 위한 기능 제공
    let blob = this.exportData({
        format : format
    })
    if(typeof blob === "string"){
        blob = new Blob([blob]);
    }
    saveAs(blob, "logistics_"+ this.origOptions.gprogrid + "." + format );
}

//============================================================
// Grid Find : Data 찾기 이벤트
//============================================================
let findRules = [];

function findHandler() {	//이벤트 핸들러 등록 시 해당 함수가 이미 등록되어 있는지 확인하고 이미 등록되어 있다면 해당 함수를 반환하여 중복 등록 방지
	let $toolbar = this.$top.find('.pq-toolbar-search');
	let value = $toolbar.find(".findValue").val();
	let dataIndx = $toolbar.find(".findColumn").val();

    if (dataIndx === "") {
    	let obj = [];
    	this.getColModel().map(function(column){
        	if(column.dataIndx === 'state' || column.hidden === true || column.dataType === 'integer'){
        		return;
        	}else{
        		obj.push({ dataIndx: column.dataIndx, value: value });
        	}
        })
        findRules = obj;
    }else {
    	findRules = [{ dataIndx: dataIndx, value: value}];
	}
    this.refreshView();
}

function findRender(ui) {	// 특정 텍스트가 발견되면 해당 부분을 하이라이트 표시
    if(ui.cellData !== null && ui.cellData !== undefined && ui.cellData !== '' && ui.dataIndx !== 'state' && findRules.length > 0 && ui.column.dataType !== 'integer' && ui.column.dataType !== 'date' && ui.column.type !== 'checkbox' && ui.column.dataType !== 'float' && ui.column.dataType !== 'bool'){
		let valType = typeof ui.cellData;
		let val = ui.cellData.toString();
		let valUpper = val.toUpperCase();
		let col = ui.dataIndx;
		let indx = -1;

		for(const findRule of findRules){
			let txt = findRule.value.toUpperCase();
			indx = valUpper.indexOf(txt);
			if(col === findRule.dataIndx){
				if (indx >= 0) {
					let txt1 = val.substring(0, indx);
					let txt2 = val.substring(indx, indx + txt.length);
					let txt3 = val.substring(indx + txt.length);
					return txt1 + "<span style='background:yellow;color:#333;'>" + txt2 + "</span>" + txt3;
				}
			}
		}
		return valType === "string" ? val : Number(val);
	}
}

function getSelectedIdx(focusRules, sel){
	let selectedIdx = 0;
	for(let idx = 0; idx < focusRules.length; idx++){
		if(sel.isSelected({rowIndx: focusRules[idx].rowIndx, colIndx: focusRules[idx].colIndx})){
			selectedIdx =  idx;
		}
	}
	return selectedIdx;
}

//============================================================
// Grid Find : Find Data 포커스 이벤트.
//============================================================

function focusHandler() {
    let toolbar = this.$top.find('.pq-toolbar-search');
    let value = toolbar.find(".findValue").val();
    if(!value){
        return;
    }

    let datas = this.getData();
    let val = value?.toString();
    let valUpper = val?.toUpperCase();
    let focusRules = [];

    for(let data of datas){
        for(let findRule of findRules){
            let dataVal = !data[findRule.dataIndx] ? '' : data[findRule.dataIndx].toString();
            let dataValUpper = dataVal?.toUpperCase();
            if (dataValUpper.indexOf(valUpper) !== -1) {
                focusRules.push({ rowIndx: data.pq_ri, colIndx: this.getColIndx( { dataIndx: findRule.dataIndx } ) });
            }
        }
    }
    if(focusRules.length){
        let sel = this.Selection();
        let selectedIdx = getSelectedIdx(focusRules, sel);
        if(selectedIdx < focusRules.length){
            this.setSelection( {rowIndx: focusRules[selectedIdx !== focusRules.length-1 ? selectedIdx+1 : 0].rowIndx, colIndx: focusRules[selectedIdx !== focusRules.length-1 ? selectedIdx+1 : 0].colIndx} );
        }
    }
}