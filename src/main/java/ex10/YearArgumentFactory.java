package ex10;

import java.sql.Types;
import java.time.Year;

import org.jdbi.v3.core.argument.AbstractArgumentFactory;
import org.jdbi.v3.core.argument.Argument;
import org.jdbi.v3.core.config.ConfigRegistry;

public class YearArgumentFactory extends AbstractArgumentFactory<Year> {

    public YearArgumentFactory() {
        super(Types.INTEGER);
    }

    @Override
    protected Argument build(Year value, ConfigRegistry config) {
        return (position, statement, ctx) -> statement.setInt(position, value.getValue());
    }

}
