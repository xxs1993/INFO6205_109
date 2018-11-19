import java.util.List;

public class Server {


    private int serverNum;

    private List<Integer> executingSpeedList;

    public int getServerNum() {
        return serverNum;
    }

    public void setServerNum(int serverNum) {
        this.serverNum = serverNum;
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
        this.serverNum = num;
        //TODO : random executingList
    }
}
