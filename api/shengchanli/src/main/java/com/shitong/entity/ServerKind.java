package	com.shitong.entity;
import java.sql.*;


public class ServerKind {
	private Integer serverId;
	private String serverName;
	private String serverPerson;
	private String serverTel;
	private Integer detailId;
	public void setServerId(Integer serverId){
		this.serverId = serverId;
	}
	public Integer getServerId(){
		return this.serverId;
	}
	public void setServerName(String serverName){
		this.serverName = serverName;
	}
	public String getServerName(){
		return this.serverName;
	}
	public void setServerPerson(String serverPerson){
		this.serverPerson = serverPerson;
	}
	public String getServerPerson(){
		return this.serverPerson;
	}
	public void setServerTel(String serverTel){
		this.serverTel = serverTel;
	}
	public String getServerTel(){
		return this.serverTel;
	}
	public void setDetailId(Integer detailId){
		this.detailId = detailId;
	}
	public Integer getDetailId(){
		return this.detailId;
	}
}
