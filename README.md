launchpad-life
==============

Proof of concept implementation of the
[Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life) to run on
a [Launchpad](https://global.novationmusic.com/launch/launchpad) with the
[launchpad](https://github.com/rynr/launchpad) library.

Colors
------

Besides of the default colors for alive (green) and dead (blank), I added two
further colors (looks better).  
When putting a pad to life manually, the color is set to red.  
If a pad comes to life by the surrounding pads, it first turns amber and then
green.  
When duying, a pad takes a darker amber color before then turning to blank.

You can see a previous version running on youtube:  
[![This is what you could build](https://img.youtube.com/vi/9cYpqWWpjjY/0.jpg)](https://www.youtube.com/watch?v=9cYpqWWpjjY)
