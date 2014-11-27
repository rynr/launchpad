launchpad
=========

This library can be used to access a [Novation Launchpad](http://uk.novationmusic.com/midi-controllers-digital-dj/launchpad)
from a unix machine. To connect, a block-device is used.

Currently the library cannot be used, it will only show the data received by the
launchpad.

usage
-----

You can find a example receiving and sending data in
[BounceToLaunchpadExample.java](https://github.com/rynr/launchpad/blob/master/src/test/java/org/rjung/utils/launchpad/example/BounceToLaunchpadExample.java)
in the test-directory.

To retrieve messages, implement a
[LaunchpadHandler](https://github.com/rynr/launchpad/blob/master/src/main/java/org/rjung/utils/launchpad/LaunchpadHandler.java)
and register it to the launchpad instance. The LaunchpadHandler will now be
called always, when a Command is received.

```java
    launchpad.addHandler(new LaunchpadHandler() {
        public void recieve(MidiCommand command) {
            System.out.println(command);
        }
    });
```

To send Commands, you can use the methods `off(x, y)` or `set(x, y, color)`.

```java
    launchpad.off(1, 2);
    launchpad.set(2, 3, Color.RED);
```

Info
----

 - [Info](https://rynr.github.io/launchpad/)
 - [Github](https://github.com/rynr/launchpad)
 - [Bugs](https://github.com/rynr/launchpad/issues)
 - [![Build Status](https://travis-ci.org/rynr/launchpad.svg?branch=master)](https://travis-ci.org/rynr/launchpad)


