fun main(args: Array<String>) {
    val archiveList: MutableList<Archive> = mutableListOf() //Archive("Архив 1", mutableListOf(Note("Заметка 1","Текст заметки")))

    val menu = Menu()
    menu.show("Список архивов", null, (archiveList as MutableList<Element>))
}