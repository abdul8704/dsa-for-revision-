// Given an array arr and target sum k, check whether there exists a subsequence such that the sum of all elements in the subsequence equals the given target sum(k).
// Example:
// Input:  arr = [10,1,2,7,6,1,5], k = 8.
// Output:  Yes
// Explanation:  Subsequences like [2, 6], [1, 7] sum upto 8

// Input:  arr = [2,3,5,7,9], k = 100. 
// Output:  No
// Explanation:  No subsequence can sum upto 100

// Your Task:
// You don't need to read or print anything. Your task is to complete the boolean function checkSubsequenceSum() which takes N, arr and K as input parameter and returns true/false based on the whether any subsequence with sum K could be found.

// Expected Time Complexity: O(N * K).
// Expected Auxiliary Space: O(N * K).

// Constraints:
// 1 <= arr.length <= 2000.
// 1 <= arr[i] <= 1000.
// 1 <= target <= 2000.

 

class Solution {
    public static boolean checkSubsequenceSum(int N, int[] arr, int k) {
        return helper(arr, 0, 0, k, N);
    }
    private static boolean helper(int[] arr, int idx, int sum, int k, int N){
        if(sum == k)
            return true;
        
        if(idx == N || sum > k)
            return false;    
        
        
        boolean case1 = helper(arr, idx+1, sum, k, N);
        if(case1)
            return true;
        
        boolean case2 = helper(arr, idx+1, sum + arr[idx], k, N);
        if(case2)
            return true;
        
        return false;        
    }
}
