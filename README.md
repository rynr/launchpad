launchpad
=========

This library can be used to access a [Novation Launchpad](http://uk.novationmusic.com/midi-controllers-digital-dj/launchpad)
from a linux/unix machine.

To connect, a block-device is used.

usage
-----

You can directly use it from maven central:
```xml
<dependency>
  <groupId>org.rjung.util</groupId>
  <artifactId>launchpad</artifactId>
  <version>0.4</version>
</dependency>
```

You can find a example receiving and sending data in
[BounceToLaunchpadExample.java](https://github.com/rynr/launchpad/blob/master/src/test/java/org/rjung/util/launchpad/example/BounceToLaunchpadExample.java)
in the test-directory. I started some projects at https://github.com/rynr/jenkins-launchpad and https://rynr.github.io/launch-tab.

To retrieve messages, implement a
[LaunchpadHandler](https://github.com/rynr/launchpad/blob/master/src/main/java/org/rjung/util/launchpad/LaunchpadHandler.java)
and register it to the launchpad instance. The LaunchpadHandler will now be
called always, when a Command is received.

```java
launchpad.addHandler(new LaunchpadHandler() {
    public void recieve(MidiCommand command) {
        System.out.println(command);
    }
});
launchpad.start();
```

To send Commands, you can use the methods `off(x, y)` or `set(x, y, color)`.

```java
launchpad.off(1, 2);
launchpad.set(2, 3, Color.RED);
```

[![This is what you could build](https://img.youtube.com/vi/9cYpqWWpjjY/0.jpg)](https://www.youtube.com/watch?v=9cYpqWWpjjY)

Info
----

 - [Info](https://rynr.github.io/launchpad/)
 - [Github](https://github.com/rynr/launchpad)
 - [Bugs](https://github.com/rynr/launchpad/issues)
 - [![Build Status](https://travis-ci.org/rynr/launchpad.svg?branch=master)](https://travis-ci.org/rynr/launchpad)


