package edu.ssafy.im.BOJ.Bronze.B3.No10162;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().sol();
	}

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int t = Integer.parseInt(br.readLine());
		if (t % 10 != 0)
			sb.append(-1);
		else
			sb.append(t / 300).append(" ").append((t % 300) / 60).append(" ").append(((t % 300) % 60) / 10);

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
