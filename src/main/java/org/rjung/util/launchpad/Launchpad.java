package org.rjung.util.launchpad;

import java.io.File;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Transmitter;

/**
 * The {@link Launchpad} provides access to a Launchpad via the midi-device.
 *
 * To get a notication when a button is pressed on the Launchpad you can add a
 * {@link LaunchpadReceiver} with the constructors {@link Launchpad#Launchpad(LaunchpadReceiver)}
 * and {@link Launchpad#Launchpad(Channel, LaunchpadReceiver)}.
 */
public class Launchpad implements Receiver {

  private final Channel channel;
  private final Transmitter transmitter;
  private final Receiver receiver;
  private final LaunchpadReceiver launchpadReceiver;

  /**
   * Use this constructor if you want to use the default implementation, and don't whant to receive
   * the event of a {@link Pad} being pressed. You probably have only one midi device, and the
   * default channel is {@link Channel#C1}.
   *
   * @throws MidiUnavailableException No midi device could be found, is your launchpad connected to
   *         your computer?
   */
  public Launchpad() throws MidiUnavailableException {
    this(Channel.C1);
  }

  /**
   * Use this constructor if you have configured your launchpad to receive on a specific midi
   * channel that is not the default {@link Channel#C1}. You will not receive any messages if a
   * {@link Pad} is beeing pressed.
   *
   * @param channel The {@link Channel} to send and receive data to and from.
   * @throws MidiUnavailableException No midi device could be found, is your launchpad connected to
   *         your computer?
   */
  public Launchpad(Channel channel) throws MidiUnavailableException {
    this(channel, null);
  }

  /**
   * This is the simpliest solution if you want to receive notifications if a {@link Pad} is being
   * pressed, but you still use the default midi channel ({@link Channel#C1}) of your launchpad.
   *
   * @param launchpadReceiver The {@link LaunchpadReceiver} implementation that will receive the
   *        information if a {@link Pad} is pressed.
   * @throws MidiUnavailableException No midi device could be found, is your launchpad connected to
   *         your computer?
   */
  public Launchpad(LaunchpadReceiver launchpadReceiver) throws MidiUnavailableException {
    this(Channel.C1, launchpadReceiver);
  }

  /**
   * This is the full configured solution if you want to receive notifications if a {@link Pad} is
   * being pressed, and want to configure the midi channel (default is {@link Channel#C1}) of your
   * launchpad.
   *
   * @param channel The {@link Channel} to send and receive data to and from.
   * @param launchpadReceiver The {@link LaunchpadReceiver} implementation that will receive the
   *        information if a {@link Pad} is pressed.
   * @throws MidiUnavailableException No midi device could be found, is your launchpad connected to
   *         your computer?
   */
  public Launchpad(Channel channel, LaunchpadReceiver launchpadReceiver)
      throws MidiUnavailableException {
    this.channel = channel;
    transmitter = MidiSystem.getTransmitter();
    receiver = MidiSystem.getReceiver();
    this.launchpadReceiver = launchpadReceiver;

    transmitter.setReceiver(this);
  }

  /**
   * To set the {@link Color} of a {@link Pad}, use this method.
   *
   * @param pad The {@link Pad} that you want to set to a {@link Color}.
   * @param color The {@link Color} that you want the {@link Pad} to be lit up.
   * @throws InvalidMidiDataException The communication failed.
   */
  public void set(final Pad pad, final Color color) throws InvalidMidiDataException {
    receiver.send(new ShortMessage(pad.getCommand(), this.channel.channelForSystem(), pad.getCode(),
        color.getCode()), -1);
  }

  /**
   * Please do not use this method. It's the internal implementation to receive midi commands.
   */
  // TODO hide this method from standard usage.
  @Override
  public void send(MidiMessage message, long timeStamp) {
    if (this.launchpadReceiver != null && message instanceof ShortMessage
        && (((ShortMessage) message).getChannel() == this.channel.channelForSystem())
        && (((ShortMessage) message).getCommand() == ShortMessage.NOTE_ON)
        && (((ShortMessage) message).getData2() > 0)) {
      this.launchpadReceiver.receive(Pad.findMidi(((ShortMessage) message).getData1()));
    }
  }

  /**
   * Before not using the {@link Launchpad} any more (for example when exiting the application),
   * please {@link #close()} the {@link Launchpad} to release resources bound by it.
   */
  @Override
  public void close() {
    this.receiver.close();
    this.transmitter.close();
  }
}
