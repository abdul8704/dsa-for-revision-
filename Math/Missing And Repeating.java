// Given an unsorted array arr of positive integers. One number a from the set [1, 2,....,n] is missing and one number b occurs twice in the array. Find numbers a and b.

// Note: The test cases are generated such that there always exists one missing and one repeating number within the range [1,n].

// Examples:
// Input: arr[] = [2, 2]
// Output: [2, 1]
// Explanation: Repeating number is 2 and smallest positive missing number is 1.

// Input: arr[] = [1, 3, 3] 
// Output: [3, 2]
// Explanation: Repeating number is 3 and smallest positive missing number is 2.

// Input: arr[] = [4, 3, 6, 2, 1, 1]
// Output: [1, 5]
// Explanation: Repeating number is 1 and the missing number is 5.

// Constraints:
// 2 ≤ arr.size() ≤ 106
// 1 ≤ arr[i] ≤ arr.size()

import java.util.ArrayList;

class Solution {
    // Function to find two elements in array
    ArrayList<Integer> findTwoElement(int arr[]) {
        long N = arr.length;
        long orginal_sum = 0, expected_sum = (long)(N * (N+1))/2;
        long orignal_sq_sum = 0, expected_sq_sum = (long)( N * (N+1) * (2*N+1)) / 6;
        
        for(int num: arr){
            orginal_sum += (long)num;
            orignal_sq_sum += ((long)num * (long)num);
        }
        
        long diff = expected_sum - orginal_sum;
        long diff_sq = expected_sq_sum - orignal_sq_sum;
        
        long missing = (diff_sq - (diff * diff)) / (2 * diff);
        long repeating = missing + diff;
        
        ArrayList<Integer> res = new ArrayList<>();
        res.add((int)missing); res.add((int)repeating);
        
        return res;
    }
}