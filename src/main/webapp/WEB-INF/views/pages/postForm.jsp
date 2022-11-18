<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:useBean id="now" class="java.util.Date" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
<<<<<<< Updated upstream
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="index.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" 
=======
    <link href="<c:url value="/static/css/index.css" />" rel="stylesheet">
 	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous" 
>>>>>>> Stashed changes
    type="text/css" >
</head>
<body>




    



<h1>Share your thoughts</h1>
<form:form method = "POST" modelAttribute = "postForm">

<<<<<<< Updated upstream



    
    <form:label path="existingPostId">Post ID</form:label>
    <form:input path="existingPostId" />
    <form:errors path="existingPostId" cssClass="error" />
=======
  <form:label path="userId" type="hidden">User ID</form:label>
	<form:hidden path="userId" type="hidden"/>
    <form:input path="userId" type="hidden"/>
    <form:errors path="userId" cssClass="error" type="hidden"/>
    <br />
	
	<form:label path="existingPostId" type="hidden">Post ID</form:label>
    <form:input path="existingPostId" type="hidden" />
    <form:errors path="existingPostId" cssClass="error"  type="hidden"/>
>>>>>>> Stashed changes
    <br />
      
    
    <form:label path="upvotes">up votes</form:label>
    <form:input path="upvotes"  />
    <form:errors path="upvotes" cssClass="error" />
    <br />
    
    <!--  name="start_date-${task.taskId}" value="<fmt:formatDate value='${task.startDate}'
    var='startFormat' type='date' pattern='yyyy-MM-dd'/>" -->
     
    <form:label path="content">What is in your mind?</form:label>
    <form:textarea path="content" />
    <form:errors path="content" cssClass="error" />
    <br />
    <input type="submit" value="Post" />
</form:form>



   
</body>
