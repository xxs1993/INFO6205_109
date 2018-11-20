import java.util.List;

public class Task {

    private static Task task = null;

    private Task(int taskNum){
        this.taskNum = taskNum;
        //TODO: random usingTimeList
    }

    public Task getInstance(int taskNum){
        if(task == null){
            task = new Task(taskNum);
        }
        return task;
    }
    //task number
    private int taskNum ;

    private List<Integer> usingTimeList;

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public List<Integer> getUsingTimeList() {
        return usingTimeList;
    }

    public void setUsingTimeList(List<Integer> usingTimeList) {
        this.usingTimeList = usingTimeList;
    }
}
