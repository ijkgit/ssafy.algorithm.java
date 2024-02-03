package edu.ssafy.im.BOJ.Silver.S4.No2164;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
    int n;

    public static void main(String[] args) throws IOException {
        new Main().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        n = Integer.parseInt(br.readLine());

        int ans = sol();

        sb.append(ans);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int sol() {
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= n; i++) {
            queue.offer(i);
        }

        while(queue.size() != 1) {
            queue.poll();
            queue.offer(queue.poll());
        }

        return queue.poll();
    }
}

