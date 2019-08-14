package com.example.corenetwork.external.restring;

import com.example.corenetwork.external.restring.Restring;

/**
 * Contains configuration properties for initializing Restring.
 */
public class RestringConfig {

    private boolean persist;
    private com.example.corenetwork.external.restring.Restring.StringsLoader stringsLoader;

    public boolean isPersist() {
        return persist;
    }

    public com.example.corenetwork.external.restring.Restring.StringsLoader getStringsLoader() {
        return stringsLoader;
    }

    private RestringConfig() {
    }

    public static class Builder {
        private boolean persist;
        private com.example.corenetwork.external.restring.Restring.StringsLoader stringsLoader;

        public Builder persist(boolean persist) {
            this.persist = persist;
            return this;
        }

        public Builder stringsLoader(Restring.StringsLoader loader) {
            this.stringsLoader = loader;
            return this;
        }

        public RestringConfig build() {
            RestringConfig config = new RestringConfig();
            config.persist = persist;
            config.stringsLoader = stringsLoader;
            return config;
        }
    }

    static RestringConfig getDefault() {
        return new Builder()
                .persist(true)
                .build();
    }
}