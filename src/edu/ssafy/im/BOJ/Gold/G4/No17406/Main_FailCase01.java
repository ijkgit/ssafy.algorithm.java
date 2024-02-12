package edu.ssafy.im.BOJ.Gold.G4.No17406;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_FailCase01 {
    private int[][] graph, copy;
    private int ans = Integer.MAX_VALUE;
    private int r, c, s;

    public static void main(String[] args) throws IOException {
        new Main_FailCase01().io();
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
        for (int i = 0; i < graph.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < graph[0].length; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = graph[i][j];
            }
        }

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken()) - 1;
            s = Integer.parseInt(st.nextToken());

            slide();
        }

        sol();

        sb.append(ans);
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private void slide() {
        int r1 = r - s;
        int c1 = c - s;
        int r2 = r + s;
        int c2 = c + s;
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

        for (int[] a : graph) {
            System.out.println(Arrays.toString(a));
        }
    }

    private void copy() {
        for(int i=0; i<graph.length; i++) {
            graph[i] = copy[i].clone();
        }
    }

    private void sol() {
        int sum;
        for (int i = 0; i < graph.length; i++) {
            sum = 0;
            for (int j = 0; j < graph[0].length; j++) {
                sum += graph[i][j];
            }
            ans = Math.min(ans, sum);
        }
    }
}
