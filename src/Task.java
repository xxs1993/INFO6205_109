import java.util.Random;

public class Task {
    private int workload;

    private int id;

    public Task(int id){
        Random random = new Random();
        this.workload =random.nextInt(GAConfiguration.task_using_time_range)+1;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkload() {
        return workload;
    }

    public void setWorkload(int workload) {
        this.workload = workload;
    }
}
