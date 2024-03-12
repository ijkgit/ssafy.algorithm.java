package edu.ssafy.im.BOJ.Gold.G4.No9935;

import java.io.*;
import java.util.Arrays;

public class Main {
    private static char[] s;
    private static char[] b;
    private static int start;
    private static int end;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        s = br.readLine().toCharArray();
        b = br.readLine().toCharArray();

        int flag = -1;
        while(flag != start) {
            end = 0;
            flag = start;
            while(setStart()) {
                check();
            }
        }

        for (int i = 0; i < s.length; i++) {
            if (s[i] != '*') sb.append(s[i]);
        }

        if (sb.length() == 0) sb.append("FRULA");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    public static boolean setStart() {
        for (int i = end; i < s.length; i++) {
            if (s[i] == b[0]) {
                start = i;
                return true;
            }
        }
        return false;
    }

    public static void check() {
        end = start + b.length;

        for (int i = start, j = 0; i < end; i++, j++) {
            if (i >= s.length) return;

            if (s[i] == '*') {
                end++;
                j--;
            }
            else if (s[i] != b[j]) {
                end = i;
                return;
            }
        }
        for (int i = start; i < end; i++) {
            s[i] = '*';
        }
    }
}
