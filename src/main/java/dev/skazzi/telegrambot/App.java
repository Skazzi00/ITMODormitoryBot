package dev.skazzi.telegrambot;

import dev.skazzi.telegrambot.bot.Bot;
import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;

/**
 * @author Alexandr Eremin (eremin.casha@gmail.com)
 */
public class App {
    private static final Logger log = Logger.getLogger(App.class);

    private static final String USER_NAME = "ITMODormitoryBot";
    private static final String TOKEN = "%token%";


    public static void main(String[] args) {
        ApiContextInitializer.init();
        Bot bot = new Bot(USER_NAME, TOKEN);
        bot.botConnect();
    }
}
