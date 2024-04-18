package edu.ssafy.im.CodeTree.toastEggmold;

import java.io.*;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    private static int N, L, R;
    private static int[][] map, union;
    private static int[][] direction = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        sol();
    }

    private static void sol() {
        union = new int[N][N];
        int num = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                union[i][j] = num++;
            }
        }

        makeUnion();
        print();
    }

    private static void makeUnion() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (!check(map[x][y], map[nx][ny])) continue;
                    union[nx][ny] = union[x][y];
                }
            }
        }
    }

    private static void move() {
        visited = new boolean[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                bfs(x, y);
            }
        }
    }

    private static int bfs(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        visited[x][y] = true;
        queue.offer(new int[]{x, y});

        int sum = map[x][y];
        int cnt = 1;
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            for (int[] d : direction) {
                int nx = cur[0] + d[0];
                int ny = cur[1] + d[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (union[x][y] != union[nx][ny]) continue;

                visited[nx][ny] = true;
                sum += map[nx][ny];
                cnt++;
            }
        }

        return sum / cnt;
    }

    private static boolean check(int a, int b) {
        return L <= Math.abs(a - b) && Math.abs(a - b) <= R;
    }

    private static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(union[i][j] + " ");
            }
            System.out.println();
        }
    }
}
