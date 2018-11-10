import com.sun.corba.se.spi.orbutil.threadpool.Work;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: yuan
 * @Date: 18-10-11 10:46
 * @Description: Chapter 09 原型模式
 * 通常简历要求包含以下信息：
 * 姓名，年龄，身份证号，若干工作经历（工作时间，公司，职位）
 * 随着年龄的增长，工作经历日渐丰富，简历的内容也会有所改变。
 * 试采用原型模式完成制作多份不同简历的最有效率方式。
 *
 * 其本质为深拷贝, https://blog.csdn.net/zhangjg_blog/article/details/18369201
 */
public class Chap09Prototype {
    public static void main(String[] args) throws CloneNotSupportedException {
        Resume zhang3 = new Resume();
        zhang3.setName("zhang3");
        zhang3.setAge(18);
//        zhang3.addExperience(new WorkExperience("1999~2001", "Mcdonald", "sales"));
        zhang3.addExperience(new WorkExperience("1999~2001", "Mcdonald", "sales"));
        System.out.println(zhang3);

        Resume chageName = (Resume)zhang3.clone();
        chageName.setName("changeName");
        System.out.println(chageName);

        Resume chageAge = (Resume)zhang3.clone();
        chageAge.setAge(20);
        System.out.println(chageAge);

        Resume addWork = (Resume)zhang3.clone();
        addWork.addExperience(new WorkExperience("2001~2005", "KFC", "sales manager"));
//        addWork.setExperience(new WorkExperience("2001~2005", "KFC", "sales manager"));
        System.out.println(addWork);
    }

}

class WorkExperience implements Cloneable, Serializable{
    String time;
    String company;
    String job;

    @Override
    public String toString() {
        return "WorkExperience{" +
                "time='" + time + '\'' +
                ", company='" + company + '\'' +
                ", job='" + job + '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public WorkExperience(String time, String company, String job) {
        this.time = time;
        this.company = company;
        this.job = job;
    }
}

class Resume implements Cloneable, Serializable{
    private String name;
    private int age;
    private List<WorkExperience> experiences;
//    private WorkExperience experience;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<WorkExperience> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<WorkExperience> experiences) {
        this.experiences = experiences;
    }

    public void addExperience(WorkExperience e) {
        if (this.experiences == null) {
            this.experiences = new ArrayList<>();
        }
        this.experiences.add(e);
    }


//    public WorkExperience getExperience() {
//        return experience;
//    }
//
//    public void setExperience(WorkExperience experience) {
//        this.experience = experience;
//    }

    @Override
    public String toString() {
        return "Resume{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", experiences='" + experiences+ '\'' +
                '}';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object cloneResume = (Resume)super.clone();
//        ((Resume) cloneResume).setExperiences(deepCopyExperiences());
        ((Resume) cloneResume).setExperiences(deepCopyExperiences(this.experiences));
        return cloneResume;
    }

    //    protected Object deepClone() throws CloneNotSupportedException {
//        Resume r = (Resume)super.clone();
//        r.experiences = this.depCopyExperiences();
//        return r;
//    }
//
    public List<WorkExperience> deepCopyExperiences() throws CloneNotSupportedException {
        List<WorkExperience> workExperienceList = new ArrayList<>();
        for (WorkExperience e: this.experiences) {
            workExperienceList.add((WorkExperience) e.clone());
        }

        return workExperienceList;
    }

    public <T> List<T> deepCopyExperiences(List<? extends T> srcList) {
        ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
        try {
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(srcList);

            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            List<T> destList = (List<T>)in.readObject();
            return destList;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
}