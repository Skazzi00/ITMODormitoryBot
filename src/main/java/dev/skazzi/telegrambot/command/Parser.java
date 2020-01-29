package dev.skazzi.telegrambot.command;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import javax.validation.constraints.Null;

/**
 * @author Alexandr Eremin (eremin.casha@gmail.com)
 */
public class Parser {
    private static Logger log = Logger.getLogger(Parser.class);
    private final String PREFIX_FOR_COMMAND = "/";
    private final String DELIMITER_COMMAND_BOTNAME = "@";
    private String botName;

    public Parser(String botName) {
        this.botName = botName;
    }

    public ParsedCommand getParsedCommand(String text) {
        ParsedCommand result = new ParsedCommand();
        if (text == null) {
            return result;
        }
        text = text.trim();
        if (text.isEmpty() || !text.startsWith(PREFIX_FOR_COMMAND)) {
            return result;
        }
        SplittedCommand splittedCommand = getDelimitedCommandFromText(text);
        if (isCommand(splittedCommand.command)) {
            if (isForMe(splittedCommand.command)) {
                result.setCommand(getCommand(splittedCommand.command));
            } else {
                result.setCommand(Command.NOTFORME);
            }
            if (splittedCommand.text != null)
                result.setText(splittedCommand.text.trim());
        }
        return result;
    }

    private SplittedCommand getDelimitedCommandFromText(String text) {
        if (text.contains(" ")) {
            int indexOfSpace = text.indexOf(" ");
            return new SplittedCommand(
                    text.substring(0, indexOfSpace),
                    text.substring(indexOfSpace + 1)
            );
        } else {
            return new SplittedCommand(null, text);
        }
    }

    private String cutCommand(String command) {
        return command.contains(DELIMITER_COMMAND_BOTNAME) ?
                command.substring(PREFIX_FOR_COMMAND.length(), command.indexOf(DELIMITER_COMMAND_BOTNAME)) :
                command.substring(PREFIX_FOR_COMMAND.length());
    }

    private Command getCommand(String text) {
        String upperCaseText = cutCommand(text).toUpperCase().trim();
        Command command = Command.NONE;
        try {
            command = Command.valueOf(upperCaseText);
        } catch (IllegalArgumentException e) {
            log.debug("Can't parse command: " + text);
        }
        return command;

    }

    private boolean isForMe(String text) {
        if (text.contains(DELIMITER_COMMAND_BOTNAME)) {
            String name = text.substring(text.indexOf(DELIMITER_COMMAND_BOTNAME) + 1);
            return name.equals(botName);
        }
        return true;
    }

    private boolean isCommand(String text) {
        return text != null && text.startsWith(PREFIX_FOR_COMMAND);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    private static class SplittedCommand {
        public String command;
        public String text;

    }
}
