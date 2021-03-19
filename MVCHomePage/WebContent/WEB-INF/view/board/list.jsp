<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<c:set var="root" value="${pageContext.request.contextPath}"></c:set>
<html>
<head>
<meta charset="UTF-8">
<title>목록 페이지</title>
</head>
<body>
	<div align="right" >
		<table>
			<tr>
				<td>
					<a href="${root}/board/write.do">글쓰기</a>
				</td>
			</tr>
		</table>
	</div>
	
	
	<c:if test="${count==0 && boardList.size()==0 }">
		<div align="center">
			게시판에 저장된 글이 없습니다.
		</div>
	</c:if>

	<c:if test="${count>0}">
		<div align="center">
			<table>
				
			</table>
		</div>
	</c:if>
</body>

</html>