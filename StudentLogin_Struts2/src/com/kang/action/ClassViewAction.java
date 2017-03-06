package com.kang.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.kang.POJO.ClassTa;
import com.kang.POJO.Student;
import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClassViewAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public String action;
	public String classid;
	@Override
	public String execute() throws Exception {
		Connection conn=DBConn.createDBConn();
		String sql="select * from ClassTa";
		Statement stclass=conn.createStatement();
		ResultSet rsclass=stclass.executeQuery(sql);
		ArrayList<ClassTa> classArray=new ArrayList<ClassTa>();
		while(rsclass.next()){
			ClassTa classta=new ClassTa();
			classta.setClassid(rsclass.getInt("classid"));
			classta.setClassname(rsclass.getString("classname"));
			classArray.add(classta);
		}
		Map request = (Map)ActionContext.getContext().get("request");
		request.put("classArray", classArray);
		if(classid!=null&&classid.length()!=0){
			sql="select * from student where classid="+classid;
			Statement state=conn.createStatement();
			ResultSet rs=state.executeQuery(sql);
			ArrayList<Student> stuArray=new ArrayList<Student>();
			while(rs.next()){
				Student stu=new Student();
				stu.setBedchamberId(rs.getInt("bedchamberId"));
				stu.setClassId(rs.getInt("classId"));
				stu.setMatriNo(rs.getString("matriNo"));
				stu.setPayAmount(rs.getFloat("payAmount"));
				stu.setPayOK(rs.getInt("payOK"));
				stu.setRegistDate(rs.getDate("registDate"));
				stu.setSpecialityId(rs.getInt("specialityId"));
				stu.setStudentId(rs.getLong("studentId"));
				stu.setStudentName(rs.getString("studentName"));
				stuArray.add(stu);
			}
			request.put("stuArray", stuArray);
		}
		DBConn.closeConn(conn);
		return SUCCESS;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}

}
