/**
 * @Auther: yuan
 * @Date: 18-10-31 19:57
 * @Description: Chapter25 中介者模式
 * 海底捞的前台服务部，厨房部以及采购部是以呼叫机为中介进行通讯。
 * 通讯场景一：
 * 后厨：２３Ａ号桌的虾滑好了。
 * 服务员：好的，我现在马上送过去。
 * 通讯场景二：
 * 服务员：小吃和果汁都没有了。
 * 采购部：好的，我通知代理商送一些过来。
 * 通讯场景三：
 * 后厨：牛肉没有了。
 * 采购部：收到，我通知食品厂送一些过来。
 *
 * 现采用你中介者模式完成上述场景。
 *
 * Thinking:
 * 多个对象之间的依赖，通过中介者，取消了多个对象之间的的关联或依赖关系。
 * 将对象之间交互的复杂性转移至中介者内部设计的复杂性，降低对象之间的耦合性。
 * 在中介者模式中，同事类没有必要抽象出一个共同的父类，中介者并不关系同事的“共通性”。　中介者也没有必要抽象出共同的父类，除非中介者逻辑非常复杂，代码量非常大，才会出现多个中介者的情况。　由于中介者模式必然代码中介者膨胀问题，当出现多个中介者时，其中的复杂性可想而知。
 */
public class Chap25Mediator {
    public static void main(String[] args) {
        Server server = new Server();
        Chef chef = new Chef();
        PurchaseDepartment purchaseDepartment = new PurchaseDepartment();
        Bleeper bleeper = new Bleeper(server, chef, purchaseDepartment);
        server.setBleeper(bleeper);
        chef.setBleeper(bleeper);
        purchaseDepartment.setBleeper(bleeper);

        server.getOrder("beef");
        chef.finishFood("beef");

        server.drinkAndSnack("juice");

        chef.beefAndSuace("chili patse");
    }
}

class Message{
    int type;
    String content;

    public Message(int type, String content) {
        this.type = type;
        this.content = content;
    }
}

class Bleeper{
    public static final int CHEF_TO_SERVER = 0;
    public static final int CHEF_TO_PURCHASE = 1;
    public static final int SERVER_TO_CHEF = 2;
    public static final int SERVER_TO_PURCHASE = 3;
    private Server server;
    private Chef chef;
    private PurchaseDepartment purchaseDepartment;

    public Bleeper(Server server, Chef chef, PurchaseDepartment purchaseDepartment) {
        this.server = server;
        this.chef = chef;
        this.purchaseDepartment = purchaseDepartment;
    }


    public void notifyMedidator(Message msg) {
        int type = msg.type;
        String content = msg.content;
        switch (type) {
            case CHEF_TO_SERVER :
                server.serverFood(content);
                break;
            case CHEF_TO_PURCHASE:
                purchaseDepartment.buy(content);
                break;
            case SERVER_TO_CHEF:
                chef.cook(content);
                break;
            case SERVER_TO_PURCHASE:
                purchaseDepartment.buy(content);
                break;
            default:
                System.out.println("wrong message type");
        }
    }
}

class Server{
    private Bleeper bleeper;

    public void setBleeper(Bleeper bleeper) {
        this.bleeper = bleeper;
    }

    void serverFood(String msg){
        System.out.println("[server] serve food:" + msg);
    }

    void getOrder(String msg) {
        System.out.println("[server] notify chef: " + msg);
        bleeper.notifyMedidator(new Message(Bleeper.SERVER_TO_CHEF, msg));
    }

    void drinkAndSnack(String msg) {
        System.out.println("[server] lack: " + msg);
        bleeper.notifyMedidator(new Message(Bleeper.SERVER_TO_PURCHASE, msg));
    }
}

class Chef{
    private Bleeper bleeper;

    public void setBleeper(Bleeper bleeper) {
        this.bleeper = bleeper;
    }

    void cook(String msg) {
        System.out.println("[chef] begin cooking: " + msg);
    }

    void finishFood(String msg) {
        System.out.println("[chef] finish: " + msg);
        bleeper.notifyMedidator(new Message(Bleeper.CHEF_TO_SERVER, msg));
    }

    void beefAndSuace(String msg){
        System.out.println("[chef] lack: " + msg);
        bleeper.notifyMedidator(new Message(Bleeper.CHEF_TO_PURCHASE, msg));
    }
}

class PurchaseDepartment{
    private Bleeper bleeper;

    public void setBleeper(Bleeper bleeper) {
        this.bleeper = bleeper;
    }

    void buy(String msg) {
        System.out.println("[purchase department] buy" + msg);
    }
}