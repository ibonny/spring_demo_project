package guru.springframework.config

import java.io.IOException
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.*
import com.mongodb.MongoClient

@Configuration
class MongoConfig {
    val MONGO_DB_URL = "localhost"
    val MONGO_DB_NAME = "embeded_db"

    @Bean
    fun mongoTemplate(): MongoTemplate {
        val mongo = EmbeddedMongoFactoryBean()

        mongo.setBindIp(MONGO_DB_URL)

        val mongoClient = mongo.getObject()

        val mongoTemplate = MongoTemplate(mongoClient!!, MONGO_DB_NAME)

        return mongoTemplate
    }
}