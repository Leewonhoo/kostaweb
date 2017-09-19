<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>service</title>

<script type="text/javascript">
function btn_infor(){
	document.servicepage.submit();
}
</script>

</head>
<body>
	<form name="servicepage" method="post" action="controller">
		<table border="1">
			<tr>
				<td>user</td>
				<td><%= session.getAttribute("userId") %></td>
			</tr>
			<tr>
				<td>
					<input type="button" onclick="btn_idSearch()">
				</td>
				<td>
					<input type="hidden" name="action" value="infor">
					<input type="submit"  value="회원 정보 호출">
				</td>
			</tr>
			<tr>
				<td>
					<a href="controller?action=logout">로그아웃</a>
				</td>
			</tr>
		</table>
	</form>
	
	<br>
	
	<form name="modiForm" action="controller">
		<table>
			<%
				Bmember list = (Bmember)request.getAttribute("Information");
				if (list != null){
			%>
				<tr>
					<td><span>아이디</span></td>
					<td><%= list.getmId() %></td>
				</tr>
				<tr>
					<td><span>비밀번호</span></td>
					<td>
						<input type="text" name="moPw" value=<%= list.getmPw().substring(0, 2)+"****" %>>
					</td>
				</tr>
				<tr>
					<td><span>이름</span></td>
					<td><%= list.getmName() %></td>
				</tr>
				<tr>
					<td><span>닉네임</span></td>
					<td>
						<input type="text" name="moPw" value=<%= list.getmNick() %>>
					</td>
				</tr>
				<tr>
					<td><span>생일</span></td>
					<td><%= list.getmBirth() %></td>
				</tr>
				<tr>
					<td><span>번호</span></td>
					<td><%= list.getmPhone() %></td>
				</tr>
				<tr>
					<td><span>이메일</span></td>
					<td>
						<%
							String[] str = list.getmMail().split("@");
						%>
						<input type="text" name="moEmail" value="<%= str[0].trim() %>">
						@  <input type="text" name="moEmail2" size="10">
						<select name="moDomain">
								<option>::::::: 선택 :::::::</option>
								<option>chol.com</option>
								<option>daum.net</option>
								<option>dreamwiz.com</option>
								<option>empal.com</option>
								<option>gmail.com</option>
								<option>hanmail.net</option>
								<option>hanmir.com</option>
								<option>hanafos.com</option>
								<option>hotmail.com</option>
								<option>lycos.co.kr</option>
								<option>nate.com</option>
								<option>naver.com</option>
								<option>paran.com</option>
								<option>yahoo.co.kr</option>
								<option>직접 입력</option>
						</select>
						<script>
							var takeDomain = document.modiForm.moDomain;
							var inputDomain = document.modiForm.moDomain;
							if (takeDomain.options == "<%= str[1].trim() %>"){
								takeDomain.value = <%= str[1].trim() %>;
								inputDomain.value = <%= str[1].trim() %>;
								inputDomain.readOnly=true;
							} else {
								takeDomain.value = "직접 입력";
								inputDomain.value = <%= str[1].trim() %>;
								inputDomain.readOnly=true;
							}
							document.modiForm.moDomain.value=<%= list.getmQuest() %>
						</script>
						all : <%= list.getmMail() %>
					</td>
				</tr>
				<tr>
					<td><span>성별</span></td>
					<td>
						<input type="radio" name="moGender" value="M">남성
						<input type="radio" name="moGender" value="F">여성
						<script>
							var takeElement = document.modiForm.moGender;
							for (var i=0; i< takeElement.length; i++){
								if (takeElement[i].value == "<%= list.getmGender() %>"){
									takeElement[i].click();
									break;
								}
							}
						</script>
					</td>
				</tr>
				<tr>
					<td><span>주소</span></td>
					<td><%= list.getmAddress() %></td>
				</tr>
				<tr>
					<td><span>질문</span></td>
					<td>
						<select name="moQuestion">
						<option value="1">기억에 남는 추억의 장소는?</option>
						<option value="2">존경하는 인물은?</option>
						<option value="3">좋아하는 물건은?</option>
						<option value="4">좋아하는 글귀는?</option>
						<option value="5">하고싶지 않은일 할 때 하는 표정은?</option>
						<script>document.modiForm.rQuestion.value=<%= list.getmQuest() %></script>
					</select>
					</td>
				</tr>
				<tr>
					<td><span>답변</span></td>
					<td>
						<input type="text" name="moAnswer" value=<%= list.getmAnswer() %>>
					</td>
				</tr>
			<%
				}
			%>
			<tr>
				<td colspan="2">
					<input type="submit" value="변경">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>