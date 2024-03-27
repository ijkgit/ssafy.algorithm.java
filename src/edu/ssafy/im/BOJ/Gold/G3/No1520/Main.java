package edu.ssafy.im.BOJ.Gold.G3.No1520;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int M, N;
    private static int[][] map, dp;
    private final static int[][] direction = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        dp = new int[M][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1;
            }
        }

        dfs(M-1, N-1);

        sb.append(dp[M-1][N-1]);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int dfs(int x, int y) {
        if (x == 0 && y == 0) return 1;

        if (dp[x][y] != -1) return dp[x][y];

        dp[x][y] = 0;
        for (int[] d : direction) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx < 0 || ny < 0 || nx >= M || ny >= N || map[nx][ny] == 0) continue;
            if (map[x][y] >= map[nx][ny]) continue;

            if (dp[nx][ny] == -1) dp[x][y] += dfs(nx, ny);
            else dp[x][y] += dp[nx][ny];
        }

        return dp[x][y];
    }
}
