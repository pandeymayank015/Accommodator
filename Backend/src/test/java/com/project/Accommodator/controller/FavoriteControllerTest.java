package com.project.Accommodator.controller;

import com.project.Accommodator.model.Favorite;
import com.project.Accommodator.model.Posting;
import com.project.Accommodator.service.FavoriteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class FavoriteControllerTest {
    @Autowired
    private FavoriteController favoriteControllerMock;
    FavoriteService favoriteServiceMock;
    @BeforeEach
    public void setup(){
        favoriteServiceMock = mock(FavoriteService.class);
        favoriteControllerMock.FavoriteService = favoriteServiceMock;
    }
    @Test
    public void objectCreated() throws Exception {
        assertNotNull(favoriteControllerMock);
    }
    @Test
    public void testGetFavoriteById() throws Exception{
        int id = 1;
        Iterable<Posting> expectedPostingList = Arrays.asList(
                new Posting(1),
                new Posting(2)
        );
        when(favoriteServiceMock.getFavoriteById(id)).thenReturn(expectedPostingList);

        Iterable<Posting> actualPostingList = favoriteControllerMock.getFavoriteById(id);

        assertEquals(expectedPostingList, actualPostingList);

    }

    @Test
    public void testCreateFavorite() throws Exception{
        Favorite expectedFavorite = new Favorite(1,1,1);
        when(favoriteServiceMock.createFavorite(expectedFavorite)).thenReturn(expectedFavorite);
        Favorite actualFavorite = favoriteControllerMock.createFavorite(expectedFavorite);
        assertEquals(expectedFavorite, actualFavorite);

    }


}