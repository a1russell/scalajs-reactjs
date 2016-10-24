package io.github.shogowada.scalajs.reactjs.example.helloworld

import io.github.shogowada.scalajs.reactjs2.VirtualDOM._
import io.github.shogowada.scalajs.reactjs2.upstream._
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js.Any.fromFunction1
import scala.scalajs.js.annotation.JSExport

@JSExport
object Main2 {

  case class Props0(name: String) extends scala.scalajs.js.Object

  val hw0: ReactCtor = fromFunction1[Props, ReactComponent](new HelloWorld(_))

  class HelloWorld(p0: Props0) extends ReactComponent {

    override def render() = <.div()(s"Hello, ${p0.name}!")

    val c0: (Props) ⇒ HelloWorld = this _

    val c: (Props) ⇒ ReactComponent = c0

//    val rc: ReactCtor = (this _)

//    override val constructor: ReactCtor = fromFunction1[Props, ReactComponent](c)
    override val constructor: ReactCtor = hw0

    override def props: PropsWithChildren = ???

    override def state: State = ???
  }

  @JSExport
  def main(element: HTMLElement) = {

    val helloWorld: ReactElement = React.createElement(hw0, Props0("Bob"))
    ReactDOM.render(helloWorld, element)
  }
}
