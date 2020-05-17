<template>
  <div class="first">

    <div class="dataChart">
      <el-row :gutter="0">
        <el-col :span="24">
          <el-card>
            <div :style="{width: '100%', height: '600px'}" ref="echarts-four"></div>
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
            name: "Educationjob";
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
                let [echartsData] = await Promise.all([
                    this._requestImg("/job/getEducation", {isFlag: 3}),
                ]).catch((err) => alert(err));
                this.educationAndJob(echartsData);
            },
            educationAndJob(echartsData) {
                // 初始化
                let myChart = this.$echarts.init(this.$refs['echarts-four']);
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
        },
        mounted() {
            this.getData();
        }
    };
</script>

<style>

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
