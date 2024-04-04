package edu.ssafy.im.Test.HSAT;

import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static char[] cctv;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        cctv = br.readLine().toCharArray();

        // 홀수인 경우 무조건 틀림
        if (N%2 == 0) sb.append(sol());
        else sb.append("No");

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static String sol() {
        Stack<Character> stack = new Stack<>();
        Stack<Character> wildCard = new Stack<>();

        boolean flag = false;
        for (char c : cctv) {
            if (c == '(') stack.push(c);
            else if (c == ')') {
                if (!stack.isEmpty()) stack.pop();
                else {
                    if (!wildCard.isEmpty()) wildCard.pop();
                    else {
                        flag = true;
                        break;
                    }
                }
            } else {
                wildCard.push(c);
            }
        }

        if (!flag) {
            if (stack.size() > wildCard.size()) flag = true;
        }

        if (flag) return "No";
        else return "Yes";
    }
}

