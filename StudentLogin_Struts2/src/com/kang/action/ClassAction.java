package com.kang.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.kang.POJO.ClassTa;
import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClassAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String action;
	public String classname;
	public String classid;
	@Override
	public String execute() throws Exception {
		Connection conn=DBConn.createDBConn();
		//----如果是要增加一个班级---
		if("add".equals(action)){
			String sql="select * from classta where classname=?";
			PreparedStatement preSQLSelect=conn.prepareStatement(sql);
			preSQLSelect.setString(1,classname);
			ResultSet rs=preSQLSelect.executeQuery();
			if(!rs.next()){//没有这个班级
				sql="insert into classta(className) values(?)";
				PreparedStatement preSQLInsert=conn.prepareStatement(sql);
				preSQLInsert.setString(1,classname);
				preSQLInsert.executeUpdate();
			}
		}
		//----如果是删除一个班级----
		if("del".equals(action)){
			String sql="delete from classta where classId=?";
			PreparedStatement preSQLDel=conn.prepareStatement(sql);
			int classidInt=0;
			if(classid!=null&&classid.length()>0)
				classidInt=Integer.parseInt(classid);
			preSQLDel.setInt(1,classidInt);
			preSQLDel.executeUpdate();
		}
		//----查询出已有的班级数据----
		String sql="select * from classta";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		ArrayList<ClassTa> classArray=new ArrayList<ClassTa>();
		while(rs.next()){
			ClassTa cla=new ClassTa();
			cla.setClassid(rs.getInt("classid"));
			cla.setClassname(rs.getString("classname"));
			classArray.add(cla);
		}
		Map<String,ArrayList<ClassTa>> request = (Map<String,ArrayList<ClassTa>>)ActionContext.getContext().get("request");
		request.put("classArray", classArray);
		DBConn.closeConn(conn);
		return SUCCESS;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}
}
