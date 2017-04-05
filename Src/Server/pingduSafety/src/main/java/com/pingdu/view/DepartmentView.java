package com.pingdu.view;

import com.pingdu.baseModel.BaseEntity;

public class DepartmentView extends BaseEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer	departmentCode;
	private String  departmentName;
	private String  departmentAddr;
	private String  departmentPerson;
	private String  departmentPersonTel;
	private String  departmentTel;
	private String  departmentParent="平度市安监局";
	
	public DepartmentView()
	{
		super();
		departmentParent="平度市安监局";
	}
	
	public DepartmentView( String deptName, String deptaddr, String deptperson, String deptPTel,String deptTel,Integer Code) {
		super();
	    departmentCode=Code;
	    departmentName=deptName;
	    departmentAddr=deptaddr;
		departmentPerson=deptperson;
	    departmentPersonTel=deptPTel;
		departmentTel=deptTel;
   	    departmentParent="平度市安监局";
		
	}
	
	public Integer getDepartmentCode()
	{
		return departmentCode;
	}

	public String getDepartmentName()
	{
		return departmentName;
	}
	
	public String getDepartmentAddr()
	{
		return departmentAddr;
	}
	
	public String getDepartmentPerson()
	{
		return departmentPerson;
	}
	
	public String getDepartmentPersonTel()
	{
		return departmentPersonTel;
	}
	
	public String getDepartmentTel()
	{
		return departmentTel;
	}
	
	public void setDepartmentCode(Integer c)
	{
		departmentCode=c;
	}
	
	public void setDepartmentName(String x)
	{
		departmentName =x;
	}
	
	public void setDepartmentAddr(String x)
	{
		departmentAddr =x;
	}
	
	public void setDepartmentPerson(String x)
	{
		departmentPerson =x;
	}
	
	public void setDepartmentTel(String x)
	{
		departmentTel =x;
	}
	
	public void setDepartmentPersonTel(String x)
	{
		departmentPersonTel =x;
	}
	
	public String getDepartmentParent()
	{
		return departmentParent;
	}
	
	public void setDepartmentParent(String x)
	{
		departmentParent=x;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
