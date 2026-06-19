//sidebar button ----------------------------
$(".btn-sidebar").on("click", function(){
  var sideWrap = $(".sidebar-container");
  if ($(this).hasClass("show")) {
      $(this).removeClass("show");
      $(this).animate({left: '50px'});   
      sideWrap.removeClass("show");
      sideWrap.animate({width: '60px'});

      $("details").prop('open', false);
      $("details.depth1 summary .menu-title,ul.sidebar-bottom li").animate({gap:'22px'});
  }
  else {
    $(this).addClass("show")
    $(this).animate({left: '230px'});
    sideWrap.addClass("show");
    sideWrap.animate({width: '240px'});
    $("details.depth1 summary .menu-title,ul.sidebar-bottom li").animate({gap:'12px'});
  }
})

$(".sidebar-main-menu details.depth1 summary").on("click",function(){
  if ($(".btn-sidebar").hasClass("show")) {}
  else {
    event.preventDefault();    
  }
})
//--------------------------------------------
//start tab-------------------------------------------------
  const li = $(".tab .tab-menu");
  li.click(function () {
    li.removeClass("on");
    $(this).addClass("on");
    
    var left = $('.tab .tab-menu.on').offset().left;  
    var tabLeft = $('.tab .tab-menu.on').position().left;  
    var curLeft = $('.tab').scrollLeft();
    var menuWidth = $('.tab .tab-menu.on').width();    
    var right = left + menuWidth;
    var maxRight = $('.tab-container').width();
    var scrMove = curLeft;

    if (right > maxRight) { // 보이는 마지막 탭을 클릭했을때 클릭한 항목 모두 보이게 왼쪽으로 스크롤 
      scrMove = curLeft + menuWidth;
    } else if (tabLeft < 0) { // 보이는 첫번째 탭을 클릭했을때 클릭한 항목 모두 보이게 오른쪽으로 스크롤
      scrMove = curLeft + tabLeft;
    }

    $('.tab').animate({scrollLeft : scrMove}, 400);    

    setTabArrowBtn(Math.floor(scrMove));    
  });

  $(".btn-tab-arrow").click(function(){    
    var curLeft = $('.tab').scrollLeft();
    if ($(this).hasClass("left")) curLeft = curLeft - 100
    else if ($(this).hasClass("right")) curLeft = curLeft + 100;

    $('.tab').animate({scrollLeft : curLeft}, 400);
    
    setTabArrowBtn(curLeft);
  })  
  
  function setTabArrowBtn(curLeft){
    var leftBtn = $(".btn-tab-arrow.left");
    var rightBtn = $(".btn-tab-arrow.right");        

    if (curLeft <= 0) leftBtn.removeClass("on") ;
    else leftBtn.addClass("on") ;  

    elConWidth = document.querySelector(".tab").clientWidth;  
    elWidth = document.querySelector(".tab").scrollWidth - curLeft;

    if (elWidth <= elConWidth) rightBtn.removeClass("on");
    else rightBtn.addClass("on");
  }
//end tab--------------------------------------------------------
// list-tab  
  $("ul.list-tab li").click(function () {
    const tabId = $(this).attr("data-tab");

    $("ul.list-tab li").removeClass("on");
    $(".list-tab-content").removeClass("active");

    $(this).addClass("on");
    $("#" + tabId).addClass("active");
  });
// search detail button ---------------------------------------
$(".btn-search-detail").on("click", function(){
  if ($(this).hasClass("open")) {
    $(this).removeClass("open");
    $(".search-conditions").animate({height: '84px'});
  }
  else {
    $(this).addClass("open");
    var elHeight = document.querySelector(".setting-grid").scrollHeight + 22;
    $(".search-conditions").animate({height: elHeight});
  }
})


// input number spin button
jQuery('<div class="quantity-nav"><div class="quantity-button quantity-up"></div><div class="quantity-button quantity-down"></div></div>').insertAfter('.quantity input');
    jQuery('.quantity').each(function() {
      var spinner = jQuery(this),
        input = spinner.find('input[type="number"]'),
        btnUp = spinner.find('.quantity-up'),
        btnDown = spinner.find('.quantity-down'),
        min = input.attr('min'),
        max = input.attr('max');

      btnUp.click(function() {
        var oldValue = parseFloat(input.val());
        if (oldValue >= max) {
          var newVal = oldValue;
        } else {
          var newVal = oldValue + 1;
        }
        spinner.find("input").val(newVal);
        spinner.find("input").trigger("change");
      });

      btnDown.click(function() {
        var oldValue = parseFloat(input.val());
        if (oldValue <= min) {
          var newVal = oldValue;
        } else {
          var newVal = oldValue - 1;
        }
        spinner.find("input").val(newVal);
        spinner.find("input").trigger("change");
      });

    });  


// location nav-menu event ------------------------------------------
  $(".btn-nav-menu,.btn-favorite").on("click", function(){
    if ($(this).hasClass("on")) {
      $(this).removeClass("on");
      $(this).next().slideUp("fast");
    }
    else {
      navMenuAllClose();
      $(this).addClass("on");      
      $(this).next().slideDown("fast");
    } 
  })
  $("ul.menu-list li").on("click", function(e){
    $(this).parent().prev().html($(this).text() + '<img class="ic-input-down">');
    navMenuAllClose();
  })
  $("ul.fa-list li").on("click", function(){
    $(".btn-favorite").removeClass("on");
    $(".fa-list").slideUp("fast");
  })

  // menulist allClose
  function navMenuAllClose() {
    $(".btn-favorite").removeClass("on");
    $(".btn-nav-menu").removeClass("on");
    $(".fa-list").slideUp("fast");
    $(".menu-list").slideUp("fast");
  }
// ------------------------------------------------------------------------

// background
$("#modal-background, .close").on('click',function(){      
  if ($(this).hasClass("close")){
    $("#modal-background").fadeOut(300);
    $(".modal-con").fadeOut(300);  
    $('body').css('overflow', 'overlay');
  }
});