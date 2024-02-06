package edu.ssafy.im.BOJ.Silver.S1.No2178;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BFS {
    int n, m;
    int[][] graph;
    boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new Main_BFS().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                graph[i][j] = s.charAt(j) - '0';
            }
        }

        int ans = bfs(0, 0, 1);

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int bfs(int x, int y, int cnt) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(x, y, cnt));
        visited = new boolean[n][m];
        visited[x][y] = true;

        int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            int px = p.x;
            int py = p.y;
            int pcnt = p.cnt;
            for (int d = 0; d < direction.length; d++) {
                int nx = px + direction[d][0];
                int ny = py + direction[d][1];

                if(nx == n-1 && ny == m-1) return pcnt+1;

                if(checkStatus(nx, ny)) {
                    visited[nx][ny] = true;
                    queue.offer(new Point(nx, ny, pcnt+1));
                }
            }
        }

        return -1;
    }

    private boolean checkStatus(int nx, int ny) {
        return 0 <= nx && nx < n && 0 <= ny && ny < m && !visited[nx][ny] && graph[nx][ny] == 1;
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

