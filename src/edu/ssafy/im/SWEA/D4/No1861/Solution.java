package edu.ssafy.im.SWEA.D4.No1861;

import java.io.*;
import java.util.*;

public class Solution {
    int[][] graph;
    boolean[][] visited;
    int n;
    int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    PriorityQueue<Point> pq;

    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine());

        for (int t = 1; t <= testCase; t++) {
            n = Integer.parseInt(br.readLine());

            graph = new int[n][n];
            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < n; j++) {
                    graph[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            pq = new PriorityQueue<>(
                    (o1, o2) -> {
                        if (o1.cnt == o2.cnt) {
                            int p1 = graph[o1.x][o1.y];
                            int p2 = graph[o2.x][o2.y];

                            return p1 - p2;
                        }
                        return o2.cnt - o1.cnt;
                    }
            );
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    visited = new boolean[n][n];
                    bfs(i, j, 1);
                }
            }

            Point p = pq.poll();
            int ans = p.cnt;
            int roomNo = graph[p.x][p.y];
            sb.append("#").append(t).append(" ").append(roomNo).append(" ").append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void bfs(int x, int y, int cnt) {
        Queue<Point> queue = new ArrayDeque();
        queue.offer(new Point(x, y, cnt));
        visited[x][y] = true;
        pq.offer(new Point(x, y, cnt));

        while (!queue.isEmpty()) {
            Point p = queue.poll();

            if(p.cnt >= pq.peek().cnt) pq.offer(new Point(x, y, p.cnt));

            for (int d = 0; d < direction.length; d++) {
                int nx = p.x + direction[d][0];
                int ny = p.y + direction[d][1];

                if (checkStatus(nx, ny)) {
                    if (graph[nx][ny] - graph[p.x][p.y] == 1) {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny, p.cnt + 1));
                    }
                }
            }
        }
    }

    private boolean checkStatus(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n && !visited[x][y];
    }


    class Point {
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
