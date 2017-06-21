import java.util.HashMap;
import java.util.Map;

/**
 * Created by wchen on 6/20/17.
 */
public class FileStorageServiceImpl  implements FileStorageServiceInterface {
  private static Map<String, String> userLocationPair = new HashMap<>();

  @Override
  public void store(String name, String location) {
    userLocationPair.put(name, location);
  }

  @Override
  public String getLocationByUserName(String name) {
    return userLocationPair.get(name);
  }
}
