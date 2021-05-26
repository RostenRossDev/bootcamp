<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>

<title>FarmEggs Spring MVC</title>
</head>
 
 
<body>
	<header>
		    <h1 style="position: absolute; top: 0px; left:10px; ">${greeting}</h1><p class="text-muted" style="position: absolute; top: 20px; left:800px; ">${pd}</p>
		    <div class="pt-5">
		    	<img src="https://media.istockphoto.com/illustrations/easter-set-with-patterned-eggs-hand-drawn-watercolor-illustration-on-illustration-id1198410935?s=2048x2048"
		    		 height="400" width="100%" style="margin: -160px -200px 0px -0px;">
		    </div>
		</header>
	
	<main>
		<h2>${manage}</h2>
		<p>${pd}</p>
		
		<div class="container-flex g-0">
			<div class="row col-12 g-0">
				<div class="col-2">
					<form action="/FarmEggsSpringMVC/createHenHouse" method = "POST" >
						<input type="submit" class="btn btn-primary" value="Create Hen House" />
					</form>
				</div>
	
				<div class="col-1">		
					<form action="/FarmEggsSpringMVC/layEggs" method = "POST" >
						<input type="submit" class="btn btn-primary" value="Lay Eggs"/>
					</form>
				</div>
				
				<div class="col-3">
					<form action="/FarmEggsSpringMVC/newFarmer" method = "POST" >
						<input type="text" name="name"/>
						<input type="submit" class="btn btn-primary" value="Create Farm"/>
					</form>
				</div>
				
				<div class="col-3">			
					<form action="/FarmEggsSpringMVC/createEggsCartons" method = "POST" >
						<input type="submit" class="btn btn-primary" value="Create Eggs Cartons"/>
					</form>			
				</div>
				<div class="col-3">			
					<form action="/FarmEggsSpringMVC/startFarming" method = "POST" >
						<input type="text" name="index">
						<input type="submit" class="btn btn-primary" value="Start Farming"/>
					</form>			
				</div>
							
			</div>
			<div class="float-end mt-3">
					<a href="/FarmEggsSpringMVC/showEggs" class="btn btn-primary">Show Cartons Eggs</a>	
			</div>
		</div>
		
		<div class="container mt-5">
			  <thead>
			<table class="table">
			    <tr>
			      <th scope="col">#</th>
			      <th scope="col">Type</th>
			      <th scope="col">amount</th>
			      <th scope="col">DeleteAll</th>
			    </tr>
			  </thead>
			  <tbody>
			    
			    <tr>
			      <th scope="ro	w">	<a href="/FarmEggsSpringMVC/farmers" class="btn btn-primary">1</a></th>
			      <td>Farmers</td>
			      <td>${farmers.size()}</td>
			      <td><a href="#"class="btn btn-danger">Delete</a></td>
			    </tr>
			    
			    <tr>
			      <th scope="row"><a href="/FarmEggsSpringMVC/hens" class="btn btn-primary">2</a></th>
			      <td>Hen House</td>
			      <td>${hensSize}</td>
			      <td><a href="#"class="btn btn-danger">Delete</a></td>
			    </tr>
			    
			    <tr>
			      <th scope="row"><a href="#" class="btn btn-primary">3</a></th>
			      <td>Eggs Cartons</td>
			      <td>${eggsCartons.size()}</td>
			      <td><a href="#"class="btn btn-danger">Delete</a></td>			      
			    </tr>
  							  
			  </tbody>
			</table>
		</div>
	</main>
 </body>
 
</html>