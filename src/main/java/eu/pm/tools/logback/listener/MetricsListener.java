package eu.pm.tools.logback.listener;

import eu.pm.tools.logback.SummaryMetrics;

import java.util.EventListener;

/**
 * TODO : comment !
 *
 * @author silviu ilie
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public interface MetricsListener extends EventListener {

    void changed(SummaryMetrics metrics);

}
