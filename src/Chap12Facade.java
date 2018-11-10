/**
 * @Auther: yuan
 * @Date: 18-10-12 15:56
 * @Description: Chapter 12 Facade
 * 某快餐店针对讲求营养但又时间紧迫的上班族提供了三种套餐，每类套餐均有一荤一素一饭，送一份甜点或饮料．
 * 套餐１：炒牛肉，红烧茄子，米饭，水果沙拉
 * 套餐２：宫保鸡丁，炒白菜，米饭，豆浆
 * 套餐３：宫保鸡丁，红烧茄子，米饭，豆浆
 * 套餐４：炒牛肉，炒白菜，米饭，水果沙拉
 * 为提高出餐率，后厨进行了任务划分：每个人负责准备一样菜品，实现流水线作业．
 * 厨师Ａ负责炒牛肉，厨师Ｂ负责宫保鸡丁，厨师Ｃ负责炒白菜，厨师Ｄ负责红烧茄子，厨师Ｅ负责煮饭，厨师Ｆ负责煮豆浆，厨师Ｇ负责制作沙拉．
 *
 * 试采用外观模式实现以上场景。
 */
public class Chap12Facade {
    public static void main(String[] args) {
        FastFoodRestaurant fastFoodRestaurant = new FastFoodRestaurant();
        fastFoodRestaurant.serveSet1();
        fastFoodRestaurant.serveSet2();
        fastFoodRestaurant.serveSet3();
        fastFoodRestaurant.serveSet4();
    }
}

class FastFoodRestaurant {
    CookA cookA = new CookA();
    CookB cookB = new CookB();
    CookC cookC = new CookC();
    CookD cookD = new CookD();
    CookE cookE = new CookE();
    CookF cookF = new CookF();
    CookG cookG = new CookG();

    public void serveSet1() {
        cookA.fryBeef();
        cookD.braiseEggplant();
        cookE.boilRice();
        cookG.makeFruitSalad();
        System.out.println("serve set1.\n\n\n");
    }

    public void serveSet2() {
        cookB.cookChicken();
        cookC.fryCabbage();
        cookE.boilRice();
        cookF.boilBeanMilk();
        System.out.println("serve set2\n\n\n");
    }

    public void serveSet3() {
        cookB.cookChicken();
        cookD.braiseEggplant();
        cookE.boilRice();
        cookF.boilBeanMilk();
        System.out.println("serve set3\n\n\n");
    }

    public void serveSet4() {
        cookA.fryBeef();
        cookC.fryCabbage();
        cookE.boilRice();
        cookG.makeFruitSalad();
        System.out.println("serve set4.");
    }

}

class CookA {
    public void fryBeef() {
        System.out.println("cook fried beef.");
    }
}

class CookB {
    public void cookChicken() {
        System.out.println("cook spicy diced chicken with peanuts.");
    }
}

class CookC {
    public void fryCabbage() {
        System.out.println("cook fried cabbage.");
    }
}

class CookD {
    public void braiseEggplant() {
        System.out.println("cook braised eggplant.");
    }
}

class CookE {
    public void boilRice() {
        System.out.println("boil rice.");
    }
}

class CookF {
    public void boilBeanMilk() {
        System.out.println("boil bean milk.");
    }
}

class CookG {
    public void makeFruitSalad() {
        System.out.println("make fruit salad.");
    }
}