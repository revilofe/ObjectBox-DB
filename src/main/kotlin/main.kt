

@JvmStatic
fun main(args: Array<String>) {
    val store: BoxStore = MyObjectBox.builder().name("objectbox-notes-db").build()
    val box: Box<Note> = store.boxFor(Note::class.java)
    val text = if (args.size > 0) java.lang.String.join(" ", *args) else "No text given"
    box.put(Note(text))
    println(box.count().toString() + " notes in ObjectBox database:")
    for (note in box.getAll()) {
        println(note)
    }
    store.close()
}