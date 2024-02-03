package edu.ssafy.im.BOJ.Gold.G5.No2493;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		new Main().sol();
	}

	private void sol() throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		Stack<Tower> stack = new Stack<>();
		for (int i = 1; i <= n; i++) {
			int h = Integer.parseInt(st.nextToken());
			if(stack.isEmpty()) {
				sb.append("0 ");
				stack.push(new Tower(i, h));
			} else {
				while (true) {
					if (stack.isEmpty()) {
						sb.append("0 ");
						stack.push(new Tower(i, h));
						break;
					}

					Tower t = stack.peek();

					if(t.height > h) {
						sb.append(t.index + " ");
						stack.push(new Tower(i, h));
						break;
					} else {
						stack.pop();
					}
				}
			}
		}
		System.out.print(sb);
	}

	class Tower {
		int index;
		int height;

		public Tower(int index, int height) {
			this.index = index;
			this.height = height;
		}
	}
}