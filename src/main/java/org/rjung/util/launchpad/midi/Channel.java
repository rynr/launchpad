package org.rjung.util.launchpad.midi;

/**
 * Midi (Music Instrument Digital Interface) toes define 16 different {@link Channel}s that can be
 * used. to send music informations. This represents the 16 {@link Channel}s from {@link #C1} to
 * {@link #C16}.
 */
public enum Channel {

  C1((byte) 0), C2((byte) 1), C3((byte) 2), C4((byte) 3), C5((byte) 4), C6((byte) 5), C7(
      (byte) 6), C8((byte) 7), C9((byte) 8), C10((byte) 9), C11((byte) 10), C12(
          (byte) 11), C13((byte) 12), C14((byte) 13), C15((byte) 14), C16((byte) 15);

  private byte channelByte;

  private Channel(final byte channel) {
    this.channelByte = channel;
  }

  /**
   * Recieve the byte representation of the {@link Channel}.
   *
   * @return The byte representation of the {@link Channel}.
   */
  public byte getByte() {
    return channelByte;
  }

  /**
   * Retrieve the {@link Channel} of the channel-bytes.
   *
   * @param channel The {@link Channel}-byte value.
   * @return The {@link Channel} representing the {@link Channel}-byte value.
   */
  public static Channel getChannel(final byte channel) {
    for (Channel entry : values()) {
      if (entry.channelByte == channel) {
        return entry;
      }
    }
    return null;
  }
}
