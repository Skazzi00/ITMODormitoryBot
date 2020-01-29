package dev.skazzi.telegrambot.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

/**
 * @author Alexandr Eremin (eremin.casha@gmail.com)
 */

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ParsedCommand {
    Command command = Command.NONE;
    String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParsedCommand that = (ParsedCommand) o;
        return command == that.command &&
                Objects.equals(text, that.text);
    }

    @Override
    public String toString() {
        return "ParsedCommand{" +
                "command=" + command +
                ", text='" + text + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(command, text);
    }
}
