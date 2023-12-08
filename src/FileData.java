import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileData {
    public String text;
    public String filePath;
    public int correctNumberOfSteps;
    public int prediction;

    public FileData(String text, String filePath, int correctNumberOfSteps) {
        this.text = text;
        this.filePath = filePath;
        this.correctNumberOfSteps = correctNumberOfSteps;
        this.prediction = -1;
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
