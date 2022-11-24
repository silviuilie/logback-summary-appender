package eu.pm.tools.logback.encoder;

import eu.pm.tools.logback.SummaryMetrics;
import org.apache.commons.text.StringSubstitutor;

import java.util.HashMap;
import java.util.Map;

/**
 * template encoder with commons.text.StringSubstitutor
 *
 * @author silviu ilie
 *
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public class StringSubstitutorEncoder implements PoorMansEncoder {

    String levelsTemplateString = "{\"${level}\":\"${count}\"}";

    String templateString = "{" +
                "  \"levels\": \"${levels}\"," +
                "  \"totalEvents\": \"${totalEvents}\"," +
                "  \"threadCount\": \"${totalThreadCount}\"," +
                "  \"start\": \"${start}\"," +
                "  \"end\": \"${end}\"," +
                "  \"duration\": \"${duration}\"" +
            "}";

    public byte[] encode(SummaryMetrics sm) {

        Map<String, Object> map = new HashMap<>();

        map.put("end", cachingDateFormatter.format(sm.getEnd()));
        map.put("start", cachingDateFormatter.format(sm.getStart()));

        map.put("levels", sm.getLevelMap());

        map.put("totalEvents", sm.getTotalEvents());
        map.put("duration", sm.getEnd() - sm.getStart());
        map.put("totalThreadCount", sm.getTotalThreadCount());

        StringSubstitutor sub = new StringSubstitutor(map);

        return sub.replace(templateString).getBytes();
    }
}
