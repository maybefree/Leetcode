package sort;

import java.util.Arrays;

public class MergeSort {


    public static void main(String[] args) {
        int[] A = {3, 2, 1};
        sortIntegers(A);

        System.out.println(Arrays.toString(A));
    }
    /**
     * @param A: an integer array
     * @return: nothing
     */
    public static void sortIntegers(int[] A) {
        // write your code here
        int[] temp = new int [A.length];
        mergeSort(A,0,A.length-1,temp);
    }

    public static void mergeSort(int[] A,int start,int end , int[] temp){

        if(start>=end){
            return ;
        }

        int mid = (start+end)/2;

        mergeSort(A,start,mid,temp);
        mergeSort(A,mid+1,end,temp);

        merge(A,start,mid,end,temp);

    }

    public static void merge(int[] A,int start,int mid,int end , int[] temp) {
        // write your code here
        //A=>[start,mid]
        //B=>[mid+1,end]
        //C=>temp[start,mid]
        int left = start;
        int right = mid+1;
        int index = start;

        while(left<=mid&&right<=end){
            if(A[left]<=A[right]){
                temp[index]=A[left];
                left++;
            }
            else{
                temp[index]=A[right];
                right++;
            }
            index++;
        }

        while(left<=mid){
            temp[index] = A[left];
            left++;
            index++;
        }

        while(right<=end){
            temp[index] = A[right];
            right++;
            index++;
        }

        for(int i=start; i<=end; i++){
            A[i] = temp[i];
        }

    }
}
