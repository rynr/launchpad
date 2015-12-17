package org.rjung.util.launchpad.midi;

public enum Command {
    NOTE_OFF((byte) 0x80), NOTE_ON((byte) 0x90), AFTERTOUCH((byte) 0xa0), CONTROL_CHANGE(
            (byte) 0xb0), PROGRAM_CHANGE((byte) 0xc0), CHANNEL_AFTERTOUCH(
            (byte) 0xd0), PITCH_BEND((byte) 0xe0), SYS_EX((byte) 0xf0);

    private byte commandByte;

    private Command(byte command) {
        this.commandByte = command;
    }

    public byte getByte() {
        return commandByte;
    }

    public static Command getCommand(byte b) {
        for (Command entry : values()) {
            if (entry.commandByte == b) {
                return entry;
            }
        }
        return null;
    }
}
