package edu.ssafy.im.BOJ.Gold.G5.No2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	StringBuilder sb = new StringBuilder();
	int[] arr, ans;
	int n;

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().sol();
	}

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		n = Integer.parseInt(br.readLine());
		String input = br.readLine();
		StringTokenizer st = new StringTokenizer(input);
		arr = new int[n];
		ans = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		recursive(0, 0);
		
		for (int i = 0; i < ans.length; i++) {
			System.out.print(ans[i] + " ");
		}
	}

	private void recursive(int i, int j) {
		// basis part
		if (i == n)
			return;

		if (j < 0) {
			ans[i] = 0;
			return;
		}

		if (arr[i] < arr[j]) {
			ans[i] = j+1;
			recursive(i + 1, i);
			return;
		}
		// inductive part
		recursive(i, j - 1);
		
		if(ans[i] == 0)
			recursive(i + 1, i);
	}
	// 탑
}
