Launchpad
=========

This library can be used to access a
[Novation Launchpad](http://uk.novationmusic.com/midi-controllers-digital-dj/launchpad)
from a java application.

Usage
-----

You can directly use it from maven central:
```xml
<dependency>
  <groupId>org.rjung.util</groupId>
  <artifactId>launchpad</artifactId>
  <version>1.0</version>
</dependency>
```

To send colors the the launchpad, the methods `set(pad, color)`:

```java
launchpad.set(Pad.A4, Color.RED);
launchpad.set(Pad.find(3, 1), Color.GREEN);
```

To retrieve messages, implement a
[LaunchpadReceiver](https://github.com/rynr/launchpad/blob/master/src/main/java/org/rjung/util/launchpad/LaunchpadReceiver.java)
and register it to the launchpad instance. The LaunchpadReceiver will now be
called always, when a Command is received.

```java
new Launchpad(new LaunchpadHandler() {
    public void recieve(MidiCommand command) {
        System.out.println(command);
    }
});
```

Example
-------

[![This is what you could build](https://img.youtube.com/vi/9cYpqWWpjjY/0.jpg)](https://www.youtube.com/watch?v=9cYpqWWpjjY)

There's also a rewritten version of the [Games of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) in the branch [life](https://github.com/rynr/launchpad/tree/life).

Build it yourself
-----------------

The build uses maven. You don't need to install anything besides a java 8 development kit (jdk8). Now just run:

```sh
./mvn clean package
```

The build jar can be found in the `target`-folder.

Info
----

 - [Info](https://rynr.github.io/launchpad/)
 - [Github](https://github.com/rynr/launchpad)
 - [Bugs](https://github.com/rynr/launchpad/issues)
 - [Sonar](https://sonarqube.com/dashboard/index?id=org.rjung.util%3Alaunchpad)
 - [![Join the chat at https://gitter.im/rynr/launchpad](https://badges.gitter.im/rynr/launchpad.svg)](https://gitter.im/rynr/launchpad)
 - [![Build Status](https://travis-ci.org/rynr/launchpad.svg?branch=master)](https://travis-ci.org/rynr/launchpad)
