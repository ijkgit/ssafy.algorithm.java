package edu.ssafy.im.BOJ.Gold.G3.No14890;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N, L;
    private static int[][] map;
    private static int ans = 0;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            if (row(i)) ans++;
            if (col(i)) ans++;
        }
        return ans;
    }

    private static boolean row(int x) {
        boolean[] v = new boolean[N];

        for (int y = 0; y < N - 1; y++) {
            int d = map[x][y] - map[x][y + 1];
            if (Math.abs(d) > 1) return false;
            else if (d == -1) {
                for (int l = 0; l < L; l++) {
                    if (y - l < 0 || v[y - l]) return false;
                    if (map[x][y] != map[x][y - l]) return false;
                    v[y - l] = true;
                }
            } else if (d == 1) {
                for (int l = 1; l <= L; l++) {
                    if (y + l >= N || v[y + l]) return false;
                    if (map[x][y] - 1 != map[x][y + l]) return false;
                    v[y + l] = true;
                }
            }
        }

        return true;
    }

    private static boolean col(int y) {
        boolean[] v = new boolean[N];

        for (int x = 0; x < N - 1; x++) {
            int d = map[x][y] - map[x + 1][y];
            if (Math.abs(d) > 1) return false;
            else if (d == -1) {
                for (int l = 0; l < L; l++) {
                    if (x - l < 0 || v[x - l]) return false;
                    if (map[x][y] != map[x - l][y]) return false;
                    v[x - l] = true;
                }
            } else if (d == 1) {
                for (int l = 1; l <= L; l++) {
                    if (x + l >= N || v[x + l]) return false;
                    if (map[x][y] - 1 != map[x + l][y]) return false;
                    v[x + l] = true;
                }
            }
        }

        return true;
    }
}
