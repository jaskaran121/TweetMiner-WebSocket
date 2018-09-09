
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
/*1.2*/import java.util.concurrent.CompletableFuture

object hashtag extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template2[CompletableFuture[List[twitter4j.Status]],String,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(tweets:CompletableFuture[List[twitter4j.Status]],searchkeyword:String):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.73*/("""

"""),format.raw/*4.1*/("""<html>
<body>
<h2>"""),_display_(/*6.6*/tweets/*6.12*/.get.size()),format.raw/*6.23*/(""" """),format.raw/*6.24*/("""Results searched for the selected hashtag :-</b>#"""),_display_(/*6.74*/searchkeyword),format.raw/*6.87*/("""</h2>
"""),_display_(/*7.2*/for(tweet<-tweets.get) yield /*7.24*/{_display_(Seq[Any](format.raw/*7.25*/("""
"""),format.raw/*8.1*/("""<p><b>Tweet:-</b>"""),_display_(/*8.19*/tweet/*8.24*/.getText),format.raw/*8.32*/("""</p>

<p>------------------------------------------------------------------------------------------------------------------------------------------------</P>
""")))}),format.raw/*11.2*/("""
"""),format.raw/*12.1*/("""</body>
</html>"""))
      }
    }
  }

  def render(tweets:CompletableFuture[List[twitter4j.Status]],searchkeyword:String): play.twirl.api.HtmlFormat.Appendable = apply(tweets,searchkeyword)

  def f:((CompletableFuture[List[twitter4j.Status]],String) => play.twirl.api.HtmlFormat.Appendable) = (tweets,searchkeyword) => apply(tweets,searchkeyword)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Sun Sep 09 17:53:00 EDT 2018
                  SOURCE: C:/Users/JASKARN SINGH/Desktop/Assignement-2Updated/Soen6441Assignment2/app/views/hashtag.scala.html
                  HASH: d0457dcd957c6bb6ed8e049af0160f8f79b47c19
                  MATRIX: 651->1|1045->49|1211->120|1241->124|1287->145|1301->151|1332->162|1360->163|1436->213|1469->226|1502->234|1539->256|1577->257|1605->259|1649->277|1662->282|1690->290|1882->452|1911->454
                  LINES: 24->1|29->2|34->2|36->4|38->6|38->6|38->6|38->6|38->6|38->6|39->7|39->7|39->7|40->8|40->8|40->8|40->8|43->11|44->12
                  -- GENERATED --
              */
          