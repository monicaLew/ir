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
</head>
<body>
<div class="container">

    <h1>Календарь</h1>

    <p>
        Это бесплатное приложение предназначено для создания календаря на заданный год.
        Просто введите год, выберите первый день недели и выполните команду "Создать".
        Вывести календарь можно как в окно браузера, так и в PDF и напечатать.
    </p>

    <p>
        При помощи этого приложения уже создано календарей: <i>${count}</i>
    </p>

    <hr/>

    <h1>Выберите параметры</h1>

    <form class="form-horizontal" role="form" method="get" action="${pageContext.request.contextPath}/calendar">

        <div class="form-group">
            <label class="control-label col-sm-3" for="year">Год</label>

            <div class="col-sm-3">
                <input class="form-control" id="year" name="year" type="number" min="1800" max="2200" value="${year}"
                       required/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-3" for="firstDOW">Первый день недели</label>

            <div class="col-sm-3">
                <select class="form-control" id="firstDOW" name="firstDOW">
                    <option value="sun">Воскресение</option>
                    <option value="mon" selected>Понедельник</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-3">Вывод в</label>

            <div class="col-sm-3">
                <div class="radio">
                    <label><input type="radio" name="mediaType" value="html" checked>Браузер</label>
                </div>
                <div class="radio">
                    <label><input type="radio" name="mediaType" value="pdf">PDF</label>
                </div>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-3">
                <input class="btn btn-default" type="submit" value="Создать">
            </div>
        </div>

    </form>

    <hr/>

    <p>
        Связаться с разработчиком: kirill.gaidai@gmail.com
    </p>

    <hr/>

    <p>
        При разработке приложения были использованы
    </p>

    <p>
        <a href="http://www.java.com">
            <img alt="Java"
                 src="http://upload.wikimedia.org/wikipedia/ru/thumb/3/39/Java_logo.svg/43px-Java_logo.svg.png"
                 style="height:50px;width:35px"/>
        </a>
        <a href="http://www.getbootstrap.com/">
            <img alt="Bootstrap"
                 src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Boostrap_logo.svg/80px-Boostrap_logo.svg.png"
                 style="height:50px;width:50px"/>
        </a>
        <a href="http://www.projects.spring.io/spring-framework/">
            <img alt="Spring Framework" src="https://upload.wikimedia.org/wikipedia/ru/7/70/Spring_Framework.png"
                 style="height:50px;width:100px"/>
        </a>
        <a href="http://www.itextpdf.com/">
            <img alt="iText" src="http://www.pdfa.org/wp-content/uploads/2015/03/iTEXT-logo-300.png"
                 style="height:50px;width:100px"/>
        </a>
        <a href="http://www.postgresql.org/">
            <img alt="PostgreSQL"
                 src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/78px-Postgresql_elephant.svg.png"
                 style="height:50px;width:50px">
        </a>
        <a href="http://www.heroku.com">
            <img alt="Heroku" src="https://upload.wikimedia.org/wikipedia/en/a/a9/Heroku_logo.png"
                 style="height:50px;width:150px"/>
        </a>
    </p>

</div>
</body>
</html>