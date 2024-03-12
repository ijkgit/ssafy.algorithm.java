package edu.ssafy.im.BOJ.Gold.G3.No15684;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, H;
    private static int ans = Integer.MAX_VALUE;
    private static int[][] map;
    private static int LIMIT;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        map = new int[H][N];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = 1;
            map[a][b+1] = -1;
        }

        if (bfs()) sb.append(0);
        else {
            for (int i = 1; i <= 3; i++) {
                LIMIT = i;
                combination(0, M);
            }
        }
        if (sb.length() == 0) sb.append(ans == Integer.MAX_VALUE ? -1 : ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void combination(int k, int h) {
        if (ans != Integer.MAX_VALUE) return;

        if (k == LIMIT || h == H) {
            if(bfs()) ans = k;
            return;
        }

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N-1; j++) {
                if (map[i][j] != 1 && map[i][j] != -1 && map[i][j+1] != 1) {
                    map[i][j] = 1;
                    map[i][j+1] = -1;
                    combination(k+1, h+1);
                    map[i][j] = 0;
                    map[i][j+1] = 0;
                }
            }
        }
    }

    private static boolean bfs() {
        for (int i = 0; i < N; i++) {
            Queue<Point> q = new ArrayDeque<>();
            q.offer(new Point(0, i));
            L:while(!q.isEmpty()) {
                Point p = q.poll();
                if (p.x == H) {
                    if (p.y == i) break L;
                    else return false;
                }
                if (p.y < 0 || p.y >= N) continue;
                if (map[p.x][p.y] == -1) {
                    q.offer(new Point(p.x+1, p.y - 1));
                }
                if (map[p.x][p.y] == 1) {
                    q.offer(new Point(p.x+1, p.y + 1));
                }
                if (map[p.x][p.y] == 0) {
                    q.offer(new Point(p.x+1, p.y));
                }
            }
        }
        return true;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Point{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }
}
