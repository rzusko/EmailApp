/*
    This class creates an email object.
 */

import java.util.Random;
import java.util.Scanner;

public class Email {
    private String email;
    private String firstName;
    private String lastName;
    private String department;
    private String password;
    private String alternateEmail = "none";
    private int mailboxCapacity = 100;
    private int defaultPasswordLength = 12;
    private String companySuffix = "company.com";

    // Constructor for creating new email from the app.

    public Email(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = determineDepartment();
        this.email = generateEmail();
        this.password = generatePassword(defaultPasswordLength);
    }

    public Email(String firstName, String lastName, String department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = generateEmail();
        this.password = generatePassword(defaultPasswordLength);
    }

    // Constructor for creating new email from file loaded by EmailDatabase object.

    public Email(String firstName, String lastName, String department, String email, String password, String alternateEmail, int mailboxCapacity) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.email = email;
        this.password = password;
        this.alternateEmail = alternateEmail;
        this.mailboxCapacity = mailboxCapacity;
    }

    // Method for determining department

    private String determineDepartment() {
        System.out.println("Departments of the company:");
        System.out.println("\t1 - Sales department");
        System.out.println("\t2 - Development department");
        System.out.println("\t3 - Accounting department");
        System.out.println("\tother - No department");
        System.out.print("Please choose one of the options above: ");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();
        scanner.nextLine();

        switch (option) {
            case 1:
                return "sales";
            case 2:
                return "development";
            case 3:
                return "accounting";
            default:
                return "none";
        }
    }

    // Method that generates new email based on firstName, lastName and department.

    private String generateEmail() {
        StringBuilder email = new StringBuilder();

        email.append(firstName.toLowerCase());
        email.append(".");
        email.append(lastName.toLowerCase());
        email.append("@");
        if (!department.equals("none")) {
            email.append(department);
            email.append(".");
        }
        email.append(companySuffix);

        return email.toString();
    }

    // Method that generates random password for email.

    public String generatePassword(int length){
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        Random rand = new Random();
        String password = "";

        for (int i = 0; i < length; i++) {
            int position = rand.nextInt(alphabet.length());
            char character = alphabet.charAt(position);
            password += character;
        }

        return password;
    }

    // Setters

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAlternateEmail(String alternateEmail) {
        this.alternateEmail = alternateEmail;
    }

    public void setMailboxCapacity(int mailboxCapacity) {
        this.mailboxCapacity = mailboxCapacity;
    }

    // Getters

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return (firstName + " " + lastName);
    }

    public String getDepartment() {
        return department;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getMailboxCapacity() {
        return mailboxCapacity;
    }

    public String getAlternateEmail() {
        return alternateEmail;
    }

    // Method that returns all of the information about Email object.

    public String showInfo() {
        return "********************\nUser: " + getName() +
                "\nDepartment: " + getDepartment() +
                "\nEmail: " + getEmail() +
                "\nPassword: " + getPassword() +
                "\nMailbox capacity: " + getMailboxCapacity() +
                "\nAlternate email: " + getAlternateEmail() +
                "\n********************";
    }

    // Method that creates new Email object.

    public static Email createEmail(String firstName, String lastName) {
        return new Email(firstName, lastName);
    }

    // Mathod that returns informations about Email object in form needed for EmailDatabase.

    @Override
    public String toString() {

        //firstName::lastName::department::email::password::alternateEmail::mailboxCapacity

        return firstName + "::" + lastName + "::" + department + "::" +
                email + "::" + password + "::" + alternateEmail + "::" + mailboxCapacity;
    }
}
