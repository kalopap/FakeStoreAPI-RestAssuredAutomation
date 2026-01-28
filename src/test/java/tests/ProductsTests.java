package tests;

import com.kalopap.base.BaseTest;
import com.kalopap.clients.BaseClient;
import com.kalopap.clients.ProductClient;
import com.kalopap.models.Product;
import com.kalopap.models.Rating;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ProductsTests extends BaseTest {


    ProductClient productClient = new ProductClient();

//****************** Test Data **********************
    @DataProvider
    public Object[][] productIds() {
        return new Object[][]{
                {"1"},{"12"},{"9"}
        };
    }

//********************* Get All Products tests *********************
    @Test
    public void verifyGetAllProducts_validInput(){
        Response resp1 = productClient.getAllProducts();

        Assert.assertEquals(resp1.getStatusCode(),200);

        List<Object> allproducts = resp1.jsonPath().get("$");
        Assert.assertTrue(!allproducts.isEmpty());
        Assert.assertEquals(allproducts.size(),20);

    }

    @Test
    public void verifyGetAllProducts_invalidEndpoint(){
        Response resp2 = BaseClient.get("invalidPdctEndPoint");
        Assert.assertEquals(resp2.getStatusCode(),404);
    }
//********************** Get Single Product tests ***************
    @Test(dataProvider = "productIds")
    public void verifyGetProductById_validInput(String id){
        Response resp3 = productClient.getProductById(id);
        Assert.assertEquals(resp3.getStatusCode(),200);
        Assert.assertEquals(resp3.jsonPath().get("id").toString(),id);
        /*Assert.assertEquals(resp3.jsonPath().get("category"), "electronics");
        Assert.assertTrue(resp3.jsonPath().get("title").toString().contains("4TB Gaming Drive"));*/
    }

    @Test
    public void verifyGetProductById_invalidInput(){
        Response resp4 = productClient.getProductById("25");
        Assert.assertEquals(resp4.getStatusCode(),200);
    }

    @Test
    public void verifyGetProductById_invalidEndpoint(){
        Response resp5 = BaseClient.get("product/ff");
        Assert.assertEquals(resp5.getStatusCode(),404);
    }
//********************* Create Product Tests ********************
    @Test
    public void verifyCreateProduct_validInput(){

        Response resp6 = productClient.createProduct(someProduct());
        Assert.assertEquals(resp6.getStatusCode(),201);
        Assert.assertEquals(resp6.jsonPath().get("title"),"Test Product Title");

        Assert.assertEquals(resp6.jsonPath().getFloat("price"),69.99f);
        Assert.assertEquals(resp6.jsonPath().get("category"),"media");

    }
    public Product someProduct(){
        Rating testRating = new Rating();
        testRating.setRate(3.5f);
        testRating.setCount(10);

        Product testProduct = new Product();
        testProduct.setId("25");
        testProduct.setCategory("media");
        testProduct.setTitle("Test Product Title");
        testProduct.setImage("someimageurlhere");
        testProduct.setDescription("This is a test product created purely for testing purposes!");
        testProduct.setPrice(69.99f);
        testProduct.setRating(testRating);

        return testProduct;
    }

    @Test
    public void verifyCreateProduct_invalidEndpoint(){
        Response resp7 = BaseClient.post("/product");
        Assert.assertEquals(resp7.getStatusCode(),415);
    }
//********************** Delete Product tests ****************
    @Test
    public void verifyDeleteProductById_validInput(){
        Response resp8 = productClient.deleteProduct("3");
        Assert.assertEquals(resp8.getStatusCode(),200);
        Assert.assertEquals(resp8.jsonPath().getInt("id"),3);
        Assert.assertEquals(resp8.jsonPath().getFloat("rating.rate"),4.7f);
        Assert.assertEquals(resp8.jsonPath().get("category"),"men's clothing");

    }

    @Test
    public void verifyDeleteProductById_invalidId(){
        Response resp9 = productClient.deleteProduct("z");
        Assert.assertEquals(resp9.getStatusCode(),400);
        Assert.assertEquals(resp9.jsonPath().get("status"),"error");
        Assert.assertEquals(resp9.jsonPath().get("message"),"product id should be provided");
    }

//************************** Update Product Tests *******************

    @Test
    public void verifyUpdateProductById_validInput(){
        Response resp11 = productClient.updateProduct(someProduct(),"6");
        Assert.assertEquals(resp11.getStatusCode(),200);
        Assert.assertEquals(resp11.jsonPath().getInt("id"),6);

    }

    @Test
    public void verifyUpdateProductById_invalidId(){
        Response resp12 = productClient.updateProduct(someProduct(),"we^#");
        Assert.assertEquals(resp12.getStatusCode(),400);
    }
    @Test
    public void verifyUpdateProductById_invalidEndpoint(){
        Response resp13 = BaseClient.put("invalidPdctEndPoint");
        Assert.assertEquals(resp13.getStatusCode(),404);
    }
}
