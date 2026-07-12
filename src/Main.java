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
        
        manager.showDashboard();
        while (running){
            displayMenu();
            System.out.println();
       int choice = InputHelper.readInt(sc, "Enter choice[1-10]: ", 1, 10);
       switch (choice) {
        case 1:{
            
         String company = InputHelper.readNonEmptyString(sc, "Company: ");
          String role = InputHelper.readNonEmptyString(sc, "Role: ");
            company = company.trim();
            role = role.trim();
            if(manager.applicationExists(company, role)){
              System.out.println("Application already exists");
              break;
            }
           String link = InputHelper.readNonEmptyString(sc, "Link: ");
          String deadline = InputHelper.readValidDate(sc, "Deadline (dd-MM-yyyy): ");
            
           String status = InputHelper.readStatus(sc);
            if (status == null) {
                System.out.println("Invalid Status!");
                break;      // or return depending on where this is
}
            
          
            String dateApplied = "";
            if(status.equalsIgnoreCase("Applied")){
            dateApplied = InputHelper.readValidDate(sc, "DateApplied (dd-MM-yyyy): ");
           }
            Application app = new Application(company, role, link, deadline, status,dateApplied);
            manager.addApplication(app);
            System.out.println(" Application Saved!");
            break;
            }
        case 2:{
            manager.viewApplication();
            break;}
        case 3:
           {String company = InputHelper.readNonEmptyString(sc, "Company Name: ");
            String role = InputHelper.readNonEmptyString(sc, "Role: ");
            String status = InputHelper.readStatus(sc);

          if(!status.isEmpty()){
              if(status.equalsIgnoreCase("applied")){
           String dateApplied =
                 InputHelper.readValidDate(sc, "Date Applied (dd-MM-yyyy): ");
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
            String company = InputHelper.readNonEmptyString(sc, "Company Name: ");
            String role = InputHelper.readNonEmptyString(sc, "Role: ");
            String notes = InputHelper.readNonEmptyString(sc, "Add/Edit Resume Notes: ");

            manager.updateNotes(company, role, notes);
            break;
        }
            
        case 6:{
            String company = InputHelper.readNonEmptyString(sc, "Company Name: ");
            String role = InputHelper.readNonEmptyString(sc, "Role: ");
            System.out.println("1. Company\n" + //
            "2. Role\n" + //
            "3. Link\n" + //
            "4. Deadline\n" + //
            "5. Status\n" + //
            "6. Notes\n"+//
            "7. Date Applied");
          int fieldChoice =
InputHelper.readInt(sc,"Field to Edit: ",1,7);
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
         String company = InputHelper.readNonEmptyString(sc, "Company Name: ");
         String role = InputHelper.readNonEmptyString(sc, "Role: ");
            company = company.trim();
            role = role.trim();
            manager.deleteApplication(company, role);
        }
            
            break;

        case 8:{
            System.out.println();
          String key = InputHelper.readNonEmptyString(sc,"Search by Company/Role: ");
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
        int filter_choice =InputHelper.readInt(sc,"Enter choice (1-8): ",1,8);

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
}

