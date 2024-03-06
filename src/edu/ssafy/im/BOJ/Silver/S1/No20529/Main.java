package edu.ssafy.im.BOJ.Silver.S1.No20529;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	private static int T;
	private static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			// 입력 받기
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());

			// 가지치기 : 비둘기집 원리
			// 33명 이상일 경우 무조건 세명이 겹칠 수 밖에 없다.
			if (N > 32) {
				sb.append(0).append("\n");
				continue;
			}

			String[] mbti = new String[N];
			for (int i = 0; i < N; i++) {
				mbti[i] = st.nextToken();
			}

			int ans = Integer.MAX_VALUE;
			L:for (int i = 0; i < N - 2; i++) {
				for (int j = i + 1; j < N - 1; j++) {
					for (int k = j + 1; k < N; k++) {
						int sum = 0;
						for (int l = 0; l < 4; l++) {
							if (mbti[i].charAt(l) != mbti[j].charAt(l)) sum++;
							if (mbti[j].charAt(l) != mbti[k].charAt(l)) sum++;
							if (mbti[k].charAt(l) != mbti[i].charAt(l)) sum++;
						}
						ans = Math.min(ans, sum);
						if (ans == 0) break L;
					}
				}
			}
			sb.append(ans).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}