package edu.ssafy.im.CodeTree.rabitAndRace;

import java.io.*;
import java.util.*;

public class Main {
    private static int N, M, P;
    private static int[][] map;
    private static Queue<Rabbit> rabbits;
    private static final int[][] direction = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    static class Rabbit implements Comparable<Rabbit> {
        int pid, d, n, r, c;

        public Rabbit(int pid, int d, int n, int r, int c) {
            this.pid = pid;
            this.d = d;
            this.n = n;
            this.r = r;
            this.c = c;
        }

        @Override
        public int compareTo(Rabbit o) {
            if (this.n == o.n) {
                if (this.r + this.c == o.r + o.c) {
                    if (this.r == o.r) {
                        if (this.c == o.c) return this.pid - o.pid;
                        return this.c - o.c;
                    }
                    return this.r - o.r;
                }
                return (this.r + this.c) - (o.r + o.c);
            }
            return this.n - o.n;
        }
    }

    private static void init(StringTokenizer st) {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        
        P = Integer.parseInt(st.nextToken());
        rabbits = new PriorityQueue<>();
        for (int p = 0; p < P; p++) {
            int pid = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            rabbits.offer(new Rabbit(pid, d, 0, 0, 0));
        }
    }

    private static void run(StringTokenizer st) {
        int K = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());

        for (int k = 0; k < K; k++) {
            Rabbit rabbit = rabbits.poll();
            int score = 0, sr = 0, sc = 0;
            int s;
            for (int dir = 0; dir < direction.length; dir++) {
                int s = dir == 0 || dir == 2 ? N : M;
                if((rabbit.d / s) % 2 == 1) dir = (dir + 2) % 4;
                
            }
        }
    }

    private static void change() {
    }

    private static void finish() {
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int Q = Integer.parseInt(br.readLine());
        for (int q = 0; q < Q; q++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            if (command == 100) init(st);
            else if (command == 200) run(st);
            else if (command == 300) change();
            else finish();
        }
    }
}
