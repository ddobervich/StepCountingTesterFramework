import java.util.ArrayList;

public abstract class StepCounter {

    public abstract ArrayList<Integer> getStepIndexes(FileData file);

    /***
     * Given a list of values and a list of indexes, return a new list consisting of the values at the
     * indexes specified in the indexes list.
     * @param vals List of values.  Method will return some of these (only those listed in indexes list).
     * @param indexes List of indexes whose values you want to return.
     * @return A list of values at the specified indexes.
     */
    public static ArrayList<Double> extractValues(ArrayList<Integer> indexes, ArrayList<Double> vals) {
        ArrayList<Double> output = new ArrayList<>();

        for (Integer index : indexes) {
            output.add( vals.get(index) );
        }

        return output;
    }
}
