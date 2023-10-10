import kotlinx.serialization.json.*
import kotlinx.serialization.json.internal.writeJson
import java.io.File
import java.net.URL


    fun main(args: Array<String>) {
        val jsonReplacer = JsonReplacer()
        jsonReplacer.getData("https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/data.json")
        jsonReplacer.replace()
        jsonReplacer.writeToFile("output/result.json")
    }

