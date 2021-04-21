<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
    <title>Create Taxi Office</title>
</head>
<body>
    <div style="width: 500px;margin: 0 auto; margin-top: 50px">
        <form name="taxiOffice" action="" method="POST">
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Office Name</span>
                <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
                <@spring.formInput "taxiOfficeForm.name" "" "text"/>
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Phone Number</span>
                <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
                <@spring.formInput "taxiOfficeForm.phoneNumber" "" "text"/>
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Owner Name</span>
                <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
                <@spring.formInput "taxiOfficeForm.ownerName" "" "text"/>
            </div>
            <div class="input-group input-group-sm mb-3">
                <span class="input-group-text" id="inputGroup-sizing-sm" style="width: 120px">Licanse Number</span>
                <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"
                <@spring.formInput "taxiOfficeForm.licenseNumber" "" "text"/>
            </div>

<#--            <span class="input-group-text" id="inputGroup-sizing-sm">Name</span>-->
<#--            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"-->
<#--                    <@spring.formInput "taxiOfficeForm.name" "" "text"/>-->
<#--            <br>-->
<#--            <span class="input-group-text" id="inputGroup-sizing-sm">Phone Number</span>-->
<#--            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"-->
<#--                    <@spring.formInput "taxiOfficeForm.phoneNumber" "" "text"/>-->
<#--            <br>-->
<#--            <span class="input-group-text" id="inputGroup-sizing-sm">Owner Name</span>-->
<#--            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"-->
<#--                     <@spring.formInput "taxiOfficeForm.ownerName" "" "text"/>-->
<#--            <br>-->
<#--            <span class="input-group-text" id="inputGroup-sizing-sm">License Number</span>-->
<#--            <input type="text" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-sm"-->
<#--                    <@spring.formInput "taxiOfficeForm.licenseNumber" "" "text"/>-->

<#--            <br>-->
<#--            <input type="submit" value="Create"/>-->
            <button type="submit" class="btn btn-outline-dark" style="margin-left: 45%">Create</button>
        </form>

    </div>
</body>
</html>