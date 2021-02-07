import java.io.File
import java.nio.file.FileVisitOption
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import kotlin.streams.toList

val projectPath = "/home/sabbib/workspace/wooliesx/"

val ignoreFilesList = listOf(
        "AndroidManifest.xml",
        "strings.xml",
        "dimens.xml",
        "attrs.xml",
        "styles.xml",
        "integers.xml",
        "colors.xml",
        "android-lint-baseline.xml",
        "detekt-baseline.xml",
        "ids.xml"
)

fun main(args: Array<String>) {
    val projectFiles = Files.walk(Paths.get(projectPath), FileVisitOption.FOLLOW_LINKS).toList()

    val sngFileList = getSngFileList(projectFiles)

    val otherFileList = getOtherFileList(projectFiles)

    matchFilesBetweenPaths(sngFileList, otherFileList)
}

private fun matchFilesBetweenPaths(sngFileList: List<File>, otherFileList: List<File>) {
    sngFileList.forEach { sngFile ->
        otherFileList.forEach { otherFile ->
            if (!ignoreFilesList.contains(sngFile.name) && sngFile.name == (otherFile.name)) {
                sngFile.name.println()
            }
        }
    }
}

internal fun getOtherFileList(projectFiles: List<Path>): List<File> {
    return projectFiles
            .map {
                it.toFile()
            }
            .filter { file ->
                !file.isDirectory && !file.path.contains("scanGoLibrary/src/main/res/") && !file.path.contains("/build")
            }
}

internal fun getSngFileList(projectFiles: List<Path>): List<File> {
    return projectFiles
            .map {
                it.toFile()
            }
            .filter { file ->
                !file.isDirectory && file.path.contains("scanGoLibrary/src/main/res/") && !file.path.contains("/build")
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