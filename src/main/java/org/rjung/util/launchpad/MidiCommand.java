package org.rjung.util.launchpad;

import org.rjung.util.launchpad.midi.Channel;
import org.rjung.util.launchpad.midi.Command;

import java.util.Arrays;
import java.util.Objects;

/**
 * Abstraction of commands that can be sent to a Midi-device, or be received by
 * a Midi-device. Midi stands for Musical Instrument Digital Interface.
 *
 * A {@link MidiCommand} consists of a command-byte and optional some further
 * data bytes. The command-byte contains a command in the higher nibble (four
 * bytes) and a channel in the lower nibble (four bytes).
 */
public class MidiCommand {

    private final byte command;
    private final byte[] data;

    /**
     * Create a {@link MidiCommand} with a command-byte and optional data-bytes.
     *
     * @param command
     *            The command-byte of the {@link MidiCommand}.
     * @param data
     *            The optiona data-bytes of the {@link MidiCommand}.
     */
    public MidiCommand(final byte command, final byte[] data) {
        this.command = command;
        this.data = data.clone();
    }

    /**
     * Retrieve the command-byte of the {@link MidiCommand}.
     *
     * @return The command-byte of the {@link MidiCommand}.
     */
    public byte getCommandByte() {
        return this.command;
    }

    /**
     * Retrieve the {@link Command}-abstraction of the higher nibble (four
     * bytes) of the command-byte.
     *
     * @return The {@link Command}-abstraction of the higher nibble (four bytes)
     *         of the command-byte.
     */
    public Command getCommand() {
        return Command.getCommand((byte) (command & 0xf0));
    }

    /**
     * Retrieve the {@link Channel}-abstraction of the lower nibble (four bytes)
     * of the command-byte.
     *
     * @return The {@link Channel}-abstraction of the lower nibble (four bytes)
     *         of the command-byte.
     */
    public Channel getChannel() {
        return Channel.getChannel((byte) (command & 0x0f));
    }

    /**
     * Retrieve the optional data bytes of the {@link MidiCommand}.
     *
     * @return The optional data bytes of the {@link MidiCommand}.
     */
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

    /**
     * Builder for {@link MidiCommand}s.
     */
    public static class Builder {
        private byte statusbyte;
        private byte[] databytes;

        /**
         * Start the build with a {@link Command} and the {@link Channel}. For
         * Launchpad it's always the Channel 1.
         *
         * @param command
         *            The {@link Command} of the {@link MidiCommand}.
         * @param channel
         *            The {@link Channel} of the {@link MidiCommand}.
         */
        public Builder(final Command command, final Channel channel) {
            this.statusbyte = (byte) (command.getByte() ^ channel.getByte());
        }

        /**
         * Set the optional data-bytes of the {@link MidiCommand}.
         *
         * @param databytes
         *            The optional data-bytes of the {@link MidiCommand}.
         * @return The {@link Builder}.
         */
        public Builder setDataBytes(final byte[] databytes) {
            this.databytes = databytes.clone();
            return this;
        }

        /**
         * Retrieve the {@link MidiCommand} built with the {@link Builder}.
         *
         * @return The {@link MidiCommand} built with the {@link Builder}.
         */
        public MidiCommand toMidiCommand() {
            return new MidiCommand(statusbyte, databytes == null ? new byte[0]
                    : databytes);
        }
    }
}
