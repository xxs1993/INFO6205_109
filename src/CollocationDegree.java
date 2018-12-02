import java.util.*;

public class CollocationDegree {

    public static int getCollocationDegreeByGenes(List<Server> genes,List<Task> tasks) {
        //TODO:
        Map<Integer,Double> map = new HashMap<>();
        for(int i = 0;i<genes.size();i++) {
            Task task = tasks.get(i);
            Server server = genes.get(i);
            int usingTime = task.getUsingTime();
            int speed = server.getExecutingSpeedList().get(i);
            double time = usingTime / speed;
            if (map.get(server.getId()) == null) {
                map.put(server.getId(), time);
                continue;
            }
            map.put(server.getId(), map.get(server.getId()) + time);
        }
        //TODO : get longest time
        double longestTime = 0;

        return getCollocationDegreeByTime(longestTime);

    }


    //quicksort
    public static int getMiddle(int[] numbers, int low,int high) //find the middle（lowest）
    {
        int temp = numbers[low];
        while(low < high)
        {
            while(low < high && numbers[high] >= temp)
            {
                high--;
            }
            numbers[low] = numbers[high];
            while(low < high && numbers[low] < temp)
            {
                low++;
            }
            numbers[high] = numbers[low] ;
        }
        numbers[low] = temp ;
        return low ;
    }

    public static void quickSort(int[] numbers,int low,int high)  //recursive order
    {
        if(low < high)
        {
            int middle = getMiddle(numbers,low,high);
            quickSort(numbers, low, middle-1);
            quickSort(numbers, middle+1, high);
        }

    }

    public static void quick(double numbers)
    {
        if(numbers.length > 0)   //check if the array is 0;
        {
            quickSort(numbers, 0, numbers.length-1);
        }
    }



    private static int getCollocationDegreeByTime(double time){
        return new Double(time * 100).intValue();
    }

    public static List<Chromosome> fitness(List<Chromosome> list){
        //TODO: select parent chromosome
        List<Chromosome> result = new ArrayList<>();
        int collocationDegreeSum  = 0;
        int[] range = new int[list.size()];
        for(int i = 0;i<list.size();i++){
            Chromosome chromosome = list.get(i);
            collocationDegreeSum = chromosome.getCollocationDegree()+ collocationDegreeSum;
            range[i] = collocationDegreeSum;
        }
        Random random = new Random();
        int r1 = random.nextInt(collocationDegreeSum);
        int r2 = random.nextInt(collocationDegreeSum);
        int mid = list.size()/2;

        int index1 =getSelectedIndex(mid,range,r1);
        int index2 =getSelectedIndex(mid,range,r2) ;

        result.add(list.get(index1));
        result.add(list.get(index2));

        //TODO: find index in which range


        return result;
    }

    private  static int getSelectedIndex(int mid,int[]range,int r){
        while (mid > 0 ){
            if(range[mid]>r ){
                if(mid == 0) break;
                if(range[--mid] < r) {
                   return mid;
                }
            }else if(range[mid] <r){
                if(range[++mid]>r){
                    return mid;
                }
            }else if(range[mid] == r){
                return mid+1;
            }
        }
        return 0;
    }

}
