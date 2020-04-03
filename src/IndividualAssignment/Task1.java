/**
 * 
 * Written by Sukhrobbek Ilyosbekov
 * Source code available in https://github.com/suxrobGM/JavaCourseworkAssignments
 * 
 **/
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Task1 
 * Code was written by Sukhrobbek Ilyosbekov
 */
public class Task1 {

    private Scanner _input;

    public Task1() {
        _input = new Scanner(System.in);
    }

    /**
     * SalesPerson
     */
    public class SalesPerson {
        public SalesPerson()
        {
        }

        public SalesPerson(String name, int id, int age, float grossSales)
        {
            this.name = name;
            this.age = age;
            this.id = id;
            this.grossSales = grossSales;
        }

        public String name;
        public int age;
        public int id;
        public float grossSales; 

        public float getComissionValue() {
            return 0.09f * grossSales;
        }

        public float getTotalEarnings() {
            return 200.f + getComissionValue();
        }
    }

    public static void main(String[] args) {
        Task1 salesPersonTask = new Task1();
        salesPersonTask.run();
    }

    public void run() {
        String choosedAction;

        while (true) {
            System.out.println("\nWhat action do you wish to perform?");
            System.out.println("Note: if you want to exit program in any time just press CTRL+C");
            System.out.println("\nType \"run\" to start or \"quit\" to exit program");
            choosedAction = _input.next();

            if (choosedAction.equals("run")) {
                displayWelcomeMessage();
                process();
            }
            else if (choosedAction.equals("quit")) {
                quitProgram();
                return;
            }
            else {
                displayErrorMessage("Invalid value, please try again");
            }
        }
    }

    private void process() {
        int salesPersonNumbers = 0;

        while (true) {
            try {
                System.out.print("Please enter number of salespersons: ");
                salesPersonNumbers = _input.nextInt(); // Check numbers should be more than or equal to 3

                if (salesPersonNumbers < 3) {
                    displayErrorMessage("There should be a minimum of 3 salespersons");
                    continue;
                }
                else{
                    break;
                }
            } 
            catch (InputMismatchException e) {
                displayErrorMessage("Invalid value, the input is NOT INTEGER value, please try again");
                _input.nextLine();
                continue;
            }
        }
        
        SalesPerson salesPerson;
        String salespersonName = "";
        String numberPostfix = "";
        int actionChoosed = 1;
        int salespersonAge = 0;
        int itemNumber = 0;
        int quantity = 0;
        float grossSales = 0;
        boolean flagInputUsername = false;
        boolean flagInputItemNumber = false;
        boolean flagInputQuantity = false;


        for (int i = 1; i <= salesPersonNumbers; i++) {
            try {
                if (i == 1) {
                    numberPostfix = "st";
                }
                else if (i == 2) {
                    numberPostfix = "nd";
                }
                else if (i == 3) {
                    numberPostfix = "rd";
                }
                else {
                    numberPostfix = "th";
                }

                if (!flagInputUsername) {
                    System.out.print(i + numberPostfix + " salesperson's name: ");
                    salespersonName = _input.next();
                    flagInputUsername = true;
                }

                if (!isValidUsername(salespersonName)) {
                    displayErrorMessage("Invalid staff name, there should be only alphabetic symbols, please try again");
                    flagInputUsername = false;
                    i--;
                    continue;
                }
          
                System.out.print(i + "st salesperson's age: ");
                salespersonAge = _input.nextInt();

                if (salespersonAge < 16) {
                    throw new InputMismatchException();
                }

                // Reset flag
                flagInputUsername = false;
            } 
            catch (InputMismatchException e) {
                displayErrorMessage("Invalid value, the input is NOT valid person age, should be more than 16 y.o, please try again");
                _input.nextLine();
                i--;
                continue;
            }

            while (true) {
                try {
                    if (!flagInputItemNumber) {
                        displayItemsTable();
                        System.out.print("Please enter assigned number of the item sold: ");
                        itemNumber = _input.nextInt();

                        if (itemNumber <= 0 || itemNumber > 5) {
                            displayErrorMessage("Please enter a number between 1 to 5");
                            continue;
                        }

                        flagInputItemNumber = true;
                    }

                    if (!flagInputQuantity) {
                        System.out.print("Please enter the quantity of the item sold: ");
                        quantity = _input.nextInt();
                        flagInputQuantity = true;
                    }
                    
                    grossSales = getItemValue(itemNumber)*quantity;
                    salesPerson = new SalesPerson(salespersonName, i, salespersonAge, grossSales);

                    System.out.println("Is there any other item sold by this salesperson?");
                    System.out.println("1 - Yes");
                    System.out.println("2 - No\n");
                    System.out.print("1 or 2: ");
                    actionChoosed = _input.nextInt();

                    if (actionChoosed == 2) {
                        displayReceipt(salesPerson);

                        // Reset flags
                        flagInputItemNumber = false;
                        flagInputQuantity = false;
                        break;
                    }

                    // Reset flags
                    flagInputItemNumber = false;
                    flagInputQuantity = false;
                } 
                catch (InputMismatchException e) {
                    displayErrorMessage("Invalid value, the input is NOT INTEGER value, please try again");
                    _input.nextLine();
                    continue;
                }
            }    
        }
    }

