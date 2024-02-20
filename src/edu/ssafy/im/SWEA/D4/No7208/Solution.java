package edu.ssafy.im.SWEA.D4.No7208;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Solution().io();
	}

	private int n;
	private int[] arr;
	private List<ArrayList<Integer>> graph;
	private int ans = 0;

	private void io() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			n = Integer.parseInt(br.readLine());

			arr = new int[n];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < arr.length; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			graph = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				graph.add(new ArrayList<Integer>());
			}
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					if (st.nextToken().charAt(0) == '1')
						graph.get(i).add(j);
				}
			}
			dfs(0);
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void dfs(int idx) {
		for (int j = 0; j < graph.get(idx).size(); j++) {
			if (arr[idx] == arr[graph.get(idx).get(j)]) {
				for (int i = 0; i < arr.length; i++) {
					arr[graph.get(idx).get(j)] = (arr[graph.get(idx).get(j)]+1)%4;
				}
				ans ++;
				dfs(j+1);
			}
		}
	}
}
