package io.ermdev.ecloth.webservice.item;

import io.ermdev.ecloth.data.exception.EntityNotFoundException;
import io.ermdev.ecloth.data.service.CategoryService;
import io.ermdev.ecloth.model.entity.Category;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
@Path("category")
public class CategoryResource {

    private CategoryService categoryService;

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Path("{categoryId}")
    @GET
    public Response getById(@PathParam("categoryId") Long categoryId) {
        try {
            Category category = categoryService.findById(categoryId);
            return Response.status(Response.Status.FOUND).entity(category).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getAll() {
        try {
            List<Category> categories = categoryService.findAll();
            return Response.status(Response.Status.FOUND).entity(categories).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response add(Category category) {
        try {
            category = categoryService.add(category);
            return Response.status(Response.Status.FOUND).entity(category).build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("{categoryId}")
    @PUT
    public Response updateById(@PathParam("categoryId") Long categoryId, Category category) {
        try {
            category = categoryService.updateById(categoryId, category);
            return Response.status(Response.Status.FOUND).entity(category).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @Path("{categoryId}")
    @DELETE
    public Response deleteById(@PathParam("categoryId") Long categoryId) {
        try {
            final Category category = categoryService.deleteById(categoryId);
            return Response.status(Response.Status.FOUND).entity(category).build();
        } catch (EntityNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
