package com;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Calendar;

@Controller(value = "homeController")
public class HomeController {

    private final int[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private final String[] DOW_SHORT_NAMES = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
    private final String[] DOW_FULL_NAMES = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private final String[] MONTH_FULL_NAMES = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    private int count;

    public HomeController() {
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
        int[][][] result = new int[12][6][7];
        for (int month = 0; month < 12; month ++) {
            for (int y = 0; y < 6; y++) {
                for (int x = 0; x < 7; x++) {
                    result[month][y][x] = 0;
                }
            }
        }
        Calendar calendar = Calendar.getInstance();
        for (int month = 0; month < 12; month++) {
            calendar.set(year, month, 1);
            int shift = calendar.get(Calendar.DAY_OF_WEEK) - firstDOW;
            if (shift < 0) {
                shift += 7;
            }
            int lastDay = getLastDay(year, month);
            for (int day = 0; day < lastDay; day++) {
                int x = (shift + day) % 7;
                int y = (shift + day) / 7;
                result[month][y][x] = day + 1;
            }
        }
        String[] dowNames = new String[7];
        for (int dow = 0; dow < 7; dow++) {
            dowNames[dow] = DOW_SHORT_NAMES[(dow + firstDOW - 1) % 7];
        }
        if ("pdf".equals(mediaType)) {
            count++;
        }
        ModelAndView modelAndView = new ModelAndView("calendar");
        modelAndView.addObject("calendar", result);
        modelAndView.addObject("monthNames", MONTH_FULL_NAMES);
        modelAndView.addObject("dowNames", dowNames);
        modelAndView.addObject("columnCount", columnCount);
        modelAndView.addObject("year", year);
        return modelAndView;
    }

    private int getLastDay(int year, int month) {
        if ((month < 0) || (month > 11)) {
            throw new RuntimeException("Unknown month");
        } else {
            return ((1 != month) || !isLeapYear(year)) ? MONTH_DAYS[month] : 29;
        }
    }

    private boolean isLeapYear(int year) {
        return (0 == year % 400) || ((0 != year % 100) && (0 == year % 4));
    }

}
