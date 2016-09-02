var currentPage = 1;
var maxPage = 3;

$(function () {
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		if(header && token){
			xhr.setRequestHeader(header, token);
		}
	});
});

$('.next').click(function(){
	
	if(currentPage<maxPage){
		
		
		if(currentPage == 2) {

			var page2 = document.getElementById('content-2');
			var filterQuestions = page2.getElementsByClassName('question-title');
			for (i = 0; i < filterQuestions.length; i++) {
				console.log("changing to black");
				filterQuestions[i].style.color = '#545e79';
			}

			var emptyFilters = new Array();
			var filter = new Array();
			
			for (i = 0; i < filterQuestions.length; i++) {
				
				console.log("filterQuestion_" + i);
				var filterQuestion = document.getElementById("filterQuestion_" + i);
				var filterQuestionAnswers = filterQuestion.getElementsByClassName('page2_multipleanswer form-control');
				var filterQuestionId = filterQuestionAnswers[0].name.split('_')[1];
				console.log(filterQuestionId);
				
				var answers = new Array();

				for (j = 0; j < filterQuestionAnswers.length; j++) {
					
					var answer = filterQuestionAnswers[j];
					var answerText = answer.value;
					console.log(answerText);

					if(answerText != 'Other') {
				
						var answerId = filterQuestionAnswers[j].name.split('_')[2];
						
						if(answer.checked) {
							answers.push(answerId);
						}
					
					}
				
				}
				
				if(answers.length != 0) {
					var question = {questionId:filterQuestionId, answerIds:answers};
					console.log(question);
					filter.push(question);
				}else{
					emptyFilters.push(filterQuestions[i]);
				}
			
			}
			
			console.log(filter);
			
			if(filter.length < filterQuestions.length) {
				document.getElementById('inputError').style.display = 'block';
				for (i = 0; i < emptyFilters.length; i++) {
					emptyFilters[i].style.color = 'red';
				}
				var firstEmptyFilterId = emptyFilters[0].parentElement.id;
				$('html, body').animate({scrollTop: $('#'+firstEmptyFilterId).offset().top-30}, 500);
				return;
			} else {
			
				filter = JSON.stringify(filter);
				
				console.log(filter);
				console.log(checkfilterl);
				$.ajax({
			        contentType: 'application/json; charset=utf-8',
			        dataType: 'json',
			        type: 'POST',
			        url: checkfilterl,
			        accepts: {
			            text: "text/plain"
			        },
			        data: filter,
			        error: function () {          
			        	window.alert("Something went wrong, please try again later!");
			        },
			        success: function (object) {
			        	console.log(object);
			        	document.getElementById('resultError').style.display = 'none';
			        	document.getElementById('inputError').style.display = 'none';
			        	if(object.result == 'resultsFound') {
			        		next();
			        	} else {
			        		if(object.result == 'missingInput') {
			        			document.getElementById('inputError').style.display = 'block';
			        		} else {
			        			document.getElementById('resultError').style.display = 'block';
			        		}
			        	}
			        }
			    }); 
			}
			
		} else {
			if(currentPage == 1) {
				if(document.getElementById('calculationName').value == '') {
					document.getElementById('nameError').style.display = 'block';
				} else {
					next();
				}
			} else {
				next();
			}
		}
	}
});


$('.prev').click(function(){
	if(currentPage>1){
		var page2 = document.getElementById('content-2');
		var filterQuestions = page2.getElementsByClassName('question-title');
		for (i = 0; i < filterQuestions.length; i++) {
			filterQuestions[i].style.color = '#545e79';
		}
		currentPage--;
		
		$('#content-'+(currentPage+1)).css("display", "none");
		$('#content-'+currentPage).css("display", "block");
		document.getElementById('inputError').style.display = 'none';
		document.getElementById('resultError').style.display = 'none';
		
		
	}

	
});

$("#submit").click(function(){
    $("#loader").html("<img src='img/loader3.gif' alt='description' width='120px' height='120px'/>");
    document.getElementById("loader").style.display = "block";
});

function next() {
	var curStep = document.getElementById("content-" + currentPage);
	currentPage++;				
	$('#content-'+(currentPage-1)).css("display", "none");
	$('#content-'+currentPage).css("display", "block");
	document.getElementById('inputError').style.display = 'none';
	document.getElementById('resultError').style.display = 'none';

};