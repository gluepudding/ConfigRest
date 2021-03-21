package com.assignment.service.impl;

import com.assignment.model.entity.ConfigData;
import com.assignment.service.ConfigService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigServiceImplTest {
    @Autowired
    private ConfigService configService;

    @Test
    public void getConfigByAppCodeAndVersion() {
        ConfigData result = configService.getConfigByAppCodeAndVersion("APP01", "1");
        Assert.assertEquals("885a45efff249a0321eac3c9ed25016f", result.getHashCode());
    }

    @Test
    public void getConfigByAppCode() {
        List<ConfigData> configList = configService.getConfigByAppCode("APP01");
        Assert.assertEquals(2, configList.size());
    }

    @Test
    public void addOrUpdateConfig() {
        //Add
        String jsonConf = "{\"autoStart\":\"false\",\"Cron\":\"Monthly\",\"Notice\":\"Email\"}";
        ConfigData resultAdd = configService.addOrUpdateConfig("App03", "1", jsonConf, "");
        Assert.assertEquals("App03", resultAdd.getAppCode());

        //Update
        ConfigData resultUpdate = configService.addOrUpdateConfig("App01", "1", jsonConf, "885a45efff249a0321eac3c9ed25016f");
        Assert.assertEquals("App01", resultUpdate.getAppCode());
    }
}