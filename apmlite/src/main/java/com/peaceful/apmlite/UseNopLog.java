package com.peaceful.apmlite;

import org.perf4j.GroupedTimingStatistics;
import org.perf4j.helpers.GenericAsyncCoalescingStatisticsAppender;

/**
 * Created by wangjun on 2018-12-29.
 */
public class UseNopLog {

    private final GenericAsyncCoalescingStatisticsAppender baseImplementation = new GenericAsyncCoalescingStatisticsAppender();

    // --- options ---

    /**
     * The <b>TimeSlice</b> option represents the length of time, in milliseconds, of the window in
     * which appended LoggingEvents are coalesced to a single GroupedTimingStatistics and sent to
     * downstream appenders. Defaults to 30,000 milliseconds.
     *
     * @return the TimeSlice option.
     */
    public long getTimeSlice() {
        return baseImplementation.getTimeSlice();
    }

    /**
     * Sets the value of the <b>TimeSlice</b> option.
     *
     * @param timeSlice The new TimeSlice option, in milliseconds.
     */
    public void setTimeSlice(long timeSlice) {
        baseImplementation.setTimeSlice(timeSlice);
    }

    public void start() {
        baseImplementation.start(new GenericAsyncCoalescingStatisticsAppender.GroupedTimingStatisticsHandler() {
            @Override
            public void handle(GroupedTimingStatistics statistics) {
                System.out.println(statistics);
                MyMemoryDB.save(statistics);
            }

            @Override
            public void error(String errorMessage) {
                System.err.println(errorMessage);
            }
        });
    }

    public void append(String message) {
        baseImplementation.append(message);
    }
}
