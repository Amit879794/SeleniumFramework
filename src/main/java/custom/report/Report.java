package custom.report;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Report {
	
	static BufferedWriter bw;
	
	public static void reportBegin() throws IOException
	{
		bw = new BufferedWriter(new FileWriter(new File("Reports\\customizeReport\\customizeHtmlReprot.html")));
		bw.write("<html>\r\n" + 
				"<head>\r\n" + 
				"<title>Test HTML Report</title>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"<h3>Test results</h3>\r\n" + 
				"<table border=\"1\">\r\n" + 
				"<tr>\r\n" + 
				"<th width=\"30%\">Action</th>\r\n" + 
				"<th width=\"20%\">Status</th>\r\n" + 
				"<th width=\"30%\">Description</th>\r\n" + 
				"</tr>\r\n");
	}
	
	public static void reportMain(String action,String status,String path) throws IOException
	{
//		System.out.println("Reporter " + action + " " + status + " " + path);
		if(action != null && status != null && path != null)
		{
			bw.write("<tr>\r\n" + 
					"<td> " + action + " </td>\r\n" + 
					"<td> " + status + " </td>\r\n" + 
					"<td> " + path + " </td>\r\n" + 
					"</tr>");
		}
	}
	
	public static void reportEnd() throws IOException
	{
		bw.write("</table></body></html>");
		bw.close();
	}

}
