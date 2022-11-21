<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<link href="<c:url value="/static/css/style.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous" type="text/css">

<style>
footer {
	position: fixed;
	left: 0;
	bottom: 0;
	width: 100%;
	height: 50px;
	background-color: #FF5349;
	color: white;
	text-align: center;
}
</style>

</head>
<body>

	<c:url value="postForm" var="postFormUrl" />
	<form:form action="${postFormUrl }">
	<div class="new-post">
		<input  type="submit" id="new-post" value="Share your thoughts.."/>
	</div>
	</form:form>
	
	
	<c:forEach items="${posts}" var="post">

		<div class="container">
			<div class="col-md-12 col-lg-12">
				<article class="post vt-post">
					<div class="row">
						<div class="col-xs-10 col-sm-5 col-md-5 col-lg-4">
							<div class="post-type post-img">
								<a href="#"><img
									src="https://avatars.dicebear.com/api/male/john.svg?mood[]=happy&mood[]=sad
"></a>
							</div>
							<div class="author-info author-info-2">
								<ul class="list-inline">
									<li>
										<div class="info">
											<p style="font-weight: bold;">
												<c:out value="${post.getUser().getUserName()}"></c:out>
											</p>
											<p>Posted on:</p>
											<strong><c:out value="${post.getTime()}"></c:out></strong>
										</div>

									</li>
									<li></li>
								</ul>
							</div>
						</div>
						<div class="content">
							<div class="caption">
								<a href="post?postId=${post.getId()}">
									<p>
										<c:out value="${post.getContent()}"></c:out>
									</p>
								</a>
								<!--<a class="btn btn-default" href="#" role="button">Read More</a> -->
							</div>
						</div>
					</div>
				</article>
				<div class="clearfix"></div>
			</div>
		</div>

	</c:forEach>