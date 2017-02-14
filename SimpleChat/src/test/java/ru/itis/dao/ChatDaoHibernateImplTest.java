package ru.itis.dao;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.itis.dao.hibernate.ChatDaoHibernateImpl;
import ru.itis.models.Chat;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestDaoConfig.class)
public class ChatDaoHibernateImplTest {

    @Autowired
    ChatDaoHibernateImpl chatDaoHibernate;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testFindWithCorrectId() throws Exception {

    }

    @Test
    public void save() throws Exception {

    }

    @Test
    public void delete() throws Exception {

    }

    @Test
    public void findAll() throws Exception {

    }

}