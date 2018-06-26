package	com.shitong.entity;
import java.sql.*;


/** 
* @author  半天  
* @version 创建时间： 2018年05月09日 17时20分03秒  星期三 
*/ 
public class CompanyTrain {     	//数据库中表名：company_train
	private Integer id;    	//字段名：id
	private String companyname;    	//字段名：companyname
	private Date registertime;    	//字段名：registertime
	private String legalperson;    	//字段名：legalperson
	private String fund;    	//字段名：fund
	private String registertype;    	//字段名：registertype
	private String companyscale;    	//字段名：companyscale
	private String companyfield;    	//字段名：companyfield
	private Date growtime;    	//字段名：growtime
	private Date identifytime;    	//字段名：identifytime
	private String identifybatch;    	//字段名：identifybatch
	private String taxoffice;    	//字段名：taxoffice
	private String region;    	//字段名：region
	private String person;    	//字段名：person
	private String telphone;    	//字段名：telphone
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
	public void setRegistertime(Date registertime){
		this.registertime = registertime;
	}
	public Date getRegistertime(){
		return this.registertime;
	}
	public void setLegalperson(String legalperson){
		this.legalperson = legalperson;
	}
	public String getLegalperson(){
		return this.legalperson;
	}
	public void setFund(String fund){
		this.fund = fund;
	}
	public String getFund(){
		return this.fund;
	}
	public void setRegistertype(String registertype){
		this.registertype = registertype;
	}
	public String getRegistertype(){
		return this.registertype;
	}
	public void setCompanyscale(String companyscale){
		this.companyscale = companyscale;
	}
	public String getCompanyscale(){
		return this.companyscale;
	}
	public void setCompanyfield(String companyfield){
		this.companyfield = companyfield;
	}
	public String getCompanyfield(){
		return this.companyfield;
	}
	public void setGrowtime(Date growtime){
		this.growtime = growtime;
	}
	public Date getGrowtime(){
		return this.growtime;
	}
	public void setIdentifytime(Date identifytime){
		this.identifytime = identifytime;
	}
	public Date getIdentifytime(){
		return this.identifytime;
	}
	public void setIdentifybatch(String identifybatch){
		this.identifybatch = identifybatch;
	}
	public String getIdentifybatch(){
		return this.identifybatch;
	}
	public void setTaxoffice(String taxoffice){
		this.taxoffice = taxoffice;
	}
	public String getTaxoffice(){
		return this.taxoffice;
	}
	public void setRegion(String region){
		this.region = region;
	}
	public String getRegion(){
		return this.region;
	}
	public void setPerson(String person){
		this.person = person;
	}
	public String getPerson(){
		return this.person;
	}
	public void setTelphone(String telphone){
		this.telphone = telphone;
	}
	public String getTelphone(){
		return this.telphone;
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
