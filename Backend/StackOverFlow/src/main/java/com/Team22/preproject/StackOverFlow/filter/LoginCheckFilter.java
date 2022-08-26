package com.Team22.preproject.StackOverFlow.filter;

import com.Team22.preproject.StackOverFlow.question.entity.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static String[] whitelist = {"/", "/css/*", "/members/login", "/members/signup" };

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI(); // 클라이언트의 요청 URI를 받아옵니다.

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try {
            log.info("인증 체크 필터 시작 {}", requestURI);


            if(isInWhitelist(requestURI)) { // whitelist에 있으면 넘어간다.

                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false); // 자동 생성을 false

                if (session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                    log.info("미인증 사용자 요청 {}", requestURI);

                    httpResponse.sendRedirect("/members/login?redirectURL=" + requestURI);
                    return;
                }
            }

            chain.doFilter(request, response);
        }catch (Exception e){
            throw e;
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }

    }

    /**
     * whitelist에 해당하는 URI 패턴은 로그인 인증하지 않도록 한다.
     * @param requestURI
     * @return boolean
     */

    private boolean isInWhitelist(String requestURI){
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI);

    }
}
