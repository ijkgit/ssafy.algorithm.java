package edu.ssafy.im.SWEA.D0.No5656;

import java.io.*;
import java.util.*;

public class Main {
    private static int N, W, H;
    private static int[][] map, history;
    private static int[] sel;
    private final static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int TC = Integer.parseInt(br.readLine());
        for (int T = 1; T <= TC; T++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            map = new int[H][W];
            history = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] != 0) history[i][j] = 1;
                }
            }
            sol();
        }
    }

    private static void sol() {
        sel = new int[3];
        permutation(0);
    }



    private static void permutation(int k) {
        if (k == 3) {
//            System.out.println(Arrays.toString(sel));
            for (int s : sel) {
                shoot(0, s);
            }
            return;
        }

        for (int i = 0; i < W; i++) {
            sel[k] = i;
            permutation(k + 1);
        }
    }

    private static void shoot(int x, int y) {
        if (x < 0) return;

        if (map[x][y] == 0) shoot(x-1, y);
        else bomb(x, y);
    }

    private static void bomb(int x, int y) {
        if (map[x][y] == 1) return;

        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(x, y, map[x][y]));
        boolean[][] v = new boolean[H][W];
        v[x][y] = true;

        while(!q.isEmpty()) {
            Point p = q.poll();
            for (int depth = 0; depth < map[p.x][p.y]; depth++) {
                for (int[] d : direction) {
                    int nx = p.x + d[0] * depth;
                    int ny = p.y + d[1] * depth;

                    if (nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                    if (map[nx][ny] == 0 || v[nx][ny]) continue;

                    history[nx][ny]
                    q.offer(new Point(nx, ny, map[nx][ny]));
                }
            }
        }
    }

    static class Point implements Comparable<Point> {
        int x, y, w;

        public Point(int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        @Override
        public int compareTo(Point o) {
            return o.w - this.w;
        }
    }
}
