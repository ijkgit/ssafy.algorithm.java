package edu.ssafy.im.BOJ.Gold.G2.No17136;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N = 10;
    private static int ans = Integer.MAX_VALUE;
    private static int[][] map;
    private static int SIZE = 5;
    private static int[] papers = {0, 5, 5, 5, 5, 5};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        map = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        recursive(0, 0, 0);

        sb.append(ans == Integer.MAX_VALUE ? -1 : ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void recursive(int x, int y, int c) {
        if (x == 10) {
            ans = Math.min(ans, c);
            return;
        }

        if (map[x][y] == 0) {
            if (y == 9) recursive(x + 1, 0, c);
            else recursive(x, y + 1, c);
        } else if (map[x][y] == 1) {
            int size = getPaperSize(x, y);
            if (size == -1) return;

            for (int i = 1; i <= size; i++) {
                if (papers[i] > 0) {
                    for (int nx = 0; nx < i; nx++) {
                        for (int ny = 0; ny < i; ny++) {
                            map[x + nx][y + ny] = 0;
                        }
                    }

                    papers[i]--;

                    if (y + i >= 10) recursive(x + 1, 0, c + 1);
                    else recursive(x, y + i, c + 1);

                    for (int nx = 0; nx < i; nx++) {
                        for (int ny = 0; ny < i; ny++) {
                            map[x + nx][y + ny] = 1;
                        }
                    }

                    papers[i]++;
                }
            }
        }
    }

    private static int getPaperSize(int nx, int ny) {
        for (int size = SIZE; size > 0; size--) {
            boolean flag = true;
            L:
            for (int x = nx; x < nx + size; x++) {
                for (int y = ny; y < ny + size; y++) {
                    if (x < 0 || y < 0 || x >= N || y >= N || map[x][y] == 0) {
                        flag = false;
                        break L;
                    }
                }
            }
            if (flag) return size;
        }
        return -1;
    }
}
