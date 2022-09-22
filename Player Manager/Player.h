#include <string>
#include <iostream>
#include "DynArray.h"
using namespace std;

class Player {
 private:
  string firstName;
  string lastName;
  int preferredNumber;
  int jerseyNumber;
  DynArray<string> career;
  
public:
  Player();
  
  Player(string, string, int, int);

  Player(const Player&); // copy constructor

  Player& operator=(const Player&); // assignment operator

  ~Player();

  void addCareer(string, int);

  void showCareer();
  
  bool empty();

  string getFirstName();

  string getLastName();
  
  int getPreferredNum();

  int getJerseyNum();

  void setJerseyNum(int);
  
  bool operator==(const Player&);

  friend ostream& operator<<(ostream&, const Player&);
};
