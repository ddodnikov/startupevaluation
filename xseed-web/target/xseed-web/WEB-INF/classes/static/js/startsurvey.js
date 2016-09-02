var currentPage = 1;

var currentPageWidth = 18;
var passPageWidth = 10;

function calculateMargin(){
	var pagesWidth = currentPageWidth + (maxPage - 1) * passPageWidth;
	var width = $('.page-progress').width();
	console.log('width: ' + width);
	var marginRight = (width - 40 - pagesWidth)/(maxPage);
	for(var i = 1; i<maxPage; i++){
		$('.page-' + i).css('margin-right', marginRight+'px');
	}
	
}

$().ready(function(){
	calculateMargin();
	var pageId;
	for(var i=1; i<currentPage; i++){
		pageId = pageId = '.page-' + i;
		$(pageId).addClass('pass-page').removeClass('empty-page');
	} 
	
	pageId = '.page-' + currentPage;
	$(pageId).addClass('current-page').removeClass('empty-page');
	$(pageId).text(currentPage); 
});

$('.next').click(function(){
	if(currentPage<maxPage){
		/*validation*/
		var curStep = document.getElementById("content-" + currentPage);
		if(currentPage == 1){
			if(document.getElementById('snapshotName').value == ''){
				document.getElementById('error-message-page1').style.display = 'block';
				return;
			}
		}
		if(isPageFilled(currentPage)){
			goToNextStep();				
			
			currentPage++;
			var pageId;
			for(var i=1; i<currentPage; i++){
				pageId  = '.page-' + i;
				$(pageId).addClass('pass-page').removeClass('empty-page');
				$(pageId).text("");
			}
			
			pageId = '.page-' + currentPage;
			$(pageId).addClass('current-page').removeClass('empty-page');
			$(pageId).text(currentPage);
			
			
			$('#content-'+(currentPage-1)).css("display", "none");
			$('#content-'+currentPage).css("display", "block");
			$('html, body').animate({scrollTop:0}, 500);
		
		}else{
			curStep.getElementsByClassName("error-message-page2")[0].style.display = 'block';
			return;
		}
	}
});


$('.prev').click(function(){
	if(currentPage>1){
		currentPage--;
		var pageId;
		pageId = pageId = '.page-' + (currentPage+1);
		$(pageId).addClass('empty-page').removeClass('current-page');
		$(pageId).text("");
		
		pageId = '.page-' + currentPage;
		$(pageId).addClass('current-page').removeClass('pass-page');
		$(pageId).text(currentPage);
		
		$('#content-'+(currentPage+1)).css("display", "none");
		$('#content-'+currentPage).css("display", "block");
		$('error-message-page1').css("display", "none");
		$('error-message-page2').css("display", "none");
	}
});