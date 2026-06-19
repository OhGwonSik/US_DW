/*
------------------------------------------
- 파일명 : common.js
- 최초생성자 : WM
- 최초생성일시 : 2023.06
- 설명 : 공통 변수 및 함수
------------------------------------------
*/
// 전역변수
// Regular Expression
const regEngOnly = /^[a-z|A-Z|0-9]/; // English Only
const regKorOnly = /^[ㄱ-ㅎ|가-힣|0-9]/; // Korean Only
const regNum15Dot10 = /^\d{1,15}.\d{0,10}$/; // 100,000,000,000,000.0000000000
const regNum15Dot3 = /^\d{1,15}.\d{0,3}$/; // 100,000,000,000,000.000
const regNum9Digit = /^\d{1,9}$/; // lower than 1,000,000,000
const reg24Time = /^(0\d|1\d|2[0-3])(0[1-9]|[0-5]\d)(0[1-9]|[0-5]\d)$/; // 24:00:00
const regDivideTime = /(\d{2})(\d{2})(\d{2})/g; // Time string divide
const divideTimeFormat = '$1:$2:$3'; // HH:mm:ss
const regDivideDateYMD = /(\d{4})(\d{2})(\d{2})/g; // Date divide yyyy-mm-dd
const regDivideDateYM = /(\d{4})(\d{2})/g; // Date divide yyyy-mm
const divideDateFormatYMD = '$1-$2-$3'; // yyyy-mm-dd
const divideDateFormatYM = '$1-$2'; // yyyy-mm

//============================================================
// 페이지 새로고침 키 무시(23/11/02 원혁책임님)
//============================================================
function doNotPageReload() {
	if ((event.ctrlKey === true && (event.keyCode === 78 || event.keyCode === 82)) || event.keyCode === 116) {
		event.keyCode = 0;
		event.cancelBubble = true;
		event.returnValue = false;
	}
}

//============================================================
// 페이지 이동
//============================================================
function openWindowPop(url) {
	var options = 'top=10, left=10, width=800, height=800, status=no, menubar=no, toolbar=no, resizable=yes';
	window.open(url, "test", options);
}

function logout(strMsg) {
	//var msg = "[[#{sy.main.auth.userinfoerr}]]";
	alert(strMsg);
	window.location.replace('/login');
}

function sessionTimeOut(strMsg) {
	//var msg = "[[#{sy.main.auth.sessionout}]]";
	alert(strMsg);
	window.location.replace('/login');
}

//============================================================
// 스피너 ON / OFF
//============================================================
function spinnerOpen() {
	$(".spinner-wrapper").show();
}

function spinnerClose() {
	$(".spinner-wrapper").hide();
}

//============================================================
// 공통 콤보박스 (GridID, 컬럼키 & ID , Param, URL)
//============================================================
function Combo(gridnm, colnm, param, url, mapob, type) {

	if (!colnm) {
		return false;
	}

	this.url = url ? url : "/md/common/code/common-code";
	this.url = rPath + this.url;
	if (!gridnm) {
		$.getJSON(this.url, param, function(response) {
			$("#" + colnm + " option").not("[value='']").remove();	//초기화
			if (mapob != null && mapob.combovl != undefined && mapob.combonm != undefined) {
				$.each(response, function(key, value) { $('#' + colnm).append($("<option></option>").attr("value", value[mapob.combovl]).text(value[mapob.combonm])); });
			} else {
				$.each(response, function(key, value) { $('#' + colnm).append($("<option></option>").attr("value", value.combovl).text(value.combonm)); });
			}
		});
	} else {
		$.getJSON(this.url, param, function(response) {
			var grid = pq.grid("#" + gridnm)
				, column = grid.getColumn({ dataIndx: colnm });

			var options = response.map(function(obj) {
				var rObj = {};
				if (mapob != null && mapob.combovl != undefined && mapob.combonm != undefined) {
					rObj[obj[mapob.combovl]] = obj[mapob.combonm];
				} else {
					rObj[obj.combovl] = obj.combonm;
				}
				return rObj;
			});
			column.editor.options = type === true ? response : options;
		});
	}
}

