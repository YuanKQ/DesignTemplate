/**
 * @Auther: yuan
 * @Date: 18-9-1 17:37
 * @Description: Chapter 7 代理模式
 *  请以代理模型实现一下场景：
 *  zuo jia yi请 dai li 送礼物给 jiao jiao
 *
 *  version 1:
 *  jiao jiao应该是zuo jia yi的参数或者成员变量　==> 代理负责传递参数给RealObject，将调用结果返回给请求方。
 *  zuo jia yi 在giveXXX method中不需要点明自己姓名，即成员变量name冗余了 ==> 请求方并不需要知道调用结果是来自于代理还是RealObject的。
 */
public class Chap07Proxy {

    public static void main(String[] args) {
        Persuit persuit = new Persuit("jiao jiao");
        Delegate delegate = new Delegate(persuit);
        delegate.giveDoll();
        delegate.giveChocolate();
        delegate.giveFlower();
       /*
       // version 1:
       Persuit persuit = new Persuit("zuo jia yi");
       Delegate delegate = new Delegate(persuit, "dai li", "jiao jiao");
       delegate.giveDoll();
       delegate.giveChocolate();
       delegate.giveFlower();
        */
    }
}

interface GiveGifts{
    void giveDoll();
    void giveFlower();
    void giveChocolate();
}

class Persuit implements GiveGifts{
    private String girlName;

    void buyDoll(){
        System.out.println(" buy a doll.");
    }

    void buyFlower(){
        System.out.println(" buy a flower.");
    }

    void buyChocolate(){
        System.out.println(" buy a chocolate.");
    }

    public Persuit(String name) {
        this.girlName = name;
    }

    @Override
    public void giveDoll() {
        buyDoll();
        System.out.println("give " + girlName);
    }

    @Override
    public void giveFlower() {
        buyFlower();
        System.out.println("give " + girlName);

    }

    @Override
    public void giveChocolate() {
        buyFlower();
        System.out.println("give " + girlName);

    }
}


class Delegate implements GiveGifts {
    private Persuit persuit;

    public Delegate(Persuit persuit) {
        this.persuit = persuit;
    }

    @Override
    public void giveDoll() {
        persuit.giveDoll();
        // 添加额外服务
        System.out.println("===========");

    }

    @Override
    public void giveFlower() {
        persuit.giveDoll();
        // 添加额外服务
        System.out.println("===========");
    }

    @Override
    public void giveChocolate() {
        persuit.giveChocolate();
        // 添加额外服务
        System.out.println("===========");
    }
}
/*
// version 1:
class Persuit implements GiveGifts{
    private String name;

    void buyDoll(){
        System.out.println(name + " buy a doll.");
    }

    void buyFlower(){
        System.out.println(name + " buy a flower.");
    }

    void buyChocolate(){
        System.out.println(name + " buy a chocolate.");
    }

    public Persuit(String name) {
        this.name = name;
    }

    @Override
    public void giveDoll() {
        buyDoll();
    }

    @Override
    public void giveFlower() {
        buyFlower();
    }

    @Override
    public void giveChocolate() {
        buyFlower();
    }
}

class Delegate implements GiveGifts{
    private Persuit persuit;

    private String name;

    private String girlName;

    public Delegate(Persuit persuit, String name, String girlName) {
        this.persuit = persuit;
        this.name = name;
        this.girlName = girlName;
    }

    @Override
    public void giveDoll() {
        persuit.giveDoll();
        System.out.println(name + " give a doll to " + girlName);
        System.out.println("===========");

    }

    @Override
    public void giveFlower() {
        persuit.giveDoll();
        System.out.println(name + " give a flower to " + girlName);
        System.out.println("===========");
    }

    @Override
    public void giveChocolate() {
        persuit.giveChocolate();
        System.out.println(name + " give a chocolate to " + girlName);
        System.out.println("===========");
    }
}*/
