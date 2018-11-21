import java.util.List;

public class GARun {
   private Task task;
   private Server server;
   public GARun(int taskNum,int serverNum){
       this.task = Task.getInstance(taskNum);
       this.server = Server.getInstance(serverNum);
   }

   public List<Chromesome> getSololutions(){
       return null;
   }
}
