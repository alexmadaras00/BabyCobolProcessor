package program

sealed interface BabyCobolObject

/**
 * A Representation is a string according to the BabyCOBOL format in Picture clauses.
 */
typealias Representation = String

/**
 * Value class, used for data of different types.
 * All implementations should also have a constructor that accepts a string.
 */
data class Value(var data: String?, val representation: Representation? = null): BabyCobolObject {
    /**
     * Returns a String representation of the value.
     * TODO implement formatting according to representation
     */
    override fun toString(): String {
        if (data == null) return "null"
        return if (isNumeric()) data!! else "'$data'"
    }

    /**
     * Sets the data field to a new value, if it is compatible with the representation.
     * @param newData the data to be set
     * TODO implement this method
     */
    fun setValue(newData: String) {

    }

    /**
     * A Value is Numeric iff it does not contain any A's or X's.
     */
    fun isNumeric(): Boolean = representation?.none { it in "AX" } ?: false

}

/**
 * Occurs data type, a 1-indexed array.
 * Representation of all elements is assumed to match.
 * @param data an array of all its elements
 */
data class Occurs(val data: Array<BabyCobolObject>): BabyCobolObject {
    val size = data.size

    // Simulates 1-indexing by subtracting 1 from the index.
    operator fun get(i: Int) = data[i-1]

    operator fun set(i: Int, v: BabyCobolObject) { data[i-1] = v }

    val indices = IntRange(1, size)
    val lastIndex = size

    // Generated equals() and hashCode(), nothing special
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Occurs

        if (!data.contentEquals(other.data)) return false

        return true
    }

    override fun hashCode(): Int {
        return data.contentHashCode()
    }
}

/**
 * Composite object, containing named sub-objects.
 */
data class Record(val components: Map<String, BabyCobolObject>):
    BabyCobolObject