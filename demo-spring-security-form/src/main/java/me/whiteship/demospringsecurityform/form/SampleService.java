package me.whiteship.demospringsecurityform.form;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;
@Service
public class SampleService {

    public void dashboard(){
        // 현재 로그인한 사용자 정보 확인을 위한 접근 => Authentication으로의 접근
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // 접근 주체에 대한 정보 획득
        Object principal = authentication.getPrincipal();
        // 사용자가 가지고 있는 권한들
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 인증 정보 (인증된 사용자의 경우 값이 들어있지 않다.)
        Object credentials = authentication.getCredentials();
        // 인증된 사용자 여부 (true/false)
        boolean authenticated = authentication.isAuthenticated();
    }

}
