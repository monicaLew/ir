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
</head>

<body>

    <div class="container">

        <h1>Календарь</h1>

        <p>Это бесплатное приложение предназначено для создания календаря на заданный год.
        Просто введите год, выберите первый день недели и выполните команду "Создать".
        Вывести календарь можно как в окно браузера, так и в PDF и напечатать.</p>

        <p>При помощи этого приложения уже создано календарей: <i>${count}</i></p>

        <h1>Выберите параметры</h1>

        <form class="form-horizontal" role="form" method="get" action="${pageContext.request.contextPath}/calendar" name="calendar-params">

            <div class="form-group">
                <label class="col-sm-3 control-label" for="year">Год</label>
                <div class="col-sm-3">
                    <input class="form-control" id="year" name="year" type="number" value="${year}"/>
                </div>
            </div>

            <div class="alert alert-danger hidden" id="year-error"></div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="first-dow">Первый день недели</label>
                <div class="col-sm-3">
                    <div class="radio">
                        <label><input type="radio" name="first-dow" value="sun">Воскресение</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" id="first-dow" name="first-dow" value="mon">Понедельник</label>
                    </div>
                </div>
            </div>

            <div class="alert alert-danger hidden" id="first-dow-error"></div>

            <div class="form-group">
                <label class="control-label col-sm-3" for="media-type">Вывод в</label>
                <div class="col-sm-3">
                    <div class="radio">
                        <label><input type="radio" name="media-type" value="html">Браузер</label>
                    </div>
                    <div class="radio">
                        <label><input type="radio" id="media-type" name="media-type" value="pdf">PDF</label>
                    </div>
                </div>
            </div>

            <div class="alert alert-danger hidden" id="media-type-error"></div>

            <div class="form-group">
                <div class="col-sm-offset-3 col-sm-3">
                    <input class="btn btn-default" type="submit" value="Создать">
                </div>
            </div>

        </form>

        <p>Связаться с разработчиком: <a href="mailto:kirill.gaidai@gmail.com">kirill.gaidai@gmail.com</a></p>

        <p>Приложение разработано с использованием</p>

        <p>
            <a href="http://www.java.com">
                <img alt="Java" src="http://upload.wikimedia.org/wikipedia/ru/thumb/3/39/Java_logo.svg/43px-Java_logo.svg.png" style="height:50px;width:35px"/>
            </a>
            <a href="http://www.getbootstrap.com/">
                <img alt="Bootstrap" src="https://upload.wikimedia.org/wikipedia/commons/thumb/e/ea/Boostrap_logo.svg/80px-Boostrap_logo.svg.png" style="height:50px;width:50px"/>
            </a>
            <a href="http://www.projects.spring.io/spring-framework/">
                <img alt="Spring Framework" src="https://upload.wikimedia.org/wikipedia/ru/7/70/Spring_Framework.png" style="height:50px;width:100px"/>
            </a>
            <a href="http://www.itextpdf.com/">
                <img alt="iText" src="http://www.pdfa.org/wp-content/uploads/2015/03/iTEXT-logo-300.png" style="height:50px;width:100px"/>
            </a>
            <a href="http://www.postgresql.org/">
                <img alt="PostgreSQL" src="https://upload.wikimedia.org/wikipedia/commons/thumb/2/29/Postgresql_elephant.svg/78px-Postgresql_elephant.svg.png" style="height:50px;width:50px">
            </a>
            <a href="http://www.heroku.com">
                <img alt="Heroku" src="https://upload.wikimedia.org/wikipedia/en/a/a9/Heroku_logo.png" style="height:50px;width:150px"/>
            </a>
        </p>

    </div>

    <script src="https://code.jquery.com/jquery-2.2.4.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script src="/resources/home.js"></script>

</body>
</html>