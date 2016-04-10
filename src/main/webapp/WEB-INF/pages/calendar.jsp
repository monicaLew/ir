<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>calendar-io</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css">
    <!--script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script-->
    <style type="text/css">
        h1, h2 {
            text-align: center;
        }
        h1 {
            font-size: 54px;
        }
        td, th {
            text-align: right;
        }
        .day {
            min-height: 24px;
            overflow: auto;
        }
        th {
            background-color: lavender;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <a href="${pageContext.request.contextPath}/home">На главную страницу</a>

    <h1>${year}</h1>

    <div class="row">
        <c:forEach var="month" begin="0" end="11">
            <div class="col-md-4 col-sm-6 col-xs-12">
                <h2>${monthNames[month]}</h2>
                <table class="table table-striped">
                    <thead>
                    <tr><c:forEach var="x" begin="0" end="6"><th>${dowNames[x]}</th></c:forEach></tr>
                    </thead>
                    <tbody>
                    <c:forEach var="y" begin="0" end="5">
                        <tr><c:forEach var="x" begin="0" end="6"><td><div class="day"><c:if test="${calendar[month][y][x] != 0}">${calendar[month][y][x]}</c:if></div></td></c:forEach></tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
