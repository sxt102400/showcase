package com.rats.showcase.utils;

import org.activiti.engine.impl.Page;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

public class PageHelper {

    private static final String PAGE_NO = "page";
    private static final String PAGE_SIZE = "size";

    private static final int DEFAULT_PAGE_NO = 1;
    private static final int DEFAULT_PAGE_SIZE = 10;

    private static int start;
    private static int limit;
    private static int pageNo;
    private static int pageSize;

    public static Page getPage(HttpServletRequest request) {
        String pageNoStr = request.getParameter(PAGE_NO);
        String pageSizeStr = request.getParameter(PAGE_SIZE);
        if (StringUtils.isNotBlank(pageNoStr)) {
            pageNo = Integer.parseInt(pageNoStr);
        } else {
            pageNo = DEFAULT_PAGE_NO;
        }
        if (StringUtils.isNotBlank(pageSizeStr)) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        start = (pageNo - 1) * pageSize + 1;
        limit = pageSize;
        return new Page(start, limit);
    }
}
