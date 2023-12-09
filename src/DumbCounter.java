import java.util.ArrayList;

public class DumbCounter extends StepCounter {

    @Override
    public ArrayList<Integer> getStepIndexes(FileData file) {
        if (file.xAcc == null) {
            file.extractSensorData();
        }

        // Assume 1 step every 10 sensor measurements
        ArrayList<Integer> stepIndexes = new ArrayList<>();
        for (int i = 0; i < file.xAcc.size(); i += 10) {
            stepIndexes.add(i);
        }

        return stepIndexes;
    }
}
