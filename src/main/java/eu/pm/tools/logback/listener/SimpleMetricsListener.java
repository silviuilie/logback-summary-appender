package eu.pm.tools.logback.listener;

import eu.pm.tools.logback.SummaryMetrics;

/**
 * @author silviu ilie
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public class SimpleMetricsListener implements MetricsListener {

    @Override
    public void changed(SummaryMetrics metrics) {
        System.out.println("SimpleMetricsListener#changed" + metrics);
    }

}
