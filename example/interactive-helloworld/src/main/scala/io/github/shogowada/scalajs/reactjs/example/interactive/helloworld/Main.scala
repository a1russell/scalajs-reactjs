package io.github.shogowada.scalajs.reactjs.example.interactive.helloworld

import io.github.shogowada.scalajs.reactjs.classes.specs.{ReactClassSpec, StatelessReactClassSpec}
import io.github.shogowada.scalajs.reactjs.elements.{ReactElement, ReactHTMLInputElement, ReactHTMLRadioElement}
import io.github.shogowada.scalajs.reactjs.example.interactive.helloworld.LetterCase.{DEFAULT, LOWER_CASE, LetterCase, UPPER_CASE}
import io.github.shogowada.scalajs.reactjs.{ReactDOM, VirtualDOM}
import org.scalajs.dom.raw.HTMLElement

import scala.scalajs.js.annotation.JSExport

object LetterCase {

  sealed class LetterCase(val name: String)

  case object DEFAULT extends LetterCase("Default")

  case object LOWER_CASE extends LetterCase("Lower Case")

  case object UPPER_CASE extends LetterCase("Upper Case")

  val ALL = Seq(DEFAULT, LOWER_CASE, UPPER_CASE)
}

case class LetterCaseRadioBoxSpecProps(letterCase: LetterCase, checked: Boolean, onChecked: () => Unit)

class LetterCaseRadioBoxSpec extends StatelessReactClassSpec with VirtualDOM {

  override type Props = LetterCaseRadioBoxSpecProps

  var letterCaseElement: ReactHTMLRadioElement = _

  override def render(): ReactElement = {
    <.span()(
      <.input(
        ^.`type` := "radio",
        ^.name := "letter-case",
        ^.value := props.letterCase.name,
        ^.ref := ((element: ReactHTMLRadioElement) => {
          letterCaseElement = element
        }),
        ^.checked := props.checked,
        ^.onChange := onChange
      )(),
      props.letterCase.name
    )
  }

  val onChange = () => {
    if (letterCaseElement.checked) {
      props.onChecked()
    }
  }
}

class InteractiveHelloWorldSpec extends ReactClassSpec with VirtualDOM {

  case class State(name: String, letterCase: LetterCase)

  override def getInitialState(): State = State(
    name = "whoever you are",
    letterCase = DEFAULT
  )

  val nameId = "name"
  var nameElement: ReactHTMLInputElement = _

  override def render() = {
    <.div()(
      createNameInput(),
      LetterCase.ALL.map(createLetterCaseRadioBox),
      <.br.empty,
      <.div(^.id := "greet")(s"Hello, ${name(state)}!")
    )
  }

  def createNameInput() = {
    <.div()(
      <.label(^.`for` := nameId)("Name: "),
      <.input(
        ^.id := nameId,
        ^.ref := ((element: ReactHTMLInputElement) => {
          nameElement = element
        }),
        ^.value := state.name,
        ^.onChange := onChange
      )()
    )
  }

  def createLetterCaseRadioBox(thisLetterCase: LetterCase): ReactElement = {
    <.reactElement(
      new LetterCaseRadioBoxSpec(),
      LetterCaseRadioBoxSpecProps(
        letterCase = thisLetterCase,
        checked = thisLetterCase == state.letterCase,
        onChecked = () => {
          setState(state.copy(letterCase = thisLetterCase))
        }
      )
    )
  }

  val onChange = () => {
    setState(state.copy(name = nameElement.value))
  }

  def name(state: State): String = {
    state.letterCase match {
      case LOWER_CASE => state.name.toLowerCase
      case UPPER_CASE => state.name.toUpperCase
      case _ => state.name
    }
  }
}

@JSExport
object Main {
  @JSExport
  def main(element: HTMLElement): Unit = {
    ReactDOM.render(new InteractiveHelloWorldSpec(), element)
  }
}
