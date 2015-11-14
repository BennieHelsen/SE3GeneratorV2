package generatorRaderketen.randomLoad;

import generatorRaderketen.simulation.positionMessage.PositionMessage;
import generatorRaderketen.simulation.positionMessage.PositionMessageLoop;

import java.util.Random;

/**
 * Created by Thomas on 2/11/2015.
 */
public class RandomLoad {


    private int[] timeFrequentie = new int[24];
    private static int[] shipIds = new int[350];
    private static String[] centraleIds;
    private   Random rand = new Random();

    public RandomLoad() {
        init();
    }

    private void init() {

        //TIMEFREQ INIT
        for (int i = 0; i < timeFrequentie.length; i++) {

            if (i > 12) {
                timeFrequentie[i] = (23 - i) * 25;
            } else {
                timeFrequentie[i] = i * 25;
            }

        }

        //SHIPIDS init
        for (int i = 0; i < shipIds.length; i++) {


            int shipId = rand.nextInt(9000000) + 1000000;

            shipIds[i] = shipId;

        }

        centraleIds = new String[]{"Vlissingen", "Kaapduinen", "Westkapelle", "Cadzand", "Terneuzen", "Hoofdplaat", "Baarland",
                "Hansweert", "Waarde", "Saeftinge", "Ballastplaat", "Prosperpolder", "Oudendijk", "Blauwgaren", "Liefkenshoek"
                , "Kruisschans", "Kallo"};

    }

    public void start(){
        for (int i = 0; i < timeFrequentie.length; i++){
            System.out.println("Time :" + i + ":00. Messages: "  + timeFrequentie[i]);
            for (int j = 0; j< timeFrequentie[i] ; j++){

                int shipId = shipIds[rand.nextInt(shipIds.length)];
                String centaleId = centraleIds[rand.nextInt(centraleIds.length)];

                int distanceToLoadingDock = rand.nextInt(80000);

                try {
                    Thread.sleep(timeFrequentie[i]/60);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                PositionMessageLoop loop = new PositionMessageLoop(new PositionMessage(String.valueOf(rand.nextInt(50000)), String.valueOf(distanceToLoadingDock), String.valueOf(shipId),centaleId));

                Thread thread = new Thread(loop);

                thread.start();

            }
        }
    }

    public static void sendRandomPositionMessage(){
        Random rand = new Random();

        int shipId = shipIds[rand.nextInt(shipIds.length)];
        String centaleId = centraleIds[rand.nextInt(centraleIds.length)];

        int distanceToLoadingDock = rand.nextInt(80000);

        PositionMessageLoop loop = new PositionMessageLoop(new PositionMessage(String.valueOf(rand.nextInt(50000)), String.valueOf(distanceToLoadingDock), String.valueOf(shipId),centaleId));

        Thread thread = new Thread(loop);

        thread.start();
    }
}
