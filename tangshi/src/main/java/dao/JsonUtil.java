package dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @PackageName: dao
 * @ClassName: JsonUtil
 * @Description: 返回转换后的json字符串
 * @author: 呆呆
 * @date: 2020/3/24
 */
public class JsonUtil {
    public static String toJson(ResultInfo info) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(info);
    }
}
