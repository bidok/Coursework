<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">    <meta charset="UTF-8">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    <title>Update Driver</title>
    <style>
        body{
            background-color: #eda501;

            background-repeat: no-repeat;
            background-size: cover;

        }
    </style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="/">Home</a>
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
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        Requests
                    </a>
                    <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
                        <li><a class="dropdown-item" href="/requests/1/">request 1</a></li>
                        <li><a class="dropdown-item" href="/requests/2">request 2</a></li>
                        <li><a class="dropdown-item" href="/requests/3">request 3</a></li>
                        <li><a class="dropdown-item" href="/requests/4/">request 4</a></li>
                        <li><a class="dropdown-item" href="/requests/5//">request 5</a></li>
                        <li><a class="dropdown-item" href="/requests/6/">request 6</a></li>
                        <li><a class="dropdown-item" href="/requests/7///">request 7</a></li>
                        <li><a class="dropdown-item" href="/requests/8//">request 8</a></li>
                        <li><a class="dropdown-item" href="/requests/9/">request 9</a></li>
                        <li><a class="dropdown-item" href="/requests/10">request 10</a></li>
                        <li><a class="dropdown-item" href="/requests/11//">request 11</a></li>
                        <li><a class="dropdown-item" href="/requests/12/">request 12</a></li>
                        <li><a class="dropdown-item" href="/requests/13">request 13</a></li>
                        <li><a class="dropdown-item" href="/requests/14/">request 14</a></li>
                        <li><a class="dropdown-item" href="/requests/15">request 15</a></li>
                    </ul>
                </li>
            </ul>
        </div>
        <li class="nav-item dropdown" style="float: right">
            <a href="/logout"><button type="button" class="btn btn-dark">Log out</button></a>
        </li>
    </div>
</nav>
<div style="width: 500px ;margin: 0 auto; margin-top: 50px">
    <form name="taxiOffice" action="" method="POST">

        <div class="input-group input-group-sm mb-3">
            <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Salary</span>
            <input type="number" value="0" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
            <@spring.formInput "salaryForDayForm.salary" "" "text"/>
        </div>
        <div class="input-group input-group-sm mb-3">
            <label class="input-group-text" style="width: 120px" for="inputGroupSelect01">Discount Cards</label>
            <select class="form-select" id="inputGroupSelect01"
            <@spring.formSingleSelect "salaryForDayForm.entity", drivers, "text"/>
        </div>
        <button type="submit" class="btn btn-outline-dark" style="margin-left: 45%">Update</button>
    </form>
</div>
</body>
</html>