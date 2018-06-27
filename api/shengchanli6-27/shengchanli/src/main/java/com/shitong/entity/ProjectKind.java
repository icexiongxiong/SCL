package	com.shitong.entity;
import java.sql.*;


public class ProjectKind {
	private Integer projectId;
	private String projectName;
	private Date declareYear;
	private Integer period;
	private Integer detailId;
	private Date periodYear;
	public void setProjectId(Integer projectId){
		this.projectId = projectId;
	}
	public Integer getProjectId(){
		return this.projectId;
	}
	public void setProjectName(String projectName){
		this.projectName = projectName;
	}
	public String getProjectName(){
		return this.projectName;
	}
	public void setDeclareYear(Date declareYear){
		this.declareYear = declareYear;
	}
	public Date getDeclareYear(){
		return this.declareYear;
	}
	public void setPeriod(Integer period){
		this.period = period;
	}
	public Integer getPeriod(){
		return this.period;
	}
	public void setDetailId(Integer detailId){
		this.detailId = detailId;
	}
	public Integer getDetailId(){
		return this.detailId;
	}
	public void setPeriodYear(Date periodYear){
		this.periodYear = periodYear;
	}
	public Date getPeriodYear(){
		return this.periodYear;
	}
}
