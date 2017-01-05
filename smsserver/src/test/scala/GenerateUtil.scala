/**
  * Created by fangzhongwei on 2016/10/24.
  */
object GenerateUtil extends App{
  slick.codegen.SourceCodeGenerator.main(
      Array("slick.jdbc.MySQLProfile", "com.mysql.jdbc.Driver", "jdbc:mysql://localhost/notify", "notify", "com.lawsofnature.sms.repo", "root", "123456")
  )
}
