package com.kang.POJO;

import java.sql.Date;

public class Student {
	long StudentId;
	String StudentName;
	int SpecialityId;
	int ClassId;
	int BedchamberId;
	String MatriNo;
	float PayAmount;
	int PayOK;
	Date RegistDate;
	public long getStudentId() {
		return StudentId;
	}
	public void setStudentId(long studentId) {
		StudentId = studentId;
	}
	public String getStudentName() {
		return StudentName;
	}
	public void setStudentName(String studentName) {
		StudentName = studentName;
	}
	public int getSpecialityId() {
		return SpecialityId;
	}
	public void setSpecialityId(int specialityId) {
		SpecialityId = specialityId;
	}
	public int getClassId() {
		return ClassId;
	}
	public void setClassId(int classId) {
		ClassId = classId;
	}
	public int getBedchamberId() {
		return BedchamberId;
	}
	public void setBedchamberId(int bedchamberId) {
		BedchamberId = bedchamberId;
	}
	public String getMatriNo() {
		return MatriNo;
	}
	public void setMatriNo(String matriNo) {
		MatriNo = matriNo;
	}
	public float getPayAmount() {
		return PayAmount;
	}
	public void setPayAmount(float payAmount) {
		PayAmount = payAmount;
	}
	public int getPayOK() {
		return PayOK;
	}
	public void setPayOK(int payOK) {
		PayOK = payOK;
	}
	public Date getRegistDate() {
		return RegistDate;
	}
	public void setRegistDate(Date registDate) {
		RegistDate = registDate;
	}
}
