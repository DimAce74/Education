package ru.itis.data;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.ChatUserDto;
import ru.itis.dto.UserDataForRegistrationDto;
import ru.itis.models.Chat;
import ru.itis.models.ChatUser;

import java.util.Arrays;
import java.util.List;

public class TestData {
    private static PasswordEncoder encoder = new BCryptPasswordEncoder();
    public static final String HASH_PASSWORD_ONE = encoder.encode("123");
    public static final String HASH_PASSWORD_TWO = encoder.encode("456");
    public static final Integer FIRST_ENTITY_ID = 1;
    public static final Integer SECOND_ENTITY_ID = 2;
    public static final Integer INCORRECT_ID = -1;

    public static final Chat FIRST_CHAT = new Chat.Builder()
            .id(FIRST_ENTITY_ID).name("Chat1").build();
    public static final Chat SECOND_CHAT = new Chat.Builder()
            .id(SECOND_ENTITY_ID).name("Chat2").build();
    public static final Chat CHAT_WITHOUT_ID = new Chat.Builder().name("Chat3").build();
    public static final ChatUser USER_ONE = new ChatUser.Builder()
            .id(FIRST_ENTITY_ID).login("Marsel").name("Marsel").password(HASH_PASSWORD_ONE).build();
    public static final ChatUser USER_WITHOUT_ID = new ChatUser.Builder()
            .login("Three").name("Petya").password(HASH_PASSWORD_TWO).build();
    public static final ChatUser USER_TWO = new ChatUser.Builder()
            .id(SECOND_ENTITY_ID).login("Two").name("Kolya").password(HASH_PASSWORD_TWO).build();
    public static final UserDataForRegistrationDto CORRECT_USER_DATA_FOR_REGISTRATION_DTO =
            new UserDataForRegistrationDto("Two", "456", "Kolya");

    public static final List<Chat> CHATS = Arrays.asList(FIRST_CHAT, SECOND_CHAT);
    public static final List<ChatUser> USERS = Arrays.asList(USER_ONE, USER_TWO);
}
