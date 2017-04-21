package com.kang.POJO;
import java.util.List;
public class Bedchamber {
	public Integer bedchamberId;
	public String bedchamberName;
	public List<Student> studentList;
	
	public String getBedchamberName() {
		return bedchamberName;
	}
	public void setBedchamberName(String bedchamberName) {
		this.bedchamberName = bedchamberName;
	}

	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public Integer getBedchamberId() {
		return bedchamberId;
	}
	public void setBedchamberId(Integer bedchamberId) {
		this.bedchamberId = bedchamberId;
	}
	
}
