/**
 *
 */
// 軽イン情報変更でのみ必要なバリデーション
activateValidation('pass_outline', 'password', 'sub_form', 'subm2');
activateValidation('newpass_outline', 'newpassword1', 'sub_form', 'subm2');
activateDoublePassValidation('newpass2_outline', 'newpassword2', 'newpassword1', 'sub_form', 'subm2');