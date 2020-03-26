package dao;

/**
 * @PackageName: dao
 * @ClassName: Echart
 * @Description: 这个类柱状图用到的数据
 * @author: 呆呆
 * @date: 2020/3/24
 */

public class Echart {

    private String name;  //诗人的名字
    private int count;    //诗人的作诗量


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "Echart{" +
                "name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}