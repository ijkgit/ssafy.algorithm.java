package edu.ssafy.im.SWEA.D2.No1954;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    StringBuilder sb = new StringBuilder();
    int[][] graph;
    int n;
    int direction[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public static void main(String[] args) throws IOException {
        new Solution().sol();
    }

    private void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            sb.append("#").append(t).append("\n");
            n = Integer.parseInt(br.readLine());
            graph = new int[n][n];
            graph[0][0] = 1;
            makeArray(0, 0, 0, 1);
        }
        System.out.println(sb);
    }

    private void makeArray(int x, int y, int d, int v) {
        // basis part
        if(v == n*n) {
            for (int r = 0; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    sb.append(graph[r][c]).append(" ");
                }
                sb.append("\n");
            }
            return;
        }

        // inductive part
        x += direction[d][0];
        y += direction[d][1];
        if(checkStatus(x, y)) {
            graph[x][y] = ++v;
            makeArray(x, y, d, v);
        } else {
            x -= direction[d][0];
            y -= direction[d][1];
            d++;
            d %= 4;
            makeArray(x, y, d, v);
        }
    }

    private boolean checkStatus(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n && graph[x][y] == 0;
    }

}
