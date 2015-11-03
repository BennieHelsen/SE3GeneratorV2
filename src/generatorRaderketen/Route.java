package generatorRaderketen;

/**
 * Created by Thomas on 1/11/2015.
 */
public class Route {

    private String delay,distanceToLoadingDock;
    private String centralId;

    public Route(String delay,  String centralId,String distanceToLoadingDock) {
        this.delay = delay;
        this.distanceToLoadingDock = distanceToLoadingDock;
        this.centralId = centralId;
    }

    public String getDelay() {
        return delay;
    }

    public String getDistanceToLoadingDock() {
        return distanceToLoadingDock;
    }

    public String getCentralId() {
        return centralId;
    }

    @Override
    public String toString() {
        return "Route{" +
                "delay='" + delay + '\'' +
                ", distanceToLoadingDock='" + distanceToLoadingDock + '\'' +
                ", centralId='" + centralId + '\'' +
                '}';
    }
}
