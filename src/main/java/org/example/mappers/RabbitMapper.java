/*
 * Created by IntelliJ IDEA Rabbit SQL plugin.
 * User: chengyuxing
 * Update: 2024-11-13 01:07:15
 */

package org.example.mappers;

import com.github.chengyuxing.common.DataRow;
import com.github.chengyuxing.sql.page.IPageable;
import com.github.chengyuxing.sql.PagedResource;
import com.github.chengyuxing.sql.annotation.*;
import com.github.chengyuxing.sql.types.Param;

import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import org.example.entity.Rabbit;

// Rabbit SQL plugin - Your imports  //CODE-BEGIN:imports

// Rabbit SQL plugin - End of your imports  //CODE-END:imports

/**
* rabbit-sql quick start demo.
*/
@XQLMapper("rabbit")
public interface RabbitMapper {
    // Rabbit SQL plugin - Your methods  //CODE-BEGIN:methods

    // Rabbit SQL plugin - End of your methods  //CODE-END:methods

    /**
     * Get a rabbit to help you!
     *
     * @param id id
     * @return Rabbit
     */
    @XQL(type = SqlStatementType.query)
    Rabbit findRabbit(
            @Arg("id") Object id
    );

}
