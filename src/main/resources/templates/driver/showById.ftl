<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-BmbxuPwQa2lc/FVzBcNJ7UAyJxM6wuqIj61tLrc4wSX0szH/Ev+nYRRuWlolflfl" crossorigin="anonymous">
    <title>Show by Id</title>
    <style>
        input{
            color: black;
            background-color: white;
        }
    </style>
</head>
<body>
    <div style="width: 400px; margin: 0 auto; margin-top: 50px">
        <h1 style="margin-left: 35%">Driver</h1>
        <ul class="list-group">

            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">ID</span>
                <input type="text" readonly placeholder=${driver.getId()}  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Driver Name</span>
                <input type="text" readonly placeholder=${driver.getName()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Phone Number</span>
                <input type="text" readonly placeholder=${driver.getPhone()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Mark</span>
                <input type="text" readonly placeholder=${driver.getMark()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">License Number</span>
                <input type="text" readonly placeholder=${driver.getLicenseNumber()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>

            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Taxi Office</span>
                <input  type="text" readonly placeholder=${driver.getTaxiOffice().getName()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>

            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Created at</span>
                <input type="text" readonly placeholder=${driver.getCreateTime()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Updated at</span>
                <input type="text" readonly placeholder=${driver.getUpdateTime()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
        </ul>
        <a href="/ui/driver/get/all"><button type="button" class="btn btn-outline-dark">To the list</button></a>
        <a style="float: right" href="/ui/taxioffice/get/${driver.getTaxiOffice().getId()}"><button type="button" class="btn btn-outline-dark" >Taxi Office</button></a>
    </div>
</body>
</html>