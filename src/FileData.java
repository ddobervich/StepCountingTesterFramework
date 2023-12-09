import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class FileData {
    public String text;
    public String filePath;
    public int correctNumberOfSteps;
    public int stepPrediction;

    public ArrayList<Double> xAcc, yAcc, zAcc, xGyro, yGyro, zGyro;
    public ArrayList<Integer> stepIndexes;

    /***
     * Construct a FileData object to store the text and extracted data associated
     * with a data file.
     *
     * Run the static method FileData.loadFile(...)  to create new instances.
     * This ensures that an object is only created if both the text and the correct #
     * of steps can be read
     * @param text the text from the file (should be in csv format with 1st line column names)
     * @param filePath the path of the file this object represents
     * @param correctNumberOfSteps the correct # of steps as recorded in the file name
     */
    private FileData(String text, String filePath, int correctNumberOfSteps) {
        this.text = text;
        this.filePath = filePath;
        this.correctNumberOfSteps = correctNumberOfSteps;
        this.stepPrediction = -1;
    }

    public static ArrayList<Double> getColumnAsList(String[] lines, int colNum) {
        ArrayList<Double> vals = new ArrayList<>();

        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] valuesArray = line.split(",");
            double val = Double.parseDouble(valuesArray[colNum].trim());
            vals.add(val);
        }

        return vals;
    }

    public void extractSensorData() {
        System.out.println("Separating data into separate lists by sensor...");
        String[] lines = text.split("\n");

        System.out.println(lines.length + " data values");
        xAcc = FileData.getColumnAsList(lines, 0);
        yAcc = FileData.getColumnAsList(lines, 1);
        zAcc = FileData.getColumnAsList(lines, 2);
        xGyro = FileData.getColumnAsList(lines, 3);
        yGyro = FileData.getColumnAsList(lines, 4);
        zGyro = FileData.getColumnAsList(lines, 5);
    }

    public static int extractNumSteps(Path path) {
        String filename = path.getFileName().toString().toLowerCase();
        if (filename.contains("step")) {
            filename = filename.substring(0, filename.indexOf("step"));
        }
        filename = filename.replaceAll("[^\\d]","");
        int steps;
        try {
            steps = Integer.parseInt(filename.trim());
        } catch (Exception e) {
            System.err.println("Error extracting # of steps from filename: " + filename);
            return -1;
        }

        return steps;
    }

    public static String readFile(String fileName) throws IOException {
        return new String(Files.readAllBytes(Paths.get(fileName)));
    }

    public static FileData loadFile(Path path) {
        String filename = path.getFileName().toString();
        int numSteps = extractNumSteps(path);
        String text;

        if (numSteps == -1) {
            System.err.println("Couldn't get correct # of steps for file: " + path);
            return null;
        }

        try {
            text = readFile(path.toString());
        } catch (Exception e) {
            System.err.println("Error reading the file: " + path);
            return null;
        }

        return new FileData(text, path.toString(), numSteps);
    }
}
