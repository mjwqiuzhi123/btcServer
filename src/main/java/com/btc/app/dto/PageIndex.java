package com.btc.app.dto;

public class PageIndex
{
  private long startindex;
  private long endindex;

  public PageIndex(long startindex, long endindex)
  {
    this.startindex = startindex;
    this.endindex = endindex;
  }
  public long getStartindex() {
    return this.startindex;
  }
  public void setStartindex(long startindex) {
    this.startindex = startindex;
  }
  public long getEndindex() {
    return this.endindex;
  }
  public void setEndindex(long endindex) {
    this.endindex = endindex;
  }
}