//============================================================
//공통 그리드 날짜 달력 오픈 이벤트(년월일)
//============================================================
function dateEditor(ui) {
	var $inp = ui.$cell.find("input"),
		format = ui.column.format || "yy-mm-dd"
	val = $inp.val(),
		val = val ? $.datepicker.formatDate(format, new Date(val)) : "";

	$inp.attr('readonly', 'readonly')
		.val(val)
		.datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat: format,
			showAnim: '',
			onSelect: function() {
				this.firstOpen = true;
			},
			beforeShow: function(input, inst) {
				setTimeout(function() {
					$('.ui-datepicker').css('z-index', 999999999999);
				});
			},
			onClose: function() {
				this.focus();
			}
		});
}

//============================================================
//공통 그리드 날짜 달력 오픈 이벤트(년월)
//============================================================
function monthEditor(ui) {
	var $inp = ui.$cell.find("input"), format = ui.column.format || "yy-mm"
	val = $inp.val(), val = val ? $.datepicker.formatDate(format, new Date(val)) : "";
	$inp.MonthPicker({ Button: false });
}

//============================================================
//공통 그리드 시간 오픈 이벤트
//============================================================
function timeEditor(ui) {
	var $inp = ui.$cell.find("input");
	$inp.datetimepicker({
		format: "HH:mm:ss",
		stepping: 1,
		icons: {
			up: "bi bi-chevron-up",
			down: "bi bi-chevron-down"
		}
	});
}

function timeEditor2(ui) { // for sustain 6 characters state and no use replace() on DB cols // 23.09.18 Park T. S.
	ui.$cell.find('input').datetimepicker({
		format: 'HHmmss',
		stepping: 1,
		icons: {
			up: 'bi bi-chevron-up',
			down: 'bi bi-chevron-down'
		}
	});
}

//============================================================
//공통 Ajax
//============================================================
var AjaxUtil_Order = function(url, params, type, dataType) {
	if (!url) {
		alert("url 정보가 없습니다.");
		return null;
	}
	// AjaxUtil_Order 생성자 함수를 사용하여 새로운 객체를 생성 할 때, 매개변수(param) url을
	// this 객체 내부의 url 멤버 변수에 할당시키기 위해 사용
	// 인스턴스 변수를 생성하여 객체 인스턴스 내부에서 사용 가능
	this.url = rPath + url;

	var generateJSON = function(params) {
		if (!params) {
			return "";
		}
		return JSON.stringify(params);
	}

	this.type = type ? type : "POST";
	this.dataType = dataType ? dataType : "json";
	this.param = type === "GET" ? params : generateJSON(params);

	this.callbackSuccess = function(json) {
		var data = JSON.stringify(json);
	}
	this.setCallbackSuccess = function(callback) {
		this.callbackSuccess = callback;
	}

	this.complete = function() {

	}
	this.setComplete = function(fun) {
		this.complete = fun;
	}

	this.err = function(xhr, status, e) {
		if (xhr.status == "500") {
			logout("Login Session Expired");
		} else {
			if (xhr.responseJSON) {
				var obj = xhr.responseJSON;
				alert(obj.code + ":" + obj.message);
			} else {
				alert('AjaxUtil_Order error');
				console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);

			}
		}
	}
	this.setErr = function(fun) {
		this.err = fun;
	}

	this.send = function() {
		$.ajax({
			type: this.type,
			url: this.url,
			dataType: this.dataType,
			beforeSend: function(xhr) {
				xhr.setRequestHeader("Accept", "application/json");
				xhr.setRequestHeader("Content-Type", "application/json;charset=utf-8");
				xhr.setRequestHeader("AJAX", true);
			},
			data: this.param,
			success: this.callbackSuccess,
			error: this.err,
			complete: this.complete
		});
	}
}

