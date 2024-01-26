package edu.ssafy.im.BOJ.No16234;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] graph;
	static int[][] direction = {{
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String input = br.readLine();	
		StringTokenizer st = new StringTokenizer(input);
		
		int n = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());
		int r = Integer.parseInt(st.nextToken());
		
		graph = new int[n][n];
		
		for (int x = 0; x < n; x++) {
			input = br.readLine();
			st = new StringTokenizer(input);
			for (int y = 0; y < n; y++) {
				graph[x][y] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	public static void dfs(int x, int y) {
		
	}
	
	
}
