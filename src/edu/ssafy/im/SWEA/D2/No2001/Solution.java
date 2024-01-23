package edu.ssafy.im.SWEA.D2.No2001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            String input = br.readLine();
            StringTokenizer st = new StringTokenizer(input);
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[][] graph = new int[n][n];
            for (int r = 0; r < n; r++) {
                input = br.readLine();
                st = new StringTokenizer(input);
                for (int c = 0; c < n; c++) {
                    graph[r][c] = Integer.parseInt(st.nextToken());
                }
            }
            int max = 0;
            for (int r = 0; r < n - m + 1; r++) {
                for (int c = 0; c < n - m + 1; c++) {
                    int sum = 0;
                    for (int i = r; i < r + m; i++) {
                        for (int j = c; j < c + m; j++) {
                            sum += graph[i][j];
                        }
                    }
                    max = Math.max(max, sum);
                }
            }

            sb.append("#" + t + " " + max + "\n");
        }
        System.out.print(sb);
    }
}
