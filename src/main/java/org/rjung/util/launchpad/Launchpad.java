package org.rjung.util.launchpad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.rjung.util.launchpad.midi.Channel;
import org.rjung.util.launchpad.midi.Color;
import org.rjung.util.launchpad.midi.Command;
import org.rjung.util.launchpad.midi.MidiReader;

/**
 * The {@link Launchpad} provides access to a Launchpad via the midi-device. To
 * create an instance provide the device-file as a {@link File} or
 * {@link String} with the path. As this requires a read-thread, you need to
 * {@link #start()} the {@link Launchpad} after you created it.
 *
 * To get a notication when a button is pressed on the Launchpad you can add a
 * {@link LaunchpadHandler} with {@link #addHandler(LaunchpadHandler)} (see
 * {@link LaunchpadHandler} on more information on building a
 * {@link LaunchpadHandler}.
 */
public class Launchpad {

    private static final String FILE_ACCESS_MODE = "rw";

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
    public Launchpad(final File device) throws FileNotFoundException {
        this.device = new RandomAccessFile(device, FILE_ACCESS_MODE);
        this.reader = new MidiReader(this.device);
        this.thread = new Thread(reader);
    }

    /**
     * The communication with the device uses a character-device. Usually it's
     * one of `/proc/midi*`.
     *
     * @param device
     *            String of the path pointing to the blockdevice of a midi
     *            controller.
     * @throws FileNotFoundException
     *             This exception is raised, when the device can not be found or
     *             accessed
     */
    public Launchpad(final String device) throws FileNotFoundException {
        this(new File(device));
    }

    /**
     * Start the asynchronous {@link Thread} to read events of the Launchpad.
     */
    public synchronized void start() {
        thread.start();
    }

    /**
     * Verify if the asynchronous {@link Thread} to read is alive.
     *
     * @return True, if the asynchronous {@link Thread} to read is alive.
     */
    public boolean isAlive() {
        return thread.isAlive();
    }

    /**
     * Verify if the asynchronous {@link Thread} to read is interrupted.
     *
     * @return True, if the asynchronous {@link Thread} to read is interrupted.
     */
    public boolean isInterrupted() {
        return thread.isInterrupted();
    }

    /**
     * Adds a {@link LaunchpadHandler} to the {@link Launchpad} instance. The
     * {@link LaunchpadHandler} is now notified whenever a event is sent from
     * the Launchpad.
     *
     * @param handler
     *            The {@link LaunchpadHandler} to be informed of events from the
     *            Launchpad.
     */
    public void addHandler(final LaunchpadHandler handler) {
        this.reader.addHandler(handler);
    }

    /**
     * Send a {@link MidiCommand} to the Launchpad.
     *
     * @param command
     *            The {@link MidiCommand} to be sent to the Launchpad.
     * @throws IOException
     *             Information on why a {@link MidiCommand} could not be sent.
     */
    public void send(final MidiCommand command) throws IOException {
        device.writeByte(command.getCommandByte());
        device.write(command.getData());
    }

    /**
     * Switch a LED at a coordinates to off.
     *
     * @param x
     *            The x-coordinate of the LED to be switched off (0-16).
     * @param y
     *            The y-coordinate of the LED to be switched off (0-16).
     * @throws IOException
     *             Information on why a {@link MidiCommand} could not be sent.
     */
    public void off(final int x, final int y) throws IOException {
        send(new MidiCommand.Builder(Command.NOTE_OFF, Channel.C1)
                .setDataBytes(new byte[] { led(x, y), Color.OFF.getByte() })
                .toMidiCommand());
    }

    /**
     * Switch a LED at a coordinates to a {@link Color}.
     *
     * @param x
     *            The x-coordinate of the LED to be switched off (0-16).
     * @param y
     *            The y-coordinate of the LED to be switched off (0-16).
     * @throws IOException
     *             Information on why a {@link MidiCommand} could not be sent.
     */
    public void set(final int x, final int y, final Color c) throws IOException {
        send(new MidiCommand.Builder(Command.NOTE_ON, Channel.C1).setDataBytes(
                new byte[] { led(x, y), c.getByte() }).toMidiCommand());
    }

    private byte led(final int x, final int y) {
        if (x < 0 || x > 16 || y < 0 || y > 16) {
            throw new IllegalArgumentException(
                    "x and y may only be within 1-16");
        }
        return (byte) (0x10 * x + y);
    }
}
