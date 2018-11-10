import javax.annotation.PostConstruct;
import java.util.Scanner;

/**
 * @Auther: yuan
 * @Date: 18-8-29 18:09
 * @Description: Chapter 2 策略模式
 *    写一个简单的收银台软件，可以完成商场多种打折方式：原价销售，打折，返现等
 *    注意：
 *    1.界面逻辑要与运算逻辑分开。
 *    2.具有可拓展性：可以很方便添加新的打折方式
 */
public class Chap02Strategy {
    public static void main(String[] args) {
        CashContext cashNormal = new CashContext("normal");
        cashNormal.getResult(100.0);
        CashContext cashOff = new CashContext("80% off");
        cashOff.getResult(100.0);
        CashContext cashReturn = new CashContext("over 400 return 150");
        cashReturn.getResult(100.0);
        cashReturn.getResult(500.0);

    }
}

abstract class CashSuper {
    Double cash;
    public void acceptCash(Double cash) {
        this.cash = cash;
    }
    public Double getResult() {
        return cash;
    }
}

class CashNormal extends CashSuper {
}

class CashDiscount extends CashSuper {
    Double discount;

    public CashDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public Double getResult() {
        return cash * discount;
    }
}

class CashReturn extends CashSuper {
    private Double threshold;
    private Double returnAmount;

    public CashReturn(Double threshold, Double returnAmount) {
        this.threshold = threshold;
        this.returnAmount = returnAmount;
    }

    @Override
    public Double getResult() {
        if (cash >= threshold) {
            return cash - returnAmount;
        } else {
            return cash;
        }
    }
}

class CashContext {
    CashSuper strategy;
    CashContext(String s) {
        switch (s) {
            case "normal":
                strategy = new CashNormal();
                break;
            case "80% off":
                strategy = new CashDiscount(0.8);
                break;
            case "over 400 return 150":
                strategy = new CashReturn(400.0, 150.0);
                break;
        }
    }

    public void getResult(Double cash) {
        if (strategy != null) {
            strategy.acceptCash(cash);
            System.out.println(strategy.getResult());
        } else {
            System.out.println(cash);
        }
    }
}


