<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>echart图表</title>
<link href="../../css/managestyle.css" rel="stylesheet" type="text/css">
<script src="../../js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="../../js/echarts.js" type="text/javascript"></script>
	<script type="text/javascript">
	var dailysales_x_axis = new Array();
	var dailysales_y_axis = new Array();
	var ordertime = new Array();
	var datemoney = new Array();
	var ordernum = new Array();
	var num = 0;//日期总数
	var item = 0;//数据库总行数
	<c:forEach items="${ordersbytime}" var="ordersbytime">
		tmptime = ${ordersbytime.orderTime.getMonth() + 1}+"月"+${ordersbytime.orderTime.getDate()}+"日"
		ordertime[item] = tmptime;
		datemoney[item++] = ${ordersbytime.orderMoney}
	</c:forEach>
	for(var i =0;i<item;i++){
		if(i ==0){
			dailysales_x_axis[num] = ordertime[i];
			dailysales_y_axis[num] = datemoney[i];
			ordernum[num] = 1;
		}
		else{
			if(ordertime[i]==dailysales_x_axis[num]) {
				dailysales_y_axis[num] += datemoney[i];
				ordernum[num] ++;
			}
			else{
				num++;
				dailysales_x_axis[num] = ordertime[i];
				dailysales_y_axis[num] = datemoney[i];
				ordernum[num] = 1;
			}
		}
	}
	</script>
