"use strict";
(function () {

    var form = document.forms["calendar-params"];

    var yearInput = form.elements["year"];
    var yearError = document.getElementById("year-error");

    var firstDOWInput = form.elements["first-dow"];
    var firstDOWError = document.getElementById("first-dow-error");

    var mediaTypeInput = form.elements["media-type"];
    var mediaTypeError = document.getElementById("media-type-error");

    form.onsubmit = function () {
        var result = true;
        var year = +yearInput.value;

        if (isNaN(year)) {
            yearError.innerHTML = "<strong>Ошибка!</strong> Год должен быть числом";
            yearError.classList.remove("hidden");
            result = false;
        } else if (year < 1800 || year > 2200) {
            yearError.innerHTML = "<strong>Ошибка!</strong> Год должен быть интервале от 1800 до 2200";
            yearError.classList.remove("hidden");
            result = false;
        } else {
            yearError.innerHTML = "";
            yearError.classList.add("hidden");
        }

        if (!firstDOWInput[0].checked && !firstDOWInput[1].checked) {
            firstDOWError.innerHTML = "<strong>Ошибка!</strong> Выберите первый день недели";
            firstDOWError.classList.remove("hidden");
            result = false;
        } else {
            firstDOWError.innerHTML = "";
            firstDOWError.classList.add("hidden");
        }

        if (!mediaTypeInput[0].checked && !mediaTypeInput[1].checked) {
            mediaTypeError.innerHTML = "<strong>Ошибка!</strong> Укажите, куда вывести календарь (браузер / PDF)";
            mediaTypeError.classList.remove("hidden");
            result = false;
        } else {
            mediaTypeError.innerHTML = "";
            mediaTypeError.classList.add("hidden");
        }

        return result;
    }
})();
