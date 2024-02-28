package edu.ssafy.im.BOJ.Silver.S3.No15652;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		new Main().sol();
	}

	private int N;
	private int M;
	private int[] sel;
	private StringBuilder sb = new StringBuilder();

	private void sol() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		sel = new int[M];

		recursion(0, 0);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private void recursion(int k, int idx) {
		if (k == sel.length) {
			for (int s : sel) sb.append(s).append(" ");
			sb.append("\n");
			return;
		}
		for (int i = idx; i < N; i++) {
			sel[k] = i + 1;
			recursion(k + 1, i);
		}
	}
}
