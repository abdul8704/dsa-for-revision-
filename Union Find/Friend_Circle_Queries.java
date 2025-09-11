// https://www.hackerrank.com/challenges/friend-circle-queries/problem 

import java.io.*;
import java.util.*;

public class Friend_Circle_Queries {
    private static HashMap<Integer, Integer> circle = new HashMap<>();
    private static HashMap<Integer, Integer> map = new HashMap<>();
    private static int max = 0;

    static int[] maxCircle(int[][] queries) {
        int[] res = new int[queries.length];
        int ptr = 0;       
        
        for(int[] query: queries){
            if(!map.containsKey(query[0])){
                map.put(query[0], query[0]);
                circle.put(query[0], 1);
                max = Math.max(max, 1);
            }
            
            if(!map.containsKey(query[1])){
                map.put(query[1], query[1]);
                circle.put(query[1], 1);
                max = Math.max(max, 1);
            }
            
            union(query[0], query[1]);
            res[ptr++] = max;
        }
        
        return res;
    }
    private static int find(int node){
        if(map.get(node) == node)
          return node;
          
        int lead = find(map.get(node));
        map.put(node, lead);
        
        return lead;
    }
    private static void union(int node, int leader){
        int lead1 = find(node);
        int lead2 = find(leader);
        
        if(lead1 != lead2){
            int size1 = circle.get(lead1);
            int size2 = circle.get(lead2);
            
            if(size1 > size2){
                map.put(lead2, lead1);
                circle.put(lead1, size1 + size2);
            }
            else{
                map.put(lead1, lead2);
                circle.put(lead2, size1 + size2);
            }
            max = Math.max(max, size1 + size2);
        }
        
        return;
    }
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

        for (int i = 0; i < ans.length; i++) {
            bufferedWriter.write(String.valueOf(ans[i]));

            if (i != ans.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
