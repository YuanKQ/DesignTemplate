import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * @Auther: yuan
 * @Date: 18-10-24 17:29
 * @Description: Chapter18 备忘录模式
 * 某游戏的角色均具有攻击力，防守力2大属性。玩家往往会先对游戏角色备份，再开始升级打怪。
 * 试才用备忘录模式表达上述情景。
 *
 * Version 1: 游戏角色本身不应该保存备份，应该设计专门的备份数据管理工具。
 *
 * Thinking:
 * 需要备份的时候就创建一个备份，然后丢给备忘录管理者进行管理，要取的时候再从管理者手中拿到这个备份。这个备份者就类似于一个备份的仓库管理员，创建一个丢进去，需要的时候再拿出来。这就是备忘录模式。
 * Further thinking:
 * 但是，其实Originator可以把备份保存至内部，没有必要交给Caretaker来管理。
 */
public class Chap18Memento {
    public static void main(String[] args) {
        System.out.println("Game begin.");
        Role gigi = new Role("gigi");
        Caretaker gigiMementos = new Caretaker();
        gigi.setAttack(100);
        gigi.setDefend(100);
        System.out.println(gigi);
        Memento mem0 = gigi.createMemento();
        gigiMementos.addMemento("begin", mem0);

        System.out.println("\n\n=====battle 1:=====");
        gigi.setAttack(89);
        gigi.setDefend((70));
        System.out.println(gigi);
        Memento mem1 = gigi.createMemento();
        gigiMementos.addMemento("1", mem1);

        System.out.println("\n\n=====battle 2:=====");
        gigi.setAttack(50);
        gigi.setDefend(30);
        System.out.println(gigi);
        Memento mem2 = gigi.createMemento();
        gigiMementos.addMemento("2", mem2);

        System.out.println("\n\n=====battle 3:=====");
        gigi.setAttack(0);
        gigi.setDefend(0);
        System.out.println(gigi);
        System.out.println("Game Over");

        for (String name: gigiMementos.getMementoNames()) {
            gigi.setMemento(gigiMementos.getMemento(name));
            System.out.println("[" + name + "]: " + gigi);
        }
        System.out.println(gigi);
    }
}

class Memento{
    private int attack;
    private int defend;

    public Memento(int attack, int defend) {
        this.attack = attack;
        this.defend = defend;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefend() {
        return defend;
    }
}

class Role{
    String name;
    int attack;
    int defend;


    public Role(int attack, int defend) {
        this.attack = attack;
        this.defend = defend;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefend(int defend) {
        this.defend = defend;
    }

    public Role(String name) {
        this.name = name;
    }

    public Memento createMemento(){
        return new Memento(attack, defend);
    }

    public void setMemento(Memento memento) {
        attack = memento.getAttack();
        defend = memento.getDefend();
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defend=" + defend +
                '}';
    }
}

class Caretaker{
    HashMap<String, Memento> mementoHashMap = new HashMap<>();

    public void addMemento(String key, Memento value) {
        mementoHashMap.put(key, value);
    }

    public Memento getMemento(String key) {
        return mementoHashMap.get(key);
    }

    public Set<String> getMementoNames() {
        return mementoHashMap.keySet();
    }
}

/*
// Version 1:
public class Chap18Memento {
    public static void main(String[] args) {
        System.out.println("Game begin.");
        Role gigi = new Role("gigi");
        Caretaker gigiMementos = new Caretaker();
        gigi.setAttack(100);
        gigi.setDefend(100);
        System.out.println(gigi);
        Memento mem0 = gigi.createMemento();
        gigiMementos.addMemento("begin", mem0);

        System.out.println("\n\n=====battle 1:=====");
        gigi.setAttack(89);
        gigi.setDefend((70));
        System.out.println(gigi);
        Memento mem1 = gigi.createMemento();
        gigiMementos.addMemento("1", mem1);

        System.out.println("\n\n=====battle 2:=====");
        gigi.setAttack(50);
        gigi.setDefend(30);
        System.out.println(gigi);
        Memento mem2 = gigi.createMemento();
        gigiMementos.addMemento("2", mem2);

        System.out.println("\n\n=====battle 3:=====");
        gigi.setAttack(0);
        gigi.setDefend(0);
        System.out.println(gigi);
        System.out.println("Game Over");
        gigi.setMemento();
        System.out.println(gigi);

    }

}

class Memento{
    private int attack;
    private int defend;

    public Memento(int attack, int defend) {
        this.attack = attack;
        this.defend = defend;
    }

    public int getAttack() {
        return attack;
    }

    public int getDefend() {
        return defend;
    }
}

class Role{
    String name;
    int attack;
    int defend;
//    Ability intelligent;
//    Ability strength;
    Memento memento;
//    Ability intelligentMemento;
//    Ability strengthMemento;

    public Role(int attack, int defend) {
        this.attack = attack;
        this.defend = defend;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setDefend(int defend) {
        this.defend = defend;
    }

    public Role(String name) {
        this.name = name;
    }

    public Memento createMemento(){
        memento = new Memento(attack, defend);
        return memento;
    }

    public void setMemento() {
        attack = memento.getAttack();
        defend = memento.getDefend();
    }

    @Override
    public String toString() {
        return "Role{" +
                "name='" + name + '\'' +
                ", attack=" + attack +
                ", defend=" + defend +
                ", memento=" + memento +
                '}';
    }
}

class Caretaker{
    HashMap<String, Memento> mementoHashMap = new HashMap<>();

    public void addMemento(String key, Memento value) {
        mementoHashMap.put(key, value);
    }

    public Memento getMemento(String key) {
        return mementoHashMap.get(key);
    }

    public Set<String> getMementoNames() {
        return mementoHashMap.keySet();
    }
}
*/