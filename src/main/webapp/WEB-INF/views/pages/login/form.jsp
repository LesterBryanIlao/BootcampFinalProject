<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



Catalog: <c:out value="${catalogParameter}"/><br/>
Price: <c:out value="${priceParameter}"/><br/>
<br/>
Search Criteria<br>
Catalog: <c:out value="${searchCriteria.catalog}"/><br/>
Min Price: <c:out value="${searchCriteria.minimumPrice}"/><br/>
Max Price: <c:out value="${searchCriteria.maximumPrice}"/><br/>
Rating: <c:out value="${searchCriteria.rating}"/><br/>
<br/>
Path Parameter: <c:out value="${usernamePathParameter}"/>
<br/>
<form name="loginForm" method="POST">
    Username <input type="text" name="username" id="username"><br/>
    Password <input type="password" name="password" id="password"><br/>
    <input type="Submit" value="Submit" id="loginButton">
</form>