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

public class Yomecolle implements Params{
	
	public static void main(String[] args) throws Exception {
		ReadXML objects = new ReadXML();
		objects.readheroXML();
		Vector<Hero> v_hero = objects.getv_hero();
		
		output();
		
		/*[cm]
		 [link target=*select1]選択肢１[endlink][r]
		 [link target=*select2]選択肢２[endlink][r]
		 [link target=*select3]選択肢３[endlink][r]
		 [s]
		 */
//		System.out.println("*hero");
//		System.out.println("[current layer=message0 page=fore]");
//		System.out.println("[er]");
//		for (int i = 0; i < v_hero.size(); i++){
//			System.out.println("[link target=*hero"+String.valueOf(i)+"]"+
//					v_hero.get(i).getname()+"[endlink][r]");
//		}
//		System.out.println("[link storage=\"first.ks\" target=*start]back[endlink]");
//		System.out.println("[s]");
//		for (int i = 0; i < v_hero.size(); i++){
//			System.out.println("*hero"+String.valueOf(i));
//			System.out.println("[current layer=message1 page=fore]");
//			System.out.println("[er]");
//			System.out.println("【"+v_hero.get(i).getname()+"】[r]");
//			System.out.println(v_hero.get(i).getcomment());
//			System.out.println("[jump target=*hero]");
//		}
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

		/*[cm]
		 [link target=*select1]選択肢１[endlink][r]
		 [link target=*select2]選択肢２[endlink][r]
		 [link target=*select3]選択肢３[endlink][r]
		 [s]
		 */
		
		String pathname = "C:\\Documents and Settings\\user\\Desktop\\kr2_232r2\\kirikiri2\\data\\scenario\\1.ks";
		File writename = new File(pathname);
		writename.createNewFile();
		BufferedWriter out = new BufferedWriter(new FileWriter(writename));

//		System.out.println("[cm]");
		out.write("*start\r\n");
		out.write("[clearimage]\r\n");
		out.write("[cm]\r\n");
		for (int i = 0; i <
			//TODO
//			20;
			v_point.size();
			i++){
			String new_chaptername = v_point.get(i).getchapter();
			if (!chapter.equals(new_chaptername)){
//				System.out.print("[link target=*chapter"+String.valueOf(chaptercount)+"]");
//				System.out.print(v_point.get(i).getchapter());
//				System.out.println("[endlink][r]");
				out.write("[link target=*chapter"+String.valueOf(chaptercount)+"]");
				out.write(v_point.get(i).getchapter());
				out.write("[endlink][r]\r\n");
				
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
			}
		}
//		System.out.println("[s]");
//		System.out.println("");
		out.write("[clearimage]\r\n");
		out.write("[link storage=\"first.ks\" target=*start]back[endlink]\r\n");
		out.write("[s]\r\n");
		out.write("\r\n");

		for (int i = 0; i < chapterlist.size(); i++){
			out.write("*chapter"+String.valueOf(i)+"|"+chapterlist.get(i)+"\r\n");
			out.write("[clearimage]\r\n");
			out.write("[cm]\r\n");
//			System.out.println("*chapter"+String.valueOf(i)+"|"+chapterlist.get(i));
//			System.out.println("[cm]");
			Vector<Point> chapters = ht_chapter.get("chapter"+String.valueOf(i));
			for (int j = 0; j < chapters.size(); j++){
//				System.out.print("[link target=*");
//				System.out.print(chapters.get(j).getID());
//				System.out.print("]");
//				System.out.print(chapters.get(j).getpoint());
//				System.out.println("[endlink][r]");
				
				out.write("[link target=*");
				out.write(chapters.get(j).getID());
				out.write("]");
				out.write(chapters.get(j).getpoint());
				out.write("[endlink][r]\r\n");
			}
//			System.out.println("[link target=*start]back[endlink][r]");
//			System.out.println("[s]");
//			System.out.println("");
			out.write("[link target=*start]back[endlink]\r\n");
			out.write("[s]\r\n");
			out.write("\r\n");
		}

		for (int i = 0; i < chapterlist.size(); i++){
			//			System.out.println("*chapter"+String.valueOf(i)+"|"+chapterlist.get(i));
			Vector<Point> chapters = ht_chapter.get("chapter"+String.valueOf(i));
			for (int j = 0; j < chapters.size(); j++){
				chapters.get(j).output(out);
				out.write("[clearimage]\r\n");
				if (chapters.get(j).getbefore_dialog() != null
						&& !chapters.get(j).getbefore_dialog().equals(""))
					ht_dialog.get(chapters.get(j).getbefore_dialog()).output(out);
				if (chapters.get(j).getwithin_dialog() != null
						&& !chapters.get(j).getwithin_dialog().equals(""))
					ht_dialog.get(chapters.get(j).getwithin_dialog()).output(out);
				if (chapters.get(j).getafter_dialog() != null
						&& !chapters.get(j).getafter_dialog().equals("")){
					if (ht_dialog.get(chapters.get(j).getafter_dialog()) == null){
						System.out.println(chapters.get(j).getafter_dialog());
					}
					else{
						ht_dialog.get(chapters.get(j).getafter_dialog()).output(out);
					}
				}

//				System.out.println("[cm]");
//				System.out.println("[link target=*chapter"+String.valueOf(i)+"]back[endlink][r]");
//				System.out.println("[s]");
//				System.out.println("");
				out.write("[cm]\r\n");
				out.write("[layopt layer=message0 page=fore visible=false]\r\n");
				out.write("[clearimage]\r\n");
				out.write("[current layer=message1 page=fore]\r\n");
				out.write("[link target=*chapter"+String.valueOf(i)+"]back[endlink]\r\n");
				out.write("[s]\r\n");
				out.write("\r\n");
				
				out.flush();
			}
		}
		out.flush();
		out.close();
	}
}