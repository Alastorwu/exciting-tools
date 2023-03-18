package com.exciting.common.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

public class TaskDurationCalculator {
    private static final LocalTime WORK_START_TIME = LocalTime.of(9, 0);
    private static final LocalTime WORK_END_TIME = LocalTime.of(18, 0);

    public static long calculateDuration(LocalDateTime startTime, LocalDateTime endTime) {
        long duration = 0;

        LocalDate currentDate = startTime.toLocalDate();
        LocalTime currentTime = startTime.toLocalTime();
        while (currentDate.isBefore(endTime.toLocalDate()) || currentDate.isEqual(endTime.toLocalDate())) {
            if (isWorkingDay(currentDate)) {
                if (currentDate.isEqual(startTime.toLocalDate())) {
                    currentTime = startTime.toLocalTime();
                } else if (currentDate.isEqual(endTime.toLocalDate())) {
                    currentTime = WORK_END_TIME;
                }

                LocalTime start = max(currentTime, WORK_START_TIME);
                LocalTime end = min(endTime.toLocalTime(), WORK_END_TIME);
                duration += ChronoUnit.MINUTES.between(start, end);
            }

            currentDate = currentDate.plusDays(1);
            currentTime = WORK_START_TIME;
        }

        return duration;
    }

    private static boolean isWorkingDay(LocalDate date) {
        return !date.getDayOfWeek().equals(DayOfWeek.SATURDAY) &&
                !date.getDayOfWeek().equals(DayOfWeek.SUNDAY) &&
                !isHoliday(date);
    }

    private static boolean isHoliday(LocalDate date) {
        // TODO: 实现节假日判断的逻辑
        return false;
    }

    private static LocalTime max(LocalTime a, LocalTime b) {
        return a.isAfter(b) ? a : b;
    }

    private static LocalTime min(LocalTime a, LocalTime b) {
        return a.isBefore(b) ? a : b;
    }
}