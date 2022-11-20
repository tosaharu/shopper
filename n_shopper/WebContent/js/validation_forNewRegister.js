/**
 *
 */
// 新規登録でのみ必要なバリデーション
activateValidation('pass_outline', 'password1', 'main_form', 'subm1');
activateDoublePassValidation('pass2_outline', 'password2', 'password1', 'main_form', 'subm1');
activateValidation('year-select_outline', 'year-select', 'main_form', 'subm1');
activateValidation('month-select_outline', 'month-select', 'main_form', 'subm1');
activateValidation('day-select_outline', 'day-select', 'main_form', 'subm1');