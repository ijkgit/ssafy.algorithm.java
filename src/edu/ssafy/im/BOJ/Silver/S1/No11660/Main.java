package edu.ssafy.im.BOJ.Silver.S1.No11660;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][n];
        for (int i = 0; i < arr.length; i++) {
            input = br.readLine();
            st = new StringTokenizer(input);
            for (int j = 0; j < arr.length; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] sum = new int[n][n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum[i][j + 1] = sum[i][j] + arr[i][j];
            }
        }

        for (int i = 0; i < m; i++) {
            input = br.readLine();
            st = new StringTokenizer(input);
            int x1 = Integer.parseInt(st.nextToken()) - 1;
            int y1 = Integer.parseInt(st.nextToken()) - 1;
            int x2 = Integer.parseInt(st.nextToken()) - 1;
            int y2 = Integer.parseInt(st.nextToken());
            int ans = 0;
            for (int j = x1; j <= x2; j++) {
                ans += sum[j][y2] - sum[j][y1];
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}
