package org.rjung.util.launchpad;

/**
 * The {@link Color} describes any color that a {@link Pad} of a {@link Launchpad} could have.
 * Currently this library only supports the colors of a launchpad mini (as I don't have another one
 * to try).
 *
 * The colors are a combination of 4 different levels of red and green. They mix up for amber. So
 * these colors are available:
 *
 * <table summary="Overview of all Colors available">
 * <tr>
 * <td style="background-color:#000">R0G0 / BLANK</td>
 * <td style="background-color:#500">R1G0</td>
 * <td style="background-color:#a00">R2G0</td>
 * <td style="background-color:#f00">R3G0 / RED</td>
 * </tr>
 * <tr>
 * <td style="background-color:#050">R0G1</td>
 * <td style="background-color:#550">R1G1</td>
 * <td style="background-color:#a50">R2G1</td>
 * <td style="background-color:#f50">R3G1</td>
 * </tr>
 * <tr>
 * <td style="background-color:#0a0">R0G2</td>
 * <td style="background-color:#5a0">R1G2</td>
 * <td style="background-color:#aa0">R2G2</td>
 * <td style="background-color:#fa0">R3G2</td>
 * </tr>
 * <tr>
 * <td style="background-color:#0f0">R0G3 / GREEN</td>
 * <td style="background-color:#5f0">R1G3</td>
 * <td style="background-color:#af0">R2G3</td>
 * <td style="background-color:#ff0">R3G3 / AMBER</td>
 * </tr>
 * </table>
 *
 * Support for other launchpads will follow, if you can help me, you can find all information via
 * <a href="https://github.com/rynr/launchpad">github</a>.
 */
public enum Color {

  R0G0(0x00), R1G0(0x01), R2G0(0x02), R3G0(0x03), //
  R0G1(0x10), R1G1(0x11), R2G1(0x12), R3G1(0x13), //
  R0G2(0x20), R1G2(0x21), R2G2(0x22), R3G2(0x23), //
  R0G3(0x30), R1G3(0x31), R2G3(0x32), R3G3(0x33);

  /**
   * Alias for {@link #R0G0}
   */
  public static final Color BLANK = R0G0;
  /**
   * Alias for {@link #R0G3}
   */
  public static final Color GREEN = R0G3;
  /**
   * Alias for {@link #R3G0}
   */
  public static final Color RED = R3G0;
  /**
   * Alias for {@link #R3G3}
   */
  public static final Color AMBER = R3G3;

  private int code;

  private Color(int code) {
    this.code = code;
  }

  /**
   * Provide the code to be sent to the launchpad device.
   *
   * @return the code to be sent to the launchpad device
   */
  public int getCode() {
    return code;
  }
}
