import java.util.Scanner

class Archive(
    override val name: String,
    val noteList: MutableList<Note> = mutableListOf()
): Element()
{
    fun addNoteInArchive(){
        noteList.add(createNote())
    }
}

private val scanner = Scanner(System.`in`)
fun createArchive(): Archive {
    while (true) {
        print("Введите название архива: ")
        val archiveName = scanner.nextLine()
        if (archiveName.isNotBlank()) {
            println("\nАрхив $archiveName создан!\n")
            return Archive(archiveName)
        } else {
            println("Некорректное значение для названия архива")
        }
    }
}

