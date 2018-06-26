package	com.shitong.entity;
import java.sql.*;


/** 
* @author  半天  
* @version 创建时间： 2018年05月09日 17时20分03秒  星期三 
*/ 
public class CompanyResearch {     	//数据库中表名：company_research
	private Integer id;    	//字段名：id
	private String researchName;    	//字段名：research_name
	private String researchCompany;    	//字段名：research_company
	private String researchField;    	//字段名：research_field
	private String province;    	//字段名：province
	private String city;    	//字段名：city
	private String county;    	//字段名：county
	private String platKind;    	//字段名：plat_kind,平台级别
	private String platClass;    	//字段名：plat_class,平台类别
	private String researchTotalincome;    	//字段名：research_totalincome
	private String totalbenefit;    	//字段名：totalbenefit
	private String projectGross;    	//字段名：project_gross
	private Integer resultTransfornum;    	//字段名：result_transfornum
	private Integer patentNum;    	//字段名：patent_num
	private String inventPatent;    	//字段名：invent_patent
	private String newPatent;    	//字段名：new_patent
	private String newInventpatent;    	//字段名：new_inventpatent
	private String researchgroupPerson;    	//字段名：researchgroup_person
	private String thousandpplannum;    	//字段名：thousandpplannum
	private String thousandpplanname;    	//字段名：thousandpplanname
	private String academiciannum;    	//字段名：academiciannum
	private String academicianname;    	//字段名：academicianname
	private String reasearchSitearea;    	//字段名：reasearch_sitearea
	private String newArea;    	//字段名：new_area
	private String introducePersontrain;    	//字段名：introduce_persontrain
	private Integer deviceNum;    	//字段名：device_num
	private String deviceValue;    	//字段名：device_value
	private String keyTechnical;    	//字段名：key_technical
	private String internalStandard;    	//字段名：internal_standard
	private String nationalStandard;    	//字段名：national_standard
	private String industrialStandard;    	//字段名：industrial_standard
	private String companyKindname;    	//字段名：company_kindname,下拉框对应的企业类型名
	private Integer userId;    	//字段名：user_id
	private Integer organizationId;    	//字段名：organization_id
	private String username;    	//字段名：username
	private String organName;    	//字段名：organ_name
	
	//客户新增需求加字段
	private String thousandpplanname2; //千人计划姓名2
	private String thousandpplanname3; 
	private String thousandpplanname4;
	private String thousandpplanname5;
	private String academicianname2;  //院士姓名2
	private String academicianname3;
	private String academicianname4;
	private String academicianname5;
    private String companytypes;  //多个企业类型
    private String detailaddress; //更加详细地址
    	

