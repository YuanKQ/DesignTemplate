/**
 * @Auther: yuan
 * @Date: 18-10-24 16:17
 * @Description: Chapter17 适配器模式
 *
 * 某国际餐厅为吸引客源，聘请了一个印度厨子在现场制作印度飞饼，厨子不会讲中文，但是他带来的徒弟会说中文和英文。每当有客人点印度飞饼的时候，他徒弟就会翻译成英文告诉他。
 * 该餐厅有多名厨子，他们均具有烹饪1个方法。
 * 试才用适配者模式来表达上述情景。
 *
 * 倘若某天，该餐厅有请来了法国厨子，思考一下，上述场景是否仍适用呢？
 *
 * version 1: 其实还不能体现适配器的作用。　加了适配器之后，顾客是看不到外国厨子的。
 */

public class Chap17Adapter {
    public static void main(String[] args) {
        Cook sichuanChef = new ChineseChef("川菜");
        Cook guangzhouChef = new ChineseChef("粤菜");
        Cook indianChef = new ChineseApprentice();

        sichuanChef.cook();
        guangzhouChef.cook();
        indianChef.cook();

    }
}

interface Cook{
    public void cook();
}

class ChineseChef implements Cook {
    String style;

    public ChineseChef(String style) {
        this.style = style;
    }

    @Override
    public void cook() {
        System.out.println(style + "在烹饪");
    }
}

class ChineseApprentice implements Cook {
    IndianChef indianChef;

    public ChineseApprentice() {
        this.indianChef = new IndianChef();
    }

    @Override
    public void cook() {
        indianChef.bake();
    }
}

class IndianChef {
    public void bake() {
        System.out.println("The indian chef makes Roti prata");
    }
}

/* Version 1:
public class Chap17Adapter {
    public static void main(String[] args) {
        Chef IndianChef = new Chef();
        ChineseStudent student = new ChineseStudent(IndianChef);

        String order = "印度飞饼";
        student.getOrder(order);

    }
}

class ChineseStudent{
    Chef teacher;

    public ChineseStudent(Chef teacher) {
        this.teacher = teacher;
    }

    void getOrder(String order){
        teacher.cook("Roti prata");
        System.out.println(order + "已完成，上菜咯～");
    }
}

class Chef{

    public void cook(String order) {
        System.out.println("make " + order);
    }
}*/
