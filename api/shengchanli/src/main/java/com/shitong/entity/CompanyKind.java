package	com.shitong.entity;
import java.sql.*;


public class CompanyKind {
	private Integer kindId;
	private String kindName;
	private Date identifyYear;
	private Integer detailId;
	public void setKindId(Integer kindId){
		this.kindId = kindId;
	}
	public Integer getKindId(){
		return this.kindId;
	}
	public void setKindName(String kindName){
		this.kindName = kindName;
	}
	public String getKindName(){
		return this.kindName;
	}
	public void setIdentifyYear(Date identifyYear){
		this.identifyYear = identifyYear;
	}
	public Date getIdentifyYear(){
		return this.identifyYear;
	}
	public void setDetailId(Integer detailId){
		this.detailId = detailId;
	}
	public Integer getDetailId(){
		return this.detailId;
	}
}
