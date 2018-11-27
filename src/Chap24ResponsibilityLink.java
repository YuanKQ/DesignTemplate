/**
 * @Auther: yuan
 * @Date: 18-10-31 19:55
 * @Description: Chapter24 责任链模式。
 * 某快餐分店是受分店店长管理的，分店店长受区域经理管理，区域经理对总经理汇报。他们的职权如下：
 * 分店店长：分店店面管理
 * 分店店长＋区域经理：分店数量的管理
 * 分店店长＋区域经理＋总经理：套餐更加地区特色而增加或修改。
 * 现采用责任链模式完成上述场景。
 *
 * Thinking:
 * task应该有一个字段，记录该task应该由那些人负责
 */
public class Chap24ResponsibilityLink {
    public static void main(String[] args) {
        Task t = new Task("manage branch");
        Task t1 = new Task("manage branch number");
        Task t2 = new Task("manage food set");
        Task t3 = new Task("hahahaha");

        GeneralManager generalManager = new GeneralManager(null);
        RegionalManager regionalManager = new RegionalManager(generalManager);
        BranchManager branchManager = new BranchManager(regionalManager);
        branchManager.handleTask(t);
        branchManager.handleTask(t1);
        branchManager.handleTask(t2);
        branchManager.handleTask(t3);
    }
}

class Task {
    private String content;

    public Task(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

class RestuarantStaff{
    RestuarantStaff superior;

    public RestuarantStaff(RestuarantStaff superior) {
        this.superior = superior;
    }

    public void handleTask(Task t) {}
}

class BranchManager extends RestuarantStaff{
    public BranchManager(RestuarantStaff superior) {
        super(superior);
    }

    @Override
    public void handleTask(Task t) {
        if (t.getContent().equals("manage branch")){
            System.out.println("finish task of managing branch");
        } else {
            this.superior.handleTask(t);
        }
    }
}

class RegionalManager extends RestuarantStaff{
    public RegionalManager(RestuarantStaff superior) {
        super(superior);
    }

    @Override
    public void handleTask(Task t) {
        if (t.getContent().equals("manage branch number")){
            System.out.println("finish task of managing branch number");
        } else {
            this.superior.handleTask(t);
        }
    }
}

class GeneralManager extends RestuarantStaff{
    public GeneralManager(RestuarantStaff superior) {
        super(superior);
    }

    @Override
    public void handleTask(Task t) {
        if (t.getContent().equals("manage food set")){
            System.out.println("finish task of managing food set");
        } else {
            System.out.println("wrong task");
        }

    }
}