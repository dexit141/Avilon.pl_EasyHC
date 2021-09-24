package pl.godzina.avilon.basic.configs;

import org.bukkit.configuration.file.*;
import pl.godzina.avilon.AvilonPlugin;

import java.lang.reflect.*;
import java.io.*;
import java.util.*;

public class GuildMessages
{
    private static File file;
    private static FileConfiguration c;
    public static List<String> listOfCommands;
    public static String yourGuildNotNull;
    public static String yourGuildIsNull;
    public static String incorrectGuildName;
    public static String guildCreateBroadcastNotification;
    public static String guildDeleteBroadcastNotification;
    public static String guildMemberJoinBroadcastNotification;
    public static String guildAllyIncludeBroadcastNotification;
    public static String guildAllyBreakBroadcastNotification;
    public static String guildAlreadyExist;
    public static String guildIsTooCloseToSpawn;
    public static String guildIsTooCloseToOtherGuild;
    public static String yourAreNotGuildLeader;
    public static String guildDeleteConfirm;
    public static String guildDeleteConfirmCorrectUsage;
    public static String playerAlreadyHasGuild;
    public static String guildTagDoesntExist;
    public static String youDontHaveAnInvitationToThisGuild;
    public static String yourGuildDontHaveAnAllianceWithThisGuild;
    public static String yourGuildAlreadyHasAllianceWithThisGuild;
    public static String allyInvitationHasBeenCanceled;
    public static String youHaveReachedMaxSizeAlliances;
    public static String guildOwnerIsOffline;
    public static String allyInvitationHasBeenCanceledFromOtherGuild;
    public static String otherGuildYouHaveReachedMaxSizeAlliances;
    public static String youCantIncludeAllianceWithYourGuild;
    public static String playerIsNotInYourGuild;
    public static String youAreNotGuildDeputy;
    public static String guildHomeIsTooLow;
    public static String guildHomeIsOutsideGuildCuboid;
    public static String successGuildHomeCreated;

