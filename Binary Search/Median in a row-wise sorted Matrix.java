// Given a row-wise sorted matrix where the number of rows and columns is always odd, find the median of the matrix.

// Examples:

// Input: mat = [[1, 3, 5], [2, 6, 9], [3, 6, 9]]
// Output: 5
// Explanation: Sorting matrix elements gives us {1,2,3,3,5,6,6,9,9}. Hence, 5 is median. 

// Input: mat = [[1], [2], [3]]
// Output: 2
// Explanation: Sorting matrix elements gives us {1,2,3}. Hence, 2 is median

// Input: mat = [[3], [5], [8]]
// Output: 5
// Explanation: Sorting matrix elements gives us {3,5,8}. Hence, 5 is median.

// Constraints:
// 1 <= mat.size(), mat[0].size() <= 400
// 1 <= mat[i][j] <= 2000

class Solution {
    int median(int mat[][]) {
        int left = 2000, right = -1;
        int R = mat.length, C= mat[0].length;
        
        for(int i=0; i<R; i++){         // set the lower and upper bound for BS
            left = Math.min(left, mat[i][0]);
            right = Math.max(right, mat[i][C-1]);
        }
        
        while(left <= right){
            int mid = (left + right)/2;
            int elements = numLessOrEqual(mat, R, C, mid);
            
            if(elements <= (R*C)/2)      // find smallest int than satisfies this condition
                left = mid + 1;
            else
                right = mid - 1;
        }
        
        return left;
    }
    private static int numLessOrEqual(int[][] mat, int R, int C, int x){
        int count = 0;
        
        for(int i=0; i<R; i++){
            int lo = 0, hi = C-1;
            
            while(lo <= hi){
                int mid = (lo + hi)/2;
                
                if(mat[i][mid] <= x)
                    lo = mid + 1;
                else 
                    hi = mid - 1;
            }
            
            count += lo;
        }
        
        return count;
        
    }
}