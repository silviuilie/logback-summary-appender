package eu.pm.tools.logback.evaluator;

import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;

/**
 * silviu 08/07/2022 21:44 logback-summary-appender
 **/
public class AlwaysTrueEventEvaluator extends EventEvaluatorBase {
    @Override
    public boolean evaluate(Object event) throws NullPointerException, EvaluationException {
        return true;
    }
}
