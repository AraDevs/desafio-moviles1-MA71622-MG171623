import java.math.BigDecimal
import java.math.RoundingMode

fun Double.twoDecimals(): String {
    return BigDecimal(this).setScale(2, RoundingMode.HALF_EVEN).toString()
}