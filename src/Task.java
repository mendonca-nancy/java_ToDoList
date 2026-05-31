public class Task {
   private String title;
   private boolean isDone;
   //constructor
    Task(String title){
        this.title=title;
        this.isDone=false;
    }
   //getters
    public String getTitle(){
        return title;
    }
    public boolean isDone(){
        return isDone;
    }
    //setters
    public void markDone(){
        isDone=true;
    }
    //overriding toString,we need not to call toString cause it's called automatically by java
    // when we use System.out.println(object);
    public String toString(){
        String status = isDone ? "[✅]" : "[ ]";
        return status+" "+title;
    }
}
