package com.pingdu.entity.rescue;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.pingdu.baseModel.BaseEntity;

/**
 * (RESCUE)
 * 
 * @description--应急救援库
 * @
 */
@Entity
@Table(name = "rescue")
public class Rescue extends BaseEntity implements java.io.Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1142211810289009098L;

	/**  */
    @Id
    @Column(name = "rescueCode", unique = true, nullable = false, length = 11)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer rescueCode;
    
    /**  */
    @Column(name = "rescueName", nullable = true, length = 100)
    private String rescueName;
    
    /**  */
    @Column(name = "phone", nullable = true, length = 20)
    private String phone;
    
    /**  */
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    
    /**  */
    @Column(name = "address", nullable = true, length = 100)
    private String address;
    
    /**  */
    @Column(name = "note", nullable = true, length = 100)
    private String note;
    
    /**
     * 获取
     * 
     * @return 
     */
    public Integer getRescueCode() {
        return this.rescueCode;
    }
     
    /**
     * 设置
     * 
     * @param rescueCode
     *          
     */
    public void setRescueCode(Integer rescueCode) {
        this.rescueCode = rescueCode;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getRescueName() {
        return this.rescueName;
    }
     
    /**
     * 设置
     * 
     * @param rescueName
     *          
     */
    public void setRescueName(String rescueName) {
        this.rescueName = rescueName;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getPhone() {
        return this.phone;
    }
     
    /**
     * 设置
     * 
     * @param phone
     *          
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getEmail() {
        return this.email;
    }
     
    /**
     * 设置
     * 
     * @param email
     *          
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getAddress() {
        return this.address;
    }
     
    /**
     * 设置
     * 
     * @param address
     *          
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * 获取
     * 
     * @return 
     */
    public String getNote() {
        return this.note;
    }
     
    /**
     * 设置
     * 
     * @param note
     *          
     */
    public void setNote(String note) {
        this.note = note;
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