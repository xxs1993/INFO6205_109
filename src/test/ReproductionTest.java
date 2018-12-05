import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ReproductionTest {

  private Reproduction reproduction;
  List<Task> task = new ArrayList<Task>();
  List<Server> server = new ArrayList<Server>();
  List<Chromosome> list = new ArrayList<Chromosome>();


  Chromosome fa = new Chromosome();
  Chromosome mo = new Chromosome();
  Chromosome son = new Chromosome();
  @Test
    public void testCrossover(){
        for(int i=0;i<5;i++){
            Task t = new Task(i);
            task.add(t);
        }
        for(int j=0;j<5;j++){
            Server s = new Server(j,j);
            server.add(s);
        }
        List<Server> geneFa = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            geneFa.add(server.get(i%5));
        }
        fa.setGenes(geneFa);
        List<Server> geneMo = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            geneMo.add(server.get(5-(i%5)));
        }
        mo.setGenes(geneMo);

        List<Server> serverResult = new ArrayList<Server>();
        for(int i=0;i<5;i++)
        {
            serverResult.add(server.get(i));
        }
        for(int i=0;i<5;i++) {
            serverResult.add(server.get(5 - i));
        }
        son.setGenes(serverResult);
        assertEquals(son,Reproduction.crossover(fa,mo));
    }

    @Test
    public void testMutate(){
        for(int i=0;i<5;i++){
            Task t = new Task(i);
            task.add(t);
        }
        for(int j=0;j<5;j++){
            Server s = new Server(j,j);
            server.add(s);
        }


        List<Server> serverResult = new ArrayList<Server>();
        for(int i=0;i<5;i++)
        {
            serverResult.add(server.get(i));
        }
        for(int i=0;i<5;i++) {
            serverResult.add(server.get(5 - i));
        }
        son.setGenes(serverResult);
        Chromosome newson;
        newson = Reproduction.mutate(son,server);

        Chromosome sons = new Chromosome();
        int times = 0;
        for(int i=0;i<2000;i++){
            sons = Reproduction.mutate(son,server);
            if(son.equals(sons)){
                times++;
            }
        }
        boolean right = false;
        if(times>=70){
            right = true;
        }

        assertEquals(10,newson.getGenes().size());
        assertEquals(1,right);
    }

    @Test
    public void testGetNextGeneration(){
        for(int i=0;i<5;i++){
            Task t = new Task(i);
            task.add(t);
        }
        for(int j=0;j<5;j++){
            Server s = new Server(j,j);
            server.add(s);
        }

        List<Server> geneFa = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            geneFa.add(server.get(i%5));
        }
        fa.setGenes(geneFa);
        List<Server> geneMo = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            geneMo.add(server.get(5-(i%5)));
        }
        mo.setGenes(geneMo);

        List<Server> serverResult = new ArrayList<Server>();
        for(int i=0;i<5;i++)
        {
            serverResult.add(server.get(i));
        }
        for(int i=0;i<5;i++) {
            serverResult.add(server.get(5 - i));
        }
        son.setGenes(serverResult);
        Chromosome newson;
        newson = Reproduction.getNextGeneration(fa,mo,server);

        Chromosome sons = new Chromosome();
        int times = 0;
        for(int i=0;i<2000;i++){
            sons = Reproduction.mutate(son,server);
            if(son.equals(sons)){
                times++;
            }
        }
        boolean right = false;
        if(times>=1400&&times<=1500){
            right = true;
        }

        //assertEquals(son,reproduction.crossover(fa,mo));
        assertEquals(10,newson.getGenes().size());
        assertEquals(1,right);
    }

    @Test
    public void testGetNextGenerationList(){
        for(int i=0;i<5;i++){
            Task t = new Task(i);
            task.add(t);
        }
        for(int j=0;j<5;j++){
            Server s = new Server(j,j);
            server.add(s);
        }

        Chromosome chromosome1 = new Chromosome();
        List<Server> gene1 = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            gene1.add(server.get(i%5));
        }
        chromosome1.setGenes(gene1);
        chromosome1.setCollocationDegree(CollocationDegree.getCollocationDegreeByGenes(chromosome1.getGenes(),task));

        Chromosome chromosome2 = new Chromosome();
        List<Server> gene2 = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            gene2.add(server.get(i%3));
        }
        chromosome2.setGenes(gene1);
        chromosome2.setCollocationDegree(CollocationDegree.getCollocationDegreeByGenes(chromosome2.getGenes(),task));

        Chromosome chromosome3 = new Chromosome();
        List<Server> gene3 = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            gene3.add(server.get(i%2));
        }
        chromosome3.setGenes(gene1);
        chromosome3.setCollocationDegree(CollocationDegree.getCollocationDegreeByGenes(chromosome3.getGenes(),task));

        Chromosome chromosome4 = new Chromosome();
        List<Server> gene4 = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            gene4.add(server.get(i%7));
        }
        chromosome4.setGenes(gene1);
        chromosome4.setCollocationDegree(CollocationDegree.getCollocationDegreeByGenes(chromosome4.getGenes(),task));

        Chromosome chromosome5 = new Chromosome();
        List<Server> gene5 = new ArrayList<Server>();
        for(int i=0;i<10;i++){
            gene5.add(server.get(i%8));
        }
        chromosome5.setGenes(gene1);
        chromosome5.setCollocationDegree(CollocationDegree.getCollocationDegreeByGenes(chromosome5.getGenes(),task));

        list.add(chromosome1);
        list.add(chromosome2);
        list.add(chromosome3);
        list.add(chromosome4);
        list.add(chromosome5);

        List<Chromosome> result;
        result = Reproduction.getNextGeneration(list,server,task);


        assertEquals(list.size(),result.size());
    }
}
