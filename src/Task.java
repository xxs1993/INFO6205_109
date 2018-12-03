import java.util.List;
import java.util.Random;

public class Task {
    private int usingTime;

    private int id;

    public Task(int id){
        Random random = new Random();
        this.usingTime =random.nextInt(GAConfiguration.task_using_time_range)+1;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsingTime() {
        return usingTime;
    }

    public void setUsingTime(int usingTime) {
        this.usingTime = usingTime;
    }
}
