<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="style.css">

</head>
<body>
	<c:forEach items="${posts}" var ="post">
    <div class="container">
        <div class="col-md-12 col-lg-12">
            <article class="post vt-post">
                <div class="row">
                    <div class="col-xs-10 col-sm-5 col-md-5 col-lg-4">
                        <div class="post-type post-img">
                            <!-- <a href="#"><img src="https://www.worldfuturecouncil.org/wp-content/uploads/2020/06/blank-profile-picture-973460_1280-1-705x705.png" 
                                ></a> -->
                        </div>
                        <div class="author-info author-info-2">
                            <ul class="list-inline">
                                <li>
                                    <div class="info">
                                        <p style="font-weight: bold;"><c:out value="Hello ${post.getUser().getUserName()}"></c:out></p>
                                        <p>Posted on:</p>
                                        
                                        <strong><c:out value="${post.getDate()}"></c:out></strong>
                                    </div>
                                </li>
                                <li>                      
                                </li>
                            </ul>
                        </div>
                    </div>
                    <div class="content">
                        <div class="caption">
                            <h3 class="heading">The Heading Text Size Should Match</h3>
                            <p> <c:out value="${post.getContent()}"></c:out></p>
                            <!--<a class="btn btn-default" href="#" role="button">Read More</a> -->
                        </div>
                    </div>
                </div>
            </article>                                
            <div class="clearfix"></div>
        </div>
    </div>
    </c:forEach>