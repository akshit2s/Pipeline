import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Application {
    String companyName;
    String role;
    String link;
    String deadline;
    String status;
    String dateApplied;
    String notes;


    Application(String companyName, String role, String link, String deadline, String status,String dateApplied) {
    this.companyName = companyName;
    this.role = role;
    this.link = link;
    this.deadline = deadline;
    this.status = status;
    this.dateApplied = dateApplied;
    this.notes = "";
}
}