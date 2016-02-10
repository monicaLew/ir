<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>calendar-io: for ${year}</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/main.css"/>
</head>
<body>
<table class="year">
    <c:forEach var="month" begin="0" end="11" step="1">
        <c:if test="${0 == month % columnCount}">
            <tr>
        </c:if>
        <td class="year">
                ${monthNames[month]}<br/>
            <table class="month">
                <tr>
                    <c:forEach var="x" begin="0" end="6">
                        <th class="month">${dowNames[x]}</th>
                    </c:forEach>
                </tr>
                <c:forEach var="y" begin="0" end="5">
                    <tr>
                        <c:forEach var="x" begin="0" end="6">
                            <td class="month">
                                <c:if test="${0 != calendar[month][y][x]}">${calendar[month][y][x]}</c:if>
                            </td>
                        </c:forEach>
                    </tr>
                </c:forEach>
            </table>
        </td>
        <c:if test="${0 == (month + 1) % columnCount}">
            </tr>
        </c:if>
    </c:forEach>
</table>
</body>
</html>