//============================================================
//공통 callAjax
//============================================================
function callAjax(rType, method, tUrl, param) {
	tUrl = rPath + tUrl;
	let rData;
	switch (rType) {

		case 'pdf':
			return new Promise((resolve, reject) => {

				$.ajax({
					type: method,
					url: tUrl,
					data: method === "GET" ? param : JSON.stringify(param),
					xhrFields: {
						responseType: "blob",
					},
					contentType: "application/json",
					success: function(data, message, xhr) {
						resolve({
							data: data,
							header: xhr.getResponseHeader('Content-Disposition')
						});
					},
					error: function(data) {
						reject(data);
					}
				})
			})


		case "data":

			$.ajax({
				type: method,
				url: tUrl,
				cache: false,
				data: param = method === "GET" ? param : JSON.stringify(param),
				async: false,
				dataType: "json",
				beforeSend: function(xhr) {
					xhr.setRequestHeader("Accept", "application/json");
					xhr.setRequestHeader("Content-Type", "application/json;charset=utf-8");
					xhr.setRequestHeader("AJAX", true);
				},
				success: function(data) {
					rData = data;
				},
				error: function(xhr, status, error) {
					if (xhr.status == "500") {
						logout("Login Session Expired");
						//location.href = "/";
					} else {
						if (xhr.responseJSON) {
							var obj = xhr.responseJSON;
							alert(obj.code + ":" + obj.message);
						} else {
							alert('AjaxUtil_Order error');
							console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);

						}
					}
				},
				complete: function() {

				}
			});

			return rData;
			break;

		case "html":



			$.ajax({
				type: method,
				url: tUrl,
				cache: false,
				data: param,
				async: false,
				dataType: "html",
				beforeSend: function() {

				},
				success: function(data) {
					rData = data;
				},
				error: function(xhr, status, error) {
					if (xhr.status == "500") {
						logout("Login Session Expired");
						//location.href = "/";
					} else {
						if (xhr.responseJSON) {
							var obj = xhr.responseJSON;
							alert(obj.code + ":" + obj.message);
						} else {
							alert('AjaxUtil_Order error');
							console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);

						}
					}
				},
				complete: function() {

				}
			});

			return rData;
			break;

		case "msg":

			$.ajax({
				type: method,
				url: tUrl,
				cache: false,
				data: param,
				dataType: "json",
				beforeSend: function() {

				},
				success: function(data) {
					alert(data['msg']);
					if (data['rUrl'] != null) {
						location.href = data['rUrl'];
					}
				},
				error: function(xhr, status, error) {
					if (xhr.status == "500") {
						logout("Login Session Expired");
						//location.href = "/";
					} else {
						if (xhr.responseJSON) {
							var obj = xhr.responseJSON;
							alert(obj.code + ":" + obj.message);
						} else {
							alert('AjaxUtil_Order error');
							console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);

						}
					}
				},
				complete: function() {

				}
			});

			break;

		case "form":

			jQuery.ajaxSettings.traditional = true;


			$.ajax({
				type: method,
				url: tUrl,
				cache: false,
				data: param,
				async: false,
				dataType: "json",
				enctype: 'multipart/form-data',
				contentType: false,
				processData: false,
				beforeSend: function() {
					spinnerOpen();
				},
				success: function(data) {
					rData = data;
				},
				error: function(xhr, status, error) {
					if (xhr.status == "500") {
						logout("Login Session Expired");
						//location.href = "/";
					} else {
						if (xhr.responseJSON) {
							var obj = xhr.responseJSON;
							alert(obj.code + ":" + obj.message);
						} else {
							alert('AjaxUtil_Order error');
							console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);

						}
					}
				},
				complete: function() {
					spinnerClose();
				}
			});

			return rData;
			break;

		default:

			$.ajax({
				type: method,
				url: tUrl,
				cache: false,
				data: param,
				dataType: "json",
				success: function(data) {
					if (data['rUrl'] != null) {
						location.href = data['rUrl'];
					}
				},
				error: function(xhr, status, error) {
					if (xhr.status == "500") {
						logout("Login Session Expired");
						//location.href = "/";
					} else {
						if (xhr.responseJSON) {
							var obj = xhr.responseJSON;
							alert(obj.code + ":" + obj.message);
						} else {
							alert('AjaxUtil_Order error');
							console.log("AjaxUtil_Order url=" + this.url + " 에러 = " + e);

						}
					}
				}
			});

			break;
	}
}

