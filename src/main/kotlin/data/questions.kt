package data

data class Question (
    val questionTitle : String ,
    val qPoints:Int,
    val variants:Array<String>,
    val answer:Int
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class.js != other::class.js) return false

        other as Question

        if (questionTitle != other.questionTitle) return false
        if (qPoints != other.qPoints) return false
        if (!variants.contentEquals(other.variants)) return false
        if (answer != other.answer) return false

        return true
    }

    override fun hashCode(): Int {
        var result = questionTitle.hashCode()
        result = 31 * result + qPoints
        result = 31 * result + variants.contentHashCode()
        result = 31 * result + answer
        return result
    }
}