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
        <title>Student</title>
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
                            <h2 class="mt-4">Student</h2>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="../controller/home">Dashboard</a></li>
                                <li class="breadcrumb-item active">Student</li>
                            </ol>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Student Table
                                    <!-- Navbar Search-->
                                    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" style="float: right">
                                        <label style="float: left; width:20% ">Gender
                                            <select class="form-control" name="gender">
                                                <option ${(requestScope.rawgender eq "all")?"selected=\"selected\"":""} value="all">All</option>
                                            <option ${(requestScope.rawgender eq "male")?"selected=\"selected\"":""} value="male">Male</option>
                                            <option ${(requestScope.rawgender eq "female")?"selected=\"selected\"":""} value="female">Female</option>
                                        </select></label>
                                    <div class="input-group" style=" float: right; width: 70%;margin-top: 24px;">
                                        <input name="sname" value="${requestScope.sname}" class="form-control" type="text" placeholder="Search by name" aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                                        <button  class="btn btn-primary" id="btnNavbarSearch" type="submit"><i class="fas fa-search"></i></button>
                                        <a type="button" class="btn btn-info" href="../student/insert"><i class="fa fa-plus"></i>ADD</a>
                                    </div>
                                </form>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Gender</th>
                                            <th>Dob</th>
                                            <th>PhoneNumber</th>
                                            <th>CardNumber</th>
                                            <th>Address</th>
                                            <th>Room</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.students}" var="s">
                                            <tr>
                                                <td>${s.name}</td>
                                                <td>${(s.gender)?"Male":"Female"}</td>
                                                <td>${s.dob}</td>
                                                <td>${s.phonenumber}</td>
                                                <td>${s.cardnumber}</td>
                                                <td>${s.address}</td>
                                                <td>${s.room.rname}</td>
                                                <td>
                                                    <a href="../student/update?sid=${s.id}" class="btn edit" title="Edit" ><i class="material-icons" style="color: yellow;">&#xE254;</i></a>
                                                    <a href="../student/delete?sid=${s.id}" class="btn delete" title="Delete" ><i class="material-icons" style="color: red;">&#xE872;</i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>   
                                    </tbody>
                                </table>
                                <c:forEach begin="1" end="${requestScope.end}" var="i">
                                    <a href="../student/list?index=${i}&gender=${requestScope.rawgender}&sname=${requestScope.sname}">${i}</a>
                                </c:forEach>
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
