package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller(value = "homeController")
public class CalendarController {

    private final String[] DOW_SHORT_NAMES = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private final String[] DOW_FULL_NAMES = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private final String[] MONTH_FULL_NAMES = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    @Autowired
    CalendarService calendarService;

    private int count;

    public CalendarController() {
        count = 0;
    }

    @RequestMapping(value = {"/", "/home"})
    public ModelAndView showHomePage() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("count", count);
        modelAndView.addObject("dowNames", DOW_FULL_NAMES);
        modelAndView.addObject("year", year);
        return modelAndView;
    }

    @RequestMapping(value = "/calendar", method = RequestMethod.GET)
    public ModelAndView getCalendar(@RequestParam(value = "year") int year,
                                    @RequestParam(value = "firstDOW") int firstDOW,
                                    @RequestParam(value = "columnCount") int columnCount,
                                    @RequestParam(value = "mediaType") String mediaType) {
        String[] dowNames = new String[7];
        for (int dow = 0; dow < 7; dow++) {
            dowNames[dow] = DOW_SHORT_NAMES[(dow + firstDOW - 1) % 7];
        }
        if ("pdf".equals(mediaType)) {
            count++;
        }
        ModelAndView modelAndView = new ModelAndView("calendar");
        modelAndView.addObject("calendar", calendarService.getCalendar(year, firstDOW));
        modelAndView.addObject("monthNames", MONTH_FULL_NAMES);
        modelAndView.addObject("dowNames", dowNames);
        modelAndView.addObject("columnCount", columnCount);
        modelAndView.addObject("year", year);
        return modelAndView;
    }

}
