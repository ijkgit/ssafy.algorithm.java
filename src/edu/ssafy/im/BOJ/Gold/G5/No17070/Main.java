package edu.ssafy.im.BOJ.Gold.G5.No17070;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private final static int[][][] direction = { { { 0, 1 }, { 1, 1 } }, { { 1, 0 }, { 1, 1 } },
			{ { 0, 1 }, { 1, 1 }, { 1, 0 } } };

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().sol();
	}

	private int n;
	private int[][] graph;

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		graph = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < direction.length; j++) {
				graph[i][j] = Integer.parseInt(st.nextToken());
				if (graph[i][j] == 1)
					graph[i][j] = -1;
			}
		}

		dfs();
	}

	private void dfs() { 
		for (int i = 0; i < direction.length; i++) {
			
		}
	}
}
