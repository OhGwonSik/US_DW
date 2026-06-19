/*
------------------------------------------
- 파일명 : fetchAPI
- 최초생성자 : WM
- 최초생성일시 : 2023.07.04
- 설명 : fetch API를 활용한 유틸
------------------------------------------
*/
async function fetchUtil(httpMethod, url, data, customErrorHandlerFunc, dataTypeVar) { // 공통 fetch
	if (!url) {
		alert('no have url');
		return;
	}
	url = rPath + url;

	const fetchUtilGlobalErrorHandler = async function(responsObj, msg, httpMethodVar) {
		if (!responsObj) {
			alert('no obj');
			return;
		}
		const httpMethod = httpMethodVar ? httpMethodVar.toUpperCase() : 'GET';
		const errorMsg = msg || defaultErrorMsg;
		try {
			if (responsObj.status !== 200) { // get 메소드는 aop 처리를 하지 않아 정상이 아니면 error라고 판단
				throw new Error(errorMsg); // error 생성(message를 catch 구문으로 보냄)
			}		// 동기 방식을 사용하여 pending 된 데이터를 받아올 때에는 반드시 사용함수 앞에 await를 붙여아함
		} catch (err) {
			if ('POST' === httpMethod || 'PUT' === httpMethod || 'PATCH' === httpMethod || 'DELETE' === httpMethod) {
				const jsonObj = await responsObj.json();
				alert('code : ' + jsonObj.code + '\n' + err + '\n' + jsonObj.message);// java단에서 exception 처리가 되는 ajax 통신의 경우 이런 처리를 해줘야 함
			} else {
				alert('code : ' + responsObj.status + '\n' + err); // get method 통신의 경우 response 객체에 status 값이 있어 출력해줌
			}
		}
	}

	httpMethod = httpMethod?.toUpperCase() || 'GET';
	const dataType = dataTypeVar || 'JSON'; // null or undefined 일 경우 'JSON' 입력
	const errorHandler = (!customErrorHandlerFunc && 'function' === (typeof customErrorHandlerFunc))
								? customErrorHandlerFunc
								: fetchUtilGlobalErrorHandler

	let resFetch;

	const headers = function(dataType) {
		if ('JSON' === dataType) {
			return { 'Content-Type': 'application/json' };
		} else if ('FORMDATA' === dataType) { // file 전송시 formdata 형식
			return { 'Content-Type': 'application/x-www-form-urlencoded' };
		} else {
			alert('no support dataType'); // 나중에 메세지는 properties로 빼자
			return;
		}
	};

	const bodyContent = function(dataType) {
		if ('JSON' === dataType) {
			return JSON.stringify(data);
		} else if ('FORMDATA' === dataType) {
			return data;
		} else {
			alert('no support dataType'); // 나중에 메세지는 properties로 빼자
			return;
		}
	};

	resFetch = ('GET' === httpMethod)
				? await fetch(url + '?' + new URLSearchParams(data).toString())
				: await fetch(url, {
										method: httpMethod,
										headers: headers(dataType),
										body: bodyContent(dataType)
									}); // get일 경우 url parameter 생성후 전송, 다른 메소드는 body에 data 넣고 전송

	errorHandler(resFetch, '', httpMethod);
	return resFetch;
}


// ver2.0 사용 보류
class EnhancedFetchUtil {
	// function
	fetchTryCatchAndErrorHandler = async function(httpMethod) {
		let responseObj;
		try {
			const headers = this.headers();
			const bodyContent = this.bodyContent();
			const response = ('GET' === httpMethod)
					 			  ? await fetch(this.url + '?' + new URLSearchParams(this.data).toString())
					 			  : await fetch(this.url, {method : httpMethod, 	headers : headers, body : bodyContent});

			responseObj = response;
			if (responseObj.status !== 200) { // get 메소드는 aop 처리를 하지 않아 정상이 아니면 error라고 판단
				throw new Error(error); // error 생성(message를 catch 구문으로 보냄)
			}
			return responseObj;
		} catch (error) {
			if('POST' === httpMethod || 'PATCH' === httpMethod || 'PUT' === httpMethod || 'DELETE' === httpMethod){
				const jsonObj = await responseObj.json();
				alert('code : ' + jsonObj.code + '\n' + (this._errorMessage || this.errorMessage) + '\n' + jsonObj.message);// java단에서 exception 처리가 되는 ajax 통신의 경우 이런 처리를 해줘야 함
			} else {
				alert('code : ' + responseObj.status + '\n' + (this._errorMessage || this.errorMessage)); // get method 통신의 경우 response 객체에 status 값이 있어 출력해줌
			}
			return null;
		}
	}

