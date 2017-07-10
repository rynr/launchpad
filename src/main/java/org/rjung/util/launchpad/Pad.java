package org.rjung.util.launchpad;

import java.util.Arrays;

import javax.sound.midi.ShortMessage;

public enum Pad {

  CON1(6, 8, ShortMessage.CONTROL_CHANGE), CON2(6, 9, ShortMessage.CONTROL_CHANGE), //
  CON3(6, 10, ShortMessage.CONTROL_CHANGE), CON4(6, 11, ShortMessage.CONTROL_CHANGE), //
  CON5(6, 12, ShortMessage.CONTROL_CHANGE), CON6(6, 13, ShortMessage.CONTROL_CHANGE), //
  CON7(6, 14, ShortMessage.CONTROL_CHANGE), CON8(6, 15, ShortMessage.CONTROL_CHANGE), //
  A1(0, 0), A2(0, 1), A3(0, 2), A4(0, 3), A5(0, 4), A6(0, 5), A7(0, 6), A8(0, 7), A(0, 8), //
  B1(1, 0), B2(1, 1), B3(1, 2), B4(1, 3), B5(1, 4), B6(1, 5), B7(1, 6), B8(1, 7), B(1, 8), //
  C1(2, 0), C2(2, 1), C3(2, 2), C4(2, 3), C5(2, 4), C6(2, 5), C7(2, 6), C8(2, 7), C(2, 8), //
  D1(3, 0), D2(3, 1), D3(3, 2), D4(3, 3), D5(3, 4), D6(3, 5), D7(3, 6), D8(3, 7), D(3, 8), //
  E1(4, 0), E2(4, 1), E3(4, 2), E4(4, 3), E5(4, 4), E6(4, 5), E7(4, 6), E8(4, 7), E(4, 8), //
  F1(5, 0), F2(5, 1), F3(5, 2), F4(5, 3), F5(5, 4), F6(5, 5), F7(5, 6), F8(5, 7), F(5, 8), //
  G1(6, 0), G2(6, 1), G3(6, 2), G4(6, 3), G5(6, 4), G6(6, 5), G7(6, 6), G8(6, 7), G(6, 8), //
  H1(7, 0), H2(7, 1), H3(7, 2), H4(7, 3), H5(7, 4), H6(7, 5), H7(7, 6), H8(7, 7), H(7, 8);

  private final int x;
  private final int y;
  private final int command;

  private Pad(int x, int y) {
    this(x, y, ShortMessage.NOTE_ON);
  }

  private Pad(int x, int y, int command) {
    this.x = x;
    this.y = y;
    this.command = command;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getCode() {
    return x * 16 + y;
  }

  public int getCommand() {
    return command;
  }

  public static Pad find(int x, int y) {
    return Arrays.stream(values())
        .filter(p -> p.command == ShortMessage.NOTE_ON && p.x == x && p.y == y).findFirst()
        .orElse(null);
  }

  public static Pad findMidi(int midiByte) {
    return find((midiByte & 0xf0) >> 4, midiByte & 0x0f);
  }
}
