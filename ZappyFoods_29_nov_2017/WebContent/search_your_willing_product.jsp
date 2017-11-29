

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.ArrayList" %>
    <%@page import="beans.Product" %>
    <%@page import="beans.Cart" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="adarsh11">
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<style>
			
			.my_menu{ 
					margin-top:27px;}
				.my_menu .navbar-default{
					background:none; border:none;}
				.my_menu .navbar-nav .active a, .my_menu .navbar-nav .active a:hover{background:#006C3A; color:#fff;}
				.my_menu .navbar-nav li a:hover{background:#006C3A; color:#fff;}
				@media(max-width:1200px){
					.my_menu .pull-right{float:none !important;}
					.cartvalue .pull-left{float:none !important;}
					}

			table { 
		  width: 100%; 
		  border-collapse: collapse; 
		}
		/* Zebra striping */
		tr:nth-of-type(odd) { 
		  background: #eee; 
		}
		tr:nth-of-type(even) { 
		  background: #eee; 
		}
		th { 
		  background: #333; 
		  color: white; 
		  font-weight: bold; 
		}
		td, th { 
		  padding: 6px; 
		  border: 1px solid #ccc; 
		  text-align: left; 
		}
		@media 
		only screen and (max-width: 760px),
		(min-device-width: 768px) and (max-device-width: 1024px)  {
		
			/* Force table to not be like tables anymore */
			table, thead, tbody, th, td, tr { 
				display: block; 
			}
			
			/* Hide table headers (but not display: none;, for accessibility) */
			thead tr { 
				position: absolute;
				top: -9999px;
				left: -9999px;
			}
			
			tr { border: 1px solid #ccc; }
			
			td { 
				/* Behave  like a "row" */
				border: none;
				border-bottom: 1px solid #eee; 
				position: relative;
				padding-left: 50%; 
			}
			
			td:before { 
				/* Now like a table header */
				position: absolute;
				/* Top/left values mimic padding */
				top: 6px;
				left: 6px;
				width: 45%; 
				padding-right: 10px; 
				white-space: nowrap;
			}
			
		
</style>
	
	
	
	
	
	
</head>
<body>
		
<a href="index.jsp"><font color="blue">back</font></a>
<%
String json=(String)request.getAttribute("data");
%>
<center>
<h1><font color="green">${m2}</font></h1>
<font color="orange"><h1>Find Your Product</h1></font>
	
	<div ng-controller="mycontroller">

	 	<font color="orange"><h1>Enter Name: </h1></font><input type="text" ng-model="name"/>	 
		<table>
			
			<tr class="col-sm-3" ng-repeat="data in products | filter:name">
				<td>
					<%
						if(json==null||json!=null)
						{
					%>
							<form action="AddToCartControllerFromJson" method="post">
							<br/><br/>								
								<center><h4>{{data.name}}</h4></center>
								<center>Price  = {{data.price}} Rs.</center>
								<center>Weight = {{data.weight}} gms.</center>
								<center>{{data.description}}</center>
								<center><image src="images/{{data.image}}" height="150" width="150"/></center>
								<center><input type="hidden" value="{{data.id}}" name="hidden1"/></center>
								<!-- <center><input type="number" min="1" max="10" value="1" name="productquantity"/></center> -->
								<center><input type="number" name="productquantity" value="1" min="1" required/></center>
								<center><input type="submit" class="btn btn-primary" value="Add to cart" name="submit" onClick="return confirm('Really you want to add product cart')" /><br/></center>						
							</form>
							<center>
										<form action="ViewProductDescriptionControllerFromInside" method="post">
											<input type="hidden" value="{{data.id}}" name="id"/>
											<input type="hidden" value="1" name="json_check"/>
											<input type="submit" value="View Details" class="btn btn-primary" name="submit"/>									
										</form>
							</center>
					<%
						}
						
					%>
				</td>
			</tr>
		
		</table>
	</div>
		
</center>	


</body>



<script>
	var mymodule=angular.module('adarsh11',[]);
	mymodule.controller('mycontroller',test);
	
	function test($scope,$http)
	{
		$http(
				{
					url:'Test',
					method:'POST'
				}	
			).then(
            		function(response)
            		{
	            	 $scope.products = response.data;
	            	}, 
	            	function(response)
		            {	            		
		                $scope.products = response;
		            }
        		);
	}

</script>
</html>