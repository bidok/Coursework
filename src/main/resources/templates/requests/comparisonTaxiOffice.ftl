<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
	<title>All taxi offices</title>
	<style>
        a{
            text-decoration: none;
            color: black;
        }
        a:hover{
            text-decoration: underline;
            color: black;
        }
        body{
            background-color: #eda501;
            background-size: 100%;
            background-repeat: no-repeat;


        }
	</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="#">Navbar</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDarkDropdown" aria-controls="navbarNavDarkDropdown" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavDarkDropdown">
			<ul class="navbar-nav">
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Employers
					</a>
					<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
						<li><a class="dropdown-item" href="/ui/taxioffice/get/all">Taxi Office</a></li>

					</ul>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Workers
					</a>
					<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
						<li><a class="dropdown-item" href="/ui/driver/get/all">Drivers</a></li>
						<li><a class="dropdown-item" href="/ui/operator/get/all">Operators</a></li>

					</ul>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Cars
					</a>
					<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
						<li><a class="dropdown-item" href="/ui/car/get/all">Cars</a></li>
						<li><a class="dropdown-item" href="/ui/model/get/all">Models</a></li>

					</ul>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Salarys for day
					</a>
					<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
						<li><a class="dropdown-item" href="/ui/salary/forday/taxioffice/get/all">Taxi Office</a></li>
						<li><a class="dropdown-item" href="/ui/salary/forday/driver/get/all">Driver</a></li>
						<li><a class="dropdown-item" href="/ui/salary/forday/dispatchservice/get/all">Dispatch Service</a></li>
					</ul>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Salarys for interval
					</a>
					<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
						<li><a class="dropdown-item" href="/ui/salary/forinterval/taxioffice/get/all">Taxi Office</a></li>
						<li><a class="dropdown-item" href="/ui/salary/forinterval/driver/get/all">Driver</a></li>
						<li><a class="dropdown-item" href="/ui/salary/forinterval/dispatchservice/get/all">Dispatch Service</a></li>
					</ul>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Payments
					</a>
					<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
						<li><a class="dropdown-item" href="/ui/order/get/all">Orders</a></li>
						<li><a class="dropdown-item" href="/ui/check/get/all">Checks</a></li>
						<li><a class="dropdown-item" href="/ui/customer/get/all">Customer</a></li>
						<li><a class="dropdown-item" href="/ui/discountcard/get/all">Cards</a></li>

					</ul>
				</li>
				<li class="nav-item dropdown">
					<a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
						Time Table
					</a>
					<ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
						<li><a class="dropdown-item" href="/ui/timetable/driver/get/all">Driver</a></li>
						<li><a class="dropdown-item" href="/ui/timetable/operator/get/all">Operator</a></li>
					</ul>
				</li>

			</ul>
		</div>
	</div>
</nav>
<div style="width: 850px; margin: 0 auto; margin-top: 50px ">
	<table class="table">

		<tr class="table-dark">
			<th>TaxiOffice</th>
			<th>salary</th>
			<th>completed</th>
			<th>uncompleted</th>


		</tr>
		<#list statistic as stat>
		<tr>
			<td>${stat.taxiOffice.name}</td>
			<td>${stat.earned}</td>
			<td>${stat.completedOrders}</td>
			<td>${stat.uncompletedOrders}</td>
		</tr>
	</#list>
	</table>
</div>
</body>
</html>