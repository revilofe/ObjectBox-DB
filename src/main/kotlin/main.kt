import io.objectbox.Box
import io.objectbox.BoxStore

fun main(args: Array<String>) {
    val store: BoxStore = MyObjectBox.builder().name("objectbox-notes-db").build()
    val box: Box<Note> = store.boxFor(Note::class.java)
    val text = if (args.size > 0) java.lang.String.join(" ", *args) else "No text given"
    box.put(Note(text=text))
    println(box.count().toString() + " notes in ObjectBox database:")
    for (note in box.getAll()) {
        println(note)
    }
    store.close()
}
/*
object ObjectBox {
    lateinit var store: BoxStore
        private set

    fun init(context: Context) {
        store = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()
    }
}*/