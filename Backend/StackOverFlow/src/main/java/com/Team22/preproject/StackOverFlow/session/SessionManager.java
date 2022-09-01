package com.Team22.preproject.StackOverFlow.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Component
public class SessionManager {

    public static final String SESSION_ID = "sessionId";
    private Map<String ,Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성
     */

    public void createSession(Object value, HttpServletResponse response){

        //세션Id todtjd
        String sessionId = UUID.randomUUID().toString();
        sessionStore.put(sessionId, value);

        //쿠키 생성
        Cookie mySessionCookie = new Cookie(SESSION_ID, sessionId);
        response.addCookie(mySessionCookie);
    }

    /**
     * 세션 조회
     */
    public Object getSession(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Object value = null;

        if(cookies == null){
            return null;
        }
        for (Cookie cookie : cookies){
            if(cookie.getName().equals(SESSION_ID)){
                value = cookie.getValue();
            }
        }
        if (value == null) return null;
        return sessionStore.get(value);
    }

    public void expire(HttpServletRequest request){

    }
}
