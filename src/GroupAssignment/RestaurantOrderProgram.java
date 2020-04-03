/**
 * 
 * Written by Sukhrobbek Ilyosbekov
 * Source code available in https://github.com/suxrobGM/JavaCourseworkAssignments
 * 
 **/
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Task3
 */
public class RestaurantOrderProgram {

    private Scanner _input;
    private ArrayList<Order> _orders;
    private String _customerName;
    private String _customerIC;
    private Order _currentOrder;
    private boolean _isContinueOrdering;

    public RestaurantOrderProgram() {
        _input = new Scanner(System.in);
        _orders = new ArrayList<Order>();
        _currentOrder = new Order();
        _customerName = "";
        _customerIC = "";
    }

    // Inner class
    public class Order {
        public String name;
        public float price;
        public float discount;
        public int quantity;

        public Order() {
            name = "";
        }

        public Order(String name, float price, float discount, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.discount = discount;
        }

        public float getSubtotal() {
            return price * quantity * ((100.0f - discount) / 100.0f);
        }
    }

    public static void main(String[] args) {
        RestaurantOrderProgram restaurantProgram = new RestaurantOrderProgram();
        restaurantProgram.run();
    }

    public void run() {
        String choosedAction;
        
        while (true) {
            if (!_isContinueOrdering) {
                welcomeMessage();
                System.out.println("What would you like to do?");
                System.out.println("Type \"order\" to begin placing order or \"quit\" to exit program");
                choosedAction = _input.next();
            }
            else {
                choosedAction = "order";
            }

            if (choosedAction.equals("order")) {
                showMenu();

                System.out.println("What would you like to order?");
                System.out.println("Place order by entering the number next to the food: ");
                int menuChoice = _input.nextInt();

                switch (menuChoice) {
                    case 1: {
                        _currentOrder = new Order("Fried Rice", 7.5f, 5.0f, 0);
                        showItem();
                        showSubtotal();

                        if (!_isContinueOrdering) {
                            showReceipt();
                        }
                        break;
                    }
                    case 2: {
                        _currentOrder = new Order("Laksa", 8.5f, 0, 0);
                        showItem();
                        showSubtotal();

                        if (!_isContinueOrdering) {
                            showReceipt();
                        }
                        break;
                    }
                    case 3: {
                        _currentOrder = new Order("Chicken Chop", 4.0f, 5.0f, 0);
                        showItem();
                        showSubtotal();

                        if (!_isContinueOrdering) {
                            showReceipt();
                        }
                        break;
                    }
                    case 4: {
                        _currentOrder = new Order("Kolo Mee", 8.0f, 10.0f, 0);
                        showItem();
                        showSubtotal();

                        if (!_isContinueOrdering) {
                            showReceipt();
                        }
                        break;
                    }
                    case 5: {
                        _currentOrder = new Order("Seafood", 12.0f, 0, 0);
                        showItem();
                        showSubtotal();

                        if (!_isContinueOrdering) {
                            showReceipt();
                        }
                        break;
                    }
                    case 6: {
                        _currentOrder = new Order("Spaghetti", 9.0f, 5.0f, 0);
                        showItem();
                        showSubtotal();

                        if (!_isContinueOrdering) {
                            showReceipt();
                        }
                        break;
                    }
                    case 7: {
                        _isContinueOrdering = false;
                        showReceipt();
                        break;
                    }    
                    default: {
                        System.out.println("\n-----------------------------------------------------------------------");
                        System.out.println("ERROR: Invalid value, please try again");
                        System.out.println("-----------------------------------------------------------------------\n");
                    }
                }
            }
            else if (choosedAction.equals("quit")) {
                quitProgram();
                return;
            }
            else {
                System.out.println("\n-----------------------------------------------------------------------");
                System.out.println("ERROR: Invalid value, please try again");
                System.out.println("-----------------------------------------------------------------------\n");               
            }
        }
    }

