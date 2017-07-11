package org.rjung.util.launchpad.example.life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

import org.rjung.util.launchpad.Launchpad;
import org.rjung.util.launchpad.LaunchpadReceiver;
import org.rjung.util.launchpad.Pad;

public class World implements LaunchpadReceiver {

  private final List<Spot> spots;
  private final Launchpad lp;

  public World() throws MidiUnavailableException {
    this.lp = new Launchpad(this);
    spots = new ArrayList<>();
    spots.add(new Spot(Pad.CON1));
    spots.add(new Spot(Pad.CON2));
    spots.add(new Spot(Pad.CON3));
    spots.add(new Spot(Pad.CON4));
    spots.add(new Spot(Pad.CON5));
    spots.add(new Spot(Pad.CON6));
    spots.add(new Spot(Pad.CON7));
    spots.add(new Spot(Pad.CON8));
    spots.add(new Spot(null));

    spots.add(new Spot(Pad.A1));
    spots.add(new Spot(Pad.A2));
    spots.add(new Spot(Pad.A3));
    spots.add(new Spot(Pad.A4));
    spots.add(new Spot(Pad.A5));
    spots.add(new Spot(Pad.A6));
    spots.add(new Spot(Pad.A7));
    spots.add(new Spot(Pad.A8));
    spots.add(new Spot(Pad.A));

    spots.add(new Spot(Pad.B1));
    spots.add(new Spot(Pad.B2));
    spots.add(new Spot(Pad.B3));
    spots.add(new Spot(Pad.B4));
    spots.add(new Spot(Pad.B5));
    spots.add(new Spot(Pad.B6));
    spots.add(new Spot(Pad.B7));
    spots.add(new Spot(Pad.B8));
    spots.add(new Spot(Pad.B));

    spots.add(new Spot(Pad.C1));
    spots.add(new Spot(Pad.C2));
    spots.add(new Spot(Pad.C3));
    spots.add(new Spot(Pad.C4));
    spots.add(new Spot(Pad.C5));
    spots.add(new Spot(Pad.C6));
    spots.add(new Spot(Pad.C7));
    spots.add(new Spot(Pad.C8));
    spots.add(new Spot(Pad.C));

    spots.add(new Spot(Pad.D1));
    spots.add(new Spot(Pad.D2));
    spots.add(new Spot(Pad.D3));
    spots.add(new Spot(Pad.D4));
    spots.add(new Spot(Pad.D5));
    spots.add(new Spot(Pad.D6));
    spots.add(new Spot(Pad.D7));
    spots.add(new Spot(Pad.D8));
    spots.add(new Spot(Pad.D));

    spots.add(new Spot(Pad.E1));
    spots.add(new Spot(Pad.E2));
    spots.add(new Spot(Pad.E3));
    spots.add(new Spot(Pad.E4));
    spots.add(new Spot(Pad.E5));
    spots.add(new Spot(Pad.E6));
    spots.add(new Spot(Pad.E7));
    spots.add(new Spot(Pad.E8));
    spots.add(new Spot(Pad.E));

    spots.add(new Spot(Pad.F1));
    spots.add(new Spot(Pad.F2));
    spots.add(new Spot(Pad.F3));
    spots.add(new Spot(Pad.F4));
    spots.add(new Spot(Pad.F5));
    spots.add(new Spot(Pad.F6));
    spots.add(new Spot(Pad.F7));
    spots.add(new Spot(Pad.F8));
    spots.add(new Spot(Pad.F));

    spots.add(new Spot(Pad.G1));
    spots.add(new Spot(Pad.G2));
    spots.add(new Spot(Pad.G3));
    spots.add(new Spot(Pad.G4));
    spots.add(new Spot(Pad.G5));
    spots.add(new Spot(Pad.G6));
    spots.add(new Spot(Pad.G7));
    spots.add(new Spot(Pad.G8));
    spots.add(new Spot(Pad.G));

    spots.add(new Spot(Pad.H1));
    spots.add(new Spot(Pad.H2));
    spots.add(new Spot(Pad.H3));
    spots.add(new Spot(Pad.H4));
    spots.add(new Spot(Pad.H5));
    spots.add(new Spot(Pad.H6));
    spots.add(new Spot(Pad.H7));
    spots.add(new Spot(Pad.H8));
    spots.add(new Spot(Pad.H));

    int size = spots.size();
    for (int i = 0; i < size; i++) {
      Spot spot = spots.get(i);
      for (int offset : Arrays.asList(1, 8, 9, 10)) {
        spot.addNeighbor(spots.get(i - offset < 0 ? i + size - offset : i - offset));
        spot.addNeighbor(spots.get(i + offset >= size ? i - size + offset : i + offset));
      }
    }
  }

  public void tick() throws InvalidMidiDataException {
    for (Spot spot : spots) {
      spot.tick(lp);
    }
  }

  public void prepare() {
    for (Spot spot : spots) {
      spot.calculateNext();
    }
  }

  @Override
  @SuppressWarnings("squid:S00112")
  public void receive(Pad arg0) {
    try {
      for (Spot spot : spots) {
        spot.setAlive(lp, arg0);
      }
    } catch (InvalidMidiDataException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

}
