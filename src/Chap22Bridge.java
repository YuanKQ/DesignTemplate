/**
 * @Auther: yuan
 * @Date: 18-10-30 09:50
 * @Description: Chapter22 桥接模式
 * 某公司食堂有多个窗口提供午餐套餐，每份套餐里都含有饮品，主食，荤菜，素菜。　为给员工们提供更多口味，每个窗口的都有自己的厨师和厨房，因而套餐的味道也不一样。
 *
 * 现才用桥接模式来描述上述场景。　
 *
 * 倘若食堂在套餐里增加了甜品，桥接模式还能hold得住吗？食堂为增加竞争，又引入了新的窗口，桥接模式还能hold得住吗？
 */
public class Chap22Bridge {
    public static void main(String[] args) {
        System.out.println("LunchTime:");
        LunchWindow lunchWindow = new LunchWindow(new SichunLunch());
        lunchWindow.serveFoodSet();
        LunchWindow lunchWindow1 = new LunchWindow(new GuangdongLunch());
        lunchWindow1.serveFoodSet();
        System.out.println("DinnerTime:");
        DinnerWindow dinnerWindow = new DinnerWindow(new WesternDinnerSet());
        dinnerWindow.serveFoodSet();
        DinnerWindow dinnerWindow1 = new DinnerWindow(new ChineseDinnerSet());
        dinnerWindow1.serveFoodSet();

        while(true) {}
    }
}

interface Set{
    void serveSet();
}

class LunchSet implements Set{
   void serveDrink(){}
   void serveStaple(){}
   void serveMeat(){}
   void serveVegetable(){}

    @Override
    public void serveSet() {
        serveDrink();
        serveStaple();
        serveMeat();
        serveVegetable();
    }
}

class DinnerSet implements Set{
    void serveSoup(){}
    void serveMainFood(){}
    void serveDesert(){}
    @Override
    public void serveSet() {
        serveSoup();
        serveMainFood();
        serveDesert();
    }
}

class SichunLunch extends LunchSet {
    @Override
    void serveDrink() {
        System.out.println("Drink: soybean milk.");
    }

    @Override
    void serveStaple() {
        System.out.println("Staple food: rice.");
    }

    @Override
    void serveMeat() {
        System.out.println("Meat: spicy diced chicken with peanut.");
    }

    @Override
    void serveVegetable() {
        System.out.println("Vegetable: spicy hot pot");
    }
}

class GuangdongLunch extends LunchSet {
    @Override
    void serveDrink() {
        System.out.println("Drink: soup.");
    }

    @Override
    void serveStaple() {
        System.out.println("Staple: rice.");
    }

    @Override
    void serveMeat() {
        System.out.println("Meat: barbecued meat.");
    }

    @Override
    void serveVegetable() {
        System.out.println("Vegetable");
    }
}

class FoodWindow {
    Set set;

    public FoodWindow(Set set) {
        this.set = set;
    }

    public void serveFoodSet(){
        set.serveSet();
        System.out.println("====================");
    }
}

class LunchWindow extends FoodWindow{
    public LunchWindow(LunchSet set) {
        super(set);
    }
}

class ChineseDinnerSet extends DinnerSet {
    @Override
    void serveSoup() {
        System.out.println("Soup: soup od day");
    }

    @Override
    void serveMainFood() {
        System.out.println("MainFood: fried rice, fried beef and fried shrimp.");
    }

    @Override
    void serveDesert() {
        System.out.println("Desert: mochi and fresh fruit platter.");
    }
}

class WesternDinnerSet extends DinnerSet {
    @Override
    void serveSoup() {
        System.out.println("Soup: mushroom cream soup");
    }

    @Override
    void serveMainFood() {
        System.out.println("Main Food: spaghetti with seafood and grilled beef steak");
    }

    @Override
    void serveDesert() {
        System.out.println("Desert: putin and latte");
    }
}

class DinnerWindow extends FoodWindow{
    public DinnerWindow(DinnerSet set) {
        super(set);
    }
}
