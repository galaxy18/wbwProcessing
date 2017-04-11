package objects;

import java.io.BufferedWriter;
import java.io.IOException;

public class Hero {
	
	public Hero(String arg_name, String arg_comment){
		name = arg_name;
		comment = arg_comment;
	}
	
	private String name="";
	private String comment="";
	
	public String getname() {
		return name;
	}
	public String getcomment() {
		return comment;
	}
}
