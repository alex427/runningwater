package alex.chapter10.exercise

class MyIterator extends Iterator[Byte]{
    override def hasNext: Boolean = {
        true
    }

    override def next(): Byte = {
        val bytes = "a".getBytes()
        bytes(1)
    }
}
