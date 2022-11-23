import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileListMaker {
    static ArrayList<String> list = new ArrayList<>();

    static Scanner in = new Scanner(System.in);
    static String menuprompt = ("A - add, D - delete, V - view, O - open, S - save C - clear, Q - quit" + "\n" + list);
    static String menu = "[AaDdVvOoSsCcQq]";
    static boolean done = false;
    static boolean newUnsavedFile = false;

    public static void main(String[] args) {

        do {
            while(true) {


                String userInput = SafeInput.getRegExString(in, menuprompt, menu);
                userInput = userInput.toUpperCase();
                System.out.println(userInput);
                switch (userInput) {
                    case "A":
                        list.add(add(list));
                        break;
                    case "D":
                        list.remove(delete(list));
                        break;
                    case "V":
                        view(list);
                        break;
                    case "O":
                        do {
                            String warningS = "are you sure you want to open a new file (Y or N) make sure you saved the one your on first";
                            String thingS = "[YyNn]";
                            String warningInput = SafeInput.getRegExString(in, warningS, thingS);
                            if (warningInput.equalsIgnoreCase("N")) {
                                break;
                            } else if (warningInput.equalsIgnoreCase("Y")) {
                                openFile(list);
                            } else {
                                System.out.println("You must answer Y or N");
                            }
                        } while (!done);
                        break;
                    case "S":
                        save(list);
                        break;
                    case "C":
                        clear(list);
                        list.clear();
                        break;
                    case "Q":
                        quit();
                        do {
                            String warning = "are you sure you want to quit, Y or N, make sure you saved beforhand";
                            String thing = "[YyNn]";
                            String warningInput = SafeInput.getRegExString(in, warning, thing);
                            if (warningInput.equalsIgnoreCase("Y")) {
                                System.exit(0);
                            } else if (warningInput.equalsIgnoreCase("N")) {
                                break;
                            } else {
                                System.out.println("You must answer Y or N");
                            }
                        } while (!done);
                        done = true;
                        break;
                    default:
                        System.out.println("Invalid letter entered");

                }

            }
        } while (!done);

    }

    private static String add(ArrayList<String> list) {
        Scanner pipe = new Scanner(System.in);
        String answer = SafeInput.getNonZeroLenString(pipe, "What will you add to the list");
        return answer;

    }

    private static String delete(ArrayList<String> list) {
        Scanner pipe = new Scanner(System.in);
        String rid = SafeInput.getNonZeroLenString(pipe, "What will you delete from the list");
        return rid;

    }

    private static void save(ArrayList<String> list) {
        newUnsavedFile = false;

    }

    private static void view(ArrayList<String> list) {
        System.out.println("-------------------------------");
        for (String value : list) {
            System.out.println(value);
        }
        System.out.println("-------------------------------");

    }

    private static void clear(ArrayList<String> list) {
        list.clear();
    }

    private static void openFile(ArrayList<String> list) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        ArrayList<String> lines = new ArrayList<>();

        try {
            File workingDirctory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirctory);

            chooser.showOpenDialog(null);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                //if the user selects a file
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                while (reader.ready()) {
                    String line = reader.readLine();
                    //           list.add(Arrays.asList(line.split("")));
                    list.addAll(Arrays.asList(line.split("")));
                    newUnsavedFile = true;

                }

                reader.close();
                System.out.println("File read successfully!");
                System.out.println(list);


            } else {
                //if an error occured or the user cancelled
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again");
                System.exit(0);
            }
            //opens the file chooser
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    public static void quit() {

    }
}
