package models

import scalikejdbc._

case class AuthInformation(
  userId: Int,
  userEmail: String,
  userPassword: String) {

  def save()(implicit session: DBSession = AuthInformation.autoSession): AuthInformation = AuthInformation.save(this)(session)

  def destroy()(implicit session: DBSession = AuthInformation.autoSession): Int = AuthInformation.destroy(this)(session)

}


object AuthInformation extends SQLSyntaxSupport[AuthInformation] {

  override val tableName = "auth_information"

  override val columns = Seq("user_id", "user_email", "user_password")

  def apply(ai: SyntaxProvider[AuthInformation])(rs: WrappedResultSet): AuthInformation = apply(ai.resultName)(rs)
  def apply(ai: ResultName[AuthInformation])(rs: WrappedResultSet): AuthInformation = new AuthInformation(
    userId = rs.get(ai.userId),
    userEmail = rs.get(ai.userEmail),
    userPassword = rs.get(ai.userPassword)
  )

  val ai = AuthInformation.syntax("ai")

  override val autoSession = AutoSession

  def find(userEmail: String)(implicit session: DBSession = autoSession): Option[AuthInformation] = {
    withSQL {
      select.from(AuthInformation as ai).where.eq(ai.userEmail, userEmail)
    }.map(AuthInformation(ai.resultName)).single.apply()
  }

  def findAll()(implicit session: DBSession = autoSession): List[AuthInformation] = {
    withSQL(select.from(AuthInformation as ai)).map(AuthInformation(ai.resultName)).list.apply()
  }

  def countAll()(implicit session: DBSession = autoSession): Long = {
    withSQL(select(sqls.count).from(AuthInformation as ai)).map(rs => rs.long(1)).single.apply().get
  }

  def findBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Option[AuthInformation] = {
    withSQL {
      select.from(AuthInformation as ai).where.append(where)
    }.map(AuthInformation(ai.resultName)).single.apply()
  }

  def findAllBy(where: SQLSyntax)(implicit session: DBSession = autoSession): List[AuthInformation] = {
    withSQL {
      select.from(AuthInformation as ai).where.append(where)
    }.map(AuthInformation(ai.resultName)).list.apply()
  }

  def countBy(where: SQLSyntax)(implicit session: DBSession = autoSession): Long = {
    withSQL {
      select(sqls.count).from(AuthInformation as ai).where.append(where)
    }.map(_.long(1)).single.apply().get
  }

  def create(
    userId: Int,
    userEmail: String,
    userPassword: String)(implicit session: DBSession = autoSession): AuthInformation = {
    withSQL {
      insert.into(AuthInformation).namedValues(
        column.userId -> userId,
        column.userEmail -> userEmail,
        column.userPassword -> userPassword
      )
    }.update.apply()

    AuthInformation(
      userId = userId,
      userEmail = userEmail,
      userPassword = userPassword)
  }

  def batchInsert(entities: collection.Seq[AuthInformation])(implicit session: DBSession = autoSession): List[Int] = {
    val params: collection.Seq[Seq[(Symbol, Any)]] = entities.map(entity =>
      Seq(
        Symbol("userId") -> entity.userId,
        Symbol("userEmail") -> entity.userEmail,
        Symbol("userPassword") -> entity.userPassword))
    SQL("""insert into auth_information(
      user_id,
      user_email,
      user_password
    ) values (
      {userId},
      {userEmail},
      {userPassword}
    )""").batchByName(params.toSeq: _*).apply[List]()
  }

  def save(entity: AuthInformation)(implicit session: DBSession = autoSession): AuthInformation = {
    withSQL {
      update(AuthInformation).set(
        column.userId -> entity.userId,
        column.userEmail -> entity.userEmail,
        column.userPassword -> entity.userPassword
      ).where.eq(column.userEmail, entity.userEmail)
    }.update.apply()
    entity
  }

  def destroy(entity: AuthInformation)(implicit session: DBSession = autoSession): Int = {
    withSQL { delete.from(AuthInformation).where.eq(column.userEmail, entity.userEmail) }.update.apply()
  }

}
