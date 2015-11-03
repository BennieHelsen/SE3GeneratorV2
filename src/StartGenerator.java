import com.sun.media.jfxmedia.logging.Logger;
import generatorRaderketen.randomLoad.RandomLoad;
import generatorRaderketen.simulation.Simulation;

import java.util.Scanner;

/**
 * Created by Thomas on 1/11/2015.
 */
public class StartGenerator {




    public static void main(String[] args) {
        //  IncidentServiceProxy incidentServiceProxy;
        boolean running = true;
        Scanner scanner = new Scanner(System.in);
        int idKeuze;


        Logger.setLevel(Logger.INFO);

        while (running) {
            System.out.println("Geef generator modus: (1 voor simulatie, 2 voor random load).");
            idKeuze = scanner.nextInt();

            if (idKeuze == 1) {
               Simulation simulation = new Simulation();
               simulation.start();
                return;

            }
            if (idKeuze == 2) {
                RandomLoad randomLoad = new RandomLoad();
                randomLoad.start();
                return;
            }
        }
    }

}
