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
        <th>Owner Name</th>
        <th>License Number</th>
        <th></th>
        <th></th>
    </tr>
    <#list taxiOffices as taxiOffice>
    <tr>
        <td><a href="/ui/taxioffice/get/${taxiOffice.id}">${taxiOffice.id}</a></td>
        <td>${taxiOffice.name}</td>
        <td>${taxiOffice.phoneNumber}</td>
        <td>${taxiOffice.ownerName}</td>
        <td>${taxiOffice.licenseNumber}</td>
        <td><a href="/ui/taxioffice/update/${taxiOffice.id}"><button type="button" class="btn btn-outline-dark">Update</button></a></td>
        <td><a href="/ui/taxioffice/delete/${taxiOffice.id}"><button type="button" class="btn btn-outline-dark">Delete</button></a></td>
    </tr>
    </#list>
</table>
    <a href="/ui/taxioffice/create"><button type="button" class="btn btn-outline-dark">Create</button></a>
</div>
</body>
</html>