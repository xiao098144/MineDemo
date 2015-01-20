package com.xiao.xxjdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Date:2015-1-20下午10:21:07 Author:Administrator TODO:TODO
 */
public class ShopCartAdapter extends BaseAdapter {

	LayoutInflater inflater;  

	private List<Test> dataList;

	private List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

	/** 是否显示店铺区 */
	private String SHOW_SHOP = "show_shop";
	/** 是否显示共计区 */
	private String SHOW_SUM = "show_sum";
	/** 是否显示item间距区 */
	private String SHOW_DIVIDER = "show_divider";

	public ShopCartAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	public void setData(List<Test> dataList) {
		this.dataList = dataList;
		preProcess();
		notifyDataSetChanged();
	}

	/**
	 * 数据预处理 判断制定区域是否显示 计算同家店铺商品购买总数 与 总价
	 */
	private void preProcess() {
		int sum_num = dataList.get(0).getGoods_Num();
		float sum_price = dataList.get(0).getGoods_Price();
		for (int i = 0; i < dataList.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (i >= 1 && i <= dataList.size() - 1) {
				// 当前值 与上一项 同属一家店铺 则 店铺与间距 隐藏
				if (dataList.get(i).getShop_Id()
						.equals(dataList.get(i - 1).getShop_Id())) {
					map.put(SHOW_SHOP, false);
					map.put(SHOW_DIVIDER, false);

					sum_num += dataList.get(i).getGoods_Num();
					sum_price += dataList.get(i).getGoods_Price();
				} else {
					map.put(dataList.get(i - 1).getShop_Id() + "num", sum_num);
					map.put(dataList.get(i - 1).getShop_Id() + "price",sum_price);
					sum_num = dataList.get(i).getGoods_Num();
					sum_price = dataList.get(i).getGoods_Price(); // 总计值 须归0
					// 否则 显示店铺 与 item间距 并且将上一项的共计区域显示出来
					if (resultList.size() >0 && resultList.get(i-1)!=null) {
						resultList.get(i - 1).put(SHOW_SUM, true);
					}
					map.put(SHOW_SHOP, true);
					map.put(SHOW_DIVIDER, true);
				}
			}
			if (i == 0) { // 第一项数据 显示 店铺区 隐藏 间距区
				map.put(SHOW_SHOP, true);
				map.put(SHOW_DIVIDER, false);
			}
			if (i == dataList.size() - 1) { // 最后一项数据 显示总计区域
				map.put(SHOW_SUM, true);
			}
			map.put("data", dataList.get(i));
			resultList.add(map);
		}
	}

	@Override
	public int getCount() {
		return dataList == null ? 0 : dataList.size();
	}

	@Override
	public Object getItem(int position) {
		return dataList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder = null;
		if (view == null) {
			view = inflater.inflate(R.layout.layout_lv_item, parent, false);
			holder = new ViewHolder();
			holder.img_divider = (ImageView) view
					.findViewById(R.id.item_img_divider);
			holder.layout_head = (LinearLayout) view
					.findViewById(R.id.item_shop_title);
			holder.layout_foot = (LinearLayout) view
					.findViewById(R.id.item_shop_sum);
			holder.tv_shop = (TextView) view.findViewById(R.id.item_shop_tv_name);
			holder.tv_goods_name = (TextView) view
					.findViewById(R.id.item_goods_tv_name);
			holder.tv_goods_price = (TextView) view
					.findViewById(R.id.item_goods_tv_price);
			holder.tv_goods_num = (TextView) view
					.findViewById(R.id.item_goods_tv_num);
			holder.tv_sum = (TextView) view.findViewById(R.id.item_tv_sum);
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
		}
		Map<String, Object> map = resultList.get(position);
		Test test = (Test) map.get("data");
		if (map.get(SHOW_DIVIDER).equals(true)) {
			holder.img_divider.setVisibility(View.VISIBLE);
		} else {
			holder.img_divider.setVisibility(View.GONE);
		}
		if (map.get(SHOW_SHOP).equals(true)) {
			holder.layout_head.setVisibility(View.VISIBLE);
			holder.tv_shop.setTag(R.id.shop_id, test.getShop_Id());
			holder.tv_shop.setText(test.getShop_Name());
		} else {
			holder.layout_head.setVisibility(View.GONE);
		}

		holder.tv_goods_name.setTag(R.id.goods_id, test.getGoods_Id());
		holder.tv_goods_name.setText(test.getGoods_Name());
		holder.tv_goods_num.setText(test.getGoods_Num() + "");
		holder.tv_goods_price.setText(test.getGoods_Price() + "");

		if (map.get(SHOW_SUM).equals(true)) {
			holder.layout_foot.setVisibility(View.VISIBLE);
			holder.tv_sum.setText("共计：购买商品："
					+ map.get(dataList.get(position).getShop_Id() + "num")
							.toString() + " 总价："
					+ map.get(dataList.get(position).getShop_Id() + "price"));
		} else {
			holder.layout_foot.setVisibility(View.GONE);
		}

		return view;
	}

	private class ViewHolder {
		/** 店铺区 默认隐藏 */
		LinearLayout layout_head;
		/** 店铺总结共计 默认隐藏 */
		LinearLayout layout_foot;
		TextView tv_shop, tv_goods_name, tv_goods_price, tv_goods_num, tv_sum;
		/** item 间距区 默认隐藏 */
		ImageView img_divider;
	}

}
