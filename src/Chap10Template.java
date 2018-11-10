/**
 * @Auther: yuan
 * @Date: 18-10-11 10:35
 * @Description: Chapter 10 模板模式
 * 某餐馆制作咖喱时总是按照以下步骤完成：
 * 1. 放入肉
 * 2. 放入蔬菜
 * 3. 放入咖喱
 * 现餐馆提供鸡肉黄咖喱，素咖喱，牛肉青咖喱，以及大虾红咖喱。
 * 请使用原型模型帮助该餐厅完成咖喱制作，以应对中午点餐高峰期。
 *
 * version 1: 父类定义了算法的框架，所以，应该把操作步骤定义好，而不是由子类确定操作步骤．子类只是实现或改变某些具体步骤．
 */
public class Chap10Template {

    public static void main(String[] args) {
        ChickenYellowCurry cyc = new ChickenYellowCurry();
        VegetarianCurry vc = new VegetarianCurry();
        BeefGreenCurry bfc = new BeefGreenCurry();
        ShrimpRedCurry src = new ShrimpRedCurry();
        cyc.cook();
        vc.cook();
        bfc.cook();
        src.cook();
        System.out.println("Enjoy it.");
    }
}

abstract class Curry {
    public void putMeat(){}
    public void putVegetable(){}
    public void putCurry(){}
    public void cook(){
        putCurry();
        putMeat();
        putVegetable();
        System.out.println("finish curry and send to a client.");
        System.out.println("==================\n");
    }
}

class ChickenYellowCurry extends Curry{
    @Override
    public void putMeat() {
        System.out.println("put chicken.");
    }

    @Override
    public void putVegetable() {
        System.out.println("put potato and onion.");
    }

    @Override
    public void putCurry() {
        System.out.println("put yellow curry.");
    }
}

class VegetarianCurry extends Curry{
    @Override
    public void putMeat() {

    }

    @Override
    public void putVegetable() {
        System.out.println("put tomato, potato, carrot and eggplant.");
    }

    @Override
    public void putCurry() {
        System.out.println("put vegetarian samosa.");
    }

}

class BeefGreenCurry extends Curry{
    @Override
    public void putMeat() {
        System.out.println("put beef.");
    }

    @Override
    public void putVegetable() {
        System.out.println("put potato and carrot.");
    }

    @Override
    public void putCurry() {
        System.out.println("put green curry.");
    }

}

class ShrimpRedCurry extends Curry {
    @Override
    public void putMeat() {
        System.out.println("put shrimp");
    }

    @Override
    public void putVegetable() {
        System.out.println("put carrot and corn.");
    }

    @Override
    public void putCurry() {
        System.out.println("put red curry.");
    }
}

/* version 1:
interface Curry {
    public void putMeat();
    public void putVegetable();
    public void putCurry();
    public void cook();
}

class ChickenYellowCurry implements Curry{
    @Override
    public void putMeat() {
        System.out.println("put chicken.");
    }

    @Override
    public void putVegetable() {
        System.out.println("put potato and onion.");
    }

    @Override
    public void putCurry() {
        System.out.println("put yellow curry.");
    }

    @Override
    public void cook() {
        putMeat();
        putCurry();
        putVegetable();
        System.out.println("finish chicken yellow curry.");
        System.out.println("==================\n");
    }
}

class VegetarianCurry implements Curry{
    @Override
    public void putMeat() {

    }

    @Override
    public void putVegetable() {
        System.out.println("put tomato, potato, carrot and eggplant.");
    }

    @Override
    public void putCurry() {
        System.out.println("put vegetarian samosa.");
    }

    @Override
    public void cook() {
        putCurry();
        putVegetable();
        System.out.println("finish vegetarian curry.");
        System.out.println("==================\n");
    }
}

class BeefGreenCurry implements Curry{
    @Override
    public void putMeat() {
        System.out.println("put beef.");
    }

    @Override
    public void putVegetable() {
        System.out.println("put potato and carrot.");
    }

    @Override
    public void putCurry() {
        System.out.println("put green curry.");
    }

    @Override
    public void cook() {
        putMeat();
        putVegetable();
        putCurry();
        System.out.println("finish beef green curry.");
        System.out.println("==================\n");

    }
}

class ShrimpRedCurry implements Curry {
    @Override
    public void putMeat() {
        System.out.println("put shrimp");
    }

    @Override
    public void putVegetable() {
        System.out.println("put carrot and corn.");
    }

    @Override
    public void putCurry() {
        System.out.println("put red curry.");
    }

    @Override
    public void cook() {
        putCurry();
        putVegetable();
        putMeat();
        System.out.println("finish shrimp red curry.");
        System.out.println("==================\n");
    }
}*/
