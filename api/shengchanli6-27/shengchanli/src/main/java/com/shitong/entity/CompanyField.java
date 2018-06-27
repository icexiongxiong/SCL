package	com.shitong.entity;
import java.sql.*;


public class CompanyField {
	private Integer fieldId;
	private String fieldName;
	private Integer fieldParandid;
	public void setFieldId(Integer fieldId){
		this.fieldId = fieldId;
	}
	public Integer getFieldId(){
		return this.fieldId;
	}
	public void setFieldName(String fieldName){
		this.fieldName = fieldName;
	}
	public String getFieldName(){
		return this.fieldName;
	}
	public void setFieldParandid(Integer fieldParandid){
		this.fieldParandid = fieldParandid;
	}
	public Integer getFieldParandid(){
		return this.fieldParandid;
	}
}
