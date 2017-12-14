package com.btc.app.bean;

import java.io.Serializable;
import java.util.Date;

public class VericodesModel
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private Integer id;
  private String identifier;
  private String phone;
  private String code;
  private Integer type;
  private Integer verified;
  private Integer used;
  private Integer errorcount;
  private Integer times;
  private Date buildat;
  private String clientid;

  public Integer getId()
  {
    return this.id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getIdentifier() {
    return this.identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = (identifier == null ? null : identifier.trim());
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = (phone == null ? null : phone.trim());
  }

  public String getCode() {
    return this.code;
  }

  public void setCode(String code) {
    this.code = (code == null ? null : code.trim().toLowerCase());
  }

  public Integer getType() {
    return this.type;
  }

  public void setType(Integer type) {
    this.type = type;
  }

  public Integer getVerified() {
    return this.verified;
  }

  public void setVerified(Integer verified) {
    this.verified = verified;
  }

  public Integer getUsed() {
    return this.used;
  }

  public void setUsed(Integer used) {
    this.used = used;
  }

  public Integer getErrorcount() {
    return this.errorcount;
  }

  public void setErrorcount(Integer errorcount) {
    this.errorcount = errorcount;
  }

  public Integer getTimes() {
    return this.times;
  }

  public void setTimes(Integer times) {
    this.times = times;
  }

  public Date getBuildat() {
    return this.buildat;
  }

  public void setBuildat(Date buildat) {
    this.buildat = buildat;
  }

  public String getClientid() {
    return this.clientid;
  }

  public VericodesModel(String identifier, Integer type, Date buildat)
  {
    this.identifier = identifier;
    this.type = type;
    this.buildat = buildat;
  }

  public VericodesModel(String identifier, Integer type, String phone, Date buildat)
  {
    this.identifier = identifier;
    this.type = type;
    this.phone = phone;
    this.buildat = buildat;
  }

  public VericodesModel(Integer type, String phone, Date buildat)
  {
    this.type = type;
    this.phone = phone;
    this.buildat = buildat;
  }

  public void setClientid(String clientid) {
    this.clientid = (clientid == null ? null : clientid.trim());
  }

  public VericodesModel(Integer id, String identifier, String phone, String code, Integer type, Integer verified, Integer used, Integer errorcount, Integer times, Date buildat, String clientid)
  {
    this.id = id;
    this.identifier = identifier;
    this.phone = phone;
    this.code = code;
    this.type = type;
    this.verified = verified;
    this.used = used;
    this.errorcount = errorcount;
    this.times = times;
    this.buildat = buildat;
    this.clientid = clientid;
  }

  public VericodesModel()
  {
  }

  public String toString()
  {
    return "VericodesModel [id=" + this.id + ", identifier=" + this.identifier + ", phone =" + this.phone + ", code=" + this.code + ", type=" + this.type + ", verified=" + this.verified + ", used=" + this.used + ", errorcount=" + this.errorcount + ", times=" + this.times + ", buildat=" + this.buildat + ", clientid=" + this.clientid + "]";
  }

  public VericodesModel(String code, Integer type)
  {
    this.code = code;
    this.type = type;
  }
}