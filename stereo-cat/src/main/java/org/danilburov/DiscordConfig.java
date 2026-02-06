package org.danilburov;

import Exceptions.TokenDoesNotExistException;

public final class DiscordConfig {

    private static final String ENV_VAR = "DISCORD_TOKEN";
    private static final String token = loadToken();

    private DiscordConfig() {
    }

    private static String loadToken() {
        String value = System.getenv(ENV_VAR);
        if (value == null || value.isBlank()) {
            throw new TokenDoesNotExistException();
        }
        return value;
    }

    public static String getToken() {
        return token;
    }
}
