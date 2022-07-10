package eu.pm.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * silviu 08/07/2022 12:59 logback-summary-appender
 **/
public class DeleteMe {

    public static void main(String[] args) {
        LOGGER.debug("test");
        LOGGER.warn("test :|");
        LOGGER.error("test :[");
        LOGGER.error("test :(");
        LOGGER.error("test :(");
        try {
            LOGGER.debug("sleep in main ");
            Thread.sleep(1000 * 60 * 2);
            LOGGER.debug("sleep done in main stop");
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(),e);
        } finally {
            LOGGER.debug("200 millis passed");
        }
    }


    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteMe.class);
}
