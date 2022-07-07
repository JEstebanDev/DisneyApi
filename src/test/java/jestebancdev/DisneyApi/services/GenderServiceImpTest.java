package jestebancdev.DisneyApi.services;

import jestebancdev.DisneyApi.model.Gender;
import jestebancdev.DisneyApi.repository.IGenderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author Juan Esteban Castaño Holguin castanoesteban9@gmail.com 7/7/2022
 */
@ExtendWith(MockitoExtension.class)
class GenderServiceImpTest {

    @Mock
    private IGenderRepository genderRepository;
    @InjectMocks
    private GenderServiceImp genderServiceImp;
    private AutoCloseable autoCloseable;
    private Gender gender;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        gender = new Gender();
        gender.setIdGender(1L);
        gender.setName("Accion");
        gender.setImage("url.image");
    }

    @AfterEach
    void Destroy() throws Exception {
        autoCloseable.close();
    }

    @Test
    void create() {
        Gender gender1 = new Gender();
        when(genderRepository.save(any(Gender.class))).thenReturn(gender1);
        Gender gender2 = genderServiceImp.create(gender);
        assertNotNull(gender2);
        assertSame(gender2.getIdGender(), gender1.getIdGender());
        assertSame(gender2.getName(), gender1.getName());
        assertSame(gender2.getImage(), gender1.getImage());
    }

    @Test
    void read() {
        Collection<Gender> gender=genderRepository.findAll();
        assertNotNull(gender);
    }

    @Test
    void delete() {
        genderRepository.deleteById(1L);
        assertEquals(genderRepository.findById(1L), Optional.empty());
    }
}