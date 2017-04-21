package com.kang.action;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import com.kang.POJO.Student;
import com.kang.db.HibernateUtil;
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
		Session sessionHibernate=HibernateUtil.getSession();
		sessionHibernate.beginTransaction();
		//----查询数据操作----
		String hsqlwhere=new String("");
		String hsql=new String("");
		if("select".equals(action)){//如果是查询操作
			if(studentname!=null&&studentname.trim().length()!=0)
				hsqlwhere="where StudentName like '%"+studentname.trim()+"%' ";
			if(hsqlwhere!=null&&hsqlwhere.length()!=0){
				if(matrino!=null&&matrino.trim().length()!=0)
					hsqlwhere+=" and MatriNo like '%"+matrino.trim()+"%' ";
			}else{
				if(matrino!=null&&matrino.trim().length()!=0)
					hsqlwhere=" where MatriNo like '%"+matrino.trim()+"%' ";
			}
			hsql="from Student "+hsqlwhere;
			Query query=sessionHibernate.createQuery(hsql);
			ArrayList<Student> stuArray =(ArrayList<Student>)query.list();
			HttpServletRequest request = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			request.setAttribute("stuArray", stuArray);			
		}
		//----交费操作----
		if(stuParamArray!=null&&"update".equals(action)){
			for(int i=0;i<stuParamArray.size();i++){
				if(stuParamArray.get(i).getPayAmount()!=null&&stuParamArray.get(i).getPayOK()!=null&&stuParamArray.get(i).getStudentId()!=null){
					String hsqlstr="from Student where StudentId="+stuParamArray.get(i).getStudentId();
					Query query=sessionHibernate.createQuery(hsqlstr);
					ArrayList<Student> stuArray =(ArrayList<Student>)query.list();
					if(stuArray.size()>=1){
						Student stu=stuArray.get(0);
						stu.setPayAmount(stuParamArray.get(i).getPayAmount());
						stu.setPayOK(stuParamArray.get(i).getPayOK());
						sessionHibernate.save(stu);
						//sessionHibernate.flush();
					}
				}	
			}
		}
		sessionHibernate.getTransaction().commit();
		//sessionHibernate.close();
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
