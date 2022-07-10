package eu.pm.tools.logback.encoder;

import ch.qos.logback.classic.Level;
import eu.pm.tools.logback.SummaryMetrics;

/**
 * {@link StringBuilder} encoder
 *
 * @author silviu ilie
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public class StringBuilderEncoder implements PoorMansEncoder {

    @Override
    public byte[] encode(SummaryMetrics metrics) {

        StringBuilder sb = new StringBuilder("{");
        String comma = ",";
        for (Level lvl : metrics.getLevels()) {
            sb.append("\"");
            sb.append(lvl.levelStr);
            sb.append("\" : \"");
            sb.append(metrics.getLevelCount(lvl));
            sb.append("\"");
            sb.append(comma);
            comma = ",";
        }

        sb.append("\"totalEvents\":\"").append(metrics.getTotalEvents()).append("\",");
        sb.append("\"threadCount\":\"").append(metrics.getTotalThreadCount()).append("\",");
        sb.append("\"start\":\"").append(cachingDateFormatter.format(metrics.getStart())).append("\",");
        sb.append("\"end\":\"").append(cachingDateFormatter.format(metrics.getEnd())).append("\",");
        sb.append("\"duration\":\"").append(metrics.getEnd() - metrics.getStart()).append("\"");


        return sb.append("}").toString().getBytes();
    }
}
