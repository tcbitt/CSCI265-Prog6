def totalCredits (students) -> int:
    credits = 0
    for x in students:
        credits += students.get(x)
    return credits
    
def classesByDept (students, department) -> list[str]:
    rtn = []
    for x in students:
        if (str(x[0:4]) == department):
            rtn.append(x)
    return rtn

def classesByCredits (students, credits) -> list[str]:
    rtn = []
    for x in students:
        if (students.get(x) == credits):
            rtn.append(x)
    return rtn

def isPartTime(students) -> bool:
    numCredits = 0
    for x in students:
        numCredits += students.get(x)
        if (numCredits >= 12):
            return False
    return True

def numOfClasses (theDictionary):
    rtn = 0
    for x in theDictionary:
        rtn += 1
    return rtn

def lowerLevelCredits(students):
    rtn = 0
    for x in students:
        if (int(x[5:]) < 300):
            rtn += students.get(x)
    return rtn

def printClasses(title, students):
    print(title)
    print()
    #print("Class".ljust(20) + "Credits".rjust(26))
    #print()
    
    sortedClasses = dict(sorted(students.items()))      
    for x in sortedClasses:
            print(x.ljust(20) + str(sortedClasses.get(x)).rjust(20)) 


def main():
    #students = {}
    students = {"CSCI 265" : 3, "MATH 208" : 3, "GEOG 101" : 4, "GEOG 262" : 4, "HIST 405" : 4, "MATH 320" : 3}
    print(totalCredits(students))
    print(classesByDept(students, "CSCI"))
    print(classesByCredits(students, 3))
    print(isPartTime(students))
    print(numOfClasses(students))
    print(lowerLevelCredits(students))
    printClasses("Travis", students)

if __name__ == "__main__":
    main()
