import java.util.Scanner;


public class Main {

    public static void displayMenu(){
       System.out.println("------   MENU   ------");
       System.out.println("1. Add New Application");
       System.out.println("2. View all Applications");
       System.out.println("3. Update Application Status");
       System.out.println("4. Check Upcoming Deadlines");
       System.out.println("5. Add/Edit Resume Notes");
       System.out.println("6. Edit Application");
       System.out.println("7. Delete Application");
       System.out.println("8. Search Application");
       System.out.println("9. Filter Application by Status");
       System.out.println("10. Exit");
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        ApplicationManager manager = new ApplicationManager();
        boolean running = true;
        //manager.showDeadlineNotifications();
        manager.showDashboard();
        while (running){
            displayMenu();
            System.out.println();
            System.out.print("Enter the operation: ");
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
            System.out.println("Select Status:");
            System.out.println("1. To Apply");
            System.out.println("2. Applied");
            System.out.println("3. OA");
            System.out.println("4. Interview");
            System.out.println("5. Offer");
            System.out.println("6. Rejected");
            System.out.print("Enter choice: ");

            int statusChoice = Integer.parseInt(sc.nextLine());

            String status = getStatusFromChoice(statusChoice);

            if (status == null) {
                System.out.println("Invalid Status!");
                break;      // or return depending on where this is
}
            
          
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
            "5. offer\n" + //
            "6. Rejected\n" + //
            "");
            System.out.print("Enter Status Choice : ");

            int statusChoice = Integer.parseInt(sc.nextLine());
          String status = getStatusFromChoice(statusChoice);


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

        case 8:{
            System.out.println();
            System.out.print("Search by companyName/Role: ");
            String key=sc.nextLine();
            int count1=manager.searchApplication(key);
        if (count1!=0)  {  System.out.println();
            System.out.print("Advanced Search companyName/Role: ");
            String key2=sc.nextLine();
            if(key2.equalsIgnoreCase("")){
                manager.searchApplication(key);
            }
            else{
            manager.searchApplicationAdv(key,key2,count1);}}
        }
               break;

        case 9:{
            System.out.println();
           
             System.out.println("\n========== FILTER ==========");
                System.out.println("1. All");
                System.out.println("2. To Apply");
                System.out.println("3. Applied");
                System.out.println("4. OA");
                System.out.println("5. Interview");
                System.out.println("6. Rejected");
                System.out.println("7. Offer");
                System.out.println("8. Back");
                System.out.print("Enter choice(1-8): ");

                int filter_choice = Integer.parseInt(sc.nextLine());

                   switch (filter_choice) {
        case 1:
            manager.viewApplication();
            break;
        case 2:
            manager.filterApplications("To Apply");
            break;
        case 3:
            manager.filterApplications("Applied");
            break;
        case 4:
            manager.filterApplications("OA");
            break;
        case 5:
            manager.filterApplications("Interview");
            break;
        case 6:
            manager.filterApplications("Rejected");
            break;
        case 7:
            manager.filterApplications("Offer");
            break;
        case 8:
            break;
        default:
            System.out.println("Invalid choice!");
    }
        }
        break;
        case 10:
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

private static String getStatusFromChoice(int choice) {

    switch (choice) {
        case 1:
            return "To Apply";

        case 2:
            return "Applied";

        case 3:
            return "OA";

        case 4:
            return "Interview";

        case 5:
            return "Offer";

        case 6:
            return "Rejected";

        default:
            return null;
    }

}}