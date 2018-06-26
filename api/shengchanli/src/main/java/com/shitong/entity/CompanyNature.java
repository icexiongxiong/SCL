package com.shitong.entity;


public class CompanyNature  implements java.io.Serializable {

  

     private Integer natureId;
     private String natureName;



    public CompanyNature() {
    }

    

    public CompanyNature(String natureName) {
        this.natureName = natureName;
    }

   

    public Integer getNatureId() {
        return this.natureId;
    }
    
    public void setNatureId(Integer natureId) {
        this.natureId = natureId;
    }

    public String getNatureName() {
        return this.natureName;
    }
    
    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }
   

}