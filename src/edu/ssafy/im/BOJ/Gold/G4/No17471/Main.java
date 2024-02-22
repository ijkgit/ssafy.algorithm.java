package edu.ssafy.im.BOJ.Gold.G4.No17471;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().io();
	}

	private int n;
	private int[] arr;
	private int[][] graph;
	private int[] sel;
	private int ans = Integer.MAX_VALUE;
	private static int TOTAL = 0;

	private void io() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(br.readLine());

		arr = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			TOTAL += arr[i];
		}

		graph = new int[n][n]; // 중복 입력 처리를 위한 인접 행렬 생성
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken()) - 1; // zero base
			while (st.hasMoreTokens()) {
				int to = Integer.parseInt(st.nextToken()) - 1;
				graph[from][to] = 1;
				graph[to][from] = 1;
			}
		}

		for (int i = 0; i < arr.length; i++) {
			System.out.println(Arrays.toString(graph[i]));
		}

		sol();
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void sol() {
		for (int i = 1; i < n; i++) {
			sel = new int[i];
			permutation(0, 0);
		}
		
		ans = ans == Integer.MAX_VALUE ? -1 : ans;
	}

	private void permutation(int k, int v) {
		if (k == sel.length) {
			System.out.println(Arrays.toString(sel));
			check();
			return;
		}

		for (int i = 0; i < n; i++) {
			if ((v & (1 << i)) == 0) {
				sel[k] = i;
				permutation(k + 1, v |= 1 << i);
			}
		}
	}

	private void check() {
		if (sel.length == 1 || sel.length == n - 1) {
			int sum = 0;
			for (int i = 0; i < sel.length; i++) {
				sum += arr[sel[i]];
			}
			ans = Math.min(ans, TOTAL - sum);
		} else {
			dfs(sel[0], 1 << sel[0], 1);
		}
	}
	
	private void dfs(int i, int v, int depth) {
		if (depth == sel.length) {
			int sum = 0;
			for (int j = 0; j < sel.length; j++) {
				sum += arr[sel[j]];
			}
			ans = Math.min(ans, TOTAL - sum);
			System.out.println(Arrays.toString(sel));
			return;
		}
		
		for (int j = 0; j < sel.length; j++) {
			if (graph[i][sel[j]] != 0) {
				if ((v & (1 << sel[j])) == 0) {
					dfs(j, v |= 1 << sel[j], depth+1);
				}
			}
		}
	}
}
