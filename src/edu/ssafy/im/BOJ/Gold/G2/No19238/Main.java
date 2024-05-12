package edu.ssafy.im.BOJ.Gold.G2.No19238;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static int N, M, E;
    private static int[][] map;
    private static Queue<Guest> guests;

    static class Guest implements Comparable<Guest>{
        int sx, sy, rx, ry, d;

        public Guest(int sx, int sy, int rx, int ry) {
            this.sx = sx;
            this.sy = sy;
            this.rx = rx;
            this.ry = ry;
        }

        @Override
        public int compareTo(Guest g) {
            if (this.d == g.d) {
                if (this.sx == g.sx) return this.sy - g.sy;
                return this.sx - g.sx;
            }
            return this.d - g.d;
        }

        public void update() {}
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
        int sx = Integer.parseInt(st.nextToken());
        int sy = Integer.parseInt(st.nextToken());

        guests = new PriorityQueue<>();
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            guests.offer(new Guest(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        
        sol();
    }

    private static void sol() {

    }
}
