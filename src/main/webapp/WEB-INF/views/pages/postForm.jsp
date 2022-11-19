<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<jsp:useBean id="now" class="java.util.Date" />

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<c:url value="/static/css/index.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous" type="text/css">
</head>
<body>

	<div class="post-area">
		<div class="post-box" id="post-box">

			<div class="post-fields">
				<h1>Share your thoughts</h1>
				<p>${user.getUserName() }</p>
				<form:form method="POST" modelAttribute="postForm">


					<!-- Hidden inputs -->
					<form:hidden path="existingPostId" />
					<form:errors path="existingPostId" cssClass="error" />


					<form:hidden path="upvotes" />

					<br />

					<!--  name="start_date-${task.taskId}" value="<fmt:formatDate value='${task.startDate}'
		    var='startFormat' type='date' pattern='yyyy-MM-dd'/>" -->


					<form:textarea path="content" name="post-content" />
					<form:errors path="content" cssClass="error" />
					<br />
					<input type="submit" value="Post" name="submit-button"
						id="postSubmit" />
				</form:form>
			</div>
		</div>
	</div>



</body>