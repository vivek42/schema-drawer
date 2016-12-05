package com.crawler.schema.web.model;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;

import com.crawler.schema.web.config.GraphOutputFormat;

import schemacrawler.schemacrawler.ExcludeAll;
import schemacrawler.schemacrawler.IncludeAll;
import schemacrawler.schemacrawler.SchemaCrawlerOptions;
import schemacrawler.schemacrawler.SchemaInfoLevelBuilder;
import schemacrawler.tools.executable.Executable;
import schemacrawler.tools.executable.SchemaCrawlerExecutable;
import schemacrawler.tools.options.OutputOptions;
import schemacrawler.tools.options.TextOutputFormat;

public class SchemaCrawlerUtility {

	public static Path runSchemaCrawler(Connection conn, String outputFileName) throws Exception {
		// Set logging on
//	    applyApplicationLogLevel(Level.ALL);
	    // Log system properties and classpath
//	    logSystemProperties();

	    // Create the options
	    final SchemaCrawlerOptions options = new SchemaCrawlerOptions();
	    // Set what details are required in the schema - this affects the
	    // time taken to crawl the schema
	    options.setSchemaInfoLevel(SchemaInfoLevelBuilder.standard());
	    options.setRoutineInclusionRule(new ExcludeAll());
		//	    options
		//	      .setSchemaInclusionRule(new RegularExpressionInclusionRule("db"));
			    
		options.setSchemaInclusionRule(new IncludeAll());


	    final Path outputFile = Paths.get(outputFileName)
	      .toAbsolutePath().normalize();
	    //final OutputOptions outputOptions = new OutputOptions(TextOutputFormat.html,
	    //                                                      outputFile);
	    //final String command = "schema";
	    
	    final OutputOptions outputOptions = new OutputOptions();
	    
	    final String command = "schema";

	    final Executable executable = new SchemaCrawlerExecutable(command);
	    executable.setSchemaCrawlerOptions(options);
	    executable.setOutputOptions(outputOptions);
	    executable.execute(conn);

	    System.out.println("Created output file, " + outputFile);
	    return outputFile;
	}
}
