import java.util.*;

public class Prog6 {
    
        public static void main(String[] args) {
            HashMap< String, Integer> test = new HashMap<>();

            test.put("CSCI 265", 3);
            test.put("MATH 207", 2);
            test.put("MATH 208", 3);
            test.put("GEOG 262", 3);
            test.put("CSCI 330", 4);
            test.put("ANTH 101", 4);

            System.out.println(totalCredits(test));

            ArrayList< String> chkList = classesByDept(test, "CSCI");
            ArrayList< String> creditList = classesByCredits(test, 3);

            for (int i = 0; i < chkList.size(); i++) {
                System.out.println(chkList.get(i));
            }
            for (int i = 0; i < creditList.size(); i++) {
                System.out.println(creditList.get(i));
            }

            System.out.println(isPartTime(test));
            System.out.println(numOfClasses(test));
            System.out.println(lowerLevelCredits(test));
            printClasses("Travis Bittner", test);
            
            
        }

        public static int totalCredits(HashMap< String, Integer> students) {
            int totalCredits = 0;

            for (Map.Entry< String, Integer> entry : students.entrySet()) {
                //String key = entry.getKey();
                Integer value = entry.getValue();

                totalCredits += value;
            }

            return totalCredits;
        }

        public static ArrayList< String> classesByDept(HashMap< String, Integer> students,
                String department) {

            ArrayList< String> rtn = new ArrayList<>();

            for (Map.Entry< String, Integer> entry : students.entrySet()) {
                String key = entry.getKey();
                String deptCode = key.substring(0, 4);
                //Integer value = entry.getValue();

                if (deptCode.equals(department)) {
                    rtn.add(key);
                }
            }

            return rtn;

        }

        public static ArrayList< String> classesByCredits(HashMap< String, Integer> students,
                int credits) {
            ArrayList< String> rtn = new ArrayList<>();

            for (Map.Entry< String, Integer> entry : students.entrySet()) {
                String key = entry.getKey();
                Integer value = entry.getValue();

                if (value.equals(credits)) {
                    rtn.add(key);
                }
            }

            return rtn;
        }

        public static boolean isPartTime(HashMap< String, Integer> students) {
            int totalCredits = 0;

            for (Map.Entry< String, Integer> entry : students.entrySet()) {
                //String key = entry.getKey();
                Integer value = entry.getValue();

                totalCredits += value;

                if (totalCredits >= 12) {
                    return false;
                }
            }
            return true;
        }

        public static int numOfClasses(HashMap< String, Integer> students) {
            int rtnVal = 0;

            for (Map.Entry< String, Integer> entry : students.entrySet()) {
                rtnVal++;
            }

            return rtnVal;
        }

        public static int lowerLevelCredits(HashMap< String, Integer> students) {
            int rtnVal = 0;

            for (Map.Entry< String, Integer> entry : students.entrySet()) {
                String classNum = entry.getKey();
                classNum = classNum.substring(5);
                int value = Integer.parseInt(classNum);

                if (value < 300) {
                    rtnVal += entry.getValue();
                }
            }

            return rtnVal;

        }

        public static void printClasses(String title, HashMap< String, Integer> students) {
            //left and right justify values    
            int rightJust = (42 + title.length()) / 2;
            int leftPadding = (40 - title.length()) / 2;

            //Create the title centered 40 width
            //StringBuilder sb = new StringBuilder();
            System.out.println(title + '\n');
            //for (int i = 0; i < leftPadding; i++) {
            //0   sb.append(' ');
            //}
            //sb.append(title);
            //System.out.println(sb.toString() + "\n");

            //Create the Class and Credit columns
            StringBuilder sb2 = new StringBuilder();
            System.out.print("Class");
            for (int i = 0; i < rightJust; i++) {
                sb2.append(' ');
            }
            sb2.append("Credits");
            System.out.println(sb2.toString() + "\n");

            //Create the dashed line
            //String line = new String(new char[40]).replace("\0", "-");
            //System.out.println(line);

            ArrayList< String> sortedList = mapToSortedList(students);

            for (int i = 0; i < sortedList.size(); i++) {
                for (Map.Entry< String, Integer> entry : students.entrySet()) {
                    String key = entry.getKey();
                    
                    if (key.equals(sortedList.get(i))) {
                        StringBuilder sb3 = new StringBuilder();
                        System.out.print(key);
                        for (int k = 0; k < rightJust + 3; k++) {
                            sb3.append(' ');
                        }
                        sb3.append(entry.getValue());
                        System.out.print(sb3.toString() + "\n");
                    }   
                }
            }

        }

        public static ArrayList< String> mapToSortedList(HashMap< String, Integer> students) {
            String[] sortedList = new String[students.size()];
            int arrIter = 0;            
            
            
            for (Map.Entry< String, Integer> entry : students.entrySet()) {
                sortedList[arrIter] = entry.getKey();
                arrIter++;
            }

            for (int i = 0; i < sortedList.length; i++) {
                for (int j = i + 1; j < sortedList.length; j++) {
                    if (sortedList[j].compareTo(sortedList[i]) < 0) {
                        String tmp = sortedList[i];
                        sortedList[i] = sortedList[j];
                        sortedList[j] = tmp;
                    }
                }
            }
            return new ArrayList<>(Arrays.asList(sortedList));
        }
}
