addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.8.0")
addSbtPlugin("org.foundweekends.giter8" % "sbt-giter8-scaffold" % "0.11.0")

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.29"
addSbtPlugin("org.scalikejdbc" %% "scalikejdbc-mapper-generator" % "3.4.0")
