package org.rjung.util.launchpad;

import org.junit.Test;
import org.rjung.util.launchpad.midi.Channel;
import org.rjung.util.launchpad.midi.Command;

import static org.junit.Assert.assertEquals;

public class MidiCommandTest {

    @Test
    public void midiCommandBuilderDoesCreateCorrectStatusByte() {
        assertEquals((byte) 0x90, new MidiCommand.Builder(Command.NOTE_ON,
                Channel.C1).setDataBytes(new byte[] {}).toMidiCommand()
                .getCommand());
        assertEquals((byte) 0x81, new MidiCommand.Builder(Command.NOTE_OFF,
                Channel.C2).setDataBytes(new byte[] {}).toMidiCommand()
                .getCommand());
        assertEquals((byte) 0xa2, new MidiCommand.Builder(Command.AFTERTOUCH,
                Channel.C3).setDataBytes(new byte[] {}).toMidiCommand()
                .getCommand());
        assertEquals((byte) 0xb3, new MidiCommand.Builder(Command.CONTROL_CHANGE,
                Channel.C4).setDataBytes(new byte[] {}).toMidiCommand()
                .getCommand());
        assertEquals((byte) 0xc4, new MidiCommand.Builder(Command.PROGRAM_CHANGE,
                Channel.C5).setDataBytes(new byte[] {}).toMidiCommand()
                .getCommand());
        assertEquals((byte) 0xd5, new MidiCommand.Builder(Command.CHANNEL_AFTERTOUCH,
                Channel.C6).setDataBytes(new byte[] {}).toMidiCommand()
                .getCommand());
        assertEquals((byte) 0xe6, new MidiCommand.Builder(Command.PITCH_BEND,
                Channel.C7).setDataBytes(new byte[] {}).toMidiCommand()
                .getCommand());
        assertEquals((byte) 0xf7, new MidiCommand.Builder(Command.SYS_EX,
                Channel.C8).setDataBytes(new byte[] {}).toMidiCommand()
                .getCommand());
    }

}
