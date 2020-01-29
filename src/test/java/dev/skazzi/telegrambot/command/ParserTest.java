package dev.skazzi.telegrambot.command;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alexandr Eremin (eremin.casha@gmail.com)
 */
class ParserTest {

    private static final String BOT_NAME = "testName";
    private Parser parser = new Parser("testName");

    @BeforeEach
    public void setParser() {
        parser = new Parser(BOT_NAME);
    }

    private void equalTest(Command command, String text, String test) {
        ParsedCommand parsedCommandAndText = parser.getParsedCommand(test);
        assertEquals(command, parsedCommandAndText.command);
        assertEquals(text, parsedCommandAndText.text);
    }

    @Test
    public void getParsedCommand_None() {
        String[] tests = new String[]{"just text", "   ", "  just text  ", "drop"};
        for (String test : tests) {
            equalTest(Command.NONE, null, test);
        }
    }

    @Test
    public void getParsedCommand_NotForMe() {
        String[] tests = new String[]{"/test@another_Bot just text", "/drop@anotherBot just text"};
        for (String test : tests) {
            equalTest(Command.NOTFORME, "just text", test);
        }
    }

    @Test
    public void getParsedCommand_NoneButForMe() {
        String test = "/test@" + BOT_NAME + " just text";
        equalTest(Command.NONE, "just text", test);

    }

    @Test
    public void getParsedCommand_Drop() {
        String[] tests = new String[]{"/drop@" + BOT_NAME + " just text", "/drop just text"};
        for (String test : tests) {
            equalTest(Command.DROP, "just text", test);
        }
    }

}