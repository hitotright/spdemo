package top.hitotright.spdemo.api;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserControllerTest {
    private MockMvc mvc;
    private RequestBuilder request = null;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(new UserController()).build();
    }

    @Test(timeout = 1000)
    public void aGetList() throws Exception {
        request = MockMvcRequestBuilders.get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk()) // 期待返回状态吗码200
                .andExpect(content().string(equalTo("[]")))
                .andDo(print()); // 打印返回的 http response 信息
    }

    @Test
    public void bPostUser() throws Exception {
        request = MockMvcRequestBuilders.post("/users/")
                .param("id", "1")
                .param("name", "测试大师")
                .param("age", "20");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));
    }

    @Test
    public void cGetUser() throws Exception {
        request = MockMvcRequestBuilders.get("/users/");
        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo("[{\"id\":1,\"name\":\"测试大师\",\"age\":20}]")));
    }

    @Test
    public void dPutUser() throws Exception {
        request = MockMvcRequestBuilders.put("/users/1")
                .param("name", "测试终极大师")
                .param("age", "30");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

    }

    @Test
    public void eEeleteUser() throws Exception {
        request = MockMvcRequestBuilders.delete("/users/1");
        mvc.perform(request)
                .andExpect(content().string(equalTo("success")));

    }
}