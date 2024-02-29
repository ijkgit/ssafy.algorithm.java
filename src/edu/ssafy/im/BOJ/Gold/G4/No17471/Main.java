package edu.ssafy.im.BOJ.Gold.G4.No17471;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

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

		for (int i = 0; i < graph.size(); i++) {
			System.out.println(i + " : " + graph.get(i));
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
			permutation(0, 0);
		}
		ans = ans == Integer.MAX_VALUE ? -1 : ans;
	}
	
	// 0 3 , 1 2 4 5
	private static boolean check(int[] a) {
		int v = 0, cnt = 0;
		for (int i : a) {
			for (int j = 0; j < graph.get(i).size(); j++) {
				for (int k : a) {
					if (k == graph.get(i).get(j) && (v & (1 << k)) == 0) {
						v |= 1 << k;
						cnt++;
					}
				}
			}
		}	
		
		if (cnt == a.length) return true;
		
		return false;
	}

// https://www.acmicpc.net/board/view/54133
	private static void permutation(int k, int v) {
		if (k == s.length) {
			makeR();
//			System.out.println(Arrays.toString(s));
//			System.out.println(Arrays.toString(r));
			if (check(s) && check(r)) {
				System.out.println(Arrays.toString(s));
				System.out.println(Arrays.toString(r));
				int s1 = 0, s2 = 0;
				for (int val : s) s1 += num[val];
				for (int val : r) s2 += num[val];
				System.out.println(Math.abs(s1 - s2));
				ans = Math.min(ans, Math.abs(s1 - s2));
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((v & (1 << i)) == 0) {
				s[k] = i;
				permutation(k + 1, v |= 1 << i);
			}
		}
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