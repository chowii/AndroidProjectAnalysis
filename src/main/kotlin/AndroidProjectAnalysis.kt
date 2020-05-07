import java.nio.file.FileVisitOption
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.streams.toList

val projectPath = "/home/sabbib/workspace/wooliesx/"
fun main(args: Array<String>) {
    val projectFiles = Files.walk(Paths.get(projectPath), FileVisitOption.FOLLOW_LINKS).toList()
    val sngFileList = projectFiles
        .map {
            it.toFile()
        }
        .filter { file ->
            !file.isDirectory && file.path.contains("scanGoLibrary") && file.extension == "xml"
        }

    val otherFileList = projectFiles
        .map {
            it.toFile()
        }
        .filter { file ->
            !file.isDirectory && !file.path.contains("scanGoLibrary") && !file.path.contains("/build") && file.extension == "xml"
        }


    sngFileList.forEach { sngFile ->
        print("sng -> ")
        sngFile.absolutePath.println()
        otherFileList.forEach { otherFile ->
            if (sngFile.name == (otherFile.name)) {
                print("other -> ")
                otherFile.absolutePath.println()
                println("sngFileName: ${sngFile.absolutePath} equals otherFileName: ${otherFile.absolutePath}")
                "---------------------------------------------------------------------------------------------------------".println()
            }
        }
        "=========================================================================================================".println()
    }

        
}


fun <T> T.print(): T {
    print(this)
    return this
}

fun <T> T.println(): T {
    println(this)
    return this
}