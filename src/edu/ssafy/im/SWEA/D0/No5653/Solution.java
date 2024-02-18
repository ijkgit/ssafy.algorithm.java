package edu.ssafy.im.SWEA.D0.No5653;

import java.io.*;
import java.util.*;

public class Solution {
    private int n, m, k;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    HashMap<Point, Time> map;

    class Point {
        int x, y, t;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }

        @Override
        public boolean equals(Object o) {
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }
    }

    class Time {
        int t, at;

        public Time(int t, int at) {
            this.t = t;
            this.at = at;
        }
    }
    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            map = new HashMap<>();
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    int v = Integer.parseInt(st.nextToken());
                    if (v != 0) {
                        map.put(new Point(i, j), new Time(v, 0));
                    }
                }
            }
            sb.append("#").append(t).append(" ").append(sol()).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int sol() {
        ArrayDeque<Point> queue = new ArrayDeque<>();
        for (int now = 0; now < k; now++) {
            for (Map.Entry<Point, Time> entry : map.entrySet()) {
                Point p = entry.getKey();
                Time t = entry.getValue();

                if(t.t == -1) continue;
                if(now - t.t == t.at) {
                    map.get(p).t = -1;
                    queue.offer(new Point(p.x, p.y, t.t));
                }
            }

            while(!queue.isEmpty()) {
                Point p = queue.poll();
                for (int d = 0; d < direction.length; d++) {
                    int nx = p.x + direction[d][0];
                    int ny = p.y + direction[d][1];

                    Point np = new Point(nx, ny);

                    if(map.containsKey(np)) {
                        if(map.get(np).t == -1) continue;
                        if(map.get(np).t > p.t) continue;
                    }
                    map.put()
                }
            }
        }
    }
}
