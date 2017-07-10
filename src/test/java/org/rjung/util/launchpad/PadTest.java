package org.rjung.util.launchpad;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Arrays;

import javax.sound.midi.ShortMessage;

import org.junit.Test;

public class PadTest {

  @Test
  public void verifyCode() {
    assertThat(Pad.A1.getCode(), is(0x00));
    assertThat(Pad.A2.getCode(), is(0x01));
    assertThat(Pad.A3.getCode(), is(0x02));
    assertThat(Pad.A4.getCode(), is(0x03));
    assertThat(Pad.A5.getCode(), is(0x04));
    assertThat(Pad.A6.getCode(), is(0x05));
    assertThat(Pad.A7.getCode(), is(0x06));
    assertThat(Pad.A8.getCode(), is(0x07));

    assertThat(Pad.B1.getCode(), is(0x10));
    assertThat(Pad.B2.getCode(), is(0x11));
    assertThat(Pad.B3.getCode(), is(0x12));
    assertThat(Pad.B4.getCode(), is(0x13));
    assertThat(Pad.B5.getCode(), is(0x14));
    assertThat(Pad.B6.getCode(), is(0x15));
    assertThat(Pad.B7.getCode(), is(0x16));
    assertThat(Pad.B8.getCode(), is(0x17));

    assertThat(Pad.C1.getCode(), is(0x20));
    assertThat(Pad.C2.getCode(), is(0x21));
    assertThat(Pad.C3.getCode(), is(0x22));
    assertThat(Pad.C4.getCode(), is(0x23));
    assertThat(Pad.C5.getCode(), is(0x24));
    assertThat(Pad.C6.getCode(), is(0x25));
    assertThat(Pad.C7.getCode(), is(0x26));
    assertThat(Pad.C8.getCode(), is(0x27));

    assertThat(Pad.D1.getCode(), is(0x30));
    assertThat(Pad.D2.getCode(), is(0x31));
    assertThat(Pad.D3.getCode(), is(0x32));
    assertThat(Pad.D4.getCode(), is(0x33));
    assertThat(Pad.D5.getCode(), is(0x34));
    assertThat(Pad.D6.getCode(), is(0x35));
    assertThat(Pad.D7.getCode(), is(0x36));
    assertThat(Pad.D8.getCode(), is(0x37));

    assertThat(Pad.E1.getCode(), is(0x40));
    assertThat(Pad.E2.getCode(), is(0x41));
    assertThat(Pad.E3.getCode(), is(0x42));
    assertThat(Pad.E4.getCode(), is(0x43));
    assertThat(Pad.E5.getCode(), is(0x44));
    assertThat(Pad.E6.getCode(), is(0x45));
    assertThat(Pad.E7.getCode(), is(0x46));
    assertThat(Pad.E8.getCode(), is(0x47));

    assertThat(Pad.F1.getCode(), is(0x50));
    assertThat(Pad.F2.getCode(), is(0x51));
    assertThat(Pad.F3.getCode(), is(0x52));
    assertThat(Pad.F4.getCode(), is(0x53));
    assertThat(Pad.F5.getCode(), is(0x54));
    assertThat(Pad.F6.getCode(), is(0x55));
    assertThat(Pad.F7.getCode(), is(0x56));
    assertThat(Pad.F8.getCode(), is(0x57));

    assertThat(Pad.G1.getCode(), is(0x60));
    assertThat(Pad.G2.getCode(), is(0x61));
    assertThat(Pad.G3.getCode(), is(0x62));
    assertThat(Pad.G4.getCode(), is(0x63));
    assertThat(Pad.G5.getCode(), is(0x64));
    assertThat(Pad.G6.getCode(), is(0x65));
    assertThat(Pad.G7.getCode(), is(0x66));
    assertThat(Pad.G8.getCode(), is(0x67));

    assertThat(Pad.H1.getCode(), is(0x70));
    assertThat(Pad.H2.getCode(), is(0x71));
    assertThat(Pad.H3.getCode(), is(0x72));
    assertThat(Pad.H4.getCode(), is(0x73));
    assertThat(Pad.H5.getCode(), is(0x74));
    assertThat(Pad.H6.getCode(), is(0x75));
    assertThat(Pad.H7.getCode(), is(0x76));
    assertThat(Pad.H8.getCode(), is(0x77));

    assertThat(Pad.A.getCode(), is(0x08));
    assertThat(Pad.B.getCode(), is(0x18));
    assertThat(Pad.C.getCode(), is(0x28));
    assertThat(Pad.D.getCode(), is(0x38));
    assertThat(Pad.E.getCode(), is(0x48));
    assertThat(Pad.F.getCode(), is(0x58));
    assertThat(Pad.G.getCode(), is(0x68));
    assertThat(Pad.H.getCode(), is(0x78));

    assertThat(Pad.CON1.getCode(), is(0x68));
    assertThat(Pad.CON2.getCode(), is(0x69));
    assertThat(Pad.CON3.getCode(), is(0x6a));
    assertThat(Pad.CON4.getCode(), is(0x6b));
    assertThat(Pad.CON5.getCode(), is(0x6c));
    assertThat(Pad.CON6.getCode(), is(0x6d));
    assertThat(Pad.CON7.getCode(), is(0x6e));
    assertThat(Pad.CON8.getCode(), is(0x6f));
  }

