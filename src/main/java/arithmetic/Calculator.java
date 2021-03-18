package arithmetic;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Java实现的表达式求值算法（包括加减乘除以及括号运算）
 *
 * 一、表达式求值简单说明：
 *
 * 1、求值表达式主要包括加减乘除四种基本运算，其实表达式可以看做由一个个二元运算构成，前一个二元运算的结果作为后一个二元运算的输入。
 *
 * 举个例子： “1+2-4=”，“1+2”就是一个二元运算，1和2是操作数，+是运算符，它们的运算结果3作为下一个二元运算的输入，所以下一个二元运算是“3-4”，
 * 这样经过两次二元运算后得出结果-1，此时碰到表达式结束符号“=”，那么表达式运算结束，最终值为-1。当然有时候表达式不是以等号作为结束符号的，这种场景要特别注意下。
 *
 * 2、加减乘除四种运算符是有优先级的，乘法和除法同级，且比加法和减法优先级高，也就是说同级的运算顺序是从左到右，高优先级的二元运算优先执行。
 * 举个例子：“1+2*3=”，“2*3”这个二元运算因为乘法的优先级高，所以优先执行，执行结果作为+二元运算的第二个操作数。
 *
 * 3、括号运算其实相当于嵌套一个子表达式，而子表达式的优先级比括号外的加减乘数二元运算高，子表达式的计算结果作为主表达式的一个操作数。
 *
 * 4、由上面的说明可知，表达式有两个基本元素，那就是操作数和运算符，运算符一般占用一个字符，而操作数可能是1, 2, 3这样的个位整数，
 * 也可能是1.0, 1.89这样的浮点数，也可能是100, 189这样的多位整数。所以我们在从表达式解析出操作数时要完整的解析出整个操作数。
 *
 */
public class Calculator {

    /**
     *
     * 二、算法思想简单说明：
     *
     * 1、表达式由正则模式 "[0-9\\.+-/*()= ]+" 来校验合法性。
     *
     * 2、表达式的操作数将push到一个数值栈中，而运算符将push到运算符栈中，解析表达式时，采用逐个读取字符的形式，特别注意操作数是多位字符的场景，
     * 可以采用一个追加器将字符先缓存起来，当完整读取一个数值时再讲数值push到数值栈中。
     *
     * 3、当准备push到运算符栈的当前运算符，优先级同级于或低于栈顶运算符时，将触发一次二元运算，参与二元运算的为运算符栈的栈顶元素以及数值栈栈顶的两个操作数。
     * 二元运算得支持高精度运算，同时避免精度丢失问题。
     *
     * 举个例子： “2 * 3 - 1=”，在读取到运算符 “-” 前，运算符栈中已有元素[“*”]，数值栈中有元素[“3”、“2”]，因为运算符“-”的优先级比栈顶运算符“*”的优先级低，
     * 所以触发二元运算 “2 * 3”，相关操作数和运算符出栈，运算结果“6”作为新的操作数push到数值栈中。特别注意，此时如果运算符栈中栈顶还有元素，那么优先级比对还得继续，
     * 这是一个递归操作，直到运算符栈没有元素或者当前运算符“-”的优先级高于栈顶元素，那么当前运算符push到运算符栈，继续读取表达式的下一个元素。
     *
     *
     * 4、括号运算，相等于子表达式运算，当表达式解析到左括号时，将左括号push到运算符栈，当解析到右括号时，将递归运算整个子表达式的所有二元运算操作，直到碰到左括号才停止，
     * 此时子表达式的计算结果作为新的操作数push到数值栈中。
     *
     *   举个例子： “2 * (3 - 1*2)=”，在解析到右括号“)”前，运算符栈中已有元素[“*”，“-”，“(”，“*”]，数值栈已有元素[“2”、“1”、“3”、“2”]。
     *   【温馨提示，这里元素从左到右依次表示从栈顶到栈底，没有用更直观的图示，请见谅。】
     *
     *   当碰到右括号时，触发一次二元运算，即“1*2”，运算符栈的栈顶元素“*”出栈，数值栈的栈顶元素“2”和“1”分别出栈，二元运算得出结果“2”，结果值push到数值栈。
     *   此时运算符栈中已有元素[“-”，“(”，“*”]，数值栈已有元素[“2”、“3”、“2”]。
     *
     *   子表达式还没运算结束，继续递归触发二元运算，即“3-2”，运算符栈的栈顶元素“-”出栈，数值栈的栈顶元素“2”和“3”分别出栈，二元运算得出结果“1”，
     *   结果值push到数值栈。此时运算符栈中已有元素[“(”，“*”]，数值栈已有元素[“1”、“2”]。
     *
     *   继续读取运算符栈顶元素，发现是左括号“(”，此时栈顶元素出栈，而无需触发二元计算，此时运算符栈中已有元素[“*”]，数值栈已有元素[“1”、“2”]。
     *
     *   表达式继续解析，此时发现读取到等号“=”，递归触发表达式的所有二元运算，即“2*1”，得出最终结果2，计算结束。
     *
     * 5、浮点数比较是否相等，因为浮点数有精度原因，所以要用精度范围的方式，参考下面代码实现。
     *
     */


// 表达式字符合法性校验正则模式，静态常量化可以降低每次使用都要编译地消耗
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("[0-9\\.+-/*()= ]+");

