package edu.ssafy.im.BOJ.Gold.G4.No17471;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	private static int N;
	private static int[] num;
	private static ArrayList<ArrayList<Integer>> graph;
	private static int[] s;
	private static int[] r;
	private static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		num = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList<>();
		for (int i = 0; i < N; i++)
			graph.add(new ArrayList<>());

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int E = Integer.parseInt(st.nextToken());
			for (int j = 0; j < E; j++) {
				int V = Integer.parseInt(st.nextToken()) - 1;
				graph.get(i).add(V);
			}
		}

		sol();
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void sol() {
		for (int i = 1; i <= N / 2; i++) {
			s = new int[i];
			r = new int[N - i];
			combination(0, 0);
		}
		ans = ans == Integer.MAX_VALUE ? -1 : ans;
	}
	
	// 0 1, 2
	private static boolean check(int[] a) {
		Queue<Integer> q = new ArrayDeque<>();
		int v = 1 << a[0];
		q.offer(a[0]);
		int cnt = 1;

		while (!q.isEmpty()) {
			int cur = q.poll();
			for (int i = 0; i < graph.get(cur).size(); i++) {
				int next = graph.get(cur).get(i);
				if(contains(a, next) && (v & (1 << next)) == 0) {
					q.offer(next);
					v |= 1 << next;
					cnt++;
				}
			}
		}

		if (cnt == a.length) return true;
		else return false;
	}

	private static boolean contains(int[] a, int next) {
		for (int v : a) if (v == next) return true;
		return false;
	}
	
	private static void combination(int k, int v) {
		if (k == s.length) {
			makeR();
			if (check(s) && check(r)) ans = Math.min(ans, getDiff());
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((v & (1 << i)) == 0) {
				s[k] = i;
				combination(k + 1, v |= 1 << i);
			}
		}
	}

	private static int getDiff() {
		int s1 = 0, s2 = 0;
		for (int val : s) s1 += num[val];
		for (int val : r) s2 += num[val];
		return Math.abs(s1 - s2);
	}

	private static void makeR() {
		int idx = 0;
		for (int i = 0; i < N; i++) {
			boolean f = false;
			for (int j : s) {
				if (i == j) {
					f = true;
					break;
				}
			}
			if (!f)
				r[idx++] = i;
		}
	}
}