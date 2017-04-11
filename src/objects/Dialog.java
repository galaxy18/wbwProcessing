package objects;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Vector;

public class Dialog {
	private Vector<String> v_name = new Vector<String>();
	private Vector<String> v_text= new Vector<String>();
	private Vector<String> v_sprite= new Vector<String>();
	
	public Dialog(String arg_id, String arg_title, 
			String arg_name, String arg_text, String arg_sprite){
		id = arg_id;
		title = arg_title;
		putText(arg_name, arg_text, arg_sprite);
	}
	
	private String msg0name = "";
	private String msg1name = "";
	private boolean showonmsg0 = true;
	
	public void output(BufferedWriter out) throws IOException{
		String sex = "";
		boolean start = true;
		
		msg0name = "";
		msg1name = "";
		
		msg0name = v_name.get(0);
		
		for (int i = 0; i < v_name.size(); i++){
			out.write("\t\"serif\": {\n");
			out.write("\t\t\"name\": \"" + v_name.get(i) + "\",\n");
			if (!v_name.get(i).equals("") && !v_name.get(i).trim().equals("　")){
				if (start){
					msg0name = v_name.get(i);
					showonmsg0 = true;
					out.write("\t\t\"page\": \"" + 1 + "\",\n");
					start = false;
				}
				else if (!v_name.get(i).equals(msg0name) && !v_name.get(i).equals(msg1name)){
					if (showonmsg0){
						msg1name = v_name.get(i);
						out.write("\t\t\"page\": \"" + 2 + "\",\n");
						showonmsg0 = false;
					}
					else{
						msg0name = v_name.get(i);
						out.write("\t\t\"page\": \"" + 1 + "\",\n");
						showonmsg0 = true;
					}
				}
				else if (v_name.get(i).equals(msg0name)){
					out.write("\t\t\"page\": \"" + 1 + "\",\n");
					showonmsg0 = true;
				}
				else if (v_name.get(i).equals(msg1name)){
					out.write("\t\t\"page\": \"" + 2 + "\",\n");
					showonmsg0 = false;
				}
				
				out.write("\t\t\"serif_text\": \"" + v_text.get(i).replaceAll("\n", "<br />") + "\"\n");
				out.write("\t},\n");
				out.write("\t\"action\": [{\n");
				out.write("\t\t\"target\": \"sprite" + v_sprite.get(i) + "\",\n");
				out.write("\t\t\"type\": \"position\"\n");
				out.write("\t}]\n");
				if (i < v_name.size() - 1){
					out.write("}, {\n");
				}
				else{
					out.write("}\n");
				}
			}
			else{
				out.write("\t\t\"serif_text\": \"" + v_text.get(i).replaceAll("\n", "<br />") + "\",\n");
				out.write("\t\t\"page\": \"" + 1 + "\"\n");
				out.write("\t},\n");
				out.write("\t\"action\": [{\n");
				out.write("\t\t\"target\": \"sprite" + v_sprite.get(i) + "\",\n");
				out.write("\t\t\"type\": \"position\"\n");
				out.write("\t}]\n");
				if (i < v_name.size() - 1){
					out.write("}, {\n");
//					out.write("\t\"clean\":\"true\"\n}, {\n");
				}
				else{
					out.write("}\n");
//					out.write("\t\"clean\":\"true\"\n}\n");
				}
				showonmsg0 = false;
			}
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
	private String title="�s�̲﨩�i-��";
	private String background="data/bg101/bg101";
	private String atlas="";
	private String sprite="";
//	private String name="�s���";
//	private String text="���\�F�I�I���������F�I�I";
	private String music="data/mus08";
}
