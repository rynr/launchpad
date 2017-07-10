package org.rjung.util.launchpad;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class ChannelTest {

  @Test
  public void verifyChannelForSystem() {
    assertThat(Channel.C1.channelForSystem(), is(0));
    assertThat(Channel.C2.channelForSystem(), is(1));
    assertThat(Channel.C3.channelForSystem(), is(2));
    assertThat(Channel.C4.channelForSystem(), is(3));
    assertThat(Channel.C5.channelForSystem(), is(4));
    assertThat(Channel.C6.channelForSystem(), is(5));
    assertThat(Channel.C7.channelForSystem(), is(6));
    assertThat(Channel.C8.channelForSystem(), is(7));
    assertThat(Channel.C9.channelForSystem(), is(8));
    assertThat(Channel.C10.channelForSystem(), is(9));
    assertThat(Channel.C11.channelForSystem(), is(10));
    assertThat(Channel.C12.channelForSystem(), is(11));
    assertThat(Channel.C13.channelForSystem(), is(12));
    assertThat(Channel.C14.channelForSystem(), is(13));
    assertThat(Channel.C15.channelForSystem(), is(14));
    assertThat(Channel.C16.channelForSystem(), is(15));
  }
}
