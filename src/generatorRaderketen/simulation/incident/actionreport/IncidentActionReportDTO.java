package generatorRaderketen.simulation.incident.actionreport;

import java.util.HashMap;

/**
 * Created by Thomas on 14/11/2015.
 */
public class IncidentActionReportDTO {
    private String incidentType,shipId,dangerousCargo,numberOfPassengers,acctionToTake,centraleId;
    private HashMap<String,String> cargos;

    public IncidentActionReportDTO() {
    }

    public String getIncidentType() {
        return incidentType;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public String getShipId() {
        return shipId;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public String getDangerousCargo() {
        return dangerousCargo;
    }

    public void setDangerousCargo(String dangerousCargo) {
        this.dangerousCargo = dangerousCargo;
    }

    public String getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public void setNumberOfPassengers(String numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public String getAcctionToTake() {
        return acctionToTake;
    }

    public void setAcctionToTake(String acctionToTake) {
        this.acctionToTake = acctionToTake;
    }

    public HashMap<String, String> getCargos() {
        return cargos;
    }

    public void setCargos(HashMap<String, String> cargos) {
        this.cargos = cargos;
    }

    public String getCentraleId() {
        return centraleId;
    }

    public void setCentraleId(String centraleId) {
        this.centraleId = centraleId;
    }
}
