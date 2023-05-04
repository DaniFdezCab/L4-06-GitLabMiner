package AISS.GitLabMiner.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Draft {
    public static void main(String[] args) {
        LocalDateTime since = LocalDateTime.now().minusDays(1);
        since.format(DateTimeFormatter.ofPattern("YYYY-MM-DD'T'HH:MM:SS'Z'")).toString();

        System.out.println(since);
    }
}
