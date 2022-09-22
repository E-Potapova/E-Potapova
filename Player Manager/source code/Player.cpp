#include "Player.h"

Player::Player()
  : firstName(""), lastName(""), preferredNumber(-1), jerseyNumber(-1) {}

Player::Player(string first, string last, int preferred, int actual)
  : firstName(first), lastName(last), preferredNumber(preferred), jerseyNumber(actual) {}

// copy constructor
Player::Player(const Player& other)
  : firstName(other.firstName), lastName(other.lastName),
    preferredNumber(other.preferredNumber), jerseyNumber(other.jerseyNumber), career(other.career) {}

// assignment operator
Player& Player::operator=(const Player& other) {
  firstName = other.firstName;
  lastName = other.lastName;
  preferredNumber = other.preferredNumber;
  jerseyNumber = other.jerseyNumber;
  career = other.career;
  return *this;
}

Player::~Player() {}

void Player::addCareer(string teamName, int jerseyNum) {
  string full = teamName + " (#" + to_string(jerseyNum) + ")";
  career.add(full);
}

void Player::showCareer() {
  career.showAll();
}

bool Player::empty() {
  if (lastName.compare("") == 0)
    return true;
  return false;
}

string Player::getFirstName() {
  return firstName;
}

string Player::getLastName() {
  return lastName;
}

int Player::getPreferredNum() {
  return preferredNumber;
}

int Player::getJerseyNum() {
  return jerseyNumber;
}

void Player::setJerseyNum(int num) {
  jerseyNumber = num;
}

bool Player::operator==(const Player& other) {
  if (lastName.compare(other.lastName) == 0)
      return true;
  return false;
}

ostream& operator<<(ostream& os, const Player& player) {
  os << player.lastName << ", " << player.firstName << " (#" << player.jerseyNumber << ")" << flush;
  return os;
}
