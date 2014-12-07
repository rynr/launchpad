package org.rjung.util.launchpad.midi;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.rjung.util.launchpad.LaunchpadHandler;
import org.rjung.util.launchpad.MidiCommand;

public class MidiReader implements Runnable {
    private static final Logger LOG = Logger.getLogger(MidiReader.class
            .getName());
    private RandomAccessFile device;
    private Set<LaunchpadHandler> handlers;

    public MidiReader(RandomAccessFile device) {
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
                byte command = device.readByte();
                if (isStatusByte(command)) {
                    handle(new MidiCommand(command, getDataForCommand(command)));
                } else {
                    LOG.log(Level.WARNING, "Received non command Midi-signal");
                }
            }
        } catch (IOException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
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
