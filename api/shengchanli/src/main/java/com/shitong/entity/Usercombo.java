package	com.shitong.entity;
import java.sql.*;


/** 
* @author  半天  
* @version 创建时间： 2018年05月22日 08时29分14秒  星期二 
*/ 
public class Usercombo {     	//数据库中表名：usercombo
	private Integer userId;    	//字段名：user_id
	private String username;    	//字段名：username
	private Integer organizationId;    	//字段名：organization_id
	private String organName;    	//字段名：organ_name
	private Boolean leader;    	//字段名：leader

	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public Integer getUserId(){
		return this.userId;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}
	public void setOrganizationId(Integer organizationId){
		this.organizationId = organizationId;
	}
	public Integer getOrganizationId(){
		return this.organizationId;
	}
	public void setOrganName(String organName){
		this.organName = organName;
	}
	public String getOrganName(){
		return this.organName;
	}
	public void setLeader(Boolean leader){
		this.leader = leader;
	}
	public Boolean getLeader(){
		return this.leader;
	}
}
