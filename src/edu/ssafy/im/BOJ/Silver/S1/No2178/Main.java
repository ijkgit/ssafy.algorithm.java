package edu.ssafy.im.BOJ.Silver.S1.No2178;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    int n, m;
    int[][] graph;
    int[][] visited;
    final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = s.charAt(j) - '0';
                visited[i][j] = Integer.MAX_VALUE;
            }
        }

        dfs(0, 0, 1);

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void dfs(int x, int y, int cnt) {
        // basis part
        if (x == n - 1 && y == m - 1) {
            ans = Math.min(ans, cnt);
            return;
        }

        // inductive part
        visited[x][y] = cnt;
        for (int d = 0; d < direction.length; d++) {
            int nx = x + direction[d][0];
            int ny = y + direction[d][1];

            if (checkStatus(nx, ny) && visited[nx][ny] > cnt + 1) {
                dfs(nx, ny, cnt + 1);
            }
        }
    }

    private boolean checkStatus(int nx, int ny) {
        return 0 <= nx && nx < n && 0 <= ny && ny < m && graph[nx][ny] == 1;
    }

    private class Point {
        int x;
        int y;
        int cnt;

        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
