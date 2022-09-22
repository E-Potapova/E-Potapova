#include "Team.h"
#include "DynArray.h"

class League {
 private:
  DynArray<Team> teams;
  DynArray<Player> freeAgents;
  
 public:
  void addTeam(string, string);

  void addPlayerToFreeAgents(string, string, int);
  
  void addPlayerToTeam(string, string, int, string);

  bool release(string, string);

  bool sign(string, string);

  void career(string);
  
  void showAllTeams();

  void showFreeAgents();
  
  void showTeamPlayers(string);
  
  int getNumTeams();
};
