package com.biz.book.model;

import java.util.Collection;

import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Alias("UserDetail")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsVO implements UserDetails {
	
	/*
	 * http 프로토콜을 통해서 VO를 주고받을 때
	 * JSON형태로 이동이 되는데
	 * 일렬로 나열된 문자열 형태로 네트워크를 통해서 이동한다
	 * 이 때 이러한 기능을 쉽게 구현하기 위해서 serialize라는
	 * 인터페이스를 implements 한다
	 * serialized 된 VO 클래스에는 serialVersionUID라는 값을 
	 * 설정해 주어야 경고가 나지 않는다.
	 * 
	 * id빼고는 하단의 변수들의 이름은 바꿔서 사용하면 안된다.
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	
	private String username;
	private String password;
	
	private boolean Enabled;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	
	// List<GrandtedAuthority>
	private Collection< ? extends GrantedAuthority> authorities;
	
	// 프로젝트에서 필요한 별도의 필드변수 선언
	// 필요에 따라서 넣는 값이기 때문에 필수 선언 값은 아니다
	private String m_name;
	private String m_email;
	private String m_tel;
	private String m_address;

}
