/**
 * @Auther: yuan
 * @Date: 18-10-22 16:05
 * @Description: Chapter16 状态模式
 * 某面包烘焙坊不同时段会出炉不一样的面包：
 *   6:00-10:00 吐司，蛋糕
 *   10:00-13:00 三文治，布丁
 *   13:00-15:00 午休
 *   15:00-17:00 蛋挞，牛角包
 *   17:00-20:00 汉堡，批萨
 *   20:00之后　关门
 * 试用状态模式来表达上述场景。
 *
 * Context内含一个State,用于记录当前state，由state决定行为；内含一个决定/改变state的因素.
 *
 * 不同状态下行为不同，把状态与其行为单独提取出来封装在具体的状态类中。
 * 环境类因状态的改变需要改变它的行为时，其实是切换到不同的状态类。
 *
 * 优点：
 * 允许状态转换逻辑与状态对象合成一体，而不是某一个巨大的条件语句块。
 * 可以让多个环境对象共享一个状态对象，从而减少系统中对象的个数。
 *
 * 缺点：
 * 开闭原则：对增加新状态模式很友好，但是需要修改负责状态转化的代码。
 * 随着状态增多，逻辑愈发复杂。
 */
public class Chap16State {
    public static void main(String[] args) {
        Bakery bakery = new Bakery(new Breakfast());
        bakery.setTime(9);
        bakery.sell();

        bakery.setTime(12);
        bakery.sell();

        bakery.setTime(14);
        bakery.sell();

        bakery.setTime(16);
        bakery.sell();

        bakery.setTime(18);
        bakery.sell();

        bakery.setTime(22);
        bakery.sell();
    }

}

class Bakery {
    final static int OPEN = 6;
    final static int BREAKFAST_END = 10;
    final static int LUNCH_END = 13;
    final static int REST_END = 15;
    final static int TEA_END = 17;
    final static int DINNER_END = 20;

    State state;
    int time;

    public Bakery(State state, int time) {
        this.state = state;
        this.time = time;
    }

    public Bakery(State state) {
        this.state = state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void sell() {
        state.handle(this);
    }
}

abstract class State{

    void handle(Bakery bakery){}
}

class Breakfast extends State {
    @Override
    void handle(Bakery bakery) {
        if (Bakery.OPEN <= bakery.time && bakery.time <= Bakery.BREAKFAST_END) {
            System.out.println("toast ,cake are fresh out of oven.");
        } else {
            bakery.setState(new Lunch());
            bakery.sell();
        }
    }
}

class Lunch extends State{
    @Override
    void handle(Bakery bakery) {
        if (Bakery.BREAKFAST_END <= bakery.time && bakery.time <= Bakery.LUNCH_END) {
            System.out.println("sandwish, pudding are fresh out of oven.");
        } else {
            bakery.setState(new Rest());
            bakery.sell();
        }
    }
}
class Rest extends State{
    @Override
    void handle(Bakery bakery) {
        if (Bakery.LUNCH_END <= bakery.time && bakery.time <= Bakery.REST_END) {
            System.out.println("Noon break");
        } else {
            bakery.setState(new Tea());
            bakery.sell();
        }
    }
}
class Tea extends State{
    @Override
    void handle(Bakery bakery) {
        if (Bakery.REST_END <= bakery.time && bakery.time <= Bakery.TEA_END) {
            System.out.println("custard tart, croissant are fresh out of oven.");
        } else {
            bakery.setState(new Dinner());
            bakery.sell();
        }
    }
}
class Dinner extends State{
    @Override
    void handle(Bakery bakery) {
        if (Bakery.TEA_END <= bakery.time && bakery.time <= Bakery.DINNER_END) {
            System.out.println("hamburger, pizza are fresh out of oven.");
        } else {
            bakery.setState(new Close());
            bakery.sell();
        }
    }
}
class Close extends State{
    @Override
    void handle(Bakery bakery) {
        if (Bakery.DINNER_END <= bakery.time) {
            System.out.println("close. Come Tomorrow.");
        } else {
            System.out.println("Wrong time.");
        }
    }
}