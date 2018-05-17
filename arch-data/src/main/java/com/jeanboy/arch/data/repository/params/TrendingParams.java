package com.jeanboy.arch.data.repository.params;

public class TrendingParams {

    public enum Period {
        Daily, Weekly, Monthly
    }

    public enum Language {
        Unknown("unknown"),
        C("c"),
        CPlusPlus("c++"),
        Java("java"),
        JavaScript("javascript"),
        Kotlin("kotlin"),
        ObjectiveC("objective-c"),
        Swift("swift"),
        Python("python"),
        Go("go"),
        PHP("php"),
        HTML("html"),
        CSS("css");

        private String value;

        Language(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    private Period period;
    private Language language;

    public TrendingParams(Period period, Language language) {
        this.period = period;
        this.language = language;
    }

    public Period getPeriod() {
        return period;
    }

    public Language getLanguage() {
        return language;
    }
}
