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
                            <h2 class="mt-4">Insert</h2>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="../contract/list">Contract</a></li>
                                <li class="breadcrumb-item active">Insert</li>
                            </ol>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Create New Contract
                                </div>
                                <div class="card-body">
                                    <form action="../contract/insert" method="post">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <p style="text-align: center">Room</p>
                                                <div class="row">
                                                    <div class="col-md-6">
                                                        <label>Name</label>
                                                    </div>
                                                    <div class="col-md-6">
                                                        <p>${requestScope.room.rname}</p>
                                                    <input type="hidden" name="rid" value="${requestScope.room.rid}">
                                                    <input type="hidden" name="rname" value="${requestScope.room.rname}">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Type</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>${requestScope.room.typeofroom.name}</p>
                                                    <input type="hidden" name="type" value="${requestScope.room.typeofroom.name}">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Price</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <p>${requestScope.room.typeofroom.price}</p>
                                                    <input type="hidden" name="price" value="${requestScope.room.typeofroom.price}">
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Deposit</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <input style="width: 171px" type="number" name="deposit" required>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Date</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <input type="date" name="date" required>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-7">
                                            <p style="text-align: center">Student</p>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Name</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <input type="text" name="sname" required >
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Gender</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <input type="radio" name="gender" checked="checked" value="male">Male
                                                    <input type="radio" name="gender"  value="female">Female
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>DOB</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <input type="date" name="dob" required >
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Phone</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <input type="number" name="phonenumber" required >
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Card Number</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <input type="number" name="cardnumber" required >
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <label>Address</label>
                                                </div>
                                                <div class="col-md-6">
                                                    <input type="text" name="address" required >
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div style="text-align: center;margin-top: 30px;">
                                        <input type="submit" class="btn-primary" value="Save">
                                    </div>
                                </form>
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
