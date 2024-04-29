package edu.ssafy.im.CodeTree.ancientRuinExploration;

import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int N = 5;
    private static int K, M;
    private static int[][] map, copy;
    private static Queue<Point> queue;
    private static boolean[][] visited;
    private static final int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static ArrayDeque<Integer> num;
    private static Queue<Point> nq;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        copy = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        num = new ArrayDeque<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) num.offer(Integer.parseInt(st.nextToken()));

        sol();
    }

    private static void sol() {
        for (int k = 0; k < K; k++) {
            int[] res = find();
            if (res[0] == 0) return;
            
            start(res);

            update();
        }
    }

    private static void update() {
        nq = new PriorityQueue<>();
        search(true);
        delete();
    }

    private static void delete() {

    }

    private static void start(int[] res) {
        for (int k = 0; k <= res[1]; k++) rotate(res[2], res[3]);

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                map[x][y] = copy[x][y];
            }
        }
    }

    private static int[] find() {
        int[] max = {0, 0, 0, 0};
        for (int x = 0; x < N - 2; x++) {
            for (int y = 0; y < N - 2; y++) {
                copy();
                for (int k = 0; k < 3; k++) {
                    rotate(x, y);
                    int res = search(false);
                    if (max[0] < res) max = new int[]{res, k, x, y};
                }
            }
        }

        return max;
    }

    private static void copy() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                copy[x][y] = map[x][y];
            }
        }
    }

    private static void rotate(int x, int y) {
        copy[x][y] = map[x + 2][y];
        copy[x][y + 1] = map[x + 1][y];
        copy[x][y + 2] = map[x][y];

        copy[x + 1][y] = map[x + 2][y + 1];
        copy[x + 1][y + 2] = map[x][y + 1];

        copy[x + 2][y] = map[x + 2][y + 2];
        copy[x + 2][y + 1] = map[x + 1][y + 2];
        copy[x + 2][y + 2] = map[x][y + 2];
    }

    private static int search(boolean flag) {
        queue = new ArrayDeque<>();
        visited = new boolean[N][N];
        int res = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                res += bfs(x, y, flag);
            }
        }
        return res;
    }

    private static int bfs(int x, int y, boolean flag) {
        queue.offer(new Point(x, y));
        visited[x][y] = true;

        int cnt = 1;
        while (!queue.isEmpty()) {
            Point p = queue.poll();
            for (int[] d : direction) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || map[x][y] != map[nx][ny]) continue;

                visited[nx][ny] = true;
                queue.offer(new Point(nx, ny));
                cnt++;
                if (flag && cnt == 3) nq.offer(new Point(nx, ny));
            }
        }

        return cnt >= 3 ? cnt : 0;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
