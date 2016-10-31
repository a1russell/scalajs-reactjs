package io.github.shogowada.scalajs.reactjs

import io.github.shogowada.scalajs.reactjs.classes.specs.ReactClassSpec
import io.github.shogowada.scalajs.reactjs.elements.{ReactElement, ReactHTMLElement}
import io.github.shogowada.statictags._

import scala.language.implicitConversions
import scala.scalajs.js
import scala.scalajs.js.JSConverters._

trait VirtualDOM extends StaticTags {

  class VirtualDOMElements extends Elements {

    case class ReactElementSpec() {
      def apply(spec: ReactClassSpec, props: Any): ReactElement = {
        React.createElement(spec, props)
      }
    }

    lazy val reactElement = ReactElementSpec()
  }

  class VirtualDOMAttributes extends Attributes {

    case class OnChangeAttributeSpec(name: String) extends AttributeSpec {
      def :=(callback: js.Function0[Unit]) = {
        Attribute[js.Function0[Unit]](name = name, value = callback)
      }
    }

    case class RefAttributeSpec(name: String) extends AttributeSpec {
      def :=[T <: ReactHTMLElement](callback: js.Function1[T, Unit]) = {
        Attribute[js.Function1[T, Unit]](name = name, value = callback)
      }
    }

    lazy val htmlFor = ForAttributeSpec("htmlFor")
    override lazy val `for` = htmlFor
    lazy val onChange = OnChangeAttributeSpec("onChange")
    lazy val ref = RefAttributeSpec("ref")
  }

  override val < = new VirtualDOMElements()
  override val ^ = new VirtualDOMAttributes()

  implicit def staticTagsToVirtualDoms(element: Element): ReactElement = {
    React.createElement(
      element.name,
      staticTagsAttributesToReactAttributes(element.attributes),
      staticTagsElementsToReactElements(element.flattenedContents): _*
    )
  }

  private def staticTagsAttributesToReactAttributes(attributes: Iterable[Attribute[_]]): js.Dictionary[Any] = {
    attributes.map(attribute => (attribute.name, attribute.value))
        .toMap
        .toJSDictionary
  }

  private def staticTagsElementsToReactElements(contents: Seq[Any]): Seq[js.Any] = {
    contents.map(staticTagsElementToReactElement)
  }

  private def staticTagsElementToReactElement(content: Any): js.Any = {
    content match {
      case element@Element(_, _, _, _) => staticTagsToVirtualDoms(element)
      case _ => content.asInstanceOf[js.Any]
    }
  }
}

object VirtualDOM extends VirtualDOM
