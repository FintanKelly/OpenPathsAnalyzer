package openpaths;

import java.util.ArrayList;
import java.util.Date;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import oauthP5.apis.OpenPathsApi;
import oauthP5.oauth.*;
import org.joda.time.DateTime;

/**
 * Class will attempt to read the live JSON from OpenPaths.
 *
 * @author Fintan
 */
public class JSONReader {

    /*
     Request JSON Data:
     - Requests the JSON data from the OpenPaths server using my personal access and secret keys
     - Currently only grabs the last 30 days worth of data
     */
    public void readJSONData() {
        final String ACCESS = "POBIITU54UUVDGKU6QWDJBRFN4C5V3YPZF7TWUCNKNTZASU3K4DQ";
        final String SECRET = "9VPV5NO98NZW3HUE1B1PD4KKICE8QPUAXMU4ETMZQFF0RYN76K7O1IHAEE9E9R6M";
        final String URL = "https://openpaths.cc/api/1";

        OAuthService authService = new ServiceBuilder().provider(OpenPathsApi.class).apiKey(ACCESS).apiSecret(SECRET).build();

        OAuthRequest request = new OAuthRequest(Verb.GET, URL);
        Token signToken = new Token("", "");
        authService.signRequest(signToken, request);

        request.addQuerystringParameter("start_time", String.valueOf(System.currentTimeMillis() / 1000 - 30 * 24 * 60 * 60));
        request.addQuerystringParameter("end_time", String.valueOf(System.currentTimeMillis() / 1000));
        //request.addQuerystringParameter("num_points", "2000");

        Response requestResponse = request.send();

        JSONArray newJArray = JSONArray.fromObject(requestResponse.getBody());

        System.out.println(newJArray.toString());

        ArrayList<OpenPath> locations = new ArrayList<>();

        for (int i = 0; i < newJArray.size(); i++) {
            JSONObject tempObject = newJArray.getJSONObject(i);

            Vector3 newCoord = new Vector3(Float.parseFloat(tempObject.getString("lat")), Float.parseFloat(tempObject.getString("lon")), Float.parseFloat(tempObject.getString("alt")));
            Date newDate = new Date(tempObject.getLong("t") * 1000);
            DateTime actualDate = new DateTime(newDate);

            OpenPath newEntry = new OpenPath(newCoord, actualDate, (String) tempObject.get("device"));
            locations.add(newEntry);
        }
    }
}
