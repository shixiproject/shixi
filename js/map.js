
$(function () {
    initMap();
})
//地图界面高度设置



//加载地图
function initMap() {
    // 百度地图API功能
    var map = new BMapGL.Map("map_div");    // 创建Map实例
    map.centerAndZoom(new BMapGL.Point(116.718, 40.142), 11);  // 初始化地图,设置中心点坐标和地图级别
    //添加地图类型控件
    var size1 = new BMapGL.Size(10, 20);
    map.addControl(new BMapGL.MapTypeControl({
        offset: size1,
        mapTypes: [
            BMAP_NORMAL_MAP,
            BMAP_HYBRID_MAP
        ]
    }));

    var bd = new BMapGL.Boundary();
    bd.get('顺义区', function (rs) {
        // console.log('外轮廓：', rs.boundaries[0]);
        // console.log('内镂空：', rs.boundaries[1]);
        var hole = new BMapGL.Polygon(rs.boundaries, {
            fillColor: 'blue',
            fillOpacity: 0.2
        });
        map.addOverlay(hole);
    });
    // 编写自定义函数,创建标注
    function addMarker(point) {
        var marker = new BMap.Marker(point);
        map.addOverlay(marker);
    }
    // 随机向地图添加25个标注
    var bounds = map.getBounds();
    var sw = bounds.getSouthWest();
    var ne = bounds.getNorthEast();
    var lngSpan = Math.abs(sw.lng - ne.lng);
    var latSpan = Math.abs(ne.lat - sw.lat);
    for (var i = 0; i < 25; i++) {
        var point = new BMap.Point(sw.lng + lngSpan * (Math.random() * 0.7), ne.lat - latSpan * (Math.random() * 0.7));
        addMarker(point);
    };
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放
    // 设备地图颜色
    // var mapStyle = {
    //     style: "midnight"
    // };
    // map.setMapStyle(mapStyle);

}

