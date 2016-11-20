package io.github.shogowada.scalajs.reactjs2.upstream

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.|

@js.native
object React extends React

@js.native
trait React extends js.Object {

  def createClass(spec: ReactComponentSpec): ReactClass = js.native

  //  def createFactory[P,S,B,N <: TopNode](t: ReactClass[P,S,B,N]): ReactComponentCU[P,S,B,N] = js.native
  //  def createFactory[P <: js.Any, S <: js.Any, N <: TopNode](t: JsComponentType[P, S, N]): JsComponentC[P, S, N] = js.native

  def createElement(`type`: String                                            ): ReactDOMElement = js.native
  def createElement(`type`: String, props: js.Object                          ): ReactDOMElement = js.native
  def createElement(`type`: String, props: js.Object, children: ReactNodeList*): ReactDOMElement = js.native

  def createElement(`type`: ReactCtor                                            ): ReactComponentElement = js.native
  def createElement(`type`: ReactCtor, props: js.Object                          ): ReactComponentElement = js.native
  def createElement(`type`: ReactCtor, props: js.Object, children: ReactNodeList*): ReactComponentElement = js.native

  //  /** Verifies the object is a ReactElement. */
  //  def isValidElement(o: JAny): Boolean = js.native

  /** React.Children provides utilities for dealing with the this.props.children opaque data structure. */
  val Children: ReactChildren = js.native
}

/** `React.Children` */
@js.native
trait ReactChildren extends js.Object {

  final type MapFn = js.Function1[ReactNode, js.Any] | js.Function2[ReactNode, Int, js.Any]

  /** Invoke fn on every immediate child contained within children with this set to context. If children is a nested object or array it will be traversed: fn will never be passed the container objects. If children is null or undefined returns null or undefined rather than an empty object. */
  def map(c: PropsChildren, fn: MapFn): js.UndefOr[js.Object] = js.native

  /** Like React.Children.map() but does not return an object. */
  def forEach(c: PropsChildren, fn: MapFn): Unit = js.native

  /** Return the only child in children. Throws otherwise. */
  def only(c: PropsChildren): ReactNode = js.native

  /** Return the total number of components in children, equal to the number of times that a callback passed to map or forEach would be invoked. */
  def count(c: PropsChildren): Int = js.native

  /** Return the children opaque data structure as a flat array with keys assigned to each child. Useful if you want to manipulate collections of children in your render methods, especially if you want to reorder or slice this.props.children before passing it down. */
  def toArray(c: PropsChildren): js.Array[ReactNode] = js.native
}

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

@js.native
object ReactDOM extends ReactDOM

@js.native
trait ReactDOM extends js.Object {

  final type Container = dom.html.Element | dom.svg.Element

  def render(element  : ReactElement,
             container: Container,
             callback : js.Function0[Unit] = js.native): ReactComponent = js.native

  def unmountComponentAtNode(container: dom.Node): Boolean = js.native

  def findDOMNode(component: ReactComponent): dom.Element = js.native
}

// ━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━

@js.native
object ReactDOMServer extends ReactDOMServer

@js.native
trait ReactDOMServer extends js.Object {

  /**
    * Render a ReactElement to its initial HTML. This should only be used on the server. React will return an HTML
    * string. You can use this method to generate HTML on the server and send the markup down on the initial request for
    * faster page loads and to allow search engines to crawl your pages for SEO purposes.
    *
    * If you call `ReactDOM.render()` on a node that already has this server-rendered markup, React will preserve it and
    * only attach event handlers, allowing you to have a very performant first-load experience.
    */
  def renderToString(e: ReactElement): String = js.native

  /**
    * Similar to [[renderToString]], except this doesn't create extra DOM attributes such as `data-react-id`, that React
    * uses internally. This is useful if you want to use React as a simple static page generator, as stripping away the
    * extra attributes can save lots of bytes.
    */
  def renderToStaticMarkup(e: ReactElement): String = js.native
}
