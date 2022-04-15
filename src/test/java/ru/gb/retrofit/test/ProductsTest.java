package ru.gb.retrofit.test;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;
import ru.gb.retrofit.dto.ProductDto;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.gb.retrofit.util.RetrofitUtil.getCategoryService;
import static ru.gb.retrofit.util.RetrofitUtil.getProductsService;

public class ProductsTest {
    ProductDto productDto;
    int productId;

    @SneakyThrows
    @BeforeEach
    void setUp() {
        productDto = new ProductDto()
                .withCategoryTitle(Objects.requireNonNull(getCategoryService().getCategory(1).execute().body()).getTitle())
                .withTitle(new Faker().food().ingredient())
                .withPrice(300);
    }

    @SneakyThrows
    @Test
    void createProductTest() {
        Response<ProductDto> productDtoResponse = getProductsService().createProducts(productDto)
                .execute();
        assertThat(productDtoResponse.isSuccessful()).isTrue();
        assertThat(productDtoResponse.body())
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(productDto);
        productId = Objects.requireNonNull(productDtoResponse.body()).getId();
    }

    @SneakyThrows
    @AfterEach
    void tearDown() {
        assertThat(getProductsService().deleteProducts(productId).execute().isSuccessful())
                .isTrue();
    }
}
