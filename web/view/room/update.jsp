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
        <title>Tables</title>
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
                                <li class="breadcrumb-item"><a href="../room/list">Room</a></li>
                                <li class="breadcrumb-item active">Insert Room</li>
                            </ol>
                            <div class="card mb-4">
                                <div class="card-header">
                                    <i class="fas fa-table me-1"></i>
                                    Room Table Insert
                                </div>
                                <div class="card-body">
                                    <form action="../room/update" method="post" >
                                        <table class="table table-bordered">
                                            <thead>
                                                <tr>

                                                    <th>Type</th>
                                                    <th>Name</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td>
                                                    <c:forEach items="${requestScope.types}" var="t">
                                                        <input required name="type" ${(requestScope.r.typeofroom.id==t.id)?"checked=\"checked\"":""} type="radio" value="${t.id}">${t.name}<br>
                                                    </c:forEach>
                                                </td>
                                                <td>
                                                    <input type="hidden" name="rid" value="${requestScope.r.rid}" >
                                                    <input type="text" name="rname" value="${requestScope.r.rname}" required>
                                                </td>

                                            </tr>
                                        </tbody>
                                    </table>
                                    <div style="text-align: center">
                                        <a type="button" class="btn btn-default" href="../room/list">Back</a>
                                        <button type="submit" class="btn btn-primary">Update</button>
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
