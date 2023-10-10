import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonArray
import java.io.File
import java.net.URL

class JsonReplacer {
     lateinit var data:String
     lateinit var text:String

    fun getData(link:String){
        val url = URL(link)
        data = url.readText()
        text = File("task/replacement.json").readText()
    }
    fun readReplacements():MutableList<Replacement>{
        val parsed = Json.parseToJsonElement(text)
        val replacements: MutableList<Replacement> = mutableListOf()
        for (item in parsed.jsonArray) {
            replacements += Json.decodeFromJsonElement<Replacement>(item)
        }
        return replacements

    }
    fun removeWhitespaces(){
        data = data.replace(",\n  \"\"", "")

    }
    fun replace(){
        val replacements = readReplacements()
        for (item in replacements.reversed()) {
            data = data.replace(item.replacement, item.source ?: "" )
        }
        removeWhitespaces()
    }
    fun writeToFile(path:String) {
        val file = File(path)
        file.writeText(data)
    }
}
