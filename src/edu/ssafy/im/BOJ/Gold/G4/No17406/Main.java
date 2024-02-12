package edu.ssafy.im.BOJ.Gold.G4.No17406;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    private int[][] graph, copy, original;
    private int ans = Integer.MAX_VALUE;
    private ArrayList<Operation> operationList;
    private int[] sel;
    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        graph = new int[n][m];
        copy = new int[n][m];
        original = new int[n][m];
        for (int i = 0; i < graph.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < graph[0].length; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = graph[i][j];
                original[i][j] = graph[i][j];
            }
        }

        operationList = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());

            operationList.add(new Operation(r, c, s));
        }

        sol();

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void sol() {
        sel = new int[operationList.size()];
        permutation(0, 0);
    }

    private void permutation(int k, int v) {
        if(k == sel.length) {
            System.out.println("------permutation------");
            System.out.println(Arrays.toString(sel));
            for(int s: sel) {
                System.out.println(s);
                slide(operationList.get(s));
            }
            getMin();
            setOriginal();
            return;
        }

        for (int i = 0; i < sel.length; i++) {
            if((v & 1 << i) == 0) {
                sel[k] = i;
                permutation(k+1, v | 1 << i);
            }
        }
    }

    private void slide(Operation o) {
        int r1 = o.r - o.s;
        int c1 = o.c - o.s;
        int r2 = o.r + o.s;
        int c2 = o.c + o.s;
        int depth = 0;

        while(r1 < r2) {
            // 우
            for (int c = c1; c <= c2-1; c++) {
                copy[r1][c+1] = graph[r1][c];
            }
            // 하
            for (int r = r1; r <= r2-1; r++) {
                copy[r+1][c2] = graph[r][c2];
            }
            // 좌
            for (int c = c2; c > c1; c--) {
                copy[r2][c-1] = graph[r2][c];
            }
            // 상
            for (int r = r2; r > r1; r--) {
                copy[r-1][c1] = graph[r][c1];
            }
            depth++;
            r1 += depth;
            c1 += depth;
            r2 -= depth;
            c2 -= depth;
        }

        copy();

        System.out.println("------graph------");
        for (int[] a : graph) {
            System.out.println(Arrays.toString(a));
        }
    }

    private void copy() {
        for(int i=0; i<graph.length; i++) {
            graph[i] = copy[i].clone();
        }
    }

    private void setOriginal() {
        for(int i=0; i<graph.length; i++) {
            graph[i] = original[i].clone();
            copy[i] = original[i].clone();
        }
        System.out.println("------original------");
        for (int[] a : graph) {
            System.out.println(Arrays.toString(a));
        }
    }

    private void getMin() {
        int sum;
        for (int i = 0; i < graph.length; i++) {
            sum = 0;
            for (int j = 0; j < graph[0].length; j++) {
                sum += graph[i][j];
            }
            ans = Math.min(ans, sum);
        }
        System.out.println("------Answer------");
        System.out.println(ans);
    }
    class Operation {
        int r, c, s;

        public Operation(int r, int c, int s) {
            this.r = r;
            this.c = c;
            this.s = s;
        }
    }
}
