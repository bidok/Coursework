<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    <title>Show by Id</title>
    <style>
        input{
            color: black;
            background-color: white;
        }
        body{
            background-image: url("https://image.shutterstock.com/image-vector/urban-background-taxi-car-skyscrapers-260nw-100603252.jpg");

            background-repeat: no-repeat;
            background-size: cover;

        }
    </style>
</head>
<#assign isOut = order.getIsOutOfCity()>
<#assign compl = order.getCompleted()>


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
    <div style="width: 400px; margin: 0 auto;">
        <h1 style="margin-left: 35%">Car</h1>
        <ul class="list-group">

            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">ID</span>
                <input type="text" readonly placeholder=${order.getId()}  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">Order Number</span>
                <input type="text" readonly placeholder=${order.getOrderNumber()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">Start Address</span>
                <input type="text" readonly placeholder=${order.getStartAddress()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">End Address</span>
                <input type="text" readonly placeholder=${order.getEndAddress()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">is ou of city</span>
                <input type="text" readonly placeholder=<#if isOut == true>Yes</#if><#if isOut != true>No</#if>
                                                         class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">Customer</span>
                <input type="text" readonly placeholder=${order.getCustomer().getName()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">operator</span>
                <input type="text" readonly placeholder=${order.getOperator().getName()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>

            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">Car</span>
                <input  type="text" readonly placeholder=${order.getCar().getCarNumber()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>

            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">Driver</span>
                <input type="text" readonly placeholder=${order.getCar().getDriver().getName()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">is ou of city</span>
                <input type="text" readonly placeholder=<#if compl == true>Yes</#if><#if compl != true>No</#if>
                       class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
<#--            <div class="input-group input-group-sm mb-3">-->
<#--                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">State</span>-->
<#--                <input type="text" readonly placeholder=${car.getState()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">-->
<#--            </div>-->
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">Created At</span>
                <input type="text" readonly placeholder=${order.getCreateTime()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 150px">Updated At</span>
                <input type="text" readonly placeholder=${order.getUpdateTime()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
        </ul>
        <a href="/ui/order/get/all"><button type="button" class="btn btn-outline-dark">To the list</button></a>
        <a style="float: right" href="/ui/customer/get/${order.getCustomer().getId()}"><button type="button" class="btn btn-outline-dark" >Customer</button></a>
        <a style="float: right" href="/ui/driver/get/${order.getDriver().getId()}"><button type="button" class="btn btn-outline-dark" >Driver</button></a>
        <a style="float: right" href="/ui/operator/get/${order.getOperator().getId()}"><button type="button" class="btn btn-outline-dark" >operator</button></a>
        <a style="float: right" href="/ui/car/get/${order.getCar().getId()}"><button type="button" class="btn btn-outline-dark" >Car</button></a>
    </div>
</body>
</html>