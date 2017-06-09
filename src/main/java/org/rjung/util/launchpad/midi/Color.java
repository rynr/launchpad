package org.rjung.util.launchpad.midi;

import org.rjung.util.launchpad.Launchpad;

/**
 * The {@link Color} represents the default {@link Color}s that {@link Launchpad} supports to send
 * to a Launchpad.
 */
public enum Color {
  OFF(0x0C), //
  YELLOW(0x3E), YELLOW_FLASH(0x3A), //
  GREEN(0x3C), GREEN_LOW(0x1C), GREEN_FLASH(0x38), //
  RED(0x0F), RED_LOW(0x0D), RED_FLASH(0x0B), //
  AMBER(0x3F), AMBER_LOW(0x1D), AMBER_FLASH(0x3B);

  private byte code;

  private Color(final int code) {
    this.code = (byte) code;
  }

  /**
   * Retrieve the byte-code representing the {@link Color}.
   *
   * @return The byte-code representing the {@link Color}.
   */
  public byte getByte() {
    return code;
  }
}
