package edu.ssafy.im.BOJ.Silver.S3.No15657;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	private static int N;
	private static int M;
	private static int[] arr;
	private static int[] sel;
	private static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);

		sel = new int[M];
		combination(0, 0);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}

	private static void combination(int k, int v) {
		if (k == M) {
			for (int i = 0; i < M; i++)
				sb.append(sel[i]).append(" ");
			sb.append("\n");
			return;
		}

		for (int i = 0; i < N; i++) {
			if ((v & (1 << i)) == 0) {
				sel[k] = arr[i];
				combination(k + 1, v);
			}
			v |= 1 << i;
		}
	}
}
