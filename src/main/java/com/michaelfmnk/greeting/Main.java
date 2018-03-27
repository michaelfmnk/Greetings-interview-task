package com.michaelfmnk.greeting;
import org.apache.commons.cli.*;

import java.util.TimeZone;

public class Main {
    private final static String PARAM_LANG = "language";
    private final static String PARAM_LANG_SHORT = "l";
    private final static String PARAM_TIMEZONE = "timezone";
    private final static String PARAM_TIMEZONE_SHORT = "tz";

    public static void main(String[] args) {
        CommandLine params;
        try {
            params = parseCommandLineParams(args);
        } catch (ParseException e) {
            e.printStackTrace();
            return;
        }

        for (String city:
                params.getArgs()) {
            HelloMessageProvider messageProvider = new HelloMessageProvider(city);
            System.out.println(messageProvider.getMessage(params.getOptionValue(PARAM_LANG_SHORT), params.getOptionValue(PARAM_TIMEZONE_SHORT)));
        }


    }

    static private CommandLine parseCommandLineParams(String[] args) throws ParseException{
        Options options = new Options();

        Option input = new Option(PARAM_LANG_SHORT, PARAM_LANG, true, "language used to say hello");
        input.setRequired(false);
        options.addOption(input);

        Option output = new Option(PARAM_TIMEZONE_SHORT, PARAM_TIMEZONE, true, "time zone you are located in");
        output.setRequired(false);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
            throw new ParseException("error while parsing command line");
        }
        return cmd;

    }

}
