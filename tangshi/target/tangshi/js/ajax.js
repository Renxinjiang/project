/*
interface Success {
    void callback(String data);
}

class Option {
    String method;
    String url;
    String dataType;
    Success callback;
}

class $ {
    public static void ajax(Option option) {
        发起 ajax 情况
    }
}

$.ajax(new Option(
    method:
    url:
    dataType:
    成功之后的会调用我的什么方法进行数据处理
));
 */
$.ajax(
    {
        method: "get",  // 发起 ajax 请求时，使用什么 http 方法
        url: "static-ranktest.json",   // 请求哪个 url
        dataType: "json",   // 返回的数据当成什么格式解析
        success: function (data) {  // 成功后，执行什么方法
            console.log("昕昕-Begin")
            console.log(data);
            console.log("昕昕-End")
        }
    }
);
