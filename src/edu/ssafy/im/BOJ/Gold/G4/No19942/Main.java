package edu.ssafy.im.BOJ.Gold.G4.No19942;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static Food LIMIT;
    private static Food[] foods;
    private static int minCost = Integer.MAX_VALUE;
    private static String ans;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        LIMIT = new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        foods = new Food[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            foods[i] = new Food(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        recursive(0, new Food(0, 0, 0, 0, 0), new StringBuilder());

        if (minCost == Integer.MAX_VALUE) sb.append(-1);
        else sb.append(minCost).append("\n").append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void recursive(int i, Food sum, StringBuilder sb) {
        if (sum.check() && sum.c < minCost) {
            ans = sb.toString();
            minCost = sum.c;
        }

        if (i == N) return;

        sum.add(foods[i]);
        recursive(i+1, sum, sb.append(i+1).append(" "));

        sum.remove(foods[i]);
        recursive(i+1, sum, sb.delete(sb.length() - ((i+1) < 10 ? 2 : 3), sb.length()));
    }

    static class Food {
        int p, f, s, v, c;

        public Food(int p, int f, int s, int v, int c) {
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
            this.c = c;
        }

        public Food(int p, int f, int s, int v) {
            this.p = p;
            this.f = f;
            this.s = s;
            this.v = v;
        }

        public void add(Food f) {
            this.p += f.p;
            this.f += f.f;
            this.s += f.s;
            this.v += f.v;
            this.c += f.c;
        }

        public void remove(Food f) {
            this.p -= f.p;
            this.f -= f.f;
            this.s -= f.s;
            this.v -= f.v;
            this.c -= f.c;
        }

        public boolean check() {
            return this.p >= LIMIT.p && this.f >= LIMIT.f && this.s >= LIMIT.s && this.v >= LIMIT.v;
        }
    }
}