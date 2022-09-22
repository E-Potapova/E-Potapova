#include "League.h"

void League::addTeam(string loc, string name) {
  if (!teams.has(Team(loc, name)))
      teams.add(Team(loc, name));
}

void League::addPlayerToFreeAgents(string first, string last, int jersey) {
  freeAgents.add(Player(first, last, jersey, jersey));
}

void League::addPlayerToTeam(string first, string last, int jersey, string teamName) {
  Team* team = teams.getItem(Team("", teamName));
  if (team != nullptr) {
    team->addPlayer(first, last, jersey);
  }
  else {
    addPlayerToFreeAgents(first, last, jersey);
  }
}

// bool for if removed properly successfully or not
bool League::release(string lastName, string teamName) {
  Team* team = teams.getItem(Team("", teamName));
  if (team != nullptr) {  // found matching team
    Player player = team->removePlayer(lastName);
    if (!player.empty()) { // player is found
      freeAgents.add(player);
      return true;
    }
  }
  return false;
}

bool League::sign(string lastName, string teamName) {
  Team* team = teams.getItem(Team("", teamName));
  if (team != nullptr) { // found matching team
    Player player = freeAgents.remove(Player("", lastName, -1, -1));
    if (!player.empty()) { // found free agent
      team->addPlayer(player);
      return true;
    }
  }
  return false;
}

void League::career(string lastName) {
  Team* team;
  while (1) {
    team = teams.iteratorGet();
    if (team == nullptr) // reached end of iterator
      break;
    team->showPlayerCareer(lastName);
  }
}

void League::showAllTeams() {
  teams.showAll();
}

void League::showFreeAgents() {
  freeAgents.showAll();
}

void League::showTeamPlayers(string teamName) {
  Team* team = teams.getItem(Team("", teamName));
  if (team != nullptr) {
    std::cout << team->getLocation() << " " << team->getNickname() << endl;
    std::cout << "Players:" << endl;
    team->showAllPlayers();
  }
}

int League::getNumTeams() {
  return teams.getNumElements();
}
