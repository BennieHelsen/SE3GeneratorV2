package generatorRaderketen.simulation;

import be.kdg.se3.proxy.IncidentServiceProxy;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;

/**
 * Created by Thomas on 3/11/2015.
 */
public class IncidentReporter {

    private static IncidentServiceProxy incidentServiceProxy = new IncidentServiceProxy();

    public static String getIncident(String shipId) {
        String xml = "";
        try {

            String url = "www.services4se3.com/incidentservice/simulate/" + shipId;



            JSONObject incident = new JSONObject( incidentServiceProxy.get(url));

            xml = XML.toString(incident);

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return xml;
    }

}
