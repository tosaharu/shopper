/**
 *
 */
let ol = document.getElementById('email_outline');
console.log(ol)

function activateValidation(outline_id, form_id,) {
	let outline = document.getElementById(outline_id);
	console.log(outline)
	let form = document.getElementById(form_id);
	console.log(form)

	form.addEventListener('focus', function () {
		outline.classList.remove('was-validated');
	}, false)

	form.addEventListener('blur', function () {
		outline.classList.add('was-validated');
		let subm_form = document.querySelector('form');
		let subm = document.getElementById('subm');
		if (subm_form.checkValidity()) {
			subm.removeAttribute('disabled');
		} else {
			subm.setAttribute('disabled', 'disabled');

		}
		let validationMessage = form.validationMessage;
		console.log(validationMessage);
		let insertAt = form.nextElementSibling;
		console.log(insertAt);
		insertAt.innerHTML = validationMessage;
	}, false)
}

function activateRadioValidation(outline_id, form_class, invalid_feedback_id) {
	let outline = document.getElementById(outline_id);
	console.log(outline)
	let forms = document.getElementsByName(form_class);
	console.log(forms)
	let insertAt = document.getElementById(invalid_feedback_id);
	console.log(insertAt)

	for (let form of forms) {
		form.addEventListener('focus', function () {
			outline.classList.remove('was-validated');
		}, false)

		form.addEventListener('blur', function () {
			outline.classList.add('was-validated');
			let subm_form = document.querySelector('form');
			let subm = document.getElementById('subm');
			if (subm_form.checkValidity()) {
				subm.removeAttribute('disabled');
			} else {
				subm.setAttribute('disabled', 'disabled');

			}
			let validationMessage = form.validationMessage;
			console.log(validationMessage);
			insertAt.innerHTML = validationMessage;
		}, false)
	}
}

function activateDoublePassValidation(outline_id, form_id, reference_id) {
	let outline = document.getElementById(outline_id);
	console.log(outline)
	let form = document.getElementById(form_id);
	console.log(form)
	let reference = document.getElementById(reference_id);
	console.log(reference)

	form.addEventListener('focus', function () {
		form.classList.remove('is-valid');
		form.classList.remove('is-invalid');
		outline.classList.remove('was-validated');
	}, false)


	form.addEventListener('blur', function () {
		console.log(form.value);
		console.log(reference.value);
		if (form.value == reference.value) {
			console.log("パス合致");
			form.classList.remove('is-invalid');
			form.classList.add('is-valid');
			form.setCustomValidity("")

		} else {
			console.log("パス不一致");
			form.classList.remove('is-valid');
			form.classList.add('is-invalid');
			form.setCustomValidity("パスワードが一致しません")

		}
		outline.classList.add('was-validated');
		let subm_form = document.querySelector('form');
		let subm = document.getElementById('subm');
		if (subm_form.checkValidity()) {
			subm.removeAttribute('disabled');
		} else {
			subm.setAttribute('disabled', 'disabled');

		}
		let validationMessage = form.validationMessage;
		console.log(validationMessage);
		let insertAt = form.nextElementSibling;
		console.log(insertAt);
		insertAt.innerHTML = validationMessage;
	}, false)
}

function activateExistingMailValidation(outline_id, form_id) {
	let outline = document.getElementById(outline_id);
	console.log(outline)
	let form = document.getElementById(form_id);
	console.log(form)

	form.addEventListener('focus', function () {
		form.classList.remove('is-valid');
		form.classList.remove('is-invalid');
		outline.classList.remove('was-validated');
	}, false)


	form.addEventListener('blur', function () {
		$.ajax({
			url: "Ajax_CheckUserMail",
			type: "GET",
			data: { mail: $('#email').val() }
		}).done(function (result) {
			//通信成功時のコールバック
			let checkMail = Number(result);
			if (checkMail == 0) {
				console.log("登録済のメールアドレス無し");
				form.classList.remove('is-invalid');
				form.classList.add('is-valid');
				form.setCustomValidity("")
			} else if (checkMail == 1) {
				console.log("登録済のメールアドレス有り");
				form.classList.remove('is-valid');
				form.classList.add('is-invalid');
				form.setCustomValidity("そのメールアドレスは既に使用されています")
			}
			outline.classList.add('was-validated');
			let subm_form = document.querySelector('form');
			let subm = document.getElementById('subm');
			if (subm_form.checkValidity()) {
				subm.removeAttribute('disabled');
			} else {
				subm.setAttribute('disabled', 'disabled');

			}
			let validationMessage = form.validationMessage;
			console.log(validationMessage);
			let insertAt = form.nextElementSibling;
			console.log(insertAt);
			insertAt.innerHTML = validationMessage;
			//やりたい処理
		}).fail(function () {
			//通信失敗時のコールバック
			console.log("通信失敗");
		}).always(function () {
			//常に実行する処理
		});

	}, false)
}

activateExistingMailValidation('email_outline', 'email');
activateValidation('pass_outline', 'password1');
activateDoublePassValidation('pass2_outline', 'password2', 'password1');
activateValidation('name_outline', 'name');
activateRadioValidation('gender_outline', 'gender', 'invalid-feedback-gender');
activateValidation('year-select_outline', 'year-select');
activateValidation('month-select_outline', 'month-select');
activateValidation('day-select_outline', 'day-select');
activateValidation('region_outline', 'region');
activateValidation('prefecture_outline', 'prefecture');
activateValidation('area_outline', 'area');

// $('#email').on('blur', function () {
// 	$.ajax({
// 		url: "Ajax_CheckUserMail",
// 		type: "GET",
// 		data: { mail: $('#email').val() }
// 	}).done(function (result) {
// 		//通信成功時のコールバック
// 		let checkMail = Number(result);
// 		if (checkMail == 0) {
// 			console.log("登録済のメールアドレス無し");
// 		} else if (checkMail == 1) {
// 			console.log("登録済のメールアドレス有り");
// 		}
// 		//やりたい処理
// 	}).fail(function () {
// 		//通信失敗時のコールバック
// 		console.log("通信失敗");
// 	}).always(function () {
// 		//常に実行する処理
// 	});
// })

