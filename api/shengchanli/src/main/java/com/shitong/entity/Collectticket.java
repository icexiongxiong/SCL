package	com.shitong.entity;
import java.sql.*;


/** 
* @author  半天  
* @version 创建时间： 2018年05月09日 17时20分03秒  星期三 
*/ 
public class Collectticket {     	//数据库中表名：collectticket
	private Integer id;    	//字段名：id
	private String organizationname;    	//字段名：organizationname
	private String address;    	//字段名：address
	private String person;    	//字段名：person
	private String phone;    	//字段名：phone
	private String serverarea;    	//字段名：serverarea
	private String ticketmoney;    	//字段名：ticketmoney
	private String cashmoney;    	//字段名：cashmoney
	private String email;    	//字段名：email
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
	public void setOrganizationname(String organizationname){
		this.organizationname = organizationname;
	}
	public String getOrganizationname(){
		return this.organizationname;
	}
	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress(){
		return this.address;
	}
	public void setPerson(String person){
		this.person = person;
	}
	public String getPerson(){
		return this.person;
	}
	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getPhone(){
		return this.phone;
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
