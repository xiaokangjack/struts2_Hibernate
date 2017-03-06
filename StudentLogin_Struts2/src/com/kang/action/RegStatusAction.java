package com.kang.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.kang.POJO.Student;
import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class RegStatusAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String studentname;
	public String action;
	@Override
	public String execute() {
		if("select".equals(action)){
			//----查询出已有的数据----
			try{
				Connection conn=DBConn.createDBConn();
				String sql="select * from student ";
				if(studentname!=null&&studentname.length()!=0)
					sql+="where studentname like '%"+studentname+"%'";
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
				Map<String,ArrayList<Student>> request = (Map<String,ArrayList<Student>>)ActionContext.getContext().get("request");
				request.put("stuArray", stuArray);
				DBConn.closeConn(conn);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}
	public String getStudentname() {
		return studentname;
	}
	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	
	
}
