import java.util.List;
import java.util.Random;

public class Task {
    private int usingTime;

    public Task(){
        Random random = new Random();
        this.usingTime = random.nextInt(100);
    }

    public int getUsingTime() {
        return usingTime;
    }

    public void setUsingTime(int usingTime) {
        this.usingTime = usingTime;
    }
}
