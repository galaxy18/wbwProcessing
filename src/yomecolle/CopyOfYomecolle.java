package yomecolle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Vector;

import objects.Dialog;
import objects.Hero;
import objects.Point;
import mainpack.Params;
import mainpack.ReadXML;

public class CopyOfYomecolle implements Params{
	
	public static void main(String[] args) throws Exception {
		ReadXML objects = new ReadXML();
		objects.readheroXML();
		Vector<Hero> v_hero = objects.getv_hero();
		
		output();
	}
	
	private static void output() throws IOException{		
		ReadXML objects = new ReadXML();
		objects.readPointXML();
		objects.readdialogXML();

		Vector<Point> v_point = objects.getv_point();
		Hashtable<String, Dialog> ht_dialog = objects.getht_dialog();

		String chapter = "";
		int chaptercount = 0;
		Hashtable<String, Vector<Point>> ht_chapter = new Hashtable<String, Vector<Point>>();
		Vector<String> chapterlist = new Vector<String>();

		String pathname1 = "C:\\Documents and Settings\\user\\Desktop\\wbw\\chapters.json";
		File writename1 = new File(pathname1);
		writename1.createNewFile();
		BufferedWriter out1 = new BufferedWriter(new FileWriter(writename1));
		out1.write("[{\n");
		out1.write("\t\"chapters\": [\n");
		
		for (int i = 0; i <
			//TODO
//			20;
			v_point.size();
			i++){
			String new_chaptername = v_point.get(i).getchapter();
			if (!chapter.equals(new_chaptername)){
				
				chapter = new_chaptername;
				chapterlist.add(chapter);

				chaptercount ++;
			}
			if (ht_chapter.containsKey("chapter"+String.valueOf(chaptercount - 1))){
				ht_chapter.get("chapter"+String.valueOf(chaptercount - 1)).add(v_point.get(i));
			}
			else{
				Vector<Point> new_chapter = new Vector<Point>();
				new_chapter.add(v_point.get(i));
				ht_chapter.put("chapter"+String.valueOf(chaptercount - 1), new_chapter);
				if (i > 0){
					out1.write("\t,\n");
				}
				out1.write("\t{\t\"name\": \""+ v_point.get(i).getchapter() +"\",\n");
				out1.write("\t\t\"id\": \""+ v_point.get(i).getID() +"\"}\n");
			}
		}

		out1.write("]");
		out1.write("}]");
		out1.flush();
		out1.close();

		for (int i = 0; i < 
			//TODO
			//0;
			//1;
			chapterlist.size();
			i++){
			//			System.out.println("*chapter"+String.valueOf(i)+"|"+chapterlist.get(i));
			Vector<Point> chapters = ht_chapter.get("chapter"+String.valueOf(i));
			for (int j = 0; j < 
			//TODO
			//2;
			chapters.size(); 
			j++){
				if (chapters.get(j).getID().equals(""))
					return;
				
				String pathname = "C:\\Documents and Settings\\user\\Desktop\\wbw\\"+chapters.get(j).getID()+".json";
				File writename = new File(pathname);
				writename.createNewFile();
				BufferedWriter out = new BufferedWriter(new FileWriter(writename));
				out.write("[{\n");
//				out.write("\"manifest\": [{\n");
//				out.write("\t\"src\": \"mon2014.Out.png\",\n");
//				out.write("\t\"id\": \"sprite2014\"}\n");
//				out.write("],\n");
				if (j < chapters.size()-1){
					out.write("\"returnURL\": \""+chapters.get(j+1).getID()+"\",\n");
				}
				out.write("\"storyScript\": [{\n");
				boolean before = false;
				boolean within = false;
				boolean after = false;
				if (chapters.get(j).getbefore_dialog() != null
						&& !chapters.get(j).getbefore_dialog().equals("")){
					ht_dialog.get(chapters.get(j).getbefore_dialog()).output(out);
					before = true;
				}
				if (chapters.get(j).getwithin_dialog() != null
						&& !chapters.get(j).getwithin_dialog().equals("")){
					if (before){
						out.write("\t,{\"clean\":\"true\"},{\n");
					}
					ht_dialog.get(chapters.get(j).getwithin_dialog()).output(out);
					within = true;
				}
				if (chapters.get(j).getafter_dialog() != null
						&& !chapters.get(j).getafter_dialog().equals("")){
					if (ht_dialog.get(chapters.get(j).getafter_dialog()) == null){
						System.out.println(chapters.get(j).getafter_dialog());
					}
					else{
						if (before || within){
							out.write("\t,{\"clean\":\"true\"},{\n");
						}
						ht_dialog.get(chapters.get(j).getafter_dialog()).output(out);
					}
					after = true;
				}
				
				if (!before && !within && !after){
					out.write("}]\n");
				}
				else{
					out.write("]\n");
				}
				out.write("}]");
				out.flush();
				out.close();
			}
		}
	}
}