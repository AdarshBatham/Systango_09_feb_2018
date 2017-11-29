<!DOCTYPE HTML>
<html>
 <link rel="shortcut icon" type="image/x-icon" href="images/zappyimage.ico" />
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-compatible" content="IE-edge">
	<meta name="viewport" content="width=device-width">
<title> Mypage</title>

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


<body
background-color:#ece5cd;
>
			
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
			
			
			
			
			
			
			
			
			
			
			
			<%
				String customeremail=(String)session.getAttribute("customeremail");
				String customeremailwhencheckout=(String)session.getAttribute("customeremailwhencheckout");
				String adminid=(String)session.getAttribute("adminid");
				
				if(customeremail!=null || customeremailwhencheckout!=null)
				{
			%>
			
			<li ><a href="customer_login.jsp">Customer</a></li>
			<li ><a href="ViewAllProductsController">Products</a></li>
			
			<%
				}
				else if(adminid!=null)
				{
					%>
					<li ><a href="adminlogin.jsp">Admin</a></li>		
					<li ><a href="ViewAllProductsController">Products</a></li>
					<%
				}
				else
				{
					%>
					<li ><a href="adminlogin.jsp">Admin</a></li>
					<li ><a href="customer_login.jsp">Customer</a></li>
					<li ><a href="ViewAllProductsController">Products</a></li>
					<li ><a href="search_your_willing_product.jsp">Search your willing product</a></li>
					<%
				}
			%>
		
			
			</ul>
			</div>
			</nav>
			
			
			</div>
			</div>
			</div>
			<!--  <div class="container">
    <div class="row">
        <div class="col-md-offset-5 col-md-3">
            <div class="form-login">
            <h4>Welcome back.</h4>
            <input type="text" id="userName" class="form-control input-sm chat-input" placeholder="username" />
            </br>
            <input type="text" id="userPassword" class="form-control input-sm chat-input" placeholder="password" />
            </br>
            <div class="wrapper">
            <span class="group-btn">     
                <a href="#" class="btn btn-primary btn-md">login <i class="fa fa-sign-in"></i></a>
            </span>
            </div>
            </div>
        
        </div>
    </div>
</div>
-->
			<div class="container">
    <div class="row">
    <div class="col-sm-12">
    <img src="images/foodpic.jpg"></img>
		
	</div>
	</div>
</div>
</body>
<script type="text/javascript" src="js/jquery.js"> </script>	
	<script type="text/javascript" src="js/bootstrap.js"></script>	
	







</body>
</html>