package space.kyu.kyucli;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import space.kyu.kyucli.cli.ConfigOPtions;
import space.kyu.kyucli.cli.ExecuteCmd;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		Options options = ConfigOPtions.options;
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (ParseException e) {
			System.err.println( "Parsing failed.  Reason: " + e.getMessage());
			return;
		}

		try {
			ExecuteCmd.exec(cmd);
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
}
