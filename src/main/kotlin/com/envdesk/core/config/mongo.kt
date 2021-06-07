package com.envdesk.core.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import java.util.*
import java.util.logging.Logger

@Configuration
class MongoConfiguration @Autowired constructor(val p: Properties) : AbstractMongoClientConfiguration() {

    val logger: Logger = Logger.getLogger(MongoConfiguration::class.simpleName);

    override fun getDatabaseName(): String {
        return "env"
    }

    override fun mongoClient(): MongoClient {
        val connectionString = ConnectionString(p.db);
        val settings = MongoClientSettings.builder().applyConnectionString(connectionString).build()
        return MongoClients.create(settings)
    }

    override fun getMappingBasePackages(): MutableCollection<String> {
        return Collections.singleton("com.envdesk.core.models")
    }

    override fun autoIndexCreation(): Boolean {
        return true;
    }
}