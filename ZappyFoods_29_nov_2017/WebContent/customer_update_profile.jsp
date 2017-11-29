
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="beans.Customer_Cart" %>
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

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>

<script type="text/javascript">

$(document).ready(function()
		{
		$("#printmsg").hide();
		$("#printmobile").hide();
		
		$(document).ready(function()
				{
			$("#printmsg").hide();
			$("#printmobile").hide();
					$("#email").blur(function(){
		/* 				alert("hiiiiii"); */
					var email="email="+$("#email").val()+"&id="+$("#id").val();
						
							
							$.ajax({
								url:'CheckEmailIfExistController',
								data:email,
								type:'post',
								success:function(result)
								{
									
									if(result.match('1'))
										{
										
										$("#printmsg").show();
										 $("#btnSubmit").prop('disabled', true);

										}
									else
										{
										$("#printmsg").hide();
										 $("#btnSubmit").prop('disabled', false);

										}
								}
								
							})
					});
				
				//Ajax for mobile if exist check-------//	
					$("#mobilenumber").blur(function(){
						
						var mobile="mobilenumber="+$("#mobilenumber").val();
								
								
								$.ajax({
									url:'checkemobileifexist',
									data:mobile,
									type:'post',
									success:function(result)
									{
										if(result.match(1))
										{
										
										$("#printmobile").show();
										 $("#btnSubmit").prop('disabled', true);

										}
									else
										{
										$("#printmobile").hide();
										}
									}
									
								})
						});
					
					
				}
				
				
				
				);
		
		
		});

</script>
</head>
<body>
<%
String customeremail=(String)session.getAttribute("customeremail");
if(customeremail!=null)
{
%>
<center>
	<h1><font color="red">${customer_details_not_found}</font></h1>
	
</center>
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
<form action="customer_home.jsp">
		<input type="submit" name="submit" value="back"/>
	</form>

	<%
		Customer_Cart cc=(Customer_Cart)request.getAttribute("customer_details_for_updatation");
	 %>
	 <center>
	<form action="CustomerGivenDetailsUpdateNowController" method="post">
		<h1><font color="orange">Update Details of Customer</font></h1>
		<table>
			<tr><td>Id</td><td><input type="text" name="id" id="id" value="<%=cc.getId()%>" readonly required/></td></tr>
			<tr><td>Email</td><td><input type="email" name="email" id="email" value="<%=cc.getEmail()%>" pattern="[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$" required/></td></tr>
			<tr id="printmsg"><td><input type="text" placeholder="Email id already exists!!!!" readonly/></td></tr>
			<tr><td>First Name</td><td><input type="text" name="firstname" value="<%=cc.getFirstname() %>" required/></td></tr>
			<tr><td>Last Name</td><td><input type="text" name="lastname" value="<%=cc.getLastname() %>" required/></td></tr>
			<!-- <tr><td>Address1</td><td><input type="text" name="address1" value="<%=cc.getAddress1() %>" required/></td></tr> -->
			
			<tr>
			  <td>Address 1</td>
			  <td>
				<textarea rows="4" cols="30" name="address1" value="<%=cc.getAddress1() %>" required>
						<%=cc.getAddress1() %>
				</textarea>
			  </td>
			</tr>
			
			<tr>
			  <td>Address 2</td>
			  <td>
				<textarea rows="4" cols="30" name="address2" value="<%=cc.getAddress2() %>" required>
						<%=cc.getAddress2() %>
				</textarea>
			  </td>
			</tr>
			
			<!-- <tr><td>Address2</td><td><input type="text" name="address2" value="<%=cc.getAddress2() %>"/></td></tr>-->
			<tr><td>Zip/Postal Code</td><td><input type="text" name="postalcode" value="<%=cc.getPostalcode() %>" required/></td></tr>
		<%-- 	<tr><td>City</td><td><input type="text" name="city" value="<%=cc.getCity() %>" required/></td></tr>
			<tr><td>State</td><td><input type="text" name="state" value="<%=cc.getState() %>" required/></td></tr> --%>
			<tr><td>Old State</td><td><input type="text" name="oldstate" value="<%=cc.getState() %>" readonly required/></td></tr>
			<tr>
						<td>Choose New State </td>
						<td>					
							<div id="selection">
						        <select id="listBox" onchange='selct_district(this.value)' name="state">
						        	<option value="<%=cc.getState() %>" selected="selected"><%=cc.getState() %></option> 
						        </select>
						        
						      </div>
						</td>
					</tr>
					<tr>
						<td>City</td>
						<td>
							<select id='secondlist' name="city">
								<option value="<%=cc.getCity() %>"><%=cc.getCity() %></option> 
							</select>
							<div id="dumdiv" align="center" style=" font-size: 10px;color: #dadada;">
						         <a id="dum" style="padding-right:0px; text-decoration:none;color: green;text-align:center;" href="#"></a>
						      </div>
						</td>
					</tr>
			
			
			
			<tr><td>Mobile Number</td><td><input type="text" name="mobilenumber" id="mobilenumber" value="<%=cc.getMobilenumber() %>" required/></td></tr>
			<tr id="printmobile"><td><input type="text" placeholder="Mobile number already exists!!!!" readonly/></td></tr>
			 <tr><td>Password</td><td><input type="text" name="password" value="<%=cc.getPassword() %>" required/></td></tr>
			 <tr><td></td><td><input type="submit" name="submit" id="btnSubmit" value="update now"/><!-- <input type="reset" name="reset" value="reset"/> --></td></tr>
		</table>	
		</form>
	</center>
	<%
}
else
response.sendRedirect("customer_login.jsp");
	%>
</body>
</html>