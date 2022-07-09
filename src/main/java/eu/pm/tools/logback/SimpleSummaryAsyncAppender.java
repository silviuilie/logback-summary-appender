package eu.pm.tools.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;
import ch.qos.logback.core.AsyncAppenderBase;
import ch.qos.logback.core.rolling.TriggeringPolicy;

/**
 *
 * TODO : delete ?
 * @see ch.qos.logback.classic.AsyncAppender
 *
 * <p>
 * silviu 21/06/2022 22:08 logback-summary-appender
 **/
public class SimpleSummaryAsyncAppender extends AsyncAppenderBase<ILoggingEvent> {

    //todo : TimeBasedRollingPolicy
    private TriggeringPolicy<ILoggingEvent> triggeringPolicy;


    private SummaryMetrics metrics;

    {    this.addAppender(new SummaryAppender()); }



    class SummaryAppender extends AppenderBase<ILoggingEvent> {

        @Override
        protected void append(ILoggingEvent eventObject) {
            metrics.incrementLevel(eventObject.getLevel())
                    .incrementThreads(eventObject.getThreadName())
                    .setEnd(eventObject.getTimeStamp());
        }

    }

}
