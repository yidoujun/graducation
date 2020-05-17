<template>
  <div class="first">

    <div class="dataChart">
      <el-row :gutter="20">
        <el-col :span="12">
          <el-card>
            <div :style="{width: '100%', height: '600px'}" ref="echarts-levelJob"></div>
          </el-card>
        </el-col>

        <el-col :span="12">
          <el-card>
            <div :style="{width: '100%', height: '600px'}" ref="echarts-locationJob"></div>
          </el-card>
        </el-col>

      </el-row>

      <el-row>
        <el-col>
          <el-card>
            <div :style="{width: '100%', height: '600px'}" ref="echarts-yearJob"></div>
          </el-card>
        </el-col>
      </el-row>
      <el-row>
        <el-col>
          <el-card>
            <div :style="{width: '100%', height: '600px'}" ref="echarts-salaryJob"></div>
          </el-card>
        </el-col>
      </el-row>
    </div>

    <div class="welcome">
      <!--<el-card class="elcard20">
        <div class="text2">WELCOME</div>
      </el-card>-->
    </div>
  </div>
</template>

<script>
    export default {
        data() {
            name: "CityLevelJob";
            return {

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
                let [cityLeveImg, locationImg, jobYearImg, salaryIntervalImg] = await Promise.all([
                    this._requestImg("/job/getCityLevel", {isFlag: 'cityLevel'}),
                    this._requestImg("/job/getLocation", {isFlag: 'location'}),
                    this._requestImg("/job/getWorkYear", {isFlag: 'jobYear'}),
                    this._requestImg("/job/getSalaryInterval", {isFlag: 'salaryInterval'}),
                ]).catch((err) => alert(err));
                this.cityLevelAndJob(cityLeveImg);
                this.locationAndJob(locationImg);
                this.jobYearAndJob(jobYearImg);
                this.salaryIntervalAndJob(salaryIntervalImg);
            },
            cityLevelAndJob(cityLeveData) {
                // 初始化
                let myChart = this.$echarts.init(this.$refs['echarts-levelJob']);
                let names = [];
                let cityData = [];
                cityLeveData.forEach((item) => {
                    names.push(item.name);
                    cityData.push({'name': item.name, 'value': item.values})
                });
                // 绘制图形
                let option = {
                    title: {
                        text: 'X线城市与职位数量关系图',
                        subtext: '职位分析',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: names
                    },
                    series: [
                        {
                            name: '区位职位数量',
                            type: 'pie',
                            radius: '55%',
                            center: ['50%', '60%'],
                            data: cityData,
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        },
                    ]
                };
                // 使用刚指定的配置项和数据显示图表
                myChart.setOption(option);
            },
            locationAndJob(locationData) {
                // 初始化
                let myChart = this.$echarts.init(this.$refs['echarts-locationJob']);
                let names = [];
                let values = [];
                locationData.forEach((item) => {
                    names.push(item.name);
                    values.push(item.values);
                });
                let option = {
                    title: {
                        text: '区位与职位数量关系图',
                        subtext: '职位分析'
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        }
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value',
                        boundaryGap: [0, 0.01]
                    },
                    yAxis: {
                        type: 'category',
                        data: names
                    },
                    series: [
                        {
                            name: '职位数',
                            type: 'bar',
                            data: values
                        }
                    ]
                };
                myChart.setOption(option);
            },
            jobYearAndJob(jobYearData) {
                // 初始化
                let myChart = this.$echarts.init(this.$refs['echarts-yearJob']);
                let names = [];
                let values = [];
                jobYearData.forEach((item) => {
                    names.push(item.name);
                    values.push(item.values);
                });
                let option = {
                    // 配置标题信息
                    title: {
                        text: "工作年限与职位数量关系图",
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
                myChart.setOption(option);
            },
            salaryIntervalAndJob(salaryIntervalData) {
                // 初始化
                let myChart = this.$echarts.init(this.$refs['echarts-salaryJob']);
                let names = [];
                let values = [];
                salaryIntervalData.forEach((item) => {
                    names.push(item.name);
                    values.push(item.values);
                });
                let option = {
                    // 配置标题信息
                    title: {
                        text: "平均薪资与职位数量关系图",
                        textStyle: {
                            color: "#000000",
                            fontWeight: "bolder"
                        },
                        left: "center"
                    },
                    tooltip: {
                        trigger: 'axis',
                        position: function (pt) {
                            return [pt[0], '10%'];
                        }
                    },
                    toolbox: {
                        feature: {
                            dataZoom: {
                                yAxisIndex: 'none'
                            },
                            restore: {},
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: false,
                        data: names
                    },
                    yAxis: {
                        type: 'value',
                        boundaryGap: [0, '100%']
                    },
                    series: [
                        {
                            name: '职位数',
                            type: 'line',
                            smooth: true,
                            symbol: 'none',
                            sampling: 'average',
                            itemStyle: {
                                color: 'rgb(255, 70, 131)'
                            },
                            areaStyle: {
                                color: new this.$echarts.graphic.LinearGradient(0, 0, 0, 1, [{
                                    offset: 0,
                                    color: 'rgb(255, 158, 68)'
                                }, {
                                    offset: 1,
                                    color: 'rgb(255, 70, 131)'
                                }])
                            },
                            data: values
                        }
                    ]
                };
                myChart.setOption(option);
            },
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
