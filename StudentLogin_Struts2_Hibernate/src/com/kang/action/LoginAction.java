package com.kang.action;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.Session;

import com.kang.POJO.AdminUser;
import com.kang.db.HibernateUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String adminusername;
	public String adminuserpassword;
	public String action;
	public String errormsg;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() {
		if("login".equals(action)){
			try{
				String hsql="from AdminUser where adminUserName=? and adminUserPassword=?";
				Session sessionHibernate=HibernateUtil.getSession();
				sessionHibernate.beginTransaction();
				Query query=sessionHibernate.createQuery(hsql);
				query.setString(0,adminusername);
				query.setString(1,adminuserpassword);
				ArrayList<AdminUser> result =(ArrayList<AdminUser>)query.list();
				sessionHibernate.getTransaction().commit();
				//sessionHibernate.close();
				if(result.size()>=1){//如果用户名和密码正确
					ActionContext.getContext().getSession().put("adminusername",result.get(0).getAdminUserName());
					ActionContext.getContext().getSession().put("adminuserpassword",result.get(0).getAdminUserPassword());
					ActionContext.getContext().getSession().put("adminuserrole",result.get(0).getAdminUserRole());
					
					return SUCCESS;
				}else{
					errormsg=new String("用户名或密码输入有误");					
				}	
			}catch(Exception e){
				errormsg=new String("数据库操作有误");
				e.printStackTrace();
			}	
		}
		return INPUT;
	}
	
	
}
