package	com.shitong.entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/** 
* @author  半天  
* @version 创建时间： 2018年05月10日 12时22分05秒  星期四 
*/ 
public class CompanyDetail {     	//数据库中表名：company_detail
	private Integer id;    	//字段名：id
	private Integer companyid;    	//字段名：companyid
	private String commonBrand;    	//字段名：common_brand
	private String famousBrand;    	//字段名：famous_brand
	private String resoundBrand;    	//字段名：resound_brand
	private String softwareCopyright;    	//字段名：software_copyright
	private String invention;    	//字段名：invention
	private String utilityModel;    	//字段名：utility_model
	private String appearModel;    	//字段名：appear_model
	private String otherModel;    	//字段名：other_model
	private String companyNature;    	//字段名：company_nature
	private String researchKind;    	//字段名：research_kind
	private Integer researchNum;    	//字段名：research_num
	private Date recordYear;    	//字段名：record_year
	private String researchMoney;    	//字段名：research_money
	private String contractExploit;    	//字段名：contract_exploit
	private String contractTransfer;    	//字段名：contract_transfer
	private String contractService;    	//字段名：contract_service
	private String contractConsult;    	//字段名：contract_consult
	private String industryStudyResearch;    	//字段名：industry_study_research
	private String operateAgency;    	//字段名：operate_agency
	private String finance;    	//字段名：finance
	private String instrumentShare;    	//字段名：instrument_share
	private String instrumentName;    	//字段名：instrument_name
	private String instrumentType;    	//字段名：instrument_type
	private String instrumentMoney;    	//字段名：instrument_money
	private String authenProject;    	//字段名：authen_project
	private String authenGist;    	//字段名：authen_gist
	private String certificateNum;    	//字段名：certificate_num
	private String authenScope;    	//字段名：authen_scope
	private String authenOrgname;    	//字段名：authen_orgname
	private Date awardTime;    	//字段名：award_time
	private Date awardFirsttime;    	//字段名：award_firsttime
	private Date newTime;    	//字段名：new_time
	private String certificateState;    	//字段名：certificate_state
	private Integer userId;    	//字段名：user_id
	private Integer organizationId;    	//字段名：organization_id
	private String username;    	//字段名：username
	private String organName;    	//字段名：organ_name
	private Date applytime;    	//字段名：applytime
	private String applymoney;    	//字段名：applymoney
	private String cashmoney;    	//字段名：cashmoney
	private String innovatetype;    	//字段名：innovatetype
	private Date startyear;    	//字段名：startyear
	private Date endyear;    	//字段名：endyear
	private List<CompanyKind> companyKindList = new  ArrayList<CompanyKind>();
	private List<ProjectKind> projectKindList = new ArrayList<ProjectKind>();
	private List<ServerKind> serverKindList = new ArrayList<ServerKind>();
	private List<SystemKind> systemKindList = new ArrayList<SystemKind>();
	private String companyKinds ;
	private String projectKinds ;
	private String serverKinds ;
	private String systemKinds ;
	

	
	public Date getStartyear() {
		return startyear;
	}
	public void setStartyear(Date startyear) {
		this.startyear = startyear;
	}
	public Date getEndyear() {
		return endyear;
	}
	public void setEndyear(Date endyear) {
		this.endyear = endyear;
	}
	public List<CompanyKind> getCompanyKindList() {
		return companyKindList;
	}
	public void setCompanyKindList(List<CompanyKind> companyKindList) {
		this.companyKindList = companyKindList;
	}
	public List<ProjectKind> getProjectKindList() {
		return projectKindList;
	}
	public void setProjectKindList(List<ProjectKind> projectKindList) {
		this.projectKindList = projectKindList;
	}
	public List<ServerKind> getServerKindList() {
		return serverKindList;
	}
	public void setServerKindList(List<ServerKind> serverKindList) {
		this.serverKindList = serverKindList;
	}
	public List<SystemKind> getSystemKindList() {
		return systemKindList;
	}
	public void setSystemKindList(List<SystemKind> systemKindList) {
		this.systemKindList = systemKindList;
	}
	public String getCompanyKinds() {
		return companyKinds;
	}
	public void setCompanyKinds(String companyKinds) {
		this.companyKinds = companyKinds;
	}
	public String getProjectKinds() {
		return projectKinds;
	}
	public void setProjectKinds(String projectKinds) {
		this.projectKinds = projectKinds;
	}
	public String getServerKinds() {
		return serverKinds;
	}
	public void setServerKinds(String serverKinds) {
		this.serverKinds = serverKinds;
	}
	public String getSystemKinds() {
		return systemKinds;
	}
	public void setSystemKinds(String systemKinds) {
		this.systemKinds = systemKinds;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public Integer getId(){
		return this.id;
	}
	public void setCompanyid(Integer companyid){
		this.companyid = companyid;
	}
	public Integer getCompanyid(){
		return this.companyid;
	}
	public void setCommonBrand(String commonBrand){
		this.commonBrand = commonBrand;
	}
	public String getCommonBrand(){
		return this.commonBrand;
	}
	public void setFamousBrand(String famousBrand){
		this.famousBrand = famousBrand;
	}
	public String getFamousBrand(){
		return this.famousBrand;
	}
	public void setResoundBrand(String resoundBrand){
		this.resoundBrand = resoundBrand;
	}
	public String getResoundBrand(){
		return this.resoundBrand;
	}
	public void setSoftwareCopyright(String softwareCopyright){
		this.softwareCopyright = softwareCopyright;
	}
	public String getSoftwareCopyright(){
		return this.softwareCopyright;
	}
	public void setInvention(String invention){
		this.invention = invention;
	}
	public String getInvention(){
		return this.invention;
	}
	public void setUtilityModel(String utilityModel){
		this.utilityModel = utilityModel;
	}
	public String getUtilityModel(){
		return this.utilityModel;
	}
	public void setAppearModel(String appearModel){
		this.appearModel = appearModel;
	}
	public String getAppearModel(){
		return this.appearModel;
	}
	public void setOtherModel(String otherModel){
		this.otherModel = otherModel;
	}
	public String getOtherModel(){
		return this.otherModel;
	}
	public void setCompanyNature(String companyNature){
		this.companyNature = companyNature;
	}
	public String getCompanyNature(){
		return this.companyNature;
	}
	public void setResearchKind(String researchKind){
		this.researchKind = researchKind;
	}
	public String getResearchKind(){
		return this.researchKind;
	}
	public void setResearchNum(Integer researchNum){
		this.researchNum = researchNum;
	}
	public Integer getResearchNum(){
		return this.researchNum;
	}
	public void setRecordYear(Date recordYear){
		this.recordYear = recordYear;
	}
	public Date getRecordYear(){
		return this.recordYear;
	}
	public void setResearchMoney(String researchMoney){
		this.researchMoney = researchMoney;
	}
	public String getResearchMoney(){
		return this.researchMoney;
	}
	public void setContractExploit(String contractExploit){
		this.contractExploit = contractExploit;
	}
	public String getContractExploit(){
		return this.contractExploit;
	}
	public void setContractTransfer(String contractTransfer){
		this.contractTransfer = contractTransfer;
	}
	public String getContractTransfer(){
		return this.contractTransfer;
	}
	public void setContractService(String contractService){
		this.contractService = contractService;
	}
	public String getContractService(){
		return this.contractService;
	}
	public void setContractConsult(String contractConsult){
		this.contractConsult = contractConsult;
	}
	public String getContractConsult(){
		return this.contractConsult;
	}
	public void setIndustryStudyResearch(String industryStudyResearch){
		this.industryStudyResearch = industryStudyResearch;
	}
	public String getIndustryStudyResearch(){
		return this.industryStudyResearch;
	}
	public void setOperateAgency(String operateAgency){
		this.operateAgency = operateAgency;
	}
	public String getOperateAgency(){
		return this.operateAgency;
	}
	public void setFinance(String finance){
		this.finance = finance;
	}
	public String getFinance(){
		return this.finance;
	}
	public void setInstrumentShare(String instrumentShare){
		this.instrumentShare = instrumentShare;
	}
	public String getInstrumentShare(){
		return this.instrumentShare;
	}
	public void setInstrumentName(String instrumentName){
		this.instrumentName = instrumentName;
	}
	public String getInstrumentName(){
		return this.instrumentName;
	}
	public void setInstrumentType(String instrumentType){
		this.instrumentType = instrumentType;
	}
	public String getInstrumentType(){
		return this.instrumentType;
	}
	public void setInstrumentMoney(String instrumentMoney){
		this.instrumentMoney = instrumentMoney;
	}
	public String getInstrumentMoney(){
		return this.instrumentMoney;
	}
	public void setAuthenProject(String authenProject){
		this.authenProject = authenProject;
	}
	public String getAuthenProject(){
		return this.authenProject;
	}
	public void setAuthenGist(String authenGist){
		this.authenGist = authenGist;
	}
	public String getAuthenGist(){
		return this.authenGist;
	}
	public void setCertificateNum(String certificateNum){
		this.certificateNum = certificateNum;
	}
	public String getCertificateNum(){
		return this.certificateNum;
	}
	public void setAuthenScope(String authenScope){
		this.authenScope = authenScope;
	}
	public String getAuthenScope(){
		return this.authenScope;
	}
	public void setAuthenOrgname(String authenOrgname){
		this.authenOrgname = authenOrgname;
	}
	public String getAuthenOrgname(){
		return this.authenOrgname;
	}
	public void setAwardTime(Date awardTime){
		this.awardTime = awardTime;
	}
	public Date getAwardTime(){
		return this.awardTime;
	}
	public void setAwardFirsttime(Date awardFirsttime){
		this.awardFirsttime = awardFirsttime;
	}
	public Date getAwardFirsttime(){
		return this.awardFirsttime;
	}
	public void setNewTime(Date newTime){
		this.newTime = newTime;
	}
	public Date getNewTime(){
		return this.newTime;
	}
	public void setCertificateState(String certificateState){
		this.certificateState = certificateState;
	}
	public String getCertificateState(){
		return this.certificateState;
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
	public void setApplytime(Date applytime){
		this.applytime = applytime;
	}
	public Date getApplytime(){
		return this.applytime;
	}
	public void setApplymoney(String applymoney){
		this.applymoney = applymoney;
	}
	public String getApplymoney(){
		return this.applymoney;
	}
	public void setCashmoney(String cashmoney){
		this.cashmoney = cashmoney;
	}
	public String getCashmoney(){
		return this.cashmoney;
	}
	public void setInnovatetype(String innovatetype){
		this.innovatetype = innovatetype;
	}
	public String getInnovatetype(){
		return this.innovatetype;
	}
}
