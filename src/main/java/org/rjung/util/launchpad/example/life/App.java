package org.rjung.util.launchpad.example.life;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class App implements Runnable {
  private World world;
  private boolean running = true;

  public static void main(String[] args) throws MidiUnavailableException {
    new App().run();
  }

  public App() throws MidiUnavailableException {
    world = new World();
  }

  public void run() {
    while (running) {
      try {
        world.tick();
        world.prepare();
        Thread.sleep(300);
      } catch (InvalidMidiDataException | InterruptedException e) {
        System.err.println(e.getMessage());
        this.running = false;
      }
    }
  }

}
