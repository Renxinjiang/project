package dao;

/**
 * @PackageName: dao
 * @ClassName: ResultInfo
 * @Description:
 * @author: 呆呆
 * @date: 2020/3/24
 */
public class ResultInfo {
    //用来存储数据
    private Object data;

    //判断是否是成功的响应
    private boolean flag = true;

    //错误的理由
    private String reason;

    public ResultInfo() {
    }

    public ResultInfo(boolean flag, String reason) {
        this.flag = flag;
        this.reason = reason;
    }

    public ResultInfo(Object data) {
        this.data = data;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "ResultInfo{" +
                "data=" + data +
                ", flag=" + flag +
                ", reason='" + reason + '\'' +
                '}';
    }
}