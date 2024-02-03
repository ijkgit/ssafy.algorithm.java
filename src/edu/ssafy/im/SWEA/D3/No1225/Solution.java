package edu.ssafy.im.SWEA.D3.No1225;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    int n = 8;
    Queue<Integer> queue;

    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            br.readLine();
            StringTokenizer st = new StringTokenizer(br.readLine());
            queue = new ArrayDeque<>();

            for (int i = 0; i < n; i++) {
                queue.offer(Integer.parseInt(st.nextToken()));
            }

            String ans = sol();

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private String sol() {
        StringBuilder sb = new StringBuilder();
        int cycle = 1;
        L:
        while (true) {
            int q = queue.poll() - cycle;
            if (q <= 0) {
                queue.offer(0);
                break L;
            } else {
                queue.offer(q);
            }

            cycle %= 5;
            cycle++;
        }

        for (int q : queue) {
            sb.append(q).append(" ");
        }

        return sb.toString();
    }
}

