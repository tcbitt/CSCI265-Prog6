/*
Name: Travis Bittner

email: travis.bittner@und.edu

C++ program that implements an unordered_map<string,int> that holds a class name/num and the credit value. Contains functions to 
check total credits, sort classes by department, sort classes by credit value, check if a student is part-time, get the number of classes taken,
check how many credits are below 300-level, and print. There is an additional function to convert the unordered_map to a vector<string> to be sorted.
The sorted vector contains the keys and when displaying the table, it uses the key to display the rest of the information in alphabetical order.

*/


#include <iostream>
#include <iomanip>
#include <unordered_map>
#include <vector>
#include <string>

int totalCredits(std::unordered_map<std::string, int>);
std::vector<std::string> classesByDept(std::unordered_map<std::string, int>, std::string);
std::vector<std::string> classesByCredits(std::unordered_map<std::string, int>, int);
bool isPartTime(std::unordered_map<std::string, int>);
int numOfClasses(std::unordered_map<std::string, int>);
int lowerLevelCredits(std::unordered_map<std::string, int>);
void printClasses(std::string, std::unordered_map<std::string, int>);
std::vector<std::string> mapToSortedVec(std::unordered_map<std::string, int>);

#ifndef COMPILE_MAIN
int main() {
    //This map is per-student
    std::unordered_map<std::string, int> test =
    {
        {"CSCI 265", 3},
        {"MATH 207", 2},
        {"MATH 208", 3},
        {"GEOG 262", 3},
        {"CSCI 330", 4},
        {"ANTH 101", 4}

    };

    std::cout << totalCredits(test);

    std::vector<std::string> returnVector = classesByDept(test, "CSCI");

    std::vector<std::string> creditVector = classesByCredits(test, 3);

    std::cout << std::endl << std::boolalpha << isPartTime(test) << std::endl;

    std::cout << numOfClasses(test) << std::endl;

    std::cout << lowerLevelCredits(test) << std::endl;

    printClasses("Travis Bittner", test);


}
#endif
/*
totalCredits function - iterates the map and adds all the values stored for total credits taken.

Parameters - An unordered_map<string,int> using the string as the class name, and the int for the number of credits
             the class is worth.

Return value - This function returns an integer, the total number of credits the student is taking.
*/
int totalCredits(std::unordered_map<std::string, int> students) {
    int rtn = 0;

    for (const auto& it : students) {
        rtn += it.second;
    }

    return rtn;
}
/*
classesByDept function - This function iterates the map and assigns a string the key, then parses first 4 letters which is
                         the department code and sets itself equal to this code. Assuming the department parameter is the code, we check their equivalence 
                         and push onto the vector<string> if it matches.

Parameters - An unordered_map<string, int> and a string containing the department code.

Return value - A vector<string> that will contain all of the classes in the requested department.
*/
std::vector<std::string> classesByDept(std::unordered_map<std::string, int> students, std::string department) {
    std::vector<std::string> rtn;
    std::string chk;
    for (const auto& it : students) {
        chk = it.first;
        chk = chk.substr(0, 4);
        if (chk == department)
            rtn.push_back(it.first);
    }

    return rtn;
}
/*
classesByCredits function - This function works similar to byDept. It will iterate over the map and check if the value for credits is
                            equal to the value of credits being searched for. If they are equal push onto the vector<string> to be returned.

Parameters - An unordered_map<string,int> and an integer, the number of credits to search for classes with.

Return value - A vector<string> containing all of the classes who have the number of credits requested.
*/
std::vector<std::string> classesByCredits(std::unordered_map<std::string, int> students, int credits) {
    std::vector<std::string> rtn;
  
    for (const auto& it : students) {
        if (it.second == credits)
            rtn.push_back(it.first);
    }

    return rtn;
}
/*
isPartTime function - This function iterates through the map and totals all of the credits, once it hits >=12 it returns false, not a part time student.
                      Otherwise it returns true.

Parameters - An unordered_map<string, int>

Return value - It returns a boolean, false is a full-time student or a student taking 0 credits. True is a part-time student
*/
bool isPartTime(std::unordered_map<std::string, int> students) {
    int total = 0;
    for (const auto& it : students) {
        total += it.second;
        if (total >= 12)
            return false;
    }
    return true;
}
/*
numOfClasses function - Iterates the map and increments a counter for each entry/class.

Parameters - An unordered_map<string,int>

Return value - It will return an integer, the number of classes the student is taking.
*/
int numOfClasses(std::unordered_map<std::string, int> students) {
    int total = 0;
    for (const auto& it : students) {
        total++;
    }

    return total;
}
/*
lowerLevelCredits function - This function itereates the map and each time it will parse the classname/key for the class number. From there
                             we use our string-to-integer function to get the numeric value. It then checks if the class number is less than 300,
                             if it is we total the number of credits the course is worth to be returned.

Parameters - An unordered_map<string,int> to be iterated.

Return value - An integer, this is the sum of credits for all the classes under the 300 level.
*/
int lowerLevelCredits(std::unordered_map<std::string, int> students) {
    std::string className;
    int classNum = 0, totalCredits = 0;
    for (const auto& it : students) {
        className = it.first;
        className = className.substr(5);
        classNum = std::stoi(className);

        if (classNum < 300)
            totalCredits += it.second;
    }

    return totalCredits;
}
/*
printClasses function - This function creates a table and displays the map contents in alphabetical order. Displays a title in the center,
                        the class names listed on the left, and their credit value listed on the right. It utilizes another function, mapToSortedVec,
                        that uses the selection sort on the vector. It will then use a nested for loop to iterated the map and for each element of the sorted vector.
                        Each iteration it uses the sorted key vector to match and display the proper credits.

Parameters - A string, the title of the table, and an unordered_map<string, int>

Return value - void, this function just displays the map in table format, alphabetically.
*/
void printClasses(std::string title, std::unordered_map<std::string, int> students) {

    //Title and table formatting
    std::cout << std::setw(12) << "" << title << std::setfill('-') << "" << std::endl << std::endl;
    std::cout << std::left << std::setfill(' ') << std::setw(18) << "Class" << "|";
    std::cout << std::right << std::setw(18) << "Credits" << std::endl << std::endl;
    std::cout << std::setfill('-') << std::setw(37) << "" << "" << std::endl;

    std::vector<std::string> sortedStudents = mapToSortedVec(students);
  
    for (int i = 0; i < sortedStudents.size(); i++) {
        for (const auto& it: students) {
            if(it.first == sortedStudents[i])
                std::cout << std::left << it.first << std::setw(29) << std::setfill(' ') << std::right << it.second << std::endl;
                //std::cout << it.first << std::setw(29) << std::setfill(' ') << it.second << std::endl;
        }
    }
}

/*
mapToSortedVec function - This is an extra function to convert the map to a vector to be sorted using selection sort.
                          Simple for loop to populate the vector with all of the classes, then sorts alphabetically for use
                          in the printClasses function.

Parameters - An unordered_map<string,int>

Return value - Returns a sorted vector<string> using the map key values (students classes)
*/
std::vector<std::string> mapToSortedVec(std::unordered_map <std::string, int> students) {
    std::vector<std::string> rtnVec;

    for (const auto& it : students) {
        rtnVec.push_back(it.first);
    }

    for (int i = 0; i < rtnVec.size(); i++) {
        for (int j = (i + 1); j < rtnVec.size(); j++) {
            if (rtnVec[j] < rtnVec[i]) {
                std::string tmp = rtnVec[i];
                rtnVec[i] = rtnVec[j];
                rtnVec[j] = tmp;
            }

        }
    }

    return rtnVec;
}
