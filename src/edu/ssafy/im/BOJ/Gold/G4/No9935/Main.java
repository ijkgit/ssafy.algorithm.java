package edu.ssafy.im.BOJ.Gold.G4.No9935;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

public class Main {
    private static char[] s;
    private static char[] b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        s = br.readLine().toCharArray();
        b = br.readLine().toCharArray();

        sb.append(sol());
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static String sol() {
        Stack<Character> stack = new Stack<>();

        for (char c : s) {
            stack.push(c);

            if (stack.size() < b.length) continue;

            boolean flag = true;
            for (int j = 0; j < b.length; j++) {
                if (stack.get(stack.size() - b.length + j) != b[j]) {
                    flag = false;
                    break;
                }
            }

            if (flag) for (char t : b) stack.pop();
        }

        StringBuilder sb = new StringBuilder();
        for(char c : stack) sb.append(c);
        return stack.isEmpty() ? "FRULA" : sb.toString();
    }
}
