package edu.ssafy.im.BOJ.Gold.G3.No17142;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int TIME = 6;
    private int[][] graph;
    private ArrayList<Virus> virusList;
    private int m, ans = 0;
    private static final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[] sel;
    private boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new Main().sol();
    }

    class Virus {
        public Virus(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }

        int x, y, time;
    }

    private void sol() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        virusList = new ArrayList<>();
        for (int i = 0; i < graph.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < graph.length; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if (graph[i][j] == 2) {
                    virusList.add(new Virus(i, j, TIME));
                }
            }
        }

        sel = new int[m];
        permutation(0, 0);
    }

    private void permutation(int k, int v) {
        if (sel.length == m) {
            visited = new boolean[graph.length][graph.length];
            ;
            bfs();
            return;
        }

        for (int i = 0; i < virusList.size(); i++) {
            if ((v & (1 << i)) == 0) {
                sel[k] = i;
                permutation(k + 1, v | 1 << i);
            }
        }
    }

    private int bfs() {
        Queue<Virus> queue = new ArrayDeque<>();
        for (int s : sel) {
            queue.offer(virusList.get(s));
        }

        int time = 0;
        boolean flag = true;
        while (!queue.isEmpty()) {
            Virus v = queue.poll();
            if (v.time == 6 && flag) {
                time++;
                flag = false;
            }
            if (v.time != 6) {
                flag = true;
            }
            if (time == 0) return time;

            for (int d=0; d< direction.length; d++) {
                int nx = v.x + direction[d][0];
                int ny = v.y + direction[d][1];

                if(checkStatus(nx, ny)) {
                    queue.offer(new Virus(nx, ny, TIME));
                }
            }
        }

        return -1;
    }

    private boolean checkStatus(int nx, int ny) {
        return 0 <= nx && nx < graph.length && 0 <= ny && ny < graph.length && !visited[nx][ny] && graph[nx][ny] != 1;
    }
}