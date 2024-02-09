package edu.ssafy.im.BOJ.Gold.G3.No16236;

import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_FailCase {
    private int[][] graph;
    private final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private int size = 2, sizeCount = 0, ans = 0;
    private PriorityQueue<Point> targetList = new PriorityQueue<>();
    private boolean[][] visited;
    Point now;

    public static void main(String[] args) throws IOException {
        new Main_FailCase().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        graph = new int[n][n];

        Point start = null;
        for (int i = 0; i < graph.length; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < graph.length; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 9) {
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
        boolean finishFlag = false;

        while(!finishFlag) {
            findTarget();

            System.out.println("-------");
            for (Point t : targetList) {
                System.out.println(t.toString());
            }
            System.out.println("-------");

            int time = 0;
            for (Point t : targetList) {
                visited = new boolean[graph.length][graph.length];
                time = bfs(now, t);
                if(time != 0) {
                    ans += time;
                    break;
                }
            }

            if (time == 0) {
                finishFlag = true;
            }
        }
    }

    private int bfs(Point s, Point t) {
        ArrayDeque<PointTime> queue = new ArrayDeque<>();
        queue.offer(new PointTime(s, 0));
        visited[s.x][s.y] = true;

        while(!queue.isEmpty()) {
            PointTime pt = queue.poll();
            for (int d = 0; d < direction.length; d++) {
                int nx = pt.p.x + direction[d][0];
                int ny = pt.p.y + direction[d][1];
                int nt = pt.time + 1;

                if(nx == t.x && ny == t.y) {
                    graph[nx][ny] = 0;
                    sizeCount++;
                    updateSize();
                    now.x = nx;
                    now.y = ny;
                    return nt;
                }

                if(checkStatus(nx, ny)) {
                    visited[nx][ny] = true;
                    queue.offer(new PointTime(new Point(nx, ny), nt));
                }
            }
        }

        return 0;
    }

    private void updateSize() {
        if(size == sizeCount) {
            size++;
            sizeCount = 0;
        }
    }

    private boolean checkStatus(int x, int y) {
        return 0 <= x && x < graph.length && 0 <= y && y < graph.length && graph[x][y] <= size;
    }

    private void findTarget() {
        targetList = new PriorityQueue<>(
                (o1, o2) -> {
                    int distance1 = Math.abs(o1.x - now.x) + Math.abs(o1.y - now.y);
                    int distance2 = Math.abs(o2.x - now.x) + Math.abs(o2.y - now.y);

                    if(distance1 == distance2) {
                        if(o1.x == o2.x) {
                            return o1.y - o2.y; // 거리가 같고 위쪽이 여러 개면 왼쪽 우선순위
                        }
                        return o1.x - o2.x; // 거리가 같다면 위쪽 우선순위
                    }
                    return distance1 - distance2; // 거리 짧은 것 우선순위
                }
        );

        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                if (graph[i][j] < size && graph[i][j] != 0) {
                    targetList.add(new Point(i, j));
                }
            }
        }
    }

    class Point {
        int x;
        int y;

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

    class PointTime {
        Point p;
        int time;

        public PointTime(Point p, int time) {
            this.p = p;
            this.time = time;
        }
    }
}
