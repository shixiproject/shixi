$(function () {
    initMap();
})
//地图界面高度设置
//加载地图
function initMap() {
    // 百度地图API功能
    var map = new BMapGL.Map("map_div");    // 创建Map实例
    var point = new BMapGL.Point(-118.2695, 33.9825)
    map.centerAndZoom(point, 11);  // 初始化地图,设置中心点坐标和地图级别
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

    //添加地图类型控件
    var size1 = new BMapGL.Size(10, 20);
    map.addControl(new BMapGL.MapTypeControl({
        offset: size1,
        mapTypes: [
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]
    }));

    // 编写自定义函数,创建标注
    // function addMarker(point) {
    //     var marker = new BMapGL.Marker(point);
    //     map.addOverlay(marker);
    // }

    // 创建点标记
    var point1=new BMapGL.Point(-118.2937,33.977);
    var point2=new BMapGL.Point(-118.435, 34.2234);
    var point3=new BMapGL.Point(-118.2783, 34.0002);
    var point4=new BMapGL.Point(-118.2727, 34.063);
    var point5=new BMapGL.Point(-118.3078,34.0174);
    var point6=new BMapGL.Point(-118.5975,34.2047);
    var point7=new BMapGL.Point(-118.4662,34.1538);
    var point8=new BMapGL.Point(-118.3213,34.0399);
    var point9=new BMapGL.Point(-118.2499,34.0453);
    var point10=new BMapGL.Point(-118.5022,34.272);
    var point11=new BMapGL.Point(-118.4019,34.2647);
    var point12=new BMapGL.Point(-118.262,33.7742);
    var point13=new BMapGL.Point(-118.1985,34.0337);
    var point14=new BMapGL.Point(-118.349,34.1034);
    var point15=new BMapGL.Point(-118.377,34.1689);
    var point16=new BMapGL.Point(-118.3962,33.9573);
    var point17=new BMapGL.Point(-118.5134,34.1769);
    var point18=new BMapGL.Point(-118.2204,34.0992);
    var point19=new BMapGL.Point(-118.2891,34.0537);
    var point20=new BMapGL.Point(-118.2739,33.9348);
    var point21=new BMapGL.Point(-118.5393,34.0528);

    var marker1 = new BMapGL.Marker(point1);
    var marker2 = new BMapGL.Marker(point2);
    var marker3 = new BMapGL.Marker(point3);
    var marker4 = new BMapGL.Marker(point4);
    var marker5 = new BMapGL.Marker(point5);
    var marker6 = new BMapGL.Marker(point6);
    var marker7 = new BMapGL.Marker(point7);
    var marker8 = new BMapGL.Marker(point8);
    var marker9 = new BMapGL.Marker(point9);
    var marker10 = new BMapGL.Marker(point10);
    var marker11 = new BMapGL.Marker(point11);
    var marker12 = new BMapGL.Marker(point12);
    var marker13 = new BMapGL.Marker(point13);
    var marker14 = new BMapGL.Marker(point14);
    var marker15 = new BMapGL.Marker(point15);
    var marker16 = new BMapGL.Marker(point16);
    var marker17 = new BMapGL.Marker(point17);
    var marker18 = new BMapGL.Marker(point18);
    var marker19 = new BMapGL.Marker(point19);
    var marker20 = new BMapGL.Marker(point20);
    var marker21 = new BMapGL.Marker(point21);

    // 在地图上添加点标记
    map.addOverlay(marker1);
    map.addOverlay(marker2);
    map.addOverlay(marker3);
    map.addOverlay(marker4);
    map.addOverlay(marker5);
    map.addOverlay(marker6);
    map.addOverlay(marker7);
    map.addOverlay(marker8);
    map.addOverlay(marker9);
    map.addOverlay(marker10);
    map.addOverlay(marker11);
    map.addOverlay(marker12);
    map.addOverlay(marker13);
    map.addOverlay(marker14);
    map.addOverlay(marker15);
    map.addOverlay(marker16);
    map.addOverlay(marker17);
    map.addOverlay(marker18);
    map.addOverlay(marker19);
    map.addOverlay(marker20);
    map.addOverlay(marker21);
    // hover
    // map.addEventListener("onmousemove",function(e){
    //     if(map_div.contains(window.event.srcElement))
    //     {
    //         console.log(e.point.lng + "," + e.point.lat);
    //     }
    // });

    // 创建信息窗口
    var opts1 = {
        width: 200,
        height: 100,
        title: '77th Street'
    };
    var opts2 = {
        width: 200,
        height: 100,
        title: 'Mission'
    };
    var opts3 = {
        width: 200,
        height: 100,
        title: 'Newton'
    };
    var opts4 = {
        width: 200,
        height: 100,
        title: 'Rampart'
    };
    var opts5 = {
        width: 200,
        height: 100,
        title: 'Southwest'
    };
    var opts6 = {
        width: 200,
        height: 100,
        title: 'Topanga'
    };
    var opts7 = {
        width: 200,
        height: 100,
        title: 'Van Nuys'
    };
    var opts8 = {
        width: 200,
        height: 100,
        title: 'Wilshire'
    };
    var opts9 = {
        width: 200,
        height: 100,
        title: 'Central'
    };
    var opts10 = {
        width: 200,
        height: 100,
        title: 'Devonshire'
    };
    var opts11 = {
        width: 200,
        height: 100,
        title: 'Foothill'
    };
    var opts12 = {
        width: 200,
        height: 100,
        title: 'Harbor'
    };
    var opts13 = {
        width: 200,
        height: 100,
        title: 'Hollenbeck'
    };
    var opts14 = {
        width: 200,
        height: 100,
        title: 'Hollywood'
    };
    var opts15 = {
        width: 200,
        height: 100,
        title: 'N Hollywood'
    };
    var opts16 = {
        width: 200,
        height: 100,
        title: 'Pacific'
    };
    var opts17 = {
        width: 200,
        height: 100,
        title: 'West Valley'
    };
    var opts18 = {
        width: 200,
        height: 100,
        title: 'Northeast'
    };
    var opts19 = {
        width: 200,
        height: 100,
        title: 'Olympic'
    };
    var opts20 = {
        width: 200,
        height: 100,
        title: 'Southeast'
    };var opts21 = {
        width: 200,
        height: 100,
        title: 'West LA'
    };
    var infoWindow1 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts1);
    var infoWindow2 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts2);
    var infoWindow3 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts3);
    var infoWindow4 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts4);
    var infoWindow5 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts5);
    var infoWindow6 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts6);
    var infoWindow7 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts7);
    var infoWindow8 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts8);
    var infoWindow9 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts9);
    var infoWindow10 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts10);
    var infoWindow11 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts11);
    var infoWindow12 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts12);
    var infoWindow13 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts13);
    var infoWindow14 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts14);
    var infoWindow15 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts15);
    var infoWindow16 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts16);
    var infoWindow17 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts17);
    var infoWindow18 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts18);
    var infoWindow19 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts19);
    var infoWindow20 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts20);
    var infoWindow21 = new BMapGL.InfoWindow('地址：北京市东城区王府井大街88号乐天银泰百货八层', opts21);

    // 点标记添加点击事件
    marker1.addEventListener('click', function () {
        map.openInfoWindow(infoWindow1, point1); // 开启信息窗口
        
    });
    marker2.addEventListener('click', function () {
        map.openInfoWindow(infoWindow2, point2); // 开启信息窗口
    });
    marker3.addEventListener('click', function () {
        map.openInfoWindow(infoWindow3, point3); // 开启信息窗口
    });
    marker4.addEventListener('click', function () {
        map.openInfoWindow(infoWindow4, point4); // 开启信息窗口
    });
    marker5.addEventListener('click', function () {
        map.openInfoWindow(infoWindow5, point5); // 开启信息窗口
    });
    marker6.addEventListener('click', function () {
        map.openInfoWindow(infoWindow6, point6); // 开启信息窗口
    });
    marker7.addEventListener('click', function () {
        map.openInfoWindow(infoWindow7, point7); // 开启信息窗口
    });
    marker8.addEventListener('click', function () {
        map.openInfoWindow(infoWindow8, point8); // 开启信息窗口
    });
    marker9.addEventListener('click', function () {
        map.openInfoWindow(infoWindow9, point9); // 开启信息窗口
    });
    marker10.addEventListener('click', function () {
        map.openInfoWindow(infoWindow10, point10); // 开启信息窗口
    });
    marker11.addEventListener('click', function () {
        map.openInfoWindow(infoWindow11, point11); // 开启信息窗口
    });
    marker12.addEventListener('click', function () {
        map.openInfoWindow(infoWindow12, point12); // 开启信息窗口
    });
    marker13.addEventListener('click', function () {
        map.openInfoWindow(infoWindow13, point13); // 开启信息窗口
    });
    marker14.addEventListener('click', function () {
        map.openInfoWindow(infoWindow14, point14); // 开启信息窗口
    });
    marker15.addEventListener('click', function () {
        map.openInfoWindow(infoWindow15, point15); // 开启信息窗口
    });
    marker16.addEventListener('click', function () {
        map.openInfoWindow(infoWindow16, point16); // 开启信息窗口
    });
    marker17.addEventListener('click', function () {
        map.openInfoWindow(infoWindow17, point17); // 开启信息窗口
    });
    marker18.addEventListener('click', function () {
        map.openInfoWindow(infoWindow18, point18); // 开启信息窗口
    });
    marker19.addEventListener('click', function () {
        map.openInfoWindow(infoWindow19, point19); // 开启信息窗口
    });
    marker20.addEventListener('click', function () {
        map.openInfoWindow(infoWindow20, point20); // 开启信息窗口
    });
    marker21.addEventListener('click', function () {
        map.openInfoWindow(infoWindow21, point21); // 开启信息窗口
    });
    
    // 点击
    // map.addEventListener("click", function (e) {
    //     if (map_div.contains(window.event.srcElement)) {
    //         console.log(e.point.lng + "," + e.point.lat);
    //     }

    // });
    // 设备地图颜色
    // var mapStyle = {
    //     style: "midnight"
    // };
    // map.setMapStyle(mapStyle);

}

