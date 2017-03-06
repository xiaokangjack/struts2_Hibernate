package com.kang.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.kang.POJO.AdminUser;
import com.kang.POJO.Bedchamber;
import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BedchamberAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	public String action;
	public String bedchambername;
	public String bedchamberid;
	
	@Override
	public String execute() throws Exception {
		Connection conn=DBConn.createDBConn();
		//----如果是要增加一个宿舍---
		if("add".equals(action)){
			String sql="select * from bedchamber where bedchambername=?";
			PreparedStatement preSQLSelect=conn.prepareStatement(sql);
			preSQLSelect.setString(1,bedchambername);
			ResultSet rs=preSQLSelect.executeQuery();
			if(!rs.next()){//没有这个宿舍
				sql="insert into bedchamber(bedchamberName) values(?)";
				PreparedStatement preSQLInsert=conn.prepareStatement(sql);
				preSQLInsert.setString(1,bedchambername);
				preSQLInsert.executeUpdate();
			}
		}
		//----如果是删除一个宿舍----
		if("del".equals(action)){
			String sql="delete from bedchamber where bedchamberId=?";
			PreparedStatement preSQLDel=conn.prepareStatement(sql);
			int bedchamberidInt=0;
			if(bedchamberid!=null&&bedchamberid.length()>0)
				bedchamberidInt=Integer.parseInt(bedchamberid);
			preSQLDel.setInt(1,bedchamberidInt);
			preSQLDel.executeUpdate();
		}
		//----查询出已有的宿舍数据----
		String sql="select * from bedchamber";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		ArrayList<Bedchamber> bedArray=new ArrayList<Bedchamber>();
		while(rs.next()){
			Bedchamber bed=new Bedchamber();
			bed.setBedchamberId(rs.getInt("bedchamberId"));
			bed.setBedchamberName(rs.getString("bedchamberName"));
			bedArray.add(bed);
		}
		Map<String,ArrayList<Bedchamber>> request = (Map<String,ArrayList<Bedchamber>>)ActionContext.getContext().get("request");
		request.put("bedArray", bedArray);
		DBConn.closeConn(conn);
		return SUCCESS;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getBedchambername() {
		return bedchambername;
	}

	public void setBedchambername(String bedchambername) {
		this.bedchambername = bedchambername;
	}

	public String getBedchamberid() {
		return bedchamberid;
	}

	public void setBedchamberid(String bedchamberid) {
		this.bedchamberid = bedchamberid;
	}
	
}
