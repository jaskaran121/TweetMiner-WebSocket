
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
/*1.2*/import java.util.concurrent.CompletableFuture;

object location extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template3[CompletableFuture[List[String]],Double,Double,play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*2.2*/(tweets:CompletableFuture[List[String]],latitude:Double,longitude:Double):play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.75*/("""

"""),format.raw/*4.1*/("""<html>
<body>
<h2>"""),_display_(/*6.6*/tweets/*6.12*/.get.size()),format.raw/*6.23*/(""" """),format.raw/*6.24*/("""Results searched for the selected location :- """),format.raw/*6.70*/("""{"""),_display_(/*6.72*/latitude),format.raw/*6.80*/(""","""),_display_(/*6.82*/longitude),format.raw/*6.91*/("""}"""),format.raw/*6.92*/("""</h2>

"""),_display_(/*8.2*/for(tweet<-tweets.get) yield /*8.24*/{_display_(Seq[Any](format.raw/*8.25*/("""
"""),format.raw/*9.1*/("""<p>Tweet:-"""),_display_(/*9.12*/tweet),format.raw/*9.17*/("""</p>

<p>------------------------------------------------------------------------------------------------------------------------------------------------</P>
""")))}),format.raw/*12.2*/("""

"""),format.raw/*14.1*/("""</body>
</html>"""))
      }
    }
  }

  def render(tweets:CompletableFuture[List[String]],latitude:Double,longitude:Double): play.twirl.api.HtmlFormat.Appendable = apply(tweets,latitude,longitude)

  def f:((CompletableFuture[List[String]],Double,Double) => play.twirl.api.HtmlFormat.Appendable) = (tweets,latitude,longitude) => apply(tweets,latitude,longitude)

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  DATE: Mon Aug 13 20:26:07 EDT 2018
                  SOURCE: C:/Users/JASKARN SINGH/Desktop/Assignement-2Updated/Soen6441Assignment2/app/views/location.scala.html
                  HASH: 2de25ef61f60267b5e9651900a04adcf3eb62697
                  MATRIX: 651->1|1044->50|1212->123|1242->127|1288->148|1302->154|1333->165|1361->166|1434->212|1462->214|1490->222|1518->224|1547->233|1575->234|1610->244|1647->266|1685->267|1713->269|1750->280|1775->285|1967->447|1998->451
                  LINES: 24->1|29->2|34->2|36->4|38->6|38->6|38->6|38->6|38->6|38->6|38->6|38->6|38->6|38->6|40->8|40->8|40->8|41->9|41->9|41->9|44->12|46->14
                  -- GENERATED --
              */
          