#include "Team.h"

// if currentNum is -1, then adding a brand new player
int Team::getNewJerseyNum(int preferred, int current) {
  if (jerseyNums.has(preferred)) {
    if ((current != -1) && (jerseyNums.has(current))) { // adding existing player
	return current;
    }
    else { // adding a player with just preferred num taken
      while (jerseyNums.has(lowestFreeJerseyNum)) {
	lowestFreeJerseyNum++;
      }
      lowestFreeJerseyNum++;
      return lowestFreeJerseyNum;
    }
  }       
  else { // preferred number not taken
    return preferred;
  }
}

Team::Team()
  : location(""), nickname(""), lowestFreeJerseyNum(1) {}

Team::Team(string loc, string name)
  : location(loc), nickname(name), lowestFreeJerseyNum(1) {}

// copy constructor
Team::Team(const Team& other)
  : location(other.location), nickname(other.nickname), players(other.players),
    lowestFreeJerseyNum(other.lowestFreeJerseyNum), jerseyNums(other.jerseyNums) {}

//assignment operator
Team& Team::operator=(const Team& other) {
  location = other.location;
  nickname = other.nickname;
  players = other.players;
  lowestFreeJerseyNum = other.lowestFreeJerseyNum;
  jerseyNums = other.jerseyNums;
  return *this;
}
// destructor
Team::~Team() {}

// for adding existing players
void Team::addPlayer(Player existing) {
  Player* player = players.add(existing);
  int preferred = player->getPreferredNum();
  int current = player->getJerseyNum();
  int jersey = getNewJerseyNum(preferred, current);
  player->setJerseyNum(jersey);
  player->addCareer(nickname, jersey);
}

// for adding completely new players
void Team::addPlayer(string firstName, string lastName, int preferredNum) {
  Player* player;
  int jersey = getNewJerseyNum(preferredNum, -1);
  player = players.add(Player(firstName, lastName, preferredNum, jersey));
  jerseyNums.add(jersey);
  /*
  if (jerseyNums.has(preferredNum)) {  // adding player without preferred num
    if ((currentNum != -1) && (!jerseyNums.has(currentNum))) { // add existing player with previous jersey number
      player = players.add(Player(firstName, lastName, preferredNum, currentNum));
      jerseyNums.add(currentNum);
    }
    else { // adding a player with preferred num taken
      while (jerseyNums.has(lowestFreeJerseyNum)) {
	lowestFreeJerseyNum++;
      }
      player = players.add(Player(firstName, lastName, preferredNum, lowestFreeJerseyNum));
      jerseyNums.add(lowestFreeJerseyNum);
      lowestFreeJerseyNum++;
    }
  }
  else { // adding player with preferred num free
    if (preferredNum == lowestFreeJerseyNum)
      lowestFreeJerseyNum++;
    player = players.add(Player(firstName, lastName, preferredNum, preferredNum));
    jerseyNums.add(preferredNum);
    } */
  player->addCareer(nickname, jersey);
}

// returns copy of Player that is removed
Player Team::removePlayer(string lastName) {
  Player player = players.remove(Player("", lastName, -1, -1));
  if (!player.empty())
    jerseyNums.remove(player.getJerseyNum());
  return player;
}

bool Team::hasPlayer(string lastName) {
  if (players.has(Player("", lastName, -1, -1)))
    return true;
  return false;
}

void Team::showAllPlayers() {
  players.showAll();
}

void Team::showPlayerCareer(string lastName) {
  Player* player = players.getItem(Player("", lastName, -1, -1));
  if (player != nullptr) { // player exists in this team
    player->showCareer();
  }
}

string Team::getLocation() {
  return location;
}

string Team::getNickname(){
  return nickname;
}

bool Team::operator==(const Team& other) {
  if (nickname.compare(other.nickname) == 0)
      return true;
  return false;
}

ostream& operator<<(ostream& os, const Team& team) {
  os << team.location << " " << team.nickname << " (" << team.players.getNumElements() << " players)" << flush;
  return os;
}

