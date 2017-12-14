package com.btc.app.dao;

import com.btc.app.bean.VericodesModel;

public abstract interface VericodesMapper extends BaseMapper<VericodesModel>
{
  public abstract VericodesModel selectByEntity(VericodesModel paramVericodesModel);

  public abstract VericodesModel selectByEntity2(VericodesModel paramVericodesModel);

  public abstract int updateByEntity(VericodesModel paramVericodesModel);
}