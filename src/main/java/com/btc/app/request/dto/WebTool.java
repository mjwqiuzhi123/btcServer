package com.btc.app.request.dto;

public class WebTool
{
  public static PageIndex getPageIndex(long pagecode, int pageNow, long pageCount)
  {
    long startpage = pageNow - (pagecode % 2L == 0L ? pagecode / 2L - 1L : pagecode / 2L);
    long endpage = pageNow + pagecode / 2L;
    if (startpage < 1L) {
      startpage = 1L;
      if (pageCount >= pagecode) endpage = pagecode; else
        endpage = pageCount;
    }
    if (endpage > pageCount) {
      endpage = pageCount;
      if (endpage - pagecode > 0L) startpage = endpage - pagecode + 1L; else
        startpage = 1L;
    }
    return new PageIndex(startpage, endpage);
  }
}