package com.btc.app.dto;

import java.util.List;

public class PageParameter
{
  private List records;
  private PageIndex pageindex;
  private long pageCount;
  private int pageSize = 10;

  private int pageNow = 1;
  private long rowCount;
  private int startPage;
  private int pagecode = 5;

  public PageParameter() {
  }
  public int getFirstResult() {
    return (this.pageNow - 1) * this.pageSize;
  }

  public int getPagecode() {
    return this.pagecode;
  }

  public void setPagecode(int pagecode) {
    this.pagecode = pagecode;
  }

  public PageParameter(int pageSize, int pageNow) {
    this.pageSize = pageSize;
    this.pageNow = pageNow;
  }
  public PageParameter(int pageNow) {
    this.pageNow = pageNow;
    this.startPage = ((this.pageNow - 1) * this.pageSize);
  }

  public void setQueryResult(long rowCount, List records) {
    setRowCount(rowCount);
    setRecords(records);
  }

  public void setRowCount(long rowCount) {
    this.rowCount = rowCount;
    setPageCount(this.rowCount % this.pageSize == 0L ? this.rowCount / this.pageSize : this.rowCount / this.pageSize + 1L);
  }

  public List getRecords()
  {
    return this.records;
  }

  public void setRecords(List records) {
    this.records = records;
  }

  public PageIndex getPageindex()
  {
    return this.pageindex;
  }

  public void setPageindex(PageIndex pageindex) {
    this.pageindex = pageindex;
  }

  public void setPageCount(long pageCount)
  {
    this.pageCount = pageCount;
    this.pageindex = WebTool.getPageIndex(this.pagecode, this.pageNow, pageCount);
  }

  public int getPageNow() {
    return this.pageNow;
  }

  public void setPageNow(int pageNow) {
    this.pageNow = pageNow;
  }

  public long getPageCount() {
    return this.pageCount;
  }

  public int getPageSize() {
    return this.pageSize;
  }

  public void setPageSize(int pageSize) {
    this.pageSize = pageSize;
  }

  public long getRowCount() {
    return this.rowCount;
  }

  public int getStartPage() {
    return this.startPage;
  }

  public void setStartPage(int startPage) {
    this.startPage = startPage;
  }
}