package 아더;

import java.util.Stack;

public class INF_괄호문자제거 {

    public static void main(String[] args) {
        String s = "(A(BC)D)EF(G(H)(IJ)K)LM(N)";
        // 답 : EFLM

        INF_괄호문자제거 inf_괄호문자제거 = new INF_괄호문자제거();
        System.out.println(inf_괄호문자제거.solution(s));
    }

    private String solution(String s) {
        String answer = "";
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                while (stack.peek() != '(') {
                    stack.pop();
                }
                stack.pop();
                continue;
            }
            stack.push(s.charAt(i));
        }

        for (Character character : stack) {
            answer += character;
        }

        return answer;
    }
}
