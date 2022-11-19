<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="<c:url value="/static/css/index.css" />" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous" type="text/css">
		
	<style>
			footer {
			position: fixed;
			left: 0;
			bottom: 0;
			width: 100%;
			background-color: #FF5349;
			color: white;
			text-align: center;
		}
	
	</style>
</head>
<div class="row justify-content-center">
    <div class="col-md-12 col-sm-12">
        <div class="card shadow-lg border-0 rounded-lg mt-5 mx-auto" style="width: 30rem;">
            <h3 class="card-header display-1 text-muted text-center">
                Error!
            </h3>

            <span class="card-subtitle mb-2 text-muted text-center">
                <c:out value="${error}" />
            </span>

            <div class="card-body mx-auto">
                <a type="button" href="home"
                class="btn btn-sm btn-info text-white"> Back To Home </a>
            </div>
        </div>
    </div>
</div>
