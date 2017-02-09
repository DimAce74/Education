package ru.itis.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.itis.config.SpringConfig;
import ru.itis.dao.ChatDao;
import ru.itis.exceptions.ChatNotFoundException;
import ru.itis.models.Chat;
import ru.itis.services.impl.ChatServiceImpl;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;
import static ru.itis.data.TestData.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@WebAppConfiguration
public class ChatServiceImplTest {

    @InjectMocks
    private ChatServiceImpl chatService;

    @Mock
    private ChatDao chatDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        doThrow(ChatNotFoundException.class).when(chatDao).find(anyInt());
        doReturn(FIRST_CHAT).when(chatDao).find(FIRST_ENTITY_ID);
        doReturn(SECOND_ENTITY_ID).when(chatDao).save(any(Chat.class));
        doReturn(FIRST_ENTITY_ID).when(chatDao).save(FIRST_CHAT);
        doThrow(ChatNotFoundException.class).when(chatDao).delete(anyInt());
        doNothing().when(chatDao).delete(FIRST_ENTITY_ID);
        doReturn(CHATS).when(chatDao).findAll();
    }

    @Test
    public void testFindCorrectId() throws Exception {
        assertEquals(FIRST_CHAT, chatService.find(FIRST_ENTITY_ID));
    }

    @Test(expected = ChatNotFoundException.class)
    public void testFindNotCorrectId() throws Exception {
        chatService.find(INCORRECT_ID);
    }

    @Test
    public void testSaveWithId() throws Exception {
        assertTrue(FIRST_ENTITY_ID ==chatService.save(FIRST_CHAT));
    }

    @Test
    public void testSaveWithoutId() throws Exception {
        assertTrue(SECOND_ENTITY_ID==chatService.save(CHAT_WITHOUT_ID));
    }

    @Test
    public void testDeleteWithCorrectId() throws Exception {
        chatService.delete(FIRST_ENTITY_ID);
        verify(chatDao).delete(FIRST_ENTITY_ID);
    }

    @Test(expected = ChatNotFoundException.class)
    public void testDeleteWithNotCorrectId() throws Exception {
        chatService.delete(INCORRECT_ID);
    }

    @Test
    public void testFindAll() throws Exception {
        assertEquals(CHATS, chatService.findAll());
    }
}