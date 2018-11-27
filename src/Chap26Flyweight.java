/**
 * @Auther: yuan
 * @Date: 18-11-1 10:57
 * @Description: Chapter26 享元模式
 * 某半山腰上有一家小餐馆，每天都会给过路的游客和山民提供地道的套餐：蘑菇炖山鸡，大酱山野菜以及竹筒饭。
 * 套餐都是事先煮好的，即点即上，十分合适赶时间的顾客。
 * 但是餐馆也会提供点餐炒菜的服务，现点现做，给顾客提供热情腾腾的饭菜。
 *
 * 现采用享元模式完成上述场景。
 */
public class Chap26Flyweight {
    public static void main(String[] args) {
        Restuarant restuarant = new Restuarant();
        System.out.println("table 1:");
        restuarant.getOrder("regular");

        System.out.println("table 2:");
        restuarant.getOrder("chicken soup noodle");

        System.out.println("table 1:");
        restuarant.getOrder("fried potato and eggplant, mint beef and rice");
    }
}
interface FoodSet {
    void server(String name);
}

class RegularFoodSet implements FoodSet{
    @Override
    public void server(String name) {
        System.out.println("serve: chicken mushroom stew, pickles and bamboo tube rice");
    }
}

class FlexibleFoodSet implements FoodSet{
    @Override
    public void server(String name) {
        String[] dishes = name.split(",");
        System.out.print("serve: ");
        for (String dish: dishes) {
            System.out.print(dish + ",");
        }
        System.out.println();
    }
}

class Restuarant {
    RegularFoodSet regularFoodSet = new RegularFoodSet();
    public void getOrder(String order) {
        if (order != "regular") {
            new FlexibleFoodSet().server(order);
        } else {
            regularFoodSet.server(order);
        }
    }
}
