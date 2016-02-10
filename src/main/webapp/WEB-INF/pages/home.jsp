<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>calendar-io: home page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/main.css"/>
</head>
<body>
<p>
    This simple web application generates a calendar for required year.
</p>

<p>
    Just input the year you need (between 1800 and 2200),<br/>
    select first day of week,<br/>
    select output destination and layout type
</p>

<p>
    ${count} calendars were already generated!
</p>

<form action="${pageContext.request.contextPath}/calendar" method="get">
    <table class="calendarParams">
        <tr>
            <th class="calendarParams">Year</th>
            <td class="calendarParams"><input name="year" type="number" min="1800" max="2200" value="${year}" required/></td>
        </tr>
        <tr>
            <th class="calendarParams">
                First day<br/>
                of week
            </th>
            <td class="calendarParams">
                <c:forEach var="dow" begin="0" end="6">
                    <input name="firstDOW" type="radio" value="${dow + 1}"
                           <c:if test="${0 == dow}">checked</c:if>/>${dowNames[dow]}<br/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <th class="calendarParams">
                Output<br/>
                destination
            </th>
            <td class="calendarParams">
                <input name="mediaType" type="radio" value="html" checked/>To HTML<br/>
                <input name="mediaType" type="radio" value="pdf"/>To PDF<br/>
            </td>
        </tr>
        <tr>
            <th class="calendarParams">
                Months<br/>
                layout
            </th>
            <td class="calendarParams">
                <input name="columnCount" type="radio" value="2"/>2 X 6<br/>
                <input name="columnCount" type="radio" value="3" checked/>3 X 4<br/>
                <input name="columnCount" type="radio" value="4"/>4 X 3<br/>
                <input name="columnCount" type="radio" value="6"/>6 X 2<br/>
            </td>
        </tr>
        <tr>
            <th class="calendarParams" colspan="2">
                <input type="submit" value="Generate"/>
            </th>
        </tr>
    </table>
</form>

</body>
</html>
