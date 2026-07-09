import java.util.Scanner;


public class Main {

    public static void displayMenu(){
       System.out.println("------   INTERNSHIP TRACKER   ------");
       System.out.println("1. Add New Application");
       System.out.println("2. View all Applications");
       System.out.println("3. Update Application Status");
       System.out.println("4. Check Upcoming Deadlines");
       System.out.println("5. Add/Edit Resume Notes");
       System.out.println("6. Edit Application");
       System.out.println("7. Delete Application");
       System.out.println("8. Exit");
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ApplicationManager manager = new ApplicationManager();
        boolean running = true;
        manager.showDeadlineNotifications();

        while (running){
            displayMenu();
            System.out.print("Enter the operation(1-8): ");
            int choice=Integer.parseInt(sc.nextLine());
       switch (choice) {
        case 1:{
            
           System.out.print("Company Name: ");
            String company = sc.nextLine();
            System.out.print("Role: ");
            String role = sc.nextLine();
            System.out.print("Application Link: ");
            String link = sc.nextLine();
            System.out.print("Deadline (DD-MM-YYYY): ");
            String deadline = sc.nextLine();
            deadline = deadline.trim();
            System.out.print("1. To Apply\n"+ //
                                "2. Applied\n" + //
                                "3. OA\n" + //
                                "4. Interview\n" + //
                                "5. Rejected\n" + //
                                "6. Offer\n" + //
                                "");
            System.out.print("Status: ");
            
            String status = sc.nextLine();
            String dateApplied = "";
            if(status.equalsIgnoreCase("Applied")){
            System.out.print("Date Applied (DD-MM-YYYY): ");
            dateApplied = sc.nextLine();}
            Application app = new Application(company, role, link, deadline, status,dateApplied);
            manager.addApplication(app);
            System.out.println(" Application Saved!");
            break;
        }
        case 2:{
            manager.viewApplication();
            break;}
        case 3:
           { System.out.print("Company Name: ");
            String company = sc.nextLine();
            System.out.print("Role: ");
            String role = sc.nextLine();
            System.out.println("1. To Apply\n"+ //
            "2. Applied\n" + //
            "3. OA\n" + //
            "4. Interview\n" + //
            "5. Rejected\n" + //
            "6. Offer\n" + //
            "");
            System.out.print("Enter Status Choice (1-6): ");

            int statusChoice = Integer.parseInt(sc.nextLine());
          String status = "";

switch(statusChoice){
    case 1:
        status = "To Apply";
        break;

    case 2:
        status = "Applied";
        break;

    case 3:
        status = "OA";
        break;

    case 4:
        status = "Interview";
        break;

    case 5:
        status = "Rejected";
        break;

    case 6:
        status = "Offer";
        break;

    default:
        System.out.println("Invalid Status Choice");
        break;
}
          if(!status.isEmpty()){
            if(status.equalsIgnoreCase("applied")){
              
               System.out.print("Date Applied (DD-MM-YYYY): ");
               String dateApplied = sc.nextLine();
               manager.editApplication(company,role,7,dateApplied);
               
               
            }
        
    manager.updateApplicationStatus(company, role, status);
}
            break;}
        case 4:
            {
                manager.checkDeadline();
            }
            
            break;
        case 5:{
            System.out.print("Company Name: ");
            String company = sc.nextLine();
            System.out.print("Role: ");
            String role = sc.nextLine();
            System.out.print("Add/Edit Resume Notes: ");
            String notes = sc.nextLine();

            manager.updateNotes(company, role, notes);
            break;
        }
            
        case 6:{
             System.out.print("Company Name: ");
            String company = sc.nextLine();
            System.out.print("Role: ");
            String role = sc.nextLine();
            System.out.println("1. Company\n" + //
            "2. Role\n" + //
            "3. Link\n" + //
            "4. Deadline\n" + //
            "5. Status\n" + //
            "6. Notes\n"+//
            "7. Date Applied");
            System.out.print("Field to Edit: ");
            int fieldChoice = Integer.parseInt(sc.nextLine());
            
            Application app = manager.findApplication(company, role);
            if(app == null){
                System.out.println("Application Not Found.");
                break;
            }
            switch(fieldChoice){
                case 1:
                    System.out.println(
                        "Current Value: " + app.companyName
                    );
                    break;
                    
                    case 2:
                        System.out.println(
                            "Current Value: " + app.role
                        );
                    break;
                    case 3:
                        System.out.println(
                            "Current Value: " + app.link
                        );
                        break;
                    case 4:
                        System.out.println(
                            "Current Value: " + app.deadline
                        );
                        break;
                    case 5:
                        System.out.println(
                            "Current Value: " + app.status
                        );
                        break;
                    case 6:
                        System.out.println(
                            "Current Value: " + app.notes
                        );
                        break;
                    case 7:
                        System.out.println(
                            "Current Value: " + app.dateApplied
                        );
                        break;
                        }

        System.out.print("New Value: ");
        String newValue = sc.nextLine();
        manager.editApplication(company, role, fieldChoice, newValue);
        break;
        }
            
        case 7:{
            System.out.print("Company Name: ");
            String company = sc.nextLine();
            System.out.print("Role: ");
            String role = sc.nextLine();
            company = company.trim();
            role = role.trim();
            manager.deleteApplication(company, role);
        }
            
            break;
        case 8:
            System.out.println("Exiting.");
            sc.close();
            running=false;
            break;
       
        default:
               System.out.println("Invalid Menu Choice");
    break;
          
       }
    }
}
}