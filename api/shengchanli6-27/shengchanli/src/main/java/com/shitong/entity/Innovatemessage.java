package	com.shitong.entity;
import java.sql.*;


/** 
* @author  半天  
* @version 创建时间： 2018年05月07日 15时35分28秒  星期一 
*/ 
public class Innovatemessage {     	//数据库中表名：innovatemessage
	private Integer id;    	//字段名：id
	private String companyname;    	//字段名：companyname
	private String companyaddress;    	//字段名：companyaddress
	private String companyperson;    	//字段名：companyperson
	private String companyphone;    	//字段名：companyphone
	private String companyemail;    	//字段名：companyemail
	private Date applicationdate;    	//字段名：applicationdate
	private String innovationmoney;    	//字段名：innovationmoney
	private String innovationpurpose;    	//字段名：innovationpurpose
	private String projectname;    	//字段名：projectname
	private String contractvalue;    	//字段名：contractvalue
	private String usevalue;    	//字段名：usevalue
	private String cash;    	//字段名：cash
	private String actualcashmoney;    	//字段名：actualcashmoney
	private Date cashdate;    	//字段名：cashdate
	private String checkcondition;    	//字段名：checkcondition
	private String organizationname;    	//字段名：organizationname
	private String organizationaddress;    	//字段名：organizationaddress
	private String organizationperson;    	//字段名：organizationperson
	private String organizationphone;    	//字段名：organizationphone
	private String serverarea;    	//字段名：serverarea
	private String ticketmoney;    	//字段名：ticketmoney
	private String cashmoney;    	//字段名：cashmoney
	private String organizationemail;    	//字段名：organizationemail

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
	public void setCompanyaddress(String companyaddress){
		this.companyaddress = companyaddress;
	}
	public String getCompanyaddress(){
		return this.companyaddress;
	}
	public void setCompanyperson(String companyperson){
		this.companyperson = companyperson;
	}
	public String getCompanyperson(){
		return this.companyperson;
	}
	public void setCompanyphone(String companyphone){
		this.companyphone = companyphone;
	}
	public String getCompanyphone(){
		return this.companyphone;
	}
	public void setCompanyemail(String companyemail){
		this.companyemail = companyemail;
	}
	public String getCompanyemail(){
		return this.companyemail;
	}
	public void setApplicationdate(Date applicationdate){
		this.applicationdate = applicationdate;
	}
	public Date getApplicationdate(){
		return this.applicationdate;
	}
	public void setInnovationmoney(String innovationmoney){
		this.innovationmoney = innovationmoney;
	}
	public String getInnovationmoney(){
		return this.innovationmoney;
	}
	public void setInnovationpurpose(String innovationpurpose){
		this.innovationpurpose = innovationpurpose;
	}
	public String getInnovationpurpose(){
		return this.innovationpurpose;
	}
	public void setProjectname(String projectname){
		this.projectname = projectname;
	}
	public String getProjectname(){
		return this.projectname;
	}
	public void setContractvalue(String contractvalue){
		this.contractvalue = contractvalue;
	}
	public String getContractvalue(){
		return this.contractvalue;
	}
	public void setUsevalue(String usevalue){
		this.usevalue = usevalue;
	}
	public String getUsevalue(){
		return this.usevalue;
	}
	public void setCash(String cash){
		this.cash = cash;
	}
	public String getCash(){
		return this.cash;
	}
	public void setActualcashmoney(String actualcashmoney){
		this.actualcashmoney = actualcashmoney;
	}
	public String getActualcashmoney(){
		return this.actualcashmoney;
	}
	public void setCashdate(Date cashdate){
		this.cashdate = cashdate;
	}
	public Date getCashdate(){
		return this.cashdate;
	}
	public void setCheckcondition(String checkcondition){
		this.checkcondition = checkcondition;
	}
	public String getCheckcondition(){
		return this.checkcondition;
	}
	public void setOrganizationname(String organizationname){
		this.organizationname = organizationname;
	}
	public String getOrganizationname(){
		return this.organizationname;
	}
	public void setOrganizationaddress(String organizationaddress){
		this.organizationaddress = organizationaddress;
	}
	public String getOrganizationaddress(){
		return this.organizationaddress;
	}
	public void setOrganizationperson(String organizationperson){
		this.organizationperson = organizationperson;
	}
	public String getOrganizationperson(){
		return this.organizationperson;
	}
	public void setOrganizationphone(String organizationphone){
		this.organizationphone = organizationphone;
	}
	public String getOrganizationphone(){
		return this.organizationphone;
	}
	public void setServerarea(String serverarea){
		this.serverarea = serverarea;
	}
	public String getServerarea(){
		return this.serverarea;
	}
	public void setTicketmoney(String ticketmoney){
		this.ticketmoney = ticketmoney;
	}
	public String getTicketmoney(){
		return this.ticketmoney;
	}
	public void setCashmoney(String cashmoney){
		this.cashmoney = cashmoney;
	}
	public String getCashmoney(){
		return this.cashmoney;
	}
	public void setOrganizationemail(String organizationemail){
		this.organizationemail = organizationemail;
	}
	public String getOrganizationemail(){
		return this.organizationemail;
	}
}
