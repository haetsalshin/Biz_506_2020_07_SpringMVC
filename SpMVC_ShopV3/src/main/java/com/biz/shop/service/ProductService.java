


package com.biz.shop.service;

import java.util.List;

import com.biz.shop.model.ProductVO;
/*
 * 쌤) ProductService 인터페이스는 GenericService 인터페이스를 상속(Extents)함으로써
 * CRUD 기본 method를 별도로 선언하지 않아도 된다.
 * 필요한 추가 method가 있으면 별도로 선언을 해주고
 * 이 인터페이스를 implements 한 클래스는 인터페이스의 영향을 받아
 * method를 구현하게 된다.
 */
// 우리가 만든 genericServcice를 상속해주고, 여기에는 아무런 코드도 만들지 않을 것이다.
public interface ProductService extends GenericService<ProductVO, String> {
	
	/*
	 *  table이 여러가지가 되더라도 기본적으로 다섯가지 메서드에 사용되는 매개변수와
	 *  PK만 바꿔주면 될 것 같다(ex. deptService  interface를 만들면 기본 기능은 다 똑같기 때문이다)
	 *  
	 productVO가 포함된 List. 이러한 항목을 우리는 generic이라고 부른다. 자바 1.5이상에서는 의무로 사용해야 한다.
	 제네릭을 넣은 이유? 이 데이터를 명시적으로 사용하기위해. 다른 데이터가 입력 된 것을 검증하기 위하여
	 우리가 이 generic을 위하여 인터페이스를 다시 설계할 것이다.  
	 */
	
	
	// Generic에는 없지만 Product를 구현하는데 필요한 메서드가 있으면
	// 그 메서드를 별도로 선언해준다.
	public List<ProductVO> findByTitle(String title);

	// 1. Controller에서 getPCode() 메서드를 "사용하는" 코드를 먼저 작성 후
	// 2. 실제 구현되는 코드를 만들기 위해 interface에 method를
	// 자동 생성하기
	public String getPCode();
	
	
	
	
	
	
}
