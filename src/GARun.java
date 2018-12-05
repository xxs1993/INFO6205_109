import java.util.*;

public class GARun {
   private static final List<Task> tasks = new ArrayList<>();
   private static final List<Server> servers = new ArrayList<>();
   private Map<String,List<Chromosome>> map = new HashMap<>();
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

    public Map<String, List<Chromosome>> getMap() {
        return map;
    }

    public void setMap(Map<String, List<Chromosome>> map) {
        this.map = map;
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
        List<Chromosome> chromosomes = initChromosomes();
        map.put("immature",new ArrayList<>());
        map.put("mature",chromosomes);
   }

    /**
     * initiate the first generation
     * @return
     */
   private List<Chromosome> initChromosomes( ){
       List<Chromosome> list = new ArrayList<>();
       for(int i=0;i<(GAConfiguration.sample_number);i++){
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
       chromosome.setPhenotypes(Phenotype.decode(genes,tasks));
       chromosome.setGenes(genes);
       chromosome.setFitness(Fitness.getCollocationDegreeByGenes(genes,tasks));
       return chromosome;
   }

   private double getAverageDegree(List<Chromosome> mature){
       mature.sort(null);
       double avargeDegree = 0;
       for(int i = mature.size()-300;i<mature.size();i++){
           avargeDegree = avargeDegree + mature.get(i).getFitness();
       }
       return avargeDegree/300;
   }


    /**
     * get all solutions
     * @return
     */
   public List<Chromosome> getSolutions(){

       double averageFitness = getAverageDegree(map.get("mature")) ;
       boolean isPrint = false;
       for (int i = 0;i < GAConfiguration.recursiveTimes;i++){
           map = breed(map);
           map = mature(map);
           map = survive(map);
           double newFitness = getAverageDegree(map.get("mature"));
           if(Math.abs(newFitness - averageFitness)/averageFitness < 0.0001 && !isPrint){
               System.out.println(" The result is convergent in generation : "+i);
               break;
//               isPrint = true;
           }
           averageFitness = newFitness;
       }
       List<Chromosome>  chromosomes = map.get("mature");
       chromosomes.addAll(map.get("toBeMature"));
       chromosomes.sort((x1,x2)->x2.getFitness()-x1.getFitness());
       if(solutionNum > chromosomes.size())return chromosomes;
       System.out.println(averageFitness);
       return chromosomes.subList(0,solutionNum);
   }

    public Map<String,List<Chromosome>> survive(Map<String,List<Chromosome>> map){
       map.put("mature",survive(map.get("mature")));
       return map;
    }

        private List<Chromosome> survive (List<Chromosome> list){
            List<Chromosome> result = new ArrayList<>();
            Random random = new Random();
            while(result.size() < GAConfiguration.sample_number/2){
                Chromosome c1 = list.get(random.nextInt(list.size()));
                Chromosome c2 = list.get(random.nextInt(list.size()));
                if(c1.getFitness()>=c2.getFitness()){
                    result.add(c1);
                    list.remove(c1);
                } else {
                    result.add(c2);
                    list.remove(c2);
                }
            }
            return result;
   }

   public Map<String,List<Chromosome>> mature(Map<String,List<Chromosome>> map){
       List<Chromosome> matureList = map.get("mature");
       if(matureList == null) matureList = new ArrayList<>();
       List<Chromosome> immatureList = map.get("toBeMature");
       if(immatureList!=null) {
           matureList.addAll(immatureList);
       }
       map.put("mature",matureList);
       map.put("toBeMature",map.get("immature"));
       map.remove("immature");
       return map;
   }
    /**
     * get next generations
     * @param map
     * @return
     */
   public Map<String,List<Chromosome>> breed(Map<String,List<Chromosome>> map){
       List<Chromosome> newList = new ArrayList<>();
       List<Chromosome> list = map.get("mature");
       list.sort(null);
       int dividerIndex = Math.max(1,new Double(list.size()*GAConfiguration.crossSolutionProp ).intValue());
       List<Chromosome> crossChromosomes = list.subList(0,dividerIndex);
       List<Chromosome> copyChromosomes  = list.subList(dividerIndex,list.size());
       newList.addAll(copyChromosomes);
       crossChromosomes = Reproduction.getNextGeneration(crossChromosomes,servers,tasks);

       newList.addAll(crossChromosomes);
       map.put("immature",newList);
       return map;
   }


}
