package eu.pm.tools.logback.evaluator;

import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;

/**
 * @author silviu ilie
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public class AlwaysTrueEventEvaluator extends EventEvaluatorBase {
    @Override
    public boolean evaluate(Object event) throws NullPointerException, EvaluationException {
        return true;
    }
}
