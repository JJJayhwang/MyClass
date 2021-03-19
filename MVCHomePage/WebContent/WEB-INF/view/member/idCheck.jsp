<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${check!=0 }">
		<div align="center">
			이미 사용중인 아이디입니다.
			<form action="${root}/member/idCheck.do">
			
				<input type="text" name="id">
				<input type="submit" value="확인">
			
			</form>
		</div>	
	</c:if>
	
	<c:if test= "${check==0 }">
		<div align="center">
			사용 가능한 아이디 입니다
		</div>
		
		
		
		<script type="text/javascript">
			opener.joinform.id.value="${id}";
										/* input태그의 id가 아닌 request의 id */
		</script>
	</c:if>	
	
	<div align="center">
		<a href="javascript:self.close()">닫기</a>
	</div>
	
	
	
	
	
</body>
</html>