/******************************************************************************
 *
 * Names:
 * Eric Duresne 6498471
 * Mohammad Hasan Kanjee 6795550
 * Course Code: SEG 4145
 * Lab Number: 4
 * File name: Main.java
 * Date: March 22nd, 2017
 *
 *
 * Description
 * *************
 * This is the main function of the client for lab 4. This initializes the clients
 * command line shell in which the user will enter commands which will be sent
 * to the robot and interpreted. This code is reusable with different intros and prompts
 * as needed. If connection fails or dropped the program will exit.
 *
 ******************************************************************************
 */
import java.io.IOException;

public class Main {

    /**
     * Main Function
     * @param args not used command line arguments
     */
    public static void main(String[] args){
        String[] prompts = {
                "1 - Move the Robot Forward",
                "2 - Move the Robot Backward",
                "3 - Rotate the robot Clockwise",
                "4 - Rotate the robot Counter-Clockwise",
                "5 - Read the distance to the nearest object",
                "6 - Read temperature values",
                "7 - Quit"
        };
        String prompt = "";
        for (String p : prompts){
            prompt = prompt + p + "\n";
        }
        try{
            CommandLineShell shell = new CommandLineShell("Welcome to Lab 4 Command Line Tool");
            System.out.println("Connecting...");
            shell.start();
        }
        catch(IOException e){
            System.out.println("Lost connection");
        }

    }
}
