package	com.shitong.entity;
import java.sql.*;


/** 
* @author  半天  
* @version 创建时间： 2018年05月15日 13时18分08秒  星期二 
*/ 
public class Authen {     	//数据库中表名：authen
	private Integer id;    	//字段名：id
	private String companyname;    	//字段名：companyname
	private String address;    	//字段名：address
	private String certifyprogram;    	//字段名：certifyprogram
	private String certifygist;    	//字段名：certifygist
	private String certificatenumber;    	//字段名：certificatenumber
	private String certifyscope;    	//字段名：certifyscope
	private String certifyorganname;    	//字段名：certifyorganname
	private Date issuedate;    	//字段名：issuedate
	private Date firstgettime;    	//字段名：firstgettime
	private Date changetime;    	//字段名：changetime
	private String state;    	//字段名：state
	private Integer userId;    	//字段名：user_id
	private Integer organizationId;    	//字段名：organization_id
	private String username;    	//字段名：username
	private String organName;    	//字段名：organ_name

	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setCompanyname(String companyname){
		this.companyname = companyname;
	}
	public String getCompanyname(){
		return this.companyname;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setCertifyprogram(String certifyprogram){
		this.certifyprogram = certifyprogram;
	}
	public String getCertifyprogram(){
		return this.certifyprogram;
	}
	public void setCertifygist(String certifygist){
		this.certifygist = certifygist;
	}
	public String getCertifygist(){
		return this.certifygist;
	}
	public void setCertificatenumber(String certificatenumber){
		this.certificatenumber = certificatenumber;
	}
	public String getCertificatenumber(){
		return this.certificatenumber;
	}
	public void setCertifyscope(String certifyscope){
		this.certifyscope = certifyscope;
	}
	public String getCertifyscope(){
		return this.certifyscope;
	}
	public void setCertifyorganname(String certifyorganname){
		this.certifyorganname = certifyorganname;
	}
	public String getCertifyorganname(){
		return this.certifyorganname;
	}
	public void setIssuedate(Date issuedate){
		this.issuedate = issuedate;
	}
	public Date getIssuedate(){
		return this.issuedate;
	}
	public void setFirstgettime(Date firstgettime){
		this.firstgettime = firstgettime;
	}
	public Date getFirstgettime(){
		return this.firstgettime;
	}
	public void setChangetime(Date changetime){
		this.changetime = changetime;
	}
	public Date getChangetime(){
		return this.changetime;
	}
	public void setState(String state){
		this.state = state;
	}
	public String getState(){
		return this.state;
	}
	public void setUserId(Integer userId){
		this.userId = userId;
	}
	public Integer getUserId(){
		return this.userId;
	}
	public void setOrganizationId(Integer organizationId){
		this.organizationId = organizationId;
	}
	public Integer getOrganizationId(){
		return this.organizationId;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return this.username;
	}
	public void setOrganName(String organName){
		this.organName = organName;
	}
	public String getOrganName(){
		return this.organName;
	}
}
