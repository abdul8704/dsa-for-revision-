// Given a rod of length n inches and an array price[], where price[i] denotes the value of a piece of length i. Your task is to determine the maximum value obtainable by cutting up the rod and selling the pieces.
// Note: n = size of price, and price[] is 1-indexed array.

// Example:
// Input: price[] = [1, 5, 8, 9, 10, 17, 17, 20]
// Output: 22
// Explanation: The maximum obtainable value is 22 by cutting in two pieces of lengths 2 and 6, i.e., 5 + 17 = 22.

// Input: price[] = [3, 5, 8, 9, 10, 17, 17, 20]
// Output: 24
// Explanation: The maximum obtainable value is 24 by cutting the rod into 8 pieces of length 1, i.e, 8*price[1] = 8*3 = 24.

// Input: price[] = [3]
// Output: 3
// Explanation: There is only 1 way to pick a piece of length 1.

// Constraints:
// 1 ≤ price.size() ≤ 103
// 1 ≤ price[i] ≤ 106

class Solution {
    public int cutRod(int[] price) {
        // code here
        int N = price.length;
        
        int[][] dp = new int[N][N+1];
        
        for(int i=0; i<N; i++){
            for(int len = 1; len <= N; len++){
                int t = (len - (i+1) >= 0) ? dp[i][len-(i+1)] + price[i]: 0;
                int nt = (i > 0) ? dp[i-1][len]: 0;
                
                dp[i][len] = Math.max(t, nt);
            }
        }
        
        return dp[N-1][N];
    }
}

class Solution { // optimised space
    public int cutRod(int[] price) {
        int N = price.length;
        
        int[] dp = new int[N+1];
        
        for(int i=0; i<N; i++){
            for(int len = 1; len <= N; len++){
                int t = (len - (i+1) >= 0) ? dp[len-(i+1)] + price[i]: 0;
                int nt =  dp[len];
                
                dp[len] = Math.max(t, nt);
            }
        }

        return dp[N];
    }
}