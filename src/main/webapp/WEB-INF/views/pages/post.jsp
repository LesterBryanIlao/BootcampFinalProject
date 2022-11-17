<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


Posted by
<c:out value="${post.userName} at ${post.time}" />
<br />
<c:out value="${post.content}" />


<form:form method="POST" modelAttribute="commentForm">
	<form:label path="content">Comment</form:label>
	<form:textarea path="content" />
	<form:errors path="content" />
	<br />
	<input type="submit" value="Comment" />
</form:form>

<c:forEach items="${comments }" var="comment">
	<c:out value="${comment.userName } ${comment.time }" />
	<br />
	<c:out value="${comment.content }" />
</c:forEach>

