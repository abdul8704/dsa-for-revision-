// Consider a rat placed at position (0, 0) in an n x n square matrix mat[][]. The rat's goal is to reach the destination at position (n-1, n-1). The rat can move in four possible directions: 'U'(up), 'D'(down), 'L' (left), 'R' (right).
// The matrix contains only two possible values:
// 0: A blocked cell through which the rat cannot travel.
// 1: A free cell that the rat can pass through.
// Your task is to find all possible paths the rat can take to reach the destination, starting from (0, 0) and ending at (n-1, n-1), under the condition that the rat cannot revisit any cell along the same path. Furthermore, the rat can only move to adjacent cells that are within the bounds of the matrix and not blocked.
// If no path exists, return an empty list.
// Note: Return the final result vector in lexicographically smallest order.

// Examples:
// Input: mat[][] = [[1, 0, 0, 0], [1, 1, 0, 1], [1, 1, 0, 0], [0, 1, 1, 1]]
// Output: ["DDRDRR", "DRDDRR"]
// Explanation: The rat can reach the destination at (3, 3) from (0, 0) by two paths - DRDDRR and DDRDRR, when printed in sorted order we get DDRDRR DRDDRR.

// Input: mat[][] = [[1, 0], [1, 0]]
// Output: []
// Explanation: No path exists as the destination cell is blocked.

// Input: mat = [[1, 1, 1], [1, 0, 1], [1, 1, 1]]
// Output: ["DDRR", "RRDD"]
// Explanation: The rat has two possible paths to reach the destination: 1. "DDRR" 2. "RRDD", These are returned in lexicographically sorted order.

// Constraints:
// 2 ≤ mat.size() ≤ 5
// 0 ≤ mat[i][j] ≤ 1

class Solution {
    // Function to find all possible paths
    private static int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}};
    
    private static char[] moves = {'D', 'L', 'R', 'U'};
    
    private static boolean inBounds(int i, int j, int R, int C){
        return (i >= 0 && i < R && j >= 0 && j < C);
    }
    
    public ArrayList<String> ratInMaze(int[][] maze) {
        int R = maze.length, C = maze[0].length;
        ArrayList<String> res = new ArrayList<>();
        
        if(maze[0][0] == 0 || maze[R-1][C-1] == 0)
            return res;
        
        
        dfs(maze, R, C, 0, 0, new StringBuilder(), res);    
        
        // Collections.sort(res);        
        return res;
    }
    private static void dfs(int[][] maze, int R, int C, int row, int col, StringBuilder str, ArrayList<String> res){
        if(row == R-1 && col == C-1){
            res.add(str.toString());
            return;
        }
        
        maze[row][col] = 2; //visited
        
        for(int i=0; i<dirs.length; i++){
            int nextR = row + dirs[i][0];
            int nextC = col + dirs[i][1];
            
            if(!inBounds(nextR, nextC, R, C))
                continue;
            
            if(maze[nextR][nextC] != 1)
                continue;
            
            str.append(moves[i]);
            dfs(maze, R, C, nextR, nextC, str, res);
            
            str.deleteCharAt(str.length() - 1);
            maze[nextR][nextC] = 1;
        }
    }
}