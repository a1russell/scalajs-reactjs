package io.github.shogowada.scalajs.reactjs

import io.github.shogowada.scalajs.reactjs.classes.specs.ReactClassSpec
import io.github.shogowada.scalajs.reactjs.elements.ReactElement
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object ReactDOM {
  def render(reactClassSpec: ReactClassSpec, node: dom.Node): Unit = {
    render(React.createElement(reactClassSpec), node)
  }

  def render(reactClassSpec: ReactClassSpec, props: Any, node: dom.Node): Unit = {
    render(React.createElement(reactClassSpec, props), node)
  }

  def render(element: ReactElement, node: dom.Node): Unit = {
    NativeReactDOM.render(element, node)
  }
}

@js.native
@JSImport("react-dom", JSImport.Namespace)
object NativeReactDOM extends js.Object {

  def render(element: ReactElement, node: dom.Node): Unit = js.native
}
