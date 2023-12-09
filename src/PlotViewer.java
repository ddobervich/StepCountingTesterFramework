import Plot.PlotWindow;
import Plot.ScatterPlot;
import java.nio.file.Path;
import java.util.ArrayList;

public class PlotViewer {
    private static final String TEST_FILE_FOLDER = "testFiles/blk3";

    public static void main(String[] args) {
        //Baseline counter = new Baseline();  /* instantiate your step counter here */
        DumbCounter counter = new DumbCounter();

        ArrayList<Path> paths = MainTester.getTestFiles(TEST_FILE_FOLDER);
        Path fileToPlot = paths.get(2);  // <-- file to plot

        System.out.println("Plotting data for: " + fileToPlot.toString());
        FileData fileData = FileData.loadFile(fileToPlot);
        fileData.extractSensorData();
        MainTester.makePrediction(counter, fileData);

        System.out.println("Your prediction: " + fileData.stepPrediction + " steps.  Actual: " + fileData.correctNumberOfSteps + " steps");

        ScatterPlot plt = new ScatterPlot(100,100,1100, 700);

        // ======= add magnitude line graph to plot ==============
        ArrayList<Double> mags = counter.calculate3dMagnitudes(fileData.xAcc, fileData.yAcc, fileData.zAcc);

        for (int i = 0; i < mags.size(); i++) {
            plt.plot(0, i, mags.get(i)).strokeColor("red").strokeWeight(2).style("-");
        }

        // ======= add step predictions to plot ===========
        ArrayList<Integer> stepIndexes = counter.getStepIndexes(fileData);
        ArrayList<Double> stepMags = StepCounter.extractValues(stepIndexes, mags);

        for (int i = 0; i < stepIndexes.size(); i++) {
            plt.plot(1, stepIndexes.get(i), stepMags.get(i)).strokeColor("blue").strokeWeight(5).style(".");
        }

        // ======== display the plot ==============
        PlotWindow window = PlotWindow.getWindowFor(plt, 1200,800);
        window.show();
    }
}

