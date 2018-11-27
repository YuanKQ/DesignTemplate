import java.util.Stack;

/**
 * @Auther: yuan
 * @Date: 18-11-1 11:24
 * @Description: Chapter27 解释器模式
 * 某餐馆采用的是机器人备餐后厨系统，听不懂人类的话。　需要前台服务员根据顾客点餐之后，翻译成相应的字符告诉机器人来备餐。
 * 口味：０正常口味，１辣，２麻辣，３清淡
 * 菜：Ａ宫保鸡丁，Ｂ红烧茄子，Ｃ咸菜卤肉
 * 是否外带：Ｙ外带，Ｎ堂食
 * 比如，　点餐代码ＹＡ１Ｂ０的意思为打包一份微辣的宫保鸡丁和正常口味的红烧茄子
 *
 * 现采用解释权模式完成上述场景。
 *
 * Thinking:
 * 解释器模式在实际的系统开发中使用得非常少，因为它会引起效率、性能以及维护等问题。
 * 若你确实遇到“一种特定类型的问题发生的频率足够高”的情况，准备使用解释器模式时，可以考虑一下Expression4J、MESP（Math Expression String Parser）、Jep等开源的解析工具包，功能都异常强大，而且非常容易使用，效率也还不错。
 * 自己没有必要从头开始编写解释器。
 */
public class Chap27Interpreter {
    public static void main(String[] args) {
        Robot robot =  new Robot();
        robot.intepret("YA1B0");
        robot.run();
        robot.intepret("A0C1");
        robot.run();
    }
}

interface OrderCode {
    String interpret(char input);
}

class WrapCode implements OrderCode {
    @Override
    public String interpret(char input) {
        switch (input) {
            case 'Y': return "for take-away";
            case 'N': return "for hear";
            default: return "";
        }
    }
}

class DishCode implements OrderCode {
    @Override
    public String interpret(char input) {
        switch (input) {
            case 'A': return "spicy diced chicken with peanuts;  ";
            case 'B': return "braise eggplant;  ";
            case 'C': return "pork with salted vegetable;  ";
            default: return "";
        }
    }
}

class TasteCode implements OrderCode {
    @Override
    public String interpret(char input) {
        switch (input) {
            case '0':
                return "[normal taste]";
            case '1':
                return "[spicy]";
            case '2':
                return "[hot]";
            case '3':
                return "[light]";
            default: return "";
        }
    }
}

class Robot {
    Stack<String> results = new Stack<>();
    WrapCode wrapCode = new WrapCode();
    DishCode dishCode = new DishCode();
    TasteCode tasteCode = new TasteCode();

    void intepret(String string){
        char[] inputChars = string.toCharArray();
        for (char c: inputChars) {
            switch (c) {
                case 'A': case 'B': case 'C':
                    results.push(dishCode.interpret(c));
                    break;
                case '0': case '1': case '2': case '3':
                    results.push(tasteCode.interpret(c));
                    break;
                case 'Y': case 'N':
                    results.push(wrapCode.interpret(c));
                    break;
                default:
                    return;
            }
        }
    }

    void run() {
        while (!results.empty()) {
            System.out.print(results.pop());
        }
        System.out.println();
    }
}