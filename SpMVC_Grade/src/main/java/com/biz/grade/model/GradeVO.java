package com.biz.grade.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class GradeVO {
	
	private long seq;//	NUMBER
	private String g_id;//	CHAR(4)
	private String g_name;//	nVARCHAR2(20)
	private int g_kor;//	NUMBER
	private int g_eng;//	NUMBER
	private int g_math;//	NUMBER
	private int g_sum;//	NUMBER
	private float g_avg;//	NUMBER


}
