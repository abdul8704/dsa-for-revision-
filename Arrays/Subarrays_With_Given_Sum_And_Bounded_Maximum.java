// question link = https://www.hackerrank.com/contests/software-engineer-prep-kit/challenges/subarrays-given-sum-bounded-maximum/problem?isFullScreen=true

// Given an integer array nums and integers k and M, count the number of contiguous subarrays whose sum equals k and whose maximum element is at most M.

// Example

// Input
// nums = [2, -1, 2, 1, -2, 3]
// k = 3
// M = 2

// Output
// 2

// Explanation
// We need subarrays with sum = 3 and all elements ≤ 2. 
// Also, any subarray containing 3 is invalid because 3 > M. Check all starts:

// - From index 0: [2, -1, 2] → sum = 3, max = 2 → valid (count = 1).
// - From index 2: [2, 1] → sum = 3, max = 2 → valid (count = 2). No other subarray qualifies. Thus the total count is 2.


// Input Format
// The first line contains an integer n denoting the number of elements in nums.
// The next n lines contains an integer denoting elements in nums followed by the value of k & M.

// Example
// 6 → number of elements in nums
// 2 → elements of nums
// -1
// 2
// 1
// -2
// 3
// 3 → value of k
// 2 → value of M

// Constraints
// 0 <= nums.length <= 1000000
// -10^9 <= nums[i] <= 10^9 for all 0 <= i < nums.length
// -10^15 <= k <= 10^15
// -10^9 <= M <= 10^9

// Output Format
// Returns a non-negative integer denoting the count of all contiguous subarrays of nums.

// Sample Input 0
// 0
// 0
// 0

// Sample Output 0
// 0

// Sample Input 1
// 1
// 5
// 5
// 5
// Sample Output 1
// 1


import java.io.*;
import java.util.*;
import java.util.stream.*;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'countSubarraysWithSumAndMaxAtMost' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. LONG_INTEGER k
     *  3. LONG_INTEGER M
     */

    public static long countSubarraysWithSumAndMaxAtMost(List<Integer> nums, long k, long M) {
        long count = 0;
        HashMap<Long, List<Integer>> map = new HashMap<>();
        List<Integer> greater = new ArrayList<>();
        
        long sum = 0;
        int max = Integer.MIN_VALUE;
        int N = nums.size();
        
        
        for(int right=0; right<N; right++){
            if(nums.get(right) > M)
                greater.add(right);
            max = Math.max(max, nums.get(right));
            sum += nums.get(right);
            
            if(sum == k){
                
                int pos = lowerBound(greater, 0);
                    if (pos == greater.size() || (pos > 0 && greater.get(pos) > right)) {
                        count++;
                    }
            }
            
            if(map.containsKey(sum - k)){
                
                int r = lowerBound(greater, right);
                
                for(Integer left: map.get(sum - k)){
                    int pos = lowerBound(greater, left + 1);
                        if (pos == greater.size() || (pos > 0 && greater.get(pos) > right)) {
                            count++;
                        }
                }
            }
            
            if(map.containsKey(sum)){
                map.get(sum).add(right);
            }
            else {
                List<Integer> l = new ArrayList<>();
                l.add(right);
                map.put(sum, l);    
            }   
        }
        
        
        return count;
    }
    private static int lowerBound(List<Integer> arr, int target){
        int N = arr.size();
        int l = 0, r = N-1;
        
        while (l <= r){
            int m = (r - l) / 2 + l;
            
            if(arr.get(m) >= target)
                r = m-1;
            else    
                l = m+1;    
        }
        
        return l == arr.size() ? l-1: l ;
    }
    private static int upperBound(List<Integer> arr, int target){
        int N = arr.size();
        int l = 0, r = N-1;
        
        while (l <= r){
            int m = (r - l) / 2 + l;
            
            if(arr.get(m) < target)
                r = m-1;
            else    
                l = m+1;    
        }
        
        return r == -1 ? 0: r;
    }

}

public class Subarrays_With_Given_Sum_And_Bounded_Maximum {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int numsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> nums = IntStream.range(0, numsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        long k = Long.parseLong(bufferedReader.readLine().trim());

        long M = Long.parseLong(bufferedReader.readLine().trim());

        long result = Result.countSubarraysWithSumAndMaxAtMost(nums, k, M);

        System.out.println(result);

        bufferedReader.close();
    }
}

class Result2 {

    /*
     * Complete the 'countSubarraysWithSumAndMaxAtMost' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY nums
     *  2. LONG_INTEGER k
     *  3. LONG_INTEGER M
     */

    public static long countSubarraysWithSumAndMaxAtMost(List<Integer> nums, long k, long M) {
        long count = 0;
        HashMap<Long, List<Integer>> map = new HashMap<>();
        List<Integer> greater = new ArrayList<>();
        
        long sum = 0;
        int N = nums.size();
        
        
        for(int right=0; right<N; right++){
            if(nums.get(right) > M)
                greater.add(right);
                
            sum += nums.get(right);
            
            if(sum == k  && isValid(greater, 0, right)){
                count++;
            }
            
            if(map.containsKey(sum - k)){
                for(Integer left: map.get(sum - k))
                    if(isValid(greater, left+1, right))
                        count++;
            }
            
            if(map.containsKey(sum)){
                map.get(sum).add(right);
            }
            else {
                List<Integer> l = new ArrayList<>();
                l.add(right);
                map.put(sum, l);    
            }   
        }
        
        
        return count;
    }
    private static boolean isValid(List<Integer> arr, int left, int right){
        if(arr.size() == 0)
            return true;
        
        int l = lowerBound(arr, left);
        int r = upperBound(arr, right);
        // System.out.println(arr + " " + left + " " + right + " "+ " lower " + l + " upper " + r + " res " + (r - l + 1 <= 0));
        return r - l + 1 <= 0;
    }
    private static int lowerBound(List<Integer> arr, int target){
        int N = arr.size();
        int l = 0, r = N-1;
        
        while (l <= r){
            int m = (r - l) / 2 + l;
            
            if(arr.get(m) >= target)
                r = m-1;
            else    
                l = m+1;    
        }
        
        return l ;
    }
    private static int upperBound(List<Integer> arr, int target){
        int N = arr.size();
        int l = 0, r = N-1;
        
        while (l <= r){
            int m = (r - l) / 2 + l;
            
            if(arr.get(m) > target)
                r = m-1;
            else    
                l = m+1;    
        }
        
        return r;
    }

}

class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int numsCount = Integer.parseInt(bufferedReader.readLine().trim());

        List<Integer> nums = IntStream.range(0, numsCount).mapToObj(i -> {
            try {
                return bufferedReader.readLine().replaceAll("\\s+$", "");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        })
            .map(String::trim)
            .map(Integer::parseInt)
            .collect(toList());

        long k = Long.parseLong(bufferedReader.readLine().trim());

        long M = Long.parseLong(bufferedReader.readLine().trim());

        long result = Result.countSubarraysWithSumAndMaxAtMost(nums, k, M);

        System.out.println(result);

        bufferedReader.close();
    }
}
