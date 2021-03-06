package com.yunxin.cb.system.entity;


import com.yunxin.cb.system.meta.ProfileName;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

/**
 *
 *  系统配置
 */
@Entity
@DynamicInsert
@DynamicUpdate
@Table
public class Profile implements Serializable {

    private static final long serialVersionUID = -7714864051064766676L;

    private int fileId;

    @NotNull
    private ProfileName profileName;

    @Length(max = 5000)
    private String fileValue;

    @Length(max = 500)
    private String remarks;

    private int isPicture;

    public Profile() {

    }
    public Profile(ProfileName profileName,String fileValue) {
           this.profileName=profileName;
           this.fileValue=fileValue;
    }

    public Profile(int fileId) {
        this.fileId = fileId;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(unique = true, nullable = false)
    public int getFileId() {
        return this.fileId;
    }

    public void setFileId(int fileId) {
        this.fileId = fileId;
    }

    @Column(length = 128, nullable = false, unique = true)
    @Enumerated(value = EnumType.STRING)
    public ProfileName getProfileName() {
        return this.profileName;
    }

    public void setProfileName(ProfileName profileName) {
        this.profileName = profileName;
    }

    @Column(length = 5000)
    public String getFileValue() {
        return fileValue;
    }

    public void setFileValue(String fileValue) {
        this.fileValue = fileValue;
    }

    @Column(length = 500)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getIsPicture() {
        return isPicture;
    }

    public void setIsPicture(int isPicture) {
        this.isPicture = isPicture;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "fileId=" + fileId +
                ", profileName='" + profileName + '\'' +
                ", fileValue='" + fileValue + '\'' +
                '}';
    }
}