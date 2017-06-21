import static spark.Spark.get;
import static spark.Spark.staticFileLocation;
import java.util.HashMap;
import java.util.Map;
import spark.QueryParamsMap;

/**
 * This is the entry point for running the Maps inside of the JavaSpark web server,
 */
public class MapServer {
    private static Map<String, String> userLocationPair;

    public static void main(String[] args) {
        userLocationPair = new HashMap<>();
        staticFileLocation("/page");

        get("/api/save", (req, res) -> {
            QueryParamsMap map = req.queryMap();
            try {
                String name = map.get("name").value();
                String place = map.get("place").value();
                boolean validInput = false;
                if (name!=null && !name.isEmpty() && place!=null && !place.isEmpty()) {
                    userLocationPair.put(name, place);
                    validInput = true;
                }
                return Boolean.valueOf(validInput);
            }
            catch (Exception e){
                return "Error: " + e.getMessage();
            }
        });

        get("/api/search", (req, res) -> {
            QueryParamsMap map = req.queryMap();
            try {
                String user = map.get("user").value();
                if ( user==null || user.isEmpty() ) {
                    throw new IllegalArgumentException("User not found.");
                }
                String location = userLocationPair.get(user);
                if ( location==null || location.isEmpty() ) {
                    throw new IllegalArgumentException("location not found.");
                }
                String str = location.replace(",", "+");
                return str;
            }
            catch (Exception e){
                return "Error: " + e.getMessage();
            }
        });
    }
}