	public String getDetailaddress() {
		return detailaddress;
	}
	public void setDetailaddress(String detailaddress) {
		this.detailaddress = detailaddress;
	}
	public String getCompanytypes() {
		return companytypes;
	}
	public void setCompanytypes(String companytypes) {
		this.companytypes = companytypes;
	}
	public String getThousandpplanname2() {
		return thousandpplanname2;
	}
	public void setThousandpplanname2(String thousandpplanname2) {
		this.thousandpplanname2 = thousandpplanname2;
	}
	public String getThousandpplanname3() {
		return thousandpplanname3;
	}
	public void setThousandpplanname3(String thousandpplanname3) {
		this.thousandpplanname3 = thousandpplanname3;
	}
	public String getThousandpplanname4() {
		return thousandpplanname4;
	}
	public void setThousandpplanname4(String thousandpplanname4) {
		this.thousandpplanname4 = thousandpplanname4;
	}
	public String getThousandpplanname5() {
		return thousandpplanname5;
	}
	public void setThousandpplanname5(String thousandpplanname5) {
		this.thousandpplanname5 = thousandpplanname5;
	}
	public String getAcademicianname2() {
		return academicianname2;
	}
	public void setAcademicianname2(String academicianname2) {
		this.academicianname2 = academicianname2;
	}
	public String getAcademicianname3() {
		return academicianname3;
	}
	public void setAcademicianname3(String academicianname3) {
		this.academicianname3 = academicianname3;
	}
	public String getAcademicianname4() {
		return academicianname4;
	}
	public void setAcademicianname4(String academicianname4) {
		this.academicianname4 = academicianname4;
	}
	public String getAcademicianname5() {
		return academicianname5;
	}
	public void setAcademicianname5(String academicianname5) {
		this.academicianname5 = academicianname5;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setResearchName(String researchName){
		this.researchName = researchName;
	}
	public String getResearchName(){
		return this.researchName;
	}
	public void setResearchCompany(String researchCompany){
		this.researchCompany = researchCompany;
	}
	public String getResearchCompany(){
		return this.researchCompany;
	}
	public void setResearchField(String researchField){
		this.researchField = researchField;
	}
	public String getResearchField(){
		return this.researchField;
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
	public void setCounty(String county){
		this.county = county;
	}
	public String getCounty(){
		return this.county;
	}
	public void setPlatKind(String platKind){
		this.platKind = platKind;
	}
	public String getPlatKind(){
		return this.platKind;
	}
	public void setPlatClass(String platClass){
		this.platClass = platClass;
	}
	public String getPlatClass(){
		return this.platClass;
	}
	public void setResearchTotalincome(String researchTotalincome){
		this.researchTotalincome = researchTotalincome;
	}
	public String getResearchTotalincome(){
		return this.researchTotalincome;
	}
	public void setTotalbenefit(String totalbenefit){
		this.totalbenefit = totalbenefit;
	}
	public String getTotalbenefit(){
		return this.totalbenefit;
	}
	public void setProjectGross(String projectGross){
		this.projectGross = projectGross;
	}
	public String getProjectGross(){
		return this.projectGross;
	}
	public void setResultTransfornum(Integer resultTransfornum){
		this.resultTransfornum = resultTransfornum;
	}
	public Integer getResultTransfornum(){
		return this.resultTransfornum;
	}
	public void setPatentNum(Integer patentNum){
		this.patentNum = patentNum;
	}
	public Integer getPatentNum(){
		return this.patentNum;
	}
	public void setInventPatent(String inventPatent){
		this.inventPatent = inventPatent;
	}
	public String getInventPatent(){
		return this.inventPatent;
	}
	public void setNewPatent(String newPatent){
		this.newPatent = newPatent;
	}
	public String getNewPatent(){
		return this.newPatent;
	}
	public void setNewInventpatent(String newInventpatent){
		this.newInventpatent = newInventpatent;
	}
	public String getNewInventpatent(){
		return this.newInventpatent;
	}
	public void setResearchgroupPerson(String researchgroupPerson){
		this.researchgroupPerson = researchgroupPerson;
	}
	public String getResearchgroupPerson(){
		return this.researchgroupPerson;
	}
	public void setThousandpplannum(String thousandpplannum){
		this.thousandpplannum = thousandpplannum;
	}
	public String getThousandpplannum(){
		return this.thousandpplannum;
	}
	public void setThousandpplanname(String thousandpplanname){
		this.thousandpplanname = thousandpplanname;
	}
	public String getThousandpplanname(){
		return this.thousandpplanname;
	}
	public void setAcademiciannum(String academiciannum){
		this.academiciannum = academiciannum;
	}
	public String getAcademiciannum(){
		return this.academiciannum;
	}
	public void setAcademicianname(String academicianname){
		this.academicianname = academicianname;
	}
	public String getAcademicianname(){
		return this.academicianname;
	}
	public void setReasearchSitearea(String reasearchSitearea){
		this.reasearchSitearea = reasearchSitearea;
	}
	public String getReasearchSitearea(){
		return this.reasearchSitearea;
	}
	public void setNewArea(String newArea){
		this.newArea = newArea;
	}
	public String getNewArea(){
		return this.newArea;
	}
	public void setIntroducePersontrain(String introducePersontrain){
		this.introducePersontrain = introducePersontrain;
	}
	public String getIntroducePersontrain(){
		return this.introducePersontrain;
	}
	public void setDeviceNum(Integer deviceNum){
		this.deviceNum = deviceNum;
	}
	public Integer getDeviceNum(){
		return this.deviceNum;
	}
	public void setDeviceValue(String deviceValue){
		this.deviceValue = deviceValue;
	}
	public String getDeviceValue(){
		return this.deviceValue;
	}
	public void setKeyTechnical(String keyTechnical){
		this.keyTechnical = keyTechnical;
	}
	public String getKeyTechnical(){
		return this.keyTechnical;
	}
	public void setInternalStandard(String internalStandard){
		this.internalStandard = internalStandard;
	}
	public String getInternalStandard(){
		return this.internalStandard;
	}
	public void setNationalStandard(String nationalStandard){
		this.nationalStandard = nationalStandard;
	}
	public String getNationalStandard(){
		return this.nationalStandard;
	}
	public void setIndustrialStandard(String industrialStandard){
		this.industrialStandard = industrialStandard;
	}
	public String getIndustrialStandard(){
		return this.industrialStandard;
	}
	public void setCompanyKindname(String companyKindname){
		this.companyKindname = companyKindname;
	}
	public String getCompanyKindname(){
		return this.companyKindname;
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
