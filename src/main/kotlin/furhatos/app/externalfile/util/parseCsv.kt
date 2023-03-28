package furhatos.app.externalfile.util

import furhatos.app.externalfile.ExternalfileSkill
import furhatos.skills.Skill
import furhatos.util.Language
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.InputStreamReader
import java.lang.RuntimeException

var textMap = mutableMapOf<String, String>()

fun parseCsv() {
    val surveyResource = ExternalfileSkill::class.java.getResourceAsStream("/content/text.csv")
    val csvParser = CSVParser(InputStreamReader(surveyResource), CSVFormat.DEFAULT.withFirstRecordAsHeader())

    for (csvRecord in csvParser) {
        val record = csvRecord.toMap()
        val key = (record["Key"] ?: "").trim()
        val text = (record["Text"] ?: "").trim()

        textMap[key] = text
    }
}

fun getText(key: String): String {
    return textMap[key].toString()
}