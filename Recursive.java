import java.util.Random;
import java.util.Arrays;

public class Recursive {
    public static void main(String args[]){
        int[] randArray = getRandomArray();
        printArr("selective sort",
                Arrays.toString(randArray),
                Arrays.toString(selectiveSort(randArray,0))
                );
        randArray = getRandomArray();
        printArr("insertion sort",
                Arrays.toString(randArray),
                Arrays.toString(insertionSort(randArray))
                );
        randArray = getRandomArray();
        printArr("shell sort",
                Arrays.toString(randArray),
                Arrays.toString(shellSort(randArray))
                );
        randArray = getRandomArray();
        printArr("merge sort",
                Arrays.toString(randArray),
                Arrays.toString(mergeSort(randArray))
                );
        randArray = getRandomArray();
        printArr("quick sort",
                Arrays.toString(randArray),
                Arrays.toString(quickSort(randArray))
                );
        randArray = getRandomArray();
        printArr("radix sort",
                Arrays.toString(randArray),
                Arrays.toString(radixSort(randArray))
                );
    }

    public static int[] selectiveSort(int[] arr, int curr) {
        for(int compare = curr+1; compare < arr.length; compare++){
            if(arr[compare] < arr[curr]){
                swap(arr,compare,curr);
            }
        }
        if(curr < arr.length-1) return selectiveSort(arr,curr+1);
        return arr;
    }
    public static int[] insertionSort(int[] arr){
        return insertionSort(arr,0,arr.length);
    }
    public static int[] insertionSort(int[] arr, int start, int end){
        int selected = arr[start];
        for(int j = start-1; j >=0; j--){
            int test = arr[j];
            if (test > selected) {
                arr[j+1] = test;
                arr[j] = selected;
            }
            else {
                arr[j+1] = selected;
                break;
            }
        }
        if(start < end - 1) return insertionSort(arr,start +1, end);
        return arr;
    }

    public static int[] shellSort(int[] arr) {
        int startIncrement = 1;
        while((2*startIncrement)+1 < arr.length) {
            startIncrement *= 2;
            startIncrement += 1;
        }
        return shellSort(arr, startIncrement);
    }
    public static int[] shellSort(int[] arr, int gap) {
        if(gap == 1) return arr; //  insertionSort(arr);
        for(int i = gap; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while(j >= gap && arr[j - gap] > temp) {
                arr[j] = arr[j - gap];
                j -= gap;
            }
            arr[j] = temp;
        }
        return shellSort(arr, (gap-1)/2);
    }

    public static int[] mergeSort(int[] arr) {
        int[] temp = new int[10];
        return mergeSort(arr, temp, 0, arr.length-1);
    }
    public static int[] mergeSort(int[] arr, int[] temp, int start, int end) {
        if (start < end) {
            int mid = start + (end-start)/2;

            mergeSort(arr, temp, start, mid);
            mergeSort(arr, temp, mid+1, end);
            merge(arr, temp, start, mid, end);
        }
        return arr;
    }
    public static int[] merge(int[] arr, int[] temp, int start, int mid, int end) {
        for(int i = start; i <= end; i++) {
            temp[i] = arr[i];
        }
        int i = start;
        int j = mid+1;
        int pos = start;
        while(i <= mid && j <= end) {
            if(temp[i] < temp[j]) {
                arr[pos] = temp[i];
                i++;
            } else {
                arr[pos] = temp[j];
                j++;
            }
            pos++;
        }
        while(i <= mid) {
            arr[pos] = temp[i];
            i++;
            pos++;
        }
        return temp;
    }

    public static int[] quickSort(int[] arr) {
        return quickSort(arr, 0, arr.length-1);
    }
    public static int[] quickSort(int[] arr, int start, int end) {
        int left = start, right = end;
        int pivot = arr[start + (end-start)/2];

        while ( left <= right) {
            while(arr[left] < pivot) {
                left++;
            }
            while(arr[right] > pivot) {
                right--;
            }
            if (left <= right) {
                swap(arr,left,right);
                left++;
                right--;
            }
        }
        if(start < right) quickSort(arr, start, right);
        if(left < end) quickSort(arr, left, end);
        return arr;
    } 
    public static int[] radixSort(int[] arr) { return radixSort(arr,1); }
    public static int[] radixSort(int[] arr, int place) {
        // create 2d array buckets
        int[][] buckets = new int[arr.length][arr.length];
        // create array bucketSizes to keep track of the top of the bucket
        int[] bucketSizes = new int[arr.length];
        for(int i = 0; i<arr.length; i++) {
            int destIndex = (arr[i]/place)%10;
            buckets[destIndex][bucketSizes[destIndex]] = arr[i];
            bucketSizes[destIndex] = bucketSizes[destIndex] + 1;
        }
        int count = 0;
        for(int x=0; x<arr.length; x++) {
            for(int y = 0; y < bucketSizes[x]; y++) {
                int value = buckets[x][y];
                buckets[x][y] = 0;
                arr[count] = value;
                count++;
            }
            bucketSizes[x] = 0;
        }
        if(place < 1000) return radixSort(arr, place*10);
        return arr;
    }
    public static void swap(int[] arr, int fromIndex, int toIndex) {
        int temp = arr[fromIndex];
        arr[fromIndex] = arr[toIndex];
        arr[toIndex] = temp;
    }

    public static void printArr(String name, String unsorted, String sorted) {
        System.out.println("-------------------------------");
        System.out.println("method: "+name);
        System.out.println("input:  "+unsorted);
        System.out.println("output: "+sorted);
    }

    public static int[] getRandomArray() {
        int[] arr = new int[10];
        Random rng = new Random();

        for(int i = 0; i< arr.length; i++) {
            arr[i] = rng.nextInt(1000);
        }
        return arr;
    }
}

