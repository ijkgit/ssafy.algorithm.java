package edu.ssafy.im.BOJ.Platinum.No5373;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static char[][][] cube = new char[6][3][3];
    private static char[][][] copy = new char[6][3][3];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int TC = Integer.parseInt(br.readLine());
        for (int T = 0; T < TC; T++) {
            init();
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                char[] order = st.nextToken().toCharArray();
                rotate(order[0], order[1]);
//                System.out.println(order);
//                print();
            }
            sb.append(cube[0][2][2]).append(cube[0][2][1]).append(cube[0][2][0]).append("\n");
            sb.append(cube[0][1][2]).append(cube[0][1][1]).append(cube[0][1][0]).append("\n");
            sb.append(cube[0][0][2]).append(cube[0][0][1]).append(cube[0][0][0]).append("\n");
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }

    private static void rotate(char s, char d) {
        copy();
        int h = -1;
        if (s == 'U') { h = 0; up(d); }
        else if (s == 'D') { h = 5; down(d); }
        else if (s == 'F') { h = 1; front(d); }
        else if (s == 'B') { h = 3; back(d); }
        else if (s == 'R') { h = 4; right(d); }
        else if (s == 'L') { h = 2; left(d); }

        rotateSelf(h, d);
    }

    private static void rotateSelf(int h, int d) {
        if (d == '+') {
            cube[h][0][0] = copy[h][2][0];
            cube[h][0][1] = copy[h][1][0];
            cube[h][0][2] = copy[h][0][0];
            cube[h][1][0] = copy[h][2][1];
            cube[h][1][1] = copy[h][1][1];
            cube[h][1][2] = copy[h][0][1];
            cube[h][2][0] = copy[h][2][2];
            cube[h][2][1] = copy[h][1][2];
            cube[h][2][2] = copy[h][0][2];
        }
        else {
            cube[h][0][0] = copy[h][0][2];
            cube[h][0][1] = copy[h][1][2];
            cube[h][0][2] = copy[h][2][2];
            cube[h][1][0] = copy[h][0][1];
            cube[h][1][1] = copy[h][1][1];
            cube[h][1][2] = copy[h][2][1];
            cube[h][2][0] = copy[h][0][0];
            cube[h][2][1] = copy[h][1][0];
            cube[h][2][2] = copy[h][2][0];
        }
    }

    private static void up(char d) {
        int[] dir;
        if (d == '+') dir = new int[]{4, 1, 2, 3};
        else dir = new int[]{2, 3, 4, 1};

        for (int h = 1; h <= 4; h++) {
            for (int y = 0; y < 3; y++) {
                cube[h][0][y] = copy[dir[h - 1]][0][y];
            }
        }
    }

    private static void down(char d) {
        int[] dir;
        if (d == '+') dir = new int[]{2, 3, 4, 1};
        else dir = new int[]{4, 1, 2, 3};

        for (int h = 1; h <= 4; h++) {
            for (int y = 0; y < 3; y++) {
                cube[h][2][y] = copy[dir[h - 1]][2][y];
            }
        }
    }

    private static void front(char d) {
        if (d == '+') {
            cube[0][0][0] = copy[2][0][2];
            cube[0][0][1] = copy[2][1][2];
            cube[0][0][2] = copy[2][2][2];
            cube[2][0][2] = copy[5][0][0];
            cube[2][1][2] = copy[5][0][1];
            cube[2][2][2] = copy[5][0][2];
            cube[5][0][0] = copy[4][2][0];
            cube[5][0][1] = copy[4][1][0];
            cube[5][0][2] = copy[4][0][0];
            cube[4][0][0] = copy[0][0][2];
            cube[4][1][0] = copy[0][0][1];
            cube[4][2][0] = copy[0][0][0];
        } else {
            cube[0][0][0] = copy[4][2][0];
            cube[0][0][1] = copy[4][1][0];
            cube[0][0][2] = copy[4][0][0];
            cube[2][0][2] = copy[0][0][0];
            cube[2][1][2] = copy[0][0][1];
            cube[2][2][2] = copy[0][0][2];
            cube[5][0][0] = copy[2][0][2];
            cube[5][0][1] = copy[2][1][2];
            cube[5][0][2] = copy[2][2][2];
            cube[4][0][0] = copy[5][0][2];
            cube[4][1][0] = copy[5][0][1];
            cube[4][2][0] = copy[5][0][0];
        }
    }

    private static void back(char d) {
        if (d == '+') {
            cube[0][2][0] = copy[4][2][2];
            cube[0][2][1] = copy[4][1][2];
            cube[0][2][2] = copy[4][0][2];
            cube[2][0][0] = copy[0][2][0];
            cube[2][1][0] = copy[0][2][1];
            cube[2][2][0] = copy[0][2][2];
            cube[5][2][0] = copy[2][0][0];
            cube[5][2][1] = copy[2][1][0];
            cube[5][2][2] = copy[2][2][0];
            cube[4][2][2] = copy[5][2][0];
            cube[4][1][2] = copy[5][2][1];
            cube[4][0][2] = copy[5][2][2];
        } else {
            cube[0][2][0] = copy[2][0][0];
            cube[0][2][1] = copy[2][1][0];
            cube[0][2][2] = copy[2][2][0];
            cube[2][0][0] = copy[5][2][0];
            cube[2][1][0] = copy[5][2][1];
            cube[2][2][0] = copy[5][2][2];
            cube[5][2][0] = copy[4][2][2];
            cube[5][2][1] = copy[4][1][2];
            cube[5][2][2] = copy[4][0][2];
            cube[4][2][2] = copy[0][2][0];
            cube[4][1][2] = copy[0][2][1];
            cube[4][0][2] = copy[0][2][2];
        }
    }

    private static void left(char d) {
        if (d == '+') {
            cube[0][0][2] = copy[3][0][2];
            cube[0][1][2] = copy[3][1][2];
            cube[0][2][2] = copy[3][2][2];
            cube[3][0][2] = copy[5][2][0];
            cube[3][1][2] = copy[5][1][0];
            cube[3][2][2] = copy[5][0][0];
            cube[5][2][0] = copy[1][2][0];
            cube[5][1][0] = copy[1][1][0];
            cube[5][0][0] = copy[1][0][0];
            cube[1][2][0] = copy[0][0][2];
            cube[1][1][0] = copy[0][1][2];
            cube[1][0][0] = copy[0][2][2];
        } else {
            cube[0][0][2] = copy[1][2][0];
            cube[0][1][2] = copy[1][1][0];
            cube[0][2][2] = copy[1][0][0];
            cube[3][0][2] = copy[0][0][2];
            cube[3][1][2] = copy[0][1][2];
            cube[3][2][2] = copy[0][2][2];
            cube[5][2][0] = copy[3][0][2];
            cube[5][1][0] = copy[3][1][2];
            cube[5][0][0] = copy[3][2][2];
            cube[1][2][0] = copy[5][2][0];
            cube[1][1][0] = copy[5][1][0];
            cube[1][0][0] = copy[5][0][0];
        }
    }

    private static void right(char d) {
        if (d == '+') {
            cube[0][0][0] = copy[1][2][2];
            cube[0][1][0] = copy[1][1][2];
            cube[0][2][0] = copy[1][0][2];
            cube[1][2][2] = copy[5][2][2];
            cube[1][1][2] = copy[5][1][2];
            cube[1][0][2] = copy[5][0][2];
            cube[5][2][2] = copy[3][0][0];
            cube[5][1][2] = copy[3][1][0];
            cube[5][0][2] = copy[3][2][0];
            cube[3][0][0] = copy[0][0][0];
            cube[3][1][0] = copy[0][1][0];
            cube[3][2][0] = copy[0][2][0];
        } else {
            cube[0][0][0] = copy[3][0][0];
            cube[0][1][0] = copy[3][1][0];
            cube[0][2][0] = copy[3][2][0];
            cube[3][0][0] = copy[5][2][2];
            cube[3][1][0] = copy[5][1][2];
            cube[3][2][0] = copy[5][0][2];
            cube[5][2][2] = copy[1][2][2];
            cube[5][1][2] = copy[1][1][2];
            cube[5][0][2] = copy[1][0][2];
            cube[1][2][2] = copy[0][0][0];
            cube[1][1][2] = copy[0][1][0];
            cube[1][0][2] = copy[0][2][0];
        }
    }

    private static void init() {
        // 윗 앞 왼 뒷 오 아
        char[] color = {'w', 'r', 'g', 'o', 'b', 'y'};
        for (int h = 0; h < 6; h++) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    cube[h][x][y] = color[h];
                    copy[h][x][y] = color[h];
                }
            }
        }
    }

    private static void copy() {
        for (int h = 0; h < 6; h++) {
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    copy[h][x][y] = cube[h][x][y];
                }
            }
        }
    }

    private static void print() {
        StringBuilder sb = new StringBuilder();
        sb.append(cube[0][2][2]).append(cube[0][2][1]).append(cube[0][2][0]).append("\n");
        sb.append(cube[0][1][2]).append(cube[0][1][1]).append(cube[0][1][0]).append("\n");
        sb.append(cube[0][0][2]).append(cube[0][0][1]).append(cube[0][0][0]).append("\n");
        System.out.println(sb);
    }
}