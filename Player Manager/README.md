## Sports Player Manager
[See full requirements here.](requirements.pdf) <br/>

All code in the `source code` folder was created by me, with the exceptions being the `makefile` and `Parser.h` files being a modified version that the professor provided.

`League`, `Team`, and `Player` are custom classes that have the declaration in the header (`.h`) files, and the definition in the C++ (`.cpp`) files. 
<br/> `DynArray` is a template class, and thus is defined entirely in the `.h` file.
<br/> `Play.cpp` is the file that puts everything together and has `main`.

Here is an example output of running the executable: <br/>
`Enter a command:` <br/>
`Choose from` <br/>
`   Team <location> <team-nickname>` <br/>
`   Player <firstname> <lastname> <number> [<team-nickname>]`<br/>
`   League`<br/>
`   Roster [<team-nickname>]`<br/>
`   Release <lastname> <team-nickname>`<br/>
`   Sign <lastname> <team-nickname>`<br/>
`   Career <lastname>`<br/>
`   Quit`<br/>
`:` <br/>

`<>` indicate a required parameter, and `[]` indicate an optional one. <br/>
*This menu appears after inputting each command; it is omitted for the rest of the document for readability.*

We add some Teams to our League with these commands:<br/>
`: Team Binghamton Bearcats` <br/>
`: Team Marist Foxes` <br/>
`: Team Cornell Bears` <br/>

Then view our League with the output: <br/>
`: League` <br/>
`Teams:3` <br/>
`Binghamton Bearcats (0 players)`<br/>
`Marist Foxes (0 players)`<br/>
`Cornell Bears (0 players)`<br/>

We add a Player: <br/>
`: Player Liz Potapova 3 Bearcats` <br/>
`: Roster Bearcats` <br/>
`Binghamton Bearcats`<br/>
`Players:`<br/>
`Potapova, Liz (#3)`<br/>

Liz can be Released from the Team and added to the Free Agents: <br/>
`: Release Potapova Bearcats`<br/>
`: Roster`<br/>
`Free Agents:`<br/>
`Potapova, Liz (#3)`<br/>

Then, we Sign her to a different Team:<br/>
`: Sign Potapova Foxes`<br/>
`: Roster Foxes`<br/>
`Marist Foxes`<br/>
`Players:`<br/>
`Potapova, Liz (#3)`<br/>

Lastly, we can check Liz's Career:<br/>
`: Career Potapova`<br/>
`Bearcats (#3)`<br/>
`Foxes (#3)`<br/>