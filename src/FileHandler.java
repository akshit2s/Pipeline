import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    static String FILE_NAME = "data.txt";
    static void save(List<Application> apps){
        try{
        BufferedWriter writer=new BufferedWriter(new FileWriter("data.txt"));
        for(Application app : apps){
        writer.write(app.companyName+","+app.role+","+app.link+","+app.deadline+","+app.dateApplied+","+app.status+","+app.notes);
        writer.newLine();}
        writer.close();
    }
    catch(IOException e){
        System.out.println("Error saving data: "+e.getMessage());
    }
}

static List<Application> load(){
    List <Application> apps =new ArrayList<>();
    try{
        BufferedReader reader= new BufferedReader(new FileReader("data.txt"));
        String line;
    while((line=reader.readLine())!= null){
        String[] parts=line.split(",");
        Application app=new Application(parts[0], parts[1], parts[2], parts[3], parts[4]);
        app.status=parts[5];
        app.notes=parts[6];
       apps.add(app);
    }}
    catch (IOException e){
        System.out.println("Error saving data: "+e.getMessage());
    }
    return apps;
    
}}