//============================================================
//공통 Preview Load
//============================================================
function callPreviewLoad(previewDomId, paramList) {
	var list = new Object();
	list.itemList = paramList;

	var data = {
		list: list
	};

	var rData = callAjax("data", "POST", "/wm/wmpt6InvoiceCall.do", data);
	rData.previewId = previewDomId;

	var url = "/main/modalLoad?gprogurl=/com/modal/wmpt6.html";
	var urlParameters = Object.entries(rData).map(e => e.join('=')).join('&');

	var tURL = url + "&" + urlParameters;

	$("#" + previewDomId).load(tURL);
}

//============================================================
//공통 Preview Load & print
//============================================================
function callPreviewLoadPrint(previewDomId, paramList) {
	var list = new Object();
	list.itemList = paramList;

	var data = {
		list: list
	};

	var rData = callAjax("data", "POST", "/wm/wmpt6InvoiceCall.do", data);
	rData.previewId = previewDomId;

	var url = "/main/modalLoad?gprogurl=/com/modal/wmpt6.html";
	var urlParameters = Object.entries(rData).map(e => e.join('=')).join('&');

	var tURL = url + "&" + urlParameters;

	$("#" + previewDomId).load(tURL, function() {
		var wrapDom = $("#" + previewDomId).find(".wmpt6-wrap");
		var rollDom = wrapDom.find(".wmpt6-roll");

		for (var i = 0; i < rollDom.length; i++) {
			rollDom.eq(i).printThis({
				importCSS: false,
				loadCSS: "/css/pt/pt.css",
			});
		}
	});
}

//============================================================
//공통 Preview print
//============================================================
function callPreviewPrint(previewDomId) {
	if ($("#" + previewDomId).length > 0) {
		var wrapDom = $("#" + previewDomId).find(".wmpt6-wrap");
		var rollDom = wrapDom.find(".wmpt6-roll");

		for (var i = 0; i < rollDom.length; i++) {
			rollDom.eq(i).printThis({
				importCSS: false,
				loadCSS: "/css/pt/pt.css",
			});
		}
	}
}

//============================================================
//공통 Javascript 숫자 3자리(천단위) 마다 콤마 찍기
//============================================================
function comma(num) {
	var parts = num.toString().split('.')
	parts[0] = parts[0].replace(/\B(?=(\d{3})+(?!\d))/g, ',')
	return parts.join('.');
	//return val.toString().replace(/\B(?<!\.\d*)(?=(\d{3})+(?!\d))/g, ",");
}

//============================================================
//공통 Javascript 숫자 3자리(천단위) 콤마 제거
//============================================================
function uncomma(num) {
	str = String(num);
	return Number(str.replace(/[^-+\d]+/g, ''));
	//return val.replace(/,/g, "");
}

//============================================================
//공통 Javascript 숫자(정수)만 가능(양수,음수)
//============================================================
function onlyNumber(e) {
	var regexp = /^[-]?[0-9]*([0-9]?)?$/;
	var regexp2 = /\s/g;

	var value = $(e).val().replaceAll(",", "");
	value = value.replace(/^[-]?0+/, "")
	if (!regexp.test(value)) {
		var result = value.replace(value, "");
		$(e).val(comma(result));
		$(e).focus();
		return false; //
	}
	$(e).val(comma(value));
}

//============================================================
//공통 Javascript 숫자(양수)만 가능
//============================================================
function onlyNumberPlus(e) {
	var regexp = /[^0-9]/g;

	var value = $(e).val().replaceAll(",", "");
	value = value.replace(/(^0+)/, "");
	if (regexp.test(value)) {
		var result = value.replace(value, "");
		$(e).val(comma(result));
		$(e).focus();
		return false;
	}
	$(e).val(comma(value));
}

//============================================================
//공통 Javascript 숫자(실수)만 가능
//============================================================
function onlyFloat(e) {
	var regexp = /[^0-9|.]/g;
	var regexp2 = /\s/g;

	var value = $(e).val().replaceAll(",", "");

	if (regexp.test(value)) {
		var result = value.replace(regexp, "");
		$(e).val(comma(result));
		$(e).focus();
		return false;
	}
	$(e).val(comma(value));
}

//============================================================
//공통 params json 형식으로 리턴
//============================================================
function paramsJsonData(d) {
	if (d) {
		d = d.replace(/ /g, "");
		d = d.replace(/{/g, "");
		d = d.replace(/}/g, "");
		d = d.split(',');
		const prams = d.reduce((newObj, obj) => {
			dobj = obj.split('=');
			newObj[dobj[0]] = dobj[1];
			return newObj;
		}, {});
		return prams;
	} else {
		return null;
	}
}

