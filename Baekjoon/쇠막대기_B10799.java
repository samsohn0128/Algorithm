package com.company;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;

public class 쇠막대기_B10799 {
    private static Stack<Character> stack = new Stack<>();

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        int answer = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(') {
                if (input.charAt(i + 1) == ')') {
                    answer += stack.size();
                    i++;
                } else {
                    stack.push(input.charAt(i));
                }
            } else {
                answer++;
                stack.pop();
            }
        }
        System.out.println(answer);
    }
}