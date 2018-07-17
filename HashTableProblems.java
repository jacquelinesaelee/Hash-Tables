// Jacqueline Saelee
// COMP 282 
// Project 5 - Hash Tables
// April 24, 2018


package hashtableproblems;

import java.util.*;

class Tuple{
    int x, y;
    
    public Tuple(int a, int b){
        x = a;
        y = b;
    }
    
    public String toString(){
        return "(" + x + ", " + y + ")";
    }
}

public class HashTableProblems {

    public static void main(String[] args) {
        
        // Problem 1
        int []arr = randomIntArray(15, 30, true);
        
        System.out.println("Problem #1 - Random array of ints");
        System.out.println("Array = " + Arrays.toString(arr));
            
        // Problem 2
        ArrayList<Integer> arrList;
     
        int[] list1 = {3, 91, 83, 13, 35, 99, 59, 81, 28, 96, 56, 11, 55, 38, 26, 29, 80, 43, 62, 30, 30};
        int[] list2 = {19, 83, 25, 47, 9,30, 30, 91, 57, 39, 70, 43, 90, 23, 73, 5};
        System.out.println("\n- - - - \nProblem #2 - Find common integers from two lists");
        System.out.println("List1 = " + Arrays.toString(list1));
        System.out.println("List2 = " + Arrays.toString(list2));
        
        arrList = numbersInCommon(list1, list2);
        System.out.println("Integers in Common: " + arrList);
        
        // Problem 3
        int[] list3 = {11, 28, 67, 16, 47, 19, 6, 35, 74, 72, 36, 59, 70, 32, 60, 89, 95, 52, 45, 22, 8, 88, 50, 39, };
        int d = 75;
        System.out.println("\n- - - - - \nProblem #3 - PairSum");
        System.out.println("d = " + d);
        System.out.println("List = " + Arrays.toString(list3));
        ArrayList<Tuple> arrList2 = pairSum(list3, d);
        System.out.println("Result = " + arrList2 + "\n");
    }
    
    // - - - - Problem 1 - - - - 
    public static int[] randomIntArray(int n, int limit, boolean nodups){
        
        // Initialize
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        int[] ret = new int[n + 1];
        Random rand = new Random();
        int num;
 
        // Insert RANDOM numbers into HashTable
        for(int i = 1; i <= n; i++){
            num = rand.nextInt(limit) + 1;
            
            // IF noDups == TRUE && there is a duplicate
            if(nodups == true && table.containsKey(num)){
                while(table.containsKey(num)){
                    num = rand.nextInt(limit) + 1;
                }
            }
           
            // Insert num @ position "i" into BOTH array and hashTable
            ret[i] = num;
            table.put(num, i);
        }
        
        return ret;
    }
    
    
    // - - - - Problem 2 - - - -
    public static ArrayList<Integer> numbersInCommon(int[] list1, int[] list2){
        
        // Initialize VARS
        ArrayList<Integer> ret = new ArrayList<Integer>();
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        int[] shortList;
        int[] longList;
        int k = 1;
        
        // Initialize Arrays - Set shorter and longer array appropriately
        if(list1.length < list2.length){
          shortList = list1;
          longList = list2;
        }
        else{
          shortList = list2;
          longList = list1;
        }
        
        // Insert Long List into HashTable
        for(int i = 0; i < longList.length; i++){
            // Insert while not a duplicate
            if(!(table.containsKey(longList[i]))){
                table.put(longList[i], k);
                k++;
            }
            
        }
        
        //Insert what shortList has into ArrayList
        for(int i = 0; i < shortList.length; i++){
            if(table.containsKey(shortList[i]) && (isDuplicate(shortList, i) == false))
                ret.add(shortList[i]);
        }
        
      return ret;
    }
    
    public static boolean isDuplicate(int[] arr, int currIndex){
        int currNum = arr[currIndex];
        
        for(int i = 0; i < currIndex; i++){
            if(arr[i] == currNum){
                return true;
            }
        }
        
        return false;
    }
    
    public static void removeDupes(int arr[], int dupe){
        
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == dupe)
                arr[i] = -1;
        }
        
    }
    
    
    // - - - - Problem 3 - - - - 
    public static ArrayList<Tuple> pairSum(int[] nums, int d){
        
        // Initialize
        Hashtable<Integer, Integer> table = new Hashtable<Integer, Integer>();
        ArrayList<Tuple> arrList = new ArrayList<Tuple>();
        int k = 1;
        int partner;
       
        // Enter nums into HashTable
        for(int i = 0; i < nums.length; i++){
            table.put(nums[i], k);
            k++;
        }
        
        // LOOP through array
        for(int i = 0; i < nums.length ; i++){
            partner = d - nums[i];
            if(table.containsKey(partner) && (isDuplicate(nums, i) == false)){
                arrList.add(new Tuple(nums[i], partner));
                table.remove(partner);
                removeDupes(nums, nums[i]);
                removeDupes(nums, partner);
            }
        }
        
        return arrList;
    }
    
    public void printArray(int[] arr){
        
        for(int i = 0; i < arr.length; i++){
            if(arr[i] != -1){
                if(i == arr.length - 1){
                    System.out.print(arr[i]);
                }
                else{
                    System.out.print(arr[i] + ", ");
                }
            }
        }
    }
}
