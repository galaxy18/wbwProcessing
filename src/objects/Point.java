package objects;

import java.io.BufferedWriter;
import java.io.IOException;

public class Point {
	
	public Point(String arg_id, String arg_map, 
			String arg_chapter, String arg_point, 
			String arg_before_dialog, String arg_within_dialog, String arg_after_dialog){
		id = arg_id;
		map = arg_map;
		chapter = arg_chapter;
		point = arg_point;
		before_dialog = arg_before_dialog;
		within_dialog = arg_within_dialog;
		after_dialog = arg_after_dialog;
	}
	
	public void output(BufferedWriter out) throws IOException{
//		System.out.println(id);
//		System.out.println(map);
//		System.out.println(chapter);
//		System.out.println(point);
		
//		System.out.println("*"+id+"|"+point);
		out.write("*"+id+"|"+point+"\r\n");
	}
	
	public String getbefore_dialog(){
		return before_dialog;
	}
	public String getwithin_dialog(){
		return within_dialog;
	}
	public String getafter_dialog(){
		return after_dialog;
	}
	public String getchapter(){
		return chapter;
	}
	public String getpoint(){
		return point;
	}
	
	private String id="101";
	private String map="³·­ì";
	private String chapter="§Ç³¹¡G¨g­·¼É«Bªº¬Û¹J";
	private String point="ªì¹J¡E1";
	private String map_x="932";
	private String map_y="750";
	private String map_atlas="Atlas/atlas5";
	private String map_sprite="1";
	private String background="Data/map311101/map311101";
	private String money="1000";
	private String exp="20";
	private String hero_exp="36";
	private String ap="0";
	private String group="101";
	private String before_dialog;
	private String within_dialog;
	private String after_dialog;
	private String round="2";
	private String monster="101";
	private String rune_prob1="4";
	private String rune_prob2="3";
	private String rune_prob3="1";
	private String rune_prob4="1";
	private String rune_prob5="2";
	private String ball_prob1="10";
	private String ball_prob2="10";
	private String ball_prob3="10";
	private String ball_prob4="10";
	private String ball_prob5="10";
	private String ball_prob6="10";
	
	public String getID() {
		return id;
	}
}