//============================================================
//검색조건 폼 펼치고 접기 - 2022.09.20 - 퍼블리싱 스크립트 추가
//============================================================
function bookmarkclick(obj) {
	$(obj).toggleClass('active');

	var pprogrid = obj.getAttribute("pprogrid");
	var bkactive = obj.getAttribute("class").indexOf('active');
	var bookmark = (bkactive >= 0) ? "Y" : "N";

	//alert(pprogrid);


	var data = { progrid: pprogrid, bookmark: bookmark };
	var aul = new AjaxUtil_Order("/main/userMenuBookmark.do", data, "GET");
	aul.setCallbackSuccess(function(data) {
		//jsonToMenu(data);
		var jsonStr = JSON.stringify(data);
		//alert(jsonStr);

		//북마크 추가
		if (bookmark == 'Y') {
			//alert(bookmark);
			//BookmarkAdd(pprogrid, progcmd, menunam, newMenulvl);
			BookmarkAdd(pprogrid);
		}

		//북마크 삭제
		if (bookmark == 'N') {
			//alert(bookmark);
			BookmarkDelete(pprogrid);
		}


	});
	aul.send();
	// ajax[/main/userMenuBookmark.do]
}

$(document).ready(function() {
	$('.main-search-wrap').each(function() {

		var rowCount = $(this).find('.form-group.row').length;

		if (rowCount < 3) {
			$(this).find('.btn-fold-wrap').hide();
		}
	})

});
function onSearchExpand(button) {
	var searchWrap = $(button).closest('.main-search-wrap');
	var rowHeight = searchWrap.find('.form-group.row').outerHeight();
	var searchForm = searchWrap.find('.search-form');
	var margin = 17;


	if ($(button).hasClass('expanded')) {

		searchForm.css('max-height', rowHeight * 2 + margin)
		$(button).removeClass('expanded');

	} else {

		searchForm.css('max-height', 'none')
		$(button).addClass('expanded');

	}
}
$(function() {
	$('.file-input').on('change', function(e) {
		var fileName = $(this)[0].files[0].name;
		var inputField = $(this).closest('.input-file-group').find('.file-name');
		inputField.html(fileName)
	})
})


//============================================================
//공통 모달 레이어 팝업 호출 - 2022.10.05
//============================================================
function callModalPopup(progId, param) {

	var url = rPath + "/main/modalLoad?gprogurl=/com/modal/" + progId + ".html";
	var urlParameters = Object.entries(param).map(e => e.join('=')).join('&');
	var tURL = url + "&" + urlParameters;

	$("#modalCommon").load(tURL);
}

//============================================================
//공통 문자열 길이 (영문,숫자=1 한글=2)
//============================================================
function getStringLength(str) {
	var len = 0;
	for (var i = 0; i < str.length; i++) {
		if (escape(str.charAt(i)).length == 6) {
			len++;
		}
		len++;
	}
	return len;
}
//문자열 자르기
function getStrCut(str, len) {
	var s = 0;
	for (var i = 0; i < str.length; i++) {
		s += (str.charCodeAt(i) > 128) ? 2 : 1;
		if (s > len) return str.substring(0, i) + "..";
	}
	return str;
}
//문자열에 공백매꾸기 : n=문저열, digits=공백갯수
function setleadingSpaces(n, digits) {
	var space = '';
	n = n.toString();

	if (n.length < digits) {
		for (var i = 0; i < digits - n.length; i++)
			space += '&nbsp;';
	}
	return space + n;
}
//날자 입력 input에서 del 키누를때 전부 삭제 하기
function setInputDateDel(e) {
	if (event.keyCode == 46) // keyboard del key code
		e.value = '';
}

//============================================================
// url get Parameter 리턴
//============================================================
function getParameter(param) {
	//현재 주소를 decoding
	var requestParam;
	var url = unescape(location.href);

	//파라미터만 자르고, 다시 &그분자를 잘라서 배열에 넣는다.
	var paramArr = (url.substring(url.indexOf("?") + 1, url.length)).split("&");

	for (var i = 0; i < paramArr.length; i++) {
		var temp = paramArr[i].split("="); //파라미터 변수명을 담음

		if (temp[0].toUpperCase() == param.toUpperCase()) {
			// 변수명과 일치할 경우 데이터 삽입
			requestParam = paramArr[i].split("=")[1];
			break;
		}
	}

	return requestParam;
}

