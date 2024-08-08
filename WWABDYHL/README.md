## WWABDYHL Code Snippets
You can freely browse three classes I've contributed to (and also see how development with others in a time crunch could get chaotic!).

[`GameManager.java`](GameManager.java) is essentially a finite state machine (FSM) that checks which state the gameplay is currently in and updates the code to reflect that accordingly. Each state has a corresponding Java class that this class re-initializes whenever it is needed. The states are `MainMenu, LevelSelect, Level, LevelPause, LevelWin, LevelLose`.

[`LevelSelect.java`](LevelSelect.javajava) is one of the game state classes that `GameManager` initializes and manages. It draws a simple background and 6 functional buttons, one for each level the user can choose. It checks if and when the buttons are pressed, and updates the game state to play that level.

[`Tower.java`](Tower.java) is an abstract parent class that is responsible for all basic functionality that each tower has: damage type, cooldown, petting cooldown, range, and so on. This class checks all timers, updates what the Tower is doing in this specific frame, and draws it each frame. There are abstract methods that child classes then implement.