import java.util.List;
import java.util.Random;

public class Server {


    private List<Integer> executingSpeedList;




    public List<Integer> getExecutingSpeedList() {
        return executingSpeedList;
    }

    public void setExecutingSpeedList(List<Integer> executingSpeedList) {
        this.executingSpeedList = executingSpeedList;
    }


    public Server(int taskNum){
        Random random = new Random();
        for(int i = 0;i<taskNum;i++){
            executingSpeedList.add(random.nextInt(20));
        }
    }
}
