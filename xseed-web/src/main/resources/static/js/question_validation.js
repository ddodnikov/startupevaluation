/**
 * 
 * Front-end question validation
 */

function getTextQuestionsByPage(page){
	var textQuestions = document.getElementById("content-" +page).getElementsByClassName("page" + page + "_TEXTquestion");
	return textQuestions;
}

function getSingleQuestionByPage(page) {
	var singleQuestions = document.getElementById("content-" +page).getElementsByClassName("page" + page + "_SINGLE_CHOICEquestion");
	return singleQuestions;
}

function getMultipleQuestionsByPage(page) {
	var multipleQuestions = document.getElementById("content-" +page).getElementsByClassName("page" + page + "_MULTIPLE_CHOICEquestion");
	return multipleQuestions;
}


//GLOBAL QUESTION ARRAY FOR ERRORS
var questionTitles = new Array();

function isPageFilled(page) {
	
	var noErrorOthers = document.getElementsByClassName("")
	 
	 var allQuestions = [];
	 var textQuestions = getTextQuestionsByPage(page);
	 var singleQuestions = getSingleQuestionByPage(page);
	 var multipleQuestions = getMultipleQuestionsByPage(page);
	 
	 allQuestions = allQuestions.concat([].slice.call(textQuestions));
	 allQuestions = allQuestions.concat([].slice.call(singleQuestions));
	 allQuestions = allQuestions.concat([].slice.call(multipleQuestions));

	 //if there is validation for text questions just uncomment this validation.
	 //if there is validation for text questions except feedback text questions 
	 //we should add new category in the question_calculations table in DB i.e. FEEDBACK
	 //validateTextQuestions(textQuestions);
	 validateSingleQuestions(singleQuestions, page);
	 validateMultipleQuestions(multipleQuestions, page);
	 
	 if(questionTitles.length != 0){
		 clearOldErrors(allQuestions);
		 printErrors();
		 questionTitles = [];
		 return false;
	 }else{
		 clearOldErrors(allQuestions);
		 return true;
	 }	
}

function goToNextStep() {
	//document.getElementById("content-" + currentPage).style.display = 'none';
	document.getElementById("error-message-page1").style.display = 'none';
	var errors = document.getElementsByClassName("error-message-page2");
	for (var j = 0; j < errors.length; j++) {
		errors[j].style.display = 'none';
	}
	//currentPage++;
	//document.getElementById("content-" + currentPage).style.display = 'block';
}

function clearOldErrors(questionTitles){
	for(var i = 0; i<questionTitles.length; i++){
		questionTitles[i].getElementsByClassName('question-title')[0].style.color='#545e79';
	 }
}

function printErrors(){
	for(var i = 0; i<questionTitles.length; i++){
		questionTitles[i].getElementsByClassName('question-title')[0].style.color='red';
	 }
	
	//scroll
	questionTitles.sort(function(a,b) {
	    if( a === b) return 0;
	    if( !a.compareDocumentPosition) {
	        // support for IE8 and below
	        return a.sourceIndex - b.sourceIndex;
	    }
	    if( a.compareDocumentPosition(b) & 2) {
	        // b comes before a
	        return 1;
	    }
	    return -1;
	});
	
	$('html, body').animate({
		scrollTop: $(questionTitles[0].getElementsByClassName('question-title')[0]).offset().top-20
	}, 500);
}

function validateTextQuestions(questions){
	if(questions.length == 0){
		return;
	}else{
		for(var i = 0; i<questions.length; i++){
			var question = questions[i];
			var answers = question.getElementsByClassName(question.className.split("_")[0]+"_textanswer");
			for(var j = 0; j<answers.length; j++){
				if(answers[j].value == null || answers[j].value == ""){
					questionTitles.push(question);
				}
			}
		}
	}
}

function validateSingleQuestions(questions, page){
	if(questions.length == 0){
		console.log("no single questions");
		return;
	}else{
		console.log("validating single questions");
		for(var i = 0; i<questions.length; i++){
			var question = questions[i];
			var answers = question.getElementsByClassName(question.className.split("_")[0]+"_singleanswer");
			
			var anySelected = false;
			var isOtherSelected = false;
			var otherAnswer = "";
			
			if(answers.length == 0){
				anySelected = true;
			}else{
				for(var j = 0; j<answers.length; j++){
					if(answers[j].checked){
						console.log("has selected");
						anySelected = true;
						var value = answers[j].parentElement.getElementsByTagName("div")[0].innerText.trim();
						console.log("Selected : " + value);
						if(value == 'Other'){
							console.log("other selected");
							isOtherSelected = true;
							var questionId = answers[j].name.split("_")[1];
							var id = "radio_" + questionId + "_other";
							otherAnswer = document.getElementById(id).value;
							console.log("other answer : " + otherAnswer);
							if(otherAnswer == null){
								console.log("null");
								anySelected = false;
								break;
							} else {
								var otherError = question.getElementsByClassName("otherlenghterror_page" + page + "_question" + questionId)[0];
								if(otherAnswer.length < 1 || otherAnswer.length > 100){
									console.log("1 - 100");
									console.log("otherlenghterror_" + page + "_" + questionId);
									otherError.style.display = 'block';
									anySelected = false;
									break;
								} else {
									otherError.style.display = 'none';
								}
							}
						}
						break;
					}
				}
			}
			
			if(!anySelected){
				questionTitles.push(question);
			}else{
				continue;
			}
		}
	}
}

function validateMultipleQuestions(questions, page){
	if(questions.length == 0){
		console.log("no multiple questions");
		return;
	}else{
		console.log("validating multiple questions");
		for(var i = 0; i<questions.length; i++){
			var question = questions[i];
			var answers = question.getElementsByClassName(question.className.split("_")[0]+"_multipleanswer");
			
			var anySelected = false;
			var isOtherSelected = false;
			var otherAnswer = "";
			
			for(var j = 0; j<answers.length; j++){
				if(answers[j].checked){
					console.log("has selected");
					anySelected = true;
					var value = answers[j].nextElementSibling.innerText.trim();
					console.log("Selected : " + value);
					if(value == 'Other'){
						console.log("other selected");
						isOtherSelected = true;
						var questionId = answers[j].name.split("_")[1];
						var id = "multiple_" + questionId + "_other";
						otherAnswer = document.getElementById(id).value;
						console.log("other answer : " + otherAnswer);
						if(otherAnswer == null){
							console.log("null");
							anySelected = false;
							break;
						} else {
							var otherError = question.getElementsByClassName("otherlenghterror_page" + page + "_question" + questionId)[0];
							if(otherAnswer.length < 1 || otherAnswer.length > 100){
								console.log("1 - 100");
								console.log("otherlenghterror_" + page + "_" + questionId);
								otherError.style.display = 'block';
								anySelected = false;
								break;
							} else {
								otherError.style.display = 'none';
							}
						}
					}
					
				}
			}
			
			if(!anySelected){
				questionTitles.push(question);
			}else{
				continue;
			}
			
		}
	}
	
}