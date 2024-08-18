import java.io.BufferedReader
import java.io.File
import java.io.FileReader

fun parseIntOrThrow(str: String): Int?{
    return try {
        str.toInt()
    }catch (e: NumberFormatException){
       null
    }
}

fun main() {
    val printFile = FilePrinter2()
    printFile.readFile("./a.txt")
}

class FilePrinter{
    fun readFile(){
        val curFile=File(".")
        val file = File(curFile.absoluteFile ,"a.txt")
        val reader = BufferedReader(FileReader(file))
        println(reader.readLine())
        reader.close()
    }
}

class FilePrinter2{
    fun readFile(path: String){
       BufferedReader(FileReader(path)).use{
           reader -> println(reader.readLine())
       }
    }
}
