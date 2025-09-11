// https://codeforces.com/problemset/problem/2134/C

import java.util.Scanner;

public class Div2_2134C_Even_Larger {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        while(T-- > 0){
            int N = sc.nextInt();
            int[] arr = new int[N];

            for(int i=0; i<N; i++){
                arr[i] = sc.nextInt();
            }

            long ops = 0;

            for(int i=1; i<N-1; i += 2){
                if(arr[i] < arr[i-1]){
                    int diff = arr[i-1] - arr[i];
                    arr[i-1] -= diff;
                    ops += diff;
                }
                if(arr[i-1] + arr[i+1] > arr[i]){
                    int op = (arr[i-1] + arr[i+1]) - arr[i];
                    ops += op;
                    arr[i+1] -= op;
                }

                // System.out.println(arr[i-1] + " " + arr[i] + " " + arr[i+1]);
            }

            if(N % 2 == 0 && arr[N-1] < arr[N-2])
                ops += arr[N-2] - arr[N-1];

            // System.out.println("arr " + Arrays.toString(arr));

            System.out.println(ops);
        }
    }
}
