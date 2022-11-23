import java.util.Scanner;

public class SafeInput {
    public static String getRegExString(Scanner pipe, String propmt, String regEx) {
        String retVal = "";
        boolean done = false;

        do {
            System.out.println("\n" + propmt + ": ");
            retVal = pipe.nextLine();
            if (retVal.matches(regEx)) {
                done = true;
            } else {
                System.out.println("\n" + retVal + "must match the pattern " + regEx);
                System.out.println("try again!");
            }
        } while (!done);
        return retVal;
    }
    public static String getNonZeroLenString(Scanner pipe, String prompt) {
        String retString = "";
        do {
            System.out.println("\n" + prompt + ": ");
            retString = pipe.nextLine();
        } while (retString.length() == 0);
        return retString;
    }
}
