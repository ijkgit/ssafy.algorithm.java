package edu.ssafy.im.BOJ.Gold.G3.No16236;
import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private int[][] graph;
    private final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int size = 2, sizeCount = 0, ans = 0;
    private boolean[][] visited;
    Point now;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        for (int i = 0; i < graph.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < graph.length; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 9) {
                    now = new Point(i, j);
                    graph[i][j] = 0;
                }
            }
        }
        sol();

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        while (true) { // 먹을 물고기가 없을 때까지 반복하면서, 최단거리 물고기를 찾음
            int time = bfs();
            if (time != 0) {
                ans += time;
            } else {
                break;
            }
        }
    }

    private int bfs() {
        int rx = 0;
        int ry = 0;
        int minTime = Integer.MAX_VALUE;
        visited = new boolean[graph.length][graph.length];
        ArrayDeque<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(now.x, now.y, 0));
        visited[now.x][now.y] = true;

        L:
        while (!queue.isEmpty()) {
            Point pt = queue.poll();
            for (int d = 0; d < direction.length; d++) {
                int nx = pt.x + direction[d][0];
                int ny = pt.y + direction[d][1];
                int nt = pt.time + 1;

                if (nt > minTime) break L; // 최단 거리를 벗어날 경우
                if (checkStatus(nx, ny)) { // 이동 가능 여부 확인
                    if (graph[nx][ny] != 0 && graph[nx][ny] < size) { // 먹을 수 있는 물고기라면
                        if (nt < minTime) { // 최단거리 갱신시
                            rx = nx;
                            ry = ny;
                            minTime = nt;
                        } else if (nt == minTime) { // 최단거리가 같은 물고기가 여러마리일 경우
                            if (nx < rx) { // 윗 물고기부터 우선순위
                                rx = nx;
                                ry = ny;
                            } else if (nx == rx) {
                                if (ny < ry) { // 윗 물고기들 중 왼쪽 물고기부터 우선순위
                                    rx = nx;
                                    ry = ny;
                                }
                            }
                        }
                    }
                    else {
                        visited[nx][ny] = true;
                        queue.offer(new Point(nx, ny, nt));
                    }
                }
            }
        }

        if (minTime == Integer.MAX_VALUE) { // 더 이상 먹을 물고기가 없음
            return 0;
        } else { // 물고기를 먹으면, 위치와 사이즈 갱신
            graph[rx][ry] = 0;
            now.x = rx;
            now.y = ry;
            updateSize();
            return minTime;
        }
    }

    private void updateSize() {
        sizeCount++;
        if (size == sizeCount) {
            size++;
            sizeCount = 0;
        }
    }

    private boolean checkStatus(int x, int y) {
        return 0 <= x && x < graph.length && 0 <= y && y < graph.length && graph[x][y] <= size && !visited[x][y];
    }

    class Point {
        int x;
        int y;
        int time;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
