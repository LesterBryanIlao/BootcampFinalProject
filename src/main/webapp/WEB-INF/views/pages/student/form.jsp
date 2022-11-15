<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<h2>Student Registration</h2>
<hr />
<c:url var="studentFormUrl" value="/app/student/form" />
<form:form method="POST" modelAttribute="studentForm">
	<form:label path="firstName">First Name</form:label>
	<form:input path="firstName" />
	<form:errors path="firstName" cssClass="error"/>
	<br />
	<form:label path="lastName">Last Name</form:label>
	<form:input path="lastName" />
	<form:errors path="lastName" cssClass="error"/>
	<br />
	<form:label path="age">Age</form:label>
	<form:input path="age" />
	<form:errors path="age" cssClass="error"/>
	<br />
	<form:label path="gradeLevel">Grade Level</form:label>
	<form:input path="gradeLevel" />
	<form:errors path="gradeLevel" cssClass="error"/>
	<br />
	<form:label path="email">Email</form:label>
	<form:input path="email" />
	<form:errors path="email" cssClass="error"/>
	<br />
	<form:label path="birthDate">Birth Date</form:label>
	<form:input path="birthDate" />
	<form:errors path="birthDate" cssClass="error"/>
	<br />
	<input type="submit" value="Register">
</form:form>