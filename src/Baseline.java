import java.util.ArrayList;

public class Baseline implements StepCounter {

    @Override
    public int countSteps(ArrayList<Double> xAcc, ArrayList<Double> yAcc, ArrayList<Double> zAcc, ArrayList<Double> xGyro, ArrayList<Double> yGyro, ArrayList<Double> zGyro) {
        System.err.println("You must implement countSteps in Baseline.java");

        ArrayList<Integer> stepIndexes = getStepIndexes(xAcc, yAcc, zAcc, xGyro, yGyro, zGyro);

        return stepIndexes.size();
    }

    @Override
    public ArrayList<Integer> getStepIndexes(ArrayList<Double> xAcc, ArrayList<Double> yAcc, ArrayList<Double> zAcc, ArrayList<Double> xGyro, ArrayList<Double> yGyro, ArrayList<Double> zGyro) {
        ArrayList<Integer> indexes = new ArrayList<>();

        System.err.println("You must implement getStepIndexes in Baseline.java");

        return indexes;
    }

}
