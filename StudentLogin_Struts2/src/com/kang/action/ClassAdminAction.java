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

public class ClassAdminAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public String studentname;
	public String action;
	public String matrino;
	public ArrayList<Student> stuParamArray; 
	
	@Override
	public String execute() throws Exception {
		Connection conn=DBConn.createDBConn();
		//----构造查询的SQL语句----
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
			while(rs!=null&&rs.next()){
				Student stu=new Student();
				stu.setStudentName(rs.getString("studentname"));
				stu.setSpecialityId(rs.getInt("specialityid"));
				stu.setMatriNo(rs.getString("matrino"));
				stu.setStudentId(rs.getLong("studentid"));
				stu.setClassId(rs.getInt("classid"));
				stuArray.add(stu);
			}
			Map request = (Map)ActionContext.getContext().get("request");
			request.put("stuArray", stuArray);
			//----查询出已有的专业数据----
			sql="select * from ClassTa";
			Statement stclass=conn.createStatement();
			ResultSet rsclass=stclass.executeQuery(sql);
			ArrayList<ClassTa> classArray=new ArrayList<ClassTa>();
			while(rsclass.next()){
				ClassTa classta=new ClassTa();
				classta.setClassid(rsclass.getInt("classid"));
				classta.setClassname(rsclass.getString("classname"));
				classArray.add(classta);
			}
			request.put("classArray", classArray);
		}
		//----设置分班情况----
		if(stuParamArray!=null&&"update".equals(action)){
			for(int i=0;i<stuParamArray.size();i++){
				if(stuParamArray.get(i).getClassId()!=0){
					String sqlstr="update student set classid="+stuParamArray.get(i).getClassId()+
						" where studentid="+stuParamArray.get(i).getStudentId();
					Statement state=conn.createStatement();
					state.executeUpdate(sqlstr);
				}	
			}
		}
		DBConn.closeConn(conn);
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

	public String getMatrino() {
		return matrino;
	}

	public void setMatrino(String matrino) {
		this.matrino = matrino;
	}

	public ArrayList<Student> getStuParamArray() {
		return stuParamArray;
	}

	public void setStuParamArray(ArrayList<Student> stuParamArray) {
		this.stuParamArray = stuParamArray;
	}

}
