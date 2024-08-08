## Jump Monarch Source Code Snippets
You can freely browse three classes I've programmed in Java that provide a good summary of how the overall project works.

[`Game.java`](Game.java) is the class responsible for the Game state (out of three states: MainMenu, GameOver, and Game). It parses player input, updates the game each frame accordingly, and draws the screen each frame after the updates.

[`ParticleManager.java`](ParticleManager.java) is the class responsible for a specific type of Particles. It utilizes a pool system where a certain number of Particles are created and simply shown or hidden accordingly (instead of re-creating and re-destroying Particles).

[`Player.java`](Player.java) handles everything Player-related like jumping, gravity, colliding with the walls, and drawing the sprite. It also checks if the player input is considered a proper swipe (with enough distance covered) to decide is the Player character jumps or not.