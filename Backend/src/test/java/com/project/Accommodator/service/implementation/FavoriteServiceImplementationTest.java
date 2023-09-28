package com.project.Accommodator.service.implementation;

import com.project.Accommodator.model.Favorite;
import com.project.Accommodator.model.Posting;
import com.project.Accommodator.repository.FavoriteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class FavoriteServiceImplementationTest {
    @Mock
    private FavoriteRepository favoriteRepositoryMock;

    @InjectMocks
    private FavoriteServiceImplementation favoriteServiceImplementationMock;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFavorite() {
        Favorite expectedFavorite = new Favorite(1,2,3);
        when(favoriteRepositoryMock.save(expectedFavorite)).thenReturn(expectedFavorite);
        Favorite actualFavorite = favoriteServiceImplementationMock.createFavorite(expectedFavorite);
        assertEquals(expectedFavorite, actualFavorite);
    }
    @Test
    public void testGetFavoriteById() {
        int id = 1;
        Iterable<Posting> expectedPostingList = Arrays.asList(
                new Posting(1),
                new Posting(2)
        );
        when(favoriteRepositoryMock.findFavoritesById(id)).thenReturn((List<Posting>) expectedPostingList);
        Iterable<Posting> actualPostingList = favoriteServiceImplementationMock.getFavoriteById(id);
        assertEquals(expectedPostingList, actualPostingList);
    }

}