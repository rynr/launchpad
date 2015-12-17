package org.rjung.util.launchpad;

import org.rjung.util.launchpad.midi.Channel;
import org.rjung.util.launchpad.midi.Command;

import java.util.Arrays;
import java.util.Objects;

public class MidiCommand {

    private final byte command;
    private final byte[] data;

    public MidiCommand(byte command, byte[] data) {
        this.command = command;
        this.data = data.clone();
    }

    public byte getCommandByte() {
        return this.command;
    }

    public Command getCommand() {
        return Command.getCommand((byte) (command & 0xf0));
    }

    public Channel getChannel() {
        return Channel.getChannel((byte) (command & 0x0f));
    }

    public byte[] getData() {
        return data.clone();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("[MIDI ");
        result.append(String.format("%02x", command));
        result.append("(" + getCommand() + "/" + getChannel() + ")");
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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        MidiCommand other = (MidiCommand) obj;
        return Objects.equals(this.command, other.command)
                && Arrays.equals(this.data, other.data);
    }

    public static class Builder {
        private byte statusbyte;
        private byte[] databytes;

        public Builder(Command command, Channel channel) {
            this.statusbyte = (byte) (command.getByte() ^ channel.getByte());
        }

        public Builder setDataBytes(byte[] databytes) {
            this.databytes = databytes.clone();
            return this;
        }

        public MidiCommand toMidiCommand() {
            return new MidiCommand(statusbyte, databytes == null ? new byte[0]
                    : databytes);
        }
    }
}
