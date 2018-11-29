import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GARun {
   private static final List<Task> tasks = new ArrayList<>();
   private static final List<Server> servers = new ArrayList<>();
   private int taskNum;
   private int serverNum;
   private int solutionNum ;

    public  List<Task> getTasks() {
        return tasks;
    }

    public  List<Server> getServers() {
        return servers;
    }

    public int getTaskNum() {
        return taskNum;
    }

    public void setTaskNum(int taskNum) {
        this.taskNum = taskNum;
    }

    public int getServerNum() {
        return serverNum;
    }

    public void setServerNum(int serverNum) {
        this.serverNum = serverNum;
    }

    public int getSolutionNum() {
        return solutionNum;
    }

    public void setSolutionNum(int solutionNum) {
        this.solutionNum = solutionNum;
    }

    public GARun(int taskNum, int serverNum, int solutionNum){
       this.taskNum = taskNum;
       this.serverNum = serverNum;
       this.solutionNum = solutionNum;
       for(int i = 0; i< taskNum;i++){
           tasks.add(new Task(i));
       }

       for(int j = 0;j< serverNum ;j++){
           servers.add(new Server(taskNum,j));
       }
   }

    /**
     * initiate the first generation
     * @return
     */
   private List<Chromosome> initChromosomes( ){
       List<Chromosome> list = new ArrayList<>();
       for(int i=0;i<2*solutionNum;i++){
           list.add(buildChromosome());
       }
       return list;
   }

    /**
     * build single chromosome
     * @return
     */
   private Chromosome buildChromosome(){
       Chromosome chromosome = new Chromosome();
       List<Server> genes = new ArrayList<>();
       Random random = new Random();
       for(int i = 0; i< taskNum; i++){
           int index = random.nextInt(serverNum);
           genes.add(servers.get(index));
       }
       chromosome.setGenes(genes);
       chromosome.setCollocationDegree(CollocationDegree.getCollocationDegreeByGenes(genes));
       return chromosome;
   }

    /**
     * get all solutions
     * @return
     */
   public List<Chromosome> getSolutions(){
       List<Chromosome> chromosomes = initChromosomes();
       for (int i = 0;i < GAConfiguration.recursiveTimes;i++){
           chromosomes = recursiveSolutions(chromosomes);
           System.out.println("generation : "+i);
       }
       return chromosomes;
   }

    /**
     * get next generations
     * @param list
     * @return
     */
   private List<Chromosome> recursiveSolutions(List<Chromosome> list){
       List<Chromosome> newList = new ArrayList<>();
       list.sort(null);
       int dividerIndex = Math.max(1,new Double(list.size()*GAConfiguration.crossSolutionProp ).intValue());
       List<Chromosome> crossChromosomes = list.subList(0,dividerIndex);
       List<Chromosome> copyChromosomes  = list.subList(dividerIndex,list.size());
       newList.addAll(copyChromosomes);
       newList.addAll(Reproduction.getNextGeneration(crossChromosomes));
       return newList;
   }


}
