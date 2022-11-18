<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>




Posted by
<c:out value="${post.getUser().getUserName()} at ${post.getTime()}" />
<br />
<c:out value="${post.getContent()}" />

<form:form method="POST" modelAttribute="commentForm">

	<form:label path="existingCommentId">Comment ID</form:label>
	<form:input path="existingCommentId" />
	<form:errors path="existingCommentId" cssClass="error" />
	<br />

	<form:label path="userId">User ID</form:label>
	<form:textarea path="userId" />
	<form:errors path="userId" cssClass="error" />
	<br />

	<form:label path="postId">Post ID</form:label>
	<form:input path="postId" />
	<form:errors path="postId" cssClass="error" />
	<br />

	<form:label path="content">Comment</form:label>
	<form:textarea path="content" />
	<form:errors path="content" cssClass="error" />
	<br />
	<input type="submit" value="Comment" />
</form:form>

Comments
<c:forEach items="${comments}" var="comment">
	<c:out value="${comment.getUser().getUserName()} ${comment.getTime()}" />
	<br />
	<c:out value="${comment.getContent()}" />
</c:forEach>