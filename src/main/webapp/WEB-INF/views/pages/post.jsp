<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>



Posted by <c:out value="${userName} at ${time}" />
<br />
<c:out value="${content}" />


<form:form method="POST" modelAttribute="commentForm">
	<form:label path="content">Comment</form:label>
	<form:input path="content" />
	<form:errors path="content" />
</form:form>

<c:forEach items="${comments }" var="comment">
	<c:out value="${comment.userName } ${comment.time }" /><br/>
	<c:out value="${comment.content }" />
</c:forEach>

