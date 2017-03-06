package com.kang.action;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.kang.db.DBConn;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	public String adminusername;   
	public String adminuserpassword;
	public String action;
	public String errormsg;  
    
	public String getAdminusername() {
		return adminusername;
	}

	public void setAdminusername(String adminusername) {
		this.adminusername = adminusername;
	}

	public String getAdminuserpassword() {
		return adminuserpassword;
	}

	public void setAdminuserpassword(String adminuserpassword) {
		this.adminuserpassword = adminuserpassword;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getErrormsg() {
		return errormsg;
	}

	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}

	@Override
	public String execute() {
		if("login".equals(action)){
			try{
				Connection conn=DBConn.createDBConn();
				String sql="select * from adminuser where adminusername=? and adminuserpassword=?";
				PreparedStatement state=conn.prepareStatement(sql);
				
				state.setString(1,adminusername);
				state.setString(2,adminuserpassword);
				ResultSet rs=state.executeQuery();
				if(rs.next()){
					ActionContext.getContext().getSession().put("adminusername",adminusername);
					ActionContext.getContext().getSession().put("adminuserrole",rs.getString("adminuserrole"));
					DBConn.closeConn(conn);//关闭数据库连接
					return SUCCESS;
				}else{
					errormsg=new String("用户名或密码输入有误");//定义了错误提示信息					
				}	
				DBConn.closeConn(conn);
			}catch(Exception e){
				errormsg=new String("数据库连接有误");
			}	
		}
		return INPUT;  
	}

}
