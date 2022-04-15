package ru.gb.retrofit.endpoints;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import ru.gb.retrofit.dto.CategoryDto;

public interface CategoryService {

    @GET("categories/{id}")
    Call<CategoryDto> getCategory(@Path("id") int id);
}
