package com.boot.RestWithSpringBoot.model;

import java.util.Date;

public class Package {

    private Integer id;

    private String name;

    private Date installed;

    private String packageAuthor;

    private String license;

    public Package() {
    }

    public Package(Integer id, String name, Date installed, String packageAuthor, String license) {
        this.id = id;
        this.name = name;
        this.installed = installed;
        this.packageAuthor = packageAuthor;
        this.license = license;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getInstalled() {
        return installed;
    }

    public void setInstalled(Date installed) {
        this.installed = installed;
    }

    public String getPackageAuthor() {
        return packageAuthor;
    }

    public void setPackageAuthor(String packageAuthor) {
        this.packageAuthor = packageAuthor;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }
}
