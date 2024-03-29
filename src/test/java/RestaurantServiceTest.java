import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class RestaurantServiceTest {

    RestaurantService service = new RestaurantService();
    Restaurant restaurant;

    public void addRestaurantDetails() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = service.addRestaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    @Test
    public void search_existing_restaurant_should_return_expected_restaurant_object() throws restaurantNotFoundException {
        addRestaurantDetails();
        String restaurantName = "Amelie's cafe";
        assertTrue(service.findRestaurantByName(restaurantName).getName() == restaurantName);
    }

    @Test
    public void search_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        addRestaurantDetails();
        String restaurantName = "Lakshman's Cafe";
        assertThrows(restaurantNotFoundException.class, () -> service.findRestaurantByName(restaurantName), "Restaurant not found");
    }

    //<<<<<<<<<<<<<<<<<<<<SEARCHING>>>>>>>>>>>>>>>>>>>>>>>>>>
    
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Test
    public void remove_restaurant_should_reduce_list_size_by_1() throws restaurantNotFoundException {
        addRestaurantDetails();
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.removeRestaurant("Amelie's cafe");
        assertEquals(initialNumberOfRestaurants - 1, service.getRestaurants().size());
    }

    @Test
    public void remove_non_existing_restaurant_should_throw_exception() throws restaurantNotFoundException {
        addRestaurantDetails();
        assertThrows(restaurantNotFoundException.class, () -> service.removeRestaurant("Pantry d'or"));
    }
        
    @Test
    public void add_restaurant_should_increase_list_size_by_1() {
        addRestaurantDetails();
        int initialNumberOfRestaurants = service.getRestaurants().size();
        service.addRestaurant("Pumpkin Tales", "Chennai", LocalTime.parse("12:00:00"), LocalTime.parse("23:00:00"));
        assertEquals(initialNumberOfRestaurants + 1, service.getRestaurants().size());
    }
    //<<<<<<<<<<<<<<<<<<<<ADMIN: ADDING & REMOVING RESTAURANTS>>>>>>>>>>>>>>>>>>>>>>>>>>
}
