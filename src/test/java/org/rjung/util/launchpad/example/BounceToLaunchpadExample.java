package org.rjung.util.launchpad.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.rjung.util.launchpad.Launchpad;
import org.rjung.util.launchpad.LaunchpadHandler;
import org.rjung.util.launchpad.MidiCommand;

public class BounceToLaunchpadExample implements Runnable {

    // Change this to your setup
    private static final String MIDI_DEVICE = "/dev/midi2";
    private static final Logger LOG = Logger.getLogger(BounceHandler.class
            .getName());

    public static void main(String[] args) throws FileNotFoundException {
        new BounceToLaunchpadExample().run();
    }

    class BounceHandler implements LaunchpadHandler {
        private Launchpad launchpad;

        public BounceHandler(Launchpad launchpad) {
            this.launchpad = launchpad;
        }

        @Override
        public void recieve(MidiCommand command) {
            try {
                LOG.log(Level.INFO, command.toString());
                launchpad.send(command);
            } catch (IOException e) {
                LOG.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }

    @Override
    public void run() {
        try {
            Launchpad launchpad = new Launchpad(MIDI_DEVICE);
            launchpad.addHandler(new BounceHandler(launchpad));
            launchpad.start();
        } catch (FileNotFoundException e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
