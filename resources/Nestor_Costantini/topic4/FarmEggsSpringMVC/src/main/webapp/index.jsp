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
	    <h1>${greeting}</h1>
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
					<form action="/FarmEggsSpringMVC/newFarmer" method = "POST" >
						<input type="text" name="name"/>
						<input type="submit" class="btn btn-primary" value="Create Eggs Cartons"/>
					</form>			
				</div>
				
				<div class="col-1">
					<a href="#" class="btn btn-primary">Start Farming!!</a>
				</div>
				
				<div class="col-2">
					<a href="#" class="btn btn-primary">Show Cartons Eggs</a>	
				</div>
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
			      <th scope="ro	w">	<a href="#" class="btn btn-primary">1</a></th>
			      <td>Farmers</td>
			      <td>${farmers.size()}</td>
			      <td><a href="#"class="btn btn-danger">Delete</a></td>
			    </tr>
			    
			    <tr>
			      <th scope="row"><a href="#" class="btn btn-primary">2</a></th>
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
  				
  				<tr>
			      <th scope="row"><a href="#" class="btn btn-primary">4</a></th>
			      <td>Eggs</td>
			      <td>${eggs.size()}</td>
			      <td><a href="#"class="btn btn-danger">Delete</a></td>
			    </tr>
			  
			  </tbody>
			</table>
		</div>
		
	</main>
 </body>
 
</html>