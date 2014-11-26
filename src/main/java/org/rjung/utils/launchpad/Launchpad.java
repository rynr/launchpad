package org.rjung.utils.launchpad;

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
    private Reader reader;

    /**
     * The communication with the device uses a character-device. Usually it's
     * one of `/proc/midi*`.
     * 
     * @param device
     * @throws FileNotFoundException
     */
    public Launchpad(File device) throws FileNotFoundException {
        this.device = new RandomAccessFile(device, FILE_ACCESS_MODE);
        this.reader = new Reader(this.device);
    }

    public Launchpad(String device) throws FileNotFoundException {
        this(new File(device));
    }

    public void setColor(int x, int y, Color color) {
        // TODO
    }

    class Reader implements Runnable {
        private RandomAccessFile device;
        private boolean running = false;

        public Reader(RandomAccessFile device) {
            this.device = device;
        }

        public void run() {
            byte[] dataGram = new byte[3];
            try {
                running = true;

                while (running) {
                    byte[] command = readCommand();
                    // TODO run handlers
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            } finally {
                running = false;
            }
        }

        private byte[] readCommand() throws IOException {
            // TODO
            return null;
        }

        // TODO registration of handlers

        public boolean isRunning() {
            return running;
        }
    }
}
