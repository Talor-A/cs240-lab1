import java.util.Random;
import java.util.Arrays;

public class Iterative {
    public static void main(String args[]){
        int size = 100;
        selectiveSort(getRandomArray(size));
        insertionSort(getRandomArray(size));
        shellSort(getRandomArray(size));
        radixSort(getRandomArray(size));
    }

    public static int[] selectiveSort(int[] arr){
        printStart("selective sort", arr);
        int compareCount = 0;
        int moveCount = 0;
        for(int curr = 0; curr < arr.length - 1; curr++){
            for(int compare = curr+1; compare < arr.length; compare++){
                compareCount++;
                if(arr[compare] < arr[curr]){
                    swap(arr,compare,curr);
                    moveCount+=2;
                }
            }
        }
        printResult(arr, compareCount, moveCount);
        return arr;
    }
    public static int[] insertionSort(int[] arr){
        printStart("insertion sort", arr);

        int compareCount = 0;
        int moveCount = 0;
        for(int i = 1; i < arr.length; i++){
            int selected = arr[i];
            moveCount++;
            for(int j = i-1; j >=0; j--){
                int test = arr[j];
                moveCount++;
                compareCount++;
                if (test > selected) {
                    arr[j+1] = test;
                    arr[j] = selected;
                    moveCount+=2;
                }
                else {
                    arr[j+1] = selected;
                    moveCount++;
                    break;
                }
            }
        }
        printResult(arr, compareCount, moveCount);
        return arr;
    }

    public static int[] shellSort(int[] arr) {
        printStart("shell sort", arr);

        int compareCount = 0;
        int moveCount = 0;
        int gap = 1;
        while((2*gap)+1 < arr.length) {
            gap *= 2;
            gap += 1;
        } 
        while (gap >= 1) {
            for(int i = gap; i < arr.length; i++) {
                int temp = arr[i];
                compareCount++;
                int j = i;
                while(j >= gap && arr[j - gap] > temp) {
                    compareCount++;
                    moveCount++;
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
                moveCount++;
            }
            gap -= 1;
            gap /= 2;
        }
        printResult(arr, compareCount, moveCount);
        return arr;
    }


    public static int[] radixSort(int[] arr) {
        printStart("radix sort", arr);

        int compareCount = 0;
        int moveCount = 0;
        int[][] buckets = new int[arr.length][arr.length];
        int[] bucketSizes = new int[arr.length];
        for(int place = 1; place <1000; place*=10){
            for(int i = 0; i<arr.length; i++) {
                int destIndex = (arr[i]/place)%10;
                buckets[destIndex][bucketSizes[destIndex]] = arr[i];
                bucketSizes[destIndex] = bucketSizes[destIndex] + 1;
                moveCount++;
                compareCount++;
            }
            int count = 0;
            for(int x=0; x<arr.length; x++) {
                for(int y = 0; y < bucketSizes[x]; y++) {
                    int value = buckets[x][y];
                    buckets[x][y] = 0;
                    arr[count] = value;
                    moveCount++;
                    count++;
                }
                bucketSizes[x] = 0;
            }
        }
        printResult(arr, compareCount, moveCount);
        return arr;
    }
    public static void swap(int[] arr, int fromIndex, int toIndex) {
        int temp = arr[fromIndex];
        arr[fromIndex] = arr[toIndex];
        arr[toIndex] = temp;
    }

    public static void printStart(String name, int[] unsorted) {
        System.out.println("-------------------------------");
        System.out.println("method: "+name);
        // System.out.println("input:  "+Arrays.toString(unsorted));
    }
    public static void printResult(int[] sorted, int compareCount, int moveCount) {
        // System.out.println("output: "+Arrays.toString(sorted));
        System.out.println("comp count: "+ compareCount);
        System.out.println("move count: "+ moveCount);
    }

    public static int[] getRandomArray(int size) {
        int[] arr = new int[size];
        Random rng = new Random();

        for(int i = 0; i< arr.length; i++) {
            arr[i] = rng.nextInt(1000);
        }
        return arr;
    }
}

