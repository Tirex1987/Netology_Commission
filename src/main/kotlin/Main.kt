const val MAX_DAY_LIMIT_CARD = 150_000_00
const val MAX_MONTH_LIMIT_CARD = 600_000_00

const val AMOUNT_START_COMMISSION_MAS_MAE = 75_000_00
const val PERCENT_COMMISSION_MAS_MAE = .006
const val ADDED_AMOUNT_COMMISSION_MAS_MAE = 20_00

const val PERCENT_COMMISSION_VISA_MIR = 0.0075
const val MIN_AMOUNT_COMMISSION_VISA_MIR = 35_00

const val MAX_DAY_LIMIT_VKPAY = 15_000_00
const val MAX_MONTH_LIMIT_VKPAY = 40_000_00

enum class CardType {
    Mastercard, Maestro, Visa, Mir, VKpay
}

fun main() {
    val transferAmount = 70_000_00
    val cardType = CardType.Mastercard
    val previousAmount = 10_000_00

    if (checkInDayLimit(transferAmount, cardType)) {
        if (checkInMonthLimit(transferAmount + previousAmount, cardType)) {
            val commission = calculateCommission(transferAmount, cardType, previousAmount)
            println("Комиссия за перевод: ${commission / 100} руб. ${commission % 100} коп.")
        } else {
            println("Превышен лимит перевода в месяц")
        }
    } else {
        println("Превышен дневной лимит перевода")
    }
}

fun calculateCommission(
    amount: Int,
    cardType: CardType = CardType.VKpay,
    previousAmount: Int = 0
): Int {
    val commission = when (cardType) {
        CardType.Mastercard, CardType.Maestro -> {
            val transfersSum = amount + previousAmount
            if (transfersSum > AMOUNT_START_COMMISSION_MAS_MAE)
                ((transfersSum - AMOUNT_START_COMMISSION_MAS_MAE) * PERCENT_COMMISSION_MAS_MAE + ADDED_AMOUNT_COMMISSION_MAS_MAE).toInt()
            else 0
        }
        CardType.Visa, CardType.Mir -> {
            val calculatedCommission = (amount * PERCENT_COMMISSION_VISA_MIR).toInt()
            if (calculatedCommission < MIN_AMOUNT_COMMISSION_VISA_MIR)
                MIN_AMOUNT_COMMISSION_VISA_MIR
            else calculatedCommission
        }
        CardType.VKpay -> 0
    }

    return commission
}

fun checkInDayLimit(
    dayAmount: Int,
    cardType: CardType = CardType.VKpay,
): Boolean = when (cardType) {
    CardType.Mastercard, CardType.Maestro, CardType.Visa, CardType.Mir -> {
        dayAmount < MAX_DAY_LIMIT_CARD
    }
    CardType.VKpay -> {
        dayAmount < MAX_DAY_LIMIT_VKPAY
    }
}

fun checkInMonthLimit(
    monthAmount: Int,
    cardType: CardType = CardType.VKpay,
): Boolean = when (cardType) {
    CardType.Mastercard, CardType.Maestro, CardType.Visa, CardType.Mir -> {
        monthAmount < MAX_MONTH_LIMIT_CARD
    }
    CardType.VKpay -> {
        monthAmount < MAX_MONTH_LIMIT_VKPAY
    }
}