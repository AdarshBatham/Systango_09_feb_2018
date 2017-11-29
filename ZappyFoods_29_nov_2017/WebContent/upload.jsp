<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="images/zappyimage.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="mystyle.css" />
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">
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
	</style>
</head>
<body>
<div class="container">
			<div class="row">
			<div class="col-sm-5">
			<img src="images/zappyimage.png" alt=""></img>
			</div>
			
			
			<div class="col-sm-7 my_menu">
			<nav class="navbar navbar-default">
			<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mynavbar">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			</button>
			</div>
			
			
			<div class="collapse navbar-collapse" id="mynavbar">
			<ul class="nav navbar-nav pull-right">
			<li ><a href="index.jsp">Home</a></li>
			
			
			
			
			</ul>
			</div>
			</nav>
			
			
			</div>
			</div>
			</div>

<%
String id=(String)session.getAttribute("adminid");

if(id!=null)
{
	response.addHeader("pragma","no-cache");
	response.addHeader("cache-control","no-store");
	response.addHeader("expire","0");
%>

<form action="AdminLogout" method="post">
	<input type="submit" name="submit" value="logout"/>
	<a href="adminhome.jsp">back</a>
</form>
<center>
		<h1><font color="blue">${imageuploaded}</font></h1>
		<h1><font color="red">${imagenotuploaded}</font></h1>
	<h1>Add Product:</h1>
	 <form action="Upload" method="post" enctype="multipart/form-data">
	 <table>
 <tr><td>Product Name</td><td><input type="text" name="name" title="only alphabets allowed" pattern="[A-Za-z- ]+" required/></td></tr> 
 <tr><td>price</td><td><input type="text" name="price" placeholder="Rs." pattern="\d+(\.\d{1,2})?" title="only numerics + two places after decimal" required/>  </td></tr>
 <tr><td>Weight</td><td><input type="text" name="weight" placeholder="gms" pattern="\d+(\.\d{1,2})?" title="only numerics + two places after decimal" required/></td></tr>
 <!-- <tr><td>Description</td><td><input type="text" name="description" required/></td></tr> -->
  <tr>
  			  <td>Description</td>
			  <td>
				<textarea rows="4" cols="30" name="description" required>						
				</textarea>
 			   </td>
</tr>
 <tr><td>Image </td><td><input type="file" name="file" accept=".jpg" required/> </td></tr>
 	
   <tr><td><input type="submit" name="submit" value="submit"/></td></tr>
   </table>
 </form>
 </center>
 <%} %>
</body>
</html>