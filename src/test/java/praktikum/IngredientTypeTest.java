package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IngredientTypeTest {
    @Test
    public void selectSauceIngredientFromEnumIsCorrect() {
        assertEquals(IngredientType.valueOf("SAUCE"), IngredientType.SAUCE);
    }

    @Test
    public void selectFillingIngredientFromEnumIsCorrect() {
        assertEquals(IngredientType.valueOf("FILLING"), IngredientType.FILLING);
    }

    @Test
    public void ingredientTypeEnumLengthTest(){
        assertEquals(2, IngredientType.values().length);
    }
}