const _commonInit = async () => {
	// 2023.08.09 한지수 추가 * 사유 : 탭 화면 구성에서 탭 추가 시 모든 탭에 해당 function이 호출되어 값이 초기화되어 프로그램 ID 해당하는 select태그만 선택되도록 추가. (테마 1번일 시 ggActivePg === 'Home')
	const selector = window.ggActivPG === 'Home' ? '' : '[id*="' + window.ggActivPG + '"]';
	const combos = document.querySelectorAll('select' + selector + '[data-cb-url][data-cb-val][data-cb-name]');
	for (const combo of combos) {
		try {
			const comboUrl = rPath + combo.getAttribute('data-cb-url');
			const comboVal = combo.getAttribute('data-cb-val');
			const comboName = combo.getAttribute('data-cb-name');
			const selectVal = combo.getAttribute('data-cb-select');
			const comboVar = combo.getAttribute('data-cb-variable');

			const res = await fetch(comboUrl);
			const options = await res.json();
			if (comboVar) {
				window[comboVar] = options;
			}
			combo.setAttribute('data-json', JSON.stringify(options));
			let optionHtml = combo.querySelector('option[value=""]');
			// 2023.07.24 박소희 추가 * 사유 : 받아온 데이터의 최상단 데이터를 화면에 노출시켜야 함. (value가 빈값인 option dom이 필요없음.)
			let optionChildHtml = optionHtml != null ? optionHtml.outerHTML : '';
			options.forEach((option) => {
				let selected = '';
				if (selectVal === option[comboVal]) {
					selected = 'selected'
				}
				optionChildHtml += '\r\n<option value="' + option[comboVal] + '" ' + selected + '>' + option[comboName] + '</option>';
			});
			combo.innerHTML = optionChildHtml;
		} catch (e) {
			console.error(e);
		}
	}
}

// 23-08-22 Park T. S. 추가(그리드 검색영역 조건 초기화)
// 23-08-24 박소희 (세부 조건 및 기능 추가)
// 23-09-18 Park T. S. month type 분기 추가 // 23-10-18 버그 수정 // 23-11-20 날짜 초기화 관련 조건 추가
function resetSearchConditions(intervalFromYear = 0, intervalFromMonth = 0, intervalFromDate = 1, noFromDate = false){
    const $inputConditionDOMs = $('.setting-grid-content').find('input'); // 검색영역 dom 변경시 바꿔줄 부분
    const $selectConditionDOMs = $('.setting-grid-content').find('select'); // 검색영역 dom 변경시 바꿔줄 부분

    for(const inputConditionDOM of $inputConditionDOMs){
        const type = inputConditionDOM.type;
        if('text' === type){ // text type은 단순 초기화
            inputConditionDOM.value = '';
        } else if('date' === type || 'month' === type){ // date or month type
            const lastDateNode = $(inputConditionDOM).parent().find(':last-child')[0];
            const toDate = new Date();
            const fromDate = new Date();
            fromDate.setFullYear(fromDate.getFullYear() - intervalFromYear);
            fromDate.setMonth(fromDate.getMonth() - intervalFromMonth);
            fromDate.setDate(fromDate.getDate() - intervalFromDate);

            if(inputConditionDOM.isSameNode(lastDateNode)){ // lastChild일 경우
                $(inputConditionDOM).val(String(toDate.getFullYear()) + '-' + String((toDate.getMonth()+1)).padStart(2, '0') + String('date' === type ? '-' + String(toDate.getDate()).padStart(2, '0') : ''));
            } else {
            	let dateValue = String(fromDate.getFullYear()) + '-' + String((fromDate.getMonth()+1)).padStart(2, '0');
            	if('date' === type){
            		dateValue +=  '-' + String(fromDate.getDate()).padStart(2, '0');
            	}
            	$(inputConditionDOM).val(!noFromDate ? dateValue : '');
            }
        } else if('checkbox' === type){
        	inputConditionDOM.checked = false;
        }
    }
    // 추후 수정
    for(const selectConditionDOM of $selectConditionDOMs){
        const $selectOptionConditionDOMs = $(selectConditionDOM).children('option');
        const selectedOption = $selectOptionConditionDOMs.filter((_key , value) => {
            return value.dataset.selected
        });
        selectConditionDOM.value = selectedOption[0]
							                ? selectedOption[0].dataset.selected
							                : selectConditionDOM[0].value;
		if('pq-select' === selectConditionDOM.className){
			$('#' + selectConditionDOM.id).pqSelect('refreshData');
		}
        // selected를 설정한 것이 아니라면 맨 첫번째 값으로 선택
    }
}

