import java.util.Map;
import java.util.Scanner;

/**
 * @Auther: yuan
 * @Date: 18-8-29 17:50
 * @Description:  Chapter 1   简单工厂模式
 *    写一个简单的二元运算的计算器，可以完成加减乘除以及n次方根运算。
 *    注意：
 *    1.界面逻辑要与运算逻辑分开。
 *    2.具有可拓展性：可以很方便添加新的运算
 */
public class Chap01SimpleFactory {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();
        while (line != null && !line.equals("") && !line.equals("#")) {
            getResult(line);
            line = sc.nextLine();
        }
    }

    public static void getResult(String line) {
        String[] strs = line.split(" ");
        Integer operand1 = Integer.parseInt(strs[0]);
        Operator operator = OperatorFactory.getOperator(strs[1]);
        Integer operand2 = Integer.parseInt(strs[2]);
        if (operator != null) {
            operator.setOperand1(operand1);
            operator.setOperand2(operand2);
            System.out.println(operator.getResult());
        }
    }
}

abstract class Operator {
    Integer operand1;
    Integer operand2;

    public void setOperand1(Integer operand1) {
        this.operand1 = operand1;
    }

    public void setOperand2(Integer operand2) {
        this.operand2 = operand2;
    }

    public Object getResult() {
        return null;
    }
}

class Add extends Operator {
    @Override
    public Integer getResult() {
        return operand1 + operand2;
    }
}

class Substract extends Operator {
    @Override
    public Integer getResult() {
        return operand1 - operand2;
    }
}

class Mutiply extends Operator {
    @Override
    public Integer getResult() {
        return operand1 * operand2;
    }
}

class Divide extends Operator {
    @Override
    public Double getResult() {
        return (operand1 + 0.0) / operand2;
    }
}

class Power extends Operator {
    @Override
    public Double getResult() {
        return Math.pow(operand1, operand2);
    }
}

class OperatorFactory {
    static public Operator getOperator(String op) {
        switch (op) {
            case "+": return new Add();
            case "-": return new Substract();
            case "*": return new Mutiply();
            case "/": return new Divide();
            case "^": return new Power();
            default: return null;
        }
    }
}