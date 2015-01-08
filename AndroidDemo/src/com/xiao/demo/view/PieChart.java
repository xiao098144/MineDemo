package com.xiao.demo.view;

import java.text.DecimalFormat;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;


/**
 *@filename PieChart.java
 *@TODO
 *@date 2014-9-22上午11:34:50
 *@Administrator 萧
 *
 */
public class PieChart {
	private int count;
	private double[] data = new double[3];

	public PieChart(int higher, int normal, int lower, int count) {
		data[0] = higher;
		data[1] = normal;
		data[2] = lower;
		this.count = count;
	}

	public GraphicalView getPieChartView(Context context, CategorySeries series, DefaultRenderer renderer) {
		return ChartFactory.getPieChartView(context, series, renderer);
	}

	/**
	 * 构造饼图数据
	 */
	public CategorySeries getDataSet() {
		// 构造数据
		CategorySeries pieSeries = new CategorySeries(" 血糖分析");
		String[] a = new String[] { "偏高", "正常", "偏低" };
		DecimalFormat df = new DecimalFormat("00.0");
		if (count > 0) {
			for (int i = 0; i < a.length; i++) {
//				if (count >0) {
					a[i] = a[i] + df.format(data[i] / count * 100) + "%";
//				}else {
//					a[i] = a[i] + "0%";
//					data[i] = 50;
//				}
				pieSeries.add(a[i], data[i]);
			}
		} else {
			pieSeries.add("未填写", 100);
		}
		Log.e(" 饼图 ", " getDataSet " + pieSeries.toString() + " ");
		return pieSeries;
	}

	/**
	 * 获取一个饼图渲染器
	 */
	public DefaultRenderer getPieRenderer() {
		// 构造一个渲染器
		DefaultRenderer renderer = new DefaultRenderer();
		// 设置渲染器显示缩放按钮
		// renderer.setZoomButtonsVisible(true);
		// 设置渲染器允许放大缩小  
		// // renderer.setZoomEnabled(true);
		// // 设置渲染器标题文字大小
		// renderer.setChartTitleTextSize(20);

		if (count <= 0) {
			SimpleSeriesRenderer simpleRenderer = new SimpleSeriesRenderer();
			simpleRenderer.setColor(Color.YELLOW);
			renderer.addSeriesRenderer(simpleRenderer);
		} else {
			// 给渲染器增加3种颜色
			SimpleSeriesRenderer yellowRenderer = new SimpleSeriesRenderer();
			yellowRenderer.setColor(Color.RED);
			SimpleSeriesRenderer blueRenderer = new SimpleSeriesRenderer();
			blueRenderer.setColor(Color.BLUE);
			SimpleSeriesRenderer redRenderer = new SimpleSeriesRenderer();
			redRenderer.setColor(Color.GREEN);
			renderer.addSeriesRenderer(yellowRenderer);
			renderer.addSeriesRenderer(blueRenderer);
			renderer.addSeriesRenderer(redRenderer);
		}

		// 设置饼图文字字体大小和饼图标签字体大小
		renderer.setLabelsTextSize(17);
		renderer.setLegendTextSize(17);
		// 消除锯齿
		renderer.setAntialiasing(true);
		// // 设置背景颜色
		 renderer.setApplyBackgroundColor(true);
		// renderer.setBackgroundColor(Color.BLACK);
		renderer.setShowLabels(true);
		renderer.setPanEnabled(true);
		renderer.setShowAxes(true);
		renderer.setShowLegend(true);
		renderer.setLabelsColor(Color.WHITE);
		renderer.setClickEnabled(true);
		renderer.setDisplayValues(true);
		renderer.setFitLegend(true);
		renderer.setInScroll(true);
		 renderer.setMargins(new int[]{3,3,3});
		renderer.setScale(1.0f);
		Log.e(" 饼图 ", " getPieRenderer " + renderer.getSeriesRenderers().length+ " ");
		return renderer;
	}
}
