package poetry.service;

import dao.Echart;
import dao.PoetryDao;
import dao.PoetryDaoImpl;

import java.util.List;
import java.util.Map;

/**
 * @PackageName: poetry.service
 * @ClassName: PoetryService
 * @Description:
 * @author: 呆呆
 * @date: 2020/3/24
 */
public class PoetryService {
    private PoetryDao poetryDao = new PoetryDaoImpl();

    public List<Echart> findByAmount(int left, int right) {
        return poetryDao.findByAmount(left,right);
    }

    public Map<String, Integer> findWordsByName(String name) {
        return poetryDao.findWordsByName(name);
    }

    public List<String> findPoetByName(int i) {
        return poetryDao.findPoetByName(i);
    }
}
