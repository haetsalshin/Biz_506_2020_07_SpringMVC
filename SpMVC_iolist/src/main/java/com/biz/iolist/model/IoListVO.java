package com.biz.iolist.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IoListVO {
	
	private long seq;
	private String io_date;
	private String io_time;
	private String io_pname;
	private String io_input;
	private int io_price;
	private int io_quan;
	private int io_total;


}
