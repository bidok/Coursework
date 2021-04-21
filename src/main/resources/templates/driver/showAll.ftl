<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
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
    </style>
</head>
<body>

<div style="width: 850px; margin: 0 auto; margin-top: 50px ">
<table class="table">

    <tr class="table-dark">
        <th>ID</th>
        <th>Name</th>
        <th>Phone Number</th>
        <th>Mark</th>
        <th>License Number</th>
        <th>Taxi Office</th>
        <th></th>
        <th></th>
    </tr>
    <#list driver as drivers>
    <tr>
        <td><a href="/ui/driver/get/${drivers.id}">${drivers.id}</a></td>
        <td>${drivers.name}</td>
        <td>${drivers.phone}</td>
        <td>${drivers.mark}</td>
        <td>${drivers.licenseNumber}</td>
        <td><a href="/ui/taxioffice/get/${drivers.taxiOffice.id}">${drivers.taxiOffice.name}</a></td>
        <td><a href="/ui/driver/update/${drivers.id}"><button type="button" class="btn btn-outline-dark">Update</button></a></td>
        <td><a href="/ui/driver/delete/${drivers.id}"><button type="button" class="btn btn-outline-dark">Delete</button></a></td>
    </tr>
    </#list>
</table>
    <a href="/ui/driver/create"><button type="button" class="btn btn-outline-dark">Create</button></a>
</div>
</body>
</html>