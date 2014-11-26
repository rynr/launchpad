package org.rjung.utils.launchpad.handler;

import org.rjung.utils.launchpad.midi.Command;

public interface MidiHandler {

    public void recieve(Command command);
}
