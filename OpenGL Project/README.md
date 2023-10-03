# Procedurally-generated Height Map from Perlin Noise

### Elizabeth Potapova & Tyler Ramos

### Objective
Procedural generation is used almost everywhere in computer graphics, including video games, animation, and film. We wanted to look at the origins of procedurally-generated noise by implementing Perlin noise, the most popular method in generating noise, via OpenGL.

### Usage
Download `main.cpp` and the `makefile`. In the same directory as both files, run the command `make` in the terminal/command line (it may not work if dependencies are not installed). Run the generated executable with `./perlinNoise`.

If you are on an Apple computer, you can try running the executable directly by downloading `perlinNoise` and executing `./perlinNoise` in the terminal/command line.


### Controls

Left click the mouse to bring up generation options:
- Tower style swaps between either solid rectangular prisms or building the tower out of individual voxels.
- Generation method switches between three different ways of picking a tower's height in the height map.
- Color switches between three color modes ("Minecraft" mode requires the the Tower style to be voxel cubes).

Press "[" to decrease the number of towers per landscape width there are (minimum 4). Press "]" to increase the number of towers per landscape width (maximum ~255).

Press "-" and "+" to decrease and increase the maximum possible height of a tower.

Press "<" and ">" to rotate the heightmap around its Y axis.

Press "L" to lock the view of the terrain to a bird's eye view.

Press "O" To enable outlines on each tower that makes up the terrain.

Press the spacebar to generate a new Perlin noise map and terrain.

Press "Z" and "X" to change the density of the Perlin Noise Map (more dense = more peaks and valleys, less dense = smooth landscape).

Press "ESC" to quit.
