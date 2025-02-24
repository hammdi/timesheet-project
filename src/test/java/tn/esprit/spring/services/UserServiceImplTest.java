/*package tn.esprit.spring.services;


//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Disabled;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.fail;

//@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserServiceImplTest {
@Disabled("Désactivé pour éviter l'exécution du test SpringBoot")
 @Test
    void testAddUser() {
        // Test vierge pour addUser (à compléter)
    }
 @Test
    void testShouldFailIfEnvVariableIsSet() {
        String failTest = System.getenv("TIMESHEET_TESTS_FAIL");
        if ("True".equalsIgnoreCase(failTest)) {
            fail("Le test a échoué car TIMESHEET_TESTS_FAIL est défini sur True");
        }
    }
}*/
/*package tn.esprit.spring.services;

import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Disabled;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Optional;
import tn.esprit.spring.entities.User;  // Pour la classe User
import tn.esprit.spring.repositories.UserRepository;  // Pour la classe UserRepository

@TestMethodOrder(OrderAnnotation.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialisation des Mocks
        testUser = new User(1L, "Hamdi", "hamdi@example.com");
    }

    @Disabled("Désactivé pour éviter l'exécution du test SpringBoot")
    @Test
    void testShouldFailIfEnvVariableIsSet() {
        String failTest = System.getenv("TIMESHEET_TESTS_FAIL");
        if ("True".equalsIgnoreCase(failTest)) {
            fail("Le test a échoué car TIMESHEET_TESTS_FAIL est défini sur True");
        }
    }

    @Test
    void testAddUser() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);
        
        User result = userService.addUser(testUser);

        assertNotNull(result);
        assertEquals("Hamdi", result.getName());
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void testUpdateUser() {
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        User updatedUser = userService.updateUser(testUser);

        assertNotNull(updatedUser);
        verify(userRepository, times(1)).save(testUser);
    }

    @Test
    void testDeleteUser() {
        doNothing().when(userRepository).deleteById(testUser.getId());

        userService.deleteUser(testUser.getId());

        verify(userRepository, times(1)).deleteById(testUser.getId());
    }

    @Test
    void testRetrieveUser() {
        when(userRepository.findById(testUser.getId())).thenReturn(Optional.of(testUser));

        User retrievedUser = userService.retrieveUser(testUser.getId());

        assertNotNull(retrievedUser);
        assertEquals(testUser.getId(), retrievedUser.getId());
        verify(userRepository, times(1)).findById(testUser.getId());
    }
}*/
package tn.esprit.spring.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.entities.User;
import tn.esprit.spring.repository.UserRepository;
import tn.esprit.spring.services.UserServiceImpl;

import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplMockTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testAddUser() {
        // Préparation des données de test
        Role role = Role.CHEF_DEPARTEMENT;  // Exemple de rôle
        Date dateNaissance = new Date();  // Date actuelle pour la naissance
        User user = new User("John", "Doe", dateNaissance, role);  // Création d'un utilisateur

        // Simulation du comportement du repository
        when(userRepository.save(any(User.class))).thenReturn(user);  // Mock du save

        // Appel de la méthode à tester
        User result = userService.addUser(user);

        // Vérifications
        assertNotNull(result);  // Vérifie que l'utilisateur retourné n'est pas nul
        assertEquals("John", result.getLastName());  // Vérifie le prénom
        assertEquals("Doe", result.getLastName());  // Vérifie le nom
        assertEquals(role, result.getRole());  // Vérifie le rôle
        assertEquals(dateNaissance, result.getDateNaissance());  // Vérifie la date de naissance
        verify(userRepository, times(1)).save(user);  // Vérifie que save a été appelé une fois
    }

    @Test
    public void testUpdateUser() {
        User user = new User("John", "Doe", new Date(), Role.TECHNICIEN);

        // Lenient stub if needed (else remove this stub)
        lenient().when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userService.updateUser(user);

        // Perform any verifications or assertions you need
        verify(userRepository).save(user); // Example of checking save method call
    }

    @Test
    public void testDeleteUser() {
        User user = new User("John", "Doe", new Date(), Role.TECHNICIEN);

        // Lenient stub if needed (else remove this stub)
        lenient().when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));

        userService.deleteUser(String.valueOf(1L));

        // Verify if deleteById was called
        verify(userRepository).deleteById(anyLong());
    }


}

