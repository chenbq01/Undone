<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
	<div data-role="page">
		<div data-role="header">
			<h1>${dishinfo['food_name']}</h1>
		</div>
		<div data-role="content">
			<img class="img-fullwidth" src="${dishinfo['pic']}"
				alt="${dishinfo['food_name']}" />
			<div class="ui-content">
				<div class="ui-info">
					<strong>价格：${dishinfo['price']}元/${dishinfo['unit']}&nbsp;&nbsp;推荐次数：${dishinfo['vote_num']}</strong>
				</div>
				<div class="ui-info">${dishinfo['food_intro']}</div>
			</div>
			<ul data-role="listview" data-inset="true" data-theme="d"
				data-divider-theme="a" data-count-theme="a">
				<li data-role="list-divider">食客的评论 <span class="ui-li-count">${commentlist.size()}</span></li>
				<c:if test="${commentlist.size() == 0}">
					<li>
						<p style="text-align: center">暂无评论</p>
					</li>
				</c:if>
				<c:forEach var="comment" items="${commentlist}">
					<li>
						<p>
							<strong>${comment['comm_content']}</strong>
						</p>
						<p class="ui-li-aside">
							<strong>${comment['comm_time']}</strong>
						</p>
					</li>
				</c:forEach>
			</ul>
		</div>
	</div>
</body>