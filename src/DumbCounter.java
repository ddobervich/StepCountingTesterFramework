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

    public ArrayList<Double> calculate3dMagnitudes(ArrayList<Double> x, ArrayList<Double> y, ArrayList<Double> z) {
        ArrayList<Double> mags = new ArrayList<>();

        for (int i = 0; i < x.size(); i++) {
            mags.add( calculate3dVector(x.get(i), y.get(i), z.get(i)));
        }

        return mags;
    }

    private Double calculate3dVector(Double x, Double y, Double z) {
        return Math.sqrt(x*x + y*y + z*z);
    }
}
