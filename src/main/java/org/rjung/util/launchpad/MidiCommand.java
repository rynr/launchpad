package org.rjung.util.launchpad;

import java.util.Arrays;

import org.rjung.util.launchpad.midi.Channel;
import org.rjung.util.launchpad.midi.Command;

public class MidiCommand {

	private byte command;
	private byte[] data;

	public MidiCommand(byte command, byte[] data) {
		this.command = command;
		this.data = data;
	}

	public byte getCommand() {
		return command;
	}

	public byte[] getData() {
		return data;
	}

	public byte getChannel() {
		return (byte) (command & 0x0f);
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("[MIDI ");
		result.append(String.format("%02x", command));
		for (int i = 0; i < data.length; i++) {
			result.append(", ");
			result.append(String.format("%02x", data[i]));
		}
		result.append("]");
		return result.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + command;
		result = prime * result + Arrays.hashCode(data);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MidiCommand other = (MidiCommand) obj;
		if (command != other.command)
			return false;
		if (!Arrays.equals(data, other.data))
			return false;
		return true;
	}

	public static class Builder {
		private byte statusbyte;
		private byte[] databytes;

		public Builder(Command command, Channel channel) {
			this.statusbyte = (byte) (command.getByte() & channel.getByte());
		}

		public Builder setDataBytes(byte[] databytes) {
			this.databytes = databytes;
			return this;
		}

		public MidiCommand toMidiCommand() {
			return new MidiCommand(statusbyte, databytes);
		}
	}
}