</head>
<body>
	<%@ include file = "_left.jsp" %>

	<div  class="page-content">
	<section id="blog" class="content-section" >
			<div class="section-heading">
				<h1>Data<br><em>Analysis用户数据分析</em></h1>
				<p>在Triple-T游戏推荐平台中，您能寻找到大部分感兴趣的游戏相关的资源。
				<br>祝您使用愉快。</p>
			</div>
			<div class="section-content">
				<div class="tabs-content">
					<!-- 游戏每日销量 -->
					<div id="dailysales" style="width: 1000px;height:667px;"></div>
					<script type="text/javascript">
			        // 基于准备好的dom，初始化echarts实例
			        var myChart = echarts.init(document.getElementById('dailysales'));

			        // 指定图表的配置项和数据


					option = {
					  legend: {},
			          xAxis: {
					    type: 'category',
					    data: dailysales_x_axis,
					    name:"日期"
					  },
					  yAxis: [
						  {
						    type: 'value',
						    name:"每日销量/元",
						    position: 'left',
						      axisLine: {
						        show: true,
						      },
						      axisLabel: {
						        formatter: '{value} 元'
						      }
						  },
						  {
					      type: 'value',
					      name: '每日订单数/份',
					      min:0,
					      max:6,
					      position: 'right',
					      offset: 40,
					      axisLine: {
					        show: true,
					      },
					      axisLabel: {
					        formatter: '{value} 份'
					      }
					    }],
					  title: {
						    text: '每日销量',
					  },
					  dataZoom: [
						    {
						      type: 'inside',
						      start: 0,
						      end: 100
						    },
						    {
						      start: 0,
						      end: 100
						    }
						  ],
					  toolbox: {
						    feature: {
						    	 dataZoom: {
					                    yAxisIndex: 'none'
					                }, //区域缩放，区域缩放还原
					                magicType: {
					                    type: ['line', 'bar']
					                },
						      dataView: { show: true, readOnly: false },
						      restore: { show: true },
						      saveAsImage: { show: true }
						    }
						  },
					  tooltip: {//鼠标放置显示数据
						    trigger: 'axis',
						    axisPointer: {
						       type: 'shadow'
						    }
						  },
					  series: [
					    {
					      name: '每日销量',
					      data: dailysales_y_axis,
					      type: 'line',
					      yAxisIndex: 0,
					      smooth: true,
					      showBackground: true,
					      markPoint: { // 设置最大值和最小值
				              data: [
				                {
				                  type: "max",
				                },
				                {
				                  type: "min",
				                },
				              ],
				            },
				            markLine:{ // 设置平均线
				              data:[
				                {
				                  type:"average",
				                  color:"#baf"
				                }
				              ]
				            },
					      backgroundStyle: {
					        color: 'rgba(180, 180, 180, 0.2)'
					      }
					    },
					    {
					        name: '每日订单数',
					        type: 'bar',
					        yAxisIndex: 1,
					        label: {
					            show: true
					          },
					        data: ordernum
					      }
					  ]
					};
			        // 使用刚指定的配置项和数据显示图表。
			        myChart.setOption(option);
			    	</script>
			    	<!-- 游戏销售总榜 -->
			    	<p>&nbsp</p>
			    	<div id="totalsales" style="width: 1000px;height:667px;"></div>
					<script type="text/javascript">
					var item = 0;
					var totalsales_x_axis = new Array();
					var productsnum_x_axis = new Array();
					var totalsales_y_axis = new Array();
					<c:forEach items="${productbysalenum}" var="productbysalenum">
						totalsales_x_axis[item] = ${productbysalenum.soldnum};
						productsnum_x_axis[item] = ${productbysalenum.pnum};
						totalsales_y_axis[item++] = "${productbysalenum.name}";
					</c:forEach>
			        // 基于准备好的dom，初始化echarts实例
			        var myChart = echarts.init(document.getElementById('totalsales'));

			        // 指定图表的配置项和数据
			        option = {
					  title: {
					    text: '游戏销售总榜'
					  },
					  tooltip: {
					    trigger: 'axis',
					    axisPointer: {
					      type: 'shadow'
					    }
					  },
					  toolbox: {
						    feature: {
						    	 dataZoom: {
					                    yAxisIndex: 'none'
					                }, //区域缩放，区域缩放还原
					                magicType: {
					                    type: ['line', 'bar']
					                },
						      dataView: { show: true, readOnly: false },
						      restore: { show: true },
						      saveAsImage: { show: true }
						    }
						  },
					  dataZoom: [
						    {
						      type: 'slider',
						      xAxisIndex: 0,
						      filterMode: 'none'
						    },
						    {
						      type: 'slider',
						      yAxisIndex: 0,
						      start: 0,
						      end: 100,
						      filterMode: 'none'
						    },

						  ],
					  legend: {},
					  grid: {
					    left: '3%',
					    right: '10%',
					    bottom: '10%',
					    containLabel: true
					  },
					  xAxis: {
					    type: 'value',
					    boundaryGap: [0, 0.01],
					  },
					  yAxis: {
					    type: 'category',
					    data: totalsales_y_axis
					  },
					  series: [
					    {
					      name: '销售量',
					      type: 'bar',
					      data: totalsales_x_axis
					    },
					    {
						  name: '库存量',
						  type: 'bar',
						  data: productsnum_x_axis
						}
					  ]
					};
			        // 使用刚指定的配置项和数据显示图表。
			        myChart.setOption(option);
			    	</script>
			    	<!-- 价格分布图（+类别） -->
			    	<p>&nbsp</p>
			    	<div id="prices" style="width: 1000px;height:667px;"></div>
					<script type="text/javascript">
					var cate_item = 0;//类别数量
					var pro_item = 0;//商品数量
					var categories = new Array();
					var product_list = new Array();
					<c:forEach items="${category}" var="category">
						categories[cate_item++] = "${category.name}";
					</c:forEach>
					for(var i =0;i<cate_item;i++){
						product_list[i] = new Array(7);//价格分为0-30、30-50、50-70、70-100、100-200、200-300、300-500
					}
					for(var i =0;i<cate_item;i++){
						for(var j = 0;j<7;j++){
							product_list[i][j] = 0;
						}
					}
					<c:forEach items="${productbysalenum}" var="productbysalenum">
						var index = categories.indexOf("${productbysalenum.category}");
						if(${productbysalenum.price}<=30.0){
							product_list[index][0]++;
						}
						else if(${productbysalenum.price}<=50.0){
							product_list[index][1]++;
						}
						else if(${productbysalenum.price}<=70.0){
							product_list[index][2]++;
						}
						else if(${productbysalenum.price}<=100.0){
							product_list[index][3]++;
						}
						else if(${productbysalenum.price}<=200.0){
							product_list[index][4]++;
						}
						else if(${productbysalenum.price}<=300.0){
							product_list[index][5]++;
						}
						else{
							product_list[index][6]++;
						}
					</c:forEach>
					var series = [];
					for(var i = 0;i<categories.length;i++){
						series.push({
							name: categories[i],
						      type: 'bar',
						      stack: 'total',
						      label: {
						        show: true,
						        formatter: function (params) {
						             if (params.value > 0) {
						               return params.value;
						             } else {
						               return '';
						             }
						           },
						      },
						      emphasis: {
						        focus: 'series'
						      },
						      data: product_list[i]
						})
					}
			        // 基于准备好的dom，初始化echarts实例
			        var myChart = echarts.init(document.getElementById('prices'));

			        // 指定图表的配置项和数据
			        option = {
					  tooltip: {
					    trigger: 'axis',
					    axisPointer: {
					      // Use axis to trigger tooltip
					      type: 'shadow' // 'shadow' as default; can also be 'line' or 'shadow'
					    }
					  },
					  title: {
						    text: '游戏价格分布图',
					  },
					  legend: {
						  data:[categories[0],categories[1],categories[2],'',categories[3],categories[4],categories[5]],
						  right: 0
					  },
					  grid: {
					    left: '3%',
					    right: '10%',
					    bottom: '3%',
					    containLabel: true
					  },
					  xAxis: {
					    type: 'value',
					    name: "游戏数量/个"
					  },
					  yAxis: {
					    type: 'category',
					    data: ['0-30元', '30-50元','50-70元', '70-100元', '100-200元','200-300元', '300-500元'],
					    name:"游戏价格/元"
					  },
					  series: series
					};

			        // 使用刚指定的配置项和数据显示图表。
			        myChart.setOption(option);
			    	</script>
			    	<!-- 类别分布图 -->
			    	<p>&nbsp</p>
			    	<div id="categories" style="width: 1000px;height:667px;"></div>
					<script type="text/javascript">
					var cate_item = 0;//类别数量
					var categories = new Array();
					var catenum = new Array();
					<c:forEach items="${category}" var="category">
						catenum[cate_item] = 0;
						categories[cate_item++] = "${category.name}";
					</c:forEach>

					<c:forEach items="${productbysalenum}" var="productbysalenum">
						var index = categories.indexOf("${productbysalenum.category}");
						catenum[index]++;
					</c:forEach>
					function compare(value){
					    return function(a,b){
					        var value1 = a.value;
					        var value2 = b.value;
					        return value2 - value1;
					    }
					}
					var tot = [];
					for(var i =0;i<categories.length;i++){
						tot.push({ name: categories[i],value: catenum[i] });
					}
					  tot.sort(compare("value"));

			        // 基于准备好的dom，初始化echarts实例
			        var myChart = echarts.init(document.getElementById('categories'));

			        // 指定图表的配置项和数据
			        option = {
					  legend: {
					    top: 'bottom'
					  },
					  title: {
						    text: '游戏类别分布图',
					  },
					  tooltip: {
						    trigger: 'item',
						    formatter: '{a} <br/>{b}: {c} ({d}%)'
						  },
					  toolbox: {
					    show: true,
					    feature: {
					      mark: { show: true },
					      dataView: { show: true, readOnly: false },
					      restore: { show: true },
					      saveAsImage: { show: true }
					    }
					  },
					  series: [
					    {
					      name: '游戏类别分布图',
					      type: 'pie',
					      radius: [50, 250],
					      center: ['50%', '50%'],
					      roseType: 'area',
					      itemStyle: {
					        borderRadius: 8
					      },
					      data: tot
					    }
					  ]
					};

			        // 使用刚指定的配置项和数据显示图表。
			        myChart.setOption(option);
			    	</script>
				</div>
			</div>
		</section>
		</div>

</body>
</html>