package com.kang.action;

import java.util.ArrayList;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import com.kang.POJO.Bedchamber;
import com.kang.POJO.Student;
import com.kang.db.HibernateUtil;
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
		Session sessionHibernate=HibernateUtil.getSession();
		sessionHibernate.beginTransaction();
		String hsqlwhere=new String("");
		String hsql=new String("");
		Map request = (Map)ActionContext.getContext().get("request");
		//----如果是查询操作----
		if("select".equals(action)){
			if(studentname!=null&&studentname.trim().length()!=0)
				hsqlwhere="where studentName like '%"+studentname.trim()+"%' ";
			if(hsqlwhere!=null&&hsqlwhere.length()!=0){
				if(matrino!=null&&matrino.trim().length()!=0)
					hsqlwhere+=" and matriNo like '%"+matrino.trim()+"%' ";
			}else{
				if(matrino!=null&&matrino.trim().length()!=0)
					hsqlwhere=" where matriNo like '%"+matrino.trim()+"%' ";
			}
			hsql="from Student "+hsqlwhere;
			Query query=sessionHibernate.createQuery(hsql);
			//sessionHibernate.getTransaction().commit();
			ArrayList<Student> stuArray =(ArrayList<Student>)query.list();
			request.put("stuArray", stuArray);
		}
		//----查询出宿舍清单----
		hsql="from Bedchamber";
		Query query=sessionHibernate.createQuery(hsql);
		ArrayList<Bedchamber> bedArray =(ArrayList<Bedchamber>)query.list();
		request.put("bedArray", bedArray);
		//----分配宿舍----
		if(stuParamArray!=null&&"update".equals(action)){
			for(int i=0;i<stuParamArray.size();i++){
				if(stuParamArray.get(i)!=null&&stuParamArray.get(i).getPayOK()!=null&&stuParamArray.get(i).getPayOK()==1&&
					stuParamArray.get(i).getBedchamber().getBedchamberId()!=null&&stuParamArray.get(i).getBedchamber().getBedchamberId()!=0){
					String hsqlstr="from Bedchamber where bedchamberId="+stuParamArray.get(i).getBedchamber().getBedchamberId();
					Query queryBed=sessionHibernate.createQuery(hsqlstr);
					ArrayList<Bedchamber> bedArray1 =(ArrayList<Bedchamber>)queryBed.list();
					if(bedArray1.size()>=1){
						hsqlstr="from Student where studentId="+stuParamArray.get(i).getStudentId();
						Query queryStu=sessionHibernate.createQuery(hsqlstr);
						ArrayList<Student> stuArray =(ArrayList<Student>)queryStu.list();
						if(stuArray.size()>=1){
							Student stu=stuArray.get(0);
							stu.setBedchamber(bedArray1.get(0));
							sessionHibernate.save(stu);
							//sessionHibernate.flush();
						}
					}
				}
			}
		}
		sessionHibernate.getTransaction().commit();
		//sessionHibernate.close();
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
