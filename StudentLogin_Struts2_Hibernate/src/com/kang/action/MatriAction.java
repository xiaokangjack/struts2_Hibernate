package com.kang.action;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import com.kang.POJO.Speciality;
import com.kang.POJO.Student;
import com.kang.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class MatriAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public String action;
	public Integer specialityid;
	public String matrino;
	public String studentname;
	public Integer currentpage=1;
	public Long studentid;
	
	@Override
	public String execute() throws Exception {
		if(studentname!=null&&studentname.length()!=0)
			studentname=studentname.trim();
		if(action!=null&&action.length()!=0)
			action=action.trim();
		if(matrino!=null&&matrino.length()!=0)
			matrino=matrino.trim();
		Session sessionHibernate=HibernateUtil.getSession();
		sessionHibernate.beginTransaction();
		Map request = (Map)ActionContext.getContext().get("request");
		//----如果是要增加一个学生---
		if("add".equals(action)){
			String hsql="from Student where matriNo=? and studentName=?"+
				" and speciality.specialityId=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setString(0,matrino);
			query.setString(1,studentname);
			query.setInteger(2,specialityid);	
			ArrayList<Student> stuArray =(ArrayList<Student>)query.list();
			if(stuArray.size()<=0){//没有此学生
				Student stu=new Student();
				stu.setMatriNo(matrino);
				stu.setStudentName(studentname);
				hsql="from Speciality where specialityId="+specialityid;
				Query query1=sessionHibernate.createQuery(hsql);
				Speciality spec=((ArrayList<Speciality>)query1.list()).get(0);
				stu.setSpeciality(spec);
				sessionHibernate.save(stu);
				//sessionHibernate.flush();
			}
		}
		//----如果是要删除一个学生---
		if("del".equals(action)){
			String hsql="from Student where studentId="+studentid;
			Query query=sessionHibernate.createQuery(hsql);
			ArrayList<Student> stuArray =(ArrayList<Student>)query.list();
			if(stuArray.size()==1){
				sessionHibernate.delete(stuArray.get(0));
				//sessionHibernate.flush();
			}
		}
		//----如果是要查询数据----
		int pagesize=5;//每页记录条数
		int pagecount=0;//总页数
		int recount=0;//总记录条数
		if("select".equals(action)){
			String hsql=null;
			String hsqlwhere=" where 1=1 ";//where子句
			if(studentname!=null&&studentname.length()>=1)
				hsqlwhere+=" and s.studentName like '%"+studentname+"%'";
			if(specialityid!=null&&specialityid.intValue()!=0)
				hsqlwhere+=" and s.speciality.specialityId="+specialityid;
			String hsqlcount="select count(*) from Student s "+hsqlwhere;
			recount = ((Long)sessionHibernate.createQuery(hsqlcount).uniqueResult()).intValue();
			//----得到总页数----
			if(recount%pagesize==0)//能整除
				pagecount=recount/pagesize;
			else//不能整除
				pagecount=(int)(recount/pagesize)+1;
			hsql="from Student s "+hsqlwhere+" order by s.studentId desc";
			System.out.println("****"+hsql);
			Query query=sessionHibernate.createQuery(hsql);
			query.setFirstResult((currentpage-1)*pagesize);
			query.setMaxResults(pagesize);
			ArrayList<Student> stuArray =(ArrayList<Student>)query.list();
			request.put("stuArray", stuArray);				
		}
		//----查询出专业数据----
		String hsql="from Speciality";
		Query query=sessionHibernate.createQuery(hsql);
		ArrayList<Speciality> specialityArray =(ArrayList<Speciality>)query.list();
		request.put("specialityArray", specialityArray);
		//----将数据放入request----
		request.put("pagesize",pagesize);
		request.put("pagecount",pagecount);
		request.put("currentpage",currentpage);
		request.put("recount",recount);
		sessionHibernate.getTransaction().commit();
		//sessionHibernate.close();
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
