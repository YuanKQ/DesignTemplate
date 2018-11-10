import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yuan
 * @Date: 18-10-16 12:02
 * @Description: Chapter 14 观察者模式
 *
 * X大学门口九点以后，就会出现由各种美食小摊贩构成美食一条街．但是，城管一来就会暴力没收小摊贩的一切财产．
 * 因此，小摊贩们一旦看到城管来了，马上收拾店铺跑路．
 * 临近世界大学生运动会开会之际，政府大力整顿城市市容，Ｘ大夜市强制整顿，停业修整三个月，X大学生看到通知后，哀嚎:今晚没有夜宵吃了，只能吃泡面了．
 *
 * 现使用观察者模式完成上述场景．
 *
 * Version 1:
 * 对Subject主题理解不够：每个主题可以有多个观察者，，观察者只关心与自己相关的主题的变化。
 * 小摊贩只关心“城管来了”这个主题
 * 学生只关心校园通知这个主题。
 * 所以应该有Subject来管理若干个Observer.
 */

public class Chap14Observer {
    public static void main(String[] args) {
        UrbanManagementOfficer officer = new UrbanManagementOfficer();
        Vendor bbqVendor = new Vendor("BBQ Vendor", officer);
        Vendor kaolenmian = new Vendor("Rousted cold noodle", officer);
        Vendor shaobin = new Vendor("sesami seed cake", officer);

        SchoolLogistics logistics = new SchoolLogistics();
        Student xiaoming = new Student("xiaoming", logistics);
        Student coco = new Student("coco", logistics);

        System.out.println("World University Games comes!");
        officer.notifyObeservers(" Urban management officers come!");
        logistics.notifyObeservers("Snack Street closed.");
    }
}

interface Observer {
    void know();
}

class Subject {
    private List<Observer> observerList = new ArrayList<>();
    String notice;

    void addObeserver(Observer observer) {
        observerList.add(observer);
    }

    void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    void notifyObeservers(String newNotice) {
        notice = newNotice;
        for (Observer observer: observerList) {
            observer.know();
        }
    }

    public String getNotice() {
        return notice;
    }
}

class SchoolLogistics extends Subject {

}

class Student implements Observer {
    String name;
    SchoolLogistics schoolLogistics;   //通过组合的方式达到订阅的目的

    public Student(String name, SchoolLogistics schoolLogistics) {
        this.name = name;
        this.schoolLogistics = schoolLogistics;
        schoolLogistics.addObeserver(this);
    }

    @Override
    public void know() {
        System.out.println(name + "knows: " + schoolLogistics.getNotice() + "\n" + "Buy instant noodle to eat.\n\n");
    }
}

class UrbanManagementOfficer extends Subject {}

class Vendor implements Observer {
     String name;
     UrbanManagementOfficer urbanManagementOfficer;

    public Vendor(String name, UrbanManagementOfficer urbanManagementOfficer) {
        this.name = name;
        this.urbanManagementOfficer = urbanManagementOfficer;
        urbanManagementOfficer.addObeserver(this);
    }

    @Override
    public void know() {
        System.out.println(name + "knows: " + urbanManagementOfficer.getNotice() + "\n" + "Run!!!\n\n");
    }
}

/*
// version 1
public class Chap14Observer {
    public static void main(String[] args) {
        Vendor bbqVendor = new Vendor("BBQ Vendor");
        Vendor kaolenmian = new Vendor("Rousted cold noodle");
        Vendor shaobin = new Vendor("sesami seed cake");
        NewsStandBoss boss = new NewsStandBoss();
        boss.addSubject(bbqVendor);
        boss.addSubject(kaolenmian);
        boss.addSubject(shaobin);

        Student xiaoming = new Student();
        Student coco = new Student();
        SchoolLogistics logistics = new SchoolLogistics();
        logistics.addSubject(xiaoming);
        logistics.addSubject(coco);

        System.out.println("World University Games comes!");
        boss.notify(" Urban management officers come!");
        logistics.notify("Snack Street closed.");
    }

}

interface Subject {
    void know(String msg);
}

class Vendor implements Subject {
    String name;

    public Vendor(String name) {
        this.name = name;
    }

    @Override
    public void know(String msg) {
        System.out.println(name + " knows: " + msg + "\nRun!!!\n\n");
    }
}

class Student implements Subject {
    @Override
    public void know(String msg) {
        System.out.println("Student knows: " + msg + "\nBuy instant noodle to eat.\n\n");
    }
}

interface  Observer {

    void notify(String msg);
    void addSubject(Subject subject);
    void removeSubject(Subject subject);
}

class NewsStandBoss implements Observer {
    private List<Vendor> vendorList = new ArrayList<>();

    @Override
    public void notify(String msg) {
        for (Subject sub: vendorList) {
            sub.know(msg);
        }
    }

    @Override
    public void addSubject(Subject subject) {
        vendorList.add((Vendor)subject);
    }

    @Override
    public void removeSubject(Subject subject) {
        vendorList.remove(subject);
    }
}

class SchoolLogistics implements Observer {
    private Student student;

    @Override
    public void notify(String msg) {
        if (student != null) {
            student.know(msg);
        }
    }

    @Override
    public void addSubject(Subject subject) {
        student = (Student)subject;
    }

    @Override
    public void removeSubject(Subject subject) {
        student =  null;
    }
}
*/





