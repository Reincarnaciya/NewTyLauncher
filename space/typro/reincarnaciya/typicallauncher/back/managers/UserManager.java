package space.typro.reincarnaciya.typicallauncher.back.managers;

import com.google.gson.Gson;
import space.typro.reincarnaciya.typicallauncher.back.objects.AuthorizeUser;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import static space.typro.reincarnaciya.typicallauncher.Main.PATH_TO_ACCOUNTS_FILE;
import static space.typro.reincarnaciya.typicallauncher.Main.globalSettings;

public class UserManager {

    public static final List<AuthorizeUser> authorizeUsers = new ArrayList<>();
    public static AuthorizeUser currentUser;
    public static void loadUsers() {
        Gson gson = new Gson();
        Path filePath = Paths.get(PATH_TO_ACCOUNTS_FILE);

        if (!filePath.toFile().exists()) {
            try {
                filePath.toFile().createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try (BufferedReader reader = Files.newBufferedReader(filePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                AuthorizeUser user = gson.fromJson(line, AuthorizeUser.class);
                if (user.isAuthorized()){
                    authorizeUsers.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static AuthorizeUser getAuthorizeUser(String username) {
        return authorizeUsers.stream()
                .filter(user -> user.getUser().getUserName().equals(username))
                .findFirst()
                .orElse(null);
    }
    public static void addUser(AuthorizeUser user){
        if (!authorizeUsers.contains(user)) {
            authorizeUsers.add(user);
        }
    }
    public static void removeUser(AuthorizeUser user){
        authorizeUsers.remove(user);
        authorizeUsers.forEach(System.out::println);
        if (globalSettings.autoAuthorizeUser.equals(user)) globalSettings.autoAuthorizeUser = AuthorizeUser.NULL_AUTHORIZE_USER;
    }

    public static void saveUsersToFile() {
        Path filePath = Paths.get(PATH_TO_ACCOUNTS_FILE);
        Gson gson = new Gson();

        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            List<String> lines = new ArrayList<>();
            for (AuthorizeUser user : authorizeUsers) {
                lines.add(gson.toJson(user));
            }
            System.err.println(lines);
            Files.write(filePath, lines, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
