package edu.ssafy.im.BOJ.Gold.G5.No14503;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private int n, m;
    private int[][] map;
    private static final int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private boolean[][] visited;
    private int ans = 1;

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

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void dfs(int r, int c, int d) {
        visited[r][c] = true;

        for (int i = 0; i < 4; i++) {
            d = (d+3)%4;
            int nr = r + direction[d][0];
            int nc = c + direction[d][1];

            if (!(nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 1 || visited[nr][nc])) {
                ans++;
                dfs(nr, nc, d);
                return;
            }
        }

        int nr = r - direction[d][0];
        int nc = c - direction[d][1];
        if (!(nr < 0 || nc < 0 || nr >= n || nc >= m || map[nr][nc] == 1)) {
            dfs(nr, nc, d);
        }
    }
}
