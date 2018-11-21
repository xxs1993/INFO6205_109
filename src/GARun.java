import java.util.ArrayList;
import java.util.List;

public class GARun {
   private static final List<Task> tasks = new ArrayList<>();
   private static final List<Server> servers = new ArrayList<>();
   public GARun(int taskNum,int serverNum){
       for(int i = 0; i< taskNum;i++){
           tasks.add(new Task());
       }

       for(int j = 0;j< serverNum ;j++){
           servers.add(new Server(taskNum));
       }
   }

   public List<Chromesome> getSololutions(){

       return null;
   }

   private List<Chromesome> recursiveSolutions(List<Chromesome> list){
       return null;
   }
}
