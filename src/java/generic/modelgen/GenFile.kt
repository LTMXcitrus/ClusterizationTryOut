package generic.modelgen

import java.io.PrintStream


object GenFile {

    fun genFile(text: String) {
        val out = PrintStream("src/resources/dataTest.csv")
        out.println(text)
    }
}