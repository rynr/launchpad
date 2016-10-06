package org.rjung.util.launchpad.midi;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.rjung.util.launchpad.LaunchpadHandler;
import org.rjung.util.launchpad.MidiCommand;

/**
 * The {@link MidiReader} listens to all events of the Launchpad and sends the
 * information to all registered {@link LaunchpadHandler}s.
 */
public class MidiReader implements Runnable {
    private static final Logger LOG = Logger.getLogger(MidiReader.class
            .getName());
    private RandomAccessFile device;
    private Set<LaunchpadHandler> handlers;

    /**
     * Create a {@link MidiReader} with the {@link RandomAccessFile} to the
     * midi-device.
     *
     * @param device
     *            {@link RandomAccessFile} to the midi-device of the Launchpad.
     */
    public MidiReader(final RandomAccessFile device) {
        this.handlers = new HashSet<>();
        this.device = device;
    }

    /**
     * Add a {@link LaunchpadHandler} to the {@link MidiReader} to be informed
     * of any events from the Launchpad.
     *
     * @param handler
     *            The {@link LaunchpadHandler} to be added.
     * @return True, if the {@link LaunchpadHandler} was not already available.
     */
    public boolean addHandler(final LaunchpadHandler handler) {
        return handlers.add(handler);
    }

    /**
     * Remove a {@link LaunchpadHandler} to not be informed any more of any
     * events from the Launchpad.
     *
     * @param handler
     *            The {@link LaunchpadHandler} to be removed.
     * @return True if the {@link LaunchpadHandler} was present.
     */
    public boolean removeHandler(final LaunchpadHandler handler) {
        return handlers.remove(handler);
    }

    /**
     * Run the {@link MidiReader}-Thread to read events of the Launchpad.
     */
    // Simplest straight forward solution, while running read status-byte,
    // decide the number of data-bytes, read that number of bytes, handle
    // the result.
    @Override
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

    private byte[] getDataForCommand(final byte command) throws IOException {
        byte length = isSysEx(command) ? device.readByte()
                : (byte) (isOneByteData(command) ? 0x01 : 0x02);
        byte[] data = new byte[length];
        for (int i = length; i > 0; i--) {
            data[length - i] = device.readByte();
        }
        return data;
    }

    private void handle(final MidiCommand command) {
        for (LaunchpadHandler midiHandler : handlers) {
            midiHandler.recieve(command);
        }
    }

    private boolean isOneByteData(final byte command) {
        return command >= (byte) 0xc0 && command < (byte) 0xe0;
    }

    private boolean isSysEx(final byte command) {
        return command >= (byte) 0xf0;
    }

    private boolean isStatusByte(final byte command) {
        return command >= (byte) 0x80;
    }

}
