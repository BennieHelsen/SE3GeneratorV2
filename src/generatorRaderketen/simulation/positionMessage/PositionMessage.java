package generatorRaderketen.simulation.positionMessage;



import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * This is an entity class for a positionMessage. This class will be converted to XML and send to the processing unit.
 */

@XmlRootElement
public class PositionMessage {

    private String delay, distanceToLoadingDock, shipId;
    private Date timestamp;
    private String centralId;

    public PositionMessage() {
    }

    public PositionMessage(String delay, String distanceToLoadingDock, String shipId, String centralId) {
        this.delay = delay;
        this.distanceToLoadingDock = distanceToLoadingDock;
        this.shipId = shipId;
        this.centralId = centralId;
        this.timestamp = new Date();
    }

    public String getDelay() {
        return delay;
    }

    @XmlElement
    public String getDistanceToLoadingDock() {
        return distanceToLoadingDock;
    }
    @XmlElement
    public String getShipId() {
        return shipId;
    }
    @XmlElement
    public Date getTimestamp() {
        return timestamp;
    }
    @XmlElement
    public String getCentralId() {
        return centralId;
    }
}
