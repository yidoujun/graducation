<template>
  <div class="first">
    <div class="message">
      <el-row :gutter="20">
        <el-col :span="6">
          <div>
            <el-card style="position: relative;" class="elcard1">
              <div class="card">
                <div class="something">
                  <span class="s1">{{job58}}</span>
                  <span class="s2">前程无忧爬取数据</span>
                  <i class="el-icon-truck" style="color: #00CCFF;"></i>
                </div>

                <el-progress :percentage="50"></el-progress>
              </div>
            </el-card>
          </div>
        </el-col>

        <el-col :span="6">
          <div>
            <el-card style="position: relative;" class="elcard1">
              <div class="card">
                <div class="something">
                  <span class="s1">{{liepin}}</span>
                  <span class="s2">猎聘网爬取数据</span>
                  <i class="el-icon-s-home" style=" color:#EB7347"></i>
                </div>

                <el-progress :percentage="80" color="skyblue"></el-progress>
              </div>
            </el-card>
          </div>
        </el-col>

        <el-col :span="6">
          <div>
            <el-card style="position: relative;" class="elcard1">
              <div class="card">
                <div class="something">
                  <span class="s1">{{dataDeal}}</span>
                  <span class="s2">数据处理数据</span>
                  <i class="el-icon-user" style="color: #26A65B"></i>
                </div>

                <el-progress :percentage="60" color="#FC9D99"></el-progress>
              </div>
            </el-card>
          </div>
        </el-col>

        <el-col :span="6">
          <div>
            <el-card style="position: relative;" class="elcard1">
              <div class="card">
                <div class="something">
                  <span class="s1">{{type}}</span>
                  <span class="s2">企业分类</span>
                  <i class="el-icon-loading" style="color: #daa520"></i>
                </div>

                <el-progress :percentage="30" color="green"></el-progress>
              </div>
            </el-card>
          </div>
        </el-col>
      </el-row>
    </div>

    <div class="dataChart">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card>
            <div :style="{width: '100%', height: '400px'}" ref="echarts-one"></div>
          </el-card>
        </el-col>
        <el-col :span="12">
          <el-card>
            <div :style="{width: '100%', height: '400px'}" ref="echarts-two"></div>
          </el-card>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-card>
            <div :style="{width: '100%', height: '900px'}" ref="echarts-three"></div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <!--<div class="welcome">
      <el-card class="elcard20">
        <div class="text2">WELCOME</div>
      </el-card>
    </div>-->
  </div>
</template>

