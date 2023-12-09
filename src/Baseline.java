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

        ArrayList<Double> mags = calculate3dMagnitudes(xAcc, yAcc, zAcc);
        for (int i = 1; i < mags.size()-1; i++) {
            double prev = mags.get(i-1);
            double current = mags.get(i);
            double next = mags.get(i+1);

            if (prev < current && current > next) {
                indexes.add(i);     // record the index of the peak
            }
        }

        return indexes;
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