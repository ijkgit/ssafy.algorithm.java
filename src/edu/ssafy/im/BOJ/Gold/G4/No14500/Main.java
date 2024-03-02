package edu.ssafy.im.BOJ.Gold.G4.No14500;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int M;
    private static int[][] map;
    private static boolean[][] v;
    private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private static int ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < M; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }
        sol();
        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void sol() {
        ans = 0;
        v = new boolean[N][M];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                v[x][y] = true;
                dfs(x, y, 1, map[x][y]);
                v[x][y] = false;
            }
        }
    }

    private static void dfs(int x, int y, int depth, int s) {
        if (depth == 4) {
            ans = Math.max(ans, s);
            return;
        }

        for (int[] d : direction) {
            int nx = x + d[0];
            int ny = y + d[1];

            if (nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
            if (v[nx][ny]) continue;

            if (depth == 2) {
                v[nx][ny] = true;
                dfs(x, y, depth + 1, s + map[nx][ny]);
                v[nx][ny] = false;
            }

            v[nx][ny] = true;
            dfs(nx, ny, depth + 1, s + map[nx][ny]);
            v[nx][ny] = false;
        }
    }
}