// var是variable
// document是js中的HTML文档对象
// document.getElement('main')找到HTML文档中的id为main的元素
// 基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

// 指定图表的配置项和数据
var option = {
    // 图表的标题
    title: {
        text: 'ECharts 入门示例'
    },
    tooltip: {},
    legend: {
        data:['销量']
    },
    // 横坐标
    xAxis: {
        data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
    },
    // 纵坐标
    yAxis: {},
    series: [{
        name: '销量',
        type: 'bar',  // bar代表柱状图
        data: [5, 20, 36, 10, 10, 20]  // 对应的每一个的数据
    }]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);