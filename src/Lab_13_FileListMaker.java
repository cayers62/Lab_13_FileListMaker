import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

import static java.nio.file.StandardOpenOption.CREATE;

public class Lab_13_FileListMaker {
    static ArrayList<String> list = new ArrayList<>();
    static JFileChooser chooser = new JFileChooser();
    static boolean dirtyFlag = false;

    public static void main(String[] args) {
        Scanner pipe = new Scanner(System.in);
        final String menu = "A - Add, C - Clear List, D - Delete an item, I - Insert an item, " +
                "M- Move an item, O - Open File, S - Save , V- View, Q - Quit";
        boolean done = false;
        String opt = "";

        //initialize the list
        // initializeList();

        list.add("Curtis Ayers");
        list.add("Amber Vinson");
        list.add("Pam Wheeler");
        list.add("Kelsey Dewitt");
        list.add("David Rohe");


        //displays the list
        displayList();


        //Get the user input for the option
        //Display the menu/options

        do {

            opt = SafeInput.getRegExString(pipe, menu, "[acdimoqsvACDIMOQSV]");
            opt = opt.toUpperCase();

            switch (opt) {
                //adds to the list
                case "A":
                    list.add(SafeInput.getNonZeroLengthString(pipe, "Enter information here"));
                    dirtyFlag = true;
                    break;

                //Clears the entire array list
                case "C":
                    if (list.size() > 0) {
                        list.clear();
                        dirtyFlag = true;
                    } else {
                        System.out.println("---                               List is currently empty                         ---");
                        System.out.println("-------------------------------------------------------------------------------------");
                    }
                    break;

                //removes items from the list
                case "D":
                    if (list.size() > 0) {
                        int indexToDelete = SafeInput.getRangedInt(pipe, "Enter number you wish to be deleted", 1, list.size());
                        list.remove(indexToDelete - 1);
                        dirtyFlag = true;
                        System.out.println("Item deleted successfully.");
                    } else {
                        System.out.println("List is empty, nothing to delete.");
                    }
                    break;

                //Inserts a new item in list anywhere.
                case "I":
                    if (list.size() > 0) {
                        int indexToInsert = SafeInput.getRangedInt(pipe, "Enter position to insert the item (1 to " + (list.size() + 1) + ")", 1, list.size() + 1);
                        String newItem = SafeInput.getNonZeroLengthString(pipe, "Enter information here");
                        list.add(indexToInsert - 1, newItem);
                        dirtyFlag = true;
                    } else {
                        System.out.println("--- List is currently empty ---");
                    }
                    break;

                //Moves item to a new spot in the list
                case "M":

                    if (list.size() > 1) { // Must have at least 2 items to move
                        int fromIndex = SafeInput.getRangedInt(pipe, "Enter the position of the item to move (1 to " + list.size() + ")", 1, list.size());
                        int toIndex = SafeInput.getRangedInt(pipe, "Enter the new position for the item (1 to " + list.size() + ")", 1, list.size());

                        String itemToMove = list.remove(fromIndex - 1); // Remove item from old position
                        list.add(toIndex - 1, itemToMove); // Insert it at the new position
                        dirtyFlag = true;
                        System.out.println("Item moved successfully.");
                    } else {
                        System.out.println("Not enough items to move.");
                    }

                    break;

                // Opens a file
                case "O":
                    if (dirtyFlag) {
                        System.out.println("You have unsaved changes. Do you want to save before exiting?");
                        boolean saveBeforeLeave = SafeInput.getYNConfirm(pipe, "Save changes?");
                        if (saveBeforeLeave) {
                            savesFiles();
                        }
                    }readFile();

                    break;

                // Saves a file
                case "S":
                    savesFiles();
                    resetDirtyFlag();
                    break;

                //Prints the list
                case "V":
                    displayList();
                    break;
                //Quits the program
                case "Q":
                    if (dirtyFlag) {
                        System.out.println("You have unsaved changes. Do you want to save before exiting?");
                        boolean saveBeforeExit = SafeInput.getYNConfirm(pipe, "Save changes?");
                        if (saveBeforeExit) {
                            savesFiles();
                        }
                    }
                    done = true;
                    break;
            }
            if (!done) {
                System.out.println("Your option is " + opt);
            }
        } while (!done);
    }


    private static void displayList() {
        System.out.println("-------------------------------------------------------------------------------------");
        if (list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                System.out.printf("\n%3d%40s", i + 1, list.get(i));

            }
            System.out.println();
        } else
            System.out.println("---                               List is currently empty                         ---");
        System.out.println("-------------------------------------------------------------------------------------");
    }

    // Initialize the list with default values
   /* private static void initializeList() {
        list.add("Curtis Ayers");
        list.add("Amber Vinson");
        list.add("Pam Wheeler");
        list.add("Kelsey Dewitt");
        list.add("David Rohe");

    }*/
    private static void readFile() {

        File selectedFile;
        String rec;
        try {


            File workingDirectory = new File(System.getProperty("user.dir"));


            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in =
                        new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(in));
                int line = 0;
                while (reader.ready()) {
                    rec = reader.readLine();
                    line++;
                    System.out.printf("\n %4d -%60s ", line, rec);
                }
                reader.close();
            }
            System.out.println("\n\n Data file read!");


        } catch (
                FileNotFoundException e) {
            System.out.println("File Not Found!!");
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    // Writes to files

    private static void savesFiles() {
        Scanner pipe = new Scanner(System.in);
        File workingDirectory = new File(System.getProperty("user.dir"));
        String fileName = SafeInput.getNonZeroLengthString(pipe, "Enter a valid file name");
        Path file = Paths.get(workingDirectory.getPath(), "src", fileName + ".txt");


        try (BufferedWriter writer = Files.newBufferedWriter(file, CREATE)) {

            for (String rec : list) {
                writer.write(rec, 0, rec.length());
                writer.newLine();

            }

            writer.close();
            System.out.println("Data file has been written!");
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void resetDirtyFlag()
    {
        dirtyFlag = false;
    }


}