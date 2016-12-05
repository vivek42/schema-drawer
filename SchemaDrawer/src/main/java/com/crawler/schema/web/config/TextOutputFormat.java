package com.crawler.schema.web.config;

import java.util.ArrayList;
import java.util.List;

public enum TextOutputFormat {

	 text("Plain text format", false),
	 html("HyperText Markup Language (HTML) format", false),
	 csv("Comma-separated values (CSV) format",false),
	 tsv("Tab-separated values (TSV) format", false),
	 json("JavaScript Object Notation (JSON) format",false),;

	  public static TextOutputFormat valueOfFromString(final String format)
	  {
	    TextOutputFormat outputFormat;
	    try
	    {
	      outputFormat = TextOutputFormat.valueOf(format);
	    }
	    catch (final IllegalArgumentException | NullPointerException e)
	    {
	      outputFormat = text;
	    }
	    return outputFormat;
	  }

	  public static boolean isTextOutputFormat(final String format)
	  {
	    try
	    {
	      TextOutputFormat.valueOf(format);
	      return true;
	    }
	    catch (final IllegalArgumentException | NullPointerException e)
	    {
	      return false;
	    }
	  }

	  private final String description;
	  private final boolean isActive;

	  private TextOutputFormat(final String description, final boolean isActive)
	  {
	    this.description = description;
	    this.isActive = isActive;
	  }

	  public String getDescription()
	  {
	    return description;
	  }

	  public String getFormat()
	  {
	    return name();
	  }
	  
	  public static List<String> getActiveTextOutputFormatList() {
		  List<String> outputList = new ArrayList<>();
		  for (TextOutputFormat format : TextOutputFormat.values()) {
			  if(format.isActive) {
				  outputList.add(format.description);
			  }
		  }
		  return outputList;
	  }
	  
	  public static List<String> getTextOutputFormatList() {
		  List<String> outputList = new ArrayList<>();
		  for (TextOutputFormat format : TextOutputFormat.values()) {
			  if(format.isActive) {
				  outputList.add(format.description);
			  }
		  }
		  return outputList;
	  }

}
