package org.rjung.util.launchpad;

/**
 * Usually you don't need to think about the {@link Channel}. A launchpad is usually configured to
 * use the default Midi-Channel 1. Only if you have configured yours differently, this is the
 * Channel you should give the {@link Launchpad#Launchpad(Channel)} or
 * {@link Launchpad#Launchpad(Channel, LaunchpadReceiver)} constructors.
 *
 * To find out more about midi-channels, checkout the
 * <a href="https://www.midi.org/specifications">midi specifications</a>.
 */
public enum Channel {

  C1(0), C2(1), C3(2), C4(3), C5(4), C6(5), C7(6), C8(7), //
  C9(8), C10(9), C11(10), C12(11), C13(12), C14(13), C15(14), C16(15);

  private final int systemChannel;

  Channel(int channel) {
    this.systemChannel = channel;
  }

  public int channelForSystem() {
    return this.systemChannel;
  }
}
