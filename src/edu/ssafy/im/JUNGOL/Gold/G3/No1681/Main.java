package edu.ssafy.im.JUNGOL.Gold.G3.No1681;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	int[][] graph;
	int ans = Integer.MAX_VALUE;
	int[] sel;

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().sol();
	}

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		graph = new int[n][n];
		StringTokenizer st;
		for (int i = 0; i < graph.length; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < graph.length; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		sel = new int[n];
		dfs(1, 0, 0);

		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void dfs(int k, int visited, int sum) {
		if (sum > ans) return;
		
		if (k == graph.length) {
			if (graph[sel[k-1]][0] == 0) {
				return;
			}
			else {
				sum += graph[sel[k-1]][0];
			}
			ans = Math.min(ans, sum);
			return;
		}
	
		for (int i = 1; i < graph.length; i++) {
			if ((visited & (1 << i)) == 0 && graph[sel[k-1]][i] != 0) {
				sel[k] = i;
				sum += graph[sel[k-1]][sel[k]];
				dfs(k + 1, visited | (1 << i), sum);
				sum -= graph[sel[k-1]][sel[k]];
			}
		}
	}
}