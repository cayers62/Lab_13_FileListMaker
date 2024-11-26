import java.util.Scanner;

public class SafeInput {
    /**
     * Gets a string from the user that has to be at least one character or more
     *
     * @param pipe   Scanner to use for input set to the console with System.in
     * @param prompt the prompt for the user the methods adds ":" and uses System.print
     *               ranged methods get a "...[low - high]:"
     * @return After looping until the user enters something a non zero String...
     */
    public static String getNonZeroLengthString(Scanner pipe, String prompt) {
        String nonZeroLength = "";

        do {
            System.out.print("\n" + prompt + ":");
            nonZeroLength = pipe.nextLine();

            if (nonZeroLength.length() == 0) {
                System.out.println("\nYou must provide some valid input");
            }

        } while ((nonZeroLength.length() == 0));

        return nonZeroLength;

    }

    /**
     * Gets a valid integer or real number
     *
     * @param pipe   Scanner the user has for input
     * @param prompt is the information the user is prompted for
     * @return is good information that was input by the user
     */

    public static int getInt(Scanner pipe, String prompt) {
        int value = 0;
        String trash = "";
        boolean done = false;

        do {

            System.out.print("ln" + prompt + ":");

            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                pipe.nextLine(); //Clearing the buffer
                done = true; // if user puts in valid input

            } else {
                trash = pipe.nextLine();
                System.out.println("\nYou must enter a valid number: " + trash);  // only if the user puts in invalid input
            }
        } while (!done);

        return value;
    }

    /**
     * Gets a valid double integer, real number, or decimal
     *
     * @param pipe   Scanner the user has for input
     * @param prompt is the information the user is prompted for
     * @return is good information that was input by the user
     */

    public static double getDouble(Scanner pipe, String prompt) {
        double value = 0.0;
        String trash = "";
        boolean done = false;

        do {

            System.out.print("ln" + prompt + ":");

            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                pipe.nextLine(); //Clearing the buffer
                done = true; // if user puts in valid input

            } else {
                trash = pipe.nextLine();
                System.out.println("\nYou must enter a valid number: " + trash);  // only if the user puts in invalid input
            }
        } while (!done);

        return value;
    }

    /**
     * Get a number within a range
     *
     * @param pipe   Scanner the user needs for input
     * @param prompt information that user needs to enter
     * @param low    lowest part of the range
     * @param high   highest part of the range
     *               return only happens if user gives good input
     */
    public static int getRangedInt(Scanner pipe, String prompt, int low, int high) {
        int value = 0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + "[" + low + " - " + high + "]: ");
            if (pipe.hasNextInt()) {
                value = pipe.nextInt();
                pipe.nextLine(); //Clears the buffer
                if (value >= low && value <= high)
                    done = true;
                else
                    System.out.println("\nYou entered a number out of range [" + low + " - " + high + "] not: " + value);

            } else {
                trash = pipe.nextLine();
                System.out.println("\nYou must enter a valid number not: " + trash);
            }
        } while (!done);

        return value;
    }

    /**
     * Get a valid double integer, number or decimal within a range
     *
     * @param pipe   Scanner the user needs for input
     * @param prompt information that user needs to enter
     * @param low    lowest part of the range
     * @param high   highest part of the range
     *               return only happens if user gives good input
     */
    public static double getRangedDouble(Scanner pipe, String prompt, int low, int high) {
        double value = 0.0;
        String trash = "";
        boolean done = false;

        do {
            System.out.print("\n" + prompt + "[" + low + " - " + high + "]: ");
            if (pipe.hasNextDouble()) {
                value = pipe.nextDouble();
                pipe.nextLine(); //Clears the buffer
                if (value >= low && value <= high)
                    done = true;
                else
                    System.out.println("\nYou entered a number out of range [" + low + " - " + high + "] not: " + value);

            } else {
                trash = pipe.nextLine();
                System.out.println("\nYou must enter a valid number not: " + trash);
            }
        } while (!done);

        return value;
    }

    /**
     * Gets a string within a pattern
     *
     * @param pipe         Gets the input from the user
     * @param prompt       Tells the user the information that is needed
     * @param regExPattern is the pattern of the information that is needed
     *                     return only if the user provides valid input
     */

    public static String getRegExString(Scanner pipe, String prompt, String regExPattern) {
        String info = "";
        boolean haveInfo = false;

        do {

            System.out.print(prompt + ":");
            //input data and test if it is correct
            info = pipe.nextLine();
            if (info.matches(regExPattern)) {
                haveInfo = true;
            } else {
                System.out.println("\nEnter valid information and not: " + info);
            }

        } while (!haveInfo);

        return info;

    }

    /**
     * Gets Y/N to continue whether it is upper or lower case
     *
     * @param pipe   This gets the users input
     * @param prompt This prompts the user for the input
     */

    public static boolean getYNConfirm(Scanner pipe, String prompt) {

        String response = "";
        boolean done = false;
        boolean confirm = false;

        do {
            System.out.print(prompt);
            response = pipe.nextLine();
            if (response.equalsIgnoreCase("Y")) {
                confirm = true;
                done = true;
            } else if (response.equalsIgnoreCase("N")) {
                confirm = true;
                done = true;
            } else {
                System.out.println("Not a valid response please try again:");
            }

        } while (!done);

        return confirm;
    }

    /**
     *
     * @param message
     *
     * Prints out a message header
     */
    public static void prettyHeader(String message) {

        int totalSpaces = 60 - 6 - message.length();
        int rightSpace;
        int leftSpace;

        if (totalSpaces % 2 == 0) {
            leftSpace = rightSpace = totalSpaces / 2;
        } else {
            rightSpace = totalSpaces / 2;
            leftSpace = rightSpace - 1;
        }

        for (int i = 0; i <= 61; i++) {
            System.out.print("*");
        }
        System.out.println();
        System.out.print("***");

        for (int i = 0; i <= leftSpace + 1; i++) {
            System.out.print(" ");

        }
        System.out.print(message);

        for (int i = 0; i <= rightSpace + 1; i++) {
            System.out.print(" ");
        }
        System.out.print("***");

        System.out.println();
        for (int i = 0; i <= 61; i++) {
            System.out.print("*");
        }
    }









}

