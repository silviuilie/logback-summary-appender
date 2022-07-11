package eu.pm.tools.logback;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.LoggingEvent;
import eu.pm.tools.DeleteMe;
import eu.pm.tools.logback.encoder.PoorMansEncoder;
import eu.pm.tools.logback.evaluator.AlwaysTrueEventEvaluator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * TODO : comment !
 *
 * @author silviu ilie
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public class SummaryFileAppenderTest   {

    SummaryFileAppender<ILoggingEvent> tested = new SummaryFileAppender<>();

    @Test
    public void subAppend( ) throws InterruptedException {

        ILoggingEvent loggingEvent = new LoggingEvent();

        tested.setContext(new LoggerContext());
        tested.setFile("dummy.out");
        tested.setEvaluator(new AlwaysTrueEventEvaluator());
        tested.setPoorMansEncoder(new PoorMansEncoder() {
            @Override
            public byte[] encode(SummaryMetrics sm) {
                 assertNotNull(sm);
                 assertEquals(1, sm.getLevels().size());
                 assertEquals(1, sm.getTotalEvents());
                 assertEquals(1, sm.getTotalThreadCount());

                return sm.toString().getBytes();
            }
        });

        tested.start();
        tested.subAppend(loggingEvent);

        assertNotNull(tested.metrics());

    }


    private static final Logger LOGGER = LoggerFactory.getLogger(SummaryFileAppenderTest.class);
}
