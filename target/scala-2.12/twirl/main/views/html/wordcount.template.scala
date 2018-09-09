
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import scala.collection.JavaConverters._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import play.data._
import play.core.j.PlayFormsMagicForJava._
/*2.2*/import java.util.concurrent.CompletableFuture

object wordcount extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template1[CompletableFuture[LinkedHashMap[String, Long]],play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*3.2*/(tweets:CompletableFuture[LinkedHashMap[String,Long]]):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*3.56*/("""

"""),format.raw/*5.1*/("""<html>
 <title>Word Count Static</title>
 
<body>
<h2>Word Level Statistics Table</h2>
"""),_display_(/*10.2*/for((word,count)<-tweets.get) yield /*10.31*/{_display_(Seq[Any](format.raw/*10.32*/("""
"""),format.raw/*11.1*/("""<p>"""),_display_(/*11.5*/word),format.raw/*11.9*/(""" """),format.raw/*11.10*/("""- """),_display_(/*11.13*/count),format.raw/*11.18*/("""</p>
""")))}),format.raw/*12.2*/("""
	
"""),format.raw/*14.1*/("""</body>
</html>"""))
      }
    }
  }

  def render(tweets:CompletableFuture[LinkedHashMap[String, Long]]): play.twirl.api.HtmlFormat.Appendable = apply(tweets)

  def f:((CompletableFuture[LinkedHashMap[String, Long]]) => play.twirl.api.HtmlFormat.Appendable) = (tweets) => apply(tweets)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Aug 13 20:26:07 EDT 2018
                  SOURCE: C:/Users/JASKARN SINGH/Desktop/Assignement-2Updated/Soen6441Assignment2/app/views/wordcount.scala.html
                  HASH: 836bdbf2f195f6d24dbd050bcb6d3bb8f3ca72bb
                  MATRIX: 651->3|1045->51|1194->105|1224->109|1343->202|1388->231|1427->232|1456->234|1486->238|1510->242|1539->243|1569->246|1595->251|1632->258|1664->263
                  LINES: 24->2|29->3|34->3|36->5|41->10|41->10|41->10|42->11|42->11|42->11|42->11|42->11|42->11|43->12|45->14
                  -- GENERATED --
              */
          