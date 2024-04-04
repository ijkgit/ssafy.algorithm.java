package edu.ssafy.im.SWEA.D4.No1249.SecondTry;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {
    private static int N;
    private static int[][] map;
    private final static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int TC = Integer.parseInt(br.readLine());
        for (int T = 1; T <= TC; T++) {
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                char[] row = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    map[i][j] = row[j] - '0';
                }
            }
            sb.append("#").append(T).append(" ").append(sol()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        Queue<Point> pq = new PriorityQueue<>();
        pq.offer(new Point(0, 0, 0));
        boolean[][] visited = new boolean[N][N];
        visited[0][0] = true;

        while (!pq.isEmpty()) {
            Point p = pq.poll();
            for (int[] d : direction) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                // basis part
                if (nx == N-1 && ny == N-1) return p.w;

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                pq.offer(new Point(nx, ny, p.w + map[nx][ny]));
            }
        }
        return -1;
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
            return this.w - o.w;
        }
    }
}
