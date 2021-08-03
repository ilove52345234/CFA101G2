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

public class jdbcUtil_CompositeQuery_ActType {

	
	//columnName == getParameterValues的 key
	//value == getParameterValues的 value[0]
	public static String get_aCondition_For_myDB(String columnName, String value) {

		String aCondition = null;
		//數字類直接用 columnName=value 來查
		if ("ACT_CATEGORY_ID".equals(columnName) || "ACT_NUMBER".equals(columnName) || "ACT_TOTAL_SCORE".equals(columnName) || "ACT_FEE".equals(columnName)) //  for numbers
			aCondition = columnName + "=" + value;
		
		//varchar 等字串類要用" like '%"+value+"%' ==>才能達成“模糊查詢”
		else if ("ACT_CATEGORY_NAME".equals(columnName) || "ACT_CATEGORY_DESC".equals(columnName)) // for varchar 
			aCondition = columnName + " like '%" + value + "%'";
		
//		//date ==> MySQL的datetime dialect跟其他DB不同
//		//個別DB dialect 可藉由Hibernate...等ORM framework來解決;
//		else if ("PART_START".equals(columnName) || "ACT_START".equals(columnName) )                          // for date
//			aCondition = columnName + "=" + "'"+ value +"'";                          //for the other DB(ex:MySQL) type  date
////		    aCondition = "to_char(" + columnName + ",'yyyy-mm-dd')='" + value + "'";  //for Oracle type date
		
		
		// aCondition 後面加空格 （防呆 取得正確的SQL串接字串） 
		return aCondition + " ";
	}
	
	
	
	//萬用查詢的源頭
	//每個key都是ParameterValues的 欄位名
	public static String get_WhereCondition(Map<String, String[]> map) {
		Set<String> keys = map.keySet();
		//用StringBuilder一樣可以
		StringBuffer whereCondition = new StringBuffer();
		
		//用 count 來判斷 萬用搜尋的條件組有幾個， aka 查詢資料的欄位數
		int count = 0;
		
		for (String key : keys) {
			//注意：取出的value是array的第一個
			String value = map.get(key)[0];
			
			//!"action".equals(key) <=== action是給servlet判斷哪段code要執行，不屬於“萬用搜尋條件的KeySet”，
			//但還是會被map.keySet()抓出來，要“過濾掉避免誤查”；
			if (value != null && value.trim().length() != 0	&& !"action".equals(key)) {
				count++;
				String aCondition = get_aCondition_For_myDB(key, value.trim());


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
		return whereCondition.toString();
	}

		//老師用main方法做test，
		//測試完的finalSQL要送去MySQL workbench測試看看結果是否正常才能上線

}
