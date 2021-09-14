package pl.godzina.avilon.helpers;

import java.net.HttpURLConnection;
import java.net.URL;

public class LoginHelper {
        public static boolean isPremium(String name) {
            try {
                return ((HttpURLConnection) new URL("https://api.ashcon.app/mojang/v2/user/" + name).openConnection()).getResponseCode() == 200;
            } catch (Exception e) {
                return false;
            }
        }
}
