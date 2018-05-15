package commonsCLI;

import org.apache.commons.cli.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * The Apache Commons CLI library provides an API for parsing command line options passed to programs.
 * It's also able to print help messages detailing the options available for a command line tool.
 *
 * @author Hu Jianbo
 * @date: 2018/5/15
 */
public class TestCLI {

    private final static String helpStr = "h";
    private final static String versionStr = "v";
    private final static String filePathStr = "f";
    private final static String fileBasePath = System.getProperty("user.dir") + "\\src\\main\\java\\commonsCLI\\";
    private final static String version = "1.0.0";

    public static void main(String[] args) {
        String[] argss = {"-f", "config.properties"};
        doIt(argss);

    }

    private static void doIt(String[] args) {
        Options options = new Options();
        options.addOption("h", null, false, "help info");
        options.addOption("v", null, false, "version info");
        options.addOption("f", null, true, "config file path");
        CommandLine commandLine;
        CommandLineParser parser = new DefaultParser();
        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption(helpStr)) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("ant", options);
                System.exit(0);
            }
            if (cmd.hasOption(versionStr)) {
                System.out.println("version:" + version);
                System.exit(0);
            }
            if (cmd.hasOption(filePathStr)) {
                String filePath = cmd.getOptionValue(filePathStr);
                String content = readFile(filePath);
                System.out.println("file content:" + content);
                System.exit(0);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static String readFile(String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(fileBasePath + filePath)));
            return content;
        } catch (IOException e) {
            return "文件读取错误";
        }
    }
}
