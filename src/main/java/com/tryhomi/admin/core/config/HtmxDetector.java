package com.tryhomi.admin.core.config;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;


public class HtmxDetector {

    private HtmxDetector() {}

    public static boolean isHtmx(HttpServletRequest httpServletRequest) {
        return StringUtils.equals(httpServletRequest.getHeader("HX-Request"), "true");
    }
}
