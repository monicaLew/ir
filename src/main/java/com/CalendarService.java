package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
public class CalendarService {

    private byte[] MONTH_DAYS = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    @Autowired
    private CalendarDAO calendarDAO;

    public int getCounterValue() {
        return calendarDAO.getCounterValue();
    }

    public byte[][][] getCalendar(int year, int dow) {
        byte[][][] calendar = new byte[12][6][7];
        for (int month = 0; month < 12; month++) {
            for (int y = 0; y < 6; y++) {
                for (int x = 0; x < 7; x++) {
                    calendar[month][y][x] = 0;
                }
            }
        }
        Calendar calendarInstance = Calendar.getInstance();
        for (int month = 0; month < 12; month++) {
            calendarInstance.set(year, month, 1);
            int shift = calendarInstance.get(Calendar.DAY_OF_WEEK) - dow;
            if (shift < 0) {
                shift += 7;
            }
            int lastDay = getLastDay(year, month);
            for (int day = 0; day < lastDay; day++) {
                int x = (shift + day) % 7;
                int y = (shift + day) / 7;
                calendar[month][y][x] = (byte) (day + 1);
            }
        }
        increaseCounterValue();
        return calendar;
    }

    private void increaseCounterValue() {
        calendarDAO.increaseCounterValue();
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
