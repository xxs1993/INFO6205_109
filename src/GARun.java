import java.util.*;

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
       Map<String,List<Chromosome>> map = new HashMap<>();
       for(int i=0;i<GAConfiguration.sample_number*2;i++){
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
       chromosome.setCollocationDegree(CollocationDegree.getCollocationDegreeByGenes(genes,tasks));
       return chromosome;
   }

    /**
     * get all solutions
     * @return
     */
   public List<Chromosome> getSolutions(){
       List<Chromosome> chromosomes = initChromosomes();
       Map<String,List<Chromosome>> map = new HashMap<>();
       map.put("immature",new ArrayList<>());
       map.put("mature",chromosomes);
       for (int i = 0;i < GAConfiguration.recursiveTimes;i++){
           map = breed(map);
           map = mature(map);
           map = survive(map);
       }
       chromosomes = map.get("mature");
       chromosomes.addAll(map.get("toBeMature"));
       chromosomes.sort(null);
       System.out.println(chromosomes.get(chromosomes.size()-1).getCollocationDegree());
       return chromosomes.subList(0,solutionNum);
   }

    private Map<String,List<Chromosome>> survive(Map<String,List<Chromosome>> map){
       map.put("mature",survive(map.get("mature")));
       return map;
    }

        private List<Chromosome> survive (List<Chromosome> list){
            List<Chromosome> result = new ArrayList<>();
            Random random = new Random();
            while(result.size() < GAConfiguration.sample_number/2){
                Chromosome c1 = list.get(random.nextInt(list.size()));
                Chromosome c2 = list.get(random.nextInt(list.size()));
                if(c1.getCollocationDegree()>=c2.getCollocationDegree()){
                    result.add(c1);
                    list.remove(c1);
                } else {
                    result.add(c2);
                    list.remove(c2);
                }
            }
            return result;
   }

   private Map<String,List<Chromosome>> mature(Map<String,List<Chromosome>> map){
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
   private Map<String,List<Chromosome>> breed(Map<String,List<Chromosome>> map){
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
