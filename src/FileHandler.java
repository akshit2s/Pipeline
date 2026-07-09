import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    static String FILE_NAME = "data.txt";
   // static int count=0;
    static void save(List<Application> apps){
        try{
        BufferedWriter writer=new BufferedWriter(new FileWriter(FILE_NAME));
        for(Application app : apps){
        writer.write(app.companyName+","+app.role+","+app.link+","+app.deadline+","+app.status+","+app.dateApplied+","+app.notes);
        writer.newLine();
       }
        writer.close();
    }
    catch(IOException e){
        System.out.println("Error saving data: "+e.getMessage());
    }
}

static List<Application> load(){
    List <Application> apps =new ArrayList<>();
    try{
        BufferedReader reader= new BufferedReader(new FileReader(FILE_NAME));
        String line;
 while((line = reader.readLine()) != null){

    String[] parts = line.split(",", -1);

    if(parts.length < 7){
        System.out.println("Skipping invalid record: " + line);
        continue;
    }

    Application app = new Application(
        parts[0],
        parts[1],
        parts[2],
        parts[3],
        parts[4],
        parts[5]
    );

    app.notes = parts[6];
    apps.add(app);
}
    
    reader.close();}
    catch (IOException e){
        System.out.println("No existing data found. Starting with an empty tracker."+e.getMessage());
    }
    return apps;
    
}}
