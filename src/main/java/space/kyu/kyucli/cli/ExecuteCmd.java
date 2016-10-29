package space.kyu.kyucli.cli;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.Properties;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 * 配置参数
 * 
 * @author yukai
 *
 */
public class ExecuteCmd {
	private static final String boolOptionsPath;
	private static final String argOptionsPath;
	private static Properties boolOptionsPro;
	private static Properties argOptionsPro;

	static {
		boolOptionsPath = "./booloptions.properties";
		argOptionsPath = "./argoptions.properties";
	}

	public static void exec(CommandLine command) throws IOException {
		boolOptionsPro = loadProperties(boolOptionsPath);
		argOptionsPro = loadProperties(argOptionsPath);

		if (command.hasOption("help")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp("help", ConfigOPtions.options);
		} else if (command.hasOption(ConfigOPtions.CD)) {
			String arg = command.getOptionValue(ConfigOPtions.CD);
			String propName = ConfigOPtions.CD + "_" + arg;
			String folderPath = argOptionsPro.getProperty(propName);
			openFolder(folderPath);
		} else if (command.hasOption(ConfigOPtions.RUN)) {
			String arg = command.getOptionValue(ConfigOPtions.RUN);
			String propName = ConfigOPtions.RUN + "_" + arg;
			String folderPath = argOptionsPro.getProperty(propName);
			runAPP(folderPath);
		} else if (command.hasOption(ConfigOPtions.URL)) {
			String arg = command.getOptionValue(ConfigOPtions.URL);
			String propName = ConfigOPtions.URL + "_" + arg;
			String folderPath = argOptionsPro.getProperty(propName);
			openUrl(folderPath);
		}
	}

	private static void openFolder(String path) {
		if (path == null || "".equals(path)) {
			// 错误的指令
			System.out.println("can't find the specified path!");
			return;
		}
		File file = new File(path);
		if (!file.exists()) {
			// 配置文件中指定的路径有误
			System.out.println("specified path error! check your argoptions.properties");
			return;
		}
		Runtime runtime = null;
		try {
			runtime = Runtime.getRuntime();
			runtime.exec("cmd /c start explorer " + path);
		} catch (IOException ex) {
			System.out.println("open folder failure! " + ex.getMessage());
		} finally {
			if (null != runtime) {
				runtime.runFinalization();
			}
		}
	}

	private static void runAPP(String appPath) {
		Runtime rt = Runtime.getRuntime();
		Process p = null;
		try {
			p = rt.exec(appPath);
		} catch (Exception e) {
			System.out.println("start app failure" + e.getMessage());
		}
	}
	
	private static void openUrl(String url) {
		try {
			Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
		} catch (IOException e) {
			System.out.println("open url failure! " + e.getMessage());
		} 
	}

	private static Properties loadProperties(String filePath) throws IOException {
		Properties properties = new Properties();
		InputStreamReader is = null;
		try {
			File file = new File(filePath);
			InputStream stream = new FileInputStream(file);
			is = new InputStreamReader(stream, "UTF-8");
			properties.load(is);
		} finally {
			if (is != null) {
				is.close();
			}
		}
		return properties;
	}

}
