$.ajax({
    method: "get",
    url: "words",
    dataType: "json",
    success: function (jsonData) {
        var myChart = echarts.init(document.getElementById('main'));

        var data = [];
        for (var i in jsonData) {
            var word = jsonData[i][0];
            var count = jsonData[i][1];
            data.push({
                name: word,
                value: count,
                textStyle: {
                    normal: {},
                    emphasis: {}
                }
            });
        }
        console.log(data);
        var option = {
            title: {
                text: '诗词用词云图'
            },
            series: [{
                type: 'wordCloud',
                shape: 'pentagon',
                left: 'center',
                top: 'center',
                width: '80%',
                height: '80%',
                right: 'center',
                bottom: 'center',
                sizeRange: [12, 60],
                rotationRange: [-90, 90],
                rotationStep: 45,
                gridSize: 8,
                drawOutOfBound: false,
                textStyle: {
                    normal: {
                        fontFamily: 'sans-serif',
                        fontWeight: 'bold',
                        color: function () {
                            return 'rgb(' + [
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160),
                                Math.round(Math.random() * 160)
                            ].join(',') + ')';
                        }
                    },
                    emphasis: {
                        shadowBlur: 10,
                        shadowColor: '#333'
                    }
                },
                data: data
            }]
        };

        myChart.setOption(option);
    }
});
