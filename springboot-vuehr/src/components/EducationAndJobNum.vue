<template>
  <div id="china">
    <div style="height: 500px; width:800px;" ref="echarts-three"></div>
    <div>
      <el-button @click="getData">按钮</el-button>
    </div>
  </div>
</template>

<script>
    export default {
        name: "EducationAndJobNum",
        data () {
            return {

            }
        },
        methods:{
            getData() {
                this.$axios
                    .post("/job/getEducation", {isFlag: '3'})
                    .then(res => {
                        if (res) {
                            this.educationAndJob(res.data.data);
                            console.log("测试")
                        }
                    })
                    .catch(err => {

                    })
            },
            // 图形展示方法
            educationAndJob(echartsData) {
                // 初始化
                let myChart = this.$echarts.init(this.$refs['echarts-three']);
                let names = [];
                let values = [];
                echartsData.forEach((item, i) => {
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
            }
        }
    };
</script>

<style scoped>

</style>
