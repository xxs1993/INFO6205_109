import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/** 
* Fitness Tester.
* 
* @author <Authors name> 
* @since <pre>Dec 2, 2018</pre> 
* @version 1.0 
*/ 
public class FitnessTest {

    private GARun gaRun;

@Before
public void before() throws Exception {
   gaRun  = new GARun(3,3,3);
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: getCollocationDegreeByGenes(List<Server> genes, List<Task> tasks) 
* 
*/ 
@Test
public void testGetCollocationDegreeByGenes() throws Exception { 
//TODO: Test goes here...
    List<Server> genes = new ArrayList<>();
    for(int i=0;i< gaRun.getTaskNum();i++){
        genes.add(gaRun.getServers().get(i));
    }
    List<Server> genes2 = genes;

    int degree =  Fitness.getCollocationDegreeByGenes(genes,gaRun.getTasks());
    int degree2 = Fitness.getCollocationDegreeByGenes(genes2,gaRun.getTasks());
    Assert.assertTrue(degree >0);
    Assert.assertEquals(degree,degree2);
} 

/** 
* 
* Method: fitness(List<Chromosome> list) 
* 
*/ 
@Test
public void testFitness() throws Exception { 
//TODO: Test goes here...
    List<Chromosome> oriList = gaRun.getMap().get("mature");
    oriList.sort(null);
    int midFitness = oriList.get(oriList.size()/2).getFitness();
    int avgFitness = 0;
    for(int i =0;i<50;i++){
        List<Chromosome> list = Fitness.fitness(oriList);
        avgFitness = list.get(0).getFitness() + list.get(1).getFitness()+avgFitness;
    }
    Assert.assertTrue(avgFitness/100>midFitness);
} 




/** 
* 
* Method: getSelectedIndex(int mid, int[]range, int r) 
* 
*/ 
@Test
public void testGetSelectedIndex() throws Exception { 
//TODO: Test goes here...
    double range[] = new double[5];
    range[0] = 5;
    range[1] = 10;
    range[2] = 15;
    range[3] = 20;
    range[4] = 25;
    int index = Fitness.getSelectedIndex(range,14);
    int index2 = Fitness.getSelectedIndex(range,24);
    int index3 = Fitness.getSelectedIndex(range,15);

    Assert.assertEquals(2,index);
    Assert.assertEquals(4,index2);
    Assert.assertEquals(3,index3);


} 

} 
