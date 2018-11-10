import javax.crypto.Mac;

/**
 * @Auther: yuan
 * @Date: 18-10-12 16:40
 * @Description: Chapter 13 Builder DesignTemplate
 * 过年了，小明家包了各式各样的饺子：
 * 1. 墨鱼猪肉饺子
 * 2. 菠菜猪肉饺子
 * 3. 南瓜猪肉胡萝卜饺子
 * 4. 鲅鱼饺子
 * 根据菜谱, 这些饺子都具有相同的制作步骤：
 * 1. 制作饺子皮
 * 2. 制作饺子馅儿
 * 3.　包饺子
 * 请使用建造者模式帮助小明包饺子。
 *
 * 建造者模式是为了获得由不同的part经过相同的处理流程得到的实例，是为了获得实例，为了获得实例，获得实例，所以会有返回构造实例的方法，本文是getDumpling()
 */
public class Chap13Builder {
    public static void main(String[] args) {
        DumplingMenu menu = new DumplingMenu();
        PorkCuttleDumplingMaker porkCuttleDumplingMaker = new PorkCuttleDumplingMaker();
        menu.wrapDumpling(porkCuttleDumplingMaker);
        Dumpling porkCuttleDumpling = porkCuttleDumplingMaker.getDumpling();
        System.out.println(porkCuttleDumpling);

        PorkSpinachDumplingMaker porkSpinachDumplingMaker = new PorkSpinachDumplingMaker();
        menu.wrapDumpling(porkSpinachDumplingMaker);
        Dumpling porkSpinachDumpling = porkCuttleDumplingMaker.getDumpling();
        System.out.println(porkSpinachDumpling);

        PorkCarrotPumpkinDumplingMake porkCarrotPumpkinDumplingMaker = new PorkCarrotPumpkinDumplingMake();
        menu.wrapDumpling(porkCarrotPumpkinDumplingMaker);
        Dumpling porkCarrotPumpkinDumpling = porkCarrotPumpkinDumplingMaker.getDumpling();
        System.out.println(porkCarrotPumpkinDumpling);

        MackerelDumplingMaker mackerelDumplingMaker = new MackerelDumplingMaker();
        menu.wrapDumpling(mackerelDumplingMaker);
        Dumpling mackerelDumpling = mackerelDumplingMaker.getDumpling();
        System.out.println(mackerelDumpling);

    }
}


class Dumpling{
    String wrapper;
    String stuffing;

    public void setWrapper(String wrapper) {
        this.wrapper = wrapper;
    }

    public void setStuffing(String stuffing) {
        this.stuffing = stuffing;
    }


    @Override
    public String toString() {
        return "Dumpling { \n\twrapper: " + wrapper + ";\t\nstuffing: " + stuffing + ";}\n\n";
    }
}

interface DumplingMaker{

    void makeWrapper();
    void makeStuffing();
    Dumpling getDumpling(); //version one doesn't have this method
}

class PorkCuttleDumplingMaker implements DumplingMaker{
    Dumpling dumpling = new Dumpling();

    @Override
    public void makeWrapper() {
        dumpling.setWrapper("cuttle juice wrapper");
    }

    @Override
    public void makeStuffing() {
        dumpling.setStuffing("pork stuffing");
    }

    @Override
    public Dumpling getDumpling() {
        System.out.println("PorkCuttleDumpling");
        return dumpling;
    }
}

class PorkSpinachDumplingMaker implements DumplingMaker{
    Dumpling dumpling = new Dumpling();
    @Override
    public void makeWrapper() {
        dumpling.setWrapper("Spinach wrapper");
    }

    @Override
    public void makeStuffing() {
        dumpling.setStuffing("pork stuffing");
    }

    @Override
    public Dumpling getDumpling() {
        System.out.println("PorkSpinachDumplingMaker");
        return dumpling;
    }
}

class PorkCarrotPumpkinDumplingMake implements DumplingMaker{
    Dumpling dumpling = new Dumpling();

    @Override
    public void makeWrapper() {
        dumpling.setWrapper("pumpkin wrapper");
    }

    @Override
    public void makeStuffing() {
        dumpling.setStuffing("pork carrot stuffing");
    }

    @Override
    public Dumpling getDumpling() {
        System.out.println("PorkCarrotPumpkinDumplingMake");
        return dumpling;
    }
}

class MackerelDumplingMaker implements DumplingMaker{
    Dumpling dumpling = new Dumpling();

    @Override
    public void makeWrapper() {
        dumpling.setWrapper("normal wrapper");
    }

    @Override
    public void makeStuffing() {
        dumpling.setStuffing("mackerel stuffing");
    }

    @Override
    public Dumpling getDumpling() {
        System.out.println("mackerel dumpling");
        return dumpling;
    }
}

class DumplingMenu {
    public void wrapDumpling(DumplingMaker dumplingMaker){
        dumplingMaker.makeWrapper();
        dumplingMaker.makeStuffing();
    }
}
