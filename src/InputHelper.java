import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHelper {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd-MM-yyyy");

   
  public static int readInt(Scanner sc, String message, int min, int max) {

    while (true) {

        System.out.print(message);

        try {

            int value = Integer.parseInt(sc.nextLine());

            if (value < min || value > max) {
                System.out.println("❌ Please enter a number between " + min + " and " + max + ".");
                continue;
            }

            return value;

        } catch (NumberFormatException e) {

            System.out.println("❌ Invalid input! Please enter a valid number.");

        }
    }
}

    public static String readNonEmptyString(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println("❌ Input cannot be empty.");
        }
    }

   
    public static String readValidDate(Scanner sc, String message) {

        while (true) {

            System.out.print(message);

            String deadline = sc.nextLine().trim();

            try {

                LocalDate enteredDate = LocalDate.parse(deadline, FORMATTER);

                if (enteredDate.isBefore(LocalDate.now())) {

                    System.out.println("❌ Deadline cannot be in the past.");
                    continue;

                }

                return deadline;

            } catch (DateTimeParseException e) {

                System.out.println("❌ Invalid date! Please use dd-MM-yyyy.");

            }
        }
    }
public static void printStatusMenu() {

        System.out.println("1. To Apply");
        System.out.println("2. Applied");
        System.out.println("3. OA");
        System.out.println("4. Interview");
        System.out.println("5. Offer");
        System.out.println("6. Rejected");

    }
    
   public static String readStatus(Scanner sc) {

    printStatusMenu();

    int choice = readInt(sc, "Enter Status: ", 1, 6);

    switch (choice) {
        case 1: return "To Apply";
        case 2: return "Applied";
        case 3: return "OA";
        case 4: return "Interview";
        case 5: return "Offer";
        case 6: return "Rejected";
    }

    return null; // This line is never reached because readInt() guarantees 1–6
}

    

}