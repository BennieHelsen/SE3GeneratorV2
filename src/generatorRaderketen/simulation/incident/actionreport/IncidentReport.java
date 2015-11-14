package generatorRaderketen.simulation.incident.actionreport;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Thomas on 14/11/2015.
 */

@XmlRootElement
public class IncidentReport {
    String incidentType,IMO;

    public IncidentReport(Object incidentType, Object imo) {
        this.incidentType = incidentType.toString();
        this.IMO = imo.toString();

    }
    @XmlElement
    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    @XmlElement
    public String getIMO() {
        return IMO;
    }

    public void setIMO(String IMO) {
        this.IMO = IMO;
    }
}
