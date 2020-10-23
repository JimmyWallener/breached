package se.breached.model;


import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

public class Breached{

    private String name;
    private String title;
    private String domain;
    private LocalDate breachDate;
    private ZonedDateTime addedDate;
    private ZonedDateTime modifiedDate;
    private int pwnCount;
    private String description;
    private List<String> dataClasses;
    private boolean isVerified;
    private boolean isFabricated;
    private boolean isSensitive;
    private boolean isRetired;
    private String logoPath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public LocalDate getBreachDate() {
        return breachDate;
    }

    public void setBreachDate(LocalDate breachDate) {
        this.breachDate = breachDate;
    }

    public ZonedDateTime getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(ZonedDateTime addedDate) {
        this.addedDate = addedDate;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(ZonedDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public int getPwnCount() {
        return pwnCount;
    }

    public void setPwnCount(int pwnCount) {
        this.pwnCount = pwnCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getDataClasses() {
        return dataClasses;
    }

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

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }
}
