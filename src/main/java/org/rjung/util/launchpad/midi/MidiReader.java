package org.rjung.util.launchpad.midi;

import org.rjung.util.launchpad.LaunchpadHandler;
import org.rjung.util.launchpad.MidiCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Set;

public class MidiReader implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(MidiReader.class);
    private RandomAccessFile device;
    private Set<LaunchpadHandler> handlers;

    public MidiReader(RandomAccessFile device) {
        LOG.debug("Reader starting");
        this.handlers = new HashSet<LaunchpadHandler>();
        this.device = device;
    }

    public boolean addHandler(LaunchpadHandler handler) {
        return handlers.add(handler);
    }

    public boolean removeHandler(LaunchpadHandler handler) {
        return handlers.remove(handler);
    }

    // Simplest straight forward solution, while running read status-byte,
    // decide the number of data-bytes, read that number of bytes, handle
    // the result.
    public void run() {
        try {
            while (true) {
                LOG.debug("Trying to read");
                byte command = device.readByte();
                LOG.debug("got: " + command);
                if (isStatusByte(command)) {
                    handle(new MidiCommand(command, getDataForCommand(command)));
                } else {
                    LOG.error("Received invalid data packet: "
                            + String.format("%02x", command));
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private byte[] getDataForCommand(byte command) throws IOException {
        byte length = (byte) (isSysEx(command) ? device.readByte()
                : (isOneByteData(command) ? 0x01 : 0x02));
        byte[] data = new byte[length];
        for (int i = length; i > 0; i--) {
            data[length - i] = device.readByte();
        }
        return data;
    }

    private void handle(MidiCommand command) {
        LOG.debug("< " + command);
        for (LaunchpadHandler midiHandler : handlers) {
            midiHandler.recieve(command);
        }
    }

    private boolean isOneByteData(byte command) {
        return command >= (byte) 0xc0 && command < (byte) 0xe0;
    }

    private boolean isSysEx(byte command) {
        return command >= (byte) 0xf0;
    }

    private boolean isStatusByte(byte command) {
        return command >= (byte) 0x80;
    }

}
