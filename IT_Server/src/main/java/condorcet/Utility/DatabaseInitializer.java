package condorcet.Utility;

import java.util.List;

import condorcet.Enums.Roles;
import condorcet.Models.Entities.User;
import condorcet.Services.UserService;

public class DatabaseInitializer {
	private final UserService userService;

    public DatabaseInitializer(UserService userService) {
        this.userService = userService;
    }

    /**
     * Проверяет, пуста ли таблица User, и добавляет администратора, если это так.
     */
    public void initializeAdminAccount() {
        List<User> users = userService.findAllEntities();
        if (users.isEmpty()) {
            // Создание администратора
            User admin = new User();
            admin.setLogin("admin");
            admin.setPassword("admin");
            admin.setRole(Roles.Admin);
            userService.saveEntity(admin);
        }
    }
}
