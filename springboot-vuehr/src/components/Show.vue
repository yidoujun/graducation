<template>
  <body id="poster">
      <div>
        <div ref="echarts-one" style="width: 600px; height: 500px"></div>
        <div class="chinaImg" ref="echarts-two" style="width: 600px; height: 500px"></div>
      </div>
      <div>
        <el-button @click="getData">按钮</el-button>
      </div>
  </body>
</template>

<script>
    import showHeader from '@/components/common/ShowHeader.vue'
    import showFooter from '@/components/common/ShowFooter.vue'
    import city from 'echarts/map/json/china-cities'
    export default {
      name: "Show",
      components: {
        showHeader,
        showFooter
      },
      // 数据定义
      data() {
        return {

        }
      },
      // 钩子函数
      mounted() {

      },
      computed :{
          dataFormat: () => (arr) => arr.map((i) =>
              ({name: i.name, value: i.values}))
        },
      // 方法
      methods: {
          _requestImg(url, params) {
              return this.$axios
                  .post(url, params)
                  .then(({ data: {data} }) => data);
          },
        // 获取数据
        async getData() {
              let [chinaImg, industryImg] = await Promise.all([
                  this._requestImg("/job/getChina", {isFlag: 1}),
                  this._requestImg("/job/getIndustry", {isFlag: 2}),
              ]).catch((err) => alert(err));
              this.show(industryImg);
              this.chinamap(chinaImg);
        },
        show(echartsData) {
          // 基于准备好的dom，初始化echarts实例化
          var myChart = this.$echarts.init(this.$refs['echarts-one']);
          var names = [];
          var values = [];
            echartsData.forEach((item, i) => {
               names.push(item.name);
               values.push(item.values);
            });
            var dealData = this.dataFormat(echartsData);
          // 绘制图表
            var option = {
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                legend: {
                    orient: 'vertical',
                    left: 5,
                    data: names
                },
                series: [
                    {
                        name: '行业',
                        type: 'pie',
                        radius: ['50%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: '30',
                                fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: dealData
                    }
                ]
            };
          myChart.setOption(option);
        },
        chinamap(chinaData) {
                // 基于准备好的dom，初始化echarts实例化
                var myChart = this.$echarts.init(this.$refs['echarts-two']);
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

<style>
  #poster {
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
    background-color: #fffef9;
  }
  body{
    margin: 0px;
  }
  .chinaImg {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
  }
</style>
