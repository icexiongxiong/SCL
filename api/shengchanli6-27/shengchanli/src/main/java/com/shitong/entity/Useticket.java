package	com.shitong.entity;
import java.sql.*;


/** 
* @author  半天  
* @version 创建时间： 2018年05月09日 17时20分03秒  星期三 
*/ 
public class Useticket {     	//数据库中表名：useticket
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
	private Integer userId;    	//字段名：user_id
	private Integer organizationId;    	//字段名：organization_id
	private String username;    	//字段名：username
	private String organName;    	//字段名：organ_name
	//客户需求新增字段
	private String collectnum; // 收券机构数量
	private String collectname1;  //收券机构名称
	private String collectname2; 
	private String collectname3; 
	private String collectname4; 
	private String collectname5; 
	

	public String getCollectnum() {
		return collectnum;
	}
	public void setCollectnum(String collectnum) {
		this.collectnum = collectnum;
	}
	public String getCollectname1() {
		return collectname1;
	}
	public void setCollectname1(String collectname1) {
		this.collectname1 = collectname1;
	}
	public String getCollectname2() {
		return collectname2;
	}
	public void setCollectname2(String collectname2) {
		this.collectname2 = collectname2;
	}
	public String getCollectname3() {
		return collectname3;
	}
	public void setCollectname3(String collectname3) {
		this.collectname3 = collectname3;
	}
	public String getCollectname4() {
		return collectname4;
	}
	public void setCollectname4(String collectname4) {
		this.collectname4 = collectname4;
	}
	public String getCollectname5() {
		return collectname5;
	}
	public void setCollectname5(String collectname5) {
		this.collectname5 = collectname5;
	}
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
