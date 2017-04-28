package com.minstone.common.util;

import java.util.UUID;

/**
 * <p>Title: 数据库操作类</p>
 *
 * <p>Description: 数据库管理</p>
 *
 * <p>Copyright: Copyright:MinStone Copyright (c) 2004-2020</p>
 *
 * <p>Company: 明动软件有限公司</p> 
 *
 * @version 1.0
 */
public class DBManager {
    public DBManager() {
    }

    
     
     /** 
      * 获得一个UUID 
      * @return String UUID 
      */ 
     public static String getUUID(){ 
         String s = UUID.randomUUID().toString(); 
         //去掉“-”符号 
         return  s.replaceAll("-", ""); 
     } 
     
     
     /** 
      * 获得指定数目的UUID 
      * @param number int 需要获得的UUID数量 
      * @return String[] UUID数组 
      */ 
     public static String[] getUUID(int number){ 
         if(number < 1){ 
             return null; 
         } 
         String[] uuid = new String[number]; 
         for(int i=0;i<number;i++){ 
        	 uuid[i] = getUUID(); 
         } 
         return uuid; 
     } 
     
}
