package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import qna.config.TestDataSourceConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestDataSourceConfig
public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");

    @Autowired
    private UserRepository userRepository;

    @DisplayName("save 검증")
    @Test
    void saveTest() {
        equals(JAVAJIGI, userRepository.save(JAVAJIGI));
    }

    @DisplayName("findByUserId 검증")
    @Test
    void findByUserIdTest() {
        userRepository.save(JAVAJIGI);
        userRepository.save(SANJIGI);

        equals(JAVAJIGI, userRepository.findByUserId(JAVAJIGI.getUserId())
                                       .orElseThrow(IllegalArgumentException::new));

        equals(SANJIGI, userRepository.findByUserId(SANJIGI.getUserId())
                                      .orElseThrow(IllegalArgumentException::new));
    }

    private void equals(User expected, User actual) {
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getPassword(), actual.getPassword());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getEmail(), actual.getEmail());
    }
}