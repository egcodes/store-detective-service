package com.egcodes.storedetectiveservice.service;

import static com.egcodes.storedetectiveservice.constants.Constants.TIME_LIMIT_FOR_INFO;
import static com.egcodes.storedetectiveservice.constants.Constants.TIME_LIMIT_FOR_WARN;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@WebFilter("/*")
@Slf4j
public class StatsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        var start = Instant.now();
        try {
            chain.doFilter(req, resp);
        } finally {
            var finish = Instant.now();
            var time = Duration.between(start, finish).toMillis();
            var path = ((HttpServletRequest) req).getServletPath();

            if (time > TIME_LIMIT_FOR_WARN)
                log.warn("{}: {} ms ", ((HttpServletRequest) req).getRequestURI(), time);
            else if (time > TIME_LIMIT_FOR_INFO)
                log.info("{}: {} ms ", ((HttpServletRequest) req).getRequestURI(), time);
        }
    }

    @Override
    public void destroy() {
    }
}