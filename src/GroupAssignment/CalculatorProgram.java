import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Task2
 */
public class CalculatorProgram {

    private Scanner _input;

    public CalculatorProgram() {
        _input = new Scanner(System.in);
    }

    public enum Action {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        HELP,
        QUIT
    }
    
    public static void main(String[] args) {
        CalculatorProgram calculatorProgram = new CalculatorProgram();
        calculatorProgram.run();
    }

    public void run()
    {
        String choosedAction;

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\t\tWelcome to this weak calculator! :D");
        System.out.println("-----------------------------------------------------------------------\n");

        while (true) {
            System.out.println("What action do you wish to perform?");
            System.out.println("Type \"help\" to view available choices or \"quit\" to exit program");
            choosedAction = _input.next();

            if (choosedAction.equals("help")) {
                showChoice();               
            }
            else if (choosedAction.equals("+"))
            {
                calculate(Action.ADD);
            }
            else if (choosedAction.equals("-"))
            {
                calculate(Action.SUBTRACT);
            }
            else if (choosedAction.equals("*"))
            {
                calculate(Action.MULTIPLY);
            }
            else if (choosedAction.equals("/"))
            {
                calculate(Action.DIVIDE);
            }
            else if (choosedAction.equals("quit")) {
                quitProgram();
                return;
            }
            else {
                System.out.println("\n-------------------------------------------------------");
                System.out.println("ERROR: Invalid value, please try again");
                System.out.println("-------------------------------------------------------\n");               
            }
        }
    }

    public void calculate(Action action) {
        try {
            switch (action) {
                case ADD: {
                    System.out.println("Please enter 2 numbers:");
                    double num1 = _input.nextDouble();
                    double num2 = _input.nextDouble();
                    add(num1, num2);
                    break;
                }
                case SUBTRACT: {
                    System.out.println("Please enter 2 numbers:");
                    double num1 = _input.nextDouble();
                    double num2 = _input.nextDouble();
                    subtract(num1, num2);
                    break;
                }   
                case MULTIPLY: {
                    System.out.println("Please enter 2 numbers:");
                    double num1 = _input.nextDouble();
                    double num2 = _input.nextDouble();
                    multiply(num1, num2);
                    break;
                }
                case DIVIDE: {
                    System.out.println("Please enter 2 numbers:");
                    double num1 = _input.nextDouble();
                    double num2 = _input.nextDouble();
                    divide(num1, num2);
                    break;
                }
                case HELP: {
                    showChoice();
                    break;
                }
                case QUIT: {
                    quitProgram();
                    break;
                }               
            }
        } 
        catch (InputMismatchException e) {
            System.out.println("\n-------------------------------------------------------");
            System.out.println("ERROR: Invalid value, please try again");
            System.out.println("-------------------------------------------------------\n");
            return;
        }      
    }

    public void add(double num1, double num2) {
        System.out.println("\n-------------------------------------------------------");
        System.out.printf("%.1f + %.1f = %.1f\n", num1, num2, num1 + num2);
        System.out.println("-------------------------------------------------------\n");
    }

    public void subtract(double num1, double num2) {
        System.out.println("\n-------------------------------------------------------");
        System.out.printf("%.1f - %.1f = %.1f\n", num1, num2, num1 - num2);
        System.out.println("-------------------------------------------------------\n");
    }

    public void multiply(double num1, double num2) {
        System.out.println("\n-------------------------------------------------------");
        System.out.printf("%.1f * %.1f = %.1f\n", num1, num2, num1 * num2);
        System.out.println("-------------------------------------------------------\n");
    }

    public void divide(double num1, double num2) {
        System.out.println("\n-------------------------------------------------------");
        System.out.printf("%.1f / %.1f = %.1f\n", num1, num2, num1 / num2);
        System.out.println("-------------------------------------------------------\n");
    }

    public void showChoice() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Here a list of available actions:");
        System.out.println("\"help\" - displays all available actions");
        System.out.println("  \"+\"  - Performs addition between 2 numbers");
        System.out.println("  \"-\"  - Performs subtraction between 2 numbers");
        System.out.println("  \"*\"  - Performs multiplication between 2 numbers");
        System.out.println("  \"/\"  - Performs division between 2 numbers");
        System.out.println("\"quit\" - Quits the program");
        System.out.println("-------------------------------------------------------\n");
    }

    public void quitProgram() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Quitted program!");
        System.out.println("-------------------------------------------------------\n");
        System.exit(0);
    }
}