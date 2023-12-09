import java.util.ArrayList;

public class Baseline extends StepCounter {

    @Override
    public ArrayList<Integer> getStepIndexes(FileData file) {
        if (file.xAcc == null) {        // if we haven't extracted the data, do it.
            file.extractSensorData();
        }

        return getStepIndexes(file.xAcc, file.yAcc, file.zAcc);
    }

    private ArrayList<Integer> getStepIndexes(ArrayList<Double> xAcc, ArrayList<Double> yAcc, ArrayList<Double> zAcc) {
        ArrayList<Integer> indexes = new ArrayList<>();

        System.err.println("You must implement getStepIndexes in Baseline.java");

        //TODO: use xAcc, yAcc and zAcc to calculate list of 3d magnitudes
        //TODO: loop over magnitudes and count # of peaks

        return indexes;
    }

    public ArrayList<Double> calculate3dMagnitudes(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> z) {
        System.err.println("You must implement calculate3dMagnitudes in Baseline.java");

        return null;
    }

}