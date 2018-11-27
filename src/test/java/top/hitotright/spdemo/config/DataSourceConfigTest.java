package top.hitotright.spdemo.config;

import javafx.application.Application;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceConfigTest {

    @Autowired
    @Qualifier(value = "primaryJdbcTemplate")
    private JdbcTemplate primaryJdbcTemplate;

    @Autowired
    @Qualifier(value = "secondaryJdbcTemplate")
    private JdbcTemplate secondaryJdbcTemplate;

    @Before
    public void setUp() {
        primaryJdbcTemplate.execute("TRUNCATE `user` ");
    }

    @Test
    public void test() throws Exception {

        // 往第一个数据源中插入两条数据
        primaryJdbcTemplate.update("insert into `user`(id,`name`,age) values(?, ?, ?)", 1, "aaa", 20);
        primaryJdbcTemplate.update("insert into `user`(id,`name`,age) values(?, ?, ?)", 2, "bbb", 30);

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("2", primaryJdbcTemplate.queryForObject("select count(*) from `user`", String.class));

        // 往第二个数据源中插入一条数据，若插入的是第一个数据源，则会主键冲突报错
        secondaryJdbcTemplate.update("insert into `user`(id,`name`,age) values(?, ?, ?)", 3, "aaa", 20);

        // 查一下第一个数据源中是否有两条数据，验证插入是否成功
        Assert.assertEquals("3", secondaryJdbcTemplate.queryForObject("select count(*) from `user`", String.class));

    }
}