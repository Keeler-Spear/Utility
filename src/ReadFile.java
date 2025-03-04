import javax.swing.*;
import java.io.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A static class that can read data from files and output data in a verity of ways.
 * <p>
 *     This class currently supports CSV files.
 * </p>
 *
 * @author Keeler Spear
 * @version %I%, %G%
 * @since 1.0
 */
public class ReadFile {
    final static int MAX_SIZE = 10000;

    //Let the user select a specific column based on a String they provide which is the "title" of the column
    //ToDo: Allow for a boolean input labelAtStart. If true, that means that the sample's labels are in first column (after skip). Otherwise the labels will be in the last column
    //c1 and c2 are classes; skip is the number of elements the program should avoid
    public static double[][] csvToArray(String file, String c1, String c2, int skip) {
        List<double[]> dataList = new ArrayList<>();

        try (Scanner in = new Scanner(new FileInputStream(file))) {
            if (in.hasNextLine()) {
                in.nextLine(); // Skip the first line (header or irrelevant data)
            }

            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] data = line.split(",");

                if (data.length <= skip) {
                    continue; // Skip lines that don't have enough columns
                }

                double[] row = new double[data.length - skip];

                for (int i = skip; i < data.length; i++) {
                    data[i] = data[i].replace(c1, "0").replace(c2, "1");
                    row[i - skip] = Double.parseDouble(data[i]);
                }

                dataList.add(row);
            }
        } catch (IOException | NumberFormatException ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        // Convert list to array
        double[][] vals = new double[dataList.size()][];
        for (int i = 0; i < dataList.size(); i++) {
            vals[i] = dataList.get(i);
        }

        return vals;
    }

}
