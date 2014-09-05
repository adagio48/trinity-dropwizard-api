package org.trinity

import com.mongodb.DB
import com.mongodb.Mongo
import com.yammer.dropwizard.Service
import com.yammer.dropwizard.config.Bootstrap
import com.yammer.dropwizard.config.Environment
import org.mongojack.JacksonDBCollection
import org.trinity.representations.Content
import org.trinity.resources.ContentResource

class TrinityService extends Service<TrinityConfiguration> {
    public static void main(String[] args) throws Exception {
        new TrinityService().run(args)
    }

    @Override
    public void initialize(Bootstrap<TrinityConfiguration> bootstrap) {
        bootstrap.setName("Trinity");
    }

    @Override
    public void run(TrinityConfiguration configuration,
                    Environment environment) throws ClassNotFoundException {
        MongoConfiguration mongoConfig = configuration.mongoConfiguration
        Mongo mongo = new Mongo(mongoConfig.host, mongoConfig.port)
        DB db = mongo.getDB(mongoConfig.db)
        db.authenticate(mongoConfig.user, mongoConfig.password.toCharArray())

        JacksonDBCollection<Content, String> contets = JacksonDBCollection.wrap(db.getCollection("content"), Content.class, String.class)
        MongoManaged mongoManaged = new MongoManaged(mongo)

        environment.manage(mongoManaged)
        environment.addResource(new ContentResource(contets))
    }
}
