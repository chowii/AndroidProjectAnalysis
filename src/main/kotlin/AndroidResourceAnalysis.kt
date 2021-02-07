import org.w3c.dom.Document
import java.nio.file.FileVisitOption
import java.nio.file.Files
import java.nio.file.Paths
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.streams.toList

fun main(args: Array<String>) {
    val projectFiles = Files.walk(Paths.get(projectPath), FileVisitOption.FOLLOW_LINKS).toList()

    val otherFileList = getOtherFileList(projectFiles)
    val sngFileList = getSngFileList(projectFiles)
            .filter {
                it.extension == "xml"
            }


    sngFileList.forEach {
        it.name.println()
        val s: Document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(it)
        s.normalize()

        val childNodes = s.childNodes
        for (i in 0 until s.childNodes.length) {
            val nodeItem = childNodes.item(i)
            nodeItem.nodeName.println()
            nodeItem?.nextSibling?.nodeName?.println()
        }
        "---------------------------------------------------------------------------------------------------------".println()
    }


}