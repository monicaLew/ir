package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;

@Controller
public class CalendarController {

    private String[] DOW_NAMES = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private String[] MONTH_NAMES = {"January", "February", "March", "April", "May", "June", "July", "August",
            "September", "October", "November", "December"};

    @Autowired
    private CalendarService calendarService;

    @RequestMapping({"/", "/home"})
    public ModelAndView showHomePage() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int count = calendarService.getCounterValue();
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("count", count);
        modelAndView.addObject("year", year);
        return modelAndView;
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public ModelAndView showCalendar(@RequestParam("year") int year,
                                     @RequestParam("first-dow") String firstDOW,
                                     @RequestParam("media-type") String mediaType,
                                     HttpServletRequest httpServletRequest,
                                     HttpServletResponse httpServletResponse) {
        if (year < 1800) {
            year = 1800;
        } else if (year > 2200) {
            year = 2200;
        }

        int dow = getFirstDOW(firstDOW);
        byte[][][] calendar = calendarService.getCalendar(year, dow);
        String[] dowNames = new String[7];
        for (int i = 0; i < 7; i++) {
            dowNames[i] = DOW_NAMES[(i + dow - 1) % 7];
        }

        ModelAndView modelAndView = new ModelAndView("calendar");
        modelAndView.addObject("year", year);
        modelAndView.addObject("calendar", calendar);
        modelAndView.addObject("dowNames", dowNames);
        modelAndView.addObject("monthNames", MONTH_NAMES);
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception ex) {
        ModelAndView model = new ModelAndView("error", "message", "Неизвестная ошибка");
        return model;
    }

    private int getFirstDOW(String firstDOW) {
        if ("sun".equalsIgnoreCase(firstDOW)) {
            return Calendar.SUNDAY;
        } else {
            return Calendar.MONDAY;
        }
    }

}
