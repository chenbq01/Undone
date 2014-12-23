<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
	<div data-role="page" data-fullscreen="false">
		<div data-role="content">
			<ul data-role="listview" data-inset="true" data-filter="true">
				<c:forEach var="log" items="${list}">
					<li><c:out value="${log.id}" /></li>
					<li><c:out value="${log.log_thread}" /></li>
					<li><c:out value="${log.log_level}" /></li>
					<li><c:out value="${log.log_class}" /></li>
					<li><c:out value="${log.log_message}" /></li>
				</c:forEach>
			</ul>
			<br /> <a href="<c:url value="/home"/>" data-role="button"
				data-icon="star">返回</a>
		</div>
		<!-- /content -->

		<div data-role="footer" data-position="fixed">
			<div data-role="navbar">
				<ul>
					<li><a href="a.html" class="ui-btn-active">One</a></li>
					<li><a href="b.html">Two</a></li>
				</ul>
			</div>
			<!-- /navbar -->
		</div>
		<!-- /footer -->
	</div>
	<!-- /page -->
</body>