package com.kang.action;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;

import com.kang.POJO.ClassTa;
import com.kang.POJO.Student;
import com.kang.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ClassViewAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	public String action;
	public String classid;
	@Override
	public String execute() throws Exception {
		Session sessionHibernate=HibernateUtil.getSession();
		sessionHibernate.beginTransaction();
		Map request = (Map)ActionContext.getContext().get("request");
		String hsql="from ClassTa";
		Query queryClass=sessionHibernate.createQuery(hsql);
		//sessionHibernate.getTransaction().commit();
		List<ClassTa> classArray =(List<ClassTa>)queryClass.list();
		request.remove("classArray");
		request.put("classArray", classArray);
		if(classid!=null&&classid.length()!=0){
			hsql="from ClassTa where classId="+classid;
			Query query=sessionHibernate.createQuery(hsql);
			//sessionHibernate.getTransaction().commit();
			List<ClassTa> classAr =(List<ClassTa>)query.list();
			List<Student> stuArray=null;
			if(classAr.size()>=1)
				stuArray=classAr.get(0).getStudentList();
			request.put("stuArray", stuArray);
		}
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
	public String getClassid() {
		return classid;
	}
	public void setClassid(String classid) {
		this.classid = classid;
	}

}
