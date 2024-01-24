package edu.ssafy.im.BOJ.No2999;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String input = br.readLine();

        int n = input.length();

        int r = 1;
        int c = n;
        for(int i = (int) Math.round(n / 2.0); i > 0; i--) {
            if (n%i == 0) {
                if (n/i <= i) {
                    r = n/i;
                    c = i;
                }
                else {
                    break;
                }
            }
        }

        char[][] graph = new char[r][c];

        int i = 0;
        for (int y = 0; y < c; y++) {
            for (int x = 0; x < r; x++) {
                graph[x][y] = input.charAt(i);
                i++;
            }
        }

        for (int x = 0; x < r; x++) {
            for (int y = 0; y < c; y++) {
                sb.append(graph[x][y]);
            }
        }

        System.out.print(sb);
    }
}
