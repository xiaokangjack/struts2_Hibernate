//定义了一些查询学生是否分专业，是否分班和是否分宿舍的方法

package com.kang.db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class StudentUtil {
	//返回是否已分专业表述的字符串
	public static String haveSplitSpec(int specialityid){
		String returnString;
		try{
			Connection conn=DBConn.createDBConn();
			if(specialityid==0)
				returnString="尚无专业";
		     else{
		    	  String sql="select * from speciality where specialityid="+specialityid;
		    	  Statement statetemp=conn.createStatement();
		    	  ResultSet rstemp=statetemp.executeQuery(sql);
		    	  if(rstemp.next())
		    		  returnString=rstemp.getString("specialityname");
		    	  else
		    		  returnString="尚无专业";
		      }
			DBConn.closeConn(conn);
			return returnString;
		}catch(Exception e){
			return null;
		}
	}
	
	//返回是否已分班表述的字符串
	public static String haveSplitClass(int classid){
		String returnString;
		try{
			Connection conn=DBConn.createDBConn();
			if(classid==0)
				returnString="尚未分班";
		     else{
		    	  String sql="select * from classTa where classid="+classid;
		    	  Statement statetemp=conn.createStatement();
		    	  ResultSet rstemp=statetemp.executeQuery(sql);
		    	  if(rstemp.next())
		    		  returnString=rstemp.getString("classname");
		    	  else
		    		  returnString="尚未分班";
		      }
			DBConn.closeConn(conn);
			return returnString;
		}catch(Exception e){
			return null;
		}
	}
	//返回是否已分配宿舍表述的字符串
	public static String haveSplitBedchamber(int bedchamberid){
		String returnString;
		try{
			Connection conn=DBConn.createDBConn();
			if(bedchamberid==0)
				returnString="尚未分配宿舍";
		     else{
		    	  String sql="select * from bedchamber where bedchamberid="+bedchamberid;
		    	  Statement statetemp=conn.createStatement();
		    	  ResultSet rstemp=statetemp.executeQuery(sql);
		    	  if(rstemp.next())
		    		  returnString=rstemp.getString("bedchambername");
		    	  else
		    		  returnString="尚未分配宿舍";
		      }
			DBConn.closeConn(conn);
			return returnString;
		}catch(Exception e){
			return null;
		}
	}	
}
