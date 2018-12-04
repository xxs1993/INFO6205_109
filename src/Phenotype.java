import java.util.ArrayList;
import java.util.List;

public class Phenotype {

    public static List<Double> decode(List<Server> genes,List<Task> tasks){
        List<Double> phenotype = new ArrayList<>();
        for(int i = 0; i< genes.size();i++){
            Server server = genes.get(i);
            phenotype.add(new Double(tasks.get(i).getUsingTime())/new Double(server.getExecutingSpeedList().get(i)));
        }
        return phenotype;
    }
}
