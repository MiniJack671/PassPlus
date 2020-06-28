package dev.nuer.pp.utils;

import dev.nuer.pp.enable.FileManager;

public class ProgressBarUtil {

    public static String bar(double current, double total) {
        double percent = (current / total) * FileManager.get("config").getInt("progress-bar-length");
        StringBuilder bar = new StringBuilder();
        for (int i = 1; i <= FileManager.get("config").getInt("progress-bar-length"); i++) {
            if (percent < 0) {
                bar.append(ColorUtil.colorize("&a|"));
            } else {
                if (i <= percent) {
                    bar.append(ColorUtil.colorize("&a|"));
                } else {
                    bar.append(ColorUtil.colorize("&c|"));
                }
            }
        }
        return bar.toString();
    }
}