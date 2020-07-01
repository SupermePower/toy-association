package cn.toy.www.excel.model;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author hsl
 * 2018年7月10日
 * version = 1.0.0
 */
@Getter@Setter
public class Title {

	private String titleName;
	
	private int width;
	
	private String field;

	public Title(){}
	
	public Title(String titleName, int width, String field){
		this.titleName = titleName;
		this.width = width;
		this.field = field;
	}
	

}
