package eu.pm.tools.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.FileAppender;
import ch.qos.logback.core.boolex.EvaluationException;
import ch.qos.logback.core.boolex.EventEvaluator;
import ch.qos.logback.core.spi.DeferredProcessingAware;
import ch.qos.logback.core.status.ErrorStatus;
import eu.pm.tools.logback.encoder.NOPLogbackEncoder;
import eu.pm.tools.logback.encoder.PoorMansEncoder;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * prints {@link SummaryMetrics} to file.
 *
 * @author silviu ilie
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public class SummaryFileAppender<E> extends FileAppender<E> {

    {
        //defaults
        setImmediateFlush(true);
        setEncoder(new NOPLogbackEncoder<E>());
    }

    final private SummaryMetrics metrics = new SummaryMetrics();
    protected EventEvaluator<E> eventEvaluator;
    private PoorMansEncoder poorMansEncoder;

    private String description;


    /**
     * see {@link super#subAppend(Object)}
     *
     * @param event
     */
    @Override
    protected void subAppend(E event) {
        if (!isStarted()) {
            return;
        }
        try {
            // this step avoids LBCLASSIC-139
            if (event instanceof DeferredProcessingAware) {
                ((DeferredProcessingAware) event).prepareForDeferredProcessing();
            }
            // the synchronization prevents the OutputStream from being closed while we
            // are writing. It also prevents multiple threads from entering the same
            // converter. Converters assume that they are in a synchronized block.
            // lock.lock();

            // no need : byte[] byteArray = this.encoder.encode(event);

            writeBytes(event);

        } catch (IOException ioe) {
            // as soon as an exception occurs, move to non-started state
            // and add a single ErrorStatus to the SM.
            this.started = false;
            addStatus(new ErrorStatus("IO failure in appender", this, ioe));
        }
    }

    private void writeBytes(E eventObject) throws IOException {

        ILoggingEvent eventObj = (ILoggingEvent) eventObject;

        lock.lock();
        try {

            metrics.incrementLevel(eventObj.getLevel())
                    .incrementThreads(eventObj.getThreadName())
                    .setDescription(this.getDescription())
                    .setEnd(eventObj.getTimeStamp());

            try {
                if (eventEvaluator.evaluate(eventObject)) {
                    // clean file
                    PrintWriter pw = new PrintWriter(getFile());
                    pw.write("");
                    pw.close();
                    // out
                    this.getOutputStream().write(poorMansEncoder.encode(metrics));
                }
            } catch (EvaluationException e) {
                addError("SummaryFileAppender's EventEvaluator threw an Exception-", e);
            }

            if (this.isImmediateFlush()) {
                getOutputStream().flush();
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

    public void setEventEvaluator(EventEvaluator<E> eventEvaluator) {
        this.eventEvaluator = eventEvaluator;
    }

    public void setPoorMansEncoder(PoorMansEncoder poorMansEncoder) {
        this.poorMansEncoder = poorMansEncoder;
    }

    SummaryMetrics metrics() {
        return this.metrics;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
