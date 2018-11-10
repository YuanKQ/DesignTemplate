/**
 * @Auther: yuan
 * @Date: 18-8-30 16:44
 * @Description: Chapter 6 装饰器模式
 *    为Dota游戏中的英雄，添加服饰。
 *    每次英雄升级，都会附加一件新的服饰。
 *    注意：
 *    具有可拓展性：可以很方便添加新的英雄和服饰。
 */
public class Chap06Decorator {
    public static void main(String[] args) {
        Knight knight = new Knight("hello kitty");
        CapDecorator cap = new CapDecorator();
        ShirtDecorator shirt = new ShirtDecorator();
        PantDecorator pant = new PantDecorator();
        ShirtDecorator shirt1 = new ShirtDecorator();

        cap.decorate(knight, "white cap");
        pant.decorate(cap, "red pant");
        shirt.decorate(pant, "black shirt");
        shirt1.decorate(shirt, "brown jacket");
        shirt1.show();
    }
}

class Hero {
    public void show(){
    }
}

class Knight extends Hero {
    private String name;

    public Knight(String name) {
        this.name = name;
    }

    @Override
    public void show() {
        System.out.format("knight: %s show!", name);
    }
}


class Decorator extends Hero {
    private Hero hero;

    public void decorate(Hero hero){
        this.hero = hero;
    }

    public void show() {
        hero.show();
    }
}


class CapDecorator extends Decorator {
    private String cap;
    public void decorate(Hero hero, String cap){
        super.decorate(hero);
        this.cap = cap;
    }

    public void show() {
        System.out.println("Cap: " + cap);
        super.show();
    }
}


class ShirtDecorator extends Decorator {
    private String shirt;

    public void decorate(Hero hero, String shirt){
        super.decorate(hero);
        this.shirt = shirt;
    }

    @Override
    public void show() {
        System.out.println("Shirt: " + shirt);
        super.show();
    }
}


class PantDecorator extends Decorator {
    private String pant;

    public void decorate(Hero hero, String pant) {
        super.decorate(hero);
        this.pant = pant;
    }

    @Override
    public void show() {
        System.out.println("Pant: " + pant);
        super.show();
    }
}