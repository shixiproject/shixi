$(function () {
    // char1();
    // char2();
    // char3();
    // char4();
})

//统计分析图
function char1(res) {
    var myChart = echarts.init($("#char1")[0]);
    var tempdata = [];
    res = JSON.parse(res);
    for (var i = 0; i < res.length; i++) {
        if (res[i].status != '') {
            tempdata.push({ value: res[i].num, name: res[i].status })
        }
    }
    console.log(tempdata)
    option = {
        tooltip: {
            trigger: 'item'
        },
        legend: {
            top: '1%',
            left: 'center',
            textStyle: {
                color: '#ffffff',
            }
        },
        series: [
            {
                name: 'Access From',
                type: 'pie',
                radius: ['40%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    show: false,
                    position: 'center'
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: '40',
                        fontWeight: 'bold'
                    }
                },
                labelLine: {
                    show: false
                },
                data: tempdata
            }
        ]
    };
    myChart.setOption(option);
    window.addEventListener('resize', function () { myChart.resize(); })

}
function char2(res) {
    var myChart = echarts.init($("#char2")[0]);

    var tempdata = [];
    res = JSON.parse(res);

    for (var i = 0; i < res.length; i++) {
        if (res[i].num > 10) {
            tempdata.push({ value: res[i].num, name: res[i].crmcddesc })
        }
    }
    tempdata.sort(function (a, b) {
        return b.value - a.value//倒序
    })
    tempdata = tempdata.slice(0, 15)
    var temp = []
    var temp2 = []
    for (var a = 0; a < tempdata.length; a++) {
        temp.push(tempdata[a].name)
    }
    for (var b = 0; b < tempdata.length; b++) {
        temp2.push(tempdata[b].value)
    }
    console.log(temp)
    option = {
        xAxis: {
            type: 'category',
            data: temp
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                data: temp2,
                type: 'bar'
            }
        ]
    };
    myChart.setOption(option);
    window.addEventListener('resize', function () { myChart.resize(); })

}
function char3(res) {
    var myChart = echarts.init($("#char3")[0]);
    var tempdata = [];
    res = JSON.parse(res);
    var numlist = []
    for (var i = 0; i < 20; i++) {
        numlist.push(0)
    }
    for (var i = 0; i < res.length; i++) {
        if (res[i].timeocc < '01:15') {
            numlist[1] += res[i].num
        } else if (res[i].timeocc < '02:30') {
            numlist[2] += res[i].num
        } else if (res[i].timeocc < '03:45') {
            numlist[3] += res[i].num
        } else if (res[i].timeocc < '05:00') {
            numlist[4] += res[i].num
        } else if (res[i].timeocc < '06:15') {
            numlist[5] += res[i].num
        } else if (res[i].timeocc < '07:30') {
            numlist[6] += res[i].num
        } else if (res[i].timeocc < '08:45') {
            numlist[7] += res[i].num
        } else if (res[i].timeocc < '10:00') {
            numlist[8] += res[i].num
        } else if (res[i].timeocc < '11:15') {
            numlist[9] += res[i].num
        } else if (res[i].timeocc < '12:30') {
            numlist[10] += res[i].num
        } else if (res[i].timeocc < '13:45') {
            numlist[11] += res[i].num
        } else if (res[i].timeocc < '15:00') {
            numlist[12] += res[i].num
        } else if (res[i].timeocc < '16:15') {
            numlist[13] += res[i].num
        } else if (res[i].timeocc < '17:30') {
            numlist[14] += res[i].num
        } else if (res[i].timeocc < '18:45') {
            numlist[15] += res[i].num
        } else if (res[i].timeocc < '20:00') {
            numlist[16] += res[i].num
        } else if (res[i].timeocc < '21:15') {
            numlist[17] += res[i].num
        } else if (res[i].timeocc < '22:30') {
            numlist[18] += res[i].num
        } else if (res[i].timeocc < '23:45') {
            numlist[19] += res[i].num
        } else {
            numlist[0] += res[i].num
        }
    }
    // console.log(numlist)

    option = {
        tooltip: {
            trigger: 'axis',
            axisPointer: {
                type: 'cross'
            }
        },
        xAxis: {
            type: 'category',
            boundaryGap: false,
            // prettier-ignore
            data: ['00:00', '01:15', '02:30', '03:45', '05:00', '06:15', '07:30', '08:45', '10:00', '11:15', '12:30', '13:45', '15:00', '16:15', '17:30', '18:45', '20:00', '21:15', '22:30', '23:45']
        },
        yAxis: {
            type: 'value',
            axisLabel: {
                formatter: '{value}'
            },
            axisPointer: {
                snap: true
            }
        },
        visualMap: {
            show: false,
            dimension: 0,
            pieces: [
                {
                    lte: 6,
                    color: 'green'
                },
                {
                    gt: 6,
                    lte: 8,
                    color: 'red'
                },
                {
                    gt: 8,
                    lte: 14,
                    color: 'green'
                },
                {
                    gt: 14,
                    lte: 17,
                    color: 'red'
                },
                {
                    gt: 17,
                    color: 'green'
                }
            ]
        },
        series: [
            {
                name: 'Electricity',
                type: 'line',
                smooth: true,
                // prettier-ignore
                data: numlist,
                markArea: {
                    itemStyle: {
                        color: 'rgba(255, 173, 177, 0.4)'
                    },
                    data: [
                        [
                            {
                                name: 'Morning Peak',
                                xAxis: '07:30'
                            },
                            {
                                xAxis: '10:00'
                            }
                        ],
                        [
                            {
                                name: 'Evening Peak',
                                xAxis: '17:30'
                            },
                            {
                                xAxis: '21:15'
                            }
                        ]
                    ]
                }
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () { myChart.resize(); })

}
function char4(res) {

    var myChart = echarts.init($("#char4")[0]);
    console.log(res)
    res=JSON.parse(res)
    list=[]
    for(var i=0;i<res.length;i++){
        if(res[i].premisdesc!=""){
            list.push({value:res[i].num,name:res[i].premisdesc})
        }
    }
    console.log(list)
    list.sort(function (a, b) {
        return b.value - a.value
    })
    list=list.slice(0,10)
    console.log(list)

    option = {
        tooltip: {
            trigger: 'item'
        },
        // legend: {
        //     orient: 'vertical',
        //     left: 'left'
        // },
        series: [
            {
                name: 'Access From',
                type: 'pie',
                radius: '50%',
                data: list,
                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    myChart.setOption(option);
    window.addEventListener('resize', function () { myChart.resize(); })

}