<script>
    import city from 'echarts/map/json/china-cities'
    export default {
        data() {
            return {
                value: new Date(),
                job58: 5980,
                liepin: 7852,
                dataDeal: 6163,
                type: 4
            };
        },
        components: {},
        created() {
            let _this = this;
        },
        computed: {
            dataFormat: () => (arr) => arr.map((i) =>
                ({name: i.name, value: i.values}))
        },
        methods: {
            _requestImg (url, params) {
                return this.$axios
                    .post(url, params)
                    .then(({ data: {data} }) => data);
            },
            // 获取数据
            async getData() {
                let [chinaImg, industryImg, educationImg] = await Promise.all([
                    this._requestImg("/job/getChina", {isFlag: 1}),
                    this._requestImg("/job/getIndustry", {isFlag: 2}),
                    this._requestImg("/job/getEducation", {isFlag: 3}),
                ]).catch((err) => alert(err));
                this.show(industryImg);
                this.chinamap(chinaImg);
                this.educationAndJob(educationImg);
            },
            // 展示学历与职位的关系
            educationAndJob(echartsData) {
                // 初始化
                let myChart = this.$echarts.init(this.$refs['echarts-one']);
                let names = [];
                let values = [];
                echartsData.forEach((item) => {
                    names.push(item.name);
                    values.push(item.values);
                });
                // 绘制图形
                let option = {
                    // 配置标题信息
                    title: {
                        text: "学历与职位数量关系图",
                        textStyle: {
                            color: "#000000",
                            fontWeight: "bolder"
                        },
                        left: "center"
                    },
                    //
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow',
                            label: true
                        }
                    },
                    // 配置横坐标
                    xAxis: {
                        type: "category",
                        data: names,
                        itemStyle: {
                            narmal: {
                                color: "orange"
                            }
                        }
                    },
                    // 配置纵坐标
                    yAxis: {
                        type: "value"
                    },
                    toolbox: {
                        show: true,
                        feature: {
                            magicType: {
                                show: true,
                                type: ['line', 'bar']
                            },
                            restore: {
                                show: true
                            },
                            saveAsImage: {
                                show: true
                            }
                        }
                    },
                    series: [{
                        name: "职位数",
                        type: 'bar',
                        data: values,
                        barWidth: "70%",
                        itemStyle: {
                            normal: {
                                // 每根柱子颜色设置
                                color: function (params) {
                                    let colorList = [
                                        "#c23531",
                                        "#2f4554",
                                        "#61a0a8",
                                        "#d48265",
                                        "#91c7ae",
                                        "#749f83",
                                        "#ca8622",
                                        "#bda29a",
                                        "#6e7074",
                                    ];
                                    return colorList[params.dataIndex];
                                }
                            }
                        },
                        // 柱状图上显示数据
                        label: {
                            show: "true",
                            position: "top",
                            color: "#FFF",
                            fontWeight: "bolder",
                            backgroundColor: "auto",
                            fontSize: "20"
                        },
                        // 平均值
                        markLine: {
                            data: [{type: "average", name: "平均值"}]
                        }
                    }],
                };
                // 使用刚指定的配置项和数据显示图表
                myChart.setOption(option);
            },
            // 展示各行业职位的关系
            show(echartsData) {
                // 基于准备好的dom，初始化echarts实例化
                var myChart = this.$echarts.init(this.$refs['echarts-two']);
                var names = [];
                var values = [];
                echartsData.forEach((item, i) => {
                    names.push(item.name);
                    values.push(item.values);
                });
                var dealData = this.dataFormat(echartsData);
                // 绘制图表
                var option = {
                    // 配置标题信息
                    title: {
                        text: "各行业招聘数量关系图",
                        textStyle: {
                            color: "#000000",
                            fontWeight: "bolder"
                        },
                        left: "center"
                    },
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
            // 中国地图展示各城市的招聘数量
            chinamap(chinaData) {
                // 基于准备好的dom，初始化echarts实例化
                var myChart = this.$echarts.init(this.$refs['echarts-three']);
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
                window.onresize = myChart.resize;
                var options = {
                    // 配置标题信息
                    title: {
                        text: "中国数据类相关岗位招聘数量图",
                        textStyle: {
                            color: "#000000",
                            fontWeight: "bolder"
                        },
                        left: "center"
                    },
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

        },
        mounted() {
            this.getData();
        }
    };
</script>

<style>

  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }

  .el-carousel__item h3 {
    color: #475669;
    font-size: 14px;
    opacity: 0.75;
    line-height: 200px;
    margin: 0;
  }

  .el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
  }

  .el-carousel__item:nth-child(2n + 1) {
    background-color: #d3dce6;
  }

  .message {
    margin-top: 10px;
  }
  .card {
    height: 90px;
  }
  .something i {
    font-size: 40px;
    position: absolute;
    right: 10px;
    top: 24px;
  }
  .something .s1 {
    font-size: 40px;
    font-weight: 700;
  }
  .something .s2 {
    /* margin-left: 10px; */
    display: block;
    color: #909399;
    font-size: 14px;
    margin-top: 5px;
  }
  .el-progress {
    margin-top: 10px;
    margin-left: 10px;
  }
  .dataChart {
    margin-top: 20px;
  }
  .elcard20 {
    height: 150px;
    /* line-height: 150px; */
    text-align: center;
    /* padding-bottom: 50px; */
    /* height: 100vh; */
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .elcard20 span {
    font-size: 100px;
    margin-bottom: 50px;
  }
  .welcome {
    margin-top: 20px;
    height: 150px;
  }
  .span1 {
    margin-right: 50px;
    color: #e6a23c;
  }
  .span2 {
    margin-right: 50px;
    color: #67c23a;
  }
  .span3 {
    color: #409eff;
  }

  .elcard20 .text2 {
    background: url(https://media.giphy.com/media/3o6Ztb45EYezY9x9gQ/giphy.gif);
    background-size: contain;
    background-position: top left;
    -webkit-background-clip: text;
    color: transparent;
    font-size: 9rem;
    font-weight: bold;
    font-family: sans-serif;
  }
</style>