function comboCreateData(gridnm, colnm, obj, mapob) {

	var grid = pq.grid('#' + gridnm);
	var column = grid.getColumn({ dataIndx: colnm });

	var options = obj.map(function(obj) {
		var rObj = {};
		if (mapob != null && mapob.combovl != undefined && mapob.combonm != undefined) {
			rObj[obj[mapob.combovl]] = obj[mapob.combonm];
		} else {
			rObj[obj.combovl] = obj.combonm;
		}
		return rObj;
	});

	column.editor.options = options;
}

// grid render function. paste시 option === undefined 일 경우 렌더는 '' 이지만 value가 초기화되지 않아 value 초기화 추가.
function gridRenderAndValue(ui) {
	var option = ui.column.editor.options.find(function(obj) {
		return (obj[ui.cellData] != null);
	});
	ui.rowData[ui.dataIndx] = option ? ui.cellData : '';
	return option ? option[ui.cellData] : '';
}

// 23-10-04 Park T. S. 추가. gridRenderAndValue에서 cellData 조작 부분 제거한 버전
function gridRender(ui) {
	const option = ui?.column?.editor?.options?.find(obj => obj[ui?.cellData]);
	return {
		text: option ? option[ui?.cellData] : ui?.cellData
	}
}

// 23-10-04 Park T. S. 추가. 그리드 초기화
function makeEmptyGrid(gridObj) {
	if (!gridObj) {
		return;
	}
	gridObj.getGrid().showLoading();
	gridObj.getGrid().option('dataModel', { data: [] });
	gridObj.getGrid().refreshDataAndView();
	gridObj.getGrid().hideLoading();
}

//2023.09.25 하주영 추가
//출력물 호출 공통 코드 추가
async function reportPrint(reportData, type) {
	$('#spinner').css('display', 'block');

	const printData = await fetchUtil('POST', '/' + type + '/report/reportlist', reportData);

	if (printData.status === 200) {
		const blob = await printData.blob();
		const objectUrl = (window.URL || window.webkitURL).createObjectURL(blob);

		$('#spinner').css('display', 'none');

		let iframe = document.createElement('iframe');
		iframe.style.display = 'none';
		iframe.src = objectUrl;

		document.body.appendChild(iframe);
		iframe.contentWindow.focus();
		iframe.contentWindow.print();
		window.URL.revokeObjectURL(objectUrl);
	}
}

// 23-10-06 Park T. S. 추가. default Tab Object(new로 생성 할 것)
function createDefaultTabObject(colModel, dataModel) {
	return {
		name: '[[#{ms.underBar}]]',
		noRename: true,
		gridOptions: !colModel && !dataModel ? null : { colModel: colModel, dataModel: dataModel }
	};
}

// 23-10-10 Park T. S. 추가. validation에서 사용할 텍스트 생성
function makeDuplicateRowText(keys, gridObj, gridCheck, gridData) {
	let rText = '';
	for (let idx = 0; idx < keys.length; idx++) {
		rText += (gridCheck.hasOwnProperty(keys[idx]) && gridCheck[keys[idx]] === gridData[keys[idx]]) ? gridObj.getColumn({ dataIndx: keys[idx] }).title + '[[#{ms.col}]] ' + gridData[keys[idx]] : ' ';
		if (idx !== keys.length - 1 && gridCheck.hasOwnProperty(keys[idx]) && gridCheck[keys[idx]] === gridData[keys[idx]]) {
			rText += ' / ';
		}
	}
	return rText;
}

