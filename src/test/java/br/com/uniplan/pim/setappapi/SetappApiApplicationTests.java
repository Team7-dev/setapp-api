package br.com.uniplan.pim.setappapi;

import br.com.uniplan.pim.setappapi.controller.VisitanteController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
class ChallengeApiApplicationTests {

    @Autowired
    private VisitanteController visitanteController;

    @Test
    public void contextLoads() {
        assertThat(visitanteController).isNotNull();
    }

}
