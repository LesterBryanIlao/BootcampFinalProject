<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<link href="<c:url value="/static/css/post.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous" type="text/css">
	<link href="/static/css/footer.css" />
	
	<style>
			footer {
			position: fixed;
			left: 0;
			bottom: 0;
			width: 100%;
			background-color: #FF5349;
			color: white;
			text-align: center;
		}
			
	
	</style>
</head>
<br />



<div class="container">
	<div class="col-md-12 col-lg-12">
		<article class="post vt-post">
			<div class="row">
				<div class="col-xs-10 col-sm-5 col-md-5 col-lg-4">
					<div class="post-type post-img">
						<a href="#"><img
							src="https://www.worldfuturecouncil.org/wp-content/uploads/2020/06/blank-profile-picture-973460_1280-1-705x705.png"></a>
					</div>
					<div class="author-info author-info-2">
						<ul class="list-inline">
							<li>
								<div class="info">
									<p>Posted by:</p>
									<p style="font-weight: bold;">
										<c:out value="${post.getUser().getUserName()}"></c:out>
									</p>
									<p>Posted at:</p>
									<strong><c:out value="${post.getTime()}"></c:out></strong>
								</div>

							</li>
							<li></li>
						</ul>
					</div>
				</div>
				<div class="content">
					<div class="caption">
						<p>
							<c:out value="${post.getContent()}"></c:out>
						</p>
						
						<!--<a class="btn btn-default" href="#" role="button">Read More</a> -->
					</div>
				</div>
				
			</div>
		</article>
		<div class="clearfix"></div>
	</div>
</div>
<c:url value="post/deletePost" var="deletePostActionUrl" />

	<form:form method="POST" modelAttribute="deleteForm"
		action="${deletePostActionUrl}">
		${requestS }
		<form:input type="hidden" path="postId" value="${post.getId()}" />
		<div class="delete-button">
			<input type="submit" value="Delete" id="delete-button"/>
			</div>
	</form:form>
	
	
	
	<c:url value="postForm" var="updatePostUrl" />

	<form:form method="GET" action="${updatePostUrl}">
		<input type="hidden" name="postId" value="${post.getId() }">
		<input type="submit" value="EditPost" >
	</form:form>


<%--
<form:form method="POST" modelAttribute="upvoteForm">
	<form:label type="hidden" path="existingPostId"></form:label>
	<form:input path="existingPostId" value="${post.getId()}"/>
	<form:errors type="hidden" path="existingPostId" cssClass="error" />
	
	<form:label type="hidden" path="content"></form:label>
	<form:input path="content" value="${post.getContent()}" />
	<form:errors type="hidden" path="content" cssClass="error" />
	
	<form:label type="hidden" path="upvotes"></form:label>
	<form:input path="content" value="${post.getUpvotes}" />
	<form:errors type="hidden" path="upvotes" cssClass="error" />
	
	<input type="submit" value="Upvote" />
</form:form>
--%>

<form:form method="POST" modelAttribute="commentForm">

	<form:label type="hidden" path="existingCommentId"></form:label>
	<form:input type="hidden" path="existingCommentId" />
	<form:errors type="hidden" path="existingCommentId" cssClass="error" />

	<form:label type="hidden" path="postId"></form:label>
	<form:input type="hidden" path="postId" value="${post.getId()}" />
	<form:errors type="hidden" path="postId" cssClass="error" />


	<div class="comment-elements">
		<form:label path="content"></form:label>

		<form:textarea placeholder="Share your thoughts... " path="content" name="comment-content" />
		<form:errors path="content" cssClass="error" />

	</div>
	<div class="vertical-center">
		<input type="submit" value="Comment" id="submit" />
	</div>
	<br />
	<c:forEach items="${comments}" var="comment">
		<div class="container">
			<div class="col-md-12 col-lg-12">
				<article class="post vt-post">
					<div class="row">
						<div class="col-xs-10 col-sm-5 col-md-5 col-lg-4">
							<div class="post-type post-img">
								<a href="#"><img
									src="https://www.worldfuturecouncil.org/wp-content/uploads/2020/06/blank-profile-picture-973460_1280-1-705x705.png"></a>
							</div>
							<div class="author-info author-info-2">
								<ul class="list-inline">
									<li>
										<div class="info">
											<p>Commented by:</p>
											<p style="font-weight: bold;">
												<c:out value="${comment.getUser().getUserName()}"></c:out>
											</p>
											<p style="font-weight: bold;">
												<c:out value="${comment.getTime()}"></c:out>
											</p>

										</div>

									</li>
									<li></li>
								</ul>
							</div>
						</div>
						<div class="comment-content">
							<div class="caption">
								<text> <c:out value="${comment.getContent()}"></c:out> </text>
							</div>
						</div>
					</div>
				</article>
				<div class="clearfix"></div>
			</div>
		</div>
	</c:forEach>
	<br />

</form:form>