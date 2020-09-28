package com.biz.book.auth;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.biz.book.model.AuthorityVO;
import com.biz.book.model.UserDetailsVO;


/*
 * member table에서 user 정보를 select 하여 권한을 관리하는 클래스
 * spring security에서 사용자 인증, 권한부여 절차
 * 사용자가 login form 에 username, password를 입력한 후 로그인을 시도하면 
 *  1. AuthenticationProvider 인터페이스를 구현한 authproviderImpl 객체를 찾는다
 *  
 *  2. authenticate() method 를 호출한다.
 *     이때 spring security는 authentication 객체에 로그인한 username, password를 실어서 전달한다. 
 *  
 *  3. authentication 객체에서 principal(user), cridential(pw) 값을 getter하여 username 과 password를 추출한다
 *  
 *  4. userDetailsService 인터페이스를 구현한 userDetailsServiceImpl 객체를 호출하여 
 *     userDetails 정보를 요청한다
 *     userDetailsServiceImpl은 username에 해당하는 사용자 정보를 user(member) 테이블에서 select하여 리턴한다
 *  
 *  5. 이때 해당하는 username 정보가 없으면 usernotfoundexception을 발생시켜 spring security에게
 *     사용자 정보가 없음을 통보한다. 
 *  6. userdetailserviceimpl로부터 return받은 userdetails에 담겨있는 password와 사용자가 로그인한 password를 비교  
 *     만약 password가 다르면 BadCredentialException을 강제로 발생시켜 spring security에게 통보한다 
 *     
 *  7. username과 password 가 확인되었다 이제 사용자 정보에 enable 값을 검사하여 사용가능한 사용자 정보인지를 알아본다. 
 *  
 */
public class AuthProviderImpl implements AuthenticationProvider{

   @Autowired
   @Qualifier("userDetailServiceV1")
   private UserDetailsService userService;
   
   @Autowired
   private PasswordEncoder passwordEncoder;
   
   
   /*
    * 사용자가 로그인 수행했을 때 username과 password를 주입받아서 정상적인 사용자 인가를 검사하는 method
    * 로그인 하면 이 클래스에게 아이디,비번 정보가 담기게 된다 어센틱이라는 클래스에 담겨 어센틱 메소드에 전달
    * principal 변수에 username이 담긴 것을 get하여 추출
    */
   @Override
   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      
      // 로그인한 사용자의 username 과 password를 authentication 객체로 부터 getter
      String username = (String)authentication.getPrincipal();
      String password = (String)authentication.getCredentials();
      
      // 로그인 인증하는 부분
      // 1. userdetailservice 에 username전달하고 db로부터 사용자 정보 select 한 결과를 userVO에 받기
      UserDetailsVO userVO = (UserDetailsVO) userService.loadUserByUsername(username);
      
      
      // 2, 비밀번호 검사
      // 비밀번호를 암호화 하지 않았을 경우 문자열 비교하기
      /*
      if(!password.equals(userVO.getPassword())) {
         // 비번이 일치하지 않으면?
         throw new BadCredentialsException("비밀번호 오류");
         
      }
      */
      
      // PasswordEncoder로 암호화된 비번 비교
      /*
       * 사용자가 입력한 password 평문 문자열을 내부에서 암호화 하여
       * DB에 저장되어 있던 암호화 된 비번(userVO.getPassword())
       * 를 비교하여 일치하는지 검사한다.
       */
      if(!passwordEncoder.matches(password, userVO.getPassword())) {
    	  throw new BadCredentialsException("비밀번호가 일치하지 않음😅");
    	  
      }
      
      
      // 3. 유효한 사용자 정보인가?
      if(!userVO.isEnabled()) {
         throw new BadCredentialsException(username + "사용자 정보 사용불가😅");
      }
      
      // 4. 임시 사용자의 권한 리스트를 생성
      
      // 가 . 임시로 사용자의 권한 리스트 생성위해 authorityVO 를 담은 list를 생성하고 
      //       권한을 지정하여 add()
      List<AuthorityVO> authList = new ArrayList<AuthorityVO>();
      authList.add(AuthorityVO.builder().m_role("ROLE_ADMIN").build());
      authList.add(AuthorityVO.builder().m_role("ROLE_USER").build());
      
      // 나. spring security의  hasrole() method에서 사용할 자료형으로 형변환
      List<GrantedAuthority> rollList = new ArrayList<GrantedAuthority>();
      for(AuthorityVO auth : authList) {
         rollList.add(new SimpleGrantedAuthority(auth.getM_role()));
      }
      
      // 로그인한 사용자에게 인증토큰을 부여 
      // 사용자의 detail정보와 roll 정보를 token에 같이 심어 놓는다.
      return new UsernamePasswordAuthenticationToken(userVO, null, rollList);
   }

   @Override
   public boolean supports(Class<?> authentication) {

	   // supports() return 값이 false이면 UsernamePasswordAuthenticationToken 을
	   // 사용하지 않겠다는 얘기이다,.
	   // 여기를 반드시  true해주어야 한다.
      return true;
   }

   
}