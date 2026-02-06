package org.danilburov;

import java.io.InputStream;
import java.util.Properties;

import Exceptions.TokenDoesNotExistException;

public final class DiscordConfig {

    private static final String CONFIG_FILE = "discord.properties";
    private static final String TOKEN_KEY = "discord.token";
    private static final String GUILD_KEY = "discord.guild";

    private static final Properties props = loadProps();

    private DiscordConfig() {}

    private static Properties loadProps() {
        try (InputStream is = DiscordConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (is == null) {
                throw new TokenDoesNotExistException("Config file not found on classpath: " + CONFIG_FILE);
            }
            Properties p = new Properties();
            p.load(is);
            return p;
        } catch (TokenDoesNotExistException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    public static String token() {
        String value = props.getProperty(TOKEN_KEY);
        if (value == null || value.isBlank()) {
            throw new TokenDoesNotExistException("Missing key: " + TOKEN_KEY);
        }
        return value.trim();
    }

    public static String getDevGuildID() {
        String value = props.getProperty(GUILD_KEY);
        if (value == null || value.isBlank()) return null;
        return value.trim();
    }
}
