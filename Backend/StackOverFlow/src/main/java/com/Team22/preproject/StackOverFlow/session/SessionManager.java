package com.Team22.preproject.StackOverFlow.session;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


// 세션 관리
@Component
public class SessionManager {

//    {
//    "sessionId": 12343525
//    "무언가":2222
//    }/

    public static final String SESSION_ID = "sessionId";
    private Map<String, Object> sessionStore = new ConcurrentHashMap<>();

    /**
     * 세션 생성
     */

    public void createSession(Object value, HttpServletResponse response) {

        // 세션 id 생성 및, 값을 세션 ID와 매핑시켜 저장
        String sessionId = UUID.randomUUID().toString(); // 랜덤한 ID 생성
        sessionStore.put(sessionId, value); // 12345
        // 쿠키 생성
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
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(SESSION_ID)){
                value = cookie.getValue(); // 12345
            }
        }

        if(value == null) return null;

        return sessionStore.get(value);
    }

    public void expire(HttpServletRequest request) {
    }



}
