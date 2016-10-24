package org.rjung.util.launchpad;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.rjung.util.launchpad.midi.Channel;
import org.rjung.util.launchpad.midi.Command;

public class MidiCommandTest {

    @Test
    public void midiCommandBuilderDoesCreateCorrectStatusByte() {
        assertThat(new MidiCommand.Builder(Command.NOTE_ON,
                Channel.C1).setDataBytes(new byte[] {}).toMidiCommand()
                        .getCommandByte(),
                equalTo((byte) 0x90));
        assertThat(new MidiCommand.Builder(Command.NOTE_OFF,
                Channel.C2).setDataBytes(new byte[] {}).toMidiCommand()
                        .getCommandByte(),
                equalTo((byte) 0x81));
        assertThat(new MidiCommand.Builder(Command.AFTERTOUCH,
                Channel.C3).setDataBytes(new byte[] {}).toMidiCommand()
                        .getCommandByte(),
                equalTo((byte) 0xa2));
        assertThat(new MidiCommand.Builder(
                Command.CONTROL_CHANGE, Channel.C4).setDataBytes(new byte[] {})
                        .toMidiCommand().getCommandByte(),
                equalTo((byte) 0xb3));
        assertThat(new MidiCommand.Builder(
                Command.PROGRAM_CHANGE, Channel.C5).setDataBytes(new byte[] {})
                        .toMidiCommand().getCommandByte(),
                equalTo((byte) 0xc4));
        assertThat(
                new MidiCommand.Builder(Command.CHANNEL_AFTERTOUCH, Channel.C6)
                        .setDataBytes(new byte[] {}).toMidiCommand()
                        .getCommandByte(),
                equalTo((byte) 0xd5));
        assertThat(new MidiCommand.Builder(Command.PITCH_BEND,
                Channel.C7).setDataBytes(new byte[] {}).toMidiCommand()
                        .getCommandByte(),
                equalTo((byte) 0xe6));
        assertThat(new MidiCommand.Builder(Command.SYS_EX,
                Channel.C8).setDataBytes(new byte[] {}).toMidiCommand()
                        .getCommandByte(),
                equalTo((byte) 0xf7));
    }

    @Test
    public void midiCommandBuilderDoesCreateCorrectDataBytes() {
        assertThat(
                new MidiCommand.Builder(Command.SYS_EX, Channel.C8)
                        .setDataBytes(new byte[] { (byte) 0xab })
                        .toMidiCommand().getData(),
                equalTo(new byte[] { (byte) 0xab }));
    }

    @Test
    public void testMidiCommandIsExtractedCorrectly() {
        assertThat(new MidiCommand.Builder(Command.NOTE_ON, Channel.C10).toMidiCommand().getCommand(),
                equalTo(Command.NOTE_ON));
        assertThat(new MidiCommand.Builder(Command.SYS_EX, Channel.C5).toMidiCommand().getCommand(),
                equalTo(Command.SYS_EX));
    }

    @Test
    public void testMidiChannelIsExtractedCorrectly() {
        assertThat(new MidiCommand.Builder(Command.NOTE_ON, Channel.C10).toMidiCommand().getChannel(),
                equalTo(Channel.C10));
        assertThat(new MidiCommand.Builder(Command.SYS_EX, Channel.C5).toMidiCommand().getChannel(),
                equalTo(Channel.C5));
    }

    @Test
    public void testToStringFormat() {
        assertThat(new MidiCommand.Builder(Command.NOTE_ON, Channel.C1).toMidiCommand().toString(),
                equalTo("MIDI[90/NOTE_ON/C1]"));
        assertThat(
                new MidiCommand.Builder(Command.SYS_EX, Channel.C5)
                        .setDataBytes(new byte[] { 1, 2, 4, 8, 16, 32, 64, 127 }).toMidiCommand()
                        .toString(),
                equalTo("MIDI[f4/SYS_EX/C5, 01, 02, 04, 08, 10, 20, 40, 7f]"));
    }
}
