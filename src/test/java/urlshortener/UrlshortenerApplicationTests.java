package urlshortener;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import urlshortener.controller.MainController;
import urlshortener.repository.URLsRepository;
import urlshortener.service.URLConverterService;
import urlshortener.service.URLManageService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UrlshortenerApplicationTests {

    @Autowired
    private MainController mainController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private URLConverterService urlConverterService;

    @MockBean
    private URLManageService urlManageService;

    @MockBean
    private URLsRepository urLsRepository;

    @Test
    void contextLoads() throws Exception {
        assertThat(mainController).isNotNull();
    }

    @Test
    void whenNoTag_thenReturnIndexView() throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("index"));
    }

    @Test
    void whenIndexUri_thenReturnIndexView() throws Exception {
        mockMvc.perform(get("/index")).andExpect(view().name("index"));
    }

    @Test
    void whenMakeShortTag_thenReturnIndexView() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("http://localhost:8080/makeshort/example.com"))
                .andExpect(view().name("index")).andReturn();
    }

    @Test
    void whenShowAllTag_thenReturnIndexView() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("http://localhost:8080/showall"))
                .andExpect(view().name("index")).andReturn();
    }

    @Test
    void whenDeleteIdTag_thenReturnRedirectIndexView() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("http://localhost:8080/delete/{id}", "1"))
                .andExpect(view().name("redirect:/index")).andReturn();
    }

    @Test
    void whenDeleteAllTag_thenReturnRedirectIndexView() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("http://localhost:8080/deleteall"))
                .andExpect(view().name("redirect:/index")).andReturn();
    }




}
