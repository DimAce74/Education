package ru.itis.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.itis.config.SpringConfig;
import ru.itis.dao.ChatUserDao;
import ru.itis.dto.ChatUserDto;
import ru.itis.exceptions.ChatNotFoundException;
import ru.itis.exceptions.UserNotFoundException;
import ru.itis.models.ChatUser;
import ru.itis.security.utils.TokenGenerator;
import ru.itis.services.impl.ChatUserServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static ru.itis.data.TestData.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@WebAppConfiguration
public class ChatUserServiceImplTest {

    @InjectMocks
    private ChatUserServiceImpl chatUserService;

    @Mock
    private ChatUserDao chatUserDao;

    @Mock
    private TokenGenerator generator;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doThrow(UserNotFoundException.class).when(chatUserDao).find(anyInt());
        doReturn(USER_ONE).when(chatUserDao).find(FIRST_ENTITY_ID);
        doReturn(SECOND_ENTITY_ID).when(chatUserDao).save(any(ChatUser.class));
        doReturn(FIRST_ENTITY_ID).when(chatUserDao).save(USER_ONE);
        doThrow(UserNotFoundException.class).when(chatUserDao).delete(anyInt());
        doNothing().when(chatUserDao).delete(FIRST_ENTITY_ID);
        doReturn(USERS).when(chatUserDao).findAll();
        //doThrow(ChatNotFoundException.class).when(chatUserDao).saveUserToChat(FIRST_ENTITY_ID, anyInt());
        doThrow(UserNotFoundException.class).when(chatUserDao).saveUserToChat(anyInt(), anyInt());
        doNothing().when(chatUserDao).saveUserToChat(FIRST_ENTITY_ID, FIRST_ENTITY_ID);
    }

    @Test
    public void testFindWithCorrectId() throws Exception {
        assertEquals(USER_ONE, chatUserService.find(FIRST_ENTITY_ID));
    }

    @Test(expected = UserNotFoundException.class)
    public void testFindWithNotCorrectId() throws Exception {
        chatUserService.find(INCORRECT_ID);
    }

    @Test
    public void testSaveWithId() throws Exception {
        assertTrue(FIRST_ENTITY_ID==chatUserService.save(USER_ONE));
    }

    @Test
    public void testSaveWithoutId() throws Exception {
        assertTrue(SECOND_ENTITY_ID==chatUserService.save(USER_WITHOUT_ID));
    }

    @Test
    public void testDeleteWithCorrectId() throws Exception {
        chatUserService.delete(FIRST_ENTITY_ID);
        verify(chatUserDao).delete(FIRST_ENTITY_ID);
    }

    @Test(expected = UserNotFoundException.class)
    public void testDeleteWithNotCorrectId() throws Exception {
        chatUserService.delete(INCORRECT_ID);
    }

    @Test
    public void testFindAll() throws Exception {
        assertEquals(USERS, chatUserService.findAll());
    }

    @Test
    public void testSaveUserToChatCorrectUserCorrectChat() throws Exception {
        chatUserService.saveUserToChat(FIRST_ENTITY_ID, FIRST_ENTITY_ID);
        verify(chatUserDao).saveUserToChat(FIRST_ENTITY_ID, FIRST_ENTITY_ID);
    }

    @Test(expected = UserNotFoundException.class)
    public void testSaveUserToChatIncorrectUserCorrectChat() throws Exception {
        chatUserService.saveUserToChat(INCORRECT_ID, FIRST_ENTITY_ID);
    }

    @Test(expected = UserNotFoundException.class)
    public void testSaveUserToChatIncorrectUserInorrectChat() throws Exception {
        chatUserService.saveUserToChat(INCORRECT_ID, INCORRECT_ID);
    }
/**
    @Test(expected = ChatNotFoundException.class)
    public void testSaveUserToChatCorrectUserInorrectChat() throws Exception {
        chatUserService.saveUserToChat(FIRST_ENTITY_ID, INCORRECT_ID);
    }
*/
    @Test
    public void testRegisterUser() throws Exception {
        //chatUserService.registerUser(CORRECT_USER_DATA_FOR_REGISTRATION_DTO);

    }

    @Test
    public void isLoginExists() throws Exception {

    }

    @Test
    public void login() throws Exception {

    }

    @Test
    public void isExistsToken() throws Exception {

    }

    @Test
    public void findUserByToken() throws Exception {

    }

    @Test
    public void getToken() throws Exception {

    }

    @Test
    public void logout() throws Exception {

    }

}