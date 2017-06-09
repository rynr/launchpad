package org.rjung.util.launchpad.midi;

import org.rjung.util.launchpad.MidiCommand;

/**
 * The {@link Command} defines the Commands defined by the Midi-definition (Music Instrument Digital
 * Interface).
 *
 * If you recieve a {@link MidiCommand} from the Launchpad, a {@link #NOTE_ON} represent that a
 * button was hit, {@link #NOTE_OFF} represents the release of a Button. Additional information on
 * which button triggered this in the data-bytes of the {@link MidiCommand}.
 */
public enum Command {
  NOTE_OFF((byte) 0x80), NOTE_ON((byte) 0x90), AFTERTOUCH((byte) 0xa0), CONTROL_CHANGE(
      (byte) 0xb0), PROGRAM_CHANGE((byte) 0xc0), CHANNEL_AFTERTOUCH(
          (byte) 0xd0), PITCH_BEND((byte) 0xe0), SYS_EX((byte) 0xf0);

  private byte commandByte;

  private Command(final byte command) {
    this.commandByte = command;
  }

  /**
   * Recieve the byte (upper 4 bytes) of the {@link Command}s representation.
   *
   * @return The byte (upper 4 bytes) of the {@link Command}s representation.
   */
  public byte getByte() {
    return commandByte;
  }

  /**
   * Retrieve the {@link Command} to a command-byte.
   *
   * @param command The command-byte.
   * @return The {@link Command} representing the command-byte.
   */
  public static Command getCommand(final byte command) {
    for (Command entry : values()) {
      if (entry.commandByte == command) {
        return entry;
      }
    }
    return null;
  }
}
