package eu.pm.tools.logback.evaluator;

import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluatorBase;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @author silviu ilie
 *
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public class OnTimerEventEvaluator extends EventEvaluatorBase {

    boolean evaluation = false;
    long delayInMinutes = 0;

    public void setDelayInMinutes(long delayInMinutes) {
        this.delayInMinutes = delayInMinutes;
        new EventTimer();
    }

    private class EventTimer {

        long delayInMillis = 0;
        private Timer timer;

        public EventTimer() {
            timer = new Timer(true);
            init();
        }

        public void init() {

            this.delayInMillis = delayInMinutes * 1000 * 60 ;
            timer.schedule(new TimerTask() {
                //private  final Logger LOGGER = LoggerFactory.getLogger(getClass());
                @Override
                public void run() {
                    //LOGGER.debug("TimerTask#run");
                    evaluation = !evaluation;
                }
            }, delayInMinutes * 1000, delayInMinutes * 1000);
        }
    }

    @Override
    public boolean evaluate(Object event) throws NullPointerException, EvaluationException {
        return evaluation;
    }
}
