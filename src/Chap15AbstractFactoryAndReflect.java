import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Auther: yuan
 * @Date: 18-10-28 10:46
 * @Description: Chapter15 抽象工厂模式
 *  * 某公司附近有３家快餐店Ａ／Ｂ，均提供炒菜（炒牛肉/炖茄子），主食（米饭/面条），饮品（豆浆/juice）．
 *  * 由于每个餐厅的做法不一样，各具风味．
 *  * 使用反射的方式进一步优化抽象工厂模式
 *
 *  优势：
 *  * 不需要通过工厂即可获得不同产品的实例。　
 *  * 添加新的产品只需要添加新的产品类，工厂类可以删减掉。
 */
public class Chap15AbstractFactoryAndReflect {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        FriedBeef friedBeef = FriedBeef.class.newInstance();
        friedBeef.fry();
        Drink juice = OrangeJuice.class.newInstance();
        juice.juicing();
        Rice rice = Rice.class.newInstance();
        rice.steam();
    }
}
