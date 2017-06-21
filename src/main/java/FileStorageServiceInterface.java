/**
 * Created by wchen on 6/20/17.
 */
public interface FileStorageServiceInterface {
  void store (String name, String location);
  String getLocationByUserName(String name);
}
