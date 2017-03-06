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

public class AcceptMoneyAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String studentname;
	public String action;
	public String matrino;
	public ArrayList<Student> stuParamArray;
	@Override
	public String execute() throws Exception {
		Connection conn=DBConn.createDBConn();
		//----查询数据操作----
		String sqlwhere=new String("");
		String sql=new String("");
		if("select".equals(action)){//如果是查询操作
			if(studentname!=null&&studentname.trim().length()!=0)
				sqlwhere="where studentname like '%"+studentname.trim()+"%' ";
			if(sqlwhere!=null&&sqlwhere.length()!=0){
				if(matrino!=null&&matrino.trim().length()!=0)
					sqlwhere+=" and matrino like '%"+matrino.trim()+"%' ";
			}else{
				if(matrino!=null&&matrino.trim().length()!=0)
					sqlwhere=" where matrino like '%"+matrino.trim()+"%' ";
			}
			sql="select * from student "+sqlwhere;
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
			Map request = (Map)ActionContext.getContext().get("request");
			request.put("stuArray", stuArray);			
		}
		//----交费操作----
		if(stuParamArray!=null&&"update".equals(action)){
			for(int i=0;i<stuParamArray.size();i++){
					String sqlstr="update student set payamount="+stuParamArray.get(i).getPayAmount()+
						", payok="+stuParamArray.get(i).getPayOK()+
						" where studentid="+stuParamArray.get(i).getStudentId();
					Statement state=conn.createStatement();
					state.executeUpdate(sqlstr);
			}
		}
		DBConn.closeConn(conn);
		return SUCCESS;
	}
	public ArrayList<Student> getStuParamArray() {
		return stuParamArray;
	}
	public void setStuParamArray(ArrayList<Student> stuParamArray) {
		this.stuParamArray = stuParamArray;
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
	public String getMatrino() {
		return matrino;
	}
	public void setMatrino(String matrino) {
		this.matrino = matrino;
	}

}
