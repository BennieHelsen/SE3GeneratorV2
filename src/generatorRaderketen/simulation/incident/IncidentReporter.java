package generatorRaderketen.simulation.incident;


import be.kdg.se3.proxy.IncidentServiceProxy;
import generatorRaderketen.simulation.incident.actionreport.IncidentReport;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.ValidationException;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/**
 * This class uses the IncidentServiceProxy to retrieve information of a given shipId (status and incidentReport)
 */
public class IncidentReporter {

    private static IncidentServiceProxy incidentServiceProxy = new IncidentServiceProxy();

    public static String getIncident(String shipId) {
        Writer writer = new StringWriter();
        try {

            String url = "www.services4se3.com/incidentservice/simulate/" + shipId;

            JSONObject incident = new JSONObject(incidentServiceProxy.get(url));
            IncidentReport report = new IncidentReport(incident.get("incidentType"), incident.get("IMO"));


            Marshaller marshaller = new Marshaller(writer);

            marshaller.marshal(report);




        } catch (JSONException | IOException | MarshalException | ValidationException e) {
            e.printStackTrace();
        }

        return writer.toString();
    }


    public static boolean getStatus(String shipId) {

        boolean status = true;
        try {

            String url = "www.services4se3.com/incidentservice/status/" + shipId;

            JSONObject incident = new JSONObject(incidentServiceProxy.get(url));

           status = incident.getBoolean("incidentStatus");


        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return status;
    }
}
