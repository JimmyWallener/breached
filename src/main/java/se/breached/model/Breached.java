package se.breached.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Breached implements Serializable {
    private static final long serialVersionUID = 1L;


    private String name, title, domain, breachDate, addedDate,modifiedDate,description, logoPath;
    private int pwnCount;
    private List<String> dataClasses;
    @JsonProperty("IsVerified")
    private boolean isVerified;
    @JsonProperty("IsFabricated")
    private boolean isFabricated;
    @JsonProperty("IsSensitive")
    private boolean isSensitive;
    @JsonProperty("IsRetired")
    private boolean isRetired;
    @JsonProperty("IsSpamList")
    private boolean isSpamList;

    public String getName() {
        return name;
    }
    @JsonSetter("Name")
    public void setName(String name) {
        this.name = name;
    }
    @JsonGetter("Title")
    public String getTitle() {
        return title;
    }
    @JsonSetter("Title")
    public void setTitle(String title) {
        this.title = title;
    }
    @JsonGetter("Domain")
    public String getDomain() {
        return domain;
    }
    @JsonSetter("Domain")
    public void setDomain(String domain) {
        this.domain = domain;
    }
    @JsonGetter("BreachDate")
    public String getBreachDate() {
        return breachDate;
    }
    @JsonSetter("BreachDate")
    public void setBreachDate(String breachDate) {
        this.breachDate = breachDate;
    }
    @JsonGetter("AddedDate")
    public String getAddedDate() {
        return addedDate;
    }
    @JsonSetter("AddedDate")
    public void setAddedDate(String addedDate) {
        this.addedDate = addedDate;
    }
    @JsonGetter("ModifiedDate")
    public String getModifiedDate() {
        return modifiedDate;
    }
    @JsonSetter("ModifiedDate")
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
    @JsonGetter("PwnCount")
    public int getPwnCount() {
        return pwnCount;
    }
    @JsonSetter("PwnCount")
    public void setPwnCount(int pwnCount) {
        this.pwnCount = pwnCount;
    }
    @JsonGetter("Description")
    public String getDescription() {
        return description;
    }
    @JsonSetter("Description")
    public void setDescription(String description) {
        this.description = description;
    }
    @JsonGetter("DataClasses")
    public List<String> getDataClasses() {
        return dataClasses;
    }
    @JsonSetter("DataClasses")
    public void setDataClasses(List<String> dataClasses) {
        this.dataClasses = dataClasses;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    public boolean isFabricated() {
        return isFabricated;
    }

    public void setFabricated(boolean fabricated) {
        isFabricated = fabricated;
    }

    public boolean isSensitive() {
        return isSensitive;
    }

    public void setSensitive(boolean sensitive) {
        isSensitive = sensitive;
    }

    public boolean isRetired() {
        return isRetired;
    }

    public void setRetired(boolean retired) {
        isRetired = retired;
    }
    public boolean isSpamList() { return isSpamList; }

    public void setSpamList(boolean spamList) { isSpamList = spamList; }
    @JsonGetter("LogoPath")
    public String getLogoPath() {
        return logoPath;
    }
    @JsonSetter("LogoPath")
    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
    @Override
    public String toString(){
        return String.format("%nName: %s%nTitle: %s%nDomain: %s%nBreach Date: %s%n" +
                "Added Date: %s%nModified Date: %s%nPwnCount: %d%nDescription: %n%s%n" +
                "DataClasses: %s%nIsVerified: %b%nIsFabricated: %b%nIsSensitive: %b%n"+
                "IsRetired: %b%nIsSpamList: %b%n%n%n" +
                "",name,title,domain,breachDate,addedDate,modifiedDate,pwnCount,description,dataClasses,isVerified,isFabricated,isSensitive,isRetired,isSpamList);
    }
}
