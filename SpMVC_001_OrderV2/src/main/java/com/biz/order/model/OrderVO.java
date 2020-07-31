package com.biz.order.model;
//com.biz.order.model 복사해서 mybatis-config.xml에 type에 붙여준다.


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
	
	// sql devoloper들어가서 user2 order table에서 가져와서 ctrl+shift+y눌러서 소문자 만들기
	private long o_seq;		//number
	private String o_num;		//char(6 byte)
	private String o_date;		//char(10 byte)
	private String o_cnum;		//char(5 byte)
	private String o_pcode;	//char(6 byte)
	private String o_pname;	//nvarchar2(125 char)
	private int o_price;	//number
	private int o_qty;		//number
	private int o_total;	//number

}
