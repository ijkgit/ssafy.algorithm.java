package edu.ssafy.im.SWEA.D4.No1218;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {
    int n;
    char[] chars;

    public static void main(String[] args) throws IOException {
        new Solution().io();
    }

    private void io() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        for (int t = 1; t <= 10; t++) {
            n = Integer.parseInt(br.readLine());
            chars = new char[n];

            String string = br.readLine();
            int ans = sol(string);

            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private int sol(String string) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            char c = string.charAt(i);

            if (c == '{' || c == '[' || c == '(' || c == '<') {
                stack.push(c);
            } else {
                switch (c) {
                    case '}':
                        if (stack.peek() == '{') {
                            stack.pop();
                            break;
                        } else {
                            return 0;
                        }
                    case ']':
                        if (stack.peek() == '[') {
                            stack.pop();
                            break;
                        } else {
                            return 0;
                        }

                    case ')':
                        if (stack.peek() == '(') {
                            stack.pop();
                            break;
                        } else {
                            return 0;
                        }
                    case '>':
                        if (stack.peek() == '<') {
                            stack.pop();
                            break;
                        } else {
                            return 0;
                        }
                }
            }
        }


        if (!stack.isEmpty())
            return 0;

        return 1;
    }
}
