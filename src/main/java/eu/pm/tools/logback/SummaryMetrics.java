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
 * silviu 07/07/2022 14:49 logback-summary-appender
 **/
public class SummaryMetrics {

    private Map<Level, Integer> levelMap = new HashMap<>();
    private Map<String, Integer> threadCount = new HashMap<>();
    private long start;
    private long end;

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

    int getLevelCount(Level level) {
       return levelMap.get(level);
    }

    public Map<Level, Integer>  getLevelMap() {
        return levelMap;
    }


    Set<Level> getLevels() {
       return levelMap.keySet();
    }

}