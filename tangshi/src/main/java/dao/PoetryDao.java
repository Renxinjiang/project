package dao;

import java.util.List;
import java.util.Map;

public interface PoetryDao {
    /**
     * 根据用户传上来的参数进行查询
     * @param left 下限
     * @param right 上限
     */
    List<Echart> findByAmount(int left, int right);

    /**
     * 查询词语
     * @param name 按照诗人的姓名
     */
    Map<String, Integer> findWordsByName(String name);

    /**
     * 按照名字的个数查找诗人
     * @param i 名字的个数
     */
    List<String> findPoetByName(int i);
}

