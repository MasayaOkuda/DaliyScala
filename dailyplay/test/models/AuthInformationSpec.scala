package models

import org.scalatest._
import scalikejdbc.scalatest.AutoRollback
import scalikejdbc._


class AuthInformationSpec extends fixture.FlatSpec with Matchers with AutoRollback {
  val ai = AuthInformation.syntax("ai")

  behavior of "AuthInformation"

  it should "find by primary keys" in { implicit session =>
    val maybeFound = AuthInformation.find("MyString")
    maybeFound.isDefined should be(true)
  }
  it should "find by where clauses" in { implicit session =>
    val maybeFound = AuthInformation.findBy(sqls.eq(ai.userEmail, "MyString"))
    maybeFound.isDefined should be(true)
  }
  it should "find all records" in { implicit session =>
    val allResults = AuthInformation.findAll()
    allResults.size should be >(0)
  }
  it should "count all records" in { implicit session =>
    val count = AuthInformation.countAll()
    count should be >(0L)
  }
  it should "find all by where clauses" in { implicit session =>
    val results = AuthInformation.findAllBy(sqls.eq(ai.userEmail, "MyString"))
    results.size should be >(0)
  }
  it should "count by where clauses" in { implicit session =>
    val count = AuthInformation.countBy(sqls.eq(ai.userEmail, "MyString"))
    count should be >(0L)
  }
  it should "create new record" in { implicit session =>
    val created = AuthInformation.create(userId = 123, userEmail = "MyString", userPassword = "MyString")
    created should not be(null)
  }
  it should "save a record" in { implicit session =>
    val entity = AuthInformation.findAll().head
    // TODO modify something
    val modified = entity
    val updated = AuthInformation.save(modified)
    updated should not equal(entity)
  }
  it should "destroy a record" in { implicit session =>
    val entity = AuthInformation.findAll().head
    val deleted = AuthInformation.destroy(entity)
    deleted should be(1)
    val shouldBeNone = AuthInformation.find("MyString")
    shouldBeNone.isDefined should be(false)
  }
  it should "perform batch insert" in { implicit session =>
    val entities = AuthInformation.findAll()
    entities.foreach(e => AuthInformation.destroy(e))
    val batchInserted = AuthInformation.batchInsert(entities)
    batchInserted.size should be >(0)
  }
}
