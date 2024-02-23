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
	ArrayList<ArrayList<Integer>> graph;

	public static void main(String[] args) throws IOException {
		new Main().io();
	}

	private int n;
	private int[] arr;
	private int ans = Integer.MAX_VALUE;

	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());

		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			graph.add(new ArrayList<>());
		}

		for (int from = 0; from < n; from++) {
			st = new StringTokenizer(br.readLine());
			int e = Integer.parseInt(st.nextToken());
			for (int j = 0; j < e; j++) {
				int to = Integer.parseInt(st.nextToken()) - 1; // zero base
				graph.get(from).add(to);
				graph.get(to).add(from);
			}
		}

		sol();
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void sol() {
		for (int i = 1; i < n; i++) {
			permutation(0, 0, new int[i]);
		}
	}

	private void permutation(int k, int v, int[] sel) {
		if (k == sel.length) {
			System.out.println("sel : " + Arrays.toString(sel));
			int[] rev = rev(sel);
			if (cal(sel, rev) > ans)
				return;
			if (dfs(0, 1 << sel[0], sel) && dfs(0, 1 << rev[0], rev)) {
				ans = Math.min(ans, cal(sel, rev));

				System.out.println("ans : " + ans);
			}
			return;
		}

		for (int i = 0; i < n; i++) {
			if ((v & (1 << i)) == 0) {
				sel[k] = i;
				permutation(k + 1, v |= 1 << i, sel);
			}
		}
	}

	private int[] rev(int[] sel) {
		int rev[] = new int[n - sel.length];
		int idx = 0;
		for (int i = 0; i < n; i++) {
			boolean flag = false;
			for (int j = 0; j < sel.length; j++) {
				if (i == sel[j])
					flag = true;
			}
			if (!flag) {
				rev[idx] = i;
				idx++;
			}
		}

		return rev;
	}

	private boolean dfs(int k, int v, int[] sel) {
		if (k == sel.length - 1) {
			System.out.println("dfs : " + Arrays.toString(sel));
			return true;
		}

		for (int s = 0; s < sel.length; s++) {
		for (int j = 0; j < graph.get(sel[k]).size(); j++) {
			
				if (sel[s] == graph.get(sel[k]).get(j) && (v & (1 << graph.get(sel[k]).get(j))) == 0) {
					return dfs(k + 1, v | 1 << graph.get(sel[k]).get(j), sel);
				}
			
		}
		}

		return false;
	}

	private int cal(int sel[], int rev[]) {
		int s1 = 0, s2 = 0;
		for (int s : sel)
			s1 += arr[s];
		for (int r : rev)
			s2 += arr[r];

		return Math.abs(s1 - s2);
	}
}