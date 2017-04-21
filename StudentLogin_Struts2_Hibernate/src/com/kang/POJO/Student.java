package com.kang.POJO;
import java.sql.Date;
public class Student {
	Long studentId;
	String studentName;
	Speciality speciality;
	Bedchamber bedchamber;
	ClassTa classTa;
	String matriNo;
	Float payAmount;
	Integer payOK;
	Date registDate;


	public Speciality getSpeciality() {
		return speciality;
	}
	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}
	public Bedchamber getBedchamber() {
		return bedchamber;
	}
	public void setBedchamber(Bedchamber bedchamber) {
		this.bedchamber = bedchamber;
	}
	public Long getStudentId() {
		return studentId;
	}
	public void setStudentId(Long studentId) {
		this.studentId = studentId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public ClassTa getClassTa() {
		return classTa;
	}
	public void setClassTa(ClassTa classTa) {
		this.classTa = classTa;
	}
	public String getMatriNo() {
		return matriNo;
	}
	public void setMatriNo(String matriNo) {
		this.matriNo = matriNo;
	}
	public Float getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(Float payAmount) {
		this.payAmount = payAmount;
	}
	public Integer getPayOK() {
		return payOK;
	}
	public void setPayOK(Integer payOK) {
		this.payOK = payOK;
	}
	public Date getRegistDate() {
		return registDate;
	}
	public void setRegistDate(Date registDate) {
		this.registDate = registDate;
	}


}
