package org.rjung.util.launchpad;


import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class ColorTest {

  @Test
  public void verifyColorCodes() {
    assertThat(Color.R0G0.getCode(), is(0x00));
    assertThat(Color.R1G0.getCode(), is(0x01));
    assertThat(Color.R2G0.getCode(), is(0x02));
    assertThat(Color.R3G0.getCode(), is(0x03));

    assertThat(Color.R0G1.getCode(), is(0x10));
    assertThat(Color.R1G1.getCode(), is(0x11));
    assertThat(Color.R2G1.getCode(), is(0x12));
    assertThat(Color.R3G1.getCode(), is(0x13));

    assertThat(Color.R0G2.getCode(), is(0x20));
    assertThat(Color.R1G2.getCode(), is(0x21));
    assertThat(Color.R2G2.getCode(), is(0x22));
    assertThat(Color.R3G2.getCode(), is(0x23));

    assertThat(Color.R0G3.getCode(), is(0x30));
    assertThat(Color.R1G3.getCode(), is(0x31));
    assertThat(Color.R2G3.getCode(), is(0x32));
    assertThat(Color.R3G3.getCode(), is(0x33));

    assertThat(Color.BLANK.getCode(), is(0x00));
    assertThat(Color.GREEN.getCode(), is(0x30));
    assertThat(Color.RED.getCode(), is(0x03));
    assertThat(Color.AMBER.getCode(), is(0x33));
  }
}
