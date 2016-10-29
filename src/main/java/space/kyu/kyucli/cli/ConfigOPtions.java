package space.kyu.kyucli.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;

public class ConfigOPtions {
	public static Options options;
	public static final String CD = "cd";
	public static final String RUN = "run";
	public static final String HELP = "help";
	public static final String URL = "url";
	static {
		options = new Options();
		config();
	}

	private static Options config() {
		Option help = new Option(HELP, "print this message");
		Option cd = Option.builder(CD)
				.desc("cd the folder specified with argument")
				.argName("folder|file")
				.hasArg(true)
				.build();
		
		Option run = Option.builder(RUN)
				.desc("run the application specified with argument")
				.argName("app")
				.hasArg(true)
				.build();
		Option url = Option.builder(URL)
				.desc("open the url specified with argument")
				.argName("uri")
				.hasArg(true)
				.build();
		
		options.addOption(help)
		       .addOption(cd)
		       .addOption(run)
		       .addOption(url);
		       
		return options;
	}
}
