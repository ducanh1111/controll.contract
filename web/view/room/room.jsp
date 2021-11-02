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
                            <h2 class="mt-4">Room</h2>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="index.jsp">Dashboard</a></li>
                                <li class="breadcrumb-item active">Room</li>
                            </ol>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Room Table
                                    <!-- Navbar Search-->
                                    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" style="float: right">
                                        <label style="float: left; width:20% ">Condition
                                            <select class="form-control" name="condition">
                                                <option ${(requestScope.condition eq "all")?"selected=\"selected\"":""} value="all">All</option>
                                            <option ${(requestScope.condition eq "availiable")?"selected=\"selected\"":""} value="availiable">Trong</option>
                                            <option ${(requestScope.condition eq "unavaliable")?"selected=\"selected\"":""} value="unavaliable">Da thue</option>
                                        </select></label>
                                    <label style="float: left; width:30% ">TypeOfRoom
                                        <select class="form-control" name="type">
                                            <option ${(requestScope.type eq "all")?"selected=\"selected\"":""} value="all">All</option>
                                            <option ${(requestScope.type eq "phongdon")?"selected=\"selected\"":""} value="phongdon">Phong Don</option>
                                            <option ${(requestScope.type eq "phongdoi")?"selected=\"selected\"":""} value="phongdoi">Phong Doi</option>
                                            <option ${(requestScope.type eq "phongba")?"selected=\"selected\"":""} value="phongba">Phong Ba</option>
                                        </select></label>
                                    <label style="float: left; width:20% ">Price
                                        <select  class="form-control" name="price">
                                            <option  ${(requestScope.price eq "-1")?"selected=\"selected\"":""} value="-1">All</option>
                                            <option ${(requestScope.price eq "1000000")?"selected=\"selected\"":""} value="1000000"><= 1.000.000</option>
                                            <option ${(requestScope.price eq "1500000")?"selected=\"selected\"":""} value="1500000"><= 1.500.000</option>
                                            <option ${(requestScope.price eq "2000000")?"selected=\"selected\"":""} value="2000000"><= 2.000.000</option>
                                        </select></label> 
                                    <div class="input-group" style=" float: right; width: 30%;margin-top: 24px;">
                                        <button  class="btn btn-primary" id="btnNavbarSearch" type="submit"><i class="fas fa-search"></i></button>
                                        <a type="button" class="btn btn-info" href="../room/insert"><i class="fa fa-plus"></i>ADD</a>
                                    </div>
                                </form>
                            </div>
                            <div class="card-body">
                                <table class="table table-bordered">
                                    <thead>
                                        <tr>
                                            <th>Name</th>
                                            <th>Condition</th>
                                            <th>Type</th>
                                            <th>Price</th>
                                            <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${requestScope.rooms}" var="r">
                                            <tr>
                                                <td>${r.rname}</td>
                                                <td>${(r.condition)?"Trong":"Da Thue"}</td>
                                                <td>${r.typeofroom.name}</td>
                                                <td>${r.typeofroom.price}</td>
                                                <td>
                                                    <a href="../room/update?rid=${r.rid}" class="btn edit" title="Edit" ><i class="material-icons" style="color: yellow;">&#xE254;</i></a>
                                                    <c:if test="${r.condition}">
                                                        <a href="../contract/insert?rid=${r.rid}" class="btn delete" title="Demise" ><i class="material-icons" style="color: sandybrown;">&#xe0e2;</i></a>
                                                    </c:if>
                                                        <c:if test="${!(r.condition)}">
                                                        <a href="../contract/delete" class="btn delete" title="Delete" ><i class="material-icons" style="color: red;">&#xe872;</i></a>
                                                    </c:if>
                                                </td>

                                            </tr>
                                        </c:forEach>   
                                    </tbody>
                                </table>
                                <c:forEach begin="1" end="${requestScope.end}" var="i">
                                    <a href="../room/list?pageindex=${i}&type=${requestScope.type}&condition=${requestScope.condition}&price=${requestScope.price}">${i}</a>
                                </c:forEach>
                            </div>

                        </div>
                    </div>
                </main>
                <jsp:include page="../homepage/footer.jsp"></jsp:include>
            </div>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
            <script src="../js/scripts.js" type="text/javascript"></script>
            <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
            <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
