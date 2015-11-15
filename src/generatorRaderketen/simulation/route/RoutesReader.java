package generatorRaderketen.simulation.route;



import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import java.util.HashMap;

/**
 * This class processes all the files in a given path. It returns them as a HashMap
 */
public class RoutesReader {


    public  HashMap loadCSVs(String directory) throws IOException {

        HashMap<String, ArrayList<Route>> allRoutes = new HashMap<>();


        Files.walk(Paths.get(directory)).forEach(filePath -> {
            if (Files.isRegularFile(filePath)) {
                allRoutes.put(filePath.getFileName().toString().replaceFirst("[.][^.]+$", ""), readCSV(filePath));
            }
        });

        return allRoutes;


    }

    //voor elk shipid.csv elke row inlezen en in arraylist steken.
    private  ArrayList<Route> readCSV(Path file) {

        ArrayList<Route> routes = new ArrayList<>();

        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";

        try {

            br = new BufferedReader(new FileReader(String.valueOf(file)));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] route = line.split(cvsSplitBy);
                routes.add(new Route(route[0], route[1], route[2]));


            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        return routes;
    }

}
