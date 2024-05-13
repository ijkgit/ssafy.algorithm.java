package edu.ssafy.im.BOJ.Gold.G2.No19238;

import java.io.*;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, E;
    private static int[][] map;
    private static Queue<Guest> guests;
    private static Point Taxi;
    private static final int[][] direction = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Guest implements Comparable<Guest> {
        Point start, target;
        int cost;

        public Guest(Point start, Point target) {
            this.start = start;
            this.target = target;
        }

        @Override
        public int compareTo(Guest g) {
            if (this.cost == g.cost) {
                if (this.start.x == g.start.x) return this.start.y - g.start.y;
                return this.start.x - g.start.x;
            }
            return this.cost - g.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int x = 0; x < N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 0; y < N; y++) {
                map[x][y] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        Taxi = new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

        guests = new PriorityQueue<>();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            guests.offer(new Guest(new Point(x, y), new Point(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1)));
            map[x][y] = -1;
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        for (int m = 0; m < M; m++) {
            Guest guest = findGuest();
            if (guest.cost == Integer.MAX_VALUE) return -1; // 모든 손님을 이동시킬 수 없는 경우

            move(guest);
            if (E < 0) return -1; // 이동하는 도중에 연료가 바닥나는 경우
        }
        return E;
    }

    private static void move(Guest guest) {
        E -= guest.cost;
        Taxi.x = guest.start.x;
        Taxi.y = guest.start.y;
        if (E <= 0) return;

        int cost = bfs(guest);
        E -= cost;
        if (E < 0) return;
        else E += cost * 2;

        Taxi.x = guest.target.x;
        Taxi.y = guest.target.y;
    }

    private static int bfs(Guest guest) {
        Queue<Point> pq = new ArrayDeque<>();
        pq.offer(new Point(Taxi.x, Taxi.y));
        boolean[][] visited = new boolean[N][N];
        visited[Taxi.x][Taxi.y] = true;
        int cost = 0;
        while (!pq.isEmpty()) {
            int size = pq.size();
            for (int c = 0; c < size; c++) {
                Point p = pq.poll();
                if (p.x == guest.target.x && p.y == guest.target.y) return cost;
                for (int[] d : direction) {
                    int nx = p.x + d[0];
                    int ny = p.y + d[1];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if (visited[nx][ny] || map[nx][ny] == 1) continue;

                    visited[nx][ny] = true;
                    pq.offer(new Point(nx, ny));
                }
            }
            cost++;
        }
        return -1;
    }

    private static Guest findGuest() {
        Queue<Point> pq = new ArrayDeque<>();
        pq.offer(new Point(Taxi.x, Taxi.y));
        boolean[][] visited = new boolean[N][N];
        visited[Taxi.x][Taxi.y] = true;
        int cost = 0;
        while (!pq.isEmpty()) {
            Point p = pq.poll();
            if (map[p.x][p.y] == -1) return new Point(p.x);
            for (int[] d : direction) {
                int nx = p.x + d[0];
                int ny = p.y + d[1];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny] || map[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                pq.offer(new Point(nx, ny));
            }
            return -1;
        }
    }
}