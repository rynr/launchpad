package org.rjung.utils.launchpad;

import org.rjung.utils.launchpad.midi.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Launchpad {

    private static final String FILE_ACCESS_MODE = "rw";

    private static final Logger LOG = LoggerFactory.getLogger(Launchpad.class);

    private RandomAccessFile device;

    private Thread reader;

    /**
     * The communication with the device uses a character-device. Usually it's
     * one of `/proc/midi*`.
     * 
     * @param device
     * @throws FileNotFoundException
     */
    public Launchpad(File device) throws FileNotFoundException {
        this.device = new RandomAccessFile(device, FILE_ACCESS_MODE);
        this.reader = new Thread(new Reader(this.device));
        reader.start();
    }

    public Launchpad(String device) throws FileNotFoundException {
        this(new File(device));
    }

    class Reader implements Runnable {
        private RandomAccessFile device;
        private boolean running = false;

        public Reader(RandomAccessFile device) {
            this.device = device;
        }

        // Simplest straight forward solution, while running read status-byte,
        // decide the number of data-bytes, read that number of bytes, handle
        // the result.
        public void run() {
            try {
                while (true) {
                    byte command = device.readByte();
                    if (isStatusByte(command)) {
                        handle(new Command(command, getDataForCommand(command)));
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

        private void handle(Command command) {
            LOG.debug("< " + command);
            // TODO handle command
        }

        // TODO registration of handlers

        private boolean isOneByteData(byte command) {
            return command >= 0xc0 && command < 0xe0;
        }

        private boolean isSysEx(byte command) {
            return command >= 0xf0;
        }

        private boolean isStatusByte(byte command) {
            return command >= 0x80;
        }

        public boolean isRunning() {
            return running;
        }

    }
}
