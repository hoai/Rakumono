package service;

import java.util.Random;

import com.alibaba.fastjson.JSON;

import decode.Item;
import encode.ShopItemResult;
import util.GenreClimber;
import api.RakutenIchiba;
import api.SearchCondition;

public class ShopItemDetect {
	//input: String keyword, String hits, String shopCode
	//output: json string
	public String getItemsInShop(String keyword, String hits, String shopCode) throws Exception{
		SearchCondition condition = new SearchCondition();
		RakutenIchiba ichiba = new RakutenIchiba();
		condition.setKeyword(keyword);
		condition.setHits(hits);
		condition.setShopCode(shopCode);
		condition.setAppID(GenreClimber.chooseAppID(new Random().nextInt(20)));
		condition.setSort_method("-reviewCount");
		String jsonString = ichiba.ichibaItemSearch(condition);
		Item item = ichiba.getItemFromJson(jsonString);
		ShopItemResult shopItemResult = new ShopItemResult();
		shopItemResult.setKeyword(keyword);
		shopItemResult.setItem(item);
		shopItemResult.setShopCode(shopCode);
		return JSON.toJSONString(shopItemResult);
	}
}