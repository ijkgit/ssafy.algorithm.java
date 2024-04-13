package edu.ssafy.im.BOJ.Gold.G5.No21608;

import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] map;
    private static int[][] lovers;
    private static int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        lovers = new int[N * N + 1][4];

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            for (int to = 0; to < 4; to++) {
                lovers[from][to] = Integer.parseInt(st.nextToken());
            }

            if (i == 0) map[1][1] = from;
            else setSeat(from);

//            System.out.println();
//            for (int j = 0; j < N; j++) {
//                for (int k = 0; k < N; k++) {
//                    System.out.print(map[j][k] + " ");
//                }
//                System.out.println();
//            }
        }

        sb.append(getScore());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void setSeat(int from) {
        int[][] count = new int[N][N];
        int max = 0;

        boolean[] isLover = new boolean[N * N + 1];
        for (int lover : lovers[from]) isLover[lover] = true;

        // 1번 조건
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (!isLover[map[x][y]]) continue;
                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (map[nx][ny] != 0) continue;

                    count[nx][ny]++;
                    max = Math.max(max, count[nx][ny]);
                }
            }
        }

        Queue<Point> q = new ArrayDeque<>();
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (count[x][y] != max) continue;
                q.offer(new Point(x, y));
            }
        }

        // 1을 만족하는 칸이 하나일 경우 종료
        if (q.size() == 1) {
            map[q.peek().x][q.peek().y] = from;
            return;
        }

        // 2번 조건, 3번 조건
        PriorityQueue<Point> pq = new PriorityQueue<>();
        while (!q.isEmpty()) {
            Point p = q.poll();
            if (map[p.x][p.y] != 0) continue;
            int c = 0;
            for (int[] d : direction) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (map[nx][ny] != 0) continue;

                c++;
            }
            pq.offer(new Point(p.x, p.y, c));
        }

        map[pq.peek().x][pq.peek().y] = from;
    }

    private static int getScore() {
        int ans = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                int score = 0;
                for (int[] d : direction) {
                    int nx = x + d[0];
                    int ny = y + d[1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;

                    for (int lover : lovers[map[x][y]]) {
                        if (map[nx][ny] == lover) score++;
                    }
                }
                if (score == 1) ans++;
                else if (score == 2) ans += 10;
                else if (score == 3) ans += 100;
                else if (score == 4) ans += 1000;
            }
        }
        return ans;
    }

    static class Point implements Comparable<Point> {
        int x, y, c;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        // 2번 조건, 3번 조건
        @Override
        public int compareTo(Point o) {
            if (o.c == this.c) {
                if (this.x == o.x) return this.y - o.y;
                return this.x - o.x;
            }
            return o.c - this.c;
        }
    }
}
