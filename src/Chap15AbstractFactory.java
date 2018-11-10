import java.io.FileReader;

/**
 * @Auther: yuan
 * @Date: 18-10-22 15:27
 * @Description: Chapter15 抽象工厂模式
 * 某公司附近有３家快餐店Ａ／Ｂ，均提供炒菜（炒牛肉/炖茄子），主食（米饭/面条），饮品（豆浆/juice）．
 * 由于每个餐厅的做法不一样，各具风味．
 * 试才用抽象工厂模式来表达上述场景．
 *
 * 现在，餐厅Ａ推出了下午茶（蛋糕，水果沙拉）深受附近上班族喜爱，餐厅Ｂ／Ｃ也不甘落后，也效仿了推出一样的下午茶．
 * 试在原来代码的基础上完成．
 */
public class Chap15AbstractFactory {
    public static void main(String[] args) {
        RestaurantA restaurantA = new RestaurantA();
        RestaurantB restaurantB = new RestaurantB();
        FriedDishes friedBeef = restaurantA.serveFriedDishes();
        FriedDishes braisedEggplant = restaurantB.serveFriedDishes();
        StapleFood rice = restaurantA.serveStapleFood();
        StapleFood noodle = restaurantB.serveStapleFood();
        Drink beanMilk = restaurantA.serveDrink();
        Drink orangeJuice = restaurantA.serveDrink();
    }
}

interface FriedDishes{
    void fry();
}

class FriedBeef implements FriedDishes{
    @Override
    public void fry() {
        System.out.println("Fired Beef");
    }
}

class BraiseEggplant implements FriedDishes {
    @Override
    public void fry() {
        System.out.println("Braise Eggplant");
    }
}

interface StapleFood{
    void steam();
}

class Rice implements StapleFood {
    @Override
    public void steam() {
        System.out.println("Steam Rice");
    }
}

class Noodle implements StapleFood {
    @Override
    public void steam() {
        System.out.println("Steam Noodle");
    }
}

interface Drink {
    void juicing();
}

class BeanMilk implements Drink {
    @Override
    public void juicing() {
        System.out.println("juicing bean and cook");
    }
}

class OrangeJuice implements Drink {
    @Override
    public void juicing() {
        System.out.println("juicing Orange and ice");
    }
}

interface Resturant {
    FriedDishes serveFriedDishes();
    StapleFood serveStapleFood();
    Drink serveDrink();
}

class RestaurantA implements Resturant {
    @Override
    public FriedDishes serveFriedDishes() {
        FriedBeef friedBeef = new FriedBeef();
        friedBeef.fry();
        System.out.println("RestaurantA serve food! ");
        return friedBeef;
    }

    @Override
    public StapleFood serveStapleFood() {
        Rice rice = new Rice();
        rice.steam();
        System.out.println("RestaurantA serve food! ");
        return rice;
    }


    @Override
    public Drink serveDrink() {
        BeanMilk beanMilk = new BeanMilk();
        beanMilk.juicing();
        System.out.println("RestaurantA serve food! ");
        return beanMilk;
    }
}

class RestaurantB implements Resturant {
    @Override
    public FriedDishes serveFriedDishes() {
        BraiseEggplant braiseEggplant = new BraiseEggplant();
        braiseEggplant.fry();
        System.out.println("RestaurantB serve food! ");
        return braiseEggplant;
    }

    @Override
    public StapleFood serveStapleFood() {
        Noodle noodle = new Noodle();
        noodle.steam();
        System.out.println("RestaurantB serve food! ");
        return noodle;
    }

    @Override
    public Drink serveDrink() {
        OrangeJuice orangeJuice = new OrangeJuice();
        orangeJuice.juicing();
        System.out.println("RestaurantB serve food! ");
        return orangeJuice;
    }
}