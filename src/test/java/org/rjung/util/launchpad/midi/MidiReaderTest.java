package org.rjung.util.launchpad.midi;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rjung.util.launchpad.LaunchpadHandler;
import org.rjung.util.launchpad.MidiCommand;

public class MidiReaderTest {

  private static final int DEFAULT_TEST_SLEEP_IN_MS = 10;
  @Mock
  private RandomAccessFile device;
  @Mock
  private LaunchpadHandler handler;
  private MidiReader midiReader;
  private Thread thread;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(this);
    midiReader = new MidiReader(device);
    thread = new Thread(midiReader);
  }

  @Test
  public void testHandlesTwoByteCommand() throws InterruptedException, IOException {
    when(device.read()).thenReturn(256 + Command.NOTE_ON.getByte()).thenReturn(0).thenReturn(0);
    midiReader.addHandler(handler);
    thread.start();
    Thread.sleep(DEFAULT_TEST_SLEEP_IN_MS);
    verify(handler).recieve(new MidiCommand.Builder(Command.NOTE_ON, Channel.C1)
        .setDataBytes(new byte[] {0, 0}).toMidiCommand());
    thread.interrupt();
  }

  @Test
  public void testHandlesOneByteCommand() throws InterruptedException, IOException {
    when(device.read()).thenReturn(256 + Command.PROGRAM_CHANGE.getByte()).thenReturn(4);
    midiReader.addHandler(handler);
    thread.start();
    Thread.sleep(DEFAULT_TEST_SLEEP_IN_MS);
    verify(handler).recieve(new MidiCommand.Builder(Command.PROGRAM_CHANGE, Channel.C1)
        .setDataBytes(new byte[] {4}).toMidiCommand());
    thread.interrupt();
  }

  @Test
  public void testHandlesSysEx() throws InterruptedException, IOException {
    when(device.read()).thenReturn(256 + Command.SYS_EX.getByte()).thenReturn(4).thenReturn(1)
        .thenReturn(2).thenReturn(3).thenReturn(4);
    midiReader.addHandler(handler);
    thread.start();
    Thread.sleep(DEFAULT_TEST_SLEEP_IN_MS);
    verify(handler).recieve(new MidiCommand.Builder(Command.SYS_EX, Channel.C1)
        .setDataBytes(new byte[] {1, 2, 3, 4}).toMidiCommand());
    thread.interrupt();
  }

}
