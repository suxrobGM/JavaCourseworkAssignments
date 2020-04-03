import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Task4
 */
public class PetrolAllowanceProgram {

    private Scanner _input;

    public PetrolAllowanceProgram() {
        _input = new Scanner(System.in);
    }

    public static void main(String[] args) {
        PetrolAllowanceProgram petrolProgram = new PetrolAllowanceProgram();
        petrolProgram.run();    
    }

    public void run() {
        String choosedAction;

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\t\tWelcome to the program! :)");
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
        System.out.println("Enter staff name:");
        String staffName = _input.next();
        float distance = 0;

        try {
            System.out.println("Enter distance travelled in kilometers");
            distance = _input.nextFloat();
        } 
        catch (InputMismatchException e) {
            System.out.println("Invalid value, this is not a number.");
            return;
        }
        
        System.out.println("\n-------------------------------------------------------\n");
        System.out.println("Final receipt:\n");
        System.out.println("Name: " + staffName);
        System.out.printf("Distance travelled: %.1fkm\n\n", distance);

        float totalPrice = 0.0f;
        float restDistance = distance;

        if (restDistance >= 10) {
            totalPrice += 10.0f * 1.80f;
            restDistance -= 10;
            System.out.printf("First 10 km: 10.0km * RM 1.80 = RM %.2f\n", 10.0f * 1.80f);
        }
        else if (restDistance > 0 && restDistance < 10) {
            totalPrice += restDistance * 1.80f;
            System.out.printf("First 10 km: %.1fkm * RM 1.80 = RM %.2f\n", restDistance, restDistance * 1.80f);
        }

        //float secondDistance = restDistance - 50;
        if (restDistance >= 50) {
            totalPrice += 50.0f * 1.50f;
            restDistance -= 50;
            System.out.printf("Next 50 km: 50.0km * RM 1.50 = %.2f\n", 50.0f * 1.50f);
        }
        else if (restDistance > 10 && restDistance < 50) {
            totalPrice += restDistance * 1.50f;
            System.out.printf("*Next 50 km: %.1fkm * RM 1.50 = RM %.2f\n", restDistance, restDistance * 1.50f);
        }

        //float thirdDistance = restDistance - 50;
        if (restDistance >= 50) {
            totalPrice += 50.0f * 1.30f;
            restDistance -= 50;
            System.out.printf("Next 50 km: 50.0km * RM 1.30 = %.2f\n", 50.0f * 1.30f);
        }
        else if (restDistance > 0 && restDistance < 50) {
            totalPrice += restDistance * 1.30f;
            System.out.printf("*Next 50 km: %.1fkm * RM 1.30 = RM %.2f\n", restDistance, restDistance * 1.30f);     
        }

        //float fourthDistance = restDistance - 100;
        if (restDistance > 110) {
            totalPrice += restDistance * 1.00f;
            System.out.printf("Beyond 100km: %.1fkm * RM 1.00 = RM %.2f\n", restDistance, restDistance * 1.00f);
        }

        if (totalPrice > 150) {
            System.out.printf("Allowance: RM %.2f + 5%% = RM %.2f\n", totalPrice, totalPrice * 1.05f);
        } 
        else {
            System.out.printf("Allowance: RM %.2f\n", totalPrice);
        }
        
        System.out.println("\n-------------------------------------------------------\n");
    }

    public void quitProgram() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Quitted program!");
        System.out.println("-------------------------------------------------------\n");
        System.exit(0);
    }
}