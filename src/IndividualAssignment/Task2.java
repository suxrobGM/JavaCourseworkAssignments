import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Task2
 */
public class Task2 {

    /**
     * Class instance variables
     */
    private Scanner _input;
    private String _username;
    private QuestionsType _selectedTest;
    private long _startTime;
    private ArrayList<Test> _tests;

    public Task2() {
        _input = new Scanner(System.in);
        _tests = new ArrayList<Test>();
    }

    // Inner class
    public class Test {
        public Test(QuestionsType questionsType) {
            this.questionsType = questionsType;
        }

        public QuestionsType questionsType;
        public int firstNumber;
        public int secondNumber;
        public int userAnswer;
        public boolean userAnswerCorrect;

        public char getToken() {
            switch (questionsType) {
                case Multiplication: return '*';
                case Addition: return '+';
                case Division: return '/';
                case Subtraction: return '-';
                default: return '*';
            }
        }

        public int getRightAnswer() {
            switch (questionsType) {
                case Multiplication: return firstNumber*secondNumber;
                case Addition: return firstNumber+secondNumber;
                case Division: return (int)Math.round((double)firstNumber / (double)secondNumber);
                case Subtraction: return firstNumber-secondNumber;
                default: return Integer.MIN_VALUE;
            }
        }

        public boolean checkAnswer() {
            if (userAnswer == getRightAnswer()) {
                userAnswerCorrect = true;
            }
            else {
                userAnswerCorrect = false;
            }
            return userAnswerCorrect;
        }
    }

    public enum QuestionsType {
        Multiplication,
        Addition,
        Subtraction,
        Division
    }

    public static void main(String[] args) {
        Task2 salesPersonTask = new Task2();
        salesPersonTask.run();
    }

