package	com.shitong.entity;
import java.sql.*;


/** 
* @author  半天  
* @version 创建时间： 2018年05月09日 17时20分03秒  星期三 
*/ 
public class Company {     	//数据库中表名：company
	private Integer id;    	//字段名：id
	private String companyname;    	//字段名：companyname
	private String creditcode;    	//字段名：creditcode
	private String legalperson;    	//字段名：legalperson
	private String fund;    	//字段名：fund
	private Date buildtime;    	//字段名：buildtime
	private String companytype;    	//字段名：companytype
	private String province;    	//字段名：province
	private String city;    	//字段名：city
	private String coutry;    	//字段名：coutry
	private String contactperson;    	//字段名：contactperson
	private String telphone;    	//字段名：telphone
	private String mainfield;    	//字段名：mainfield
	private String legaltelphone;    	//字段名：legaltelphone
	private String stockmessage;    	//字段名：stockmessage
	private Integer companynum;    	//字段名：companynum
	private String weixin;    	//字段名：weixin
	private String email;    	//字段名：email
	private Integer userId;    	//字段名：user_id
	private Integer organizationId;    	//字段名：organization_id
	private String username;    	//字段名：username
	private String organName;    	//字段名：organ_name
	//后面需求客户加的
	
	private String whetherpublic;  //企业是否上市
	private String publiccode; //上市代码
	private Date publictime; //上市时间
	private String publictype; //上市类型
	private String stockcode; //股票代码
	private String industrykind; //所属行业
	private String staffnum; //企业职工人数
	private String sciencestaffnum; //科技人员人数
	private String detailaddress; //更加详细地址
	
	
	public Date getPublictime() {
		return publictime;
	}
	public void setPublictime(Date publictime) {
		this.publictime = publictime;
	}
	public String getDetailaddress() {
		return detailaddress;
	}
	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}
	public String getWhetherpublic() {
		return whetherpublic;
	}
	public void setWhetherpublic(String whetherpublic) {
		this.whetherpublic = whetherpublic;
	}
	public String getPubliccode() {
		return publiccode;
	}
	public void setPubliccode(String publiccode) {
		this.publiccode = publiccode;
	}

	public String getPublictype() {
		return publictype;
	}
	public void setPublictype(String publictype) {
		this.publictype = publictype;
	}
	public String getStockcode() {
		return stockcode;
	}
	public void setStockcode(String stockcode) {
		this.stockcode = stockcode;
	}
	public String getIndustrykind() {
		return industrykind;
	}
	public void setIndustrykind(String industrykind) {
		this.industrykind = industrykind;
	}
	public String getStaffnum() {
		return staffnum;
	}
	public void setStaffnum(String staffnum) {
		this.staffnum = staffnum;
	}
	public String getSciencestaffnum() {
		return sciencestaffnum;
	}
	public void setSciencestaffnum(String sciencestaffnum) {
		this.sciencestaffnum = sciencestaffnum;
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
	public void setCreditcode(String creditcode){
		this.creditcode = creditcode;
	}
	public String getCreditcode(){
		return this.creditcode;
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
	public void setBuildtime(Date buildtime){
		this.buildtime = buildtime;
	}
	public Date getBuildtime(){
		return this.buildtime;
	}
	public void setCompanytype(String companytype){
		this.companytype = companytype;
	}
	public String getCompanytype(){
		return this.companytype;
	}
	public void setProvince(String province){
		this.province = province;
	}
	public String getProvince(){
		return this.province;
	}
	public void setCity(String city){
		this.city = city;
	}
	public String getCity(){
		return this.city;
	}
	public void setCoutry(String coutry){
		this.coutry = coutry;
	}
	public String getCoutry(){
		return this.coutry;
	}
	public void setContactperson(String contactperson){
		this.contactperson = contactperson;
	}
	public String getContactperson(){
		return this.contactperson;
	}
	public void setTelphone(String telphone){
		this.telphone = telphone;
	}
	public String getTelphone(){
		return this.telphone;
	}
	public void setMainfield(String mainfield){
		this.mainfield = mainfield;
	}
	public String getMainfield(){
		return this.mainfield;
	}
	public void setLegaltelphone(String legaltelphone){
		this.legaltelphone = legaltelphone;
	}
	public String getLegaltelphone(){
		return this.legaltelphone;
	}
	public void setStockmessage(String stockmessage){
		this.stockmessage = stockmessage;
	}
	public String getStockmessage(){
		return this.stockmessage;
	}
	public void setCompanynum(Integer companynum){
		this.companynum = companynum;
	}
	public Integer getCompanynum(){
		return this.companynum;
	}
	public void setWeixin(String weixin){
		this.weixin = weixin;
	}
	public String getWeixin(){
		return this.weixin;
	}
	public void setEmail(String email){
		this.email = email;
	}
	public String getEmail(){
		return this.email;
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
