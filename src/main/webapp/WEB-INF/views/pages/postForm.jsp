<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<jsp:useBean id="now" class="java.util.Date" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" type="text/css" href="index.css" />
</head>
<body>




    



<h1>Share your thoughts</h1>
<form:form method = "POST" modelAttribute = "postForm">




    
    <form:label path="existingPostId">Post ID</form:label>
    <form:input path="existingPostId" />
    <form:errors path="existingPostId" cssClass="error" />
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
</html>