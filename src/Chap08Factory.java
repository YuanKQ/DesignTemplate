/**
 * @Auther: yuan
 * @Date: 18-9-1 17:55
 * @Description: Chapter 8 工厂模式
 *  Chap01中的简单工厂模式改为工厂模式。
 */
public class Chap08Factory {
    public static void main(String[] args) {
        Integer op1 = 10;
        Integer op2 = 80;
        // 选择何种实例创建的逻辑移至客户端。
        OpFactory factory = new AddFactory();
        Operator op = factory.getOperator();
        op.setOperand1(op1);
        op.setOperand2(op2);
        System.out.println(op.getResult());

        // 更改实例时，只需要修改factory创建这一句话，更改代价比较小。
        factory = new SubFactory();
        op = factory.getOperator();
        op.setOperand1(op1);
        op.setOperand2(op2);
        System.out.println(op.getResult());

        factory = new MulFactory();
        op = factory.getOperator();
        op.setOperand1(op1);
        op.setOperand2(op2);
        System.out.println(op.getResult());
    }
}

interface OpFactory {
    public Operator getOperator();
}

class AddFactory implements OpFactory{
    @Override
    public Operator getOperator() {
        return new Add();
    }
}

class SubFactory implements OpFactory{
    @Override
    public Operator getOperator() {
        return new Substract();
    }
}

class MulFactory implements OpFactory{
    @Override
    public Operator getOperator() {
        return new Mutiply();
    }
}

class PowFactory implements OpFactory{
    @Override
    public Operator getOperator() {
        return new Power();
    }
}

class  DivFactory implements OpFactory{
    @Override
    public Operator getOperator() {
        return new Divide();
    }
}