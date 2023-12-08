import java.util.ArrayList;

public interface StepCounter {

    public int countSteps(ArrayList<Double> xAcc,
                          ArrayList<Double> yAcc,
                          ArrayList<Double> zAcc,
                          ArrayList<Double> xGyro,
                          ArrayList<Double> yGyro,
                          ArrayList<Double> zGyro);

    public ArrayList<Integer> getStepIndexes(ArrayList<Double> xAcc,
                                          ArrayList<Double> yAcc,
                                          ArrayList<Double> zAcc,
                                          ArrayList<Double> xGyro,
                                          ArrayList<Double> yGyro,
                                          ArrayList<Double> zGyro);

    public static ArrayList<Double> getColumnAsList(String[] lines, int colNum) {
        System.err.println("Implement getColumnAsList(...) in StepCounter.java");
        // TODO: implement this
        return new ArrayList<>();
    }
}
