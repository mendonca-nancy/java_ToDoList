import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;

public class ToDOList {
    static ArrayList<Task> list=new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    public static void main (String[] args){
        loadTasksFromFile();
        while(true){
        System.out.println("--to do list--");
        System.out.println("1.Add Task");
        System.out.println("2.View Task");
        System.out.println("3.Delete Task");
        System.out.println("4.Mark as done.");
        System.out.println("5.Exit");
        System.out.println("Enter your choice:");
        int c = sc.nextInt();
        sc.nextLine();//consumes the enter or nextline in the sense when u enter choice
        // i.e a number then u press enter right that is taken as string task in case 1
        switch (c) {
            case 1://adding tasks
                addTask();
                break;
            case 2://displaying tasks
                viewtasks();
                break;
            case 3://deleting task
                deleteTask();
                break;
            case 4://marking as done
               markAsdone();
               break;
            case 5:
                System.out.println("exiting...");
                return;
            default:
                System.out.println("invalid choice,try again");
        }
        }
    }
    public static void addTask(){
        System.out.println("Enter the task:");
        String tasktitle = sc.nextLine();
        list.add(new Task(tasktitle));
        saveTasksToFile();
        System.out.println("task added");
    }
    public static void viewtasks(){
        System.out.println("\nYour tasks:");
        if(list.isEmpty()){
            System.out.println("No task added yet");
        }
        else{
            for(int i=0;i<list.size();i++){
                System.out.println(i+1+"."+list.get(i));
            }
        }
    }
    public static void deleteTask(){
        System.out.println("enter the task number to delete:");
        int n=sc.nextInt();
        if(n>=1&&n<=list.size()) {
            list.remove(n - 1);
            System.out.println("task deleted!");
            saveTasksToFile();
        }else {
            System.out.println("invalid task number!");
        }
    }
    public static void markAsdone(){
        System.out.println("enter the task number to mark as done:");
        int doneIndex=sc.nextInt();
        if(doneIndex>=1&&doneIndex<=list.size()){
            list.get(doneIndex-1).markDone();
            System.out.println("marked as done!");
            saveTasksToFile();
        }
        else {
            System.out.println("invalid task number!");
        }
    }
    public static void saveTasksToFile(){
        try {
            FileWriter writer = new FileWriter("task.txt");
            for (Task task : list) {
                writer.write(task.getTitle() + "," + task.isDone() +"\n");
            }
            writer.close();
        } catch(IOException e){
              System.out.println("Error saving tasks to file!");
        }
    }
    public static void loadTasksFromFile(){
        try {
            File fileOb = new File("task.txt");
            Scanner sc = new Scanner(fileOb);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] parts = line.split(",", 2);
                if (parts.length == 2) {
                    Task task = new Task(parts[0]);
                    if (parts[1].trim().equals("true")) {
                        task.markDone();
                    }
                    list.add(task);
                }
            }
            sc.close();
        }
        catch(IOException e){
            System.out.println("Error file doesn't exit");
        }

    }
}
