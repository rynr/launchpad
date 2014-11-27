package org.rjung.util.launchpad;

import org.rjung.util.launchpad.midi.Channel;
import org.rjung.util.launchpad.midi.Color;
import org.rjung.util.launchpad.midi.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashSet;
import java.util.Set;

public class Launchpad {

	private static final String FILE_ACCESS_MODE = "rw";

	private static final Logger LOG = LoggerFactory.getLogger(Launchpad.class);

	private RandomAccessFile device;

	private Thread thread;

	private Reader reader;

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
		this.reader = new Reader(this.device);
		this.thread = new Thread(reader);
		thread.start();
	}

	public Launchpad(String device) throws FileNotFoundException {
		this(new File(device));
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

	class Reader implements Runnable {
		private RandomAccessFile device;
		private Set<LaunchpadHandler> handlers;

		public Reader(RandomAccessFile device) {
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
						handle(new MidiCommand(command,
								getDataForCommand(command)));
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
}
