<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

<title>Hens Spring MVC</title>
</head>
<body>

<body>
		<header>
		    <h1 style="position: absolute; top: 0px; left:10px; ">${greeting}</h1><p class="text-muted" style="position: absolute; top: 20px; left:800px; ">${pd}</p>
		    <div class="pt-5">
		    	<img src="https://media.istockphoto.com/illustrations/easter-set-with-patterned-eggs-hand-drawn-watercolor-illustration-on-illustration-id1198410935?s=2048x2048"
		    		 height="400" width="100%" style="margin: -160px -200px 0px -0px;">
		    </div>
		</header>
		
		<main>
			<div class="container-flex g-0 justify-content-center ">
				<div class="row col-1 g-0">
					<a href="/FarmEggsSpringMVC/home" class="btn btn-success">Home</a>
				</div>
			</div>
		</main>
		<div class="container mt-5">
			<div class="mb-3 row">
    		  <label for="staticEmail" class="col-sm-2 col-form-label">Hen Color</label>
	      	  <div class="col-sm-10">
	      	  	<input type="text" readonly class="form-control-plaintext" id="staticEmail" value=${hen.getEggsColor()}>
		      </div>
			</div>
			
			<div class="mb-3 row">
    		  <label for="staticEmail" class="col-sm-2 col-form-label">Eggs</label>
	     	  <div class="col-sm-10">
	     	  	<input type="text" readonly class="form-control-plaintext" id="staticEmail" value=${egss}>
			  </div>
			</div>	
		</div>
		
</body>

</html>