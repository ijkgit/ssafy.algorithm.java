package edu.ssafy.im.BOJ.S3.No15649;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	int[] arr;
	int[] sel;
	boolean[] visited;
	StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		new Main().sol();
	}

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		arr = new int[n];
		sel = new int[m];
		visited = new boolean[n];
		for (int i = 1; i <= arr.length; i++) {
			arr[i-1] = i;
		}
		
		recursive(0);
		System.out.print(sb);
	}
	
	private void recursive(int m) {
		// basis part
		if (m == sel.length) {
			for(int s : sel)
				sb.append(s).append(" ");
			sb.append("\n");
			return;
		}
			
		// inductive part
		for (int i = 0; i < arr.length; i++) {
			if(!visited[i]) {
				visited[i] = true;
				sel[m] = arr[i];
				recursive(m+1);
				visited[i] = false;
			}
		}
	}
}
