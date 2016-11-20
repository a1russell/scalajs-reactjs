package io.github.shogowada.scalajs.reactjs2

import scala.scalajs.js
import scala.scalajs.js.annotation.JSName
import scala.scalajs.js.|

package object upstream {

  type Undefined = js.UndefOr[Nothing]

  type JsNumber = Byte | Short | Int | Float | Double

  type Props = js.Object | Null

  type State = js.Object | Null

  type Key = String | Boolean | JsNumber | Null

  type Ref = String | Null

  type ReactText = String | JsNumber

  type ReactNode = ReactElement | ReactFragment | ReactText

  type ReactEmpty = Boolean | Undefined | Null

  type ReactNodeList = ReactNode | ReactEmpty

  type PropsChildren = ReactNodeList

  @js.native
  trait PropsWithChildren extends js.Object {
    val children: PropsChildren
  }

  /** ReactComponentElement | ReactDOMElement */
  @js.native
  trait ReactElement extends js.Object

  @js.native
  trait ReactDOMElement extends ReactElement {
    def `type`: String
    def props: PropsWithChildren
    def key: Key
    def ref: Ref
  }

  type ReactCtor = ReactClass | ReactFunctionalComponent

  @js.native
  trait ReactComponentElement extends ReactElement {
    def `type`: ReactCtor
    def props: PropsWithChildren
    def key: Key
    def ref: Ref
  }

  // Type aliases can't be recursive
  // type ReactFragment = js.Array[ReactNode | ReactEmpty]
  @js.native
  trait ReactFragment extends js.Any
  @inline implicit def ReactFragment[A](a: A)(implicit w: A => js.Array[ReactNode | ReactEmpty]): ReactFragment =
    w(a).asInstanceOf[ReactFragment]

  //  @inline def emptyReactNodeList: ReactNodeList =
  //    js.undefined

  type ReactClass = js.Function1[Props, ReactComponent]

  /** Once-mounted component. */
  @js.native
  trait ReactComponent extends js.Object {
    // [ 3/30] childContextTypes         : object   = null
    // [ 4/30] componentDidMount         : object   = null
    // [ 5/30] componentDidUpdate        : object   = null
    // [ 6/30] componentWillMount        : object   = null
    // [ 7/30] componentWillReceiveProps : object   = null
    // [ 8/30] componentWillUnmount      : object   = null
    // [ 9/30] componentWillUpdate       : object   = null
    val constructor: ReactCtor
    // [11/30] context                   : object   = [object Object]
    // [12/30] contextTypes              : object   = null
    def forceUpdate(callback: js.Function0[Unit] = js.native): Unit = js.native
    // [14/30] getChildContext           : object   = null
    def getDefaultProps: Props = js.native
    def getInitialState: State = js.native
    def isMounted(): Boolean = js.native
    // [18/30] isReactComponent          : object   = [object Object]
    val mixins: js.Array[js.Object] | Null = js.native
    // [20/30] propTypes                 : object   = null
    def props: PropsWithChildren
    // [22/30] refs                      : object   = [object Object]
    def render(): ReactElement
    def replaceState(newState: State, callback: js.Function0[Unit] = js.native): Unit = js.native
    def setState(partialState: State, callback: js.Function0[Unit] = js.native): Unit = js.native
    @JSName("setState") def modState(fn: js.Function1[js.Object, js.Object], callback: js.Function0[Unit] = js.native): Unit = js.native
    // [26/30] shouldComponentUpdate     : object   = null
    def state: State
    // [28/30] statics                   : object   = null
    // [29/30] updateComponent           : object   = null
    // [30/30] updater                   : object   = [object Object]
  }

  type ReactFunctionalComponent = js.Function1[Props, ReactElement]

  @js.native
  trait ReactComponentSpec extends js.Object {
    var displayName              : String                                                                   = js.native
    var render                   : js.ThisFunction0[upstream.ReactComponent, upstream.ReactElement]                   = js.native
    var getInitialState          : js.Function                                                              = js.native
    // var getInitialState          : js.ThisFunction0[upstream.ReactComponentElement, State] | js.Function0[State] = js.native
    var componentWillMount       : js.ThisFunction0[upstream.ReactComponent, Unit]                               = js.native
    var componentWillUnmount     : js.ThisFunction0[upstream.ReactComponent, Unit]                               = js.native
    var componentDidMount        : js.ThisFunction0[upstream.ReactComponent, Unit]                               = js.native
    var componentWillUpdate      : js.ThisFunction2[upstream.ReactComponent, Props, State, Unit]                 = js.native
    var componentDidUpdate       : js.ThisFunction2[upstream.ReactComponent, Props, State, Unit]                 = js.native
    var componentWillReceiveProps: js.ThisFunction1[upstream.ReactComponent, Props, Unit]                        = js.native
    var shouldComponentUpdate    : js.ThisFunction2[upstream.ReactComponent, Props, State, Boolean]              = js.native
    // var getDefaultProps          : xxxxx                                                                    = js.native
    // var mixins                   : xxxxx                                                                    = js.native
  }

}
