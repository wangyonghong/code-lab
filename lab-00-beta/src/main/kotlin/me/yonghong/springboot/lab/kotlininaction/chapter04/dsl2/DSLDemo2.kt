package me.yonghong.springboot.lab.kotlininaction.chapter04.dsl2

fun main(args: Array<String>) {
    val result =
            html {
                head {
                    title { "HTML encoding with Kotlin" {} }
                }
                body {
                    h1 {
                        "HTML encoding with Kotlin" {
                            "color" to "red"
                        }
                    }
                    p { "this format can be used as an alternative markup to HTML" { "color" to "green" } }

                    // an element with attributes and text content
                    a(href = "http://jetbrains.com/kotlin") { "Kotlin"{ "color" to "blue" } }

                    // mixed content
                    p {
                        "This is some"{}
                        b { "mixed"{} }
                        "text. For more see the"{}
                        a(href = "http://jetbrains.com/kotlin") { "Kotlin"{} }
                        "project"{}
                    }
                    p { "some text"{} }

                    // content generated from command-line arguments
                    p {
                        "Command line arguments were:"{}
                        ul {
                            for (arg in args)
                                li { arg {} }
                        }
                    }
                }
            }
    println(result)
}

interface Element {
    fun render(builder: StringBuilder, indent: String)
}

class TextElement(private val text: String) : Element {
    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent$text\n")
    }
}

abstract class Tag(private val name: String) : Element {
    val children = arrayListOf<Element>()
    val attributes = hashMapOf<String, String>()

    protected fun <T : Element> initTag(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun render(builder: StringBuilder, indent: String) {
        builder.append("$indent<$name${renderAttributes()}>\n")
        for (c in children) {
            c.render(builder, "$indent  ")
        }
        builder.append("$indent</$name>\n")
    }

    private fun renderAttributes(): String? {
        val builder = StringBuilder()
        for (a in attributes.keys) {
            builder.append(" $a=\"${attributes[a]}\"")
        }
        return builder.toString()
    }


    override fun toString(): String {
        val builder = StringBuilder()
        render(builder, "")
        return builder.toString()
    }
}

// + 操作符
abstract class TagWithText(name: String) : Tag(name) {
    private val textAttributes = hashMapOf<String, String>()

//    operator fun String.unaryPlus() {
//        children.add(TextElement(this))
//    }

    operator fun String.invoke(block: () -> Unit) {
        block.invoke()
        if (textAttributes.isEmpty()) {
            children.add(TextElement(this))
        } else {
            children.add(FONT(this, textAttributes))
        }
    }

    infix fun String.`to`(value: String) {
        textAttributes[this] = value
    }
}

class HTML() : TagWithText("html") {
    fun head(init: Head.() -> Unit) = initTag(Head(), init)

    fun body(init: Body.() -> Unit) = initTag(Body(), init)
}

class Head() : TagWithText("head") {
    fun title(init: Title.() -> Unit) = initTag(Title(), init)
}

class Title() : TagWithText("title")

abstract class BodyTag(name: String) : TagWithText(name) {
    fun b(init: B.() -> Unit) = initTag(B(), init)
    fun p(init: P.() -> Unit) = initTag(P(), init)
    fun h1(init: H1.() -> Unit) = initTag(H1(), init)
    fun ul(init: UL.() -> Unit) = initTag(UL(), init)
    fun a(href: String, init: A.() -> Unit) {
        val a = initTag(A(), init)
        a.href = href
    }
}

class Body() : BodyTag("body")
class UL() : BodyTag("ul") {
    fun li(init: LI.() -> Unit) = initTag(LI(), init)
}

class B() : BodyTag("b")
class LI() : BodyTag("li")
class P() : BodyTag("p")
class H1() : BodyTag("h1")

class A() : BodyTag("a") {
    public var href: String
        get() = attributes["href"]!!
        set(value) {
            attributes["href"] = value
        }
}

fun html(init: HTML.() -> Unit): HTML {
    val html = HTML()
    html.init()
    return html
}

class FONT(text: String, textAttributes: HashMap<String, String>) : BodyTag("font") {
    init {
        attributes.putAll(textAttributes)
        children.add(TextElement(text))
    }
}