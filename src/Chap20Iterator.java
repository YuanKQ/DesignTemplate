import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yuan
 * @Date: 18-10-25 17:24
 * @Description: Chapter20 迭代器模式
 * 松鼠小弟为过冬，屯了一大堆栗子。　每天早上起来，它会先一遍栗子。
 * 
 * 现在采用迭代器模式来描述上述场景。
 *
 * Version 1: 迭代器/聚集类的method设计，与实际情况有出入
 * 
 * Thinking:
 * 按照单一职责原则，迭代器应该抽象出来单独做一个类。　但凡具有迭代器这个性质的类，就会内含一个迭代器。
 * 将聚集类抽象出来，而不需要管具体聚集类里面的具体实现（里面使用集合类or数组）
 */
public class Chap20Iterator {
    /*public static void main(String[] args) {
        List<Chestnut> chestnuts = new ArrayList<>();
        for (int i = 0; i < 10; i ++)
            chestnuts.add(new Chestnut(i));
        ChestnutAggregate heap = new ChestnutAggregate(chestnuts);
        Iterator iterator = heap.createIterator();
        for (int day = 1; day <= 7; day ++) {
            System.out.println("Day" + day + ":");
            iterator.first();
             while (!iterator.end()) {
                 System.out.println(iterator.currentItem());
                 iterator.next();
             }
            System.out.println("\n\n");
        }
    } */
    public static void main(String[] args) {
        ChestnutAggregate chestnuts = new ChestnutAggregate();
        for (int i = 0; i < 10; i++)
            chestnuts.add(new Chestnut(i));
        Iterator iterator = chestnuts.createIterator();
        for (int day = 1; day <= 7; day++) {
            System.out.println("Day" + day + ":");

            iterator.head();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());

            }
            System.out.println("\n\n");
        }
    }
}

class Chestnut {
    int number;

    public Chestnut(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Chestnut{" +
                "number=" + number +
                '}';
    }
}

interface Iterator{
    Object first();
    void head();
    Object next();
    boolean hasNext();
}

interface Aggregate{
    int size();
    Object get(int index);
    Iterator createIterator();
}


class ChestnutIterator implements Iterator{
    Aggregate aggregate;
    int currentIndex;

    @Override
    public void head() {
        currentIndex = 0;
    }

    public ChestnutIterator(Aggregate aggregate) {
        this.aggregate = aggregate;
        currentIndex = 0;
    }

    @Override
    public Object first() {
        return aggregate.get(0);
    }

    @Override
    public Object next() {
        return aggregate.get(currentIndex ++);
    }

    @Override
    public boolean hasNext() {
        return currentIndex < aggregate.size();
    }
}

class ChestnutAggregate implements Aggregate{
    private List<Chestnut> chestnuts;

    public ChestnutAggregate() {
        chestnuts = new ArrayList<>();
    }


    public void add(Chestnut o) {
        chestnuts.add(o);
    }

    public void remove(Chestnut o) {
        chestnuts.remove(o);
    }


    public ChestnutAggregate(List<Chestnut> chestnuts) {
        this.chestnuts = chestnuts;
    }

    @Override
    public Iterator createIterator() {
        return new ChestnutIterator(this);
    }

    @Override
    public int size() {
        return chestnuts.size();
    }

    @Override
    public Object get(int index) {
        if (chestnuts.size() <= index)
            return null;
        else return chestnuts.get(index);
    }
}
/*
interface Iterator{
    void first();
    void next();
    boolean end();
    Object currentItem();
}



class ChestnutIterator implements Iterator {
    Aggregate aggregate;     // List<Chestnut> chestnuts;  抽象程度不够，会使该迭代器严重依赖于底层实现。
    int currentIndex;

    public ChestnutIterator(Aggregate aggregate) {
        this.aggregate = aggregate;
        currentIndex = 0;
    }

    @Override
    public void first() {
        currentIndex = 0;
    }

    @Override
    public void next() {
        currentIndex ++;
    }

    @Override
    public boolean end() {
        return currentIndex == aggregate.size();
    }

    @Override
    public Chestnut currentItem() {
        return (Chestnut)aggregate.get(currentIndex);
    }
}

interface Aggregate{
    Iterator createIterator();
    int size();
    Object get(int index);
}

*/
/*
class ChestnutAggregate implements Aggregate{
    private List<Chestnut> chestnuts;

    public ChestnutAggregate(List<Chestnut> chestnuts) {
        this.chestnuts = chestnuts;
    }

    @Override
    public Iterator createIterator() {
        return new ChestnutIterator(this);
    }

    @Override
    public int size() {
        return chestnuts.size();
    }

    @Override
    public Object get(int index) {
        if (chestnuts.size() <= index)
            return null;
        else return chestnuts.get(index);
    }

    public void countNuts() {
        Iterator counter = createIterator();
        while (!counter.end()) {
            System.out.println(counter.currentItem());
            counter.next();
    }
}

}
*/