    public void run() {
        String choosedAction;

        while (true) {
            System.out.println("\nWhat action do you wish to perform?");
            System.out.println("\nType \"run\" to start or \"quit\" to exit program");
            choosedAction = _input.next();

            if (choosedAction.equals("run")) {
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

    // Drive code
    private void process() {
        displayWelcomeMessage();

        boolean isFirstRun = true;
        boolean flagInputTestNumber = false;
        boolean flagInputQuestionsNumber = false;
        int questionsNumber = 0;
        
        while (true) {
            if (!flagInputTestNumber) {
                try {
                    displayTests();
                    System.out.print("Enter test number: ");
                    int selectedTestNumber = _input.nextInt();
    
                    if (selectedTestNumber <= 0 || selectedTestNumber > 6) {
                        displayErrorMessage("Invalid value, input should be between 1-6 numbers, please try again");
                        _input.nextLine();
                        continue;
                    }

                    if (selectedTestNumber == 3) {
                        System.out.println("Note: For division operations round your answer to the nearest integer");
                    }
    
                    if (isFirstRun && selectedTestNumber == 5) {
                        displayErrorMessage("Plese solve at least one test then you can see overall result, please try again");
                        _input.nextLine();
                        continue;
                    }
                    else if (!isFirstRun && selectedTestNumber == 5) {
                        displayOverallResult();
                        break;
                    }
    
                    if (selectedTestNumber == 6) {
                        quitProgram();
                    }
    
                    _selectedTest = getQuestionsType(selectedTestNumber);
                } 
                catch (InputMismatchException e) {
                    displayErrorMessage("Invalid value, the input is NOT INTEGER value, please try again");
                    _input.nextLine();
                    continue;
                }

                flagInputTestNumber = true;
            }
            
            
            if (!flagInputQuestionsNumber) {
                try {
                    System.out.println("Note: You can select number of questions only between 5-8");
                    System.out.print("Enter number of questions: ");
                    questionsNumber = _input.nextInt(); // User input questins number

                    if (questionsNumber <= 4 || questionsNumber > 8) {
                        displayErrorMessage("Invalid value, number of questions should be between 5-8, please try again");
                        _input.nextLine();
                        continue;
                    }    
                } 
                catch (InputMismatchException e) {
                    displayErrorMessage("Invalid value, the input is NOT INTEGER value, please try again");
                    _input.nextLine();
                    continue;
                }
                
                flagInputQuestionsNumber = true;
            }

            if (isFirstRun) {
                System.out.print("Please enter the user name: ");
                _username = _input.next();

                if (!isValidUsername(_username)) {
                    displayErrorMessage("Invalid staff name, there should be only alphabetic symbols, please try again");
                    _input.nextLine();
                    continue;
                }
            }

            _startTime = System.currentTimeMillis();
            for (int i = 1; i <= questionsNumber; i++) {
                try {
                    switch (_selectedTest) {
                        case Multiplication: {
                            Test test = new Test(QuestionsType.Multiplication);
                            generateQuestion(i, test);
                            _tests.add(test);
                            break;
                        }
                        case Addition: {
                            Test test = new Test(QuestionsType.Addition);
                            generateQuestion(i, test);
                            _tests.add(test);
                            break;
                        }
                        case Division: {   
                            Test test = new Test(QuestionsType.Division);
                            generateQuestion(i, test);
                            _tests.add(test);
                            break;
                        }
                        case Subtraction: {
                            Test test = new Test(QuestionsType.Subtraction);
                            generateQuestion(i, test);
                            _tests.add(test);
                            break;
                        }
                        default:
                            break;
                    }
                } 
                catch (InputMismatchException e) {
                    displayErrorMessage("Invalid value, the input is NOT INTEGER value, please try again");
                    _input.nextLine();
                    i--;
                    continue;
                }
            }

            // Reset all flags
            isFirstRun = false;
            flagInputTestNumber = false;
            flagInputQuestionsNumber = false;
        }
    }

    private void generateQuestion(int questionNum, Test test) {
        int numberOne = getRandomNumberInRange(10, 99);
        int numberTwo = getRandomNumberInRange(10, 99);
        test.firstNumber = numberOne;
        test.secondNumber = numberTwo;

        System.out.printf("\n%d. What is %d %c %d  = ", questionNum, test.firstNumber, test.getToken(), test.secondNumber);
        int userAnswer = _input.nextInt();
        test.userAnswer = userAnswer;

        if (test.checkAnswer()) {
            System.out.println("Welldone..!! You are correct!");
        }
        else {
            System.out.println("Your answer is wrong.");
            System.out.printf("%d %c %d should be %d\n", test.firstNumber, test.getToken(), test.secondNumber, test.getRightAnswer());
        }
    }

    private void displayOverallResult() {
        long correctAnswersCount = _tests.stream().filter(x -> x.userAnswerCorrect == true).count();
        long performance = correctAnswersCount*100 / _tests.size();
        long endTime = System.currentTimeMillis();
        float testTime = (endTime - _startTime) / 1000F;
        System.out.println("\nUser name: " + _username);
        System.out.println("Correct count is " + correctAnswersCount);

        for (Test test : _tests) {
            if (test.userAnswerCorrect) {
                System.out.printf("%d %c %d = %d correct\n", test.firstNumber, test.getToken() ,test.secondNumber, test.userAnswer);
            } 
            else {
                System.out.printf("%d %c %d = %d wrong\n", test.firstNumber, test.getToken() ,test.secondNumber, test.userAnswer);
            }
        }
        System.out.println("-----------------------------------------------------------------------");
        System.out.printf("Test time is %.1f seconds\n", testTime);
        System.out.printf("Overall performance: %d%%\n", performance);
        System.out.println("-----------------------------------------------------------------------\n");
    }

    private int getRandomNumberInRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
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

    private QuestionsType getQuestionsType(int testNumber) {
        switch (testNumber) {
            case 1: return QuestionsType.Multiplication;
            case 2: return QuestionsType.Addition;
            case 3: return QuestionsType.Division;
            case 4: return QuestionsType.Subtraction;
            default: return QuestionsType.Multiplication;
        }
    }

    private void displayTests() {
        System.out.println("\t\t\tPossible List of Tests");
        System.out.println("\t\t\t----------------------------");
        System.out.println("Select the following available Test(s), which you would like to do");
        System.out.println("1. Multiplication");
        System.out.println("2. Addition");
        System.out.println("3. Division");
        System.out.println("4. Subtraction");
        System.out.println("5. Display the overall result");
        System.out.println("6. Quit");
    }

    private void displayWelcomeMessage() {
        System.out.println();
        System.out.println("**********************************************************");
        System.out.println("*  University of Massachusetts Boston                    *");
        System.out.println("*  100 William T. Morrissey Blvd. Boston, MA 02125-3393  *");
        System.out.println("*  Contact Phone: +1 617-287-5000                        *");
        System.out.println("**********************************************************\n");
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
}