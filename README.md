# Hi, I'm Liz!
I’m a senior at Binghamton University studying Computer Science. Here are some cool things I've done!
- [Video Games](#video-games) <br/>
    - Curious Cats (2D point-and-click) <br/>
    - Little Trouble (2D platformer) <br/>
    - I Can't Believe It's Not Breakout <br/>
    - Definitely Not Asteroids <br/>
- [Programming Projects](#programming-projects) <br/>
    - Map with Biome Generation using Perlin Noise in PICO-8 <br/>
    - Procedurally-Generated Landscapes in OpenGL <br/>
    - Cache Simulator <br/>
- [Applications](#applications) <br/>
    - Rock Climbing Site <br/>
- [Game Development Group](#game-development-group) </br>

## Video Games
![logo](https://img.itch.zone/aW1nLzEwNjY2OTI1LnBuZw==/original/%2FT6l5K.png)
### Curious Cats
A 2D puzzle game me and a partner created in Unity with C#. 
<br/>It houses original pixel art assets and was made within a month for the GameOff 2022 game jam!
<br/>My role was the artist and UI programmer. I created all sprites and animations and integrated them into the code properly. I designed the UI, created all sprites for UI elements, and ensured that the interface was easy and satisfying to use. I'm really happy with how it turned out, from the custom cursor to the bouncy buttons!
<br/> [See more info and source code.](https://github.com/E-Potapova/GameOff2022)
#### [Play the game right now!](https://e-potapova.itch.io/curious-cats) 
<img src="curious_cats.gif" alt="GIF of Curious Cats gameplay"/>

![logo](https://img.itch.zone/aW1nLzc1Njg5MjUucG5n/original/KU9tPL.png)
#### Little Trouble [:top:](#hi-im-liz)
This is a small platformer video game me and a partner created in Unity (in C#). 
<br/>It is a 2D metroidvania with original pixel art assets made within a month for the GameOff 2021 game jam.
<br/> I personally was the project lead and asset developer. I organized responsibilities, set up deadlines, designed ability flavors (i.e. the double jump being a pair of maple tree seeds), designed UI, and created *all* art assets.
<br/> [See more info and source code.](https://github.com/E-Potapova/GameOff2021)
#### [Play the game right now!](https://e-potapova.itch.io/little-trouble) 
*Might need to zoom out in the browser and then make the game full-screen to see all UI elements.
<img src="little_trouble.gif" alt="GIF of Little Trouble gameplay"/>

### I Can't Believe It's Not Breakout [:top:](#hi-im-liz)
A small game in Unity that furthered my knowledge on scenes, object spawning, and event management.
<br/> The blocks spawn with a chance of providing an effect: giving +1 ball to the player (orange), freezing the paddle (blue), or speeding up all balls x2 (yellow).
<br/> (This is a very original game not at all related to *Breakout* by Atari.)
<img src="breakout.gif" alt="GIF of gameplay of an Atari Breakout clone"/>

### Definitely Not Asteroids [:top:](#hi-im-liz)
The very first game I made in Unity, the game is not pretty but taught me a lot of important things.
<br/> Oh, and it's *not* at all similar to *Asteroids* by Atari, I have no idea what you're talking about ... 
<img src="asteroids.gif" alt="GIF of gameplay of an Atari Asteroids clone"/>

## Programming Projects
### Map with Biome Generation using Perlin Noise in PICO-8 [:top:](#hi-im-liz)
Playing around with map generation and layering noise maps for smooth biomes. Created in the PICO-8 engine, which uses Lua; each pixel in the displayed gif would correlate to a chunk of the map that can be scaled up to any size. 

Techniques I used:
 - Layering together two separate noise maps (elevation and moisture) to choose biomes
 - Using 3 octaves for both noise maps, offsetting the noise values being fetched so that they are independent
 - 1st octave gets tapered out at edges using the square bump distance function
<br/><img src="map-gen.gif" alt="GIF of a showcase of various top-down pixel art maps that loop"/>
<img src="map-biomes.png" alt="PNG of a spreadsheet indicating which combination of the elevation and moisture values correlates to which biome"/>

### Procedurally-Generated Landscapes in OpenGL [:top:](#hi-im-liz)
Capstone project for my Computer Graphics course made with one other teammate. We generate a 2D Perlin noise map and display it on-screen for the user to see. The user can modify the density of the map and generate a new one entirely.

From the noise map we construct a rough 'landscape' by dividing the 2D map into squares, from which we draw a rectangular prism with the height corresponding to the color value of the noise. White means taller, black means shorter. The user has options for voxel cubes instead of rectangular prisms, which pixel is used to determine the height (upper-left, center, or average), and color schemes.. we added Minecraft for fun. :) [See source code.](OpenGL%20Project/)
<br/><img src="computer-graphics.gif" alt="GIF of the OpenGL project running, showcasing all options"/>

### Cache Simulator
Made for my Computer Architecture III course, this project simulates six different types of cache organization systems and tests them with varying parameters on large traces of memory instructions.
 - Direct-mapped
    - Cache line: 32 bytes, cache sizes tested: 1KB, 4KB, 16KB, 32KB
 - Set-associative
    - Cache line: 32 bytes, cache size: 16KB
    - Associativity tested: 2, 4, 8, 16, least-recently-used replacement
 - Fully-associative
    - Cache line: 32 bytes, cache size: 16KB
    - Least-recently-used (LRU) replacement policy & hot-cold LRU approximation
 - Set-associative, no allocation on write miss
    - Cache line: 32 bytes, cache size: 16KB
    - Associativity tested: 2, 4, 8, 16, least-recently-used replacement
    - If a store instruction misses, the missing line is written directly into memory instead of cache
 - Set-associative with next-line prefetching
    - For any cache access, bring next line into cache as well
 - Set-associative with next-line prefetching only on cache miss
    - When accessing the cache and missing, bring next line into cache as well

Source code available for view upon request.

## Applications
### Rock Climbing Site [:top:](#hi-im-liz)
For my 2022 summer internship, me and 2 other interns had to develop a simple application within 2 weeks based on a user story. A big focus was proper communication with its database.
<br/> Our application has three roles: Climber, Guide, and Administrator. Climbers can view routes across the U.S. and request a guide to take them through that route. Guides approve or reject requests and can add new routes to the database. Admins approve additions and have a dashboard with various reports.
<br/> I personally worked a lot on the 'Climber requests a Guide' aspect, as well as making sure the user experience and UI was at its best.
[See more info here.](Internship%20Site/) <br/>
<img src="Internship%20Site/media/all_roles.gif" alt="GIF of using Rock Climbing application"/>

## Game Development Group 
I was quite disappointed to not find *any* resources at my university regarding game development—so I took things into my own hands and founded a new club dedicated for creating games!

Fall 2023 had a series of beginner Unity workshops, a talk on how to get started making games, and a 48-hour game jam hosted on campus, all created and organized by me! Spring 2024 has a proper team hosting 2 workshops weekly for a variety of game engines and tools (Unity, PICO-8, GameMaker, Blender, Twine, etc.) and another 48-hour game jam in April!

<br/><img src="GDG-logo-full-text.svg" alt="Game Development Group Logo" width="40%"/>
