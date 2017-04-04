/******************************************************************************
 *
 * Names:
 * Eric Duresne 6498471
 * Mohammad Hasan Kanjee 6795550
 * Course Code: SEG 4145
 * Lab Number: 4
 * File name: CommandLineShell.java
 * Date: March 22nd, 2017
 *
 *
 * Description
 * *************
 * This piece of code is initialized and ran from Main.java. This is the client shell
 * that keeps the user in a loop until exit. The prompt will be ran in repeat and when
 * valid commands are entered the commands are sent as byte arrays to the robot. Descriptions
 * of how the client communicates are shown above each function.
 *
 ******************************************************************************
 */

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class CommandLineShell {
    /**
     * Prompt each time a user is asked for a command
     */
    private String prompt;

    /**
     * Intro that is printed before the first prompt
     */
    private String intro;

    /**
     * In loop keeps the loop running until the "quit" function is ran
     */
    private boolean inLoop;

    /**
     * Takes keyboard inputs
     */
    private Scanner keyboard;

    /**
     * Port Number constant that is used to open up a client socket to the wifi server from the robot.
     * 80 as this is the default for HTTP communication
     */
    private static final int portNumber = 80;

    /**
     * Host name for the robots server. This is the IP address for the stingray wifi in which the robot
     * has been set to open a server with
     */
    private static final String host = "192.168.1.133";

    /**
     * The socket that is opened with the host and port.
     */
    private Socket socket;

    /**
     * A buffered writer is opened once the socket has connected to the robot. This output takes all the
     * commands from the user and sends them to the robot with a buffer in the middle.
     */
    private BufferedWriter output;

    /**
     * Constructor to start the command shell. This sets up keyboard inputs and initializes variables.
     * @param intro message shown at the start
     * @param prompt message shown before each command
     * @throws IOException
     */
    public CommandLineShell(String intro) throws IOException{
        this.intro = intro;
        //this.prompt = prompt;
        this.inLoop = false;
        this.keyboard = new Scanner(System.in);
    }

    /**
     * Opens a socket with the host and port. Throws IOException if the connection is refused
     * @throws IOException
     */
    public void waitForConnection() throws IOException{
        this.socket = new Socket(host, portNumber);
    }

    /**
     * Sets up buffered output writer from the socket. If the sockets stream cannot be created IOException is thrown
     * @throws IOException
     */
    public void setupStreams() throws IOException{
        OutputStream stream = this.socket.getOutputStream();
        output = new BufferedWriter(new OutputStreamWriter(stream));
    }

    /**
     * Starts execution. Calls the above methods to open a socket with a buffered writer. Enters the loop upon success
     * and breaks out once a quit is called. Takes integer input from the user and calls the specific method based on
     * the command sent. If a command is not valid, it is passed on.
     * @throws IOException
     */
    public void start() throws IOException{
        //waitForConnection();
        //setupStreams();
        this.inLoop = true;

        System.out.println(this.intro);
        while(inLoop){
            System.out.print(this.prompt);
            String line = keyboard.nextLine();
            Integer i;
            try{
                i = Integer.parseInt(line.trim());
            }
            catch (NumberFormatException e){
                System.out.println("Not a valid command");
                continue;
            }
            switch (i){
                case 1:
                    moveForward();
                    break;
                case 2:
                    moveBackward();
                    break;
                case 3:
                    rotateClockwise();
                    break;
                case 4:
                    rotateCounterClockwise();
                    break;
                case 5:
                    readDistance();
                    break;
                case 6:
                    readTemperature();
                    break;
                case 7:
                    quit();
                    break;
                default:
                    System.out.println("Not a valid command");
                    break;
            }
        }
    }

    /**
     * Asks user for distance argument then writes value to robot if valid. Moves forward
     * @throws IOException
     */
    public void moveForward() throws IOException{
        Integer arg = getArg("How many cm? (0 to 20 cm)");
        if(arg == null){
            System.out.println("Invalid. Value must be in between 0 and 20cm");
        }
        else if (arg > 20){
            System.out.println("Invalid. Value must be in between 0 and 20cm");
        }
        else if (arg != 0){
            writeValue("forward "+arg);
        }
    }

    /**
     * Asks user for distance argument then writes value to robot if valid. Moves backward
     * @throws IOException
     */
    public void moveBackward() throws IOException{
        Integer arg = getArg("How many cm? (0 to 20 cm)");
        if(arg == null){
            System.out.println("Invalid. Value must be in between 0 and 20cm");
        }
        else if (arg > 20){
            System.out.println("Invalid. Value must be in between 0 and 20cm");
        }
        else if (arg != 0){
            writeValue("backward "+arg);
        }
    }

    /**
     * Asks user for rotation argument then writes value to robot if valid. Rotates clockwise
     * @throws IOException
     */
    public void rotateClockwise() throws IOException {
        Integer arg = getArg("How many degrees? (0 to 359 deg)");
        if(arg == null){
            System.out.println("Invalid. Value must be in between 0 and 359 deg");
        }
        else if (arg > 359){
            System.out.println("Invalid. Value must be in between 0 and 359 deg");
        }
        else if (arg != 0){
            writeValue("clockwise "+arg);
        }

    }

    /**
     * Asks user for rotation argument then writes value to robot if valid. Rotates counter-clockwise
     * @throws IOException
     */
    public void rotateCounterClockwise() throws IOException {
        Integer arg = getArg("How many degrees? (0 to 359 deg)");
        if(arg == null){
            System.out.println("Invalid Input");
        }
        else if (arg > 359){
            System.out.println("Invalid. Value must be in between 0 and 359");
        }
        else if (arg != 0){
            writeValue("counter_clockwise "+arg);
        }

    }

    /**
     * Sends distance command to robot.
     * @throws IOException
     */
    public void readDistance() throws IOException {
        writeValue("distance");

    }

    /**
     * Sends read temperature command to robot.
     * @throws IOException
     */
    public void readTemperature() throws IOException {
        writeValue("temperature");

    }

    /**
     * Closes connection to the robot and exits the command shell loop
     * @throws IOException
     */
    public void quit() throws IOException {
        output.close();
        socket.close();
        this.inLoop = false;
    }

    /**
     * Helper method to write a value to the command string (command, a space, then arguments). Concatenates the string
     * with a new line so that the robot can interpret this as the end of the command.
     * @throws IOException
     */
    public void writeValue(String value) throws IOException {
        output.write(value+"\n");
        output.flush();
    }

    /**
     * Helper method that displays the prompt for the specific command. The value inputted is read and parsed. The value
     * is then returned or null if invalid.
     * @param prompt will be the message asking for another input
     * @return the integer value that the user inputed, or null if the value was not an integer or negative.
     */
    private Integer getArg(String prompt){
        System.out.println(prompt);
        String arg = keyboard.nextLine();
        try{
            Integer i = Integer.parseInt(arg);
            if (i < 0){
                return null;
            }
            return i;
        }
        catch (NumberFormatException e){
            return null;
        }
    }

}
