package eu.pm.tools.logback.encoder;

import eu.pm.tools.logback.SummaryMetrics;

/**
 * slimmer alias to ch.qos.logback.core.encoder.Encoder
 * TODO : rename
 * @author silviu ilie (as silviu)
 * @since add version on logback-summary-appender
 **/
public interface PoorMansEncoder {

    public byte[] encode(SummaryMetrics sm);

}
