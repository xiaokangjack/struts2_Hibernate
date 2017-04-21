package com.kang.action;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import com.kang.POJO.ClassTa;
import com.kang.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
public class ClassAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String action;
	public String classname;
	public String classid;
	@Override
	public String execute() throws Exception {
		Session sessionHibernate=HibernateUtil.getSession();
		sessionHibernate.beginTransaction();
		Map request = (Map)ActionContext.getContext().get("request");
		//----如果是要增加一个班级---
		if("add".equals(action)){
			String hsql="from ClassTa where className=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setString(0,classname);
			ArrayList<ClassTa> claArray =(ArrayList<ClassTa>)query.list();
			if(claArray.size()<=0){//没有这个班级
				ClassTa cla=new ClassTa();
				cla.setClassName(classname);
				sessionHibernate.save(cla);
				//sessionHibernate.flush();
			}
		}
		//----如果是删除一个班级----
		if("del".equals(action)){
			String hsql="from ClassTa where classId=?";
			Query query=sessionHibernate.createQuery(hsql);
			query.setInteger(0,Integer.parseInt(classid));
			ArrayList<ClassTa> claArray =(ArrayList<ClassTa>)query.list();
			if(claArray.size()>=1){
				sessionHibernate.delete(claArray.get(0));
				//sessionHibernate.flush();
			}
		}
		//----查询出已有的班级数据----
		String hsql="from ClassTa";
		Query query=sessionHibernate.createQuery(hsql);
		ArrayList<ClassTa> classArray =(ArrayList<ClassTa>)query.list();
		request.put("classArray", classArray);
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
