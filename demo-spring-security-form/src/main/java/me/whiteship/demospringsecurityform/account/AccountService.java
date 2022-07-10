package me.whiteship.demospringsecurityform.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        //user 정보가 없는 경우
        if(account == null){
            throw new UsernameNotFoundException(username);
        }

        //user 정보가 있으면 UserDetails 타입으로 변경해줘야 한다. (Account -> UserDetails로 변경)
        //스프링 시큐리티에서는 형변환을 쉽게 할 수 있도록 User 클래스를 제공한다.
        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    public Account createNew(Account account){
        account.encodePassword(passwordEncoder);
        return this.accountRepository.save(account);
    }
}
