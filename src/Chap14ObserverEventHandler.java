import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yuan
 * @Date: 18-10-27 21:26
 * @Description: X大学门口九点以后，就会出现由各种美食小摊贩构成美食一条街．但是，城管一来就会暴力没收小摊贩的一切财产．
 *  * 因此，小摊贩们一旦看到城管来了，马上收拾店铺跑路．
 *  * 临近世界大学生运动会开会之际，政府大力整顿城市市容，Ｘ大夜市强制整顿，停业修整三个月，X大学生看到通知后，哀嚎:今晚没有夜宵吃了，只能吃泡面了．
 *  *
 *  * 现使用观察者模式完成上述场景．
 *
 *  使用事件委托模式后，观察者的行为可以是多样的；并不需要为每一个主题都新建一个类，在允许情况下，可以只有一个通知者，即可完成通知不同主题的观察者；通知者不知道观察者的存在（EventHandler)，代码耦合性降低
 */
public class Chap14ObserverEventHandler {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Vendor1 bbqVendor = new Vendor1("BBQ Vendor");
        Vendor1 kaolenmian = new Vendor1("Rousted cold noodle");
        Vendor1 shaobin = new Vendor1("sesami seed cake");

        Student1 xiaoming = new Student1("xiaoming");
        Student1 coco = new Student1("coco");

        ConcreteNotifyer notifyer = new ConcreteNotifyer(new EventHandler());
        notifyer.addEvent(bbqVendor, "run", "Urban management officers come!");
        notifyer.addEvent(kaolenmian, "run", "Urban management officers come!");
        notifyer.addEvent(shaobin, "run", "Urban management officers come!");
        notifyer.addEvent(xiaoming, "buyInstantNoodle", "Snack Street closed.");
        notifyer.addEvent(coco, "buyInstantNoodle", "Snack street closed");

        System.out.println("World University Games comes!");
        notifyer.notifyEvent();
    }
}

class Event{
    Object object;
    String methodName;
    Class[] paramTypes;
    Object[] params;

    public Event(Object object, String methodName, Object... params) {
        this.object = object;
        this.methodName = methodName;
        this.params = params;
        extractParamTypes(params);
    }

    private void extractParamTypes(Object... params) {
        int len = this.params.length;
        this.paramTypes = new Class[len];
        for (int i = 0; i < len; i++) {
            this.paramTypes[i] = this.params[i].getClass();
        }
    }

    public void invoke() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = object.getClass().getMethod(methodName, paramTypes);
        m.invoke(object, params);
    }
}

class EventHandler {
    List<Event> eventList = new ArrayList<>();

    public void addEvent(Event event) {
        eventList.add(event);
    }

    public void notifyEvents() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        for (Event event: eventList) {
            event.invoke();
        }
    }
}

interface Notifyer {
    public void addEvent(Object object, String methodName, Object... params);
    public void notifyEvent() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException;
}

class ConcreteNotifyer implements Notifyer{
    EventHandler eventHandler;

    public ConcreteNotifyer(EventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }

    @Override
    public void addEvent(Object object, String methodName, Object... params) {
        eventHandler.addEvent(new Event(object, methodName, params));
    }

    @Override
    public void notifyEvent() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        eventHandler.notifyEvents();
    }
}

class Vendor1{
    String name;

    public Vendor1(String name) {
        this.name = name;
    }

    public void run(String notice) {
        System.out.println(name + " knows: " + notice + "\n" + "Run!!!\n\n");
    }
}

class Student1{
    String name;

    public Student1(String name) {
        this.name = name;
    }

    public void buyInstantNoodle(String notice) {
        System.out.println(name + " knows: " + notice + "\n" + "Buy instant noodle to eat.\n\n");
    }
}
