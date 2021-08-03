/*
 *  1. 萬用複合查詢-可由客戶端隨意增減任何想查詢的欄位
 *  2. 為了避免影響效能:
 *     所以動態產生萬用SQL的部份,本範例無意採用MetaData的方式,也只針對個別的Table自行視需要而個別製作之
 * */

	//未來結合framework使用 get_aCondition_For_anyDB(String TableName, String value) {} 
	//先select取得 ResultSet 並call ResultSetMetaData
	//再用ResultSetMetaData interface的 getColumnTypeName 取得該Table的所有column Types;
	//再用ResultSetMetaData interface的 getColumnName 取得該Table的所有columnNames;



package com.act.CompositeQuery;

import java.util.*;

public class jdbcUtil_CompositeQuery_Act {

	
	//columnName == getParameterValues的 key
	//value == getParameterValues的 value[0]
	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;
		//數字類直接用 columnName=value 來查
		if ("ACT_ID".equals(columnName) || "ACT_CATEGORY_ID".equals(columnName) || "ACT_PROMOTION_ID".equals(columnName) || "ACT_FEE".equals(columnName)) { //  for numbers
			aCondition = columnName + "=" + value;
		System.out.println(aCondition);

		//varchar 等字串類要用" like '%"+value+"%' ==>才能達成“模糊查詢”
	} else if ("ACT_DESCRIPTION".equals(columnName) || "ACT_STATUS".equals(columnName)) { // for varchar 
			aCondition = columnName + " like '%" + value + "%'";
		System.out.println(aCondition);

		//date ==> MySQL的datetime dialect跟其他DB不同
		//個別DB dialect 可藉由Hibernate...等ORM framework來解決;
	} else if ("PART_START".equals(columnName) || "ACT_START".equals(columnName) ) {                         // for date
			aCondition = columnName + "=" + "'"+ value +"'";                          //for the other DB(ex:MySQL) type  date
//		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle type date
		System.out.println(aCondition);
	}
		
		// aCondition 後面加空格 （防呆 取得正確的SQL串接字串） 
		return aCondition + " ";
	}
	
	
	
	//萬用查詢的源頭
	//每個key都是ParameterValues的 欄位名
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		System.out.println("前端form傳來的參數keys一共如下： "+keys);

		//用StringBuilder一樣可以
		StringBuffer whereCondition = new StringBuffer();
		
		//用 count 來判斷 萬用搜尋的條件組有幾個， aka 查詢資料的欄位數
		int count = 0;
		
		for (String key : keys) {
			//注意：取出的value是array的第一個
			String value = map.get(key)[0];
			System.out.println("從Key取得的value是： "+value);

			//!"action".equals(key) <=== action是給servlet判斷哪段code要執行，不屬於“萬用搜尋條件的KeySet”，
			//但還是會被map.keySet()抓出來，要“過濾掉避免誤查”；
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());
				System.out.println("經過 get_aCondition_For_myDB 按type分別處理後的條件如下： "+aCondition);


				//用 count 來判斷 萬用搜尋的條件組有幾個， aka 查詢資料的欄位數
				//第一個條件 串接 " where " + aCondition  
				//其餘條件 串接 " and " + aCondition
				if (count == 1)
					whereCondition.append(" where " + aCondition);
				else
					whereCondition.append(" and " + aCondition);
					
				//確認 查詢條件append進去的總共幾筆（排除了action)
				System.out.println("有送出查詢資料的欄位數count = " + count);
			}
		}// end or for-each
		
		//注意：StringBuffer whereCondition 最後一定要toString()
		System.out.println("最後的condition： " +whereCondition);

		return whereCondition.toString();
	}

	
	
	
		//老師用main方法做test，
		//測試完的finalSQL要送去MySQL workbench測試看看結果是否正常才能上線
	public static void main(String argv[]) {


		// new String[] { "7001" } <==== 匿名陣列(Anonymous Array) （ check:小吳 匿名類別）
		
		// 是一種語法糖，還原成這樣： String[] xx = new String[1]; xx[0] = "7001";
		//String[] xx = {"7001"}  <===非匿名 還是要宣告xx
		
		// new String[] { "7001" } <== 最快
		// new String[] { "7001","7002" }
		
		
		// 用TreeMap的原因 ==>1. 同type 2. comparable 比較String key的大小;
		// 但此例子用LinkedHashMap,HashMap,HashTable一樣可以跑
		// String impl 1. Comparable<String> 2. Serializable 3. CharSequence  <==字串實作了這3個interface
		
		// 配合 req.getParameterMap()方法 回傳 java.util.Map<java.lang.String,java.lang.String[]> 之測試

		Map<String, String[]> map = new TreeMap<String, String[]>();
		map.put("ACT_ID", new String[] { "8002" });
		map.put("ACT_START", new String[] { "報名中" });
		map.put("ACT_START", new String[] { "2021-11-25 08:00:00" });
		map.put("ACT_FEE", new String[] { "1500" });
		
		
		// 注意: req.getParameterMap裡面會含有action的key ==>這是給Servlet判斷要執行哪段code，
					//但查詢的時候，這個action不能列入查詢的key
		map.put("action", new String[] { "getXXX" }); 
		
		//以下finalSQL複製去
		String finalSQL = "select * from ACTIVITY "
				          + jdbcUtil_CompositeQuery_Act.get_WhereCondition(map)
				          + "order by ACT_ID";
		
		// 拿這段 finalSQL  去MySQL workbench測試過再上線
		System.out.println("finalSQL = " + finalSQL);
		
	}
}
