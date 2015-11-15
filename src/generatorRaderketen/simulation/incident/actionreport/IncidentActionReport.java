package generatorRaderketen.simulation.incident.actionreport;

import java.util.HashMap;

/**
 * Entity class representing a IncidentActionReport
 */
public class IncidentActionReport {
    private String incidentType,shipId,dangerousCargo,numberOfPassengers,acctionToTake,centraleId;
    private HashMap<String,String> cargos;

    public IncidentActionReport() {
    }

    public String getIncidentType() {
        return incidentType;
    }

    public String getShipId() {
        return shipId;
    }

    public String getDangerousCargo() {
        return dangerousCargo;
    }

    public String getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public String getAcctionToTake() {
        return acctionToTake;
    }

    public HashMap<String, String> getCargos() {
        return cargos;
    }

    public void setIncidentType(String incidentType) {
        this.incidentType = incidentType;
    }

    public void setShipId(String shipId) {
        this.shipId = shipId;
    }

    public void setDangerousCargo(String dangerousCargo) {
        this.dangerousCargo = dangerousCargo;
    }

    public void setNumberOfPassengers(String numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setAcctionToTake(String acctionToTake) {
        this.acctionToTake = acctionToTake;
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
