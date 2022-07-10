package eu.pm.tools.logback.encoder;

import ch.qos.logback.core.util.CachingDateFormatter;
import eu.pm.tools.logback.SummaryMetrics;

/**
 * slimmer alias to ch.qos.logback.core.encoder.Encoder
 * TODO : rename
 * @author silviu ilie (as silviu)
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public interface PoorMansEncoder {

    public byte[] encode(SummaryMetrics sm);

    CachingDateFormatter cachingDateFormatter = new CachingDateFormatter("MM/dd/yyyy HH:mm:ss.SSS");



}