  @Test
  public void verifyCommand() {
    for (Pad pad : Arrays.asList( //
        Pad.A1, Pad.A2, Pad.A3, Pad.A4, Pad.A5, Pad.A6, Pad.A7, Pad.A8, //
        Pad.B1, Pad.B2, Pad.B3, Pad.B4, Pad.B5, Pad.B6, Pad.B7, Pad.B8, //
        Pad.C1, Pad.C2, Pad.C3, Pad.C4, Pad.C5, Pad.C6, Pad.C7, Pad.C8, //
        Pad.D1, Pad.D2, Pad.D3, Pad.D4, Pad.D5, Pad.D6, Pad.D7, Pad.D8, //
        Pad.E1, Pad.E2, Pad.E3, Pad.E4, Pad.E5, Pad.E6, Pad.E7, Pad.E8, //
        Pad.F1, Pad.F2, Pad.F3, Pad.F4, Pad.F5, Pad.F6, Pad.F7, Pad.F8, //
        Pad.G1, Pad.G2, Pad.G3, Pad.G4, Pad.G5, Pad.G6, Pad.G7, Pad.G8, //
        Pad.H1, Pad.H2, Pad.H3, Pad.H4, Pad.H5, Pad.H6, Pad.H7, Pad.H8, //
        Pad.A, Pad.B, Pad.C, Pad.D, Pad.E, Pad.F, Pad.G, Pad.H)) {
      assertThat(pad.getCommand(), is(ShortMessage.NOTE_ON));
    }

    for (Pad pad : Arrays.asList( //
        Pad.CON1, Pad.CON2, Pad.CON3, Pad.CON4, Pad.CON5, Pad.CON6, Pad.CON7, Pad.CON8)) {
      assertThat(pad.getCommand(), is(ShortMessage.CONTROL_CHANGE));
    }
  }

  @Test
  public void verifyPadsAreFoundByCoordinate() {
    for (Pad pad : Pad.values()) {
      // Do not tests CON?-Pads
      if (pad.getCommand() == ShortMessage.NOTE_ON) {
        assertThat(Pad.find(pad.getX(), pad.getY()), is(pad));
      }
    }
  }

  @Test
  public void verifyFindByMidiCode() {
    for (Pad pad : Arrays.asList( //
        Pad.A1, Pad.A2, Pad.A3, Pad.A4, Pad.A5, Pad.A6, Pad.A7, Pad.A8, //
        Pad.B1, Pad.B2, Pad.B3, Pad.B4, Pad.B5, Pad.B6, Pad.B7, Pad.B8, //
        Pad.C1, Pad.C2, Pad.C3, Pad.C4, Pad.C5, Pad.C6, Pad.C7, Pad.C8, //
        Pad.D1, Pad.D2, Pad.D3, Pad.D4, Pad.D5, Pad.D6, Pad.D7, Pad.D8, //
        Pad.E1, Pad.E2, Pad.E3, Pad.E4, Pad.E5, Pad.E6, Pad.E7, Pad.E8, //
        Pad.F1, Pad.F2, Pad.F3, Pad.F4, Pad.F5, Pad.F6, Pad.F7, Pad.F8, //
        Pad.G1, Pad.G2, Pad.G3, Pad.G4, Pad.G5, Pad.G6, Pad.G7, Pad.G8, //
        Pad.H1, Pad.H2, Pad.H3, Pad.H4, Pad.H5, Pad.H6, Pad.H7, Pad.H8, //
        Pad.A, Pad.B, Pad.C, Pad.D, Pad.E, Pad.F, Pad.G, Pad.H)) {
      assertThat(Pad.findMidi(pad.getCode()), is(pad));
    }
  }
}
