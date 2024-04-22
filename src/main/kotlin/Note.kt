import java.util.Scanner

class Note (
    override val name: String,
    val text: String
): Element()

private val scanner = Scanner(System.`in`)
fun createNote(): Note {
    var noteName = ""
    var noteText = ""
    while (true) {
        print("Введите название заметки: ")
        noteName = scanner.nextLine()
        if (noteName.isNotBlank()) {
            break
        } else {
            println("Некорректное значение для названия заметки")
        }
    }
    while (true) {
        print("Введите текст заметки: ")
        noteText = scanner.nextLine()
        if (noteText.isNotBlank()) {
            break
        } else {
            println("Некорректное значение для текста заметки")
        }
    }
    println("\nЗаметка $noteName создана!\n")
    return Note(noteName, noteText)
}
