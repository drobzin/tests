import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.*

internal class JsonReplacerTest {
    private val obj = JsonReplacer()

    @Test
    fun `data should contain correct string`(){
        val input = "https://raw.githubusercontent.com/thewhitesoft/student-2023-assignment/main/data.json"
        val expected = "[\n  \"Two roads diverged in a yellow d12324344rgg6f5g6gdf2ddjf,\",\n  \"Robert Frost poetAnd sorry I cou1d not trave1 both\",\n  \"An other text\",\n  \"And be one trave1er, long I stood\",\n  \"And 1ooked down one as far as I cou1d\",\n  \"Bla-bla-bla-blaaa, just some RANDOM tExT\",\n  \"To where it bent in the undergrowth;\",\n  \"Then Random text, yeeep the other, as just as fair,\",\n  \"And having perhaps parentheses - that is a smart word,\",\n  \"Bla-bla-bla-blaaa, just some RANDOM tExT\",\n  \"Because it was grassy and wanted wear;\",\n  \"An other text\",\n  \"An other text\",\n  \"Though as for that the passing there\",\n  \"Emptry... or NOT! them rea11y about the same,\",\n  \"And both that morning equally lay\",\n  \"In 1eaves no step had trodden b1ack.\",\n  \"Oh, I kept the first for another day!\",\n  \"Yet Skooby-dooby-doooo 1eads on to way,\",\n  \"Ha-haaa, hacked you.\",\n  \"An other text\",\n  \"I shall be te11ing this with a sigh\",\n  \"sdshdjdskfm sfjsdif jfjfidjf\",\n  \"Two roads diverged in a d12324344rgg6f5g6gdf2ddjf, and I\",\n  \"I Random text, yeeep the one less traveled by,\",\n  \"And that has made a11 the difference.\",\n  \"Bla-bla-bla-blaaa, just some RANDOM tExT\"\n]\n"

        obj.getData(input)
        assertEquals(expected, obj.data)
    }

    @Test
    fun `replacements should be correct`(){
        obj.text ="[\n" +
                "  {\n" +
                "    \"replacement\": \"Ha-haaa, hacked you\",\n" +
                "    \"source\": \"I doubted if I should ever come back\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"replacement\": \"sdshdjdskfm sfjsdif jfjfidjf\",\n" +
                "    \"source\": \"Somewhere ages and ages hence:\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"replacement\": \"1\",\n" +
                "    \"source\": \"l\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"replacement\": \"Emptry... or NOT!\",\n" +
                "    \"source\": null\n" +
                "  }\n" +
                "]"

        val replacement = obj.readReplacements()

        assertEquals("Ha-haaa, hacked you", replacement[0].replacement)
        assertEquals("Somewhere ages and ages hence:", replacement[1].source)
        assertEquals(null, replacement[3].source)
        assertEquals(4, replacement.size)
    }
    @Test
    fun `whitespaces should be removed`(){
        obj.data ="[\n" +
                "  \"Two roads diverged in a yellow wood,\",\n" +
                "  \"Robert Frost poetAnd sorry I could not travel both\",\n" +
                "  \"\",\n" +
                "  \"And be one traveler, long I stood\",\n" +

                "]\n"
        val expectedText = "[\n" +
                "  \"Two roads diverged in a yellow wood,\",\n" +
                "  \"Robert Frost poetAnd sorry I could not travel both\",\n" +
                "  \"And be one traveler, long I stood\",\n" +
                "]\n"
        obj.removeWhitespaces()
        assertEquals( expectedText, obj.data)
    }

    @Test
    fun `text should be replaced`(){
        obj.text ="[\n" +
                "  {\n" +
                "    \"replacement\": \"=)\",\n" +
                "    \"source\": \"-_-\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"replacement\": \"test\",\n" +
                "    \"source\": \"work\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"replacement\": \"1\",\n" +
                "    \"source\": \"l\"\n" +
                "  },\n" +
                "  {\n" +
                "    \"replacement\": \"fufu\",\n" +
                "    \"source\": null\n" +
                "  }\n" +
                "]"
        obj.data ="[\n" +
                "  \"Fa11,\",\n" +
                "  \"Robert Frost =) sorry I cou1d not test \",\n" +
                "  \"fufufufu\"\n"+
                "]\n"
        val expectedText = "[\n" +
                "  \"Fall,\",\n" +
                "  \"Robert Frost -_- sorry I could not work \"\n" +
                "]\n"
        obj.replace()
        assertEquals( expectedText, obj.data)

    }



}