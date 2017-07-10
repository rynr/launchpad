package org.rjung.util.launchpad;

public enum Color {

  R0G0(0x00), R1G0(0x01), R2G0(0x02), R3G0(0x03), //
  R0G1(0x10), R1G1(0x11), R2G1(0x12), R3G1(0x13), //
  R0G2(0x20), R1G2(0x21), R2G2(0x22), R3G2(0x23), //
  R0G3(0x30), R1G3(0x31), R2G3(0x32), R3G3(0x33);

  public static final Color BLANK = R0G0;
  public static final Color GREEN = R0G3;
  public static final Color RED = R3G0;
  public static final Color AMBER = R3G3;

  private int code;

  private Color(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
