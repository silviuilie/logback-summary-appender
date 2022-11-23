package eu.pm.tools.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.spi.DeferredProcessingAware;
import ch.qos.logback.core.status.ErrorStatus;
import eu.pm.tools.logback.listener.MetricsListener;

import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * logging summary emitter; see {@link MetricsListener}.
 *
 * @see SummaryMetrics
 * @see MetricsListener
 *
 * @author silviu ilie
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public class SummaryUnsynchronizedAppender<E> extends UnsynchronizedAppenderBase<E> {

    final private SummaryMetrics metrics = new SummaryMetrics();
    protected EventEvaluator<E> eventEvaluator;
    protected MetricsListener listener;

    protected String description;


    /**
     * All synchronization in this class is done via the lock object.
     */
    protected final ReentrantLock lock = new ReentrantLock(false);

    @Override
    protected void append(E eventObject) {

        if (!isStarted()) {
            return;
        }

        subAppend(eventObject);
    }

    protected void subAppend(E event) {
        if (!isStarted()) {
            return;
        }
        try {
            // this step avoids LBCLASSIC-139
            if (event instanceof DeferredProcessingAware) {
                ((DeferredProcessingAware) event).prepareForDeferredProcessing();
            }
            writeBytes(event);

        } catch (IOException ioe) {
            // as soon as an exception occurs, move to non-started state
            // and add a single ErrorStatus to the SM.
            this.started = false;
            addStatus(new ErrorStatus("IO failure in appender", this, ioe));
        }

    }

    @Override
    public void stop() {
        lock.lock();
        try {
            super.stop();
        } finally {
            lock.unlock();
        }
    }

    private void writeBytes(E eventObject) throws IOException {

        ILoggingEvent eventObj = (ILoggingEvent)eventObject;

        lock.lock();
        try {

            metrics.incrementLevel(eventObj.getLevel())
                    .incrementThreads(eventObj.getThreadName())
                    .setDescription(this.getDescription())
                    .setEnd(eventObj.getTimeStamp());

            try {
                if (eventEvaluator.evaluate(eventObject)) {
                    listener.changed(metrics);
                }
            } catch (EvaluationException e) {
                addError("SummaryFileAppender's EventEvaluator threw an Exception-", e);
            }

        } finally {
            lock.unlock();
        }
    }


    public void setEvaluator(EventEvaluator<E> eventEvaluator) {
        this.eventEvaluator = eventEvaluator;
    }
    public EventEvaluator<E> getEventEvaluator() {
        return eventEvaluator;
    }

    public MetricsListener getMetricsListener() {
        return listener;
    }

    public void setMetricsListener(MetricsListener listener) {
        this.listener = listener;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
