import java.util.Scanner
private val scanner = Scanner(System.`in`)
class Menu{
    private var archiveList: MutableList<Archive> = mutableListOf()
    private var noteList: MutableList<Note> = mutableListOf()


    fun show(head: String, currentElement: Element?, menuActions: MutableList<Element>){
        if (menuActions.elementAtOrNull(0) is Archive){ //кейс: запуск с заранее заданными архивами. Добавил чтобы сделать класс Menu гибким
            archiveList = menuActions as MutableList<Archive>
        }

        while (true) {
            println("$head:")
            print("0. Создать ")
            if (head.equals("Список архивов")) println("архив") else println("заметку")
            for (i in menuActions.indices) println("${i + 1}. ${menuActions[i].name}")
            println("${menuActions.size + 1}. Выход\n")

            val elements: MutableList<Element> = mutableListOf()
            elements.addAll(menuActions)
            while (true) {
                val command = scanner.nextLine().toIntOrNull()
                when {
                    command == 0 -> { // Создать новый Архив или Заметку
                        if (head.equals("Список архивов")) {
                            elements.add(createArchive())
                            archiveList = elements as MutableList<Archive>
                            show(
                                "Список архивов",
                                currentElement,
                                archiveList as MutableList<Element>
                            )
                        } else {
                            (currentElement as Archive).addNoteInArchive()
                            noteList = currentElement.noteList
                            show(
                                "Список заметок архива ${currentElement.name}",
                                currentElement,
                                noteList as MutableList<Element>
                            )
                        }
                        break
                    }

                    command == menuActions.size + 1 -> { // Выход
                        if (head.equals("Список архивов")){
                            scanner.close()
                            break
                        } else {
                            show(
                                "Список архивов",
                                null,
                                archiveList as MutableList<Element>
                            )
                        }
                        break
                    }

                    command in 1..(menuActions.size) && menuActions.size !=0 -> { // Открыть Архив или Заметку
                        val el = menuActions[command!! - 1]
                        if (el is Archive) {
                            noteList = el.noteList
                            show(
                                "Список заметок архива ${el.name}",
                                el,
                                noteList as MutableList<Element>
                            )
                        } else if (el is Note) {
                            println("Заметка: ${el.name}")
                            println("Текст: ${el.text}\n")
                            println("Для Выхода из заметки нажмите Enter")
                            scanner.nextLine()
                            show(
                                "Список заметок архива ${currentElement?.name}",
                                currentElement,
                                noteList as MutableList<Element>
                            )
                        }
                        break
                    }

                    command == null -> {
                        println("Ошибка ввода. Допустимые значения для ввода - числа от 0 до ${menuActions.size + 1}!")
                    }

                    command < 0 || command > menuActions.size + 1 -> {
                        println("Пункта меню под числом $command не существует. Допустимые значения для ввода - числа от 0 до ${menuActions.size + 1}!")
                    }
                }
            }
            break
        }
    }
}