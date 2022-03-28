import org.junit.Assert.*
import org.junit.Test

class MainKtTest {

    @Test
    fun calculateCommission_mastercardNoCommission() {
        val amount = 50_000_00
        val cardType = CardType.Mastercard
        val previousAmount = 10_000_00

        val result = calculateCommission(
            amount = amount,
            cardType = cardType,
            previousAmount = previousAmount
        )

        assertEquals(0,result)
    }

    @Test
    fun calculateCommission_maestroWithCommission() {
        val amount = 75_000_00
        val cardType = CardType.Maestro
        val previousAmount = 10_000_00

        val result = calculateCommission(
            amount = amount,
            cardType = cardType,
            previousAmount = previousAmount
        )

        assertEquals(80_00,result)
    }

    @Test
    fun calculateCommission_visaMinimumCommission() {
        val amount = 4_000_00
        val cardType = CardType.Visa
        val previousAmount = 0

        val result = calculateCommission(
            amount = amount,
            cardType = cardType,
            previousAmount = previousAmount
        )

        assertEquals(35_00,result)
    }

    @Test
    fun calculateCommission_mirCommission() {
        val amount = 40_000_00
        val cardType = CardType.Mir
        val previousAmount = 0

        val result = calculateCommission(
            amount = amount,
            cardType = cardType,
            previousAmount = previousAmount
        )

        assertEquals(300_00,result)
    }

    @Test
    fun calculateCommission_vkpay() {
        val amount = 100_000_00
        val cardType = CardType.VKpay
        val previousAmount = 0

        val result = calculateCommission(
            amount = amount,
            cardType = cardType,
            previousAmount = previousAmount
        )

        assertEquals(0,result)
    }

    @Test
    fun checkInDayLimit_card_amountInLimit() {
        val dayAmount = 100_000_00
        val cardType = CardType.Mastercard

        val result = checkInDayLimit(
            dayAmount = dayAmount,
            cardType = cardType
        )

        assertTrue(result)
    }

    @Test
    fun checkInDayLimit_card_amountExceedLimit() {
        val dayAmount = 200_000_00
        val cardType = CardType.Mastercard

        val result = checkInDayLimit(
            dayAmount = dayAmount,
            cardType = cardType
        )

        assertFalse(result)
    }

    @Test
    fun checkInDayLimit_vkpay_amountInLimit() {
        val dayAmount = 10_000_00
        val cardType = CardType.VKpay

        val result = checkInDayLimit(
            dayAmount = dayAmount,
            cardType = cardType
        )

        assertTrue(result)
    }

    @Test
    fun checkInDayLimit_vkpay_amountExceedLimit() {
        val dayAmount = 20_000_00
        val cardType = CardType.VKpay

        val result = checkInDayLimit(
            dayAmount = dayAmount,
            cardType = cardType
        )

        assertFalse(result)
    }

    @Test
    fun checkInMonthLimit_card_amountInLimit() {
        val monthAmount = 500_000_00
        val cardType = CardType.Mastercard

        val result = checkInMonthLimit(
            monthAmount = monthAmount,
            cardType = cardType
        )

        assertTrue(result)
    }

    @Test
    fun checkInMonthLimit_card_amountExceedLimit() {
        val monthAmount = 700_000_00
        val cardType = CardType.Mastercard

        val result = checkInMonthLimit(
            monthAmount = monthAmount,
            cardType = cardType
        )

        assertFalse(result)
    }

    @Test
    fun checkInMonthLimit_vkpay_amountInLimit() {
        val monthAmount = 30_000_00
        val cardType = CardType.VKpay

        val result = checkInMonthLimit(
            monthAmount = monthAmount,
            cardType = cardType
        )

        assertTrue(result)
    }

    @Test
    fun checkInMonthLimit_vkpay_amountExceedLimit() {
        val monthAmount = 50_000_00
        val cardType = CardType.VKpay

        val result = checkInMonthLimit(
            monthAmount = monthAmount,
            cardType = cardType
        )

        assertTrue(result)
    }
}