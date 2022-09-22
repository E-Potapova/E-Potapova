#include "Player.h"
#include "DynArray.h"

class Team {
 private:
  string location;
  string nickname;
  DynArray<Player> players;
  int lowestFreeJerseyNum;
  DynArray<int> jerseyNums;

  int getNewJerseyNum(int, int);
  
 public:
  Team();
  
  Team(string, string);

  Team(const Team&); // copy constructor

  Team& operator=(const Team&); // assignment operator
  
  ~Team(); // destructor

  void addPlayer(Player);
  
  void addPlayer(string, string, int);

  Player removePlayer(string);

  bool hasPlayer(string);

  void showAllPlayers();

  void showPlayerCareer(string);
  
  string getLocation();
  
  string getNickname();

  bool operator==(const Team&);

  friend ostream& operator<<(ostream&, const Team&);
};
