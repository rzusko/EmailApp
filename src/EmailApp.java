import java.util.Scanner;

public class EmailApp {

    private static Scanner scanner = new Scanner(System.in);
    private static EmailDatabase edb = new EmailDatabase();
    private static Email email = null;

    public static void main(String[] args) {
        System.out.println("Welcome to EmailApp.");
        boolean exit = false;
        showOptions();

        while (!exit) {
            System.out.println("********************");
            System.out.print("Please choose one of the options (press 0 for list of available options: ");
            char option = scanner.nextLine().charAt(0);

            switch (option) {
                case '0':
                    showOptions();
                    break;
                case '1':
                    createNewEmail();
                    break;
                case '2':
                    displayInfo();
                    break;
                case '3':
                    setPassword();
                    break;
                case '4':
                    setMailboxCapacity();
                    break;
                case '5':
                    setAlternateEmail();
                    break;
                case 'i':
                case 'I':
                    addToDatabase();
                    break;
                case 'x':
                case 'X':
                    removeFromDatabase();
                    break;
                case 'f':
                case 'F':
                    findInDatabase();
                    break;
                case 'l':
                case 'L':
                    listAll();
                    break;
                case 's':
                case 'S':
                    saveDatabase();
                    break;
                case 'q':
                case 'Q':
                    if (!edb.isSaved()) {
                        System.out.println("Changes have been made in database. Do you want to save them?");
                        System.out.print("Press 'y' or 'Y' for \"yes\" or any other key for \"no\": ");
                        option = scanner.nextLine().charAt(0);
                        if ((option == 'y') || (option == 'Y')) {
                            saveDatabase();
                        }
                    }
                    System.out.println("Thank you for using our app.");
                    exit = true;
                    break;
                default:
                    System.out.println("There is no such option, please try again!");
            }
        }
    }

    private static void showOptions() {
        System.out.println("Available options:");
        System.out.println("\t0 - show this list");
        System.out.println("\t1 - create new email");
        System.out.println("\t2 - display informations about email");
        System.out.println("\t3 - set new password");
        System.out.println("\t4 - set mailbox capacity");
        System.out.println("\t5 - set alternate email address");
        System.out.println("\tI/i - add email to database");
        System.out.println("\tX/x - remove email from database");
        System.out.println("\tF/f - find in database");
        System.out.println("\tL/l - list all emails in database");
        System.out.println("\tS/s - save database");
        System.out.println("\tQ/q - exit");
    }

    private static void createNewEmail() {
        System.out.print("\nPlease enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Please enter last name: ");
        String lastName = scanner.nextLine();
        email = Email.createEmail(firstName, lastName);

        System.out.println("New email with following parameters was successfully created!");
        displayInfo();
        System.out.println("Do you want to add email to database?");
        System.out.print("Press 'y' of 'Y' for \"yes\" or any other key for \"no\": ");
        char option = scanner.nextLine().charAt(0);
        if ((option == 'y') || (option == 'Y')) {
            addToDatabase();
        }
    }

    private static void displayInfo() {
        if (email != null) {
            System.out.println(email.showInfo());
        } else {
            error();
        }
    }

    private static void setPassword() {
        if (email != null) {
            System.out.println("Please press: ");
            System.out.println("\t0 - to generate new random password");
            System.out.println("\tother - to enter password");

            int option = scanner.nextInt();
            scanner.nextLine();

            String password;

            if (option == 0) {
                System.out.print("Please enter the length of new password: ");
                int length = scanner.nextInt();
                scanner.nextLine();
                password = email.generatePassword(length);
            } else {
                System.out.print("Please enter new password: ");
                password = scanner.nextLine();
            }
            email.setPassword(password);
            System.out.println("New password: " + password + " successfully set!");
        } else {
            error();
        }
    }

    private static void setMailboxCapacity() {
        if (email != null) {
            System.out.print("Please enter new mailbox capacity in MB: ");
            int newCapacity = scanner.nextInt();
            scanner.nextLine();
            email.setMailboxCapacity(newCapacity);
            System.out.println("Mailbox capacity successfully set!");
        } else {
            error();
        }
    }

    private static void setAlternateEmail() {
        if (email != null) {
            System.out.print("Please enter new alternate email: ");
            String newAltEmail = scanner.nextLine();
            email.setAlternateEmail(newAltEmail);
            System.out.println("Alternate email successfully set!");
        } else {
            error();
        }
    }

    private static void addToDatabase() {
        if (email != null) {
            edb.addToDatabase(email);
            System.out.println("Email successfully added to database. Please save the database to make it permanent.");
        } else {
            error();
        }
    }

    private static void removeFromDatabase() {
        if (email != null) {
            System.out.println("Do you really want to remove email: " + email.getEmail() + "?");
            System.out.print("Press 'y' or 'Y' for \"yes\" or any other key for \"no\": ");
            char option = scanner.nextLine().charAt(0);
            if ((option == 'y') || (option == 'Y')) {
                edb.removeFromDatabase(email);
            } else {
                System.out.println("If you want to remove other email, please choose option 'f' of 'F' to find email to remove!");
            }
        } else {
            System.out.println("Please choose option 'f' of 'F' - find in database first!");
        }
    }

    private static void findInDatabase() {
        System.out.print("Please enter first name of the user to find: ");
        String firstName = scanner.nextLine();
        System.out.print("Please enter last name of the user to find: ");
        String lastName = scanner.nextLine();
        email = edb.findEmail(firstName, lastName);
        if (email != null) {
            System.out.println("Email found in database.");
        } else {
            System.out.println("No email found for user with this name.");
        }
    }

    private static void listAll() {
        edb.listAll();
    }

    private static void saveDatabase() {
        edb.saveDatabase();
    }

    private static void error() {
        System.out.println("Please create an email first!");
    }
}