    public static void load() {
        try {
            if (!GuildMessages.file.exists()) {
                GuildMessages.file.getParentFile().mkdirs();
                final InputStream is = AvilonPlugin.getInstance().getResource(GuildMessages.file.getName());
                if (is != null) {
                    copy(is, GuildMessages.file);
                }
            }
            GuildMessages.c = (FileConfiguration)YamlConfiguration.loadConfiguration(GuildMessages.file);
            Field[] fields;
            for (int length = (fields = GuildMessages.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                if (GuildMessages.c.isSet(f.getName())) {
                    f.set(null, GuildMessages.c.get(f.getName()));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try {
            Field[] fields;
            for (int length = (fields = GuildMessages.class.getFields()).length, i = 0; i < length; ++i) {
                final Field f = fields[i];
                GuildMessages.c.set(f.getName(), f.get(null));
            }
            GuildMessages.c.save(GuildMessages.file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reload() {
        load();
        save();
    }

    public static void copy(final InputStream in, final File file) {
        try {
            final OutputStream out = new FileOutputStream(file);
            final byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            out.close();
            in.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    static {
        GuildMessages.file = new File(AvilonPlugin.getInstance().getDataFolder(), "guildMessages.yml");
        GuildMessages.c = null;
        GuildMessages.listOfCommands = Arrays.asList("    &8&m-----&8(&b&l GILDIE &8)&8&M-----", "&8>> &3/g zaloz <tag> <nazwa> &8- &fzaklada gildie", "&8>> &b/g dolacz <tag/nazwa> &8- &fdolaczasz do gildii", "&8>> &3/g opusc &8- &fopuszczasz gildie", "&8>> &b/g dom &8- &fteleportacja do gildii", "&8>> &3/g ustawdom &8- &fustawia baze gildii", "&8>> &b/g przedluz &8- &fprzedluza gildie", "&8>> &3/g powieksz &8- &fpowieksza teren gildii", "&8>> &b/g zastepca <gracz> &8- &fnadaje range zastepca czlonkowi", "&8>> &3/g zapros <nick> &8- &fzaprasza gracza do gildii", "&8>> &b/g wyrzuc <nick> &8- &fwyrzuca gracza z gildii", "&8>> &3/g itemy &8- &fpokazuje potrzebne itemy na gildie", "&8>> &b/g lider <nick> &8- &fprzekazuje wlasciciela gildii", "&8>> &3/g info <tag> &8- &finformacje o gildii", "&8>> &b/g sojusz <zawrzyj/zerwij> <tag> &8- &fnawiazuje lub zrywa sojusz z gildia", "&8>> &3/g usun &8- &fusuwa gildie", "&8>> &b/g pvp <sojusz> &8- &fwlacza/wylacza pvp w gildii/sojuszu", "&8>> &3/g panel &8- &fpanel gildyjny", "", "&8>> &3! &8- &fWiadomosc do gildii", "&8>> &3!! &8- &fWiadomosc do sojuszy");
        GuildMessages.yourGuildNotNull = "&4Blad: &cPosiadasz juz gildie!";
        GuildMessages.yourGuildIsNull = "&4Blad: &cNie posiadasz gildii!";
        GuildMessages.incorrectGuildName = "&4Blad: &cTag gildi musi zawierac 2-5 zankow, nazwa 4-32 znakow, nazwa i tag gildii moze zawierac tylko litery i cyfry.";
        GuildMessages.guildCreateBroadcastNotification = "&8>> &7Gildia &8[&b{TAG}&8] &3{NAME} &7zostala utworzona przez &b{PLAYER}&7!";
        GuildMessages.guildDeleteBroadcastNotification = "&8>> &7Gildia &8[&b{TAG}&8] &3{NAME} &7zostala rozwiazana przez &b{PLAYER}&7!";
        GuildMessages.guildMemberJoinBroadcastNotification = "&8>> &7Gracz &b{PLAYER} &7dolaczyl do gildii &8[&b{TAG}&8] &3{NAME}&7!";
        GuildMessages.guildAllyIncludeBroadcastNotification = "&8>> &7Gildia &8[&b{GUILDA}&8] &7zawarla sojusz z gildia &8[&b{GUILDB}&8]&7!";
        GuildMessages.guildAllyBreakBroadcastNotification = "&8>> &7Gildia &8[&b{GUILDA}&8] &7zerwala sojusz z gildia &8[&b{GUILDB}&8]&7!";
        GuildMessages.guildAlreadyExist = "&4Blad: &cGildia o takim tagu lub nazwie juz istnieje!";
        GuildMessages.guildIsTooCloseToSpawn = "&4Blad: &cJestes zbyt blisko spawna!";
        GuildMessages.guildIsTooCloseToOtherGuild = "&4Blad: &cJestes zbyt blisko innej gildii!";
        GuildMessages.yourAreNotGuildLeader = "&4Blad: &cNie jestes zalozycielem gildii!";
        GuildMessages.guildDeleteConfirm = "&8>> &7Wpisz komende &b/g usun potwierdz &7aby potwierdzic usuniecie gildii!";
        GuildMessages.guildDeleteConfirmCorrectUsage = "&4Blad: &cAby moc potwierdzic usuniecie, musisz pierw wpisac komende /g usun";
        GuildMessages.playerAlreadyHasGuild = "&4Blad: &cTen gracz posiada juz gildie!";
        GuildMessages.guildTagDoesntExist = "&4Blad: &cNie gildii o takim tagu na serwerze";
        GuildMessages.youDontHaveAnInvitationToThisGuild = "&4Blad: &cNie posiadasz zaproszenia do tej gildii!";
        GuildMessages.yourGuildDontHaveAnAllianceWithThisGuild = "&4Blad: &cTwoja gildia nie ma sojuszu z gildia {TAG}";
        GuildMessages.yourGuildAlreadyHasAllianceWithThisGuild = "&4Blad: &cTwoja gildia posiada juz sojusz z gildia {TAG}";
        GuildMessages.allyInvitationHasBeenCanceled = "&8>> &7Zaproszenie do sojuszu z gildia &8[&b{TAG}&8] &7zostalo cofniete!";
        GuildMessages.youHaveReachedMaxSizeAlliances = "&4Blad: &cPosiadasz juz maksymalna liczbe sojuszy! (3)";
        GuildMessages.guildOwnerIsOffline = "&4Blad: &cZalozyciel tej gildii jest offline!";
        GuildMessages.allyInvitationHasBeenCanceledFromOtherGuild = "&8>> &7Gildia &8[&b{TAG}&8] &7cofnela zaproszenie do sojuszu!";
        GuildMessages.otherGuildYouHaveReachedMaxSizeAlliances = "&8>> &7Gildia &8[&b{TAG}&8] &7posiada juz maksymalna liczbe sojuszy! &b(3)";
        GuildMessages.youCantIncludeAllianceWithYourGuild = "&4Blad: &cNie mozesz zawrzec sojuszu ze swoja gildia!";
        GuildMessages.playerIsNotInYourGuild = "&4Blad: &cGracz nie jest w twojej gildii!";
        GuildMessages.youAreNotGuildDeputy = "&4Blad: &cNie jestes zastepca gildii!";
        GuildMessages.guildHomeIsTooLow = "&4Blad: &cBaz\u0119 gildii mo\u017cesz ustawic powyzej 2 poziomu Y.";
        GuildMessages.guildHomeIsOutsideGuildCuboid = "&4Blad: &cAby ustawic baze gildyjna, musisz byc na terenie swojej gildii!";
        GuildMessages.successGuildHomeCreated = "&8>> &aPomyslnie ustawiles nowa lokalizacje bazy gildyjnej!";
    }
}

