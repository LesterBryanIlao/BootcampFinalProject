<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<br />
Posted by
<c:out value="${post.getUser().getUserName()} at ${post.getTime()}" />
<br />
<c:out value="${post.getContent()}" />
<br />
Upvotes:
<c:out value="${post.getUpvotes()}" />

<form:form method="POST" modelAttribute="UpVoteForm">
	<input type="submit" value="Upvote" />
</form:form>

<form:form method="POST" modelAttribute="commentForm">

	<form:label type="hidden" path="existingCommentId"></form:label>
	<form:input type="hidden" path="existingCommentId" />
	<form:errors type="hidden" path="existingCommentId" cssClass="error" />

	<form:label type="hidden" path="userId"></form:label>
	<form:input type="hidden" path="userId" value="${user.getId()}" />
	<form:errors type="hidden" path="userId" cssClass="error" />

	<form:label type="hidden" path="postId"></form:label>
	<form:input type="hidden" path="postId" value="${post.getId()}" />
	<form:errors type="hidden" path="postId" cssClass="error" />

	
	Previous Comments:
	<br />

	<c:forEach items="${comments}" var="comment">
		<c:out value="${comment.getUser().getUserName()} ${comment.getTime()}" />
		<br />
		<c:out value="${comment.getContent()}" />
	</c:forEach>
	<br />

	<form:label path="content">Comment</form:label>
	<form:textarea path="content" />
	<form:errors path="content" cssClass="error" />
	<input type="submit" value="Comment" />
</form:form>