	headers = function(){
		if ('JSON' === this.dataType) {
			return { 'Content-Type': 'application/json' };
		} else if ('FORMDATA' === this.dataType) { // file 전송시 formdata 형식
			return new Object;
		}
	};

	bodyContent = function(){
		if ('JSON' === this.dataType) {
			return JSON.stringify(this.data);
		} else if ('FORMDATA' === this.dataType) {
			return this.data;
		}
	};

	/**
     * @param {function} alternate function for errorHandle(recommend async function)
     * @param {(httpMethod: string) => Promise<Response>} func
     */
	set fetchErrorHandler(func){
		if('function' !== typeof func){
			alert('is not function');
			return;
		}
		this.fetchTryCatchAndErrorHandler = func;
	}

	/**
     * @param {string} msg
     */
	set errorMessage(msg){ // setter
		if('string' !== typeof msg){
			alert('is not string');
			return;
		}
		this._errorMessage = msg;
	}

	// method
	get = async () => {
		const httpMethod = 'GET'
		const tryRestAPI = await this.fetchTryCatchAndErrorHandler(httpMethod);

		if('JSON' === this.dataType){
			const responseData = tryRestAPI ? await tryRestAPI.json() : [];
			return responseData;
		} else if('FORMDATA' === this.dataType){
//			const disposition = await tryRestAPI.headers.get('Content-disposition');
			const blob = tryRestAPI ? await tryRestAPI.blob() : '';
			return blob;
//			const objectURL = URL.createObjectURL(blob);
//			let fileName = 'file'; // 파일 기본 이름 // refactoring 필요
//		 	if(disposition && disposition.indexOf('attachment')!==-1) { // http header에서 이름만 떼오는 정규식
//			    const filenameRegex = /filename[^;=\n]*=((['"]).*?\2|[^;\n]*)/;
//			    const matches = filenameRegex.exec(disposition);
//			    if (matches != null && matches[1]) {
//			      fileName = matches[1].replace(/['"]/g, '');
//			    }
//			}
//		 	fileName = decodeURIComponent(fileName); // uri인코딩 -> 디코딩
//			return {file : blob, fileName : fileName};
		}
	}

	post = async () => {
		const httpMethod = 'POST'
		const tryRestAPI = await this.fetchTryCatchAndErrorHandler(httpMethod);
		if('JSON' === this.dataType){
			const responseData = tryRestAPI ? await tryRestAPI.json() : 0;
			if(responseData === 0){
				return false;
			}
			alert(saveSuccessMsg);
			return true;
			//return responseData;
		} else if('FORMDATA' === this.dataType){
			return tryRestAPI;
		}
	}

	patch = async () => {
		const httpMethod = 'POST'
		const tryRestAPI = await this.fetchTryCatchAndErrorHandler(httpMethod);
		if('JSON' === this.dataType){
			const responseData = tryRestAPI ? await tryRestAPI.json() : 0;
			if(responseData === 0){
				return false;
			}
			alert(saveSuccessMsg);
			return true;
			//return responseData;
		} else if('FORMDATA'=== this.dataType){
			return tryRestAPI;
		}
	}

	put = async () => {
		const httpMethod = 'POST'
		const tryRestAPI = await this.fetchTryCatchAndErrorHandler(httpMethod);
		if('JSON' === this.dataType){
			const responseData = tryRestAPI ? await tryRestAPI.json() : 0;
			if(responseData === 0){
				return false;
			}
			alert(saveSuccessMsg);
			return true;
			//return responseData;
		} else if('FORMDATA'=== this.dataType){
			return tryRestAPI;
		}
	}

	delete = async () => {
		const httpMethod = 'POST'
		const tryRestAPI = await this.fetchTryCatchAndErrorHandler(httpMethod);
		if('JSON' === this.dataType){
			const responseData = tryRestAPI ? await tryRestAPI.json() : 0;
			if(responseData === 0){
				return false;
			}
			alert(deleteSuccessMsg);
			return true;
			//return responseData;
		} else if('FORMDATA' === this.dataType){
			return tryRestAPI;
		}
	}

	// constructor
	constructor(url, data, dataType) {
		if(!url){
			alert('no url');
			return;
		}
		this.url = rPath + url;
		this.data = data || new Object;
		this.dataType = dataType ? dataType.toUpperCase() : 'JSON';
		this.errorMessage = defaultErrorMsg;
	}
}