    private void displayReceipt(SalesPerson salesPerson) {
        System.out.println("\nName of salesperson:\t\t" + salesPerson.name);
        System.out.println("Salesperson's age:\t\t" + salesPerson.age);
        System.out.println("Gross sales this week:\t\tRM " + salesPerson.grossSales);
        System.out.println("Comission (9% of gross sales):\tRM " + salesPerson.getComissionValue());
        System.out.println("Earnings for this week:\t\tRM " + salesPerson.getTotalEarnings() + "\n");
    }

    private void displayItemsTable() {
        System.out.println();
        System.out.println(" _________________________________________________________");
        System.out.println("|         Item               |          Value (RM)        |");
        System.out.println("|                            |                            |");
        System.out.println("|           1                |             450.50         |");
        System.out.println("|           2                |             300.25         |");
        System.out.println("|           3                |             250.33         |");
        System.out.println("|           4                |             50.45          |");
        System.out.println("|           5                |             2780.70        |");
        System.out.println("|____________________________|____________________________|");
        System.out.println();
    }

    private void displayWelcomeMessage() {
        System.out.println("\t\t\t7 Eleven");
        System.out.println("\t\t\tJalan SS 16/1,");
        System.out.println("\t\t\tSS16, 47500 Subang Jaya,");
        System.out.println("\t\t\tSelangor, Malaysia.\n");
        System.out.println("********************************************************************************");
        System.out.println("*                                                                              *");
        System.out.println("*                   Weekly gross sales by salesperson                          *");
        System.out.println("*                                                                              *");
        System.out.println("********************************************************************************\n");
    }

    private void displayErrorMessage(String errorMsg) {
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("ERROR: " + errorMsg);
        System.out.println("-----------------------------------------------------------------------\n");
    }

    private void quitProgram() {
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("Quitted program!");
        System.out.println("-----------------------------------------------------------------------\n");
        System.exit(0);
    }

    private float getItemValue(int itemNumber)
    {
        if (itemNumber == 1) {
            return 450.50f;
        } 
        else if (itemNumber == 2) {
            return 300.25f;
        }
        else if (itemNumber == 3) {
            return 250.33f;
        }
        else if (itemNumber == 4) {
            return 50.45f;
        }
        else if (itemNumber == 5) {
            return 2780.70f;
        }
        else {
            return 0;
        }
    }

    private boolean isValidUsername(String name) {
        boolean isValidName = true;
        char[] nameChars = name.toCharArray();

        for (char c : nameChars) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                isValidName = false;
                break;
            }
        }

        return isValidName;
    }
}