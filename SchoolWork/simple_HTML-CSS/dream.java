/*
	Taylor Siebenberg
	cs337
	dream.java
*/

import java.util.Scanner;
import java.io.*;

public class dream {
	public static void main(String [] args) throws IOException {
		File inFile = new File("./dream.txt");
		Scanner sc = new Scanner(inFile);

		File outFile = new File("./index.html");

		if(!outFile.exists()) {
			outFile.createNewFile();
		}

		FileWriter fWrite = new FileWriter(outFile.getAbsoluteFile());
		BufferedWriter bWrite = new BufferedWriter(fWrite);

		bWrite.write("<!doctype html>" + "\n");
		bWrite.write("<html>" + "\n");
		bWrite.write("<head>\n<title>A Midsummer Night's Dream</title>\n</head>\n");
		bWrite.write("<body>\n");
		bWrite.write("<h2>" + sc.nextLine() + "</h2>"  + "\n");
		bWrite.write(sc.nextLine() + "\n");		
		bWrite.write("<h3>" + sc.nextLine() + "</h3>" + "\n");		
		bWrite.write(sc.nextLine() + "\n");				

		int count = 0;
		boolean afterName = false;

		while(sc.hasNextLine()) {
			String str = sc.nextLine();
			boolean link = false;
	
			if( countWords(str) == 1 ) {
				for(int i = 0; i < str.length(); i++) {
					if(Character.isUpperCase(str.charAt(i))) {
						link = true;	
					}
					else{
						link = false;
						break;
					}
				}
				if( link ) {
					count++;
					if ( count == 1) {
						bWrite.write("<a href=\"characters.html#theseus\">" + str + "</a>\n");
						afterName = true;
					}
					else {
						if(str.equals("THESEUS")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#theseus\">" + str + "</a>\n");
						}
						if(str.equals("HIPPOLYTA")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#hippolyta\">" + str + "</a>\n");
						}
						if(str.equals("PHILOSTRATE")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#philostrate\">" + str + "</a>\n");
						}
						if(str.equals("HERMIA")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#hermia\">" + str + "</a>\n");
						}
						if(str.equals("HELENA")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#helena\">" + str + "</a>\n");
						}
						if(str.equals("LYSANDER")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#lysander\">" + str + "</a>\n");
						}	
						if(str.equals("DEMETRIUS")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#demetrius\">" + str + "</a>\n");
						}	
						if(str.equals("PETER QUINCE")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#peter\">" + str + "</a>\n");
						}	
						if(str.equals("BOTTOM")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#nick\">" + str + "</a>\n");
						}	
						if(str.equals("FRANCIS FLUTE")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#francis\">" + str + "</a>\n");
						}	
						if(str.equals("ROBIN STARVELING")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#robin\">" + str + "</a>\n");
						}	
						if(str.equals("TOM SNOUT")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#tom\">" + str + "</a>\n");
						}	
						if(str.equals("SNUG")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#snug\">" + str + "</a>\n");
						}	
						if(str.equals("OBERON")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#oberon\">" + str + "</a>\n");
						}	
						if(str.equals("TITANIA")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#titania\">" + str + "</a>\n");
						}	
						if(str.equals("PUCK")) {
							bWrite.write("</blockquote>\n<a href=\"characters.html#puck\">" + str + "</a>\n");
						}
						afterName = true;
					}
					
				}
				else {
					if(str.equals("Thisbe")) {
						bWrite.write("</blockquote>\n<a>" + str + "</a>\n");
						afterName = true;
					}
					else if(str.equals("Prologue")) {
						bWrite.write("</blockquote>\n<a>" + str + "</a>\n");
						afterName = true;
					}
					else if(str.equals("Lion")) {
						bWrite.write("</blockquote>\n<a>" + str + "</a>\n");
						afterName = true;
					}
					else if(str.equals("Pyramus")) {
						bWrite.write("</blockquote>\n<a>" + str + "</a>\n");
						afterName = true;
					}
					else {
						bWrite.write(str + "<br>");
					}
				}
			}
			else if(afterName) {
				bWrite.write("<blockquote>" + str + "<br>");
				afterName = false;
			}
			else {
				bWrite.write(str + "<br>");
			}
		}

		bWrite.write("</blockquote>\n");
		bWrite.write("</body>\n</html>");
		bWrite.close();

	}

	public static int countWords(String str) {
		int count = 0;
		boolean pw = false;

		for(int i = 0; i < str.length(); i++) {
			char ch = str.charAt(i);
			if(Character.isLetter(ch)) {
				pw = true;
				if(i == (str.length() - 1)){
					count++;
				}
			}
			else if(!Character.isLetter(ch) && pw) {
				count++;
				pw = false;
			}
		}
		return count;
	}
}
