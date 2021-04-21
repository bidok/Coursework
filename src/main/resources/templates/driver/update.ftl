<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">    <meta charset="UTF-8">
    <title>Update Driver</title>
</head>
<body>
<div style="width: 500px ;margin: 0 auto; margin-top: 50px">
    <form name="taxiOffice" action="" method="POST">

        <div class="input-group input-group-sm mb-3">
            <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Name</span>
            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
            <@spring.formInput "driverForm.name" "" "text"/>
        </div>
        <div class="input-group input-group-sm mb-3">
            <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Phone Number</span>
            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
            <@spring.formInput "driverForm.phone" "" "text"/>
        </div>
        <div class="input-group input-group-sm mb-3">
            <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Mark</span>
            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
            <@spring.formInput "driverForm.mark" "" "text"/>
        </div>
        <div class="input-group input-group-sm mb-3">
            <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">License Number</span>
            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
            <@spring.formInput "driverForm.licenseNumber" "" "text"/>
        </div>

        <div class="input-group mb-3">
            <label class="input-group-text" style="width: 120px" for="inputGroupSelect01">Options</label>
            <select class="form-select" id="inputGroupSelect01"
             <@spring.formSingleSelect "driverForm.taxiOffice", taxiOffices, ""/>
        </div>


        <button type="submit" class="btn btn-outline-dark" style="margin-left: 45%">Update</button>
    </form>
</div>
</body>
</html>