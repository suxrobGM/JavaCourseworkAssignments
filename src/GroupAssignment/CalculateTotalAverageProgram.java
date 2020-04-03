import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Task1
 */
public class CalculateTotalAverageProgram {

    private Scanner _input;

    // Default constructor
    public CalculateTotalAverageProgram() {
        _input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        CalculateTotalAverageProgram calculateTotalAverageProgram = new CalculateTotalAverageProgram();
        calculateTotalAverageProgram.run();
    }
    
    public void run() {
        String choosedAction;

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\t\tWelcome to the program! :)");
        System.out.println("This program will calculate total and average of 5 integer numbers");
        System.out.println("-----------------------------------------------------------------------");

        while (true) {
            System.out.println("\nWhat action do you wish to perform?");
            System.out.println("\nType \"run\" to begin calculation or \"quit\" to exit program");
            choosedAction = _input.next();

            if (choosedAction.equals("run")) {
                calculate();
            }
            else if (choosedAction.equals("quit")) {
                quitProgram();
                break;
            }
            else {
                System.out.println("\n-------------------------------------------------------");
                System.out.println("ERROR: Invalid value, please try again");
                System.out.println("-------------------------------------------------------\n");               
            }
        }
    }

    public void calculate() {
        System.out.println("Please key 5 integers");
        int number = 0;
        int sum = 0;
        float average = 0.0f;

        try {
            for (int i = 0; i < 5; i++) {
                number = _input.nextInt();
                sum += number;
            } 
        } 
        catch (InputMismatchException e) {
            System.out.println("Invalid value, this is not an integer.");     
            return;
        }
        
        average = sum / 5.0f;
        
        System.out.println("-------------------------------------------------------");
        System.out.printf("Sum: %d\n", sum);
        System.out.printf("Average: %.1f\n", average);
        System.out.println("-------------------------------------------------------");
    }

    public void quitProgram() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Quitted program!");
        System.out.println("-------------------------------------------------------\n");
        System.exit(0);
    }
}