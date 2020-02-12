import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * This class test the time it takes quickSort to sort arrays with different recursive call limits
 * Created by zachrooney on 3/20/17.
 */
public class RecLimAnalysis {


    public static void main(String[] args) {
        int arraySizeLimit = 10000000;         //Final limit is 100000000
        int arraySize = 2000;
        Integer[] testArray;
        BufferedWriter bw;

        String s = "Output/resultsTest.csv";      // File path

        try {
            bw = new BufferedWriter(new FileWriter(s));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR: Problem creating output file: " + s);
            return;
        }

        RandomArray arrayGenerator = new RandomArray();

        while (arraySize <= arraySizeLimit) {
            try {
                bw.write("Array Size: " + arraySize);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("ERROR: Problem writing to output file ArraySize: " + s);
            }

            for (int i = 2; i < 301; i += 2) {
                testArray = arrayGenerator.getRandomArray(arraySize);
                QuickSort.setRecursionLimit(i);
//
//                System.out.println("**********Array Size: " + arraySize + "*********************");  //Debugging
//                for (Integer e:testArray){
//                    System.out.println(e);
//                }

                long startTime = System.nanoTime();
                QuickSort.quickSort(testArray);
                long endTime = System.nanoTime();

                long duration = (endTime - startTime);

//                System.out.println("**********SORTED*********************");  //Debugging
//                for (Integer e:testArray){
//                    System.out.println(e);
//                }


                try {
                    bw.write(i + "," + duration);
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("ERROR: Problem writing to output file: " + s);
                }
            }

            try {

                bw.flush();

                // write new line to the buffered writer
                bw.newLine();

                // to make it easier to read
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("ERROR: Problem flushing bw : Array size - " + arraySize);
            }
            System.gc();
            System.runFinalization();
            arraySize = arraySize * 5;

        }
    }
}






