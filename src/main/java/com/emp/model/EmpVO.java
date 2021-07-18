package com.emp.model;

import java.sql.Date;

public class EmpVO implements java.io.Serializable{
    private Integer empId;
    private String empName;
    private String empAccount;
    private String empPassword;
    private Integer empStatus;
    private Date empAddDate;

    @Override
    public String toString() {
        return "EmpVO{" +
                "empId=" + empId +
                ", empName='" + empName + '\'' +
                ", empAccount='" + empAccount + '\'' +
                ", empPassword='" + empPassword + '\'' +
                ", empStatus=" + empStatus +
                ", empAddDate=" + empAddDate +
                '}';
    }

    public Date getEmpAddDate() {
        return empAddDate;
    }

    public void setEmpAddDate(Date empAddDate) {
        this.empAddDate = empAddDate;
    }

    public EmpVO() {
    }



    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpAccount() {
        return empAccount;
    }

    public void setEmpAccount(String empAccount) {
        this.empAccount = empAccount;
    }

    public String getEmpPassword() {
        return empPassword;
    }

    public void setEmpPassword(String empPassword) {
        this.empPassword = empPassword;
    }

    public Integer getEmpStatus() {
        return empStatus;
    }

    public void setEmpStatus(Integer empStatus) {
        this.empStatus = empStatus;
    }

    public EmpVO(Integer empId, String empName, String empAccount, String empPassword, Integer empStatus, Date empAddDate) {
        this.empId = empId;
        this.empName = empName;
        this.empAccount = empAccount;
        this.empPassword = empPassword;
        this.empStatus = empStatus;
        this.empAddDate = empAddDate;
    }
}