// 23-10-10 Park T. S. 추가. validation에서 복합키 값 중복체크
function isKeyDuplication(keys, gridCheck, gridData) {
	for (const key of keys) {
		if (gridCheck[key] !== gridData[key]) {
			return false;
		}
	}
	return true;
}

// 23-10-11 고혜련 책임님 & Park T. S. 추가. 마감 탭 객체 생성 함수
function createTabModel(rData, colModel, groupCols, groupBy, groupByName, summaryCols) { // groupBy는 반드시 key값으로
	const reTabs = [];
	const tabsFilterOption = rData.reduce((tab, now) => {
		let groupByOption = '';
		for (let groupOrder = 0; groupOrder < groupBy.length; groupOrder++) {
			if (now.hasOwnProperty(groupBy[groupOrder])) {
				groupByOption += (groupByName && Array.isArray(groupByName) && groupByName[groupOrder] ? now[groupByName[groupOrder]] : now[groupBy[groupOrder]])
					+ (groupOrder !== groupBy.length - 1 ? '_' : '')
			}
		}
		tab[groupByOption] ? tab[groupByOption].push(now) : tab[groupByOption] = [now];
		return tab;
	}, {});

	for (let index of Object.keys(tabsFilterOption)) {
		reTabs.push({
			name: index,
			hidden: false,
			noClose: true,
			noRename: true,
			gridOptions: {
				colModel: colModel,
				dataModel: { data: tabsFilterOption[index] },
				groupModel: { dataIndx: groupCols || [], showSummary: summaryCols || [], grandSummary: true, merge: true, on: true, header: false, skipSingleSummary: true }
			}
		});
	}
	return reTabs;
}

// 23-10-11 Park T. S. 추가. 탭 초기화(pqgrid 탭은 1개가 무조건 있어야 해서 번거롭게 삭제해야함.(히든 포함)))
function resetTabContainer(gridObj) {
	if (!gridObj) {
		return;
	}
	const gridTab = gridObj.Tab();
	const tabModel = gridObj.option('tabModel');

	const startIdxForInit = tabModel.tabs.length - 1; // push 전 길이
	tabModel.tabs.push(new createDefaultTabObject(gridObj.option('colModel')));
	for (let idx = startIdxForInit; idx < tabModel.tabs.lenght; idx++) {
		gridTab.create(idx);
	}
	gridTab.activate(startIdxForInit + 1); // 추가된 tab 중 첫번째 활성화(기존 탭들을 지우기 위해서)

	const tabsLength = gridTab.tabs().length; // remove를 하면 length값이 변하기 때문에 고정 값이 필요함

	for (let tIdx = 0; tIdx < tabsLength; tIdx++) {
		gridTab.remove(0); // remove를 하게 되면 자동으로 앞으로 당겨짐(빈 탭이 마지막에 추가됐으니 0번째만 순서대로 지움)
	}
}

function turnOnGridGruopSummary(gridObj) {
	if (!gridObj) {
		return;
	}
	gridObj.Group().option({ on: true, header: false });
}

function getCaseBySummaryTitle(obj){
	return (obj.attr[0].indexOf('pq-sum-cell') !== -1 ? '합계 : ' : '소계 : ') + obj.formatVal;
}

function setSummaryRowCls(gridObj){
	if(!gridObj?.Tab()?.tabs()?.length){
		return;
	}
	for(const tab of gridObj.Tab().tabs()){
		for(const tabRow of tab._inst.pdata){
			if(tabRow.hasOwnProperty('pq_gsummary') && tabRow.pq_gsummary){
				tab._inst.addClass({rowIndx: tabRow.pq_ri, cls: 'color-gray'});
			}
		}
	}
}

function getSummaryCellClass(ui){
	let cellClass = '';
	if(ui?.attr[0].indexOf('pq-sum-cell') === -1 && ui?.rowData?.hasOwnProperty('pq_gsummary') && ui?.rowData?.pq_gsummary){
		cellClass = 'color-gray';
	} else if(ui?.attr[0].indexOf('pq-sum-cell') === -1 && typeof ui?.column?.editable === 'function' && ui?.column?.editable(ui)){
		cellClass = 'color-green'
	}
	return cellClass;
}