
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class ApplicationManager{
    private List<Application> apps;
 public ApplicationManager(){
        apps=FileHandler.load();

    }

    public void addApplication(Application app){
     apps.add(app);
      FileHandler.save(apps);
   }

    public void viewApplication(){
        sortDeadlines();
          if(apps.isEmpty()){
        System.out.println("No applications found.");
            return;}
    for(int i = 0; i < apps.size(); i++){
        Application app = apps.get(i);
        System.out.println("Application #" + (i + 1));
       printApplication(app);
    }
}

    public void updateApplicationStatus(String companyName,String role, String status){
        boolean found=false;
    
     for(Application app : apps){
        if((app.companyName.equalsIgnoreCase(companyName))
            && (app.role.equalsIgnoreCase(role))){
         app.status = status;
         found=true;
         break;
        }}
    
    
    if (found){
        FileHandler.save(apps);
        System.out.println("Status Updated.");
    }
    else{
        System.out.println("Record Not Found.");
    }

    }
    public void checkDeadline(){
        sortDeadlines();
        for (Application app: apps){
      long days_left = getDaysLeft(app);
  if (days_left<=7){ 

    if(days_left<0){
        continue;
    }
    else if (days_left==0){
System.out.println(app.companyName+" | "+app.role+" | " +" 🚨LAST DAY ");
    }
    else{
   System.out.println(app.companyName+" | " +app.role+" | " + days_left +" days left.");}}
    }}

    public void updateNotes(String companyName,String role, String notes){
        boolean found=false;
        for(Application app: apps){
            if((app.companyName.equalsIgnoreCase(companyName))
                &&(app.role.equalsIgnoreCase(role))){
                app.notes=notes;
                found=true;
                break;
            }
        }
        if(found){
            FileHandler.save(apps);
            System.out.println("Notes Updated");
        }
        else{
            System.out.println("Record Not Found.");
        }
    
    }
    public void deleteApplication(String companyName,String role){
        Application appToDelete=null;
        boolean found=false;
        for (Application app: apps){
            if((app.companyName.equalsIgnoreCase(companyName))
            &&(app.role.equalsIgnoreCase(role))){
        appToDelete=app;
        found=true;
        break;
        }
    }
    if(found){
    apps.remove(appToDelete);
    FileHandler.save(apps);
    System.out.println("Application Deleted Successfully");
    }
    else{
        System.out.println("Application Not Found");

    }
}

 public void editApplication(String companyName, String role, int choice, String newValue ){
    boolean found=false;
    for(Application app: apps){
        if((app.companyName.equalsIgnoreCase(companyName))
        &&(app.role.equalsIgnoreCase(role))){
         switch(choice){
    case 1:
        app.companyName = newValue;
        break;

    case 2:
        app.role = newValue;
        break;

    case 3:
        app.link = newValue;
        break;

    case 4:
        app.deadline = newValue;
        break;

    case 5:
        app.status = newValue;
        break;

    case 6:
        app.notes = newValue;
        break;
    
    case 7:
        app.dateApplied=newValue;
        break;

    default:
         System.out.println("Invalid Choice");
         found=false;
         return;

}
    found=true;
    break;

        }
    }
    if(found){
        FileHandler.save(apps);
        System.out.println("Updated!");
    }
    else{
        System.out.println(" \"Application not found. Check company name and role.");
    }
 }
  public int getApplicationCount(){
    return apps.size();
}

public Application findApplication(String companyName , String role ){
    for(Application app: apps){
         if(app.companyName.equalsIgnoreCase(companyName)
           && app.role.equalsIgnoreCase(role)){

            return app;
    }}
    return null;}

public void sortDeadlines(){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yyyy");

    apps.sort((a,b) -> {
        LocalDate dateA = LocalDate.parse(a.deadline,formatter);
  LocalDate dateB =
  LocalDate.parse(b.deadline, formatter);
   return dateA.compareTo(dateB);
    });

    
    }

    public void showDeadlineNotifications(){
        sortDeadlines();
        for(Application app : apps){
   long days_left= getDaysLeft(app);
   if(app.status.equalsIgnoreCase("To Apply")){
   if (days_left<=7){
    if(days_left<0){
        continue;
    }
System.out.println(
    "⚠️ " + app.companyName +
    " | " + app.role +
    " | Deadline: " + app.deadline +
    " | " + days_left + " days left"
);
   }}
}
     
    }

   public long getDaysLeft(Application app){
  DateTimeFormatter formatter= DateTimeFormatter.ofPattern("d-M-yyyy");
        LocalDate today=LocalDate.now();
      
      String deadline=app.deadline;
      LocalDate deadlinedate=LocalDate.parse(deadline,formatter);
     long days_left=ChronoUnit.DAYS.between(today,deadlinedate );
     
     return days_left;
    }
   

   public int searchApplication(String keyword){
   int count =0;
    for (Application app : apps){
    if (app.companyName.toLowerCase().contains(keyword.toLowerCase()) ||
    app.role.toLowerCase().contains(keyword.toLowerCase())){
          printApplication(app);
        count++;
        }
    }
    if (count==0) {
    System.out.println("No matching applications found.");
}
System.out.println(count+" Application/s found");
return count;
   }
   public void searchApplicationAdv(String keyword,String keyword2,int count){
    keyword = keyword.toLowerCase();
    keyword2 = keyword2.toLowerCase();
    int count2=0;
   
    for (Application app : apps){
      if ((app.companyName.toLowerCase().contains(keyword) ||
     app.role.toLowerCase().contains(keyword)) &&
    (app.companyName.toLowerCase().contains(keyword2) ||
     app.role.toLowerCase().contains(keyword2))){
          printApplication(app);
          count2++;
        
    }}
    
      if (count2==0) {
    System.out.println("No matching applications found.");
}
System.out.println(count2+" Application/s found");
   }

   private void printApplication(Application app){
     System.out.println();
            System.out.println("Company: " + app.companyName);
        System.out.println("Role: " + app.role);
        System.out.println("Link: " + app.link);
        System.out.println("Deadline: " + app.deadline);
        System.out.println("Status: " + app.status);
        if(!app.dateApplied.isEmpty()){
        System.out.println("Applied On: " + app.dateApplied);}
        if(!app.notes.isEmpty()){
        System.out.println("Notes: " + app.notes);}
        System.out.println();
        System.out.println("--------------------");
   }

  public void  filterApplications(String status){
    boolean found = false;
    for (Application app : apps){
        if(app.status.contains(status)){
         printApplication(app);
         found=true;
        }
    }
     if (!found) {
        System.out.println("No applications found with status: " + status);
    }
   }
   public void showDashboard(){
   System.out.println("========================================");
    System.out.println("      INTERNSHIP TRACKER     ");
    System.out.println("========================================");
    System.out.println();
    System.out.println("------------ DASHBOARD ------------");
    System.out.println();
    System.out.println("Total Applications : " + apps.size());
    System.out.println();
    System.out.println("To Apply           : " +status_count("To Apply"));
    System.out.println("Applied            : " +status_count("Applied"));
    System.out.println("OA                 : " + status_count("OA"));
    System.out.println("Interview          : " + status_count("Interview"));
    System.out.println("Offer              : " + status_count("Offer"));
    System.out.println("Rejected           : " +status_count("Rejected"));
    System.out.println("No status added    : " +(apps.size()-(status_count("To Apply")+
                                                                status_count("Applied")+
                                                                status_count("OA")+
                                                                status_count("Interview")+
                                                                status_count("Offer")+
                                                                status_count("Rejected"))));
    System.out.println();
    showNearestDeadlines();
    System.out.println();
   }

   public int status_count(String status){
    int count = 0;
     for (Application app : apps){
        if(app.status.contains(status)){
            count++;
        }
     }
     return count;
   }

public void showNearestDeadlines() {

    if (apps.isEmpty()) {
        System.out.println("No applications available.");
        return;
    }

    sortDeadlines();

    long nearestDays = -1;

   
    for (Application app : apps) {
        long daysLeft = getDaysLeft(app);

        if (daysLeft >= 0) {
            nearestDays = daysLeft;
            break;
        }
    }

    // If all deadlines have passed
    if (nearestDays == -1) {
        System.out.println("No upcoming deadlines.");
        return;
    }

    System.out.println("----------------------------------------");
    System.out.println("Nearest Deadline(s)");
    System.out.println("----------------------------------------");

    // Print all applications having the same nearest deadline
    for (Application app : apps) {

        if (getDaysLeft(app) == nearestDays) {
            System.out.println(app.companyName + " | " + app.role + " | " + nearestDays + " day(s) left");
        }
    }
}
}