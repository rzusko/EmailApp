/*
 *  This class creates an EmailDatabase object.
 */


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class EmailDatabase {
    private String fileName = "emails.txt";     // name of the file that saves database of emails
    private List<Email> emails;
    private boolean saved = true;               // information whether database was saved to file.

    // Constructor that creates EmailDatabase object and loads existing database from file.

    public EmailDatabase() {
        this.emails = new ArrayList<>();
        loadDatabase();
    }

    // Method that loads database from file.
    //firstName::lastName::department::email::password::alternateEmail::mailboxCapacity

    private void loadDatabase() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] vars = line.split("::");

                String firstName = vars[0];
                String lastName = vars[1];
                String department = vars[2];
                String email = vars[3];
                String password = vars[4];
                String alternateEmail = vars[5];
                int mailboxCapacity = Integer.parseInt(vars[6]);

                Email newEmail = new Email(firstName, lastName, department, email, password, alternateEmail, mailboxCapacity);
                this.emails.add(newEmail);
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    // Method that saves the database to file.

    public void saveDatabase() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (Email email : emails) {
                bw.write(email.toString() + "\n");
            }
            saved = true;
            System.out.println("Database successfully saved.");
        } catch (IOException ioe) {
            System.out.println("Unable to save database. " + ioe.getMessage());
        }
    }

    // Method that adds new email to database.

    public void addToDatabase(Email email) {
        if (email != null) {
            if (searchEmail(email) == -1) {
                emails.add(email);
                saved = false;
//                System.out.println(email);  // for testing purposes only.
//                System.out.println("Email successfully added to database.");
            } else {
                System.out.println("Email already exist");
            }
        } else {
            System.out.println("Unable to add.");
        }
    }

    // Method that removes email from database.

    public void removeFromDatabase(Email email) {
        if (email != null) {
            int position = searchEmail(email);

            if (position != -1) {
                emails.remove(position);
                saved = false;
                System.out.println("Email successfully removed.");
            } else {
                System.out.println("Email doesn't exist!");
            }
        } else {
            System.out.println("Unable to remove.");
        }
    }

    // Method that finds email in database for user.

    public Email findEmail (String firstName, String lastName) {
        if ((firstName != null) && (lastName != null)) {
            for (Email email : emails) {
                if ((email.getFirstName().equalsIgnoreCase(firstName))) {
                    if ((email.getLastName().equalsIgnoreCase(lastName))) {
                        return email;
                    }
                }
            }
        }

        return null;
    }

    // Method that search database for an email.

    private int searchEmail (Email emailToSearch) {
        if (emailToSearch != null) {
            for (Email email : emails) {
                if ((emailToSearch.getEmail()).equals(email.getEmail())) {
                    return emails.indexOf(email);
                }
            }
        }

        return -1;
    }

    public void listAll() {
        System.out.println("********************\nEmails in database: ");
        for (Email email : emails) {
            System.out.println("\t" + email.getName() + ": " + email.getEmail());
        }
    }


    // Getter. Returns value of variable saved.

    public boolean isSaved() {
        return saved;
    }

}
