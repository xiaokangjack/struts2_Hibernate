package com.kang.action;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.kang.POJO.Bedchamber;
import com.kang.POJO.Student;
import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BedAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public String studentname;
	public String action;
	public String matrino;
	public ArrayList<Student> stuParamArray;
	@Override
	public String execute() throws Exception {
		Connection conn=DBConn.createDBConn();
		String sqlwhere=new String("");
		String sql=new String("");
		Map request = (Map)ActionContext.getContext().get("request");
		//----如果是查询操作----
		if("select".equals(action)){
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
				stu.setPayAmount(rs.getFloat("payAmount"));
				stu.setPayOK(rs.getInt("payok"));
				stu.setBedchamberId(rs.getInt("bedchamberId"));
				stuArray.add(stu);
			}
			request.put("stuArray", stuArray);
		}
		//----查询出宿舍清单----
		sql="select * from bedchamber";
		Statement statebed=conn.createStatement();
		ResultSet rsbed=statebed.executeQuery(sql);
		ArrayList<Bedchamber> bedArray=new ArrayList<Bedchamber>();
		while(rsbed.next()){
			Bedchamber bed=new Bedchamber();
			bed.setBedchamberId(rsbed.getInt("bedchamberId"));
			bed.setBedchamberName(rsbed.getString("bedchamberName"));
			bedArray.add(bed); 
		}
		request.put("bedArray", bedArray);
		//----分配宿舍----
		if(stuParamArray!=null&&"update".equals(action)){
			for(int i=0;i<stuParamArray.size();i++){
				if(stuParamArray.get(i).getPayOK()==1&&stuParamArray.get(i).getBedchamberId()!=0){
					String sqlstr="update student set bedchamberid="+stuParamArray.get(i).getBedchamberId()+
						" where studentid="+stuParamArray.get(i).getStudentId();
					Statement stateBed=conn.createStatement();
					stateBed.executeUpdate(sqlstr);
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
