<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Cache-Control" content="max-age=300" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta name="format-detection" content="telephone=no">

<title>商品已成功加入购物车</title>
<link rel="stylesheet" type="text/css" href="/css/base.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/psearch20131008.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/psearch.onebox.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/pop_compare.css" media="all" />
<script type="text/javascript" src="/js/jquery-1.6.4.js"></script>

<link rel="stylesheet" type="text/css"
	href="/css/ItemIntoCart_files/saved_resource">

<style type="text/css"></style>
<link type="text/css" rel="stylesheet"
	href="/css/ItemIntoCart_files/saved_resource(2)" source="widget">

</head>
<body>
<script type="text/javascript" src="/js/base-2011.js" charset="utf-8"></script>
<!-- header start -->
<jsp:include page="commons/header.jsp" />
<!-- header end -->


<div class="main">
		<div class="success-wrap">
			<div class="w" id="result">
				<div class="m succeed-box">
					<div class="mc success-cont">
						<div class="success-lcol">
							<div class="success-top">
								<b class="succ-icon"></b>
								<h3 class="ftx-02">商品已成功加入购物车！</h3>
							</div>
							<div class="p-item">
								<div class="p-img">
									<a href="http://item.jd.com/2456405.html" target="_blank"><img
										src="${cartItem.images[0] }"
										clstag="pageclick|keycount|201601152|11"></a>
								</div>
								<div class="p-info">
									<div class="p-name">
										<a href="http://www.taotao.com/item/${cartItem.id}.html" target="_blank"
											clstag="pageclick|keycount|201601152|2"
											title="${cartItem.title }">${cartItem.title }</a>
									</div>
									<div class="p-extra">
										<span class="txt">/
											数量：1</span>
									</div>
								</div>
								<div class="clr"></div>
							</div>
						</div>
						<div class="success-btns">
							<div class="success-ad">
								<a
									href="http://cart.jd.com/addToCart.html?rcd=1&amp;pid=2456405&amp;pc=1&amp;rid=1471663404914&amp;em=#none"></a>
							</div>
							<div class="clr"></div>
							<div>
								<a class="btn-tobback"
									href="http://cart.jd.com/addToCart.html?rcd=1&amp;pid=2456405&amp;pc=1&amp;rid=1471663404914&amp;em=#"
									onclick="window.history.back();return false;"
									clstag="pageclick|keycount|201601152|3">返回</a><a
									class="btn-addtocart"
									href="http://www.taotao.com/order-cart.html"
									id="GotoShoppingCart" clstag="pageclick|keycount|201601152|4"><b></b>去购物车结算</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>



<!-- footer start -->
<jsp:include page="commons/footer.jsp" />
<!-- footer end -->
<script type="text/javascript" src="/js/jquery.hashchange.js"></script>
<script type="text/javascript" src="/js/search_main.js"></script>
<script type="text/javascript">
//${paginator.totalPages}
SEARCH.query = "${query}";
SEARCH.bottom_page_html(${page},${totalPages},'');
</script>
</body>
</html>