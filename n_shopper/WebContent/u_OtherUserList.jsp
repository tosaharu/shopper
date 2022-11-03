<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@page import="model.U_User,java.text.SimpleDateFormat"%>
<%@page import="model.U_User"%>
<%@page import="model.U_OtherUser"%>
<!-- リクエストスコープからエラーメッセージを受け取る -->
<%
U_User user = (U_User) session.getAttribute("loginUser");
%>
<%
List<U_OtherUser> rankingUserList = (List<U_OtherUser>) request.getAttribute("rankingUserList");
%>

<!DOCTYPE html>
<head>
<title>他ユーザーリスト</title>
</head>
<body>

	<div class="container">
		<div class="d-flex justify-content-center align-items-center">
			<div class="">
				<br> <br>
				<h2>フォロワーランキング</h2>
				<br>

				<%
				for (int i = 0; i < rankingUserList.size(); i++) {
				%>
				<tr>
					<th>ランキング</th>
					<td><%=rankingUserList.get(i).getCount_ranking()%></td>
					<th>ユーザー名</th>
					<td><%=rankingUserList.get(i).getUser_name()%></td>

					<form action="/shopper/U_Follow" method="get">
						<input type=’hidden’ name=’namae’ value=’’ /> <input
							type=’submit’ value=’フォローする’ />
						<th>フォロワー数</th>
						<td><%=rankingUserList.get(i).getCount_follower()%></td> <br></br>
				</tr>
				<%
				}
				%>


			</div>
		</div>
	</div>




</body>
</html>
