
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.omg.PortableInterceptor.ServerIdHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/** 
* CollocationDegree Tester. 
* 
* @author <Authors name> 
* @since <pre>Dec 2, 2018</pre> 
* @version 1.0 
*/ 
public class CollocationDegreeTest {

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
    int degree =  CollocationDegree.getCollocationDegreeByGenes(genes,gaRun.getTasks());
    int degree2 = CollocationDegree.getCollocationDegreeByGenes(genes2,gaRun.getTasks());
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
} 




/** 
* 
* Method: getSelectedIndex(int mid, int[]range, int r) 
* 
*/ 
@Test
public void testGetSelectedIndex() throws Exception { 
//TODO: Test goes here... 

} 

} 
