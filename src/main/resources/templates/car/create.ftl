<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
    <title>Create Car</title>
    <style>
        body{
            background-image: url("https://image.shutterstock.com/image-vector/urban-background-taxi-car-skyscrapers-260nw-100603252.jpg");

            background-repeat: no-repeat;
            background-size: cover;
        }
        .changeBitton{
            background-color: lightgray;
            border: none;
            border-right: 1px lightgrey solid;
            border-radius: 2px;
        }
        .changeBitton:hover{
            background: none;
            border: 1px lightgray solid;
        }
        a{
            color: black;
            text-decoration: none;
        }
        a:hover{
            color: black;
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
    <div style="width: 500px;margin: 0 auto; margin-top: 50px">
        <form name="taxiOffice" action="" method="POST">
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Car Number</span>
                <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
                <@spring.formInput "carForm.carNumber" "" "text"/>
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Location</span>
                <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
                <@spring.formInput "carForm.location" "" "text"/>
            </div>

            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Insurance Number</span>
                <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
                <@spring.formInput "carForm.insuranceNumber" "" "text"/>
            </div>

            <div class="input-group input-group-sm mb-3">
                <label class="input-group-text" style="width: 120px" for="inputGroupSelect01">State</label>
                <select class="form-select" id="inputGroupSelect01"
                <@spring.formSingleSelect "carForm.state", state, ""/>
            </div>

            <div class="input-group input-group-sm mb-3">
                <label class="input-group-text" style="width: 120px" for="inputGroupSelect01">Model</label>
                <select class="form-select" id="inputGroupSelect01"
                <@spring.formSingleSelect "carForm.modell", models, ""/>
            </div>
            <div class="input-group mb-3" style="height: 31px">
                <button class="changeBitton" type="button" onclick=test() style="width: 120px"><a id="taxiOfficeFilter" href="#" >set office</a></button>
                <select class="form-select testclass" id="inputGroupSelect03" aria-label="Example select with button addon"
                <@spring.formSingleSelect "carForm.taxiOffice", taxiOffice, "text"/>
                </select>
            </div>


            <script type="text/javascript">
                function test() {
                    let el = document.getElementById("taxiOfficeFilter");
                    let val = document.getElementsByClassName("testclass")[0];
                    el.setAttribute("href", "/ui/car/create?taxiOffice=" + val.value)
                    console.log("sad")
                }

            </script>

            <div class="input-group input-group-sm mb-3">
                <label class="input-group-text" style="width: 120px" for="inputGroupSelect01">Driver</label>
                <select class="form-select" id="inputGroupSelect01"
                <@spring.formSingleSelect "carForm.driver", drivers, "text"/>
            </div>





            <button type="submit" class="btn btn-outline-dark" style="margin-left: 45%">Create</button>
        </form>

    </div>
<sript>

</sript>
</body>
</html>