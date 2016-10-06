package org.rjung.util.launchpad;

/**
 * To receive notifications when a button is pressed on the Launchpad you need
 * to register a {@link LaunchpadHandler} to the {@link Launchpad}.
 *
 * When a {@link LaunchpadHandler} is registered to the {@link Launchpad}, the
 * method {@link #recieve(MidiCommand)} will always be called when a event is
 * recieved.
 */
public interface LaunchpadHandler {

    /**
     * Implement this message to react on any event from the Launchpad. The
     * information on the event is encoded in a {@link MidiCommand}.
     *
     * @param command
     *            The {@link MidiCommand} of the event recieved from the
     *            Launchpad.
     */
    void recieve(final MidiCommand command);
}
