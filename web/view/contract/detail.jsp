<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Room</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <link href="../css/styles.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="../homepage/header.jsp"></jsp:include>
            <div id="layoutSidenav">
            <jsp:include page="../homepage/left.jsp"></jsp:include>
                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <h2 class="mt-4">Detail</h2>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="../contract/list">Contract</a></li>
                                <li class="breadcrumb-item active">Detail</li>
                            </ol>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Contract Detail
                                </div>
                                <div class="card-body">
                                    <h5>${requestScope.contract.name}</h5>
                                <div class="row">
                                    <div class="col-md-6">
                                        <p style="text-align: center">Student</p>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Name</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.contract.student.name}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Gender</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${(requestScope.contract.student.gender)?"Male":"Female"}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>DOB</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.contract.student.dob}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Phone</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.contract.student.phonenumber}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Card Number</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.contract.student.cardnumber}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Address</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.contract.student.address}</p>
                                            </div>
                                        </div>

                                    </div>
                                    <div class="col-md-6">
                                        <p style="text-align: center">Room</p>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Name</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.contract.room.rname}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Type</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.contract.room.typeofroom.name}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>Price</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.contract.room.typeofroom.price}</p>
                                            </div>
                                        </div>
                                            <div class="row">
                                            <div class="col-md-6">
                                                <label>Deposit</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.contract.deposit}</p>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-md-6">
                                                <label>From</label>
                                            </div>
                                            <div class="col-md-6">
                                                <p>${requestScope.contract.from}</p>
                                            </div>
                                        </div>
                                    </div>
                                </div>


                            </div>
                        </div>
                    </div>
                </main>
                <jsp:include page="../homepage/footer.jsp"></jsp:include>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../js/scripts.js" type="text/javascript"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
