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
 * Created by Thomas on 3/11/2015.
 */
public class IncidentReporter {

    private static IncidentServiceProxy incidentServiceProxy = new IncidentServiceProxy();

    public static String getIncident(String shipId) {
        Writer writer = new StringWriter();
        try {

            String url = "www.services4se3.com/incidentservice/simulate/" + shipId;

            JSONObject incident = new JSONObject(incidentServiceProxy.get(url));
            IncidentReport report = new IncidentReport(incident.get("incidentType"), incident.get("IMO"));


            try {
                Marshaller.marshal(report, writer);
            } catch (MarshalException | ValidationException e) {
                e.printStackTrace();
            }


        } catch (JSONException | IOException e) {
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
