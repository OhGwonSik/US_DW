$(function(){

    //사이드 메뉴

    $(".btn-menu").on('click',function(){
        $("#modal-background").fadeIn(300);
        $("#mainMenu").animate({left: '0'},300);
        $("body").css("overflow", "hidden");
    });

    //사이드 메뉴 닫기

    $(".modal-close").on('click',function(){           
        $("#modal-background").fadeOut(300);
        $(".modal-con").animate({left: '-360px'},300);
        $('body').css('overflow', 'overlay');      
    });
	 
     $('#pdaConfirmCancel').on('click', function(){
		$('.popup').css('top', '-100%');
		$('.bg').hide();
	 });
})
 //alert 창 띄우기
 function alertOpen(msg, callback){
  	$('.popup').eq(1).css('top', '40%');
  	$('.msg-txt').eq(1).text(msg);
	$('.bg').show();
	$('#pdaAlert').focus();
	$('#pdaAlert').off('click').on('click', function(){
		// 콜백 함수를 실행
        if (callback && typeof callback === 'function') {
            callback();
        }
    	$('.popup').css('top', '-100%');
		$('.bg').hide();
	});
}

 //모달용(PDA긴급출고) alert 창 띄우기
 function modelAlertOpen(msg, callback){
  	$('.popup').eq(1).css('top', '40%');
  	$('.msg-txt').eq(1).text(msg);
	$('.bg').show();
	$('#pdaAlert').focus();
	$('#pdaAlert').off('click').on('click', function(){
		// 콜백 함수를 실행
        if (callback && typeof callback === 'function') {
            callback();
        }
    	$('.popup').css('top', '-100%');
	});
}

function confirmOpen(msg, callback){
	$('.popup').eq(0).css('top', '40%');
	$('.msg-txt').eq(0).text(msg);
	$('.bg').show();
	$('#pdaConfirm').off('click').on('click', function(){
		$('.popup').css('top', '-100%');
		$('.bg').hide();
		if (callback && typeof callback === 'function') {
			callback(true);
    	}
    	$('#pdaConfirm').blur();
	});
	$('#pdaConfirmCancel').off('click').on('click', function(){
		$('.popup').css('top', '-100%');
		$('.bg').hide();
		if (callback && typeof callback === 'function') {
			callback(false);
    	}
    	$('#pdaConfirmCancel').blur();
	});
	
}

async function confirmOpenAsync(msg) {
    return new Promise(resolve => {
        confirmOpen(msg, function(result) {
            resolve(result);
        });
    });
}
