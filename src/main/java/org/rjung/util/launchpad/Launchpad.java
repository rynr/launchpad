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
 * The {@link Launchpad} provides access to a Launchpad via the midi-device. To create an instance
 * provide the device-file as a {@link File} or {@link String} with the path. As this requires a
 * read-thread, you need to {@link #start()} the {@link Launchpad} after you created it.
 *
 * To get a notication when a button is pressed on the Launchpad you can add a
 * {@link LaunchpadHandler} with {@link #addHandler(LaunchpadHandler)} (see {@link LaunchpadHandler}
 * on more information on building a {@link LaunchpadHandler}.
 */
public class Launchpad implements Receiver {

  private final Channel channel;
  private final Transmitter transmitter;
  private final Receiver receiver;
  private final LaunchpadReceiver launchpadReceiver;

  public Launchpad() throws MidiUnavailableException {
    this(Channel.C1);
  }

  public Launchpad(Channel channel) throws MidiUnavailableException {
    this(channel, null);
  }

  public Launchpad(LaunchpadReceiver launchpadReceiver) throws MidiUnavailableException {
    this(Channel.C1, launchpadReceiver);
  }

  public Launchpad(Channel channel, LaunchpadReceiver launchpadReceiver)
      throws MidiUnavailableException {
    this.channel = channel;
    transmitter = MidiSystem.getTransmitter();
    receiver = MidiSystem.getReceiver();
    this.launchpadReceiver = launchpadReceiver;

    transmitter.setReceiver(this);
  }

  public void set(final Pad pad, final Color color) throws InvalidMidiDataException {
    receiver.send(new ShortMessage(pad.getCommand(), this.channel.channelForSystem(), pad.getCode(),
        color.getCode()), -1);
  }

  @Override
  public void send(MidiMessage message, long timeStamp) {
    if (this.launchpadReceiver != null && message instanceof ShortMessage
        && (((ShortMessage) message).getChannel() == this.channel.channelForSystem())
        && (((ShortMessage) message).getCommand() == ShortMessage.NOTE_ON)
        && (((ShortMessage) message).getData2() > 0)) {
      this.launchpadReceiver.receive(Pad.findMidi(((ShortMessage) message).getData1()));
    }
  }

  @Override
  public void close() {
    this.receiver.close();
    this.transmitter.close();
  }
}
