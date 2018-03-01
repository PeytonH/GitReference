package com.example.skybreaker.json;

/**
 * Created by SkyBreaker on 2/25/2018.
 */

public class commandList {
        private String command;
        private String example;
        private String explanation;
        private String section;

        public commandList() {}
        public String getExplanation() {
        return explanation;
    }
        public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
        public String getCommand() {
            return command;
        }
        public void setCommand(String command) {
            this.command = command;
        }
        public String getExample() {
            return example;
        }
        public void setExample(String example) {
            this.example = example;
        }
        public String getSection() {
            return section;
        }
        public void setSection(String section) {
            this.section = section;
        }
}
