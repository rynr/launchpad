package org.rjung.util.launchpad;

import org.rjung.util.launchpad.midi.Channel;
import org.rjung.util.launchpad.midi.Color;
import org.rjung.util.launchpad.midi.Command;
import org.rjung.util.launchpad.midi.MidiReader;
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

    private Thread thread;

    private MidiReader reader;

    /**
     * The communication with the device uses a character-device. Usually it's
     * one of `/proc/midi*`.
     * 
     * @param device
     *            File pointing to the blockdevice of a midi controller.
     * @throws FileNotFoundException
     *             This exception is raised, when the device can not be found or
     *             accessed
     */
    public Launchpad(File device) throws FileNotFoundException {
        LOG.debug("Starting Launchpad with " + device);
        this.device = new RandomAccessFile(device, FILE_ACCESS_MODE);
        this.reader = new MidiReader(this.device);
        this.thread = new Thread(reader);
    }

    public Launchpad(String device) throws FileNotFoundException {
        this(new File(device));
    }

    public synchronized void start() {
        thread.start();
    }

    public boolean isAlive() {
        return thread.isAlive();
    }

    public boolean isInterrupted() {
        return thread.isInterrupted();
    }

    public void addHandler(LaunchpadHandler handler) {
        this.reader.addHandler(handler);
    }

    public void send(MidiCommand command) throws IOException {
        LOG.debug("> " + command);
        device.writeByte(command.getCommand());
        device.write(command.getData());
    }

    public void off(int x, int y) throws IOException {
        send(new MidiCommand.Builder(Command.NOTE_OFF, Channel.C1)
                .setDataBytes(new byte[] { led(x, y), Color.OFF.getByte() })
                .toMidiCommand());
    }

    public void set(int x, int y, Color c) throws IOException {
        send(new MidiCommand.Builder(Command.NOTE_ON, Channel.C1).setDataBytes(
                new byte[] { led(x, y), c.getByte() }).toMidiCommand());
    }

    private byte led(int x, int y) {
        if (x < 0 || x > 16 || y < 0 || y > 16) {
            throw new IllegalArgumentException(
                    "x and y may only be within 1-16");
        }
        return (byte) (0x10 * x + y);
    }
}
