package generatorRaderketen.simulation.route;

/**
 * This is an entity class for a Route. This will be use for rows form the CSV files
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
