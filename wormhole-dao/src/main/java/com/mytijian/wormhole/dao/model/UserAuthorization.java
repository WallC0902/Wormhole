package com.mytijian.wormhole.dao.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mytijian.wormhole.dao.constant.YesOrNo;

import java.util.Date;

@TableName("tb_wormhole_user_authorization")
public class UserAuthorization {
    private Integer id;

    @TableField(value = "access_id")
    private Integer accessId;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "id_card")
    private String idCard;

    private String name;

    private String mobile;

    private String gender;

    @TableField(value = "marriage_status")
    private String marriageStatus;

    private String status;

    @TableField(value = "is_sso")
    private YesOrNo isSso;

    @TableField(value = "sso_date")
    private Date ssoDate;

    @TableField(value = "is_medical_report")
    private YesOrNo isMedicalReport;

    @TableField(value = "medical_report_date")
    private Date medicalReportDate;

    @TableField(value = "create_date")
    private Date createDate;

    @TableField(value = "update_date")
    private Date updateDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public String getMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(String marriageStatus) {
        this.marriageStatus = marriageStatus == null ? null : marriageStatus.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public YesOrNo getIsSso() {
        return isSso;
    }

    public void setIsSso(YesOrNo isSso) {
        this.isSso = isSso;
    }

    public YesOrNo getIsMedicalReport() {
        return isMedicalReport;
    }

    public void setIsMedicalReport(YesOrNo isMedicalReport) {
        this.isMedicalReport = isMedicalReport;
    }

    public Date getSsoDate() {
        return ssoDate;
    }

    public void setSsoDate(Date ssoDate) {
        this.ssoDate = ssoDate;
    }

    public Date getMedicalReportDate() {
        return medicalReportDate;
    }

    public void setMedicalReportDate(Date medicalReportDate) {
        this.medicalReportDate = medicalReportDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}