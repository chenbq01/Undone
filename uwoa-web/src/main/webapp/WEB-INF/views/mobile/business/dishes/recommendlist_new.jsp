<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<body>
	<div data-role="page" data-fullscreen="true">
		<%@include
			file="/WEB-INF/views/mobile/business/common/ordernoheader.jsp"%>
		<div data-role="content">
			<%@include
				file="/WEB-INF/views/mobile/business/common/mainplaceholder.jsp"%>
			<c:if test="${recommendlist.size() == 0}">
				<p style="text-align: center">暂无推荐菜品</p>
			</c:if>
			<c:if test="${recommendlist.size() > 0}">
				<div class="device">
					<a class="arrow-left" href="#"></a> <a class="arrow-right" href="#"></a>
					<div class="swiper-container">
						<div class="swiper-wrapper">
							<c:forEach var="recommend" items="${recommendlist}"
								varStatus="status">
								<div class="swiper-slide">
									<div class="content-slide">
										<img src="${recommend['pic']}" width="250px" height="250px" />
										<p class="title">
											No${status.count}.${recommend['food_name']}&nbsp;<a
												data-role="button" data-mini="true" data-icon="star"
												data-theme="b" data-corners="false" data-inline="true"
												href="javascript:quickbuy('<c:url value="/mobileorder/temporary/quickbuy"/>', '${sessionScope._orderno}', '${recommend['id']}', '${recommend['food_name']}', '${recommend['unit']}', '${recommend['price']}');">我想吃</a>
											已点<span id="${recommend['id']}" class="ui-li-count">${recommend['ordernum']==null?"0":(recommend['ordernum']==0?"?":recommend['ordernum'])}</span>份
										</p>
										<p>
											${recommend['price']}元/${recommend['unit']}&nbsp;&nbsp;推荐次数${recommend['vote_num']}&nbsp;&nbsp;<a
												data-role="button" data-mini="true" data-icon="search"
												data-theme="a" data-corners="false" data-inline="true"
												href="#"
												onclick="javascript:getDishesCommentData('${recommend['id']}');">查看评论</a>
										</p>
										<p>${recommend['food_intro']}</p>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					<div class="pagination"></div>
				</div>
			</c:if>
		</div>
		<div data-role="popup" id="demo" data-theme="a">
			<a href="#" data-rel="back" data-role="button" data-theme="a"
				data-icon="delete" data-iconpos="notext" class="ui-btn-left">Close</a>

			<div id="demo_info" data-theme="a" style="overflow: auto;">
				<div class="ui-content ui-body-c">
					<p style="text-align: center">
						<strong id="food_name"></strong>
					</p>
					<img id="food_img" class="img-fullwidth" src="" alt="" />
					<div class="ui-content">
						<div id="food_price" class="ui-info"></div>
						<div id="food_intro" class="ui-info"></div>
					</div>
					<ul id="commentlist" data-role="listview" data-inset="true"
						data-theme="d" data-divider-theme="a" data-count-theme="a">
					</ul>
				</div>
			</div>
		</div>
		<%@include file="/WEB-INF/views/mobile/business/common/navfooter.jsp"%>
		<script type="text/javascript">
			var mySwiper = new Swiper('.swiper-container', {
				pagination : '.pagination',
				loop : true,
				grabCursor : true,
				paginationClickable : true
			})
			$('.arrow-left').on('click', function(e) {
				e.preventDefault()
				mySwiper.swipePrev()
			})
			$('.arrow-right').on('click', function(e) {
				e.preventDefault()
				mySwiper.swipeNext()
			})
		</script>
	</div>
</body>