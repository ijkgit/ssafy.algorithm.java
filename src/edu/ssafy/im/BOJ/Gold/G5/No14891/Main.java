package edu.ssafy.im.BOJ.Gold.G5.No14891;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static int[][] gear = new int[4][8];
    private static Command[] commands;
    private static int[] moves;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 4; i++) {
            char[] chars = br.readLine().toCharArray();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = chars[j] - '0';
            }
        }

        int K = Integer.parseInt(br.readLine());
        commands = new Command[K];
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            commands[i] = new Command(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()));
        }

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static int sol() {
        for (Command c : commands) {
            moves = new int[4];
            moves[c.n] = c.d;
            if (c.n == 0) {
                if (gear[0][2] != gear[1][6]) moves[1] = c.d == 1 ? -1 : 1;
                if (moves[1] != 0 && gear[1][2] != gear[2][6]) moves[2] = moves[1] == 1 ? -1 : 1;
                if (moves[2] != 0 && gear[2][2] != gear[3][6]) moves[3] = moves[2] == 1 ? -1 : 1;
            } else if (c.n == 1) {
                if (gear[0][2] != gear[1][6]) moves[0] = c.d == 1 ? -1 : 1;
                if (gear[1][2] != gear[2][6]) moves[2] = c.d == 1 ? -1 : 1;
                if (moves[2] != 0 && gear[2][2] != gear[3][6]) moves[3] = moves[2] == 1 ? -1 : 1;
            } else if (c.n == 2) {
                if (gear[1][2] != gear[2][6]) moves[1] = c.d == 1 ? -1 : 1;
                if (gear[2][2] != gear[3][6]) moves[3] = c.d == 1 ? -1 : 1;
                if (moves[1] != 0 && gear[0][2] != gear[1][6]) moves[0] = moves[1] == 1 ? -1 : 1;
            } else {
                if (gear[2][2] != gear[3][6]) moves[2] = c.d == 1 ? -1 : 1;
                if (moves[2] != 0 && gear[1][2] != gear[2][6]) moves[1] = moves[2] == 1 ? -1 : 1;
                if (moves[1] != 0 && gear[0][2] != gear[1][6]) moves[0] = moves[1] == 1 ? -1 : 1;
            }
            rotate();
        }

        int ans = 0;
        if (gear[0][0] == 1) ans += 1;
        if (gear[1][0] == 1) ans += 2;
        if (gear[2][0] == 1) ans += 4;
        if (gear[3][0] == 1) ans += 8;

        return ans;
    }

    private static void rotate() {
        for (int i = 0; i < 4; i++) {
            int move = moves[i];
            if (move == 1) {
                int tmp = gear[i][7];
                for (int j = 7; j > 0; j--) {
                    gear[i][j] = gear[i][j-1];
                }
                gear[i][0] = tmp;
            } else if (move == -1) {
                int tmp = gear[i][0];
                for (int j = 0; j < 7; j++) {
                    gear[i][j] = gear[i][j+1];
                }
                gear[i][7] = tmp;
            }
        }
    }

    static class Command {
        int n, d;

        public Command(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }
}