    // 运算符优先级map
    private static final Map<String, Integer> OPT_PRIORITY_MAP = new HashMap<String, Integer>() {
        private static final long serialVersionUID = 6968472606692771458L;
        {
            put("(", 0);
            put("+", 2);
            put("-", 2);
            put("*", 3);
            put("/", 3);
            put(")", 7);
            put("=", 20);
        }
    };


    /**
     * 输入加减乘除表达式字符串，返回计算结果
     * @param expression 表达式字符串
     * @return 返回计算结果
     */
    public static double executeExpression(String expression) {
        // 非空校验
        if (null == expression || "".equals(expression.trim())) {
            throw new IllegalArgumentException("表达式不能为空！");
        }

        // 表达式字符合法性校验
        Matcher matcher = EXPRESSION_PATTERN.matcher(expression);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("表达式含有非法字符！");
        }

        Stack<String> optStack = new Stack<>(); // 运算符栈
        Stack<BigDecimal> numStack = new Stack<>(); // 数值栈，数值以BigDecimal存储计算，避免精度计算问题
        StringBuilder curNumBuilder = new StringBuilder(16); // 当前正在读取中的数值字符追加器

        // 逐个读取字符，并根据运算符判断参与何种计算
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            if (c != ' ') { // 空白字符直接丢弃掉
                if ((c >= '0' && c <= '9') || c == '.') {
                    curNumBuilder.append(c); // 持续读取一个数值的各个字符
                } else {
                    if (curNumBuilder.length() > 0) {
                        // 如果追加器有值，说明之前读取的字符是数值，而且此时已经完整读取完一个数值
                        numStack.push(new BigDecimal(curNumBuilder.toString()));
                        curNumBuilder.delete(0, curNumBuilder.length());
                    }

                    String curOpt = String.valueOf(c);
                    if (optStack.empty()) {
                        // 运算符栈栈顶为空则直接入栈
                        optStack.push(curOpt);
                    } else {
                        if (curOpt.equals("(")) {
                            // 当前运算符为左括号，直接入运算符栈
                            optStack.push(curOpt);
                        } else if (curOpt.equals(")")) {
                            // 当前运算符为右括号，触发括号内的字表达式进行计算
                            directCalc(optStack, numStack, true);
                        } else if (curOpt.equals("=")) {
                            // 当前运算符为等号，触发整个表达式剩余计算，并返回总的计算结果
                            directCalc(optStack, numStack, false);
                            return numStack.pop().doubleValue();
                        } else {
                            // 当前运算符为加减乘除之一，要与栈顶运算符比较，判断是否要进行一次二元计算
                            compareAndCalc(optStack, numStack, curOpt);
                        }
                    }
                }
            }
        }

        // 表达式不是以等号结尾的场景
        if (curNumBuilder.length() > 0) {
            // 如果追加器有值，说明之前读取的字符是数值，而且此时已经完整读取完一个数值
            numStack.push(new BigDecimal(curNumBuilder.toString()));
        }
        directCalc(optStack, numStack, false);
        return numStack.pop().doubleValue();
    }

    /**
     * 拿当前运算符和栈顶运算符对比，如果栈顶运算符优先级高于或同级于当前运算符，
     * 则执行一次二元运算（递归比较并计算），否则当前运算符入栈
     * @param optStack 运算符栈
     * @param numStack 数值栈
     * @param curOpt 当前运算符
     */
    public static void compareAndCalc(Stack<String> optStack, Stack<BigDecimal> numStack,
                                      String curOpt) {
        // 比较当前运算符和栈顶运算符的优先级
        String peekOpt = optStack.peek();
        int priority = getPriority(peekOpt, curOpt);
        if (priority == -1 || priority == 0) {
            // 栈顶运算符优先级大或同级，触发一次二元运算
            String opt = optStack.pop(); // 当前参与计算运算符
            BigDecimal num2 = numStack.pop(); // 当前参与计算数值2
            BigDecimal num1 = numStack.pop(); // 当前参与计算数值1
            BigDecimal bigDecimal = floatingPointCalc(opt, num1, num2);

            // 计算结果当做操作数入栈
            numStack.push(bigDecimal);

            // 运算完栈顶还有运算符，则还需要再次触发一次比较判断是否需要再次二元计算
            if (optStack.empty()) {
                optStack.push(curOpt);
            } else {
                compareAndCalc(optStack, numStack, curOpt);
            }
        } else {
            // 当前运算符优先级高，则直接入栈
            optStack.push(curOpt);
        }
    }

    /**
     * 遇到右括号和等号执行的连续计算操作（递归计算）
     * @param optStack 运算符栈
     * @param numStack 数值栈
     * @param isBracket true表示为括号类型计算
     */
    public static void directCalc(Stack<String> optStack, Stack<BigDecimal> numStack,
                                  boolean isBracket) {
        String opt = optStack.pop(); // 当前参与计算运算符
        BigDecimal num2 = numStack.pop(); // 当前参与计算数值2
        BigDecimal num1 = numStack.pop(); // 当前参与计算数值1
        BigDecimal bigDecimal = floatingPointCalc(opt, num1, num2);

        // 计算结果当做操作数入栈
        numStack.push(bigDecimal);

        if (isBracket) {
            if ("(".equals(optStack.peek())) {
                // 括号类型则遇左括号停止计算，同时将左括号从栈中移除
                optStack.pop();
            } else {
                directCalc(optStack, numStack, isBracket);
            }
        } else {
            if (!optStack.empty()) {
                // 等号类型只要栈中还有运算符就继续计算
                directCalc(optStack, numStack, isBracket);
            }
        }
    }

    /**
     * 不丢失精度的二元运算，支持高精度计算
     * @param opt
     * @param bigDecimal1
     * @param bigDecimal2
     * @return
     */
    public static BigDecimal floatingPointCalc(String opt, BigDecimal bigDecimal1,
                                               BigDecimal bigDecimal2) {
        BigDecimal resultBigDecimal = new BigDecimal(0);
        switch (opt) {
            case "+":
                resultBigDecimal = bigDecimal1.add(bigDecimal2);
                break;
            case "-":
                resultBigDecimal = bigDecimal1.subtract(bigDecimal2);
                break;
            case "*":
                resultBigDecimal = bigDecimal1.multiply(bigDecimal2);
                break;
            case "/":
                resultBigDecimal = bigDecimal1.divide(bigDecimal2, 10, BigDecimal.ROUND_HALF_DOWN); // 注意此处用法
                break;
            default:
                break;
        }
        return resultBigDecimal;
    }

    /**
     * priority = 0  表示两个运算符同级别
     * priority = 1  第二个运算符级别高，负数则相反
     * @param opt1
     * @param opt2
     * @return
     */
    public static int getPriority(String opt1, String opt2) {
        int priority = OPT_PRIORITY_MAP.get(opt2) - OPT_PRIORITY_MAP.get(opt1);
        return priority;
    }

    /**
     * 浮点数相等比较函数
     * @param value1
     * @param value2
     * @return
     */
    private static boolean isDoubleEquals (double value1, double value2) {
        System.out.println("正确结果=" + value1 + ", 实际计算结果=" + value2);
        return Math.abs(value1 - value2) <= 0.0001;
    }

    public static void main(String[] args) {
        // 几个测试数据
        System.out.println(isDoubleEquals(-39.5, executeExpression(" 2 + 3/2 * 3 - 4 *(2 + 5 -2*4/2+9) + 3 + (2*1)-3= ")));
        System.out.println(isDoubleEquals(60.3666, executeExpression("9*2+1/3*2-4+(3*2/5+3+3*6)+3*5-1+3+(4+5*1/2)=")));
        System.out.println(isDoubleEquals(372.8, executeExpression(" 9.2 *(20-1)-1+199 = ")));
        System.out.println(isDoubleEquals(372.8, executeExpression(" 9.2 *(20-1)-1+199  ")));
        System.out.println(isDoubleEquals(372.8, executeExpression(" 9.2 *(20-1)-1+199")));
        System.out.println(isDoubleEquals(-29, executeExpression(" 9 *(20-1)-(1+199) ")));
        System.out.println(isDoubleEquals(1.0E24, executeExpression("1000000000000*1000000000000 = ")));
    }

}
