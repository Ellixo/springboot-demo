package com.ellixo.blog;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.IMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Profile("test")
public class MongoDBEmbed {

    private MongodExecutable mongodExecutable;

    @PostConstruct
    public void start() {
        MongodStarter starter = MongodStarter.getDefaultInstance();

        try {
            IMongodConfig mongodConfig = new MongodConfigBuilder()
                    .version(Version.Main.PRODUCTION)
                    .net(new Net(27017,false))
                    .build();
            mongodExecutable = starter.prepare(mongodConfig);
            mongodExecutable.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void stop() {
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }

}
