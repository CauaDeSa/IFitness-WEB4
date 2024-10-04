"use strict"

window.onload = initPage;

function initPage(){
	let form;
	form = document.getElementById('form1');
	// desativar a validação automática do HTML5
	form.noValidate = true;
	form.addEventListener('submit', function(e){
		let valid = processValidity(this);
		if(!valid){
			e.preventDefault();
		}
	});
}

function processValidity(form){
	let valid;
	validatePassword(form);
	valid = applyValidity(form);
	return valid;
}

function applyValidity(form){
	let valid = true;
	let count = 0;
	let elements = form.elements;
	for(let i = 0; i < elements.length - 1; i++){
		let element = elements[i];
		let span = document.getElementById(i);
		if(!element.validity.valid){
			span.innerHTML = element.validationMessage;
			count++;
		}else{
			span.innerHTML = "";
		}
	}
	if(count > 0){
		valid = false;
	}
	return valid;
}

function validatePassword(form){
	let password, confirmPassword;
	password = document.getElementById('password');
	confirmPassword = document.getElementById('confirmPassword');
	if(password.value != confirmPassword.value){
		password.setCustomValidity(
		'Os valores dos campos de senha e confirmação '+ 
		'de senha são diferentes.');
	}else{
		password.setCustomValidity('');
	}
}




