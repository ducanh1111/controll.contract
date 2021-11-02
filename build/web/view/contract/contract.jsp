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
        <title>Contract</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/js/all.min.js" crossorigin="anonymous"></script>
        <link href="../css/styles.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <script src="../js/scripts.js" type="text/javascript"></script>

    </head>
    <body class="sb-nav-fixed">
        <jsp:include page="../homepage/header.jsp"></jsp:include>
            <div id="layoutSidenav">
            <jsp:include page="../homepage/left.jsp"></jsp:include>
                <div id="layoutSidenav_content">
                    <main>
                        <div class="container-fluid px-4">
                            <h2 class="mt-4">Contract</h2>
                            <ol class="breadcrumb mb-4">
                                <li class="breadcrumb-item"><a href="../controller/home">Dashboard</a></li>
                                <li class="breadcrumb-item active">Contract</li>
                            </ol>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Contract Table
                                    <!-- Navbar Search-->
                                    <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" style="float: right">
                                        From<input type="date" name="from">

                                        To<input type="date" name="to">

                                        <div class="input-group" style=" float: right; width: 40%">
                                            <input name="sname" value="" class="form-control" type="text" placeholder="Search by Student" aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                                            <button  class="btn btn-primary" id="btnNavbarSearch" type="submit"><i class="fas fa-search"></i></button>
                                            <a type="button" class="btn btn-info" href="../contract/insert"><i class="fa fa-plus"></i>CREATE</a>
                                        </div>
                                    </form>
                                </div>
                                <div class="card-body">
                                    <table class="table table-bordered">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Student</th>
                                                <th>Phone</th>
                                                <th>Deposit</th>
                                                <th>From</th>
                                                <th></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach items="${requestScope.contracts}" var="c">
                                            <tr>
                                                <td>${c.name}</td>
                                                <td>${c.student.name}</td>
                                                <td>${c.student.phonenumber}</td>
                                                <td>${c.deposit}</td>
                                                <td>${c.from}</td>
                                                <td>
                                                    <a href="../contract/detail?cid=${c.id}" class="btn detail" title="Detail" ><i class="material-icons" style="color: blue;">&#xe895;</i></a>
                                                </td>
                                            </tr>
                                        </c:forEach>   
                                    </tbody>
                                </table>
                                <div class="pagger" id="pagebottom"></div>
                                <script>
                                    createPagger('pagebottom', ${requestScope.pageindex}, 2,${requestScope.end});
                                </script>
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
