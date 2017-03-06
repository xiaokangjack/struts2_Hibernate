package com.kang.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import com.kang.POJO.Speciality;
import com.kang.POJO.Student;
import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MatriAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String action;
	public int specialityid;
	public String matrino;//表示录取通知书编号
	public String studentname;
	public int currentpage=1;
	public long studentid;
	
	@Override
	public String execute() throws Exception {
		if(studentname!=null&&studentname.length()!=0)
			studentname=studentname.trim();//Trim() 函数的功能是去掉首尾空格
		if(action!=null&&action.length()!=0)
			action=action.trim();
		if(matrino!=null&&matrino.length()!=0)
			matrino=matrino.trim();
		Connection conn=DBConn.createDBConn();
		//----如果是要增加一个学生---
		if("add".equals(action)){
			String sql="select * from Student where matrino=? and studentname=?"+
				" and specialityid=?";
			PreparedStatement preSQLSelect=conn.prepareStatement(sql);
			preSQLSelect.setString(1,matrino);
			preSQLSelect.setString(2,studentname);
			preSQLSelect.setInt(3,specialityid);
			ResultSet rs=preSQLSelect.executeQuery();
			if(!rs.next()){
				sql="insert into Student(matrino,studentname,specialityid) "+
					" values(?,?,?)";
				PreparedStatement preSQLInsert=conn.prepareStatement(sql);
				preSQLInsert.setString(1,matrino);
				preSQLInsert.setString(2,studentname);
				preSQLInsert.setInt(3,specialityid);
				preSQLInsert.executeUpdate();
			}
		}
		//----如果是要删除一个学生---
		if("del".equals(action)){
			String sql="delete from Student where studentid=?";
			PreparedStatement preSQLUpdate=conn.prepareStatement(sql);
			preSQLUpdate.setLong(1,studentid);
			preSQLUpdate.executeUpdate();
		}
		//----如果是要查询数据----
		ResultSet rsselect=null;
		int pagesize=5;//每页记录条数
		int pagecount=0;//总页数
		int recount=0;//总记录条数
		if("select".equals(action)){
			String sql=null;
			if((specialityid==0)){
				sql="select studentname,"+
					"studentid,matrino,specialityid "+
					"from Student where 1=1 limit  "+pagesize;
				String sqlcount="select count(*) as recount from Student ";
				Statement stateCount=conn.createStatement();
				ResultSet rscount=stateCount.executeQuery(sqlcount);
				rscount.next();
				recount=rscount.getInt("recount");
				if(recount%pagesize==0)
					pagecount=recount/pagesize;
				else
					pagecount=(int)(recount/pagesize)+1;
				//----生成得到当前页数据查询的附件条件----
				if(pagecount>1&&currentpage>1){
					String sqladd=" and studentid not in "+
						"(select studentid from Student limit "+(currentpage-1)*pagesize+")";
					sql=sql+sqladd;
				}
				Statement state=conn.createStatement();
				rsselect=state.executeQuery(sql);
			}else{  //检测到学生已经分配专业
				//----先生成查询的where子句----
				String sqlwhere=" where 1=1 ";
				if(specialityid!=0)
					{
					sqlwhere=sqlwhere+" and specialityid=? and studentname like BINARY ? ";
			}
				else
					sqlwhere=sqlwhere+" and studentname like BINARY ? ";
				sql="select studentname,studentid,matrino,specialityid "+
					"from Student "+sqlwhere +"limit "+pagesize;
				//----得到总记录条数----
				String sqlcount="select count(*) as recount "+
					"from Student "+sqlwhere;
				PreparedStatement preSQLCount=conn.prepareStatement(sqlcount);
				if(specialityid!=0){
					preSQLCount.setInt(1,specialityid);
					preSQLCount.setString(2,"%"+studentname+"%");
				}else
					preSQLCount.setString(1,"%"+studentname+"%");
				ResultSet rscount=preSQLCount.executeQuery();
				rscount.next();
				recount=rscount.getInt("recount");//得到总记录条数
				//----得到总页数----
				if(recount%pagesize==0)//能整除
					pagecount=recount/pagesize;
				else//不能整除
					pagecount=(int)(recount/pagesize)+1;
				//----生成得到当前页数据查询的附件条件----
				PreparedStatement preSQLSelect=null;
				//总页数大于1且当前页码大于1
				if(pagecount>1&&currentpage>1){
					String sqladd=" and studentid not in "+
						"(select studentid from Student "+sqlwhere+"limit "+(currentpage-1)*pagesize+
						") ";
					sql=sql+sqladd;
					preSQLSelect=conn.prepareStatement(sql);
					if(specialityid!=0){
						preSQLSelect.setInt(1,specialityid);
						preSQLSelect.setString(2,"%"+studentname+"%");
					}else{
						preSQLSelect.setString(1,"%"+studentname+"%");
					}				
				}else{//当前为第1页或仅只有1页
					preSQLSelect=conn.prepareStatement(sql);
					if(specialityid!=0){//如果选择了专业
						preSQLSelect.setInt(1,specialityid);
						preSQLSelect.setString(2,"%"+studentname+"%");
					}else
						preSQLSelect.setString(1,"%"+studentname+"%");	
				}
				rsselect=preSQLSelect.executeQuery();
			}
		}
		
		ArrayList<Student> stuArray=new ArrayList<Student>();
		while(rsselect!=null&&rsselect.next()){
			Student stu=new Student();
			stu.setStudentName(rsselect.getString("studentname"));
			stu.setSpecialityId(rsselect.getInt("specialityid"));
			stu.setMatriNo(rsselect.getString("matrino"));
			stu.setStudentId(rsselect.getLong("studentid"));
			stuArray.add(stu);
		}
		Map request = (Map)ActionContext.getContext().get("request");
		request.remove("stuArray");
		request.put("stuArray", stuArray);		
		//----查询出专业数据----
		String sql="select * from Speciality";
		Statement state=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
		ResultSet rs=state.executeQuery(sql);
		ArrayList<Speciality> specialityArray=new ArrayList<Speciality>();
		while(rs.next()){
			Speciality spec=new Speciality();
			spec.setSpecialityid(rs.getInt("specialityid"));
			spec.setSpecialityname(rs.getString("specialityname"));
			specialityArray.add(spec);
		}
		request.remove("specialityArray");
		request.put("specialityArray", specialityArray);
		//----将数据放入request----
		request.remove("pagesize");
		request.remove("pagecount");
		request.remove("currentpage");
		request.remove("recount");
		request.put("pagesize",pagesize);
		request.put("pagecount",pagecount);
		request.put("currentpage",currentpage);
		request.put("recount",recount);
		DBConn.closeConn(conn);
		return SUCCESS;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public int getSpecialityid() {
		return specialityid;
	}

	public void setSpecialityid(int specialityid) {
		this.specialityid = specialityid;
	}

	public String getMatrino() {
		return matrino;
	}

	public void setMatrino(String matrino) {
		this.matrino = matrino;
	}

	public String getStudentname() {
		return studentname;
	}

	public void setStudentname(String studentname) {
		this.studentname = studentname;
	}

	public int getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(int currentpage) {
		this.currentpage = currentpage;
	}

	public long getStudentid() {
		return studentid;
	}

	public void setStudentid(long studentid) {
		this.studentid = studentid;
	}

}
