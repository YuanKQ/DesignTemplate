import java.util.ArrayList;
import java.util.List;

/**
 * @author  yuan
 * @ 18-10-30 10:25
 * @Description: Chapter23 命令模式
 * 在某烧烤店里，提供丰富的食物：烤羊肉串，烤鸡翅，烤生蚝，果汁。　顾客向服务员点餐后，服务员再把菜单按顺序交给后厨处理。　顾客还可以随时修改菜单，增加或者减少食物的数量。
 *
 * 现采用命令模式来完成上述场景。
 */
public class Chap23Command {
    public static void main(String[] args) {
        Invoker server1 = new Invoker();
        Invoker server2 = new Invoker();
        Barbecure barbecurer = new Barbecure();
        BarServer barServer = new BarServer();
        System.out.println("The restaurant is open.");
        server1.getOrder(new BakeChickenWingCommand(barbecurer, 1));
        server2.getOrder(new BakeChickenWingCommand(barbecurer, 2));
        server1.getOrder(new BakeMuttonCommand(barbecurer, 1));
        server2.getOrder(new BakeMuttonCommand(barbecurer, 2));
        BeerCommand beerCommand = new BeerCommand(barServer, 1);
        server1.getOrder(beerCommand);
        server1.getOrder(new BeerCommand(barServer, 1));
        server2.getOrder(new JuiceCommand(barServer, 2));
        server2.getOrder(new JuiceCommand(barServer, 2));
        server1.removeOrder(beerCommand);
        server1.notifyKitchen();
        server2.notifyKitchen();

    }
}

class Command {
    Receiver receiver;
    private int tableNo;

    public Command(Receiver receiver, int tableNo) {
        this.receiver = receiver;
        this.tableNo = tableNo;
    }

    public int getTableNo() {
        return tableNo;
    }

    void executeCommand(){}
}

class BakeMuttonCommand extends Command {
    public BakeMuttonCommand(Receiver receiver, int tableNo) {
        super(receiver, tableNo);
    }

    @Override
    void executeCommand() {
        System.out.println("from Table[" + this.getTableNo() + "] order:");
        ((Barbecure)receiver).bakeMutton();
        System.out.println("===================");
    }
}

class BakeChickenWingCommand extends Command {
    public BakeChickenWingCommand(Receiver receiver, int tableNo) {
        super(receiver, tableNo);
    }

    @Override
    void executeCommand() {
        System.out.println("from Table[" + this.getTableNo() + "] order:");
        ((Barbecure)receiver).bakeChickenWing();
        System.out.println("===================");
    }
}

class BeerCommand extends Command {
    public BeerCommand(Receiver receiver, int tableNo) {
        super(receiver, tableNo);
    }

    @Override
    void executeCommand() {
        System.out.println("from Table[" + this.getTableNo() + "] order:");
        ((BarServer)receiver).serveBeer();
        System.out.println("===================");
    }
}

class JuiceCommand extends Command {
    public JuiceCommand(Receiver receiver, int tableNo) {
        super(receiver, tableNo);
    }

    @Override
    void executeCommand() {
        System.out.println("from Table[" + this.getTableNo() + "] order:");
        ((BarServer)receiver).serveJuice();
        System.out.println("===================");
    }
}

interface Receiver {}

class Barbecure implements Receiver {
    void bakeMutton() {
        System.out.println("bake mutton!");
    }

    void bakeChickenWing() {
        System.out.println("bake chicken wing!");
    }
}

class BarServer implements Receiver {
    void serveBeer() {
        System.out.println("bartend beer!");
    }

    void serveJuice() {
        System.out.println("juicing orange juice!");
    }
}


class Invoker {
    List<Command> orders = new ArrayList<>(16);

    void getOrder(Command command) {
        orders.add(command);
    }

    void notifyKitchen() {
        for (Command order: orders) {
            order.executeCommand();
        }
    }

    void removeOrder(Command command) {
        orders.remove( command);
    }
}
