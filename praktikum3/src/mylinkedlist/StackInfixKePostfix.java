package mylinkedlist;

import mylinkedlist.SingleList;

public class StackInfixKePostfix {
    private static int prioritas(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }

    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        SingleList<Character> stack = new SingleList<>();

        for (char ch : expression.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            } else if (ch == '(') {
                stack.pushS(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.head.data != '(') {
                    result.append(stack.remove());
                }
                stack.pop();
            } else {
                while (!stack.isEmpty() && prioritas(ch) <= prioritas(stack.head.data)) {
                    result.append(stack.remove());
                }
                stack.pushS(ch);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.remove());
        }
        return result.toString();
    }

    public static void main(String[] args) {
        String infix = "(a+b*(c/(d-e)))+f/g";
        System.out.println("Infix : " + infix);
        String postfix = infixToPostfix(infix);
        System.out.println("Hasil Postfix : " + postfix);
    }
}
