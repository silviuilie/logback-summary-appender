package eu.pm.tools.logback;


import ch.qos.logback.classic.Level;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 *
 *
 * describes metrics to gather :
 *
 *     level-1 : ${count}
 *     level-2 : ${count}
 *     ..
 *     threads : ${count}
 *     total : ${count}
 *     duration : ${end-start}
 *     start : ${start}
 *     end : ${end}
 *   todo : what else?
 *
 * @author silviu ilie
 *
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public class SummaryMetrics {

    private Map<Level, Integer> levelMap = new HashMap<>();
    private Map<String, Integer> threadCount = new HashMap<>();
    private long start;
    private long end;

    private String description;

    public SummaryMetrics() {
        start = System.currentTimeMillis();
    }

    SummaryMetrics incrementLevel(Level level) {
        levelMap.compute(level, (lvl,count) ->  count == null ? 1: count + 1);
        return this;
    }

    SummaryMetrics incrementThreads(String theadName) {
        threadCount.compute(theadName, (tname, count) -> count == null ? 1 : count+ 1);
        return this;
    }

    SummaryMetrics setStart(long start) {
        this.start = start;
        return this;
    }

    SummaryMetrics setEnd(long end) {
        this.end = end;
        return this;
    }

    public long getTotalEvents() {
        return levelMap.values().stream().reduce(0, Integer::sum);
    }

    public long getTotalThreadCount() {
        return threadCount.keySet().stream().distinct().count();
    }

    public long getStart() {
        return start;
    }

    public long getEnd() {
        return end;
    }

    public int getLevelCount(Level level) {
       return levelMap.get(level);
    }

    public Map<Level, Integer>  getLevelMap() {
        return levelMap;
    }


    public String getDescription() {
        return description;
    }

    public SummaryMetrics setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<Level> getLevels() {
       return levelMap.keySet();
    }

    @Override
    public String toString() {
        return "SummaryMetrics{" +
                "levelMap=" + levelMap +
                ", threadCount=" + threadCount +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
