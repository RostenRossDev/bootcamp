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
 
 
<body >
	<header>
	    <h1 style="position: absolute; top: 0px; left:10px; ">${greeting}</h1><p class="text-muted" style="position: absolute; top: 20px; left:800px; ">${pd}</p>
	    <div class="pt-5">
	    	<img src="https://media.istockphoto.com/illustrations/easter-set-with-patterned-eggs-hand-drawn-watercolor-illustration-on-illustration-id1198410935?s=2048x2048"
	    		 height="400" width="100%" style="margin: -160px -200px 0px -0px;">
	    </div>
	</header>
	
	<main class="ms-5 me-5" >
		<div class="d-flex justify-content-center mb-5">
			<h2 class=" ">${manage}</h2>
		</div>
		
		<div class="container-flex g-0">
		
			<div class="row col-12 g-0 ">
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
					
				
				<c:forEach items="${eggsCartons}" var="carton">
					
						<p>carton</p>
						<p>${carton.color}
						<c:set var="line" value="0" scope="page" />
						<c:set var="count" value="0" scope="page" />
						<tr>
						<table >
						<c:forEach items="${carton.eggs}" var="egg" >
								<c:if test="${egg.color == 'RED'}" >
									<td>
										<img src="https://image.shutterstock.com/image-illustration/red-egg-260nw-24543421.jpg" width="50px">
									</td>
								</c:if>
								<c:if test="${egg.color == 'WHITE'}" >
									<td>
										<img src="https://image.shutterstock.com/image-illustration/egg-isolated-on-white-background-260nw-112960825.jpg" width="50px">
									</td>
								</c:if>
								
								
	        					<c:set var="count" value="${count+1}" />
	        					<c:set var="line" value="${line+1}" />
	        					<c:if test="${line>4}">
	        						<c:set var="line" value="${0}" />
	        						
	        						</tr>
	        					</c:if>   
	        					
						</c:forEach>
						<c:if test="${count < 30}">
							
							<c:forEach var="i" begin="${count}" end="29" step="1" varStatus ="status">
									
								<td>
									<img src="https://thumbs.dreamstime.com/b/cracked-eggshell-23573.jpg" width="50px">
									<c:out value="${line}" />
								<td>
								<c:set var="line" value="${line+1}" />
							
								<c:if test="${line>4}">
		        					<c:set var="line" value="${0}" />
		        					</tr>
		        				</c:if>
							</c:forEach>	
						</c:if>
					</table>	
				</c:forEach>
		</div>
	</main>
	
	 
 </body>

</html>