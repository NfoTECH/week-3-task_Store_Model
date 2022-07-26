package dev.nfotech;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainTest {

    @Test
    @DisplayName("Should print the sales made according to the quantity of items on the cart")
    void mainShouldPrintSalesMadeAccordingToTheQuantityOfItemsOnTheCart() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.main(new String[]{});
        String expected =
                "Potato Chips Snacks 12 0.5\n"
                        + "Oatmeal Raisin Snacks 5 0.5\n"
                        + "Carrot Vegetables 10 0.5\n"
                        + "Arrowroot Vegetables 8 0.5\n"
                        + "Chocolate Chip Snacks 17 0.5\n"
                        + "Biscuit Snacks 17 0.5\n"
                        + "---------------------------------\n"
                        + "\n"
                        + "Sales made according to the QUANTITY of items on the cart\n"
                        + "Total items in Jackson cart is 56.\n"
                        + "Total items in Richard cart is 45.\n"
                        + "Total items in Aliyah cart is 59.\n"
                        + "\n"
                        + "Carrot balance quantity after sales is -2\n";

        assertEquals(expected, outContent.toString());
    }

    @Test
    @DisplayName("Should print the carrot balance quantity after sales")
    void mainShouldPrintCarrotBalanceQuantityAfterSales() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Main.main(new String[]{});
        assertEquals("\nCarrot balance quantity after sales is 7\n", outContent.toString());
    }
}