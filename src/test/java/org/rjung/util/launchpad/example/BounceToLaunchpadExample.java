package org.rjung.util.launchpad.example;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.rjung.util.launchpad.Launchpad;
import org.rjung.util.launchpad.LaunchpadHandler;
import org.rjung.util.launchpad.MidiCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BounceToLaunchpadExample implements Runnable {

	// Change this to your setup
	private static final String MIDI_DEVICE = "/dev/midi2";
	private static final Logger LOG = LoggerFactory
			.getLogger(BounceHandler.class);

	public static void main(String[] args) throws FileNotFoundException {
		new BounceToLaunchpadExample().run();
	}

	class BounceHandler implements LaunchpadHandler {
		private Launchpad launchpad;

		public BounceHandler(Launchpad launchpad) {
			this.launchpad = launchpad;
		}

		public void recieve(MidiCommand command) {
			try {
				launchpad.send(command);
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}
	}

	public void run() {
		try {
			Launchpad launchpad = new Launchpad(MIDI_DEVICE);
			launchpad.addHandler(new BounceHandler(launchpad));
		} catch (FileNotFoundException e) {
			LOG.error(e.getMessage(), e);
		}
	}
}
