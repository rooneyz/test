import java.util.Random;

/**
 * Created by zachrooney on 3/20/17.
 */
public class RandomArray {
   // private int size;


    public class ramdomArray {
    }

    public Integer [] getRandomArray(int size) {

        int n = size;
        // Try block: Most stream operations may throw IO exception
        Random random = new Random();
        Integer [] returnArray = new Integer[size];
        for (int i = 0; i < size; i++) {
            // Randomize an integer and write it to the output file
            returnArray[i] = random.nextInt(1000000);

        }

        return returnArray;
    }


}
