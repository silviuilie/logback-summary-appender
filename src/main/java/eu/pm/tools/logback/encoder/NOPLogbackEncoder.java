package eu.pm.tools.logback.encoder;

import ch.qos.logback.core.encoder.EchoEncoder;

/**
 * no operation appender.
 *
 * @author silviu ilie
 * @since 1.0-SNAPSHOT on logback-summary-appender
 **/
public class NOPLogbackEncoder<E> extends EchoEncoder<E> {
    @Override
    public byte[] encode(E event) {
        return null;
    }

    @Override
    public byte[] footerBytes() {
        return null;
    }

    @Override
    public byte[] headerBytes() {
        return null;
    }
}
