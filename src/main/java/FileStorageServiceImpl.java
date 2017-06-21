import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wchen on 6/20/17.
 */
public class FileStorageServiceImpl  implements FileStorageServiceInterface {
  private static Map<String, String> userLocationPair = new HashMap<>();

  public FileStorageServiceImpl() throws IOException {
  }

  @Override
  public void store(String name, String location) {
    BufferedWriter bw = null;
    FileWriter fw = null;

    try {
      File file = new File("recBook.txt");
      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      // true = append file
      fw = new FileWriter(file.getAbsoluteFile(), true);
      bw = new BufferedWriter(fw);

      String record = createRecordToStore(name, location);
      bw.write(record);
      System.out.println("Done");
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (bw != null)
          bw.close();
        if (fw != null)
          fw.close();
      } catch (IOException ex) {
        ex.printStackTrace();
      }
    }
  }

  @Override
  public String getLocationByUserName(String name) {
    try(BufferedReader br = new BufferedReader(new FileReader("recBook.txt"))) {
      for(String line; (line = br.readLine()) != null; ) {
        // process the line. each array has 3 elements: name, delimiter"|", and location
        List<String> recList = Arrays.asList(line.split("[|]"));

        userLocationPair.put(recList.get(0), recList.get(recList.size()-1));
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return userLocationPair.get(name);
  }


  private String createRecordToStore(String name, String location) {
    //no need to validate the inputs, they are done already.
    StringBuffer rec = new StringBuffer();
    rec.append(name);
    rec.append("|");
    rec.append(location);
    rec.append("\n");
    return rec.toString();
  }
}
