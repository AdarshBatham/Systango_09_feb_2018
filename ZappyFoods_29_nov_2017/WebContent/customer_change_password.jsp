<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="images/zappyimage.ico" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
	 function function1()
	 {
		 alert("really want to change password ?");
	 }
	 function check()
	 {
		 var newpassword=document.getElementById("newpassword").value;
		 var confirmpassword=document.getElementById("newpassword").value;
		 
		 var result=newpassword.match(confirmpassword);
	 }
</script>
<link rel="stylesheet" href="css/bootstrap.css" />
	<link rel="stylesheet" href="mystyle.css" />
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.6.3/css/font-awesome.min.css">

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
			<form action="CustomerLogoutController" method="post">	
		<input type="submit" name="submit" value="logout"/>
	</form>
<form action="customer_home.jsp" method="post">
		<input type="submit" name="submit" value="back"/>
	</form>	
		
	
<center>
	<form action="CustomerChangePasswordController" method="post">
	<table>
	
		<font color="blue"><h1>${customerpasswordchanged}</h1></font>
		<font color="red"><h1>${customerpasswordnotchanged}</h1></font>
			<tr><td>Old Password</td><td><input type="text" name="oldpassword" required/></td></tr>
			<tr><td>New Password</td><td><input type="password" id="newpassword" name="newpassword" required/></td></tr>
			<tr><td>Confirm Password</td><td><input type="password" id="confirmpassword" name="confirmpassword" required onBlur="check()"/></td></tr>		
			<tr><td></td><td><input type="submit" name="submit" value="ok" onClick="function1()"/></td></tr>
	</table>
		</form>
	
</center>
</body>
</html>