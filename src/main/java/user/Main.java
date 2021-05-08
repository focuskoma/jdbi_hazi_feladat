package user;

import com.github.javafaker.Faker;
import java.time.ZoneId;
import java.util.Locale;
import org.apache.commons.codec.digest.DigestUtils;
import org.jdbi.v3.core.*;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

public class Main {
    public static void main(String[] args) {
        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:test");
        jdbi.installPlugin(new SqlObjectPlugin());

        try(Handle handle = jdbi.open()){
            UserDao dao = handle.attach(UserDao.class);
            dao.createTable();

            var faker = new Faker(new Locale("hu"));
            for(int i = 0; i < 20; i++) {
                User user = User.builder()
                        .username(faker.name().name())
                        .password(DigestUtils.md5Hex(faker.internet().password()))
                        .name(faker.name().fullName())
                        .email(faker.internet().emailAddress())
                        .gender(faker.options().option(User.Gender.values()))
                        .birthDate(faker.date().birthday().toInstant()
                                .atZone(ZoneId.systemDefault()).toLocalDate())
                        .enabled(faker.bool().bool())
                        .build();
                dao.insert(user);
            }

            User user1 = User.builder()
                    .username(faker.name().name())
                    .password(DigestUtils.md5Hex(faker.internet().password()))
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .gender(faker.options().option(User.Gender.values()))
                    .birthDate(faker.date().birthday().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate())
                    .enabled(faker.bool().bool())
                    .build();

            User user2 = User.builder()
                    .username(faker.name().name())
                    .password(DigestUtils.md5Hex(faker.internet().password()))
                    .name(faker.name().fullName())
                    .email(faker.internet().emailAddress())
                    .gender(faker.options().option(User.Gender.values()))
                    .birthDate(faker.date().birthday().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate())
                    .enabled(faker.bool().bool())
                    .build();

            long szam = dao.insert(user1);
            dao.insert(user2);
            System.out.println("User1: " + dao.findById(szam));
            System.out.println("User2: " + dao.findByUsername(user2.getUsername()));
            System.out.println("Listázás1: ");
            dao.list().forEach(System.out::println);
            dao.delete(dao.findById(10).get());
            dao.delete(dao.findById(13).get());
            System.out.println("Listázás2: ");
            dao.list().forEach(System.out::println);
        }
    }
}