    public void showMenu()
    {
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("\t\t\t ORDER");
        System.out.println("\t\t\t---------\n");
        
        if (!_isContinueOrdering) {
            inputNameIC();
        }

        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Here is our menu, please have a look: \n");
        System.out.println(" (1) Fried Rice - RM 7.50 (5.0% Discount)");
        System.out.println(" (2) Laksa - RM 8.50");
        System.out.println(" (3) Chicken Chop - RM 4.00 (5.0% Discount)");
        System.out.println(" (4) Kole Mee - RM 8.00 (10.0% Discount)");
        System.out.println(" (5) Seafood - RM 12.00");
        System.out.println(" (6) Spaghetti - RM 9.00 (5.0% Discount)");
        System.out.println(" (7) Checkout");
        System.out.println("-----------------------------------------------------------------------");      
    }

    public void showItem() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\t\t\t ITEM:");
        System.out.println("\t\t\t---------");
        System.out.println("Order: " + _currentOrder.name);
        System.out.println("Price: " + _currentOrder.price);
        System.out.println("Current quantity: 0");
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("Please enter the quantity: ");
        _currentOrder.quantity = _input.nextInt();
        _orders.add(_currentOrder);
    }

    public void showSubtotal() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\t\t\t SUBTOTAL:");
        System.out.println("\t\t\t---------");
        System.out.println("Order: ");

        float subtotal = 0.0f;
        for (Order order : _orders) {
            if (order.discount > 0) {
                System.out.printf("%s : %d * RM %.2f - %.2f%% = RM %.2f\n", order.name, order.quantity, order.price, order.discount, order.getSubtotal());    
            } 
            else {
                System.out.printf("%s : %d * RM %.2f = RM %.2f\n", order.name, order.quantity, order.price, order.getSubtotal());
            }
            
            subtotal += order.getSubtotal();
        }
        
        System.out.printf("Subtotal: RM %.2f\n", subtotal);
        System.out.println("-----------------------------------------------------------------------\n");

        System.out.println("Would you like to continue ordering?");
        System.out.println("Enter \"y\" to add more items or \"n\" to checkout");
        String userSelect = _input.next();

        if (userSelect.equals("y")) {
            _isContinueOrdering = true;
        } 
        else {
            _isContinueOrdering = false;
        }
    }

    public void showReceipt() {
        System.out.println("\n-----------------------------------------------------------------------");
        System.out.println("\t\t\t RECEIPT");
        System.out.println("\t\t\t---------\n");
        System.out.println("Thank you for eating at this restaurant!\n");
        System.out.println("Name:\t" + _customerName);
        System.out.println("IC / Passport: " + _customerIC);
        System.out.println("\nOrder:\n");
        
        float subtotal = 0.0f;
        for (Order order : _orders) {
            if (order.discount > 0) {
                System.out.printf("%s : %d * RM %.2f - %.2f%% = RM %.2f\n", order.name, order.quantity, order.price, order.discount, order.getSubtotal());    
            } 
            else {
                System.out.printf("%s : %d * RM %.2f = RM %.2f\n", order.name, order.quantity, order.price, order.getSubtotal());
            }

            subtotal += order.getSubtotal();
        }

        float sst = subtotal * 0.02f;
        float grandTotal = subtotal + sst;

        System.out.printf("\nSubtotal: RM %.2f\n", subtotal);
        System.out.printf("SST: RM %.2f\n", sst);
        System.out.printf("Grand Total: RM %.2f\n", grandTotal);
        System.out.println("\n\t\tPlease enjoy your meal!");
        System.out.println("-----------------------------------------------------------------------\n");
    }

    public void inputNameIC() {
        System.out.print("Please enter your name: ");
        _customerName = _input.next();

        System.out.print("Please enter your IC / Passport number: ");
        _customerIC = _input.next();
    }

    public void welcomeMessage() {
        System.out.println("-----------------------------------------------------------------------");
        System.out.println("\t\tWelcome To This Restaurant!");
        System.out.println("-----------------------------------------------------------------------\n");
    }

    public void quitProgram() {
        System.out.println("\n-------------------------------------------------------");
        System.out.println("Quitted program!");
        System.out.println("-------------------------------------------------------\n");
        System.exit(0); // Exit from console
    }
}