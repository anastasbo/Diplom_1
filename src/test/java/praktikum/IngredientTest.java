package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class IngredientTest {
    private  final Burger burger = new Burger();
    @Mock
    Database database;
    public List<Ingredient> ingredients = new ArrayList<>();
    @Before
    public void addIngredients() {
        ingredients.add(new Ingredient(SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(FILLING, "sausage", 300));
        Mockito.when(database.availableIngredients()).thenReturn(ingredients);
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
    public void getPriceIngredientTest(){
        Ingredient ingredient = database.availableIngredients().get(0);
        assertEquals(100, ingredient.getPrice(), 0.01f);
    }

    @Test
    public void getNameIngredientTest(){
        Ingredient ingredient = database.availableIngredients().get(0);
        assertEquals("hot sauce", ingredient.getName());
    }

    @Test
    public void getTypeIngredientTest(){
        Ingredient ingredient = database.availableIngredients().get(0);
        assertEquals(SAUCE, ingredient.getType());
    }
}
