package arithmetic;

import java.util.Stack;

public class MinStack{
    public Stack<Integer> stackData = new Stack<>();

    public Stack<Integer> stackMin = new Stack<>();

    public void push(Integer value) {
        if (stackMin.isEmpty()) {
            stackMin.push(value);
        } else if (value <= getMin()) {
            stackMin.push(value);
        } else {
            stackMin.push(getMin());
        }
        stackData.push(value);
    }

    public Integer pop() {
        if (stackData.isEmpty()) {
            System.out.println("the stack is empty");
            return null;
        }
        stackMin.pop();
        return stackData.pop();
    }

    public Integer getMin() {
        if (stackMin.isEmpty()) {
            System.out.println("the stack is empty");
            return null;
        }
        return stackMin.peek();
    }

    //测试
    public static void main(String[] args) {
        MinStack t6 = new MinStack();
        t6.push(5);
        t6.push(4);
        t6.push(1);
        t6.push(9);
        t6.push(3);
        System.out.println(t6.getMin());
        //连续弹出两次
        t6.pop();
        t6.pop();
        System.out.println(t6.getMin());
    }
}

