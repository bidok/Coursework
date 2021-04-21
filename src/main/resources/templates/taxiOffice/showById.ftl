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
        <ul class="list-group">
            <h1 style="margin-left: 30%">Taxi Office</h1>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">ID</span>
                <input type="text" readonly placeholder=${taxiOffice.getId()}  class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Office Name</span>
                <input type="text" readonly placeholder=${taxiOffice.getName()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Phone Number</span>
                <input type="text" readonly placeholder=${taxiOffice.getPhoneNumber()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Owner Name</span>
                <input type="text" readonly placeholder=${taxiOffice.getOwnerName()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">License Number</span>
                <input type="text" readonly placeholder=${taxiOffice.getLicenseNumber()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Created at</span>
                <input type="text" readonly placeholder=${taxiOffice.getCreateTime()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Updated at</span>
                <input type="text" readonly placeholder=${taxiOffice.getUpdateTime()} class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm">
            </div>
        </ul>
        <a href="/ui/taxioffice/get/all"><button type="button" class="btn btn-outline-dark">To the list</button></a>
        <a href="#" onclick="history.back()" style="float: right" ><button type="button" class="btn btn-outline-dark">Back</button></a>
    </div>
</body>
</html>