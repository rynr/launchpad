package org.rjung.utils.launchpad.midi;

public enum Channel {

	C1((byte) 0), C2((byte) 1), C3((byte) 2), C4((byte) 3), C5((byte) 4), C6(
			(byte) 5), C7((byte) 6), C8((byte) 7), C9((byte) 8), C10((byte) 9), C11(
			(byte) 10), C12((byte) 11), C13((byte) 12), C14((byte) 13), C15(
			(byte) 14), C16((byte) 15);

	private byte channel;

	private Channel(byte channel) {
		this.channel = channel;
	}

	public byte getByte() {
		return channel;
	}
}
