package objects;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Vector;

public class CopyOfDialog {
	private Vector<String> v_name = new Vector<String>();
	private Vector<String> v_text= new Vector<String>();
	private Vector<String> v_sprite= new Vector<String>();
	
	public CopyOfDialog(String arg_id, String arg_title, 
			String arg_name, String arg_text, String arg_sprite){
		id = arg_id;
		title = arg_title;
		putText(arg_name, arg_text, arg_sprite);
	}
	
	private String msg0name = "";
	private String msg1name = "";
	private boolean showonmsg0 = true;
	
	public void output(BufferedWriter out) throws IOException{
		boolean showmsg0once = false;
		String sex = "";
		
		msg0name = "";
		msg1name = "";
		
//		out.write("[cm]\r\n");
//		out.write("[current layer=message0 page=fore]\r\n");
		showonmsg0 = true;
		msg0name = v_name.get(0);
		
		out.write("[layopt layer=message0 page=fore visible=false]\r\n");
		
//		System.out.println(id);
//		System.out.println(title);
		for (int i = 0; i < v_name.size(); i++){
//			System.out.println("[cm]");
			if (!v_name.get(i).equals("") && !v_name.get(i).equals("　")){
				if (!v_name.get(i).equals(msg0name) && !v_name.get(i).equals(msg1name)){
					if (showonmsg0){
						if (!showmsg0once){
							out.write("[layopt layer=message0 page=fore visible=true]\r\n");
							showmsg0once = true;
						}
						out.write("[current layer=message0 page=fore]\r\n");
						out.write("[er]\r\n");
						msg1name = v_name.get(i);
						showonmsg0 = false;
					}
					else{
						out.write("[current layer=message1 page=fore]\r\n");
						out.write("[er]\r\n");
						msg0name = v_name.get(i);
						showonmsg0 = true;
					}
				}
				else if (v_name.get(i).equals(msg0name)){
					out.write("[current layer=message1 page=fore]\r\n");
					out.write("[er]\r\n");
//					msg0name = v_name.get(i);
					showonmsg0 = true;
				}
				else if (v_name.get(i).equals(msg1name)){
					if (!showmsg0once){
						out.write("[layopt layer=message0 page=fore visible=true]\r\n");
						showmsg0once = true;
					}
					out.write("[current layer=message0 page=fore]\r\n");
					out.write("[er]\r\n");
//					msg0name = v_name.get(i);
					showonmsg0 = false;
				}
				if (v_sprite.get(i).equals("2015")){ //迪恩
					charaoutput("sf0001a", "sf0001af", out);
					sex = "M";
				}
				else if (v_sprite.get(i).equals("2018")){ //梅魯
					charaoutput("sf0002a", "sf0002aa", out);
					sex = "F";
				}
				else if (v_sprite.get(i).equals("2022")){ //千語
					charaoutput("sf0003a", "sf0003aa", out);
					sex = "F";
				}
				else if (v_sprite.get(i).equals("2025")){ //凱
					charaoutput("sf0004a", "sf0004af", out);
					sex = "M";
				}
				else if (v_sprite.get(i).equals("2026")){ //可可
					charaoutput("sf0005a", "sf0005c", out);
					sex = "F";
				}
				else if (v_sprite.get(i).equals("2049")){ //迪恩
					charaoutput("sf0006a", "sf0006ae", out);
					sex = "M";
				}
				else if (v_sprite.get(i).equals("2030")){ //厭夜
					charaoutput("sf0013a", "sf0013l", out);
					sex = "F";
				}
				else{
					sex = "";
					out.write("[clearimage");
					if (!showonmsg0)
						out.write(" chara0");
					else
						out.write(" chara1");
					out.write("]\r\n");
				}
//				System.out.print("【"+v_name.get(i)+"】");
				if ("M".equals(sex) || "F".equals(sex))
					out.write("[namecolor "+sex.toLowerCase()+"]");
				out.write("【"+v_name.get(i)+"】");
			}
			else{
				showmsg0once = false;
				out.write("[clearimage]\r\n");
				out.write("[cm]\r\n");
				out.write("[layopt layer=message0 page=fore visible=false]\r\n");
				out.write("[current layer=message1 page=fore]\r\n");
				if (showonmsg0)
					showonmsg0 = false;
				else
					showonmsg0 = true;
			}
//			System.out.println("[r]");
//			System.out.print(v_text.get(i));
//			System.out.println("[p]");
			out.write("[r]\r\n");
			if ("M".equals(sex) || "F".equals(sex))
				out.write("[namecolor]\r\n");
			out.write(v_text.get(i));
			out.write("[p]\r\n");
		}
	}

	private void charaoutput(String chara, String face, BufferedWriter out) throws IOException{
		out.write("[loadchara chara=\""+chara+"\" face=\""+face+"\"");
		if (!showonmsg0)
			out.write(" chara0");
		out.write("]\r\n");
	}
	
	public String getID() {
		return id;
	}
	public void putText(String arg_name, String arg_text, String arg_sprite){
		if (arg_name == null)
			arg_name = "";
		if (arg_text == null)
			arg_text = "";
		
		v_name.add(arg_name);
		v_text.add(arg_text);
		v_sprite.add(arg_sprite);
	}
	
	private String id="200101";
	private String title="新依莎貝菈-初";
	private String background="data/bg101/bg101";
	private String atlas="";
	private String sprite="";
//	private String name="盜賊Ａ";
//	private String text="成功了！！金塊拿到手了！！";
	private String music="data/mus08";
}
