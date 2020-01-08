package com.personal.ibonny.springdemo

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Component

import org.bson.types.ObjectId

import org.springframework.boot.CommandLineRunner
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import org.springframework.data.mongodb.repository.MongoRepository

import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.repository.query.Param

@Controller
class MyController(var myBean: MyBean, var sb: SecondBean) {
    @RequestMapping("/")
    @ResponseBody
    fun index(): String {
        myBean.testoutfunc("This is a test.")
        
        return sb.doit("Is this working?")
    }
}

@Component
class SecondBean {
    fun doit(str: String): String {
        return str + ": OK, we got here."
    }
}

/**
 * poko - plain old kotlin object ;).
 *
 * defines the structure for our document entries.
 */
@Document(collection = "test")
class ConfigurationDocument(@Id val id: ObjectId, @Field val name: String)

/**
 * simple mongo repository.
 *
 * provides the main CRUD operations.
 */
@RepositoryRestResource(path = "docs")
interface ConfigurationDocumentRepository : MongoRepository<ConfigurationDocument, ObjectId> {
    fun findAllByName(@Param("name") name: String): List<ConfigurationDocument>
}

/**
 * simple class command line runner impl.
 *
 * will be executed on application startup and create 2 entries.
 *
 * keep in mind, that this method will be executed on each app start and may pollute/spam your database;
 * you usually only want to use this with embedded databases.
 */
@Component
class SampleDataCLR(val repository: ConfigurationDocumentRepository) : CommandLineRunner {
 override fun run(vararg args: String?) {
   repository.insert(arrayListOf(
       ConfigurationDocument(ObjectId.get(), "this"),
       ConfigurationDocument(ObjectId.get(), "is"),
       ConfigurationDocument(ObjectId.get(), "wonderful")
   ))
 }
}