package edu.ssafy.im.SWEA.D4.No5672s;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static String res;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            sb.append("#").append(tc).append(" ");
            Deque<Character> dq = new LinkedList<>();
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                dq.offer(br.readLine().charAt(0));
            }
            makeCandidate(dq, new char[N], 0);
            sb.append(res).append("\n");
        }
        System.out.println(sb);
    }

    private static void makeCandidate(Deque<Character> dq, char[] chars, int k) {
        if (dq.isEmpty()) {
            res = String.valueOf(chars);
            return;
        }
        if (dq.peekFirst().compareTo(dq.peekLast()) < 0) {
            addFist(dq, chars, k);
            return;
        }
        if (dq.peekFirst().equals(dq.peekLast())) {
            Object[] copy = dq.toArray();
            for (int i = 0; i < dq.size() / 2; i++) {
                if (((Character) copy[i]).compareTo((Character) copy[dq.size() - i - 1]) < 0) {
                    addFist(dq, chars, k);
                    return;
                }
            }
            addLast(dq, chars, k);
            return;
        }
        addLast(dq, chars, k);
    }

    private static void addLast(Deque<Character> dq, char[] chars, int k) {
        chars[k] = dq.peekLast();
        dq.pollLast();
        makeCandidate(dq, chars, k + 1);
        dq.offerLast(chars[k]);
    }

    private static void addFist(Deque<Character> dq, char[] chars, int k) {
        chars[k] = dq.peekFirst();
        dq.pollFirst();
        makeCandidate(dq, chars, k + 1);
        dq.offerFirst(chars[k]);
    }
}