public class Test {
    public static void main(String[] args) {
        Email email = new Email("John", "Smith");

//        System.out.println("User: " + email.getName());
//        System.out.println("Department: " + email.getDepartment());
//        System.out.println("Email: " + email.getEmail());
//        System.out.println("Password: " + email.getPassword());
//        System.out.println("Mailbox Capacity: " + email.getMailboxCapacity() + "MB");
//        System.out.println("Alternate email: " + email.getAlternateEmail());
        System.out.println(email.showInfo());
        System.out.println("********************");

        email.setPassword("1234567890");
        email.setAlternateEmail("js@gmail.com");
        email.setMailboxCapacity(500);

//        System.out.println("User: " + email.getName());
//        System.out.println("Email: " + email.getEmail());
//        System.out.println("Password: " + email.getPassword());
//        System.out.println("Mailbox Capacity: " + email.getMailboxCapacity() + "MB");
//        System.out.println("Alternate email: " + email.getAlternateEmail());
        System.out.println(email.showInfo());
        System.out.println(email.toString());

        System.out.println("\n*****\nCreating new database.");
        EmailDatabase edb = new EmailDatabase();
        System.out.println("Database saved: " + edb.isSaved());

        System.out.println("\n*****\nAdding to database:");
        for (int i = 0; i < 10; i++) {
            String firstName = "" + Character.toString('a' + i);
            String lastName = "" + Character.toString('A' + i);
            String dept = "development";
            edb.addToDatabase(new Email(firstName, lastName, dept));
        }
        System.out.println("Database saved: " + edb.isSaved());

        System.out.println("\n*****\nSaving database.");
        edb.saveDatabase();
        System.out.println("Database saved: " + edb.isSaved());

        System.out.println("\n*****\nAdding to database existing email. Should fail.");
        edb.addToDatabase(new Email("b", "B", "development"));
        System.out.println("Database saved: " + edb.isSaved());

        System.out.println("\n*****\nAdding to database - same name, different dept. Should be OK.");
        edb.addToDatabase(new Email("b", "B", "sales"));
        System.out.println("Database saved: " + edb.isSaved());

        System.out.println("\n*****\nRemoving from database - existing email. Should be successful.");
        edb.removeFromDatabase(new Email("c", "C", "development"));
        System.out.println("Database saved: " + edb.isSaved());

        System.out.println("\n*****\nRemoving from database - not existing email. Should fail.");
        edb.removeFromDatabase(new Email("x", "X", "sales"));
        System.out.println("Database saved: " + edb.isSaved());

        System.out.println("\n*****\nRemoving from database - same name, different dept. Should fail.");
        edb.removeFromDatabase(new Email("d", "D", "sales"));
        System.out.println("Database saved: " + edb.isSaved());


        System.out.println("\n*****\nFinding email for user e E:");
        System.out.println(edb.findEmail("e", "E").showInfo());
        System.out.println("Database saved: " + edb.isSaved());

        System.out.println("\n*****\nSaving to database:");
        edb.saveDatabase();
        System.out.println("Database saved: " + edb.isSaved());

        System.out.println("\n*****\nFinding email for not existing user x X:");
        try {
            System.out.println(edb.findEmail("x", "X").showInfo());
        } catch (NullPointerException e) {
            System.out.println("User doesn't exist!!!");
        }
        System.out.println("Database saved: " + edb.isSaved());


        //*********
        
        System.out.println("\n*****\nCreating another database.");
        EmailDatabase edb2 = new EmailDatabase();
        System.out.println("Database saved: " + edb2.isSaved());

        System.out.println("\n*****\nAdding to database:");
        for (int i = 0; i < 5; i++) {
            String firstName = "" + Character.toString('k' + i);
            String lastName = "" + Character.toString('K' + i);
            String dept = "sales";
            edb2.addToDatabase(new Email(firstName, lastName, dept));
        }
        System.out.println("Database saved: " + edb2.isSaved());

        System.out.println("\n*****\nSaving database.");
        edb2.saveDatabase();
        System.out.println("Database saved: " + edb2.isSaved());

        System.out.println("\n*****\nAdding to database existing email. Should fail.");
        edb2.addToDatabase(new Email("m", "M", "sales"));
        System.out.println("Database saved: " + edb2.isSaved());

        System.out.println("\n*****\nAdding to database - same name, different dept. Should be OK.");
        edb2.addToDatabase(new Email("m", "M", "accounting"));
        System.out.println("Database saved: " + edb2.isSaved());

        System.out.println("\n*****\nRemoving from database - existing email. Should be successful.");
        edb2.removeFromDatabase(new Email("o", "O", "sales"));
        System.out.println("Database saved: " + edb2.isSaved());

        System.out.println("\n*****\nRemoving from database - not existing email. Should fail.");
        edb2.removeFromDatabase(new Email("x", "X", "accounting"));
        System.out.println("Database saved: " + edb2.isSaved());

        System.out.println("\n*****\nRemoving from database - same name, different dept. Should fail.");
        edb2.removeFromDatabase(new Email("n", "N", "accounting"));
        System.out.println("Database saved: " + edb2.isSaved());


        System.out.println("\n*****\nFinding email for user l L:");
        System.out.println(edb2.findEmail("l", "L").showInfo());
        System.out.println("Database saved: " + edb2.isSaved());

        System.out.println("\n*****\nSaving to database:");
        edb2.saveDatabase();
        System.out.println("Database saved: " + edb2.isSaved());


        System.out.println("\n*****\nFinding email for not existing user x X:");
        try {
            System.out.println(edb.findEmail("x", "X").showInfo());
        } catch (NullPointerException e) {
            System.out.println("User doesn't exist!!!");
        }
        System.out.println("Database saved: " + edb2.isSaved());


    }
}
