import java.util.*;

public class Prog6 {

    public static void main(String[] args) {
        HashMap<String, Integer> test = new HashMap<>();

        test.put("CSCI 265", 3);
        test.put("MATH 207", 2);
        test.put("MATH 208", 3);
        test.put("GEOG 262", 3);
        test.put("CSCI 330", 4);
        test.put("ANTH 101", 4);

        System.out.println(totalCredits(test));

        ArrayList<String> chkList = classesByDept(test, "MATH");
        ArrayList<String> creditList = classesByCredits(test, 4);
        
        for (int i = 0; i < chkList.size(); i++) {
            System.out.println(chkList.get(i));
        }
        for (int i = 0; i < creditList.size(); i++) {
            System.out.println(creditList.get(i));
        }
        
        System.out.println(isPartTime(test));
        System.out.println(numOfClasses(test));
    }

    public static int totalCredits(HashMap<String, Integer> students) {
        int totalCredits = 0;

        for (Map.Entry<String, Integer> entry : students.entrySet()) {
            //String key = entry.getKey();
            Integer value = entry.getValue();

            totalCredits += value;
        }

        return totalCredits;
    }

    public static ArrayList<String> classesByDept(HashMap<String, Integer> students, String department) {

        ArrayList<String> rtn = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : students.entrySet()) {
            String key = entry.getKey();
            String deptCode = key.substring(0, 4);
            //Integer value = entry.getValue();

            if (deptCode.equals(department)) {
                rtn.add(key);
            }
        }

        return rtn;

    }
    
    public static ArrayList<String> classesByCredits(HashMap<String, Integer> students, int credits) {
        ArrayList<String> rtn = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : students.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
        
            if(value.equals(credits)) {
                rtn.add(key);
            }
        }
        
        return rtn;
    }
    
    public static boolean isPartTime(HashMap<String, Integer> students) {
        int totalCredits = 0;

        for (Map.Entry<String, Integer> entry : students.entrySet()) {
            //String key = entry.getKey();
            Integer value = entry.getValue();

            totalCredits += value;
            
            if (totalCredits >= 12) {
                return false;
            }
        }
        return totalCredits > 0;
    }
    
    public static int numOfClasses(HashMap<String, Integer> students) {
        int rtnVal = 0;
        
        for(Map.Entry<String, Integer> entry : students.entrySet()) {
            rtnVal++;
        }
        
        return rtnVal;
    }
    
    public static int lowerLevelCredits(HashMap<String, Integer> students) {
        return 0;
    }
}
