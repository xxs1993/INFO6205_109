import java.io.*;
import java.util.List;

public class FileUtil {

    public static String writeData(List<String> list){

        String fileName = System.currentTimeMillis()+ ".csv";
        File file = new File(fileName);
        if(!file.exists()) {
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            return "";
        }
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            for(int i =0;i< list.size();i++){
                writer.write(list.get(i));
                writer.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
            return "";
        }
        return fileName;
    }

}
