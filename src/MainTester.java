import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MainTester {
    public static void main(String[] args) {
        StepCounter counter = new Baseline();  /* instantiate your step counter here */

        List<FileData> results = generatePredictionsWith(counter, "testFiles/blk3");
        display(results);

    }

    private static List<FileData> generatePredictionsWith(StepCounter counter, String testFilesFolder) {
        ArrayList<FileData> results = new ArrayList<>();
        for (Path file : getTestFiles(testFilesFolder)) {
            FileData fileData = FileData.loadFile(file);
            makePrediction(counter, fileData);
            results.add(fileData);
        }
        return results;
    }

    private static void makePrediction(StepCounter counter, FileData file) {
        String[] lines = file.text.split("\n");

        ArrayList<Double> accX = StepCounter.getColumnAsList(lines, 0);
        ArrayList<Double> accY = StepCounter.getColumnAsList(lines, 1);
        ArrayList<Double> accZ = StepCounter.getColumnAsList(lines, 2);
        ArrayList<Double> gyroX = StepCounter.getColumnAsList(lines, 3);
        ArrayList<Double> gyroY = StepCounter.getColumnAsList(lines, 4);
        ArrayList<Double> gyroZ = StepCounter.getColumnAsList(lines, 5);

        file.prediction = counter.countSteps(accX, accY, accZ, gyroX, gyroY, gyroZ);
    }

    private static void display(List<FileData> results) {
        if (results.size() == 0) {
            System.err.println("No results to display (results list size 0)");
            return;
        }

        System.out.println("Filename \t\t\t prediction \t\t correct \t\t error");
        double totalError = 0;

        for (FileData result : results) {
            int error = result.correctNumberOfSteps - result.prediction;
            totalError += (error*error);
            System.out.println(result.filePath + "\t\t" + result.prediction + "\t\t" + result.correctNumberOfSteps + "\t\t" + error);
        }

        System.out.println();
        System.out.println("Mean squared error: " + (totalError/results.size()));
    }

    private static ArrayList<Path> getTestFiles(String TEST_FILE_FOLDER) {
        ArrayList<Path> paths = new ArrayList<>();
        Path workDir = Paths.get(TEST_FILE_FOLDER);
        if (!Files.notExists(workDir)) {
            try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(workDir)) {
                for (Path p : directoryStream) {
                    paths.add(p);
                }
                return paths;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
