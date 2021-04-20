package cn.zb.study.alg.stack;

import java.util.Stack;

/**
 * @author zb
 * @date 2021-04-20
 * @description 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串s ，判断字符串是否有效
 * 有效字符串需满足：
 * 1.左括号必须用相同类型的右括号闭合
 * 2.左括号必须以正确的顺序闭合
 */
public class ValidBracketsSolution {

    /**
     * 是否是有效的括号
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        boolean valided = false;

        for (int i = 0; i < s.length(); i++){
            char ch = s.charAt(i);
            if (ch == '(' || ch == '{' || ch == '['){
                stack.push(ch);
            }

            if ((ch == ')' || ch == '}' || ch == ']') && stack.isEmpty()){
                valided = false;
            }

            if (ch == ')' && !stack.isEmpty()){
                char left = stack.pop();
                if (left == '('){
                    valided = true;
                } else {
                    valided = false;
                }
            }
            if (ch == '}' && !stack.isEmpty()){
                char left = stack.pop();
                if (left == '{'){
                    valided = true;
                } else {
                    valided = false;
                }
            }
            if (ch == ']' && !stack.isEmpty()){
                char left = stack.pop();
                if (left == '['){
                    valided = true;
                } else {
                    valided = false;
                }
            }
        }

        if (!stack.isEmpty()){
            valided = false;
        }
        return valided;
    }

    public static void main(String[] args) {
        String s1 = "()";
        String s2 = "()[]{}";
        String s3 = "(]";
        String s4 = "([)]";
        String s5 = "{[]}";
        String s6 = ")";
        String s7 = "({{{{}}}))";
        boolean result = new ValidBracketsSolution().isValid(s7);
        System.out.print(result);
    }
}
