import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yuan
 * @Date: 18-10-25 16:38
 * @Description: Chapter19 组合模式
 * 这个夏天是属于小龙虾的，生意是属于江味龙虾馆的。　江味龙虾馆在深圳各个区都开了分店和外卖点。　分店可以现场宰杀小龙虾，烹饪小龙虾，招待客人，送外卖。　而外卖点只能烹饪小龙虾和送外面。
 *
 * 现才用组合模式来表达上述场景。
 *
 * Version 1: 组合模式除了简化基本对象和组合对象的类图，还有一个功能是，组合对象具有添加／删除基本对象的功能
 */
public class Chap19Composite {
    public static void main(String[] args) throws UnsupportedOperationException{
        BranchRestaurant branchRestaurant = new BranchRestaurant("shenzhen general");
        BranchRestaurant branchRestaurant1 = new BranchRestaurant("xili");
        BranchRestaurant branchRestaurant2 = new BranchRestaurant("nanshan");
        TakeAway takeAway = new TakeAway("longgang");
        branchRestaurant.addBranch(branchRestaurant1);
        branchRestaurant.addBranch(branchRestaurant2);
        branchRestaurant.addBranch(takeAway);

        branchRestaurant.displayBranches();

        branchRestaurant.removeBranch(branchRestaurant2);

        branchRestaurant.displayBranches();

        // Version 1:
        // BranchRestaurant branchRestaurant = new BranchRestaurant(new TakeAway());
        // branchRestaurant.killLobster();
        // branchRestaurant.cookLobster();
        // branchRestaurant.serveClient();
        // branchRestaurant.deliveryFood();
        //
        // TakeAway takeAway = new TakeAway();
        // takeAway.killLobster();
        // takeAway.cookLobster();
        // takeAway.serveClient();
        // takeAway.deliveryFood();

    }
}

abstract class JiangWei{
    String name;
    abstract void killLobster();
    abstract void cookLobster();
    abstract void serveClient();
    abstract void deliveryFood();

    public JiangWei(String name) {
        this.name = name;
    }
}


class BranchRestaurant extends JiangWei{
//    String name;
    List<JiangWei> branches = new ArrayList<>();

    public BranchRestaurant(String name) {
        super(name);
    }

    @Override
    public void killLobster() {
        System.out.println("branch kill lobster.");
    }

    @Override
    public void cookLobster() {
        System.out.println("branch cook lobster.");
    }

    @Override
    public void serveClient() {
        System.out.println("branch serve lobster");
    }

    @Override
    public void deliveryFood() {
        System.out.println("branch delivery lobster");
    }

    public void addBranch(JiangWei branch) {
        branches.add(branch);
    }

    public void removeBranch(JiangWei branch) {
        branches.remove(branch);
    }

    public void displayBranches() {
        for (JiangWei j: branches) {
            System.out.println(j.getClass() + "\t" + j.name);
        }
    }
}
// Version 1:
//class BranchRestaurant extends JiangWei{
//
//    private TakeAway takeAway;
//
//    public BranchRestaurant(TakeAway takeAway) {
//        this.takeAway = takeAway;
//    }
//
//    @Override
//    public void killLobster() {
//        System.out.println("kill lobster");
//    }
//
//    @Override
//    public void cookLobster() {
//        System.out.println("restaurant cook lobster");
//    }
//
//    @Override
//    public void serveClient() {
//        System.out.println("serve client");
//    }
//
//    @Override
//    public void deliveryFood() {
//        takeAway.deliveryFood();
//    }
//}

class TakeAway extends JiangWei{

    public TakeAway(String name) {
        super(name);
    }

    @Override
    public void killLobster() {
        throw new UnsupportedOperationException("can't kill lobster.");
    }

    @Override
    public void cookLobster() {
        System.out.println("takeaway cook lobster.");
    }

    @Override
    public void serveClient() {
        throw new UnsupportedOperationException("can't server client.");
    }

    @Override
    public void deliveryFood() {
        System.out.println("delivery lobster.");
    }
}