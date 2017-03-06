
package com.kang.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import com.kang.POJO.Speciality;
import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class SpecialityAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String specialityname;
	public int specialityid;
	public String action;
	
	@Override
	public String execute() throws Exception {
		Connection conn=DBConn.createDBConn();
		//----如果是要增加一个专业---
		if("add".equals(action)){
			String sql="select * from Speciality where SpecialityName=?";
			//专业名是否存在
			PreparedStatement preSQLSelect=conn.prepareStatement(sql);
			preSQLSelect.setString(1,specialityname);
			ResultSet rs=preSQLSelect.executeQuery();
			if(!rs.next()){//没有这个专业，则注册新专业
				sql="insert into Speciality(SpecialityName) values(?)";
				PreparedStatement preSQLInsert=conn.prepareStatement(sql);
				preSQLInsert.setString(1,specialityname);
				preSQLInsert.executeUpdate();//更新数据库
			}
		}
		if("del".equals(action)){
			String sql="delete from Speciality where SpecialityId=?";
			PreparedStatement preSQLDel=conn.prepareStatement(sql);
			preSQLDel.setInt(1,specialityid);
			preSQLDel.executeUpdate();
		}
		//----查询出已有的专业数据----
		String sql="select * from Speciality";
		Statement state=conn.createStatement();
		ResultSet rs=state.executeQuery(sql);
		ArrayList<Speciality> specialityArray=new ArrayList<Speciality>();
		while(rs.next()){//把查询结果放入	ArrayList中
			Speciality spec=new Speciality();//Speciality是一个javaBean
			spec.setSpecialityid(rs.getInt("specialityid"));//通过查询结果获取id值，并放入javaBean中
			spec.setSpecialityname(rs.getString("specialityname"));//获取名字，放入javaBean
			specialityArray.add(spec);//放入specialityArray中
		}
		Map<String,ArrayList<Speciality>> request = (Map<String,ArrayList<Speciality>>)ActionContext.getContext().get("request");
		//上述代码获取了request对象，只不过是一个map类型key为String型，value为ArrayList<Speciality>型
		request.put("specialityArray", specialityArray);
		//向request中添加一个数据，在specialityadmin.jsp中通过
		//#request.specialityArray可以得到这个数据
		DBConn.closeConn(conn);
		return SUCCESS;
	}

	public String getSpecialityname() {
		return specialityname;
	}

	public void setSpecialityname(String specialityname) {
		this.specialityname = specialityname;
	}

	public int getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(int specialityid) {
		this.specialityid = specialityid;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}
	
	

}
