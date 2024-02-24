package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private final Burger burger = new Burger();
    @Mock
    Ingredient ingredient;
    @Mock
    Ingredient ingredientSecond;
    @Mock
    Database database;
    private final List<Bun> buns = Arrays.asList(new Bun("grey bun",100.50F));

    @Before
    public void setDefaultBun() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient(SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(FILLING, "sausage", 300));
        Mockito.when(database.availableIngredients()).thenReturn(ingredients);
        Mockito.when(database.availableBuns()).thenReturn(buns);
    }

    @Test
    public void checkSetBuns() {
        burger.setBuns(database.availableBuns().get(0));
        assertEquals("grey bun", burger.bun.getName());
    }

    @Test
    public void checkAddIngredients() {
        burger.addIngredient(database.availableIngredients().get(0));
        assertEquals(1, burger.ingredients.size());
    }
    @Test
    public void checkRemoveIngredients() {
        burger.addIngredient(database.availableIngredients().get(0));
        burger.removeIngredient(0);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void checkMoveIngredient() {
        burger.addIngredient(database.availableIngredients().get(1));
        burger.addIngredient(database.availableIngredients().get(0));
        burger.moveIngredient(0,1);
        assertEquals(database.availableIngredients(), burger.ingredients);
    }

    @Test
    public void checkGetPrice() {
        Mockito.when(ingredient.getPrice()).thenReturn(125.5F);
        Mockito.when(ingredientSecond.getPrice()).thenReturn(250F);
        burger.setBuns(database.availableBuns().get(0));
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientSecond);
        float expectedBurgerPrice = 576.5F;
        assertEquals("Некорректная цена бургера с двумя добавленными ингредиентами", expectedBurgerPrice, burger.getPrice(), 0);
    }

    @Test
    public void checkGetReceipt() {
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient.getName()).thenReturn("hot sauce");
        Mockito.when(ingredient.getPrice()).thenReturn(100F);
        Mockito.when(ingredientSecond.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredientSecond.getName()).thenReturn("sausage");
        Mockito.when(ingredientSecond.getPrice()).thenReturn(300F);
        burger.setBuns(database.availableBuns().get(0));
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredientSecond);
        String expected = "(==== grey bun ====)" + "\n"
                + "= sauce hot sauce =" + "\n"
                +"= filling sausage ="+ "\n"
                + "(==== grey bun ====)" + "\n\n"
                + "Price: 601,000000" + "\n";
        assertEquals(expected, burger.getReceipt());
    }
}