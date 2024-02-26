package edu.ssafy.im.BOJ.Gold.G5.No17070;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private final static int[][] direction = {{1, 1}, {0, 1}, {1, 0}};

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().sol();
	}

	private int n;
	private int[][] map;

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		for (int i = 0; i < n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < direction.length; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					map[i][j] = -1;
			}
		}

		dfs(0, 1);
	}

	private void dfs(int x, int y) { 
		for (int d = 0; d < direction.length; d++) {
			boolean flag = false;
			if (d == 0) {
				for (int i = 0; i < direction.length; i++) {
					int nx = x + direction[i][0];
					int ny = y + direction[i][1];
					
					if (nx < 0 || ny < 0 || nx >= n || ny >= n) break;
					if (map[nx][ny] == -1) break;
					
					flag = true;
				}
			}
		}
	}
}
