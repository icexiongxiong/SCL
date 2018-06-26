package	com.shitong.entity;
import java.sql.*;


public class SystemKind {
	private Integer systemId;
	private String systemName;
	private Date periodTime;
	private Integer detailId;
	public void setSystemId(Integer systemId){
		this.systemId = systemId;
	}
	public Integer getSystemId(){
		return this.systemId;
	}
	public void setSystemName(String systemName){
		this.systemName = systemName;
	}
	public String getSystemName(){
		return this.systemName;
	}
	public void setPeriodTime(Date periodTime){
		this.periodTime = periodTime;
	}
	public Date getPeriodTime(){
		return this.periodTime;
	}
	public void setDetailId(Integer detailId){
		this.detailId = detailId;
	}
	public Integer getDetailId(){
		return this.detailId;
	}
}
