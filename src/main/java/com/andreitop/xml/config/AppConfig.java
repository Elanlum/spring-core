package com.andreitop.xml.config;

import com.andreitop.xml.mount.Mount;
import com.andreitop.xml.mount.Tiger;
import com.andreitop.xml.mount.Wolf;
import com.andreitop.xml.unit.Human;
import com.andreitop.xml.unit.Orc;
import com.andreitop.xml.unit.Troll;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
@PropertySource("classpath:config/heroes.properties")
public class AppConfig {

    @Value("${character.created}")
    public String created;

    @Bean
    public Wolf frostWolf() {
        return new Wolf();
    }

    @Bean
    public Tiger shadowTiger() {
        return new Tiger();
    }

    @Bean
    public Human knight() {
        return new Human(shadowTiger(), "thunderFury", "soulBlade");
    }

    @Bean
    public Orc trall() {
        Orc orc = new Orc(frostWolf());
        orc.setColorCode(9);
        orc.setWeapon("furryAxe");
        return orc;
    }

    @Bean
    public SimpleDateFormat dateFormatter() {
        return new SimpleDateFormat("dd/mm/yyyy");
    }

    @Bean
    public Map<String, Mount> trollMountMap() {
        Map<String, Mount> trollMap = new HashMap<>();
        trollMap.put("m1", frostWolf());
        trollMap.put("m2", shadowTiger());
        return trollMap;
    }

    @Bean
    public Set<Mount> trollMountSet() {
        Set<Mount> trollMountSet = new HashSet<>();
        trollMountSet.add(frostWolf());
        trollMountSet.add(shadowTiger());
        return trollMountSet;
    }

    @Bean
    public Troll zulJin() throws ParseException {
        Troll zulJin = new Troll();
        zulJin.setColorCode(ThreadLocalRandom.current().nextInt(1, 9));
        zulJin.setCreationDate(dateFormatter().parse(created));
        zulJin.setListOfMounts(Arrays.asList(Troll.DEFAULT_MOUNT, null, shadowTiger()));
        zulJin.setSetOfMounts(trollMountSet());
        zulJin.setMapOfMounts(trollMountMap());
        return zulJin;
    }
}
