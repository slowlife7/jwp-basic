<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="kr">
<head>
	<%@ include file="/include/header.jspf" %>
</head>
<body>
<%@ include file="/include/navigation.jspf" %>

<div class="container" id="main">
	<div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
		<div class="panel panel-default qna-list">
			<ul class="list">
				<c:forEach items="${questions}" var="question">
				<li>
					<div class="wrap">
						<div class="main">
								<strong class="subject">
									<a href="/qna/show?questionId=${question.questionId}">${question.title}</a>
								</strong>
								<div class="auth-info">
									<i class="icon-add-comment"></i>
									<span class="time">${question.createdDate}</span>
									<a href="./user/profile.html" class="author">${question.writer}</a>
								</div>
								<div class="reply" title="댓글">
									<i class="icon-reply"></i>
									<span class="point">${question.countOfAnswer}</span>
								</div>
						</div>
					</div>
				</li>
				</c:forEach>
			</ul>
			<div class="row">
				<div class="col-md-3"></div>
				<div class="col-md-6 text-center">
					<ul class="pagination center-block" style="display:inline-block;">
						<li><a href="#">«</a></li>
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
						<li><a href="#">»</a></li>
					</ul>
				</div>
				<div class="col-md-3 qna-write">
					<a href="/qna/form" class="btn btn-primary pull-right" role="button">질문하기</a>
				</div>
			</div>
		</div>
	</div>
</div>
<%@ include file="/include/footer.jspf" %>
</body>
</html>