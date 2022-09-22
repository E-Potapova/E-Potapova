#include "League.h"
#include "Parser.h"
using namespace std;

int main() {
   string line = "";  
   string command = "";  
   int num;
   League league;
   
   while (true) {
      cout << endl << "Enter a command: " << endl;
      cout << "Choose from " << endl <<
	"   Team <location> <team-nickname>" << endl <<
	"   Player <firstname> <lastname> <number> [<team-nickname>]" << endl <<
        "   League" << endl <<
        "   Roster [<team-nickname>]" << endl <<
        "   Release <lastname> <team-nickname>" << endl <<
        "   Sign <lastname> <team-nickname>" << endl <<
        "   Career <lastname>" << endl <<
        "   Quit" << endl;
      cout << ": "; 

      getline(cin, line);
      Parser parser(line);
      if (parser.getOperation().empty()) {
         if (cin.fail())
	    break;
         continue;
      }

      if (parser.getOperation() == "Quit") {
         if (parser.numArgs() > 0) 
            cout << endl << "Ignoring unexpected arguments." << endl; 
         break;
      }

      else if (parser.getOperation() == "League") {
         if (parser.numArgs() > 0) 
            cout << endl << "Ignoring unexpected arguments." << endl; 
	 cout << "Teams:" << league.getNumTeams() << endl;
	 league.showAllTeams();
      }
            
      else if (parser.getOperation() == "Roster") {
	if (parser.numArgs() == 0) {
	  cout << "Free Agents:" << endl;
	  league.showFreeAgents();
	}
	else {
	  league.showTeamPlayers(parser.getArg1());
	}
	if (parser.numArgs() > 1) {
	  cout << endl << "Ignoring unexpected arguments." << endl;
	}
      }
      
      else if (parser.getOperation() == "Career") {
         if (parser.numArgs() < 1) 
            cout << endl << "Missing arguments." << endl;
	 else 
            league.career(parser.getArg1()); 
         if (parser.numArgs() > 1) 
            cout << endl << "Ignoring unexpected arguments." << endl; 
      }

      else if (parser.getOperation() == "Team") {
	if (parser.numArgs() < 2)
	  cout << endl << "Missing arguments." << endl;
	else
	  league.addTeam(parser.getArg1(), parser.getArg2());
	if (parser.numArgs() > 2)
	  cout << endl << "Ignoring unexpected arguments." << endl;
      }

      else if (parser.getOperation() == "Release") {
	if (parser.numArgs() < 2)
	  cout << endl << "Missing arguments." << endl;
	else
	  if (!league.release(parser.getArg1(), parser.getArg2()))
	    cout << "Error releasing player." << endl;
	if (parser.numArgs() > 2)
	  cout << endl << "Ignoring unexpected arguments." << endl;
      }

      else if (parser.getOperation() == "Sign") {
	if (parser.numArgs() < 2)
	  cout << endl << "Missing arguments." << endl;
	else
	  if (!league.sign(parser.getArg1(), parser.getArg2()))
	    cout << "Error signing player." << endl;
	if (parser.numArgs() > 2)
	  cout << endl << "Ignoring unexpected arguments." << endl;
      }
      
      else if (parser.getOperation() == "Player") {
         if (parser.numArgs() < 3) 
            cout << endl << "Missing arguments." << endl;
	 else {
           if (Parser::isInteger(parser.getArg3())) {
              num = stoi(parser.getArg3());
	      if (parser.numArgs() == 4)
		league.addPlayerToTeam(parser.getArg1(), parser.getArg2(), num, parser.getArg4());
	      else
		league.addPlayerToFreeAgents(parser.getArg1(), parser.getArg2(), num);
           }
           else 
	      cout << "Error: third argument (" << parser.getArg3() << ") is not an integer." << endl;
           if (parser.numArgs() > 4) 
              cout << endl << "Ignoring unexpected arguments." << endl; 
         }
      }

      else
	cout << "Your command is invalid. Please re-enter." << endl;
   }
}
