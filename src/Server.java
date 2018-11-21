import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server {


    private int taskNum;
    
    private  static final int speedRange = 20;

    private List<Integer> executingSpeedList = null;

    public int getServerNum() {
        return taskNum;
    }

    public void setServerNum(int serverNum) {
        this.taskNum = serverNum;
    }

    public List<Integer> getExecutingSpeedList() {
        return executingSpeedList;
    }

    public void setExecutingSpeedList(List<Integer> executingSpeedList) {
        this.executingSpeedList = executingSpeedList;
    }

    private static Server server = null;

    public static Server getInstance(int num){
        if(server == null){
            server = new Server(num);
        }
        return server;
    }
    private Server(int num){
        this.taskNum = num;
        Random r = new Random();
        this.executingSpeedList = new ArrayList<>();
        for(int i = 0;i<this.taskNum;i++){
            this.executingSpeedList.add(r.nextInt(speedRange));
        }
        //TODO : random executingList
    }
}
