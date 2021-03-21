package com.assignment.repository;

import com.assignment.model.entity.ConfigData;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ConfigDataRepositoryTest {
    @Autowired
    private ConfigDataRepository configDataRepository;

    @Test
    public void findConfigByAppCodeAndVersion() {
        ConfigData result = configDataRepository.findConfigByAppCodeAndVersion("APP01", "1");
        Assert.assertEquals("885a45efff249a0321eac3c9ed25016f", result.getHashCode());
    }

    @Test
    public void findConfigByAppCode() {
        List<ConfigData> configList = configDataRepository.findConfigByAppCode("APP01");
        Assert.assertEquals(2, configList.size());
    }

    @Test
    public void save() {
        String jsonConf = "{\"autoStart\":\"false\",\"Cron\":\"Monthly\",\"Notice\":\"Email\"}";
        ConfigData configData = new ConfigData();
        configData.setAppCode("App03");
        configData.setVersion("1");
        configData.setJsonConf(jsonConf);
        configData.setCreationTime(new Date());
        configData.setHashCode(DigestUtils.md5Hex(jsonConf));
        ConfigData result = configDataRepository.save(configData);
        Assert.assertNotNull(result);
    }

    @Test
    @Transactional
    public void updateConfigByAppCodeAndVersion() {
        int result = configDataRepository.updateConfigByAppCodeAndVersion("APP02", "1", "{\"name\":\"NewApp\",\"host\":\"www.two.com\"}", new Date(),"99ddb86de77f3a3ea5c7037b0af01a68");
        Assert.assertEquals(1, result);
    }

}
