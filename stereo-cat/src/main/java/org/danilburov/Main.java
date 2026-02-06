package org.danilburov;
import Exceptions.TokenDoesNotExistException;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.Commands;


public class Main {
    public static void main(String[] args) {
        JDA jda = JDABuilder.createDefault(DiscordConfig.token()).addEventListeners(new SlashCommandListener()).build();


        try {
            jda.awaitReady();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Bot startup interrupted");
        }

        // Register the command (global OR guild). Use guild for fast testing.
        String guildId = DiscordConfig.getDevGuildID();
        System.out.println(jda.getGuildById(guildId));

        if (guildId != null && !guildId.isBlank()) {
            // Fast: appears within seconds in that server
            var guild = jda.getGuildById(guildId);
            if (guild == null) {
                System.err.println("DEV_GUILD_ID is set but the bot is not in that server (or ID is wrong).");
            } else {
                guild.updateCommands()
                        .addCommands(Commands.slash("ping", "Replies with Pong!"))
                        .addCommands(Commands.slash("firstowncommand", "Replies with 'Hi there'"))
                        .queue();
                System.out.println("Registered /ping for guild " + guildId);
            }
        } else {
            // Slow: can take minutes to propagate globally
            jda.updateCommands()
                    .addCommands(Commands.slash("ping", "Replies with Pong!"))
                    .addCommands(Commands.slash("firstowncommand", "Replies with 'Hi there'"))
                    .queue();
            System.out.println("Registered /ping globally (may take some time to appear).");
        }

        System.out.println("Bot is READY as: " + jda.getSelfUser());
    }
}