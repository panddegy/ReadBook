<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<%@include file="/WEB-INF/views/include/include-head.jspf" %>
</head>
<script type="text/javascript">
$(function(){
	$(".table-row").click(function(){
		let id=$(this).attr("data-id")
		location.href="<c:url value='/bookupdate/'/>"+id
	})
})
</script>
<body>
	<%@include file="/WEB-INF/views/include/include-menu.jspf" %>
	<section>
		<c:if test="${BODY=='LIST'}">
			<table>
				<tr>
					<th>NO</th>
					<th>작성자</th>
					<th>도서코드</th>
					<th>제목</th>
					<th>작성일자</th>
					<th>평점</th>
				</tr>
				<c:choose>
				<c:when test="${empty BOOKS}">
					<tr><td colspan="6">입력된 자료가 없습니다.</td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach var="book" items="${BOOKS}" varStatus="i">
						<tr class="table-row" data-id="${book.b_id}">
							<td>${i.count}</td>
							<td>${book.b_userid}</td>
							<td>${book.b_isbn}</td>
							<td>${book.b_title}</td>
							<td>${book.b_date}</td>
							<td>${book.b_star}</td>
						</tr>
					</c:forEach>
				</c:otherwise>
				</c:choose>
			</table>
		</c:if>
		<c:if test="${BODY=='LOGINFORM'}">
			<%@include file="/WEB-INF/views/bodies/login-form.jspf" %>
		</c:if>
		<c:if test="${BODY=='SIGNFORM'}">
			<%@include file="/WEB-INF/views/bodies/signup-form.jspf" %>
		</c:if>
		<c:if test="${BODY=='WRITEFORM'}">
			<%@include file="/WEB-INF/views/bodies/write-form.jspf" %>
		</c:if>
	</section>
<footer>
	<address>Read Book</address>
</footer>
</body>
</html>























