package com.mytijian.wormhole.dao.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.mytijian.wormhole.dao.constant.YesOrNo;

import java.util.Date;

@TableName("tb_wormhole_user_oper_log")
public class UserOperLog {
    private Integer id;

    @TableField(value = "user_id")
    private Integer userId;

    @TableField(value = "access_id")
    private Integer accessId;

    @TableField(value = "id_card")
    private String idCard;

    private String name;

    @TableField(value = "ip_addr")
    private String ipAddr;

    private String type;

    @TableField(value = "oper_key")
    private Long operKey;

    private YesOrNo success;

    @TableField(value = "exception_info")
    private String exceptionInfo;

    @TableField(value = "oper_date")
    private Date operDate;

    @TableField(value = "request_time")
    private Long requestTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAccessId() {
        return accessId;
    }

    public void setAccessId(Integer accessId) {
        this.accessId = accessId;
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

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }


    public Long getOperKey() {
        return operKey;
    }

    public void setOperKey(Long operKey) {
        this.operKey = operKey;
    }

    public YesOrNo getSuccess() {
        return success;
    }

    public void setSuccess(YesOrNo success) {
        this.success = success;
    }

    public String getExceptionInfo() {
        return exceptionInfo;
    }

    public void setExceptionInfo(String exceptionInfo) {
        this.exceptionInfo = exceptionInfo == null ? null : exceptionInfo.trim();
    }

    public Date getOperDate() {
        return operDate;
    }

    public void setOperDate(Date operDate) {
        this.operDate = operDate;
    }

    public Long getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Long requestTime) {
        this.requestTime = requestTime;
    }
}