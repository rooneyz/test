/**
 * This class is designed to use the quickSort algorithm from the FHSort class
 * Created by zachrooney on 3/20/17.
 */
public class QuickSort {

    protected static int QS_RECURSION_LIMIT = 2;
    Integer [] arrayToSort;

    // quicksort and helpers -------------------------------------------
    // median3 sorts a[left], a[center] and a[right].
    // it leaves the smallest in a[left], the largest in a[right]
    // and median (the pivot) is moved "out-of-the-way" in a[right-1].
    // (a[center] has what used to be in a[right-1])
    protected static < E extends Comparable< ? super E > >
    E median3(E[] a, int left, int right)
    {
        int center;
        E tmp;

        // swaps are done in-line for speed;  each compound line is a swap
        center = (left + right) / 2;
        if(a[center].compareTo(a[left]) < 0)
        { tmp = a[center]; a[center] = a[left]; a[left] = tmp; }
        if(a[right].compareTo(a[left]) < 0)
        { tmp = a[right]; a[right] = a[left]; a[left] = tmp; }
        if(a[right].compareTo(a[center]) < 0)
        { tmp = a[right]; a[right] = a[center]; a[center] = tmp; }

        tmp = a[center]; a[center] = a[right-1]; a[right-1] = tmp;

        return a[right - 1];
    }

    protected void setArrayToSort (Integer [] array){
        arrayToSort = array;
    }

    public static boolean setRecursionLimit(int newLim)
    {
        if (newLim < 2 || newLim > 400)
            return false;
        QS_RECURSION_LIMIT = newLim;
       // System.out.println(QS_RECURSION_LIMIT);    //Debugging
        return true;
    }

    protected static < E extends Comparable< ? super E > >
    void quickSort(E[] a, int left, int right)
    {
        E pivot, tmp;
        int i, j;

        if( left + QS_RECURSION_LIMIT <= right )
        {
            pivot = median3(a, left, right);
            for(i = left, j = right - 1; ; )
            {
                while( a[++i].compareTo(pivot) < 0 )
                    ;
                while( pivot.compareTo(a[--j]) < 0)
                    ;
                if(i < j)
                { tmp = a[i]; a[i] = a[j]; a[j] = tmp; }
                else
                    break;
            }

            // restore pivot
            tmp = a[i]; a[i] = a[right - 1]; a[right - 1] = tmp;

            // recursive calls on smaller sub-groups
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        }
        else
            // non-recursive escape valve - insertion sort
            insertionSort(a, left, right);
    }

    // private insertion sort that works on sub-arrays --------------
    protected static < E extends Comparable< ? super E > >
    void insertionSort(E[] a, int start, int stop)
    {
//        System.out.println("INSERTION SORT CALL");   //Debug
//        System.out.println("Size: " + (stop - start));   //Debug
        int k, pos;
        E temp;

        // we are not testing for ranges to keep times down - private so ok
        for(pos = start + 1; pos <= stop; pos++ )
        {
            temp = a[pos];
            for(k = pos; k > 0 && temp.compareTo(a[k-1]) < 0; k-- )
                a[k] = a[k-1];
            a[k] = temp;
        }
    }

    // public quicksort
    public static < E extends Comparable< ? super E > >
    void quickSort( E[] a )
    {
        quickSort(a, 0, a.length - 1);
    }
}
