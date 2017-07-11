package org.rjung.util.launchpad.example.life;

import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.InvalidMidiDataException;

import org.rjung.util.launchpad.Color;
import org.rjung.util.launchpad.Launchpad;
import org.rjung.util.launchpad.Pad;

public class Spot {

  private final Pad pad;
  private final List<Spot> neighbors;
  private boolean alive;
  private boolean aliveShortly;

  public Spot(final Pad pad) {
    this.neighbors = new ArrayList<>();
    this.pad = pad;
  }

  public void addNeighbor(Spot neighbor) {
    this.neighbors.add(neighbor);
  }

  public boolean isAlive() {
    return alive;
  }

  public void calculateNext() {
    long aliveNeighbors = neighbors.stream().filter(n -> n.isAlive()).count();
    if (alive) {
      aliveShortly = aliveNeighbors == 2 || aliveNeighbors == 3;
    } else {
      aliveShortly = aliveNeighbors == 3;
    }
  }

  public void tick(Launchpad lp) throws InvalidMidiDataException {
    if (pad != null) {
      lp.set(pad,
          aliveShortly ? (alive ? Color.GREEN : Color.AMBER) : (alive ? Color.R1G1 : Color.BLANK));
    }
    alive = aliveShortly;
  }

  public void setAlive(Launchpad lp, Pad pad) throws InvalidMidiDataException {
    if (pad.equals(this.pad)) {
      lp.set(pad, Color.RED);
      this.alive = true;
      this.aliveShortly = true;
    }
  }
}
