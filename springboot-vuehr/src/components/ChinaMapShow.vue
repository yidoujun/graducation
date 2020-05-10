<template>
    <div id="china">
      <div id="chinaMap" style="height: 800px; width: 1000px;" ref="myEcharts"></div>
      <div>
        <el-button @click="getChina">按钮</el-button>
      </div>
    </div>

</template>

<script>
    require('echarts/map/js/province/beijing');   // 引入北京地图
    require('echarts/map/js/province/zhejiang');  // 引入浙江地图
    require('echarts/map/js/province/hunan');     // 引入湖南地图
    // require('echarts/map/json/china-cities');     // 引入中国城市
    import city from 'echarts/map/json/china-cities'
    export default {
        name: "ChinaMapShow",
        data() {
          return {

          }
        },
      mounted() {
        console.log(city)
      },
      computed :{
          dataFormat: () => (arr) => arr.map((i) =>
            ({name: i.name, value: i.values}))
      },
      methods: {
          // 获取地图数据
          getChina() {
            this.$axios
                .post('/job/getChina', {
                    isFlag: '1'
                })
                .then( res => {
                  if (res) {
                    this.chinamap(res.data.data);
                      console.log("测试")
                  }

                })
                .catch(err =>{

                })
          },
          chinamap(chinaData) {
            // 基于准备好的dom，初始化echarts实例化
            var myChart = this.$echarts.init(this.$refs['myEcharts']);
            var geoCoordMap = {};
            var mapName = 'china';
            myChart.showLoading();
            var mapFeatures = city.features;
            myChart.hideLoading();
            mapFeatures.forEach(function (v) {  // 地区名称
                var name = v.properties.name;   // 地区经纬度
                geoCoordMap[name] = v.properties.cp;
            });
            var data = [];
            data = chinaData;
            var convertData = function (data) {
                var res = [];
                data.forEach((item, i) => {
                    var geoCoord = geoCoordMap[item.name];
                    console.log(geoCoord);
                    if (geoCoord) {
                        res.push({
                            name:item.name,
                            // count:item.values,
                            value: geoCoord.concat(item.value),
                        });
                    }
                });
                return res;
            };
            console.log("============")
            window.onresize = myChart.resize;
            var options = {
                backgroundColor: "transparent",
                tooltip: {
                    trigger: "item",
                    backgroundColor: "#1540a1",
                    borderColor: "#FFFFCC",
                    showDelay: 0,
                    hideDelay: 0,
                    enterable: false,
                    transitionDuration: 0,
                    extraCssText: "z-index:100",
                    formatter: function (params, ticket, callback) {
                        let index = params.dataIndex;
                        let name = params.name;
                        var tipHtml = '';
                        tipHtml =
                            '<div style="width:280px;height:200px;background:rgba(22,80,158,0.8);border:1px solid rgba(7,166,255,0.7)">'
                            + '<div style="width:100%;height:40px;line-height:40px;border-bottom:2px solid rgba(7,166,255,0.7);padding:0 20px">'
                            + '<i style="display:inline-block;width:8px;height:8px;background:#16d6ff;border-radius:40px;">' + '</i>'
                            + '<span style="margin-left:10px;color:#fff;font-size:16px;">' + name + '</span>'
                            + '</div>'
                            + '<div style="padding:20px">'
                            + '<p style="color:#fff;font-size:12px;">'
                            +'<i style="display:inline-block;width:10px;height:10px;background:#16d6ff;border-radius:40px;margin:0 8px">' + '</i>'
                            + '职位总数：' + '<span style="color:#f48225;margin:0 6px;">' + chinaData[index].values +'</span>'+ '个'
                            + '</p>'
                            // + '<p style="color:#fff;font-size:12px;">'
                            // + '<i style="display:inline-block;width:10px;height:10px;background:#16d6ff;border-radius:40px;margin:0 8px">' + '</i>'
                            // + '正常运行机器数：' + '<span style="color:#f48225;margin:0 6px;">' + _this.mapData[index].good +'</span>' +'个'
                            // + '</p>'
                            // + '<p style="color:#fff;font-size:12px;">'
                            // + '<i style="display:inline-block;width:10px;height:10px;background:#16d6ff;border-radius:40px;margin:0 8px">' +'</i>'
                            // + '待修机器数：' + '<span style="color:#f48225;margin:0 6px;">' + _this.mapData[index].bad +'</span>' +'个'
                            // + '</p>'
                            + '</div>'
                            + '</div>';
                        return tipHtml;
                    }
                },
                visualMap: {
                    show: false,
                    min: 0,
                    max: 200,
                    left: '10%',
                    top: 'bottom',
                    calculable: false,
                    seriesIndex: [1],
                    inRange: {
                      color: ['#04387b', '#467bc0']
                   }
                },
                geo: {
                    map: mapName,
                    label: {
                        normal: {
                            show: false
                        },
                        emphasis: {
                            show: true
                        },
                        color: '#fff'
                    },
                    roam: true,
                    zoom: .6,
                    scaleLimit: {
                        min: .6
                    },
                    layoutCenter: ["50%", "50%"], //地图位置
                    layoutSize: "180%",
                    itemStyle: {
                        normal: {
                            areaColor: '#0083ce', //地图颜色
                            borderColor: '#0066ba' //省份边界颜色
                        },
                        emphasis: {
                            orderColor: '#0066ba', //选中省份边界颜色
                            areaColor: "#0494e1", //选中省份颜色
                            shadowColor: 'rgba(0, 0, 0, 0.4)', //选中省份投影
                            shadowBlur: 10
                        }
                    }
                },
                series: [{
                    name: '散点',
                    type: 'effectScatter',
                    coordinateSystem: 'geo',
                    data: convertData(chinaData),
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    rippleEffect: {
                        // 涟漪效果
                        period: 4,
                        brushType: "stroke",
                        scale: 4
                    },
                    hoverAnimation: true,
                    symbolSize: 5,
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'right',
                            show: true
                        },
                        emphasis: {
                            show: false
                        }
                    },
                    itemStyle: {
                        normal: {
                            color: '#fff'
                        }
                    }
                }, {
                    name: '散点',
                    type: 'effectScatter',
                    coordinateSystem: 'geo',
                    data: convertData(chinaData.sort(function (a, b) {
                        return b.count - a.count;
                    }).slice(0, 6)),
                    symbolSize: function (val) {
                        return val[2] / 10;
                    },
                    // symbolSize: 8,
                    showEffectOn: 'render',
                    rippleEffect: {
                        //涟漪特效
                        period: 3, //动画时间，值越小速度越快
                        brushType: "stroke", //波纹绘制方式 stroke, fill
                        scale: 4 //波纹圆环最大限制，值越大波纹越大
                    },
                    hoverAnimation: true,
                    label: {
                        normal: {
                            formatter: '{b}',
                            position: 'left',
                            show: false
                        }
                    },
                    itemStyle: {
                        normal: {
                            formatter: '{b}',
                            position: 'left',
                            show: false
                        }
                    },
                    zlevel: 1
                }]
            };

              myChart.setOption(options);
          }
      }
    }
</script>

<style scoped>

</style>
