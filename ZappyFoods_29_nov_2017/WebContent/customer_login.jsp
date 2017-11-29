<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="shortcut icon" type="image/x-icon" href="images/zappyimage.ico" />
 <script language="Javascript" src="js/jquery1.js"></script>
    <script type="text/JavaScript" src='js/state.js'></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
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
<script type="text/javascript">
	$(document).ready(function()
					  {
		$("#email").blur(function()
						{
			/* alert("hiiiiiii"); */
							 var data="email="+$("#email").val();
								$.ajax(
										{
											url:'CustomerAlreadyExistsOrNotController',
												data:data,
													type:'post',
													success:function(result)
													{														
														
														if(result.match('1'))
															{
																$("#firstname1").hide();
																$("#lastname1").hide();
																$("#mobilenumber1").hide();
																$("#state1").hide();
																$("#city1").hide();
																$("#address11").hide();
																$("#address21").hide();
																$("#postalcode1").hide();
																$("#newpassword1").hide();
																$("#confirmpassword1").hide();
																$("#submit1").hide();
																$("#cancel1").hide();
																alert("you are already registered!!!!!");
															}
														else
															{
																$("#firstname1").show();
																$("#lastname1").show();
																$("#mobilenumber1").show();
																$("#state1").show();
																$("#city1").show();
																$("#address11").show();
																$("#address21").show();
																$("#postalcode1").show();
																$("#newpassword1").show();
																$("#confirmpassword1").show();
																$("#submit1").show();
																$("#cancel1").show();
															}
														
														
													}
														
										}
									 ); 
						});
					  });
</script>


</head>
<body>
<%
		String customeremail=(String)session.getAttribute("customeremail");
String customeremailwhencheckout=(String)session.getAttribute("customeremailwhencheckout");
	if(customeremail==null && customeremailwhencheckout==null)
		
		{
		
%>
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
${customerlogin}
	<form action="index.jsp" >
		<input type="submit" name="submit" value="back"/>
	</form>
	
					<center>
					<h4><font color="red">${adminloginfailed}</font></h4>
	<font color="green"><h1>Customer Login</h1>	</font>
							<form action="CustomerLoginController" method="post">
							<table>
								<tr>
									<td>Enter Email Id</td><td><input type="email" name="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="email id" required/></td>
								</tr>
								<tr>
									<td>Enter Password</td><td><input type="password" name="password" placeholder="password" required/></td>
								</tr>
								<tr>
									<td><input type="submit" name="submit" value="submit"/></td>
								</tr>
							</table>
							</form>
					</center>


			<center>
			<h1><font color="red">${newcustomernotinserted}</font></h1>
			<form action="SignUpCustomerController" method="post">
				<h1><font color="green">New Customer</font></h1>
				<table>
					
					<tr id="email1"><td>Email</td><td><input type="email" name="email" id="email" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" placeholder="must be unique" required/></td></tr>
					<tr id="firstname1"><td>First Name</td><td><input type="text" name="firstname" id="firstname" pattern="[A-Za-z]+" title="only alphabet" required/></td></tr>
					<tr id="lastname1"><td>Last Name</td><td><input type="text" name="lastname" id="lastname" pattern="[A-Za-z]+" title="only alphabet" required/></td></tr>
					<tr id="mobilenumber1"><td>Mobile Number</td><td><input type="text" name="mobilenumber" id="mobilenumber" pattern="[7-9]{1}[0-9]{9}" maxlength="10" title="Please only use numeric" placeholder="Enter 10 digits only" required/></td></tr>
						<!-- <tr><td>City</td><td><input type="text" name="city" pattern="[A-Za-z]+" title="only alphabet" placeholder="only alphabets without space" required/></td></tr> -->
					<!-- <tr><td>State</td><td><input type="text" name="state" pattern="[A-Za-z]+" title="only alphabet" placeholder="only alphabets without space" required/></td></tr>   -->
					
					<tr id="state1">
						<td>State </td>
						<td>					
							<div id="selection">
						        <select id="listBox" onchange='selct_district(this.value)' name="state"></select>
						        
						      </div>
						</td>
					</tr>
					<tr id="city1">
						<td>City</td>
						<td>
							<select id='secondlist' name="city"></select>
							<div id="dumdiv" align="center" style=" font-size: 10px;color: #dadada;">
						         <a id="dum" style="padding-right:0px; text-decoration:none;color: green;text-align:center;" href="#"></a>
						      </div>
						</td>
					</tr>
					
					<!-- <tr id="address11"><td>Address1</td><td><input type="text" name="address1" id="address1" pattern="^\S+.*?\S+$" required/></td></tr>
					<tr id="address21"><td>Address2</td><td><input type="text" name="address2" id="address2" pattern="^\S+.*?\S+$" required/></td></tr> -->
					
					
					
					<tr id="address11">
					  <td>Address 1</td>
					  <td>
						<textarea rows="4" cols="30" name="address1" pattern="^\S+.*?\S+$" required>
						
						</textarea>
					  </td>
					</tr>
			
			<tr id="address21">
			  <td>Address 2</td>
			  <td>
				<textarea rows="4" cols="30" name="address2" pattern="^\S+.*?\S+$" required>
						
				</textarea>
			  </td>
			</tr>
					
					<tr id="postalcode1"><td>Zip/Postal Code</td><td><input type="text" name="postalcode" id="postalcode" pattern="[4-6]{1}[0-9]{5}" maxlength="6" title="Please use only numeric and first digit must be 4 or 5 or 6" placeholder="starts with 9/8/7 only" required/></td></tr>
				
					
					
					<tr id="newpassword1"><td>Password</td><td><input type="password" name="password1" id="password1" required/></td></tr>
					<tr id="confirmpassword1"><td>Confirm Password</td><td><input type="password" name="password2"  id="password2" required/></td></tr>
					 <tr ><td></td><td><input type="submit" name="submit1" id="submit1" value="save"/><input type="submit" name="cancel1" id="cancel1" value="cancel"/></td></tr>
				</table>	
				</form>
			</center>
	
		<%
}
		
		else if((customeremail!=null || customeremailwhencheckout==null))
			response.sendRedirect("customer_home.jsp");
		else if((customeremail==null || customeremailwhencheckout!=null))
			response.sendRedirect("customer_home.jsp");
	
	%>
</body>
</html>