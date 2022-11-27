<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html lang="ja">
<head>
<meta charset="UTF-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta http-equiv="x-ua-compatible" content="ie=edge" />
<title>退会完了</title>
<jsp:include page="/common_css.jsp" />
</head>
<body>
	<jsp:include page="/header.jsp" />
	<div class="container" style="max-width: 540px;">
		<div
			class="d-flex flex-column justify-content-center align-items-center">
			<br>
			<h2>退会処理が完了しました</h2>
			<br>
			<p>またのご利用をお待ちしております。</p>
			<br>
			<button type="button" class="btn btn-outline-primary col-10"
				onclick="location.href='/shopper/U_Main'">トップへ戻る</button>
		</div>
	</div>



	<jsp:include page="/common_js.jsp" />

</body>
</html>
