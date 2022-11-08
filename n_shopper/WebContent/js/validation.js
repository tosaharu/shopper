/**
 *
 */
let ol = document.getElementById('email_outline');
console.log(ol)

function emptyValidation(outline_id, form_id) {
	let outline = document.getElementById(outline_id);
	console.log(outline)
	let form = document.getElementById(form_id);
	console.log(form)

	form.addEventListener('focus', function() {
		outline.classList.remove('was-validated');
	}, false)


	form.addEventListener('blur', function() {
		if (form.value != "") {
			form.classList.remove('is-invalid');
			form.classList.add('is-valid');
		} else {
			form.classList.remove('is-valid');
			form.classList.add('is-invalid');

		}
		outline.classList.add('was-validated');
		let subm_form = document.querySelector('form');
		let subm = document.getElementById('subm');
		if (subm_form.checkValidity()) {
			subm.removeAttribute('disabled');
		} else {
			subm.setAttribute('disabled','disabled');

		}
	}, false)
}

emptyValidation('email_outline', 'email');
emptyValidation('pass_outline', 'password1');
emptyValidation('pass2_outline', 'password2');
emptyValidation('name_outline', 'name');
emptyValidation('year-select_outline', 'year-select');
emptyValidation('month-select_outline', 'month-select');
emptyValidation('day-select_outline', 'day-select');
emptyValidation('region_outline', 'region');
emptyValidation('prefecture_outline', 'prefecture');
emptyValidation('area_outline', 'area');

$('#email').on('blur',function(){
	$.ajax({
		url:"Ajax_CheckUserMail",
		type:"GET",
		data:{mail:$('#email').val()}
	}).done(function(result){
		//通信成功時のコールバック
		//やりたい処理
	}).fail(function(){
		//通信失敗時のコールバック
		//やりたい処理
	}).always(function(){
		//常に実行する処理
	});
})


