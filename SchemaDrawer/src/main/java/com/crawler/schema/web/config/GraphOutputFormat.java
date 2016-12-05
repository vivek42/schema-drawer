package com.crawler.schema.web.config;

import java.util.ArrayList;
import java.util.List;

public enum GraphOutputFormat {

	 htmlx("htmlx", "SchemaCrawler graph embedded in HTML5", false),
	 scdot("scdot", "SchemaCrawler generated format", false),
	 //
	 bmp("bmp", "Windows Bitmap Format", false),
	 canon("canon", "DOT", false),
	 dot("dot", "DOT", false),
	 gv("gv", "DOT", false),
	 xdot("xdot", "DOT", false),
	 xdot1_2("xdot1.2", "DOT", false),
	 xdot1_4("xdot1.4", "DOT", false),
	 cgimage("cgimage", "CGImage bitmap format", false),
	 cmap("cmap", "Client-side imagemap (deprecated)", false),
	 eps("eps", "Encapsulated PostScript", false),
	 exr("exr", "OpenEXR", false),
	 fig("fig", "FIG", false),
	 gd("gd", "GD/GD2 formats", false),
	 gd2("gd2", "GD/GD2 formats", false),
	 gif("gif", "GIF", false),
	 gtk("gtk", "GTK canvas", false),
	 ico("ico", "Icon Image File Format", false),
	 imap("imap", "Server-side and client-side imagemaps", false),
	 cmapx("cmapx", "Server-side and client-side imagemaps", false),
	 imap_np("imap_np", "Server-side and client-side imagemaps", false),
	 cmapx_np("cmapx_np", "Server-side and client-side imagemaps", false),
	 jp2("jp2", "JPEG 2000", false),
	 jpg("jpg", "JPEG", true),
	 jpeg("jpeg", "JPEG", false),
	 jpe("jpe", "JPEG", false),
	 pct("pct", "PICT", false),
	 pict("pict", "PICT", false),
	 pdf("pdf", "Portable Document Format (PDF)", false),
	 pic("pic", "Kernighan's PIC graphics language", false),
	 plain("plain", "Simple text format", false),
	 plain_ext("plain-ext", "Simple text format", false),
	 png("png", "Portable Network Graphics format", false),
	 pov("pov", "POV-Ray markup language (prototype)", false),
	 ps("ps", "PostScript", false),
	 ps2("ps2", "PostScript for PDF", false),
	 psd("psd", "PSD", false),
	 sgi("sgi", "SGI", false),
	 svg("svg", "Scalable Vector Graphics", false),
	 svgz("svgz", "Scalable Vector Graphics", false),
	 tga("tga", "Truevision TGA", false),
	 tif("tif", "TIFF (Tag Image File Format)", false),
	 tiff("tiff", "TIFF (Tag Image File Format)", false),
	 tk("tk", "TK graphics", false),
	 vml("vml", "Vector Markup Language (VML)", false),
	 vmlz("vmlz", "Vector Markup Language (VML)", false),
	 vrml("vrml", "VRML", false),
	 wbmp("wbmp", "Wireless BitMap format", false),
	 webp("webp", "Image format for the Web", false),
	 xlib("xlib", "Xlib canvas", false),
	 x11("x11", "Xlib canvas", false),;

	private final String format;

	private final String description;

	private final boolean isActive;

	private GraphOutputFormat(final String format, final String description, final boolean isActive) {
		this.format = format;
		this.description = description;
		this.isActive = isActive;
	}

	public static List<String> getActiveGraphOutputFormatList() {
		List<String> outputList = new ArrayList<>();
		for (GraphOutputFormat format : GraphOutputFormat.values()) {
			if (format.isActive) {
				outputList.add(format.format + ", " + format.description);
			}
		}
		return outputList;
	}

}
